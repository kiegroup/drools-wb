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
package org.drools.workbench.screens.scenariosimulation.kogito.client.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import com.google.gwt.core.client.GWT;
import jsinterop.base.Js;
import org.drools.workbench.screens.scenariosimulation.kogito.client.cloned.feel.BuiltInType;
import org.drools.workbench.screens.scenariosimulation.kogito.client.cloned.feel.MapBackedType;
import org.drools.workbench.screens.scenariosimulation.kogito.client.cloned.feel.Type;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDRGElement;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDecision;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDefinitions;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITInformationItem;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITInputData;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITItemDefinition;

import static org.drools.scenariosimulation.api.utils.ConstantsHolder.VALUE;

/**
 * Abstract class to provide methods shared by <b>runtime</b> and <b>testing</b> environments.
 * <p>
 * Most of this code is cloned/adapted from ScenarioSimulation backend and dmn core
 */
public abstract class AbstractKogitoDMNService implements KogitoDMNService {

    public static final String URI_FEEL = "http://www.omg.org/spec/DMN/20180521/FEEL/";
    private static final DMNType UNKNOWN = new DMNType(URI_FEEL,
                                                       BuiltInType.UNKNOWN.getName(),
                                                       null, true, false, null, null,
                                                       BuiltInType.UNKNOWN);
    private static final QName TYPEREF_QNAME = new QName("", "typeRef", "");

    @Override
    public FactModelTuple getFactModelTuple(final JSITDefinitions jsitDefinitions) {
        SortedMap<String, FactModelTree> visibleFacts = new TreeMap<>();
        SortedMap<String, FactModelTree> hiddenFacts = new TreeMap<>();
        ErrorHolder errorHolder = new ErrorHolder();
        Map<String, DMNType> dmnTypesMap = getDMNTypesMap(jsitDefinitions.getItemDefinition(), jsitDefinitions.getNamespace());
        final List<JSITDRGElement> jsitdrgElements = jsitDefinitions.getDrgElement();
        for (int i = 0; i < jsitdrgElements.size(); i++) {
            final JSITDRGElement jsitdrgElement = Js.uncheckedCast(jsitdrgElements.get(i));
            if (JSITInputData.instanceOf(jsitdrgElement)) {
                try {
                    JSITInputData jsitInputData = Js.uncheckedCast(jsitdrgElement);
                    final JSITInformationItem jsitInputDataVariable = jsitInputData.getVariable();
                    DMNType type = getDMNTypeFromMaps(dmnTypesMap, JSITInformationItem.getOtherAttributesMap(jsitInputDataVariable));
                    checkTypeSupport(type, errorHolder, jsitInputData.getName());
                    visibleFacts.put(jsitInputData.getName(), createTopLevelFactModelTree(jsitInputData.getName(), type, hiddenFacts, FactModelTree.Type.INPUT));
                } catch (Exception e) {
                    GWT.log("Exception " + e.getMessage(), e);
                }
            } else if (JSITDecision.instanceOf(jsitdrgElement)) {
                JSITDecision jsitDecision = Js.uncheckedCast(jsitdrgElement);
                final JSITInformationItem jsitDecisionVariable = jsitDecision.getVariable();
                DMNType type = getDMNTypeFromMaps(dmnTypesMap, JSITInformationItem.getOtherAttributesMap(jsitDecisionVariable));
                checkTypeSupport(type, errorHolder, jsitdrgElement.getName());
                try {
                    visibleFacts.put(jsitDecisionVariable.getName(), createTopLevelFactModelTree(jsitDecisionVariable.getName(), type, hiddenFacts, FactModelTree.Type.DECISION));
                } catch (Exception e) {
                    GWT.log("Exception " + e.getMessage(), e);
                }
            }
        }
        FactModelTuple toReturn = new FactModelTuple(visibleFacts, hiddenFacts);
        errorHolder.getMultipleNestedCollection().forEach(toReturn::addMultipleNestedCollectionError);
        errorHolder.getMultipleNestedObject().forEach(toReturn::addMultipleNestedObjectError);
        return toReturn;
    }

