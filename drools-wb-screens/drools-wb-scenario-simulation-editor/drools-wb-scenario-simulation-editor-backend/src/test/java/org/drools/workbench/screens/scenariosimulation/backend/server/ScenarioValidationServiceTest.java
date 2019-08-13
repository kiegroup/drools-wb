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
package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNType;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.backend.vfs.Path;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioValidationServiceTest {

    @Mock
    private KieContainer kieContainerMock;

    @Mock
    private Path pathMock;

    @Mock
    private DMNModel dmnModelMock;

    private Map<String, DecisionNode> mapOfMockDecisions = new HashMap<>();

    @Before
    public void init() {
        when(kieContainerMock.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
        when(kieContainerMock.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
        when(dmnModelMock.getDecisionByName(anyString()))
                .thenAnswer(invocation -> mapOfMockDecisions.get(invocation.getArguments()[0]));
    }

    @After
    public void end() {
        mapOfMockDecisions.clear();
    }

    @Test
    public void validateSimulationStructure() {
        Simulation simulation = new Simulation();

        ScenarioValidationService scenarioValidationServiceSpy = spy(new ScenarioValidationService() {
            @Override
            protected List<FactMappingValidationError> validateDMN(Simulation simulation, KieContainer kieContainer) {
                return Collections.emptyList();
            }

            @Override
            protected List<FactMappingValidationError> validateRULE(Simulation simulation, KieContainer kieContainer) {
                return Collections.emptyList();
            }

            @Override
            protected KieContainer getKieContainer(Path path) {
                return kieContainerMock;
            }
        });

        simulation.getSimulationDescriptor().setType(ScenarioSimulationModel.Type.DMN);
        scenarioValidationServiceSpy.validateSimulationStructure(simulation, pathMock);
        verify(scenarioValidationServiceSpy, timeout(1)).validateDMN(eq(simulation), eq(kieContainerMock));

        reset(scenarioValidationServiceSpy);

        simulation.getSimulationDescriptor().setType(ScenarioSimulationModel.Type.RULE);
        scenarioValidationServiceSpy.validateSimulationStructure(simulation, pathMock);
        verify(scenarioValidationServiceSpy, timeout(1)).validateRULE(eq(simulation), eq(kieContainerMock));

        simulation.getSimulationDescriptor().setType(null);
        assertThatThrownBy(() -> scenarioValidationServiceSpy.validateSimulationStructure(simulation, pathMock))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validateDMN() {
        ScenarioValidationService scenarioValidationServiceSpy = spy(new ScenarioValidationService() {
            @Override
            protected DMNModel getDMNModel(KieContainer kieContainer, String dmnPath) {
                return dmnModelMock;
            }
        });

        // Test 0 - skip empty or not GIVEN/EXPECT columns
        Simulation test0 = new Simulation();
        test0.getSimulationDescriptor().addFactMapping(
                FactIdentifier.DESCRIPTION,
                ExpressionIdentifier.create("value", FactMappingType.OTHER));
        test0.getSimulationDescriptor().addFactMapping(
                FactIdentifier.EMPTY,
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        List<FactMappingValidationError> errorsTest0 = scenarioValidationServiceSpy.validateDMN(test0, kieContainerMock);
        checkResult(errorsTest0);

        // Test 1 - simple type
        Simulation test1 = new Simulation();
        test1.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", "tMYSIMPLETYPE"),
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        createDMNType("mySimpleType", "mySimpleType");

        List<FactMappingValidationError> errorsTest1 = scenarioValidationServiceSpy.validateDMN(test1, kieContainerMock);
        checkResult(errorsTest1);

        test1.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", "notValidClass"),
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        errorsTest1 = scenarioValidationServiceSpy.validateDMN(test1, kieContainerMock);
        checkResult(errorsTest1, "Node type has changed: old 'notValidClass', current 'tMYSIMPLETYPE'");

        // Test 2 - nested field
        Simulation test2 = new Simulation();
        // nameFM is valid
        FactIdentifier myComplexFactIdentifier = FactIdentifier.create("myComplexType", "tMYCOMPLEXTYPE");
        FactMapping nameFM = test2.getSimulationDescriptor().addFactMapping(
                myComplexFactIdentifier,
                ExpressionIdentifier.create("name", FactMappingType.GIVEN));
        nameFM.addExpressionElement("name", "tNAME");

        createDMNType("myComplexType", "myComplexType", "name");

        // parentFM is valid
        FactMapping parentFM = test2.getSimulationDescriptor().addFactMapping(
                myComplexFactIdentifier,
                ExpressionIdentifier.create("parent", FactMappingType.EXPECT));
        parentFM.addExpressionElement("parent", "tPARENT");

        createDMNType("myComplexType", "myComplexType", "parent");

        List<FactMappingValidationError> errorsTest2 = scenarioValidationServiceSpy.validateDMN(test2, kieContainerMock);
        checkResult(errorsTest2);

        // parentFM is not valid anymore
        parentFM.addExpressionElement("notExisting", "notExisting");
        errorsTest2 = scenarioValidationServiceSpy.validateDMN(test2, kieContainerMock);
        checkResult(errorsTest2, "Impossible to find field 'notExisting' in type 'tPARENT'");

        // nameWrongTypeFM has a wrong type
        FactMapping nameWrongTypeFM = test2.getSimulationDescriptor().addFactMapping(
                myComplexFactIdentifier,
                ExpressionIdentifier.create("parent2", FactMappingType.EXPECT));
        nameWrongTypeFM.addExpressionElement("name", Integer.class.getCanonicalName());
        errorsTest2 = scenarioValidationServiceSpy.validateDMN(test2, kieContainerMock);
        checkResult(errorsTest2,
                    "Impossible to find field 'notExisting' in type 'tPARENT'",
                    "Field type has changed: old 'java.lang.Integer', current 'tNAME'");

        // Test 3 - list
        Simulation test3 = new Simulation();
        // topLevelListFM is valid
        FactMapping topLevelListFM = test3.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("myList", List.class.getCanonicalName()),
                ExpressionIdentifier.create("name", FactMappingType.GIVEN));
        topLevelListFM.setGenericTypes(Collections.singletonList("tPERSON"));

        createDMNType("myList", "person");
        when(mapOfMockDecisions.get("myList").getResultType().isCollection()).thenReturn(true);

        // addressesFM is valid
        FactMapping addressesFM = test3.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("myComplexObject", "tMYCOMPLEXOBJECT"),
                ExpressionIdentifier.create("addresses", FactMappingType.EXPECT));
        addressesFM.addExpressionElement("addresses", List.class.getCanonicalName());
        addressesFM.setGenericTypes(Collections.singletonList("tADDRESSES"));

        createDMNType("myComplexObject", "myComplexObject", "addresses");
        when(mapOfMockDecisions.get("myComplexObject").getResultType().getFields().get("addresses").isCollection()).thenReturn(true);

        List<FactMappingValidationError> errorsTest3 = scenarioValidationServiceSpy.validateDMN(test3, kieContainerMock);
        checkResult(errorsTest3);

        // Test 4 - complex type changed
        Simulation test4 = new Simulation();
        test4.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", "tMYSIMPLETYPE"),
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        createDMNType("mySimpleType", "mySimpleType", "name");

        List<FactMappingValidationError> errorsTest4 = scenarioValidationServiceSpy.validateDMN(test4, kieContainerMock);
        checkResult(errorsTest4, "Node type has changed: old 'tMYSIMPLETYPE', current 'tMYSIMPLETYPE'");

        // Test 5 - not existing node
        Simulation test5 = new Simulation();
        test5.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", "tMYSIMPLETYPE"),
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        when(dmnModelMock.getDecisionByName(anyString())).thenReturn(null);
        List<FactMappingValidationError> errorsTest5 = scenarioValidationServiceSpy.validateDMN(test5, kieContainerMock);
        checkResult(errorsTest5, "Node type has changed: old 'tMYSIMPLETYPE', current 'node not found'");

    }

    @Test
    public void validateRULE() {
        ScenarioValidationService scenarioValidationServiceSpy = spy(new ScenarioValidationService() {
            @Override
            protected KieContainer getKieContainer(Path path) {
                return kieContainerMock;
            }
        });

        // Test 0 - skip empty or not GIVEN/EXPECT columns
        Simulation test0 = new Simulation();
        test0.getSimulationDescriptor().addFactMapping(
                FactIdentifier.DESCRIPTION,
                ExpressionIdentifier.create("value", FactMappingType.OTHER));
        test0.getSimulationDescriptor().addFactMapping(
                FactIdentifier.EMPTY,
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        List<FactMappingValidationError> errorsTest0 = scenarioValidationServiceSpy.validateRULE(test0, kieContainerMock);
        checkResult(errorsTest0);

        // Test 1 - simple type
        Simulation test1 = new Simulation();
        test1.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", int.class.getCanonicalName()),
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        List<FactMappingValidationError> errorsTest1 = scenarioValidationServiceSpy.validateRULE(test1, kieContainerMock);
        checkResult(errorsTest1);

        test1.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", "notValidClass"),
                ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        errorsTest1 = scenarioValidationServiceSpy.validateRULE(test1, kieContainerMock);
        checkResult(errorsTest1, "Impossible to load class notValidClass");

        // Test 2 - nested field
        Simulation test2 = new Simulation();
        // nameFM is valid
        FactIdentifier myFactIdentifier = FactIdentifier.create("mySimpleType", SampleBean.class.getCanonicalName());
        FactMapping nameFM = test2.getSimulationDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("name", FactMappingType.GIVEN));
        nameFM.addExpressionElement("name", String.class.getCanonicalName());

        // parentFM is valid
        FactMapping parentFM = test2.getSimulationDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("parent", FactMappingType.EXPECT));
        parentFM.addExpressionElement("parent", SampleBean.class.getCanonicalName());

        List<FactMappingValidationError> errorsTest2 = scenarioValidationServiceSpy.validateRULE(test2, kieContainerMock);
        checkResult(errorsTest2);

        // parentFM is not valid anymore
        parentFM.addExpressionElement("notExisting", String.class.getCanonicalName());
        errorsTest2 = scenarioValidationServiceSpy.validateRULE(test2, kieContainerMock);
        checkResult(errorsTest2, "Impossible to find field with name 'notExisting' in class org.drools.workbench.screens.scenariosimulation.backend.server.SampleBean");

        // nameWrongTypeFM has a wrong type
        FactMapping nameWrongTypeFM = test2.getSimulationDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("parent2", FactMappingType.EXPECT));
        nameWrongTypeFM.addExpressionElement("name", Integer.class.getCanonicalName());
        errorsTest2 = scenarioValidationServiceSpy.validateRULE(test2, kieContainerMock);
        checkResult(errorsTest2,
                    "Impossible to find field with name 'notExisting' in class org.drools.workbench.screens.scenariosimulation.backend.server.SampleBean",
                    "Field type has changed: old 'java.lang.Integer', current 'java.lang.String'");

        // Test 3 - list
        Simulation test3 = new Simulation();
        // topLevelListFM is valid
        FactMapping topLevelListFM = test3.getSimulationDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", List.class.getCanonicalName()),
                ExpressionIdentifier.create("name", FactMappingType.GIVEN));
        topLevelListFM.setGenericTypes(Collections.singletonList(String.class.getCanonicalName()));

        // addressesFM is valid
        FactMapping addressesFM = test3.getSimulationDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("addresses", FactMappingType.EXPECT));
        addressesFM.addExpressionElement("addresses", List.class.getCanonicalName());
        addressesFM.setGenericTypes(Collections.singletonList(String.class.getCanonicalName()));

        List<FactMappingValidationError> errorsTest3 = scenarioValidationServiceSpy.validateRULE(test3, kieContainerMock);
        checkResult(errorsTest3);
    }

    private void checkResult(List<FactMappingValidationError> validationErrors, String... expectedErrors) {
        if (expectedErrors.length == 0) {
            assertEquals(0, validationErrors.size());
        }

        for (String expectedError : expectedErrors) {
            assertTrue("Expected error: '" + expectedError + "' not found",
                       validationErrors.stream().anyMatch(
                               validationError -> Objects.equals(expectedError, validationError.getErrorMessage())));
        }
    }

    private void createDMNType(String decisionName, String rootType, String... steps) {
        DecisionNode decisionNodeMock = getOrCreateDecisionNode(decisionName, rootType);

        DMNType currentType = decisionNodeMock.getResultType();
        for (String step : steps) {
            currentType = addStep(currentType, step);
        }

        mapOfMockDecisions.put(decisionName, decisionNodeMock);
    }

    private DecisionNode getOrCreateDecisionNode(String decisionName, String typeName) {
        DecisionNode decisionNodeMock;
        if (mapOfMockDecisions.containsKey(decisionName)) {
            decisionNodeMock = mapOfMockDecisions.get(decisionName);
            String decisionTypeName = decisionNodeMock.getResultType().getName();
            if (!Objects.equals(decisionTypeName, createDMNTypeName(typeName))) {
                throw new IllegalArgumentException(
                        "Decision with name " + decisionName + " already created of type " + decisionTypeName);
            }
        } else {
            decisionNodeMock = mock(DecisionNode.class);
            mapOfMockDecisions.put(decisionName, decisionNodeMock);
            when(decisionNodeMock.getName()).thenReturn(decisionName);
            DMNType initDMNType = initDMNType(typeName);
            when(decisionNodeMock.getResultType()).thenReturn(initDMNType);
        }
        return decisionNodeMock;
    }

    private DMNType addStep(DMNType dmnTypeMock, String field) {
        DMNType nestedDmnTypeMock = initDMNType(field);
        dmnTypeMock.getFields().put(field, nestedDmnTypeMock);
        return nestedDmnTypeMock;
    }

    private DMNType initDMNType(String name) {
        DMNType dmnTypeMock = mock(DMNType.class);
        when(dmnTypeMock.getFields()).thenReturn(new HashMap<>());
        String type = createDMNTypeName(name);
        when(dmnTypeMock.getName()).thenReturn(type);
        when(dmnTypeMock.isComposite())
                .thenAnswer(invocation -> ((DMNType) invocation.getMock()).getFields().size() != 0);
        return dmnTypeMock;
    }

    private String createDMNTypeName(String name) {
        return "t" + name.toUpperCase();
    }
}