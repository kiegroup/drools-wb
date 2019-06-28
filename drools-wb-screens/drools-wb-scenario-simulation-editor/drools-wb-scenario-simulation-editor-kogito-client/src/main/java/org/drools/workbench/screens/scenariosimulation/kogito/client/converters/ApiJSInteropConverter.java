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
import java.util.Optional;

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
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionIdentifierReferenceType;
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
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSISimulationDescriptorReferenceType;
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
        JSIScenarioSimulationModelType toReturn = JSIScenarioSimulationModelType.newInstance();
        toReturn.setVersion(source.getVersion());
        toReturn.setImports(getImports(source.getImports()));
        toReturn.setSimulation(getSimulation(source.getSimulation()));
        return toReturn;
    }

    protected static JSIImportsType getImports(Imports source) {
        JSIImportsType toReturn = JSIImportsType.newInstance();
        final List<Import> imports = source.getImports();
        JSIWrappedImportsType jsiWrappedImportsType = JSIWrappedImportsType.newInstance();
        toReturn.setImports(jsiWrappedImportsType);
        if (imports != null) {
            JsArrayLike<JSIImportType> jsiImportTypeJsArrayLike = getNativeArray();
            jsiImportTypeJsArrayLike.setLength(imports.size());
            for (int i = 0; i < imports.size(); i++) {
                JSIImportType toAdd = getImport(imports.get(i));
                jsiImportTypeJsArrayLike.setAt(i, toAdd);
            }
            jsiWrappedImportsType.setImport(jsiImportTypeJsArrayLike);
        }
        return toReturn;
    }

    protected static JSIImportType getImport(Import source) {
        JSIImportType toReturn = JSIImportType.newInstance();
        toReturn.setType(source.getType());
        return toReturn;
    }

    protected static JSISimulationType getSimulation(Simulation source) {
        JSISimulationType toReturn = JSISimulationType.newInstance();
        ///
        JSISimulationDescriptorType jsiSimulationDescriptorType = getSimulationDescriptor(source.getSimulationDescriptor());
        toReturn.setSimulationDescriptor(jsiSimulationDescriptorType);
        ///
        final List<Scenario> unmodifiableScenarios = source.getUnmodifiableScenarios();
        JSIScenariosType jsiScenariosType = JSIScenariosType.newInstance();
        toReturn.setScenarios(jsiScenariosType);
        JsArrayLike<JSIScenarioType> jsiScenarioTypes = getNativeArray();
        jsiScenarioTypes.setLength(unmodifiableScenarios.size());
        jsiScenariosType.setScenario(jsiScenarioTypes);
        for (int i = 0; i < unmodifiableScenarios.size(); i++) {
            JSIScenarioType jsiScenarioType = Js.uncheckedCast(getScenario(unmodifiableScenarios.get(i), source.getSimulationDescriptor().getFactMappings()));
            jsiScenarioTypes.setAt(i, jsiScenarioType);
        }
        return toReturn;
    }

    protected static JSISimulationDescriptorType getSimulationDescriptor(SimulationDescriptor source) {
        JSISimulationDescriptorType toReturn = JSISimulationDescriptorType.newInstance();
        final List<FactMapping> factMappings = source.getFactMappings();
        JSIFactMappingsType jsiFactMappingsType = JSIFactMappingsType.newInstance();
        toReturn.setFactMappings(jsiFactMappingsType);
        JsArrayLike<JSIFactMappingType> jsiFactMappingTypes = getNativeArray();
        jsiFactMappingTypes.setLength(factMappings.size());
        jsiFactMappingsType.setFactMapping(jsiFactMappingTypes);
        for (int i = 0; i < factMappings.size(); i++) {
            JSIFactMappingType jsiFactMappingType = Js.uncheckedCast(getFactMapping(factMappings.get(i)));
            jsiFactMappingTypes.setAt(i, jsiFactMappingType);
        }
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

    protected static JSIScenarioType getScenario(Scenario source, List<FactMapping> factMappings) {
        JSIScenarioType toReturn = JSIScenarioType.newInstance();
        JSIFactMappingValuesType factMappingValuesType = JSIFactMappingValuesType.newInstance();
        toReturn.setFactMappingValues(factMappingValuesType);
        final List<FactMappingValue> unmodifiableFactMappingValues = source.getUnmodifiableFactMappingValues();
        JsArrayLike<JSIFactMappingValueType> jsiFactMappingValueTypes = getNativeArray();
        jsiFactMappingValueTypes.setLength(unmodifiableFactMappingValues.size());
        factMappingValuesType.setFactMappingValue(jsiFactMappingValueTypes);
        for (int i = 0; i < unmodifiableFactMappingValues.size(); i++) {
            FactMappingValue factMappingValue = unmodifiableFactMappingValues.get(i);
            final JSIFactMappingValueType factMappingValueType = Js.uncheckedCast(getFactMappingValue(factMappingValue, factMappings, i > 0));
            jsiFactMappingValueTypes.setAt(i, factMappingValueType);
        }
        JSISimulationDescriptorReferenceType jsiSimulationDescriptorReferenceType = Js.uncheckedCast(getSimulationDescriptorReference());
        toReturn.setSimulationDescriptor(jsiSimulationDescriptorReferenceType);
        return toReturn;
    }

    protected static JSISimulationDescriptorReferenceType getSimulationDescriptorReference() {
        JSISimulationDescriptorReferenceType toReturn = JSISimulationDescriptorReferenceType.newInstance();
        toReturn.setReference("../../../simulationDescriptor");
        return toReturn;
    }

    protected static JSIFactMappingValueType getFactMappingValue(FactMappingValue source, List<FactMapping> factMappings, boolean withRawValue) {
        JSIFactMappingValueType toReturn = JSIFactMappingValueType.newInstance();
        final JSIExpressionIdentifierReferenceType jsiExpressionIdentifierReferenceType = Js.uncheckedCast(getJSIExpressionIdentifierReferenceType(source.getExpressionIdentifier(), factMappings));
        toReturn.setExpressionIdentifier(jsiExpressionIdentifierReferenceType);
        final JSIFactIdentifierType jsiFactIdentifierReferenceType = Js.uncheckedCast(getFactIdentifier(source.getFactIdentifier(), factMappings));
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
        JSIRawValueType toReturn = JSIRawValueType.newInstance();
        toReturn.setClazz("string");
        toReturn.setValue(rawValue.toString());
        return toReturn;
    }

    protected static JSIFactMappingType getFactMapping(FactMapping source) {
        JSIFactMappingType toReturn = JSIFactMappingType.newInstance();
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
            JSIGenericTypes toSet = JSIGenericTypes.newInstance();
            toSet.setString(genericTypes.toArray(new String[genericTypes.size()]));
            toReturn.setGenericTypes(toSet);
        }
        return toReturn;
    }

    protected static JSIExpressionElementsType getExpressionElements(List<ExpressionElement> expressionElements) {
        JSIExpressionElementsType toReturn = JSIExpressionElementsType.newInstance();
        JsArrayLike<JSIExpressionElementType> jsiExpressionElementTypes = getNativeArray();
        jsiExpressionElementTypes.setLength(expressionElements.size());
        toReturn.setExpressionElement(jsiExpressionElementTypes);
        for (int i = 0; i < jsiExpressionElementTypes.getLength(); i++) {
            JSIExpressionElementType jsiExpressionElementType = Js.uncheckedCast(getExpressionElement(expressionElements.get(i)));
            jsiExpressionElementTypes.setAt(i, jsiExpressionElementType);
        }
        return toReturn;
    }

    protected static JSIExpressionElementType getExpressionElement(ExpressionElement expressionElement) {
        JSIExpressionElementType toReturn = JSIExpressionElementType.newInstance();
        toReturn.setStep(expressionElement.getStep());
        return toReturn;
    }

    protected static JSIExpressionIdentifierType getExpressionIdentifier(ExpressionIdentifier source) {
        JSIExpressionIdentifierType toReturn = JSIExpressionIdentifierType.newInstance();
        toReturn.setName(source.getName());
        toReturn.setType(source.getType().name());
        return toReturn;
    }