    protected DMNType getDMNTypeFromMaps(Map<String, DMNType> dmnTypesMap, Map<QName, String> source) {
        String typeRef = source.get(TYPEREF_QNAME);
        return dmnTypesMap.get(typeRef);
    }

    protected Map<String, DMNType> getDMNTypesMap(final List<JSITItemDefinition> jsitItemDefinitions,
                                                  final String nameSpace) {
        Map<String, DMNType> toReturn = new HashMap<>();
        for (BuiltInType type : BuiltInType.values()) {
            for (String name : type.getNames()) {
                DMNType feelPrimitiveType;
                if (type == BuiltInType.UNKNOWN) {
                    // already added, skip it
                    continue;
                } else if (type == BuiltInType.LIST) {
                    feelPrimitiveType = new DMNType(URI_FEEL, name, null, false, UNKNOWN, type);
                } else if (type == BuiltInType.CONTEXT) {
                    feelPrimitiveType = new DMNType(URI_FEEL, name, null, false, true, Collections.emptyMap(), null, type);
                } else {
                    feelPrimitiveType = new DMNType(URI_FEEL, name, null, false, false, null, null, type);
                }
                toReturn.put(name, feelPrimitiveType);
            }
        }
        for (int i = 0; i < jsitItemDefinitions.size(); i++) {
            final JSITItemDefinition jsitItemDefinition = Js.uncheckedCast(jsitItemDefinitions.get(i));
            toReturn.put(jsitItemDefinition.getName(), new DMNType(jsitItemDefinition, nameSpace));
        }
        return toReturn;
    }

    /**
     * Recursively visit a <i>composite</i> <code>DMNType</code> to eventually detect and add errors to given <code>ErrorHolder</code>
     * @param type
     * @param errorHolder
     * @param fullPropertyPath
     */
    protected void checkTypeSupport(final DMNType type,
                                    final ErrorHolder errorHolder,
                                    final String fullPropertyPath) {
        internalCheckTypeSupport(type, false, errorHolder, fullPropertyPath, new HashSet<>());
    }

    /**
     * @param type
     * @param alreadyInCollection
     * @param errorHolder
     * @param fullPropertyPath
     * @param alreadyVisited
     */
    protected void internalCheckTypeSupport(final DMNType type,
                                            final boolean alreadyInCollection,
                                            final ErrorHolder errorHolder,
                                            final String fullPropertyPath,
                                            final Set<String> alreadyVisited) {
        alreadyVisited.add(type.getName());
        if (type.isComposite()) {
            for (Map.Entry<String, DMNType> entry : type.getFields().entrySet()) {
                String name = entry.getKey();
                DMNType nestedType = entry.getValue();
                String nestedPath = fullPropertyPath + "." + name;
                if (alreadyInCollection && nestedType.isCollection()) {
                    errorHolder.getMultipleNestedCollection().add(nestedPath);
                }
                if (alreadyInCollection && nestedType.isComposite()) {
                    errorHolder.getMultipleNestedObject().add(nestedPath);
                }
                if (!alreadyVisited.contains(nestedType.getName())) {
                    internalCheckTypeSupport(nestedType,
                                             alreadyInCollection || nestedType.isCollection(),
                                             errorHolder,
                                             nestedPath,
                                             alreadyVisited);
                }
            }
        }
    }

    /**
     * This method is the <b>entry point</b> for <code>FactModelTree</code>. It is the one to be called from the very top level <code>DMNType</code>
     * @param factName
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @return
     * @throws Exception
     */
    protected FactModelTree createTopLevelFactModelTree(final String factName,
                                                        final DMNType type,
                                                        final SortedMap<String, FactModelTree> hiddenFacts,
                                                        final FactModelTree.Type fmType) throws Exception {
        return isToBeManagedAsCollection(type) ? createFactModelTreeForCollection(new HashMap<>(), factName, type, hiddenFacts, fmType, new ArrayList<>()) : createFactModelTreeForNoCollection(new HashMap<>(), factName, factName, type.getName(), type, hiddenFacts, fmType, new ArrayList<>());
    }

