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

import java.util.List;

import org.drools.scenariosimulation.api.model.ExpressionElement;
import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingValue;
import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.scenariosimulation.api.model.SimulationDescriptor;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionElementType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionElementsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionIdentifierReferenceType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactIdentifierReferenceType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingValueType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingValuesType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingsType;
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
 * Class used to convert from <b>api</b> bean to <b>JSInterop</b> one
 */
public class ApiJSInteropConverter {

    public static JSIScenarioSimulationModelType getJSIScenarioSimulationModelType(ScenarioSimulationModel source) {
        JSIScenarioSimulationModelType toReturn = new JSIScenarioSimulationModelType();
        toReturn.setImports(getImports(source.getImports()));
        toReturn.setSimulation(getSimulation(source.getSimulation()));
        return toReturn;
    }

    protected static JSIImportsType getImports(Imports source) {
        JSIImportsType toReturn = new JSIImportsType();
        JSIWrappedImportsType jsiWrappedImportsType = new JSIWrappedImportsType();
        toReturn.setImports(jsiWrappedImportsType);
        final List<Import> imports = source.getImports();
        if (imports != null) {
            JSIImportType[] _import = new JSIImportType[imports.size()];
            for (int i = 0; i < _import.length; i ++) {
                _import[i] = getImport(imports.get(i));
            }
            jsiWrappedImportsType.setImport(_import);
        }
        return toReturn;
    }

    protected static JSIImportType getImport(Import source) {
        JSIImportType toReturn = new JSIImportType();
        toReturn.setType(source.getType());
        return toReturn;
    }

    protected static JSISimulationType getSimulation(Simulation source) {
        JSISimulationType toReturn = new JSISimulationType();
        ///
        JSISimulationDescriptorType jsiSimulationDescriptorType = new JSISimulationDescriptorType();
        toReturn.setSimulationDescriptor(jsiSimulationDescriptorType);
        populateSimulationDescriptor(jsiSimulationDescriptorType, source.getSimulationDescriptor());
        ///
        final List<Scenario> unmodifiableScenarios = source.getUnmodifiableScenarios();
        JSIScenariosType jsiScenariosType = new JSIScenariosType();
        toReturn.setScenarios(jsiScenariosType);
        JSIScenarioType[] jsiScenarioTypes = new JSIScenarioType[unmodifiableScenarios.size()];
        jsiScenariosType.setScenario(jsiScenarioTypes);
        for (int i = 0; i < jsiScenarioTypes.length; i ++) {
            JSIScenarioType added = new JSIScenarioType();
            populateScenario(added, unmodifiableScenarios.get(i), source.getSimulationDescriptor().getFactMappings());

        }
        return toReturn;
    }

    protected static void populateScenario(JSIScenarioType toPopulate, Scenario source, List<FactMapping> factMappings) {
        JSIFactMappingValuesType factMappingValuesType = new JSIFactMappingValuesType();
        toPopulate.setFactMappingValues(factMappingValuesType);
        final List<FactMappingValue> unmodifiableFactMappingValues = source.getUnmodifiableFactMappingValues();
        JSIFactMappingValueType[] jsiFactMappingValueTypes = new JSIFactMappingValueType[unmodifiableFactMappingValues.size()];
        factMappingValuesType.setFactMappingValue(jsiFactMappingValueTypes);
        for (int i = 0; i < jsiFactMappingValueTypes.length; i ++) {
            FactMappingValue factMappingValue = unmodifiableFactMappingValues.get(i);
            jsiFactMappingValueTypes[i] = new JSIFactMappingValueType();
            jsiFactMappingValueTypes[i].setExpressionIdentifier(getJSIExpressionIdentifierReferenceType(factMappingValue.getExpressionIdentifier(), factMappings));
            jsiFactMappingValueTypes[i].setFactIdentifier(getJSIFactIdentifierReferenceType(factMappingValue.getFactIdentifier(), factMappings));
            jsiFactMappingValueTypes[i].setRawValue(factMappingValue.getRawValue().toString());
        }
    }

