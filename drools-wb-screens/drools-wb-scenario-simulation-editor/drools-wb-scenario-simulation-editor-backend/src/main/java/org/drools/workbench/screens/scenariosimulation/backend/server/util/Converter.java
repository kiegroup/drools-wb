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
package org.drools.workbench.screens.scenariosimulation.backend.server.util;

/**
 * Class used to provide conversion method from/to bc-available beans and more generic ones
 */
public class Converter {

//    public static org.drools.workbench.screens.scenariosimulation.model.PackageDataModelOracleBaselinePayload getSimplePackageDataModelOracleBaselinePayload(PackageDataModelOracleBaselinePayload toConvert) {
//        org.drools.workbench.screens.scenariosimulation.model.PackageDataModelOracleBaselinePayload toReturn = new org.drools.workbench.screens.scenariosimulation.model.PackageDataModelOracleBaselinePayload();
//        final Map<org.drools.workbench.screens.scenariosimulation.oracle.ExtensionKind<?>, List<?>> extensions = convertExtensionsMap(toConvert.getExtensions());
//        toReturn.setAllPackageElements(extensions);
//        toReturn.setCollectionTypes(toConvert.getCollectionTypes());
//        toReturn.setEventTypes(toConvert.getEventTypes());
//        toReturn.setFieldParametersType(toConvert.getFieldParametersType());
//        toReturn.setGlobalTypes(toConvert.getGlobals());
//        toReturn.setJavaEnumDefinitions(toConvert.getJavaEnumDefinitions());
//        final Map<String, List<org.drools.workbench.screens.scenariosimulation.oracle.MethodInfo>> methodInfos = convertMethodInfoMap(toConvert.getMethodInformation());
//        toReturn.setMethodInformation(methodInfos);
//        final Map<String, org.drools.workbench.screens.scenariosimulation.oracle.ModelField[]> modelFields = convertModelFieldMap(toConvert.getModelFields());
//        toReturn.setModelFields(modelFields);
//        toReturn.setModuleName(toConvert.getModuleName());
//        toReturn.setPackageName(toConvert.getPackageName());
//        toReturn.setPackageNames(toConvert.getPackageNames());
//        toReturn.setSuperTypes(toConvert.getSuperTypes());
//        final Map<String, Set<org.drools.workbench.screens.scenariosimulation.oracle.Annotation>> annotations = convertAnnotationMap(toConvert.getTypeAnnotations());
//        toReturn.setTypeAnnotations(annotations);
//        final Map<String, Map<String, Set<org.drools.workbench.screens.scenariosimulation.oracle.Annotation>>> typeFieldsAnnotations = convertTypeFieldsAnnotationMap(toConvert.getTypeFieldsAnnotations());
//        toReturn.setTypeFieldsAnnotations(typeFieldsAnnotations);
//        final Map<String, org.drools.workbench.screens.scenariosimulation.oracle.TypeSource> typeSources = convertTypeSourceMap(toConvert.getTypeSources());
//        toReturn.setTypeSources(typeSources);
//        toReturn.setWorkbenchEnumDefinitions(toConvert.getWorkbenchEnumDefinitions());
//        return toReturn;
//    }
//
//    public static org.drools.workbench.screens.scenariosimulation.oracle.Annotation getSimpleAnnotation(Annotation toConvert) {
//        org.drools.workbench.screens.scenariosimulation.oracle.Annotation toReturn = new org.drools.workbench.screens.scenariosimulation.oracle.Annotation(toConvert.getQualifiedTypeName());
//        toReturn.getParameters().putAll(toConvert.getParameters());
//        return toReturn;
//    }
//
//    public static org.drools.workbench.screens.scenariosimulation.oracle.FieldAccessorsAndMutators getSimpleFieldAccessorsAndMutators(FieldAccessorsAndMutators toConvert) {
//        return org.drools.workbench.screens.scenariosimulation.oracle.FieldAccessorsAndMutators.valueOf(toConvert.name());
//    }
//
//    public static org.drools.workbench.screens.scenariosimulation.oracle.ExtensionKind getSimpleExtensionKind(ExtensionKind toConvert) {
//        return new org.drools.workbench.screens.scenariosimulation.oracle.ExtensionKind() {
//                // TODO ???
//        };
//    }
//
//    public static org.drools.workbench.screens.scenariosimulation.oracle.MethodInfo getSimpleMethodInfo(MethodInfo toConvert) {
//        return new org.drools.workbench.screens.scenariosimulation.oracle.MethodInfo(toConvert.getName(), toConvert.getParams(), toConvert.getReturnClassType(), toConvert.getParametricReturnType(), toConvert.getGenericType());
//    }
//
//    public static org.drools.workbench.screens.scenariosimulation.oracle.ModelField getSimpleModelField(ModelField toConvert) {
//        org.drools.workbench.screens.scenariosimulation.oracle.ModelField.FIELD_CLASS_TYPE fieldClassType = org.drools.workbench.screens.scenariosimulation.oracle.ModelField.FIELD_CLASS_TYPE.valueOf(toConvert.getClassType().name());
//        org.drools.workbench.screens.scenariosimulation.oracle.ModelField.FIELD_ORIGIN fieldOrigin = org.drools.workbench.screens.scenariosimulation.oracle.ModelField.FIELD_ORIGIN.valueOf(toConvert.getOrigin().name());
//        org.drools.workbench.screens.scenariosimulation.oracle.FieldAccessorsAndMutators accessorsAndMutators = getSimpleFieldAccessorsAndMutators(toConvert.getAccessorsAndMutators());
//        return new org.drools.workbench.screens.scenariosimulation.oracle.ModelField(toConvert.getName(), toConvert.getClassName(), fieldClassType, fieldOrigin, accessorsAndMutators, toConvert.getType());
//    }
//
//    public static org.drools.workbench.screens.scenariosimulation.oracle.TypeSource getSimpleTypeSource(TypeSource toConvert) {
//        return org.drools.workbench.screens.scenariosimulation.oracle.TypeSource.valueOf(toConvert.name());
//    }
//
//    public static Map<org.drools.workbench.screens.scenariosimulation.oracle.ExtensionKind<?>, List<?>> convertExtensionsMap(Map<ExtensionKind<?>, List<?>> toConvert) {
//        return toConvert.entrySet().stream().collect(Collectors.toMap(
//                e -> getSimpleExtensionKind(e.getKey()),
//                Map.Entry::getValue
//        ));
//    }
//
//    public static Map<String, List<org.drools.workbench.screens.scenariosimulation.oracle.MethodInfo>> convertMethodInfoMap(Map<String, List<MethodInfo>> toConvert) {
//        return toConvert.entrySet().stream().collect(Collectors.toMap(
//                Map.Entry::getKey,
//                e -> e.getValue().stream().map(Converter::getSimpleMethodInfo).collect(Collectors.toList())
//        ));
//    }
//
//    public static Map<String, org.drools.workbench.screens.scenariosimulation.oracle.ModelField[]> convertModelFieldMap(Map<String, ModelField[]> toConvert) {
//        return toConvert.entrySet().stream().collect(Collectors.toMap(
//                Map.Entry::getKey,
//                e -> Arrays.stream(e.getValue()).map(Converter::getSimpleModelField).toArray(org.drools.workbench.screens.scenariosimulation.oracle.ModelField[]::new)
//        ));
//    }
//
//    public static Map<String, Map<String, Set<org.drools.workbench.screens.scenariosimulation.oracle.Annotation>>> convertTypeFieldsAnnotationMap(Map<String, Map<String, Set<Annotation>>> toConvert) {
//        return toConvert.entrySet().stream().collect(Collectors.toMap(
//                Map.Entry::getKey,
//                e -> convertAnnotationMap(e.getValue())
//        ));
//    }
//
//    public static Map<String, Set<org.drools.workbench.screens.scenariosimulation.oracle.Annotation>> convertAnnotationMap(Map<String, Set<Annotation>> toConvert) {
//        return toConvert.entrySet().stream().collect(Collectors.toMap(
//                Map.Entry::getKey,
//                e -> e.getValue().stream().map(Converter::getSimpleAnnotation).collect(Collectors.toSet())
//        ));
//    }
//
//    public static Map<String, org.drools.workbench.screens.scenariosimulation.oracle.TypeSource> convertTypeSourceMap(Map<String, TypeSource> toConvert) {
//        return toConvert.entrySet().stream().collect(Collectors.toMap(
//                Map.Entry::getKey,
//                e -> getSimpleTypeSource(e.getValue())
//        ));
//    }
}
