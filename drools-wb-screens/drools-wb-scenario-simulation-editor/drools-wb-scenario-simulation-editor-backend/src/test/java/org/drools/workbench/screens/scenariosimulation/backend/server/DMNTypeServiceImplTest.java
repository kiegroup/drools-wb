/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.drools.scenariosimulation.api.model.Settings;
import org.drools.workbench.screens.scenariosimulation.backend.server.exceptions.WrongDMNTypeException;
import org.drools.workbench.screens.scenariosimulation.model.DMNMetadata;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.junit.Before;
import org.junit.Test;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNType;
import org.kie.dmn.api.core.ast.DMNNode;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.kie.dmn.api.core.ast.InputDataNode;
import org.kie.dmn.core.impl.CompositeTypeImpl;
import org.kie.dmn.core.impl.SimpleTypeImpl;
import org.kie.dmn.model.api.Import;
import org.uberfire.backend.vfs.Path;

import static org.drools.scenariosimulation.api.utils.ConstantsHolder.IMPORTED_PREFIX;
import static org.drools.scenariosimulation.api.utils.ConstantsHolder.VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

public class DMNTypeServiceImplTest extends AbstractDMNTest {

    private DMNTypeServiceImpl dmnTypeServiceImpl;

    @Before
    public void init() {
        super.init();
        dmnTypeServiceImpl = new DMNTypeServiceImpl() {
            @Override
            public DMNModel getDMNModel(Path path, String stringPath) {
                return dmnModelLocal;
            }
        };
    }

    @Test
    public void initializeNameAndNamespace() {
        Settings settings = new Settings();
        dmnTypeServiceImpl.initializeNameAndNamespace(settings, mock(Path.class), "");

        assertEquals(NAMESPACE, settings.getDmnNamespace());
        assertEquals(MODEL_NAME, settings.getDmnName());
    }

    @Test
    public void getDMNMetadata() {
        DMNMetadata dmnMetadata = dmnTypeServiceImpl.getDMNMetadata(mock(Path.class), "");
        assertEquals(NAMESPACE, dmnMetadata.getDmnNamespace());
        assertEquals(MODEL_NAME, dmnMetadata.getDmnName());
    }

    @Test
    public void retrieveFactModelTupleDmnList() {
        setDmnModelLocal("dmn-list.dmn", "https://github.com/kiegroup/drools/kie-dmn/_CC8924B0-D729-4D70-9588-039B5824FFE9", "dmn-list");
        FactModelTuple factModelTuple = dmnTypeServiceImpl.retrieveFactModelTuple(mock(Path.class), null);
        // VisibleFacts should match inputs and decisions on given model
        int expectedVisibleFacts = dmnModelLocal.getInputs().size() + dmnModelLocal.getDecisions().size();
        assertEquals(expectedVisibleFacts, factModelTuple.getVisibleFacts().size());
        Map<String, String> importedModelsMap = dmnModelLocal.getDefinitions().getImport().stream()
                .collect(Collectors.toMap(Import::getNamespace, Import::getName));
        // Verify each inputDataNode has been correctly mapped
        dmnModelLocal.getInputs().forEach(inputDataNode -> verifyFactModelTree(factModelTuple, inputDataNode, factModelTuple.getHiddenFacts(), importedModelsMap));
        // Verify each decisionNode has been correctly mapped
        dmnModelLocal.getDecisions().forEach(decisionNode -> verifyFactModelTree(factModelTuple, decisionNode, factModelTuple.getHiddenFacts(), importedModelsMap));
    }

    @Test
    public void retrieveFactModelTupleDmnListComposite() {
        setDmnModelLocal("dmn-list-composite.dmn", "https://github.com/kiegroup/drools/kie-dmn/_25BF2679-3109-488F-9AD1-DDBCCEBBE5F1", "dmn-list-composite");
        FactModelTuple factModelTuple = dmnTypeServiceImpl.retrieveFactModelTuple(mock(Path.class), null);
        // VisibleFacts should match inputs and decisions on given model
        int expectedVisibleFacts = dmnModelLocal.getInputs().size() + dmnModelLocal.getDecisions().size();
        assertEquals(expectedVisibleFacts, factModelTuple.getVisibleFacts().size());
        Map<String, String> importedModelsMap = dmnModelLocal.getDefinitions().getImport().stream()
                .collect(Collectors.toMap(Import::getNamespace, Import::getName));
        // Verify each inputDataNode has been correctly mapped
        dmnModelLocal.getInputs().forEach(inputDataNode -> verifyFactModelTree(factModelTuple, inputDataNode, factModelTuple.getHiddenFacts(), importedModelsMap));
        // Verify each decisionNode has been correctly mapped
        dmnModelLocal.getDecisions().forEach(decisionNode -> verifyFactModelTree(factModelTuple, decisionNode, factModelTuple.getHiddenFacts(), importedModelsMap));
    }