    protected static void populateSimulationDescriptor(JSISimulationDescriptorType toPopulate, SimulationDescriptor source) {
        final List<FactMapping> factMappings = source.getFactMappings();
        JSIFactMappingsType jsiFactMappingsType = new JSIFactMappingsType();
        toPopulate.setFactMappings(jsiFactMappingsType);
        JSIFactMappingType[] jsiFactMappingTypes = new JSIFactMappingType[factMappings.size()];
        jsiFactMappingsType.setFactMapping(jsiFactMappingTypes);
        for (int i = 0; i < jsiFactMappingTypes.length; i ++) {
            jsiFactMappingTypes[i] = getFactMapping(factMappings.get(i));
        }
        toPopulate.setDmoSession(source.getDmoSession());
        toPopulate.setDmnFilePath(source.getDmnFilePath());
        toPopulate.setType(source.getType().name());
        toPopulate.setDmnFilePath(source.getFileName());
        toPopulate.setKieSession(source.getKieSession());
        toPopulate.setKieBase(source.getKieBase());
        toPopulate.setRuleFlowGroup(source.getRuleFlowGroup());
        toPopulate.setDmnNamespace(source.getDmnNamespace());
        toPopulate.setDmnName(source.getDmnName());
        toPopulate.setSkipFromBuild(source.isSkipFromBuild());
    }
    
    protected static JSIFactMappingType getFactMapping(FactMapping source) {
        JSIFactMappingType toReturn = new JSIFactMappingType();
        toReturn.setClassName(source.getClassName());
        toReturn.setExpressionAlias(source.getExpressionAlias());
        toReturn.setExpressionElements(getExpressionElements(source.getExpressionElements()));
        toReturn.setExpressionIdentifier(getExpressionIdentifier(source.getExpressionIdentifier()));
        toReturn.setFactAlias(source.getFactAlias());
        toReturn.setFactIdentifier(getFactIdentifier(source.getFactIdentifier()));
        toReturn.setGenericTypes(source.getGenericTypes().toArray(new String[0]));
        return toReturn;
    }

    protected static JSIExpressionElementsType getExpressionElements(List<ExpressionElement> expressionElements) {
        JSIExpressionElementsType toReturn = new JSIExpressionElementsType();
        JSIExpressionElementType[] jsiExpressionElementTypes = new JSIExpressionElementType[expressionElements.size()];
        toReturn.setExpressionElement(jsiExpressionElementTypes);
        for (int i = 0; i < jsiExpressionElementTypes.length; i ++) {
            jsiExpressionElementTypes[i] = getExpressionElement(expressionElements.get(i));
        }
        return toReturn;
    }

    protected static JSIExpressionElementType getExpressionElement(ExpressionElement expressionElement) {
        JSIExpressionElementType toReturn = new JSIExpressionElementType();
        toReturn.setStep(expressionElement.getStep());
        return toReturn;
    }

    protected static JSIExpressionIdentifierType getExpressionIdentifier(ExpressionIdentifier source) {
        JSIExpressionIdentifierType toReturn = new JSIExpressionIdentifierType();
        toReturn.setName(source.getName());
        toReturn.setType(source.getType().name());
        return toReturn;
    }

    protected static JSIFactIdentifierType getFactIdentifier(FactIdentifier source) {
        JSIFactIdentifierType toReturn = new JSIFactIdentifierType();
        toReturn.setClassName(source.getClassName());
        toReturn.setName(source.getName());
        return toReturn;
    }

    protected static JSIExpressionIdentifierReferenceType getJSIExpressionIdentifierReferenceType(ExpressionIdentifier expressionIdentifier, List<FactMapping> jsiFactMappingTypes) {
        JSIExpressionIdentifierReferenceType toReturn = new JSIExpressionIdentifierReferenceType();
//        String numberPart = "1";
//        if (reference.contains("[") && reference.contains("]")) {
//            numberPart = reference.substring(reference.indexOf("[") +1, reference.indexOf("]"));
//        }
//        try {
//            int index = Integer.parseInt(numberPart) -1;
//            return jsiFactMappingTypes[index].getFactIdentifier();
//        } catch (Throwable t) {
//            GWT.log("Failed to parse reference " + reference, t);
//            return null;
//        }
        return toReturn;
    }

    protected static JSIFactIdentifierReferenceType getJSIFactIdentifierReferenceType(FactIdentifier factIdentifier, List<FactMapping> jsiFactMappingTypes) {
        JSIFactIdentifierReferenceType toReturn = new JSIFactIdentifierReferenceType();
//        String numberPart = "1";
//        if (reference.contains("[") && reference.contains("]")) {
//            numberPart = reference.substring(reference.indexOf("[") +1, reference.indexOf("]"));
//        }
//        try {
//            int index = Integer.parseInt(numberPart) -1;
//            return jsiFactMappingTypes[index].getFactIdentifier();
//        } catch (Throwable t) {
//            GWT.log("Failed to parse reference " + reference, t);
//            return null;
//        }
        return toReturn;
    }
}
