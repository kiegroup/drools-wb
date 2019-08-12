/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.drools.scenariosimulation.api.model.ExpressionElement;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.scenariosimulation.backend.runner.ScenarioException;
import org.drools.scenariosimulation.backend.util.ScenarioBeanWrapper;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNType;
import org.uberfire.backend.vfs.Path;

import static org.drools.scenariosimulation.api.model.FactIdentifier.EMPTY;
import static org.drools.scenariosimulation.api.model.FactMappingType.OTHER;
import static org.drools.scenariosimulation.api.model.ScenarioSimulationModel.Type.DMN;
import static org.drools.scenariosimulation.api.model.ScenarioSimulationModel.Type.RULE;
import static org.drools.scenariosimulation.backend.util.DMNSimulationUtils.extractDMNModel;
import static org.drools.scenariosimulation.backend.util.DMNSimulationUtils.extractDMNRuntime;
import static org.drools.scenariosimulation.backend.util.ScenarioBeanUtil.loadClass;
import static org.drools.scenariosimulation.backend.util.ScenarioBeanUtil.navigateToObject;

@ApplicationScoped
public class ScenarioValidationService
        extends AbstractKieContainerService {

    public List<FactMappingValidationError> validateSimulationStructure(Simulation simulation, Path path) {
        KieContainer kieContainer = getKieContainer(path);
        ScenarioSimulationModel.Type type = simulation.getSimulationDescriptor().getType();
        if (DMN.equals(type)) {
            return validateDMN(simulation, kieContainer);
        } else if (RULE.equals(type)) {
            return validateRULE(simulation, kieContainer);
        } else {
            throw new IllegalArgumentException("Only DMN and RULE test scenarios can be validated");
        }
    }

    protected List<FactMappingValidationError> validateDMN(Simulation simulation, KieContainer kieContainer) {
        List<FactMappingValidationError> errors = new ArrayList<>();
        String dmnFilePath = simulation.getSimulationDescriptor().getDmnFilePath();
        DMNModel dmnModel = getDMNModel(kieContainer, dmnFilePath);

        for (FactMapping factMapping : simulation.getSimulationDescriptor().getFactMappings()) {
            if (isToSkip(factMapping)) {
                continue;
            }

            String instanceClassName = factMapping.getFactIdentifier().getClassName();

            String nodeName = factMapping.getFactIdentifier().getName();

            DMNType rootType = dmnModel.getDecisionByName(nodeName) != null ?
                    dmnModel.getDecisionByName(nodeName).getResultType() :
                    dmnModel.getInputByName(nodeName).getType();

            List<String> steps = expressionElementToString(factMapping);

            // verify if node type has changed
            if (!Objects.equals(instanceClassName, rootType.getName())) {
                errors.add(new FactMappingValidationError(
                        factMapping,
                        "Node type has changed: old '" + instanceClassName + "', current '" + rootType.getName() + "'"));
                continue;
            }

            try {
                DMNType fieldType = navigateDMNType(rootType, steps);

                if (!Objects.equals(factMapping.getClassName(), fieldType.getName())) {
                    errors.add(
                            new FactMappingValidationError(
                                    factMapping,
                                    "Field type has changed: old '" + factMapping.getClassName() + "', current '" + fieldType.getName() + "'"));
                }
            } catch (IllegalStateException e) {
                errors.add(new FactMappingValidationError(factMapping, e.getMessage()));
            }
        }
        return errors;
    }

    protected List<FactMappingValidationError> validateRULE(Simulation simulation, KieContainer kieContainer) {
        List<FactMappingValidationError> errors = new ArrayList<>();
        Map<String, Object> beanInstanceMap = new HashMap<>();
        for (FactMapping factMapping : simulation.getSimulationDescriptor().getFactMappings()) {
            if (isToSkip(factMapping)) {
                continue;
            }
            String instanceClassName = factMapping.getFactIdentifier().getClassName();

            Object bean = beanInstanceMap.computeIfAbsent(instanceClassName,
                                                          className -> loadClass(className, kieContainer.getClassLoader()));

            // try to navigate using all the steps to verify if structure is still valid
            List<String> steps = expressionElementToString(factMapping);

            try {
                ScenarioBeanWrapper<?> scenarioBeanWrapper = navigateToObject(bean, steps, true);

                String targetClassName = scenarioBeanWrapper.getBeanClass() != null ?
                        scenarioBeanWrapper.getBeanClass().getCanonicalName() :
                        null;

                // check if target field has
                if (!Objects.equals(factMapping.getClassName(), targetClassName)) {
                    errors.add(
                            new FactMappingValidationError(
                                    factMapping,
                                    "Field type has changed: old '" + factMapping.getClassName() + "', current '" + targetClassName + "'"));
                }
            } catch (ScenarioException e) {
                errors.add(new FactMappingValidationError(factMapping, e.getMessage()));
            }
        }
        return errors;
    }

    protected DMNModel getDMNModel(KieContainer kieContainer, String dmnPath) {
        return extractDMNModel(extractDMNRuntime(kieContainer), dmnPath);
    }

    private List<String> expressionElementToString(FactMapping factMapping) {
        return factMapping.getExpressionElements().stream().map(ExpressionElement::getStep).collect(Collectors.toList());
    }

    private boolean isToSkip(FactMapping factMapping) {
        return OTHER.equals(factMapping.getExpressionIdentifier().getType()) ||
                EMPTY.equals(factMapping.getFactIdentifier());
    }

    private DMNType navigateDMNType(DMNType rootType, List<String> steps) {
        DMNType toReturn = rootType;
        for(String step : steps) {
            if(!toReturn.getFields().containsKey(step)) {
                throw new IllegalStateException("Impossible to find field '" + step + "' in type '" + toReturn.getName() + "'");
            }
            toReturn = toReturn.getFields().get(step);
        }
        return toReturn;
    }
}