    @Test
    public void retrieveFactModelTupleUsingImportedTrafficViolation() {
        setDmnModelLocal("Using Imported.dmn", "https://kiegroup.org/dmn/_7120AA2F-BEB6-4CB1-BCC7-427A17AEF71D", "Using Imported", "Traffic Violation.dmn");
        FactModelTuple factModelTuple = dmnTypeServiceImpl.retrieveFactModelTuple(mock(Path.class), null);
        // VisibleFacts should match inputs and decisions on given model
        int expectedVisibleFacts = dmnModelLocal.getInputs().size() + dmnModelLocal.getDecisions().size();
        assertEquals(expectedVisibleFacts, factModelTuple.getVisibleFacts().size());
        Map<String, String> importedModelsMap = dmnModelLocal.getDefinitions().getImport().stream()
                .collect(Collectors.toMap(Import::getNamespace, Import::getName));
        // Verify each inputDataNode has been correctly mapped
        dmnModelLocal.getInputs().forEach(inputDataNode -> verifyFactModelTree(factModelTuple, inputDataNode, factModelTuple.getHiddenFacts(), importedModelsMap));
        // Verify each decisionNode has been correctly mapped
        dmnModelLocal.getDecisions().forEach(decisionNode -> verifyFactModelTree(factModelTuple, decisionNode, factModelTuple.getHiddenFacts(), importedModelsMap));
    }

    @Test
    public void createTopLevelFactModelTreeSimpleNoCollection() throws WrongDMNTypeException {
        // Single property retrieve
        DMNType simpleString = getSimpleNoCollection();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", IMPORTED_PREFIX, simpleString, new TreeMap<>(), FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(1, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey(VALUE));
        assertEquals(simpleString.getName(), retrieved.getSimpleProperties().get(VALUE).getTypeName());
        assertFalse(retrieved.getSimpleProperties().get(VALUE).getBaseTypeName().isPresent());
        assertEquals(simpleString.getName(), retrieved.getSimpleProperties().get(VALUE).getPropertyTypeNameToVisualize());
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertTrue(retrieved.getGenericTypesMap().isEmpty());
        assertEquals(IMPORTED_PREFIX, retrieved.getImportPrefix());
    }

    @Test
    public void createTopLevelFactModelTreeSimpleCollection() throws WrongDMNTypeException {
        // Single property collection retrieve
        DMNType simpleCollectionString = getSimpleCollection();
        TreeMap<String, FactModelTree> hiddenFactSimpleCollection = new TreeMap<>();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", IMPORTED_PREFIX, simpleCollectionString, hiddenFactSimpleCollection, FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(1, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey(VALUE));
        assertEquals("java.util.List", retrieved.getSimpleProperties().get(VALUE).getTypeName());
        assertFalse(retrieved.getSimpleProperties().get(VALUE).getBaseTypeName().isPresent());
        assertEquals("java.util.List", retrieved.getSimpleProperties().get(VALUE).getPropertyTypeNameToVisualize());
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertEquals(1, retrieved.getGenericTypesMap().size());
        assertTrue(retrieved.getGenericTypesMap().containsKey(VALUE));
        assertNotNull(retrieved.getGenericTypesMap().get(VALUE));
        assertEquals(1, retrieved.getGenericTypesMap().get(VALUE).size());
        assertEquals(simpleCollectionString.getName(), retrieved.getGenericTypesMap().get(VALUE).get(0));
        assertEquals(IMPORTED_PREFIX, retrieved.getImportPrefix());
    }

