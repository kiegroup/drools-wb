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

import java.util.HashMap;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.drools.workbench.screens.scenariosimulation.backend.server.exceptions.WrongDMNTypeException;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.junit.Before;
import org.junit.Test;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.api.core.DMNType;
import org.kie.dmn.api.core.ast.DMNNode;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.kie.dmn.api.core.ast.InputDataNode;
import org.kie.dmn.core.impl.CompositeTypeImpl;
import org.kie.dmn.core.impl.SimpleTypeImpl;
import org.kie.dmn.core.util.DMNRuntimeUtil;
import org.uberfire.backend.vfs.Path;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
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
                final DMNRuntime runtime = DMNRuntimeUtil.createRuntime("dmn-list-composite.dmn", this.getClass());
                dmnModelLocal = runtime.getModel("https://github.com/kiegroup/drools/kie-dmn/_25BF2679-3109-488F-9AD1-DDBCCEBBE5F1", "dmn-list-composite");
                assertThat(dmnModelLocal, notNullValue());
                assertThat(DMNRuntimeUtil.formatMessages(dmnModelLocal.getMessages()), dmnModelLocal.hasErrors(), is(false));
                return dmnModelLocal;
            }
        };
    }

    @Test
    public void retrieveFactModelTuple() throws WrongDMNTypeException {
        FactModelTuple factModelTuple = dmnTypeServiceImpl.retrieveFactModelTuple(mock(Path.class), null);
        // VisibleFacts should match inputs and decisions on given model
        int expectedVisibleFacts = dmnModelLocal.getInputs().size() + dmnModelLocal.getDecisions().size();
        assertEquals(expectedVisibleFacts, factModelTuple.getVisibleFacts().size());
        // Verify each inputDataNode has been correctly mapped
        dmnModelLocal.getInputs().forEach(inputDataNode -> commonFactModelTreeValidation(factModelTuple, inputDataNode, factModelTuple.getHiddenFacts()));
        dmnModelLocal.getDecisions().forEach(decisionNode -> commonFactModelTreeValidation(factModelTuple, decisionNode, factModelTuple.getHiddenFacts()));
    }

    @Test
    public void createTopLevelFactModelTreeSimpleNoCollection() throws WrongDMNTypeException {
        // Single property retrieve
        DMNType simpleString = getSimpleNoCollection();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", new HashMap<>(), simpleString, new TreeMap<>(), FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(1, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey("value"));
        assertEquals(simpleString.getName(), retrieved.getSimpleProperties().get("value"));
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertTrue(retrieved.getGenericTypesMap().isEmpty());
    }

    @Test
    public void createTopLevelFactModelTreeSimpleCollection() throws WrongDMNTypeException {
        // Single property collection retrieve
        DMNType simpleCollectionString = getSimpleCollection();
        TreeMap<String, FactModelTree> hiddenFactSimpleCollection = new TreeMap<>();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", new HashMap<>(), simpleCollectionString, hiddenFactSimpleCollection, FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(1, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey("value"));
        assertEquals("java.util.List", retrieved.getSimpleProperties().get("value"));
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertEquals(1, retrieved.getGenericTypesMap().size());
        assertTrue(retrieved.getGenericTypesMap().containsKey("value"));
        assertNotNull(retrieved.getGenericTypesMap().get("value"));
        assertEquals(1, retrieved.getGenericTypesMap().get("value").size());
        assertEquals(simpleCollectionString.getName(), retrieved.getGenericTypesMap().get("value").get(0));
    }

    @Test
    public void createTopLevelFactModelTreeCompositeNoCollection() throws WrongDMNTypeException {
        // Single property retrieve
        DMNType compositePerson = getSingleCompositeWithSimpleCollection();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", new HashMap<>(), compositePerson, new TreeMap<>(), FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(2, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey("friends"));
        assertEquals("java.util.List", retrieved.getSimpleProperties().get("friends"));
        assertTrue(retrieved.getSimpleProperties().containsKey("name"));
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getSimpleProperties().get("name"));
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
    }

    @Test
    public void createTopLevelFactModelTreeCompositeCollection() throws WrongDMNTypeException {
        // Single property collection retrieve
        DMNType compositePerson = getCompositeCollection();
        TreeMap<String, FactModelTree> hiddenFactSimpleCollection = new TreeMap<>();
        FactModelTree retrieved = dmnTypeServiceImpl.createTopLevelFactModelTree("testPath", new HashMap<>(), compositePerson, hiddenFactSimpleCollection, FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(1, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey("value"));
        assertEquals("java.util.List", retrieved.getSimpleProperties().get("value"));
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertEquals(1, retrieved.getGenericTypesMap().size());
        assertTrue(retrieved.getGenericTypesMap().containsKey("value"));
        assertNotNull(retrieved.getGenericTypesMap().get("value"));
        assertEquals(1, retrieved.getGenericTypesMap().get("value").size());
        assertEquals(compositePerson.getName(), retrieved.getGenericTypesMap().get("value").get(0));
    }

    @Test
    public void checkTypeSimpleTopLevelCollection() {
        // top level collection
        SimpleTypeImpl topLevelCollection = getSimpleCollection();
        DMNTypeServiceImpl.ErrorHolder errorHolder = new DMNTypeServiceImpl.ErrorHolder();
        dmnTypeServiceImpl.checkType(topLevelCollection, errorHolder, "fieldName");
        assertEquals(1, errorHolder.getTopLevelCollection().size());
        assertEquals(0, errorHolder.getMultipleNestedObject().size());
        assertEquals(0, errorHolder.getMultipleNestedCollection().size());
        assertEquals("fieldName", errorHolder.getTopLevelCollection().get(0));
    }

    @Test
    public void checkTypeSingleCompositeWithNestedCompositeCollection() {
        // nested collection
        CompositeTypeImpl singleCompositeWithComplexCollection = getSingleCompositeWithNestedCollection();
        DMNTypeServiceImpl.ErrorHolder errorHolder = new DMNTypeServiceImpl.ErrorHolder();
        dmnTypeServiceImpl.checkType(singleCompositeWithComplexCollection, errorHolder, "fieldName");
        assertEquals(0, errorHolder.getTopLevelCollection().size());
        assertEquals(0, errorHolder.getMultipleNestedObject().size());
        assertEquals(1, errorHolder.getMultipleNestedCollection().size());
        assertEquals("fieldName.phoneNumbers.numbers", errorHolder.getMultipleNestedCollection().get(0));
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
        dmnTypeServiceImpl.checkType(person, errorHolder, "fieldName");
        assertEquals(0, errorHolder.getTopLevelCollection().size());
        assertEquals(1, errorHolder.getMultipleNestedObject().size());
        assertEquals(0, errorHolder.getMultipleNestedCollection().size());
        assertEquals("fieldName.phoneNumbers.complexNumbers", errorHolder.getMultipleNestedObject().get(0));
    }

    private void commonFactModelTreeValidation(FactModelTuple factModelTuple, DMNNode dmnNode, SortedMap<String, FactModelTree> hiddenFacts) {
        assertTrue(factModelTuple.getVisibleFacts().containsKey(dmnNode.getName()));
        final FactModelTree mappedFactModelTree = factModelTuple.getVisibleFacts().get(dmnNode.getName());
        assertNotNull(mappedFactModelTree);
        DMNType originalType;
        if (dmnNode instanceof InputDataNode) {
            originalType = ((InputDataNode) dmnNode).getType();
        } else if (dmnNode instanceof DecisionNode) {
            originalType = ((DecisionNode) dmnNode).getResultType();
        } else {
            fail("Unrecognized node type " + dmnNode.getName() + " -> " + dmnNode.getClass().getCanonicalName());
            return;
        }
        if (originalType.isCollection()) { // if original type is a collection
            assertTrue(mappedFactModelTree.getSimpleProperties().containsKey("value")); // mappedFactModelTree must have a single simple property "value"
            assertEquals(List.class.getName(), mappedFactModelTree.getSimpleProperties().get("value")); // mapped to java.util.List
            assertEquals(1, mappedFactModelTree.getGenericTypesMap().size());  // and one generic property "value"
            assertTrue(mappedFactModelTree.getGenericTypesMap().containsKey("value")); // with "value as key
            final String genericType = mappedFactModelTree.getGenericTypesMap().get("value").get(0); // since this is a list, it just have one generic
            if (originalType.isComposite()) { // a composite collection is a collection of itself, the generic type is the DMNType itself
                assertEquals(originalType.getName(), genericType);
            } else { // otherwise we have to check if it is a "direct" collection or a referenced one
                if (originalType.getBaseType() != null) { // it is a referenced collection, i.e. a collection of other types
                    assertEquals(originalType.getBaseType().getName(), genericType);
                } else { // it is a collection of itself, the generic type is the DMNType itself
                    assertEquals(originalType.getName(), genericType);
                }
            }
            assertTrue(hiddenFacts.containsKey(genericType));
            assertNotNull(hiddenFacts.get(genericType));
        } else { // Otherwise look inside for specific cases
            if (originalType.isComposite()) { // verify each property of the original type has been correctly mapped
                originalType.getFields().entrySet().forEach(stringDMNTypeEntry -> {
                    if (stringDMNTypeEntry.getValue().isComposite()) { // If it is composite it should be an expandable property
                        assertTrue(mappedFactModelTree.getExpandableProperties().containsKey(stringDMNTypeEntry.getKey()));
                    } else {
                        assertTrue(mappedFactModelTree.getSimpleProperties().containsKey(stringDMNTypeEntry.getKey())); // otherwise a simple one
                    }
                });
            } else {
                assertTrue(mappedFactModelTree.getSimpleProperties().containsKey("value")); // otherwise a simple one
                assertEquals(originalType.getName(), mappedFactModelTree.getSimpleProperties().get("value"));
            }
        }
    }
}