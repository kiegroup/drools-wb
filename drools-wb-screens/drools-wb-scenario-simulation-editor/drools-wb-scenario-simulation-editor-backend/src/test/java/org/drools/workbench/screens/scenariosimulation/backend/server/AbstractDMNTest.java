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

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kie.api.io.Resource;
import org.kie.dmn.api.core.DMNMessage;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNType;
import org.kie.dmn.api.core.ast.BusinessKnowledgeModelNode;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.kie.dmn.api.core.ast.DecisionServiceNode;
import org.kie.dmn.api.core.ast.InputDataNode;
import org.kie.dmn.api.core.ast.ItemDefNode;
import org.kie.dmn.core.ast.DecisionNodeImpl;
import org.kie.dmn.core.ast.InputDataNodeImpl;
import org.kie.dmn.core.impl.CompositeTypeImpl;
import org.kie.dmn.core.impl.SimpleTypeImpl;
import org.kie.dmn.model.api.Definitions;
import org.kie.dmn.model.v1_2.TDecision;
import org.kie.dmn.model.v1_2.TInputData;

public class AbstractDMNTest {

//    @Mock
    protected DMNModel dmnModelLocal;

    protected static final String SIMPLE_INPUT_DATA_NAME = "SIMPLE_INPUT_DATA_NAME";
    protected static final String SIMPLE_DECISION_DATA_NAME = "SIMPLE_DECISION_DATA_NAME";
    protected static final String COMPOSITE_DECISION_DATA_NAME = "COMPOSITE_DECISION_DATA_NAME";
    protected static final String SIMPLE_TYPE_NAME = "string";
    protected static final String COMPOSITE_TYPE_NAME = "COMPOSITE_TYPE_NAME";
    protected static final String EXPANDABLE_PROPERTY_PHONENUMBERS = "phoneNumbers";
    protected static final String EXPANDABLE_PROPERTY_DETAILS = "details";
    protected static final String PHONENUMBER_NUMBER = "number";
    protected static final String PHONENUMBER_PREFIX = "prefix";

    protected DMNType simpleTypeNoCollection;
    protected DMNType compositeNoCollection;
    protected DMNType nestedComplexTypeMock;
    protected Map<String, DMNType> complexFields;
    protected Map<String, DMNType> nestedComplexFields;
    protected Set<InputDataNode> inputDataNodes;
    protected Set<DecisionNode> decisionNodes;

    protected void init() {
        inputDataNodes = new HashSet<>();
        simpleTypeNoCollection = getSimpleNoCollection();
        InputDataNode inputDataNodeSimpleNoCollection = getInputDataNodeSimpleNoCollection(simpleTypeNoCollection);
        inputDataNodes.add(inputDataNodeSimpleNoCollection);
        decisionNodes = new HashSet<>();
        DecisionNode decisionNodeSimpleNoCollection = getDecisionNodeSimpleNoCollection(simpleTypeNoCollection);
        decisionNodes.add(decisionNodeSimpleNoCollection);
        compositeNoCollection = getSingleCompositeWithSimpleCollection();
        DecisionNode decisionNodeCompositeNoCollection = getDecisionNodeCompositeNoCollection(compositeNoCollection);
        decisionNodes.add(decisionNodeCompositeNoCollection);
        dmnModelLocal = new DMNModel() {

            @Override
            public List<DMNMessage> getMessages() {
                return null;
            }

            @Override
            public List<DMNMessage> getMessages(DMNMessage.Severity... sevs) {
                return null;
            }

            @Override
            public boolean hasErrors() {
                return false;
            }

            @Override
            public String getNamespace() {
                return null;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public Definitions getDefinitions() {
                return null;
            }

            @Override
            public InputDataNode getInputById(String id) {
                return null;
            }

            @Override
            public InputDataNode getInputByName(String name) {
                return null;
            }

            @Override
            public Set<InputDataNode> getInputs() {
                return inputDataNodes;
            }

            @Override
            public DecisionNode getDecisionById(String id) {
                return null;
            }

            @Override
            public DecisionNode getDecisionByName(String name) {
                return null;
            }

            @Override
            public Set<DecisionNode> getDecisions() {
                return decisionNodes;
            }

            @Override
            public Set<InputDataNode> getRequiredInputsForDecisionName(String decisionName) {
                return null;
            }

            @Override
            public Set<InputDataNode> getRequiredInputsForDecisionId(String decisionId) {
                return null;
            }

            @Override
            public BusinessKnowledgeModelNode getBusinessKnowledgeModelById(String id) {
                return null;
            }

            @Override
            public BusinessKnowledgeModelNode getBusinessKnowledgeModelByName(String name) {
                return null;
            }

            @Override
            public Set<BusinessKnowledgeModelNode> getBusinessKnowledgeModels() {
                return null;
            }

            @Override
            public ItemDefNode getItemDefinitionById(String id) {
                return null;
            }

            @Override
            public ItemDefNode getItemDefinitionByName(String name) {
                return null;
            }

            @Override
            public Set<ItemDefNode> getItemDefinitions() {
                return null;
            }

            @Override
            public Resource getResource() {
                return null;
            }

            @Override
            public Collection<DecisionServiceNode> getDecisionServices() {
                return null;
            }
        };

    }

    protected InputDataNode getInputDataNodeSimpleNoCollection(DMNType dmnType) {
        TInputData inputData = new TInputData();
        inputData.setName(SIMPLE_INPUT_DATA_NAME);
        InputDataNode toReturn = new InputDataNodeImpl(inputData, dmnType);
        return toReturn;
    }

    protected DecisionNode getDecisionNodeSimpleNoCollection(DMNType dmnType) {
        TDecision decision = new TDecision();
        decision.setName(SIMPLE_DECISION_DATA_NAME);
        DecisionNode toReturn = new DecisionNodeImpl(decision, dmnType);
        return toReturn;
    }

    protected DecisionNode getDecisionNodeCompositeNoCollection(DMNType dmnType) {
        TDecision decision = new TDecision();
        decision.setName(COMPOSITE_DECISION_DATA_NAME);
        DecisionNode toReturn = new DecisionNodeImpl(decision, dmnType);
        return toReturn;
    }

    /**
     * Returns a <b>single</b> <code>SimpleTypeImpl</code>
     * @return
     */
    protected SimpleTypeImpl getSimpleNoCollection() {
        return new SimpleTypeImpl("simpleNameSpace", SIMPLE_TYPE_NAME, null);
    }

    /**
     * Returns a <b>string collection</b> <code>SimpleTypeImpl</code>
     * @return
     */
    protected SimpleTypeImpl getSimpleCollection() {
        // Single property collection retrieve
        SimpleTypeImpl simpleCollectionString = new SimpleTypeImpl("simpleNameSpace", SIMPLE_TYPE_NAME, null);
        simpleCollectionString.setCollection(true);
        return simpleCollectionString;
    }

    /**
     * Returns a <b>single</b>  <b>person</b> <code>CompositeTypeImpl</code> that in turns contains other <code>CompositeTypeImpl</code>s properties
     * @return
     */
    protected CompositeTypeImpl getSingleCompositeWithSimpleCollection() {
        // Complex object retrieve
        CompositeTypeImpl toReturn = new CompositeTypeImpl("compositeNameSpace", COMPOSITE_TYPE_NAME, null);

        CompositeTypeImpl phoneNumberComposite = getPhoneNumberComposite(false);

        CompositeTypeImpl detailsComposite = new CompositeTypeImpl(null, "tDetails", "tDetails");
        detailsComposite.addField("gender", new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));
        detailsComposite.addField("weight", new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));

