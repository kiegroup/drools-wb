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
import java.util.TreeMap;

import org.drools.workbench.screens.scenariosimulation.backend.server.exceptions.WrongDMNTypeException;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.junit.Before;
import org.junit.Test;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNType;
import org.kie.dmn.core.impl.CompositeTypeImpl;
import org.kie.dmn.core.impl.SimpleTypeImpl;
import org.uberfire.backend.vfs.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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
    public void retrieveFactModelTuple() throws WrongDMNTypeException {
        FactModelTuple factModelTuple = dmnTypeServiceImpl.retrieveFactModelTuple(mock(Path.class), null);
        assertTrue(factModelTuple.getVisibleFacts().containsKey(SIMPLE_INPUT_DATA_NAME));
        assertEquals(SIMPLE_INPUT_DATA_NAME, factModelTuple.getVisibleFacts().get(SIMPLE_INPUT_DATA_NAME).getFactName());
        assertEquals(SIMPLE_TYPE_NAME, factModelTuple.getVisibleFacts().get(SIMPLE_INPUT_DATA_NAME).getSimpleProperties().get("value"));
        assertTrue(factModelTuple.getVisibleFacts().get(SIMPLE_INPUT_DATA_NAME).isSimple());

        assertTrue(factModelTuple.getVisibleFacts().containsKey(SIMPLE_DECISION_DATA_NAME));
        assertEquals(SIMPLE_DECISION_DATA_NAME, factModelTuple.getVisibleFacts().get(SIMPLE_DECISION_DATA_NAME).getFactName());
        assertEquals(SIMPLE_TYPE_NAME, factModelTuple.getVisibleFacts().get(SIMPLE_DECISION_DATA_NAME).getSimpleProperties().get("value"));
        assertTrue(factModelTuple.getVisibleFacts().get(SIMPLE_DECISION_DATA_NAME).isSimple());

        assertTrue(factModelTuple.getVisibleFacts().containsKey(COMPOSITE_DECISION_DATA_NAME));
        assertEquals(COMPOSITE_DECISION_DATA_NAME, factModelTuple.getVisibleFacts().get(COMPOSITE_DECISION_DATA_NAME).getFactName());
        assertTrue(factModelTuple.getVisibleFacts().get(COMPOSITE_DECISION_DATA_NAME).getExpandableProperties().containsKey(EXPANDABLE_PROPERTY_PHONENUMBERS));
        String hiddenKey = factModelTuple.getVisibleFacts().get(COMPOSITE_DECISION_DATA_NAME).getExpandableProperties().get(EXPANDABLE_PROPERTY_PHONENUMBERS);
        assertTrue(factModelTuple.getHiddenFacts().containsKey(hiddenKey));
        assertEquals(SIMPLE_TYPE_NAME, factModelTuple.getHiddenFacts().get(hiddenKey).getSimpleProperties().get(PHONENUMBER_NUMBER));
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
        assertTrue(retrieved.getGenericTypesMap().containsKey("value"));
        assertEquals(SIMPLE_TYPE_NAME, retrieved.getGenericTypesMap().get("value").get(0));
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
}