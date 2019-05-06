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
package org.drools.workbench.screens.scenariosimulation.client.datamodel;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintViolation;

import org.drools.workbench.models.datamodel.rule.DSLSentence;
import org.kie.soup.project.datamodel.imports.Imports;
import org.kie.soup.project.datamodel.oracle.Annotation;
import org.kie.soup.project.datamodel.oracle.DropDownData;
import org.kie.soup.project.datamodel.oracle.FieldAccessorsAndMutators;
import org.kie.soup.project.datamodel.oracle.MethodInfo;
import org.kie.soup.project.datamodel.oracle.ModelField;
import org.kie.soup.project.datamodel.oracle.TypeSource;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.callbacks.Callback;

public class SubmarineAsyncPackageDataModelOracle {

    private final List<String> packageNames = Arrays.asList("com", "com.example");
    private final String[] factTypes = {"Author", "Book", String.class.getSimpleName(), Integer.class.getSimpleName()};
    private final String[] fqcnNames = {"com.Author", "com.Book", String.class.getCanonicalName(), Integer.class.getCanonicalName()};
    private final Map<String, String> fqcnNamesMap = Stream.of(new String[][]{
            factTypes,
            fqcnNames,
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    private Path resourcePath;

    public void init(Path resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Path getResourcePath() {
        return resourcePath;
    }

    public List<String> getPackageNames() {
        return packageNames;
    }

    
    public String[] getFactTypes() {
        return factTypes;
    }

    
    public String[] getAllFactTypes() {
        return factTypes;
    }

    
    public String[] getInternalFactTypes() {
        return factTypes;
    }

    
    public String[] getExternalFactTypes() {
        return new String[0];
    }

    
    public String getFQCNByFactName(String factName) {
        return fqcnNamesMap.get(factName);
    }

    
    public String getFactNameFromType(String fqcnName) {
        return null;
    }

    
    public boolean isFactTypeRecognized(String factType) {
        return false;
    }

    
    public void isFactTypeAnEvent(String factType, Callback<Boolean> callback) {
        // Not implemented
    }

    
    public void getTypeSource(String factType, Callback<TypeSource> callback) {

    }

    
    public void getSuperType(String factType, Callback<String> callback) {

    }

    
    public void getSuperTypes(String factType, Callback<List<String>> callback) {

    }

    
    public void getTypeAnnotations(String factType, Callback<Set<Annotation>> callback) {

    }

    
    public void getTypeFieldsAnnotations(String factType, Callback<Map<String, Set<Annotation>>> callback) {

    }

    
    public <T> void validateField(String factType, String fieldName, T value, Callback<Set<ConstraintViolation<T>>> callback) {

    }

    
    public ModelField[] getFieldCompletions(String factType) {
        return new ModelField[0];
    }

    
    public void getFieldCompletions(String factType, FieldAccessorsAndMutators accessor, Callback<ModelField[]> callback) {

    }

    
    public String getFieldType(String variableClass, String fieldName) {
        return null;
    }

    
    public String getFieldClassName(String factName, String fieldName) {
        return null;
    }

    
    public String getParametricFieldType(String factType, String fieldName) {
        return null;
    }

    
    public void getOperatorCompletions(String factType, String fieldName, Callback<String[]> callback) {

    }

    
    public void getConnectiveOperatorCompletions(String factType, String fieldName, Callback<String[]> callback) {

    }

    
    public void getMethodInfos(String factType, Callback<List<MethodInfo>> callback) {

    }

    
    public void getMethodInfos(String factType, int parameterCount, Callback<List<MethodInfo>> callback) {

    }

    
    public void getMethodParams(String factType, String methodNameWithParams, Callback<List<String>> callback) {

    }

    
    public void getMethodInfo(String factName, String methodName, Callback<MethodInfo> callback) {

    }

    
    public String[] getGlobalVariables() {
        return new String[0];
    }

    
    public String getGlobalVariable(String variable) {
        return null;
    }

    
    public boolean isGlobalVariable(String variable) {
        return false;
    }

    
    public void getFieldCompletionsForGlobalVariable(String variable, Callback<ModelField[]> callback) {

    }

    
    public void getMethodInfosForGlobalVariable(String variable, Callback<List<MethodInfo>> callback) {

    }

    
    public String[] getGlobalCollections() {
        return new String[0];
    }

    
    public List<String> getAvailableCollectionTypes() {
        return null;
    }

    
    public List<DSLSentence> getDSLConditions() {
        return null;
    }

    
    public List<DSLSentence> getDSLActions() {
        return null;
    }

    
    public DropDownData getEnums(String type, String field) {
        return null;
    }

    
    public DropDownData getEnums(String factType, String factField, Map<String, String> currentValueMap) {
        return null;
    }

    
    public String[] getEnumValues(String factType, String factField) {
        return new String[0];
    }

    
    public boolean hasEnums(String factType, String factField) {
        return false;
    }

    
    public boolean hasEnums(String qualifiedFactField) {
        return false;
    }

    
    public boolean isDependentEnum(String factType, String factField, String field) {
        return false;
    }

    
    public void filter(Imports imports) {
        // Not implemented
    }

    
    public void filter() {
        // Not implemented
    }

    
    public void setModuleName(String moduleName) {
        // Not implemented
    }

    
    public void setPackageName(String packageName) {
        // Not implemented
    }

    
    public void addModelFields(Map<String, ModelField[]> modelFields) {
        // Not implemented
    }

    
    public void addFieldParametersType(Map<String, String> fieldParametersType) {
        // Not implemented
    }

    
    public void addEventTypes(Map<String, Boolean> eventTypes) {
        // Not implemented
    }

    
    public void addTypeSources(Map<String, TypeSource> typeSources) {
        // Not implemented
    }

    
    public void addSuperTypes(Map<String, List<String>> superTypes) {

    }

    
    public void addTypeAnnotations(Map<String, Set<Annotation>> annotations) {
        // Not implemented
    }

    
    public void addTypeFieldsAnnotations(Map<String, Map<String, Set<Annotation>>> typeFieldsAnnotations) {
        // Not implemented
    }

    
    public void addJavaEnumDefinitions(Map<String, String[]> dataEnumLists) {
        // Not implemented
    }

    
    public void addMethodInformation(Map<String, List<MethodInfo>> methodInformation) {
        // Not implemented
    }

    
    public void addCollectionTypes(Map<String, Boolean> collectionTypes) {
        // Not implemented
    }

    
    public void addPackageNames(List<String> packageNames) {
        // Not implemented
    }

    
    public void addWorkbenchEnumDefinitions(Map<String, String[]> dataEnumLists) {
        // Not implemented
    }

    
    public void addDslConditionSentences(List<DSLSentence> dslConditionSentences) {
        // Not implemented
    }

    
    public void addDslActionSentences(List<DSLSentence> dslActionSentences) {
        // Not implemented
    }

    
    public void addGlobals(Map<String, String> packageGlobalTypes) {
        // Not implemented
    }
}