//    protected static JSIFactIdentifierType getFactIdentifier(FactIdentifier source) {
//        JSIFactIdentifierType toReturn = JSIFactIdentifierType.newInstance();
//        toReturn.setClassName(source.getClassName());
//        toReturn.setName(source.getName());
//        return toReturn;
//    }

    protected static JSIExpressionIdentifierReferenceType getJSIExpressionIdentifierReferenceType(ExpressionIdentifier expressionIdentifier, List<FactMapping> factMappings) {
        JSIExpressionIdentifierReferenceType toReturn = JSIExpressionIdentifierReferenceType.newInstance();
        String referenceTemplate = "../../../../../simulationDescriptor/factMappings/FactMapping$s/expressionIdentifier";
        final Optional<Integer> optionalIndex = factMappings.stream().filter(factMapping -> factMapping.getExpressionIdentifier().equals(expressionIdentifier)).findFirst().map(factMappings::indexOf);
        optionalIndex.ifPresent(integer -> {
            String replacement = "";
            if (integer > 0) {
                integer += 1;
                replacement = "[" + integer + "]";
            }
            String reference = referenceTemplate.replace("$s", replacement);
            toReturn.setReference(reference);
        });
        return toReturn;
    }

    protected static JSIFactIdentifierType getFactIdentifier(FactIdentifier factIdentifier) {
        JSIFactIdentifierType toReturn = JSIFactIdentifierType.newInstance();
        toReturn.setName(factIdentifier.getName());
        toReturn.setClassName(factIdentifier.getClassName());
        return toReturn;
    }

    protected static JSIFactIdentifierType getFactIdentifier(FactIdentifier factIdentifier, List<FactMapping> factMappings) {
        JSIFactIdentifierType toReturn;
        String referenceTemplate = "../../../../../simulationDescriptor/factMappings/FactMapping$s/factIdentifier";
        final Optional<Integer> optionalIndex = factMappings.stream().filter(factMapping -> factMapping.getFactIdentifier().equals(factIdentifier)).findFirst().map(factMappings::indexOf);
        if (optionalIndex.isPresent()) {
            toReturn = JSIFactIdentifierType.newInstance();
            int integer = optionalIndex.get();
            String replacement = "";
            if (integer > 0) {
                integer += 1;
                replacement = "[" + integer + "]";
            }
            String reference = referenceTemplate.replace("$s", replacement);
            toReturn.setReference(reference);
        } else {
            toReturn = getFactIdentifier(factIdentifier);
        }
        return toReturn;
    }

//    protected static native Object getJSIType(String typeName) /*-{
//        var json = "{\"TYPE_NAME\": \"" + typeName + "\"}";
//        var retrieved = JSON.parse(json)
//        console.log("retrieved " + retrieved);
//        return retrieved
//
//    }-*/;

//    private static class MyCollectionList<E> extends AbstractCollection<E> {
//
//        /**
//         * This field holds a JavaScript array.
//         */
//        private transient E[] array = (E[]) new Object[0];
//
//        @Override
//        public Iterator<E> iterator() {
//            return null;
//        }
//
//        @Override
//        public boolean add(E o) {
//            array[array.length] = o;
//            return true;
//        }
//
//        @Override
//        public int size() {
//            return array.length;
//        }
//    }

    protected static native JsArrayLike getNativeArray() /*-{
        return [];
    }-*/;
}