    /**
     * Return <code>true</code> if the given <code>DMNType</code> has to be managed as <b>collection</b>
     * @param type
     * @return <code>true</code> if <code>DMNType.isCollection() == true</code> <b>and</b> <code>BaseDMNTypeImpl.getFeelType() != BuiltInType.UNKNOWN</code>
     */
    protected boolean isToBeManagedAsCollection(final DMNType type) {
        boolean toReturn = type.isCollection();
        if (toReturn) {
            Type feelType = getRootType(type);
            // BuiltInType.UNKNOWN is a special case: it is instantiated as collection but it should be considered as single for editing
            if (feelType instanceof BuiltInType && feelType.equals(BuiltInType.UNKNOWN)) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    /**
     * Return <code>true</code> if the given <code>DMNType</code> has to be managed as <b>composite</b>
     * @param type
     * @return <code>true</code> if <code>DMNType.isCollection() == true</code> <b>and</b> <code>BaseDMNTypeImpl.getFeelType() != BuiltInType.UNKNOWN</code>
     */
    protected boolean isToBeManagedAsComposite(final DMNType type) {
        boolean toReturn = type.isComposite();
        if (toReturn) {
            Type feelType = getRootType(type);
            // BuiltInType.CONTEXT is a special case: it is instantiated as composite but has no nested fields so it should be considered as simple for editing
            if (feelType instanceof BuiltInType && feelType.equals(BuiltInType.CONTEXT)) {
                toReturn = false;
            }
        }
        return toReturn;
    }

    /**
     * @param dmnType
     * @return
     */
    protected Type getRootType(final DMNType dmnType) {
        if (dmnType.getFeelType() instanceof BuiltInType) {
            return dmnType.getFeelType();
        } else if (dmnType.getBaseType() != null) {
            return getRootType(dmnType.getBaseType());
        }
        return dmnType.getFeelType();
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isCollection()</code> == <code>true</code>
     * @param genericTypeInfoMap
     * @param factName
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @param alreadyVisited
     * @return
     * @throws Exception if <code>DMNType.isCollection()</code> != <code>true</code>
     */
    protected FactModelTree createFactModelTreeForCollection(final Map<String, List<String>> genericTypeInfoMap,
                                                             final String factName,
                                                             final DMNType type,
                                                             final SortedMap<String, FactModelTree> hiddenFacts,
                                                             final FactModelTree.Type fmType,
                                                             final List<String> alreadyVisited) throws Exception {
        if (!type.isCollection() && !isToBeManagedAsCollection(type)) {
            throw new Exception();
        }
        String typeName = type.getName();
        populateGeneric(genericTypeInfoMap, VALUE, typeName);
        FactModelTree toReturn = createFactModelTreeForSimple(genericTypeInfoMap, factName, List.class.getCanonicalName(), fmType);
        if (!hiddenFacts.containsKey(typeName) && !alreadyVisited.contains(typeName)) {
            alreadyVisited.add(typeName);
            FactModelTree genericFactModelTree = createFactModelTreeForGenericType(new HashMap<>(), typeName, typeName, typeName, type, hiddenFacts, fmType, alreadyVisited);
            hiddenFacts.put(typeName, genericFactModelTree);
        }
        return toReturn;
    }

    /**
     * This method map the given <b>name</b> to <b>List.class.getCanonicalName()</b> inside <b>simpleFields</b>,
     * and map <b>name</b>  to a singleton list containing a newly generated <b>key</b> (path + "." + type) inside <b>genericTypeInfoMap</b>
     * @param genericTypeInfoMap
     * @param fullPropertyPath
     * @param type
     * @return
     */
    protected String populateGeneric(final Map<String, List<String>> genericTypeInfoMap,
                                     final String fullPropertyPath,
                                     final String type) {
        String toReturn = fullPropertyPath;
        genericTypeInfoMap.put(fullPropertyPath, Collections.singletonList(type));
        return toReturn;
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isCollection()</code> != <code>true</code>
     * @param genericTypeInfoMap
     * @param factName
     * @param propertyName
     * @param fullPropertyPath
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @param alreadyVisited
     * @return
     * @throws Exception if <code>DMNType.isCollection()</code> == <code>true</code>
     */
    protected FactModelTree createFactModelTreeForNoCollection(final Map<String, List<String>> genericTypeInfoMap,
                                                               final String factName,
                                                               final String propertyName,
                                                               final String fullPropertyPath,
                                                               final DMNType type,
                                                               final SortedMap<String, FactModelTree> hiddenFacts,
                                                               final FactModelTree.Type fmType,
                                                               final List<String> alreadyVisited) throws Exception {
        if (type.isCollection() && isToBeManagedAsCollection(type)) {
            throw new Exception();
        }
        return isToBeManagedAsComposite(type) ? createFactModelTreeForComposite(genericTypeInfoMap, propertyName, fullPropertyPath, type, hiddenFacts, fmType, alreadyVisited) : createFactModelTreeForSimple(genericTypeInfoMap, factName, type.getName(), fmType);
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code>
     * @param genericTypeInfoMap
     * @param factName
     * @param propertyName
     * @param fullPropertyPath
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @param alreadyVisited
     * @return
     * @throws Exception
     */
    protected FactModelTree createFactModelTreeForGenericType(final Map<String, List<String>> genericTypeInfoMap,
                                                              final String factName,
                                                              final String propertyName,
                                                              final String fullPropertyPath,
                                                              final DMNType type,
                                                              final SortedMap<String, FactModelTree> hiddenFacts,
                                                              final FactModelTree.Type fmType,
                                                              final List<String> alreadyVisited) throws Exception {
        return type.isComposite() ? createFactModelTreeForComposite(genericTypeInfoMap, propertyName, fullPropertyPath, type, hiddenFacts, fmType, alreadyVisited) : createFactModelTreeForSimple(genericTypeInfoMap, factName, type.getName(), fmType);
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isComposite()</code> != <code>true</code>.
     * Returned <code>FactModelTree</code> will have only one single property, whose name is <b>VALUE</b> and whose value is the given <b>propertyClass</b>
     * @param genericTypeInfoMap
     * @param factName
     * @param propertyClass
     * @param fmType
     * @return
     */
    protected FactModelTree createFactModelTreeForSimple(final Map<String, List<String>> genericTypeInfoMap,
                                                         final String factName,
                                                         final String propertyClass,
                                                         final FactModelTree.Type fmType) {
        Map<String, String> simpleProperties = new HashMap<>();
        FactModelTree simpleFactModelTree = new FactModelTree(factName, "", simpleProperties, genericTypeInfoMap, fmType);
        simpleFactModelTree.addSimpleProperty(VALUE, propertyClass);
        simpleFactModelTree.setSimple(true);
        return simpleFactModelTree;
    }

    /**
     * Creates a <code>FactModelTree</code> for <code>DMNType</code> where <code>DMNType.isComposite()</code> == <code>true</code>
     * @param genericTypeInfoMap
     * @param name
     * @param fullPropertyPath
     * @param type
     * @param hiddenFacts
     * @param fmType
     * @param alreadyVisited
     * @return
     * @throws Exception if <code>DMNType.isComposite()</code> != <code>true</code>
     */
    protected FactModelTree createFactModelTreeForComposite(final Map<String, List<String>> genericTypeInfoMap,
                                                            final String name,
                                                            final String fullPropertyPath,
                                                            final DMNType type,
                                                            final SortedMap<String, FactModelTree> hiddenFacts,
                                                            final FactModelTree.Type fmType,
                                                            final List<String> alreadyVisited) throws Exception {
        if (!type.isComposite() && !isToBeManagedAsComposite(type)) {
            throw new Exception();
        }
        Map<String, String> simpleFields = new HashMap<>();
        FactModelTree toReturn = new FactModelTree(name, "", simpleFields, genericTypeInfoMap, fmType);
        for (Map.Entry<String, DMNType> entry : type.getFields().entrySet()) {
            String expandablePropertyName = fullPropertyPath + "." + entry.getKey();
            if (isToBeManagedAsCollection(entry.getValue())) {  // if it is a collection, generate the generic and add as hidden fact a simple or composite fact model tree
                FactModelTree fact = createFactModelTreeForCollection(new HashMap<>(), entry.getKey(), entry.getValue(), hiddenFacts, FactModelTree.Type.UNDEFINED, alreadyVisited);
                simpleFields.put(entry.getKey(), List.class.getCanonicalName());
                genericTypeInfoMap.put(entry.getKey(), fact.getGenericTypeInfo(VALUE));
            } else {
                String typeName = entry.getValue().getName();
                if (entry.getValue().isComposite()) { // a complex type needs the expandable property and then in the hidden map, its fact model tree
                    if (!hiddenFacts.containsKey(typeName) && !alreadyVisited.contains(typeName)) {
                        alreadyVisited.add(typeName);
                        FactModelTree fact = createFactModelTreeForNoCollection(genericTypeInfoMap, entry.getKey(), VALUE, expandablePropertyName, entry.getValue(), hiddenFacts, FactModelTree.Type.UNDEFINED, alreadyVisited);
                        hiddenFacts.put(typeName, fact);
                    }
                    toReturn.addExpandableProperty(entry.getKey(), typeName);
                } else {  // a simple type is just name -> type
                    simpleFields.put(entry.getKey(), typeName);
                }
            }
        }
        return toReturn;
    }

    private static class ErrorHolder {

        Set<String> multipleNestedObject = new TreeSet<>();
        Set<String> multipleNestedCollection = new TreeSet<>();

        public Set<String> getMultipleNestedObject() {
            return multipleNestedObject;
        }

        public Set<String> getMultipleNestedCollection() {
            return multipleNestedCollection;
        }
    }

    private static class DMNType {

        private String namespace;
        private String name;
        private String id;
        private boolean collection;
        private boolean composite;
        private Map<String, DMNType> fields;
        private Type feelType;
        private DMNType baseType;

        public DMNType(JSITItemDefinition itemDefinition, String namespace) {
            this.name = itemDefinition.getName();
            this.id = itemDefinition.getId();
            this.namespace = namespace;
            this.collection = itemDefinition.getIsCollection();
        }

        public DMNType(String namespace, String name, String id, boolean isCollection, DMNType baseType, Type feelType) {
            this.namespace = namespace;
            this.name = name;
            this.id = id;
            this.collection = isCollection;
            this.feelType = feelType;
            this.baseType = baseType;
        }

        public DMNType(String namespace, String name, String id, boolean isCollection, boolean isComposite, Map<String, DMNType> fields, DMNType baseType, Type feelType) {
            this(namespace, name, id, isCollection, baseType, feelType);
            this.fields = fields;
            this.composite = isComposite;
            if (feelType == null) {
                feelType = new MapBackedType(name);
                this.feelType = feelType;
                if (fields != null) {
                    for (Map.Entry<String, DMNType> field : fields.entrySet()) {
                        ((MapBackedType) feelType).addField(field.getKey(), (field.getValue()).getFeelType());
                    }
                }
            }
        }

        public String getNamespace() {
            return namespace;
        }

        public String getName() {
            return name;
        }

        public String getId() {
            return id;
        }

        public boolean isCollection() {
            return collection;
        }

        public boolean isComposite() {
            return composite;
        }

        public Map<String, DMNType> getFields() {
            return fields;
        }

        public Type getFeelType() {
            return feelType;
        }

        public DMNType getBaseType() {
            return baseType;
        }
    }
}