        SimpleTypeImpl nameSimple = new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null);

        SimpleTypeImpl friendsSimpleCollection = new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null);
        friendsSimpleCollection.setCollection(true);

        toReturn.addField("friends", friendsSimpleCollection);
        toReturn.addField(EXPANDABLE_PROPERTY_PHONENUMBERS, phoneNumberComposite);
        toReturn.addField(EXPANDABLE_PROPERTY_DETAILS, detailsComposite);
        toReturn.addField("name", nameSimple);

        return toReturn;
    }

    /**
     * Returns a <b>single</b>  <b>person</b> <code>CompositeTypeImpl</code> that in turns contains other <code>CompositeTypeImpl</code>s properties
     * @return
     */
    protected CompositeTypeImpl getSingleCompositeWithNestedCollection() {
        // Complex object retrieve
        CompositeTypeImpl toReturn = new CompositeTypeImpl("compositeNameSpace", COMPOSITE_TYPE_NAME, null);

        CompositeTypeImpl phoneNumberCompositeCollection = new CompositeTypeImpl(null, "tPhoneNumber", null, true);
        phoneNumberCompositeCollection.addField(PHONENUMBER_PREFIX, new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));
        SimpleTypeImpl numbers = new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null);
        numbers.setCollection(true);
        phoneNumberCompositeCollection.addField("numbers", numbers);

        CompositeTypeImpl detailsComposite = new CompositeTypeImpl(null, "tDetails", "tDetails");
        detailsComposite.addField("gender", new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));
        detailsComposite.addField("weight", new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));

        SimpleTypeImpl nameSimple = new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null);

        SimpleTypeImpl friendsSimple = new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null);

        toReturn.addField("friends", friendsSimple);
        toReturn.addField(EXPANDABLE_PROPERTY_PHONENUMBERS, phoneNumberCompositeCollection);
        toReturn.addField(EXPANDABLE_PROPERTY_DETAILS, detailsComposite);
        toReturn.addField("name", nameSimple);

        return toReturn;
    }

    /**
     * Returns a <b>person collection</b> <code>CompositeTypeImpl</code> that in turns contains other <code>CompositeTypeImpl</code>s properties
     * @return
     */
    protected CompositeTypeImpl getCompositeCollection() {
        // Complex object retrieve
        CompositeTypeImpl toReturn = new CompositeTypeImpl("compositeNameSpace", COMPOSITE_TYPE_NAME, null);
        toReturn.setCollection(true);
        CompositeTypeImpl phoneNumberCompositeCollection = getPhoneNumberComposite(true);

        CompositeTypeImpl detailsComposite = new CompositeTypeImpl(null, "tDetails", "tDetails");
        detailsComposite.addField("gender", new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));
        detailsComposite.addField("weight", new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));

        SimpleTypeImpl nameSimple = new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null);

        SimpleTypeImpl friendsSimpleCollection = new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null);
        friendsSimpleCollection.setCollection(true);

        toReturn.addField("friends", friendsSimpleCollection);
        toReturn.addField(EXPANDABLE_PROPERTY_PHONENUMBERS, phoneNumberCompositeCollection);
        toReturn.addField(EXPANDABLE_PROPERTY_DETAILS, detailsComposite);
        toReturn.addField("name", nameSimple);

        return toReturn;
    }

    /**
     * Returns a <b>phone number</b> <code>CompositeTypeImpl</code>
     * @return
     */
    protected CompositeTypeImpl getPhoneNumberComposite(boolean isCollection) {
        CompositeTypeImpl toReturn = new CompositeTypeImpl(null, "tPhoneNumber", null, isCollection);
        toReturn.addField(PHONENUMBER_PREFIX, new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));
        toReturn.addField(PHONENUMBER_NUMBER, new SimpleTypeImpl(null, SIMPLE_TYPE_NAME, null));
        return toReturn;
    }
}