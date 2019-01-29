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

package org.drools.workbench.screens.scenariosimulation.client.editor.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.editor.AbstractScenarioSimulationEditorTest;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModelContent;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.mocks.CallerMock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class DMNDataManagementStrategyTest extends AbstractScenarioSimulationEditorTest {

    @Mock
    private DMNTypeService dmnTypeServiceMock;

    @Mock
    private FactModelTuple factMappingTupleMock;

    @Mock
    private  DMNDataManagementStrategy.ResultHolder factModelTreeHolderMock;


    private DMNDataManagementStrategy dmnDataManagementStrategySpy;

    @Before
    public void init() {
        super.setup();
        when(dmnTypeServiceMock.retrieveType(any(), anyString())).thenReturn(factMappingTupleMock);
        dmnDataManagementStrategySpy = spy(new DMNDataManagementStrategy(new CallerMock<>(dmnTypeServiceMock)) {
            {
                this.factModelTreeHolder = factModelTreeHolderMock;
            }
        });
    }

    @Test
    public void populateRightPanel() {
        dmnDataManagementStrategySpy.manageScenarioSimulationModelContent(observablePathMock, content);
        dmnDataManagementStrategySpy.populateRightPanel(rightPanelPresenterMock, scenarioGridModelMock);
        verify(dmnTypeServiceMock, times(1)).retrieveType(any(), anyString());
        verify(dmnDataManagementStrategySpy, times(1)).getSuccessCallback(rightPanelPresenterMock, scenarioGridModelMock);
        verify(dmnDataManagementStrategySpy, times(1)).getSuccessCallbackMethod(eq(factMappingTupleMock), eq(rightPanelPresenterMock), eq(scenarioGridModelMock));
    }

    @Test
    public void manageScenarioSimulationModelContent() {
        final ScenarioSimulationModelContent scenarioSimulationModelContentMock = spy(content);
        dmnDataManagementStrategySpy.manageScenarioSimulationModelContent(observablePathMock, scenarioSimulationModelContentMock);
        verify(observablePathMock, times(1)).getOriginal();
        verify(scenarioSimulationModelContentMock, times(1)).getModel();
    }

    @Test
    public void getSuccessCallbackMethod() {
        Map<String, List<String>> alreadyAssignedProperties = new HashMap<>();
        SortedMap<String, FactModelTree> hiddenFacts = new TreeMap<>();
        when(factMappingTupleMock.getHiddenFacts()).thenReturn(hiddenFacts);
        doReturn(alreadyAssignedProperties).when(dmnDataManagementStrategySpy).getAlreadyAssignedProperties(scenarioGridModelMock);
        dmnDataManagementStrategySpy.getSuccessCallbackMethod(factMappingTupleMock, rightPanelPresenterMock, scenarioGridModelMock);
        verify(dmnDataManagementStrategySpy, times(1)).getAlreadyAssignedProperties(eq(scenarioGridModelMock));
        verify(factModelTreeHolderMock, times(1)).setFactModelTuple(eq(factMappingTupleMock));
        verify(dmnDataManagementStrategySpy, times(2)).filterFactModelTreeMap(isA(SortedMap.class), eq(alreadyAssignedProperties));
        verify(rightPanelPresenterMock, times(1)).setDataObjectFieldsMap(isA(SortedMap.class));
        verify(rightPanelPresenterMock, times(1)).setSimpleJavaTypeFieldsMap(isA(SortedMap.class));
        verify(factMappingTupleMock, times(1)).getHiddenFacts();
        verify(rightPanelPresenterMock, times(1)).setHiddenFieldsMap(eq(hiddenFacts));
    }
}