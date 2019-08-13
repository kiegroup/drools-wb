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
package org.drools.workbench.screens.scenariosimulation.kogito.client.converters;

import java.util.ArrayList;
import java.util.List;

import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;
import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.scenariosimulation.api.model.SimulationDescriptor;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionElementType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionElementsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingValueType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIGenericTypes;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIImportType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIImportsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenarioSimulationModelType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenarioType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenariosType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSISimulationDescriptorType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSISimulationType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIWrappedImportsType;
import org.kie.soup.project.datamodel.imports.Import;
import org.kie.soup.project.datamodel.imports.Imports;

/**
 * Class used to convert from <b>JSInterop</b> bean to <b>api</b> one
 */
public class JSInteropApiConverter {

    public static ScenarioSimulationModel getScenarioSimulationModel(JSIScenarioSimulationModelType source) {
        ScenarioSimulationModel toReturn = new ScenarioSimulationModel();
        toReturn.setImports(getImports(source.getImports()));
        toReturn.setSimulation(getSimulation(source.getSimulation()));
        return toReturn;
    }

    protected static Imports getImports(JSIImportsType source) {
        Imports toReturn = new Imports();
        if (source != null) {
            final JSIWrappedImportsType imports = source.getImports();
            if (imports != null) {
                final JsArrayLike<JSIImportType> importArray = imports.getImport();
                if (importArray != null) {
                    for (int i = 0; i < importArray.getLength(); i++) {
                        JSIImportType jsiImportType = Js.uncheckedCast(importArray.getAt(i));
                        toReturn.addImport(getImport(jsiImportType));
                    }
                }
            }
        }
        return toReturn;
    }

    protected static Import getImport(JSIImportType source) {
        Import toReturn = new Import();
        toReturn.setType(source.getType());
        return toReturn;
    }

    protected static Simulation getSimulation(JSISimulationType source) {
        Simulation toReturn = new Simulation();
        populateSimulationDescriptor(toReturn.getSimulationDescriptor(), source.getSimulationDescriptor());
        final JSIScenariosType jsiScenariosType = source.getScenarios();
        final JsArrayLike<JSIScenarioType> jsiScenarioTypes = jsiScenariosType.getScenario();
        if (jsiScenarioTypes != null) {
            for (int i = 0; i < jsiScenarioTypes.getLength(); i++) {
                Scenario added = toReturn.addScenario();
                JSIScenarioType jsiScenarioType = Js.uncheckedCast(jsiScenarioTypes.getAt(i));
                populateScenario(added, jsiScenarioType, source.getSimulationDescriptor().getFactMappings().getFactMapping());
            }
        }
        return toReturn;
    }

    protected static void populateScenario(Scenario toPopulate, JSIScenarioType source, JsArrayLike<JSIFactMappingType> jsiFactMappingTypes) {
        final JsArrayLike<JSIFactMappingValueType> factMappingValue = source.getFactMappingValues().getFactMappingValue();
        for (int i = 0; i < factMappingValue.getLength(); i++) {
            JSIFactMappingValueType jsiFactMappingValueType = Js.uncheckedCast(factMappingValue.getAt(i));
            JSIFactIdentifierType factIdentifierType = jsiFactMappingValueType.getFactIdentifier();
            final JSIExpressionIdentifierType expressionIdentifierType = jsiFactMappingValueType.getExpressionIdentifier();
            if (factIdentifierType != null && expressionIdentifierType != null) {
                String value = jsiFactMappingValueType.getRawValue() != null ? jsiFactMappingValueType.getRawValue().getValue() : null;
                toPopulate.addMappingValue(getFactIdentifier(factIdentifierType), getExpressionIdentifier(expressionIdentifierType), value);
            }
        }
    }

    protected static void populateSimulationDescriptor(SimulationDescriptor toPopulate, JSISimulationDescriptorType source) {
        final JsArrayLike<JSIFactMappingType> factMapping = source.getFactMappings().getFactMapping();
        for (int i = 0; i < factMapping.getLength(); i++) {
            JSIFactMappingType jsiFactMappingType = Js.uncheckedCast(factMapping.getAt(i));
            final FactMapping added = toPopulate.addFactMapping(getFactIdentifier(jsiFactMappingType.getFactIdentifier()), getExpressionIdentifier(jsiFactMappingType.getExpressionIdentifier()));
            added.setFactAlias(jsiFactMappingType.getFactAlias());
            added.setExpressionAlias(jsiFactMappingType.getExpressionAlias());
            final JSIGenericTypes genericTypes = jsiFactMappingType.getGenericTypes();
            if (genericTypes != null && genericTypes.getString() != null) {
                final String[] genericString = genericTypes.getString();
                List<String> toSet = new ArrayList<>();
                for (int j = 0; j < genericString.length; j++) {
                    toSet.add(genericString[j]);
                }
                added.setGenericTypes(toSet);
            }
            final JSIExpressionElementsType expressionElements = jsiFactMappingType.getExpressionElements();
            if (expressionElements != null) {
                final JsArrayLike<JSIExpressionElementType> expressionElement = expressionElements.getExpressionElement();
                if (expressionElement != null) {
                    for (int j = 0; j < expressionElement.getLength(); j++) {
                        JSIExpressionElementType jsiExpressionElementType = Js.uncheckedCast(expressionElement.getAt(j));
                        added.addExpressionElement(jsiExpressionElementType.getStep(), jsiFactMappingType.getClassName());
                    }
                }
            }
        }
        toPopulate.setDmoSession(source.getDmoSession());
        toPopulate.setDmnFilePath(source.getDmnFilePath());
        toPopulate.setType(ScenarioSimulationModel.Type.valueOf(source.getType()));
        toPopulate.setDmnFilePath(source.getFileName());
        toPopulate.setKieSession(source.getKieSession());
        toPopulate.setKieBase(source.getKieBase());
        toPopulate.setRuleFlowGroup(source.getRuleFlowGroup());
        toPopulate.setDmnNamespace(source.getDmnNamespace());
        toPopulate.setDmnName(source.getDmnName());
        toPopulate.setSkipFromBuild(source.getSkipFromBuild());
    }

    protected static ExpressionIdentifier getExpressionIdentifier(JSIExpressionIdentifierType source) {
        return new ExpressionIdentifier(source.getName(), FactMappingType.valueOf(source.getType()));
    }

    protected static FactIdentifier getFactIdentifier(JSIFactIdentifierType source) {
        return new FactIdentifier(source.getName(), source.getClassName());
    }
}
