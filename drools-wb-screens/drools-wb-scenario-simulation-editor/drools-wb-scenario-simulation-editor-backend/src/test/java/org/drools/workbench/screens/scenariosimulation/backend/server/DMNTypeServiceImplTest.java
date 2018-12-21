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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNType;
import org.kie.dmn.api.core.ast.DecisionNode;
import org.kie.dmn.api.core.ast.InputDataNode;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.backend.vfs.Path;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DMNTypeServiceImplTest {

    @Mock
    DMNModel dmnModelMock;

    DMNTypeServiceImpl dmnTypeService;

    static final String SIMPLE_TYPE_NAME = "SIMPLE_TYPE_NAME";
    static final String SIMPLE_DECISION_TYPE_NAME = "SIMPLE_DECISION_TYPE_NAME";
    static final String COMPLEX_DECISION_TYPE_NAME = "COMPLEX_DECISION_TYPE_NAME";
    static final String BASE_TYPE = "BASE_TYPE";
    static final String COMPLEX_TYPE = "COMPLEX_TYPE";

    @Before
    public void init() {
        dmnTypeService = new DMNTypeServiceImpl() {
            @Override
            public DMNModel getDMNModel(Path path,String stringPath) {
                return dmnModelMock;
            }
        };
    }

    @Test
    public void retrieveType() {
        Set<InputDataNode> inputDataNodes = new HashSet<>();

        InputDataNode inputDataNodeSimpleMock = mock(InputDataNode.class);
        DMNType simpleTypeMock = mock(DMNType.class);
        when(simpleTypeMock.isComposite()).thenReturn(false);
        when(simpleTypeMock.getName()).thenReturn(BASE_TYPE);
        when(inputDataNodeSimpleMock.getType()).thenReturn(simpleTypeMock);
        when(inputDataNodeSimpleMock.getName()).thenReturn(SIMPLE_TYPE_NAME);

        inputDataNodes.add(inputDataNodeSimpleMock);
        when(dmnModelMock.getInputs()).thenReturn(inputDataNodes);

        Set<DecisionNode> decisionNodes = new HashSet<>();

        DecisionNode decisionNodeSimpleMock = mock(DecisionNode.class);
        when(decisionNodeSimpleMock.getResultType()).thenReturn(simpleTypeMock);
        when(decisionNodeSimpleMock.getName()).thenReturn(SIMPLE_DECISION_TYPE_NAME);
        decisionNodes.add(decisionNodeSimpleMock);

        DecisionNode decisionNodeComplexMock = mock(DecisionNode.class);

        DMNType complexTypeMock = mock(DMNType.class);
        when(complexTypeMock.isComposite()).thenReturn(true);
        when(complexTypeMock.getName()).thenReturn(COMPLEX_TYPE);

        Map<String, DMNType> complexFields = new HashMap<>();
        complexFields.put(SIMPLE_DECISION_TYPE_NAME, simpleTypeMock);
        when(complexTypeMock.getFields()).thenReturn(complexFields);

        DMNType nestedComplexTypeMock = mock(DMNType.class);
        when(nestedComplexTypeMock.isComposite()).thenReturn(true);
        when(nestedComplexTypeMock.getName()).thenReturn(COMPLEX_TYPE);
        complexFields.put(COMPLEX_DECISION_TYPE_NAME, nestedComplexTypeMock);

        Map<String, DMNType> nestedComplexFields = new HashMap<>();
        nestedComplexFields.put(SIMPLE_DECISION_TYPE_NAME, simpleTypeMock);
        when(nestedComplexTypeMock.getFields()).thenReturn(nestedComplexFields);

        when(decisionNodeComplexMock.getResultType()).thenReturn(complexTypeMock);
        when(decisionNodeComplexMock.getName()).thenReturn(COMPLEX_DECISION_TYPE_NAME);
        decisionNodes.add(decisionNodeComplexMock);

        when(dmnModelMock.getDecisions()).thenReturn(decisionNodes);

        FactModelTuple factModelTuple = dmnTypeService.retrieveType(mock(Path.class), mock(String.class));

        assertEquals(SIMPLE_TYPE_NAME, factModelTuple.getVisibleFacts().get(SIMPLE_TYPE_NAME).getFactName());
        assertEquals(BASE_TYPE, factModelTuple.getVisibleFacts().get(SIMPLE_TYPE_NAME).getSimpleProperties().get("value"));
        assertTrue(factModelTuple.getVisibleFacts().get(SIMPLE_TYPE_NAME).isSimple());

        assertEquals(SIMPLE_DECISION_TYPE_NAME, factModelTuple.getVisibleFacts().get(SIMPLE_DECISION_TYPE_NAME).getFactName());
        assertEquals(BASE_TYPE, factModelTuple.getVisibleFacts().get(SIMPLE_DECISION_TYPE_NAME).getSimpleProperties().get("value"));
        assertTrue(factModelTuple.getVisibleFacts().get(SIMPLE_DECISION_TYPE_NAME).isSimple());

        assertEquals(COMPLEX_DECISION_TYPE_NAME, factModelTuple.getVisibleFacts().get(COMPLEX_DECISION_TYPE_NAME).getFactName());
        String hiddenKey = factModelTuple.getVisibleFacts().get(COMPLEX_DECISION_TYPE_NAME).getExpandableProperties().get(COMPLEX_DECISION_TYPE_NAME);
        assertEquals(BASE_TYPE, factModelTuple.getHiddenFacts().get(hiddenKey).getSimpleProperties().get(SIMPLE_DECISION_TYPE_NAME));
    }
}