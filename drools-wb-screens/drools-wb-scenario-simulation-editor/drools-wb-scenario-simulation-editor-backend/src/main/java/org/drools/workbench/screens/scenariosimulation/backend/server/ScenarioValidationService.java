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
import java.util.Collections;
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
import org.drools.scenariosimulation.api.utils.ScenarioSimulationSharedUtils;
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
import static org.drools.scenariosimulation.backend.util.ScenarioBeanUtil.fillBean;
import static org.drools.scenariosimulation.backend.util.ScenarioBeanUtil.loadClass;
import static org.drools.scenariosimulation.backend.util.ScenarioBeanUtil.navigateToObject;
import static org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError.createFieldChangedError;
import static org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError.createGenericError;
import static org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError.createNodeChangedError;

@ApplicationScoped
public class ScenarioValidationService
        extends AbstractKieContainerService {

    /**
     * Validate the structure of a simulation. It does not validate the content of the cells
     * @param simulation to validate
     * @param path to test scenario file
     * @return list of validation errors
     */
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

    /**
     * Validate structure of a DMN test scenario.
     * Supported checks for each column:
     * - empty column skip
     * - DMN node removed
     * - simple type becomes complex type
     * - node type changed
     * - navigation of data type still valid
     * - field type changed
     * @param simulation
     * @param kieContainer
     * @return
     */
    protected List<FactMappingValidationError> validateDMN(Simulation simulation, KieContainer kieContainer) {
        List<FactMappingValidationError> errors = new ArrayList<>();
        String dmnFilePath = simulation.getSimulationDescriptor().getDmnFilePath();
        DMNModel dmnModel = getDMNModel(kieContainer, dmnFilePath);

        for (FactMapping factMapping : simulation.getSimulationDescriptor().getFactMappings()) {
            if (isToSkip(factMapping)) {
                continue;
            }

            String nodeName = factMapping.getFactIdentifier().getName();

            DMNType rootType;
            try {
                rootType = dmnModel.getDecisionByName(nodeName) != null ?
                        dmnModel.getDecisionByName(nodeName).getResultType() :
                        dmnModel.getInputByName(nodeName).getType();
            } catch (NullPointerException e) {
                errors.add(createNodeChangedError(factMapping, "node not found"));
                continue;
            }

            List<String> steps = expressionElementToString(factMapping);

            // error if direct mapping (= simple type) but it is a composite
            if (steps.size() == 0 && rootType.isComposite()) {
                errors.add(createNodeChangedError(factMapping, rootType.getName()));
                continue;
            }

            if (!isDMNFactMappingValid(factMapping.getFactIdentifier().getClassName(), factMapping, rootType)) {
                errors.add(createNodeChangedError(factMapping, rootType.getName()));
                continue;
            }

            try {
                DMNType fieldType = navigateDMNType(rootType, steps);

                if (!isDMNFactMappingValid(factMapping.getClassName(), factMapping, fieldType)) {
                    errors.add(createFieldChangedError(factMapping, fieldType.getName()));
                }
            } catch (IllegalStateException e) {
                errors.add(createGenericError(factMapping, e.getMessage()));
            }
        }
        return errors;
    }

    /**
     * Validate structure of a RULE test scenario.
     * Supported checks for each column:
     * - empty column skip
     * - instance type removed
     * - navigation of bean still valid
     * - field type changed
     * @param simulation
     * @param kieContainer
     * @return
     */
    protected List<FactMappingValidationError> validateRULE(Simulation simulation, KieContainer kieContainer) {
        List<FactMappingValidationError> errors = new ArrayList<>();
        Map<String, Object> beanInstanceMap = new HashMap<>();
        for (FactMapping factMapping : simulation.getSimulationDescriptor().getFactMappings()) {
            if (isToSkip(factMapping)) {
                continue;
            }

            // try to navigate using all the steps to verify if structure is still valid
            List<String> steps = expressionElementToString(factMapping);

            try {
                String instanceClassName = factMapping.getFactIdentifier().getClassName();

                if (steps.isEmpty()) {
                    // in case of top level simple types just try to load the class
                    loadClass(instanceClassName, kieContainer.getClassLoader());
                } else {
                    Object bean = beanInstanceMap.computeIfAbsent(
                            instanceClassName,
                            className -> fillBean(className, Collections.emptyMap(), kieContainer.getClassLoader()));

                    List<String> stepsToField = steps.subList(0, steps.size() - 1);
                    String lastStep = steps.get(steps.size() - 1);

                    ScenarioBeanWrapper<?> beanBeforeLastStep = navigateToObject(bean, stepsToField, true);

                    ScenarioBeanWrapper<?> beanWrapper = navigateToObject(beanBeforeLastStep.getBean(), Collections.singletonList(lastStep), false);

                    String targetClassName = beanWrapper.getBeanClass() != null ?
                            beanWrapper.getBeanClass().getCanonicalName() :
                            null;

                    // check if target field has valid type
                    if (!Objects.equals(factMapping.getClassName(), targetClassName)) {
                        errors.add(createFieldChangedError(factMapping, targetClassName));
                    }
                }
            } catch (ScenarioException e) {
                errors.add(createGenericError(factMapping, e.getMessage()));
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

    /**
     * Skip descriptive columns (FactMappingType.OTHER), column with no instance (FactIdentifier.EMPTY)
     * and with not expression elements
     * @param factMapping
     * @return
     */
    private boolean isToSkip(FactMapping factMapping) {
        return OTHER.equals(factMapping.getExpressionIdentifier().getType()) ||
                EMPTY.equals(factMapping.getFactIdentifier()) ||
                factMapping.getExpressionElements().size() == 0;
    }

    private DMNType navigateDMNType(DMNType rootType, List<String> steps) {
        DMNType toReturn = rootType;
        for (String step : steps) {
            if (!toReturn.getFields().containsKey(step)) {
                throw new IllegalStateException("Impossible to find field '" + step + "' in type '" + toReturn.getName() + "'");
            }
            toReturn = toReturn.getFields().get(step);
        }
        return toReturn;
    }

    private boolean isDMNFactMappingValid(String typeName, FactMapping factMapping, DMNType dmnType) {
        boolean isCoherent = ScenarioSimulationSharedUtils.isList(typeName) ==
                dmnType.isCollection();
        if (!isCoherent) {
            return false;
        }
        String factMappingType = ScenarioSimulationSharedUtils.isList(typeName) ?
                factMapping.getGenericTypes().get(0) :
                typeName;

        if (Objects.equals(factMappingType, dmnType.getName())) {
            return true;
        }
        return false;
    }
}