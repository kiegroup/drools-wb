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
import java.util.stream.Collectors;

import jsinterop.base.Js;
import jsinterop.base.JsArrayLike;
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
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingValueType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingValuesType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIGenericTypes;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIImportType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIImportsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIRawValueType;
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
        toReturn.setVersion(source.getVersion());
        toReturn.setImports(getImports(source.getImports()));
        toReturn.setSimulation(getSimulation(source.getSimulation()));
        return toReturn;
    }

    protected static JSIImportsType getImports(Imports source) {
        JSIImportsType toReturn = new JSIImportsType();
        final List<Import> imports = source.getImports();
        JSIWrappedImportsType jsiWrappedImportsType = new JSIWrappedImportsType();
        toReturn.setImports(jsiWrappedImportsType);
        if (imports != null) {
            List<JSIImportType> toSet =imports.stream().map(ApiJSInteropConverter::getImport).collect(Collectors.toList());
            jsiWrappedImportsType.setImport(toSet);
//            JsArrayLike<JSIImportType> jsiImportTypeJsArrayLike = getNativeArray();
//            jsiImportTypeJsArrayLike.setLength(imports.size());
//
//            for (int i = 0; i < imports.size(); i++) {
//                JSIImportType toAdd = getImport(imports.get(i));
//                jsiImportTypeJsArrayLike.setAt(i, toAdd);
//            }
//            jsiWrappedImportsType.setImport(jsiImportTypeJsArrayLike);
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
        JSISimulationDescriptorType jsiSimulationDescriptorType = getSimulationDescriptor(source.getSimulationDescriptor());
        toReturn.setSimulationDescriptor(jsiSimulationDescriptorType);
        final List<Scenario> unmodifiableScenarios = source.getUnmodifiableScenarios();
        JSIScenariosType jsiScenariosType = new JSIScenariosType();
        toReturn.setScenarios(jsiScenariosType);
        List<JSIScenarioType> toSet =unmodifiableScenarios.stream()
                .map(ApiJSInteropConverter::getScenario).collect(Collectors.toList());
        jsiScenariosType.setScenario(toSet);
//        JsArrayLike<JSIScenarioType> jsiScenarioTypes = getNativeArray();
//        jsiScenarioTypes.setLength(unmodifiableScenarios.size());
//        jsiScenariosType.setScenario(jsiScenarioTypes);
//        for (int i = 0; i < unmodifiableScenarios.size(); i++) {
//            JSIScenarioType jsiScenarioType = Js.uncheckedCast(getScenario(unmodifiableScenarios.get(i)));
//            jsiScenarioTypes.setAt(i, jsiScenarioType);
//        }
        return toReturn;
    }

    protected static JSISimulationDescriptorType getSimulationDescriptor(SimulationDescriptor source) {
        JSISimulationDescriptorType toReturn = new JSISimulationDescriptorType();
        final List<FactMapping> factMappings = source.getFactMappings();
        JSIFactMappingsType jsiFactMappingsType = new JSIFactMappingsType();
        toReturn.setFactMappings(jsiFactMappingsType);
        List<JSIFactMappingType> toSet =factMappings.stream()
                .map(ApiJSInteropConverter::getFactMapping).collect(Collectors.toList());
        jsiFactMappingsType.setFactMapping(toSet);
//
//        JsArrayLike<JSIFactMappingType> jsiFactMappingTypes = getNativeArray();
//        jsiFactMappingTypes.setLength(factMappings.size());
//        jsiFactMappingsType.setFactMapping(jsiFactMappingTypes);
//        for (int i = 0; i < factMappings.size(); i++) {
//            JSIFactMappingType jsiFactMappingType = Js.uncheckedCast(getFactMapping(factMappings.get(i)));
//            jsiFactMappingTypes.setAt(i, jsiFactMappingType);
//        }
        toReturn.setDmoSession(source.getDmoSession());
        toReturn.setDmnFilePath(source.getDmnFilePath());
        toReturn.setType(source.getType().name());
        toReturn.setDmnFilePath(source.getFileName());
        toReturn.setKieSession(source.getKieSession());
        toReturn.setKieBase(source.getKieBase());
        toReturn.setRuleFlowGroup(source.getRuleFlowGroup());
        toReturn.setDmnNamespace(source.getDmnNamespace());
        toReturn.setDmnName(source.getDmnName());
        toReturn.setSkipFromBuild(source.isSkipFromBuild());
        return toReturn;
    }

    protected static JSIScenarioType getScenario(Scenario source) {
        JSIScenarioType toReturn = new JSIScenarioType();
        JSIFactMappingValuesType factMappingValuesType = new JSIFactMappingValuesType();
        toReturn.setFactMappingValues(factMappingValuesType);
        final List<FactMappingValue> unmodifiableFactMappingValues = source.getUnmodifiableFactMappingValues();
        List<JSIFactMappingValueType> toSet = new ArrayList<>();
//        JsArrayLike<JSIFactMappingValueType> jsiFactMappingValueTypes = getNativeArray();
//        jsiFactMappingValueTypes.setLength(unmodifiableFactMappingValues.size());
//        factMappingValuesType.setFactMappingValue(jsiFactMappingValueTypes);
        for (int i = 0; i < unmodifiableFactMappingValues.size(); i++) {
            FactMappingValue factMappingValue = unmodifiableFactMappingValues.get(i);
            toSet.add(Js.uncheckedCast(getFactMappingValue(factMappingValue, i > 0)));
//            final JSIFactMappingValueType factMappingValueType = Js.uncheckedCast(getFactMappingValue(factMappingValue, i > 0));
//            jsiFactMappingValueTypes.setAt(i, factMappingValueType);
        }
        return toReturn;
    }


    protected static JSIFactMappingValueType getFactMappingValue(FactMappingValue source, boolean withRawValue) {
        JSIFactMappingValueType toReturn = new JSIFactMappingValueType();
        final JSIExpressionIdentifierType expressionIdentifierType = Js.uncheckedCast(getExpressionIdentifier(source.getExpressionIdentifier()));
        toReturn.setExpressionIdentifier(expressionIdentifierType);
        final JSIFactIdentifierType jsiFactIdentifierReferenceType = Js.uncheckedCast(getFactIdentifier(source.getFactIdentifier()));
        toReturn.setFactIdentifier(jsiFactIdentifierReferenceType);
        if (withRawValue) {
            Object rawValue = source.getRawValue();
            if (rawValue != null) {
                JSIRawValueType jsiRawValueType = Js.uncheckedCast(getRawValueReference(rawValue));
                toReturn.setRawValue(jsiRawValueType);
            }
        }
        return toReturn;
    }

    protected static JSIRawValueType getRawValueReference(Object rawValue) {
        JSIRawValueType toReturn = new JSIRawValueType();
        toReturn.setClazz("string");
        toReturn.setValue(rawValue.toString());
        return toReturn;
    }

    protected static JSIFactMappingType getFactMapping(FactMapping source) {
        JSIFactMappingType toReturn = new JSIFactMappingType();
        toReturn.setClassName(source.getClassName());
        toReturn.setExpressionAlias(source.getExpressionAlias());
        JSIExpressionElementsType jsiExpressionElementsType = Js.uncheckedCast(getExpressionElements(source.getExpressionElements()));
        toReturn.setExpressionElements(jsiExpressionElementsType);
        JSIExpressionIdentifierType jsiExpressionIdentifierType = Js.uncheckedCast(getExpressionIdentifier(source.getExpressionIdentifier()));
        toReturn.setExpressionIdentifier(jsiExpressionIdentifierType);
        toReturn.setFactAlias(source.getFactAlias());
        JSIFactIdentifierType jsiFactIdentifierType = Js.uncheckedCast(getFactIdentifier(source.getFactIdentifier()));
        toReturn.setFactIdentifier(jsiFactIdentifierType);
        List<String> genericTypes = source.getGenericTypes();
        if (genericTypes != null) {
            JSIGenericTypes toSet = new JSIGenericTypes();
            toSet.setString(genericTypes);
            toReturn.setGenericTypes(toSet);
        }
        return toReturn;
    }

    protected static JSIExpressionElementsType getExpressionElements(List<ExpressionElement> expressionElements) {
        JSIExpressionElementsType toReturn = new JSIExpressionElementsType();
        List<JSIExpressionElementType> toSet =expressionElements.stream()
                .map(ApiJSInteropConverter::getExpressionElement).collect(Collectors.toList());
        toReturn.setExpressionElement(toSet);
//        JsArrayLike<JSIExpressionElementType> jsiExpressionElementTypes = getNativeArray();
//        jsiExpressionElementTypes.setLength(expressionElements.size());
//        toReturn.setExpressionElement(jsiExpressionElementTypes);
//        for (int i = 0; i < jsiExpressionElementTypes.getLength(); i++) {
//            JSIExpressionElementType jsiExpressionElementType = Js.uncheckedCast(getExpressionElement(expressionElements.get(i)));
//            jsiExpressionElementTypes.setAt(i, jsiExpressionElementType);
//        }
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

    protected static JSIFactIdentifierType getFactIdentifier(FactIdentifier factIdentifier) {
        JSIFactIdentifierType toReturn = new JSIFactIdentifierType();
        toReturn.setName(factIdentifier.getName());
        toReturn.setClassName(factIdentifier.getClassName());
        return toReturn;
    }

}