    @Test
    public void createTopLevelFactModelTreeCompositeNoCollectionBaseType() throws WrongDMNTypeException {
        // Single property retrieve
        DMNType composite = getSingleCompositeWithBaseTypeField();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", IMPORTED_PREFIX, composite, new TreeMap<>(), FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(2, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey("name"));
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getSimpleProperties().get("name").getTypeName());
        assertFalse(retrieved.getSimpleProperties().get("name").getBaseTypeName().isPresent());
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getSimpleProperties().get("name").getPropertyTypeNameToVisualize());
        //
        assertTrue(retrieved.getSimpleProperties().containsKey("gender"));
        assertEquals("simpleType", retrieved.getSimpleProperties().get("gender").getTypeName());
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getSimpleProperties().get("gender").getBaseTypeName().get());
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getSimpleProperties().get("gender").getPropertyTypeNameToVisualize());
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertTrue(retrieved.getGenericTypesMap().isEmpty());
        assertEquals(IMPORTED_PREFIX, retrieved.getImportPrefix());
    }

    @Test
    public void createTopLevelFactModelTreeCompositeNoCollection() throws WrongDMNTypeException {
        // Single property retrieve
        DMNType compositePerson = getSingleCompositeWithSimpleCollection();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", IMPORTED_PREFIX, compositePerson, new TreeMap<>(), FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(2, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey("friends"));
        assertEquals("java.util.List", retrieved.getSimpleProperties().get("friends").getTypeName());
        assertFalse(retrieved.getSimpleProperties().get("friends").getBaseTypeName().isPresent());
        assertEquals("java.util.List", retrieved.getSimpleProperties().get("friends").getPropertyTypeNameToVisualize());
        assertTrue(retrieved.getSimpleProperties().containsKey("name"));
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getSimpleProperties().get("name").getTypeName());
        assertFalse(retrieved.getSimpleProperties().get("name").getBaseTypeName().isPresent());
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getSimpleProperties().get("name").getPropertyTypeNameToVisualize());
        //
        assertEquals(1, retrieved.getGenericTypesMap().size());
        assertTrue(retrieved.getGenericTypesMap().containsKey("friends"));
        assertEquals(compositePerson.getFields().get("friends").getName(), retrieved.getGenericTypesMap().get("friends").get(0));
        //
        assertEquals(2, retrieved.getExpandableProperties().size());
        assertTrue(retrieved.getExpandableProperties().containsKey(EXPANDABLE_PROPERTY_PHONENUMBERS));
        assertEquals("tPhoneNumber", retrieved.getExpandableProperties().get(EXPANDABLE_PROPERTY_PHONENUMBERS));
        assertTrue(retrieved.getExpandableProperties().containsKey(EXPANDABLE_PROPERTY_DETAILS));
        assertEquals("tDetails", retrieved.getExpandableProperties().get(EXPANDABLE_PROPERTY_DETAILS));
        assertEquals(IMPORTED_PREFIX, retrieved.getImportPrefix());
    }

    @Test
    public void createTopLevelFactModelTreeCompositeCollection() throws WrongDMNTypeException {
        // Single property collection retrieve
        DMNType compositePerson = getCompositeCollection();
        TreeMap<String, FactModelTree> hiddenFactSimpleCollection = new TreeMap<>();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", IMPORTED_PREFIX, compositePerson, hiddenFactSimpleCollection, FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(1, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey(VALUE));
        assertEquals("java.util.List", retrieved.getSimpleProperties().get(VALUE).getTypeName());
        assertFalse(retrieved.getSimpleProperties().get(VALUE).getBaseTypeName().isPresent());
        assertEquals("java.util.List", retrieved.getSimpleProperties().get(VALUE).getPropertyTypeNameToVisualize());
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertEquals(1, retrieved.getGenericTypesMap().size());
        assertTrue(retrieved.getGenericTypesMap().containsKey(VALUE));
        assertNotNull(retrieved.getGenericTypesMap().get(VALUE));
        assertEquals(1, retrieved.getGenericTypesMap().get(VALUE).size());
        assertEquals(compositePerson.getName(), retrieved.getGenericTypesMap().get(VALUE).get(0));
        assertEquals(IMPORTED_PREFIX, retrieved.getImportPrefix());
    }

    @Test
    public void createTopLevelFactModelTreeRecursiveTypes() throws WrongDMNTypeException {
        SortedMap<String, FactModelTree> hiddenFacts = new TreeMap<>();
        FactModelTree person = dmnTypeServiceImpl.createTopLevelFactModelTree("person", IMPORTED_PREFIX, getRecursivePersonComposite(false), hiddenFacts, FactModelTree.Type.DECISION);
        assertNotNull(person);
        assertTrue(person.getExpandableProperties().containsKey("parent"));
        assertEquals("tPerson", person.getExpandableProperties().get("parent"));
        assertTrue(hiddenFacts.containsKey("tPerson"));
        assertEquals(IMPORTED_PREFIX, person.getImportPrefix());

        hiddenFacts = new TreeMap<>();
        FactModelTree personCollection = dmnTypeServiceImpl.createTopLevelFactModelTree("person", IMPORTED_PREFIX, getRecursivePersonComposite(true), hiddenFacts, FactModelTree.Type.DECISION);

        assertNotNull(personCollection);
        assertTrue(personCollection.getGenericTypesMap().containsKey(VALUE));
        assertEquals("tPerson", personCollection.getGenericTypeInfo(VALUE).get(0));
        assertTrue(hiddenFacts.containsKey("tPerson"));
        assertTrue(hiddenFacts.containsKey("tPersonList"));
        assertEquals(IMPORTED_PREFIX, personCollection.getImportPrefix());
    }

    @Test
    public void checkTypeSimpleTopLevelCollection() {
        // top level collection
        SimpleTypeImpl topLevelCollection = getSimpleCollection();
        DMNTypeServiceImpl.ErrorHolder errorHolder = new DMNTypeServiceImpl.ErrorHolder();
        dmnTypeServiceImpl.checkTypeSupport(topLevelCollection, errorHolder, "fieldName");
        assertEquals(0, errorHolder.getMultipleNestedObject().size());
        assertEquals(0, errorHolder.getMultipleNestedCollection().size());
    }

    @Test
    public void checkTypeSingleCompositeWithNestedCompositeCollection() {
        // nested collection
        CompositeTypeImpl singleCompositeWithComplexCollection = getSingleCompositeWithNestedCollection();
        DMNTypeServiceImpl.ErrorHolder errorHolder = new DMNTypeServiceImpl.ErrorHolder();
        dmnTypeServiceImpl.checkTypeSupport(singleCompositeWithComplexCollection, errorHolder, "fieldName");
        assertEquals(0, errorHolder.getMultipleNestedObject().size());
        assertEquals(1, errorHolder.getMultipleNestedCollection().size());
        assertTrue(errorHolder.getMultipleNestedCollection().contains("fieldName.phoneNumbers.numbers"));
    }

    @Test
    public void checkTypeSingleCompositeWithCollection() {
        // nested object into collection
        CompositeTypeImpl person = new CompositeTypeImpl();
        CompositeTypeImpl complexNumbers = new CompositeTypeImpl(null, "tPhoneNumber", null, false);
        CompositeTypeImpl phoneNumberCompositeCollection = new CompositeTypeImpl(null, "tPhoneNumber", null, true);
        phoneNumberCompositeCollection.addField("complexNumbers", complexNumbers);
        person.addField(EXPANDABLE_PROPERTY_PHONENUMBERS, phoneNumberCompositeCollection);
        DMNTypeServiceImpl.ErrorHolder errorHolder = new DMNTypeServiceImpl.ErrorHolder();
        dmnTypeServiceImpl.checkTypeSupport(person, errorHolder, "fieldName");
        assertEquals(1, errorHolder.getMultipleNestedObject().size());
        assertEquals(0, errorHolder.getMultipleNestedCollection().size());
        assertTrue(errorHolder.getMultipleNestedObject().contains("fieldName.phoneNumbers.complexNumbers"));
    }

    @Test
    public void checkTypeRecursive() {
        DMNTypeServiceImpl.ErrorHolder errorHolder = new DMNTypeServiceImpl.ErrorHolder();
        dmnTypeServiceImpl.checkTypeSupport(getRecursivePersonComposite(false), errorHolder, "");
        assertEquals(1, errorHolder.getMultipleNestedCollection().size());
        assertEquals(2, errorHolder.getMultipleNestedObject().size());

        errorHolder = new DMNTypeServiceImpl.ErrorHolder();
        dmnTypeServiceImpl.checkTypeSupport(getRecursivePersonComposite(true), errorHolder, "");
        assertEquals(2, errorHolder.getMultipleNestedCollection().size());
        assertEquals(2, errorHolder.getMultipleNestedObject().size());
    }

    /**
     * Verify the <code>FactModelTree</code> generated for a <b>given</b> <code>DMNNode</code> (<code>InputDataNode</code> or <code>DecisionNode</code>)
     * @param factModelTuple
     * @param dmnNode
     * @param hiddenFacts
     */
    private void verifyFactModelTree(FactModelTuple factModelTuple, DMNNode dmnNode, SortedMap<String, FactModelTree> hiddenFacts, Map<String, String> importedModelsMap) {
        final String name = importedModelsMap.containsKey(dmnNode.getModelNamespace()) ?
                importedModelsMap.get(dmnNode.getModelNamespace()) + "." + dmnNode.getName() :
                dmnNode.getName();
        // Check the FactModelTree has been mapped between visible facts
        assertTrue(factModelTuple.getVisibleFacts().containsKey(name));
        final FactModelTree mappedFactModelTree = factModelTuple.getVisibleFacts().get(name);
        // Check the FactModelTree is not null
        assertNotNull(mappedFactModelTree);
        DMNType originalType;
        // Retrieving DMNType mapped by original DMNNode
        if (dmnNode instanceof InputDataNode) {
            originalType = ((InputDataNode) dmnNode).getType();
        } else if (dmnNode instanceof DecisionNode) {
            originalType = ((DecisionNode) dmnNode).getResultType();
        } else {
            fail("Unrecognized node type " + name + " -> " + dmnNode.getClass().getCanonicalName());
            return;
        }
        if (originalType.isCollection()) { // if original type is a collection
            verifyCollectionDMNType(mappedFactModelTree, originalType, hiddenFacts);
        } else { // Otherwise look inside for specific cases
            if (originalType.isComposite()) {
                verifyCompositeDMNType(mappedFactModelTree, originalType, hiddenFacts);
            } else {
                verifySimpleDMNType(mappedFactModelTree, originalType);
            }
        }
    }

    /**
     * Verify the <code>FactModelTree</code> generated for a <b>collection</b> <code>DMNType</code>
     * @param mappedFactModelTree
     * @param originalType
     * @param hiddenFacts
     */
    private void verifyCollectionDMNType(FactModelTree mappedFactModelTree, DMNType originalType, SortedMap<String, FactModelTree> hiddenFacts) {
        if (originalType.isComposite()) { // a composite collection is a collection of itself, the generic type is the DMNType itself
            if (!mappedFactModelTree.getGenericTypesMap().isEmpty()) {
                assertTrue(mappedFactModelTree.getGenericTypesMap().containsKey(VALUE)); // with "value as key
                final String genericType = mappedFactModelTree.getGenericTypesMap().get(VALUE).get(0); // since this is a list, it just have one generic
                assertTrue(hiddenFacts.containsKey(genericType));
                final FactModelTree genericFactModelTree = hiddenFacts.get(genericType);
                assertNotNull(genericFactModelTree);
                verifyCompositeDMNType(genericFactModelTree, originalType, hiddenFacts);
            } else {
                verifyCompositeDMNType(mappedFactModelTree, originalType, hiddenFacts);
            }
        } else { // otherwise we have to check if it is a "direct" collection or a referenced one
            verifySimpleDMNType(mappedFactModelTree, originalType);
        }
    }

    /**
     * Verify the <code>FactModelTree</code> generated for a <b>composite</b> (<b>not collection</b>) <code>DMNType</code>
     * @param mappedFactModelTree
     * @param originalType
     */
    private void verifyCompositeDMNType(FactModelTree mappedFactModelTree, DMNType originalType, SortedMap<String, FactModelTree> hiddenFacts) {
        originalType.getFields().forEach((key, value) -> {
            if (value.isCollection()) {
                assertTrue(hiddenFacts.containsKey(key));
                verifyCollectionDMNType(hiddenFacts.get(key), value, hiddenFacts);
            } else {
                if (value.isComposite()) { // If it is composite it should be an expandable property
                    assertTrue(mappedFactModelTree.getExpandableProperties().containsKey(key));
                    // Verify that referenced genericType is mapped and not null inside hiddenFacts
                    assertTrue(hiddenFacts.containsKey(value.getName()));
                    assertNotNull(hiddenFacts.get(value.getName()));
                } else {
                    assertTrue(mappedFactModelTree.getSimpleProperties().containsKey(key)); // otherwise a simple one
                }
            }
        });
    }

    /**
     * Verify the <code>FactModelTree</code> generated for a <b>simple</b> (<b>not collection</b>) <code>DMNType</code>
     * @param mappedFactModelTree
     * @param originalType
     */
    private void verifySimpleDMNType(FactModelTree mappedFactModelTree, DMNType originalType) {
        assertTrue(mappedFactModelTree.getSimpleProperties().containsKey(VALUE)); // otherwise a simple one
        assertEquals(originalType.getName(), mappedFactModelTree.getSimpleProperties().get(VALUE).getTypeName());
        assertEquals(originalType.getName(), mappedFactModelTree.getSimpleProperties().get(VALUE).getPropertyTypeNameToVisualize());
        if (originalType.getBaseType() != null) {
            assertTrue(mappedFactModelTree.getSimpleProperties().get(VALUE).getBaseTypeName().isPresent());
            assertEquals(originalType.getBaseType().getName(), mappedFactModelTree.getSimpleProperties().get(VALUE).getBaseTypeName().get());
            assertEquals(originalType.getBaseType().getName(), mappedFactModelTree.getSimpleProperties().get(VALUE).getPropertyTypeNameToVisualize());
        }
    }
}