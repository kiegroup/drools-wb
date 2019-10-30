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

package org.drools.workbench.screens.scenariosimulation.businesscentral.client.editor.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gwt.event.shared.EventBus;
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
import org.mockito.Mockito;
import org.uberfire.backend.vfs.Path;
import org.uberfire.mocks.CallerMock;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class BusinessCentralDMNDataManagementStrategyTest extends AbstractScenarioSimulationEditorTest {

    @Mock
    private DMNTypeService dmnTypeServiceMock;

    private BusinessCentralDMNDataManagementStrategy businessCentralDmnDataManagementStrategySpy;
    private BusinessCentralDMNDataManagementStrategy.ResultHolder factModelTreeHolderlocal;

    private FactModelTuple factModelTupleLocal;
    private SortedMap<String, FactModelTree> visibleFactsLocal = new TreeMap<>();
    private SortedMap<String, FactModelTree> hiddenFactsLocal = new TreeMap<>();

    @Before
    public void setup() {
        super.setup();
        factModelTupleLocal = new FactModelTuple(visibleFactsLocal, hiddenFactsLocal);
        factModelTreeHolderlocal = new BusinessCentralDMNDataManagementStrategy.ResultHolder();
        factModelTreeHolderlocal.setFactModelTuple(factModelTupleLocal);
        try {
            when(dmnTypeServiceMock.retrieveFactModelTuple(any(), anyString())).thenReturn(factModelTupleLocal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        modelLocal.getSettings().setDmnFilePath("dmn_file_path");
        businessCentralDmnDataManagementStrategySpy = spy(new BusinessCentralDMNDataManagementStrategy(new CallerMock<>(dmnTypeServiceMock),
                                                                                                       mock(EventBus.class)) {
            {
                this.currentPath = mock(Path.class);
                this.model = modelLocal;
                this.factModelTreeHolder = factModelTreeHolderlocal;
            }
        });
    }

    @Test
    public void populateTestToolsWithoutFactModelTuple() {
        factModelTreeHolderlocal.setFactModelTuple(null);
        businessCentralDmnDataManagementStrategySpy.populateTestTools(testToolsPresenterMock, scenarioSimulationContextLocal);
        verify(dmnTypeServiceMock, times(1)).retrieveFactModelTuple(any(), anyString());
        verify(businessCentralDmnDataManagementStrategySpy, times(1)).getSuccessCallback(testToolsPresenterMock, scenarioSimulationContextLocal);
        verify(businessCentralDmnDataManagementStrategySpy, times(1)).getSuccessCallbackMethod(eq(factModelTupleLocal), eq(testToolsPresenterMock), eq(scenarioSimulationContextLocal));
    }

    @Test
    public void populateTestToolsWithFactModelTuple() {
        businessCentralDmnDataManagementStrategySpy.populateTestTools(testToolsPresenterMock, scenarioSimulationContextLocal);
        verify(dmnTypeServiceMock, never()).retrieveFactModelTuple(any(), anyString());
        verify(businessCentralDmnDataManagementStrategySpy, times(1)).getSuccessCallback(testToolsPresenterMock, scenarioSimulationContextLocal);
        verify(businessCentralDmnDataManagementStrategySpy, times(1)).getSuccessCallbackMethod(eq(factModelTupleLocal), eq(testToolsPresenterMock), eq(scenarioSimulationContextLocal));
    }

    @Test
    public void manageScenarioSimulationModelContent() {
        final ScenarioSimulationModelContent contentMock = Mockito.spy(content);
        businessCentralDmnDataManagementStrategySpy.manageScenarioSimulationModelContent(observablePathMock, contentMock);
        verify(observablePathMock, times(1)).getOriginal();
        verify(contentMock, times(1)).getModel();
    }

    @Test
    public void isADataType() {
        visibleFactsLocal.clear();
        hiddenFactsLocal.clear();
        commonIsADataType("TEST", false);
        visibleFactsLocal.put("TEST", new FactModelTree());
        commonIsADataType("TOAST", false);
        commonIsADataType("TEST", true);
        visibleFactsLocal.clear();
        hiddenFactsLocal.put("TEST", new FactModelTree());
        commonIsADataType("TOAST", false);
        commonIsADataType("TEST", true);
    }

    private void commonIsADataType(String value, boolean expected) {
        boolean retrieved = businessCentralDmnDataManagementStrategySpy.isADataType(value);
        if (expected) {
            assertTrue(retrieved);
        } else {
            assertFalse(retrieved);
        }
    }

    @Test
    public void getSuccessCallbackMethod() {
        Map<String, List<String>> alreadyAssignedProperties = new HashMap<>();
        factModelTreeHolderlocal.setFactModelTuple(null);
        doReturn(alreadyAssignedProperties).when(businessCentralDmnDataManagementStrategySpy).getPropertiesToHide(scenarioGridModelMock);
        businessCentralDmnDataManagementStrategySpy.getSuccessCallbackMethod(factModelTupleLocal, testToolsPresenterMock, scenarioSimulationContextLocal);
        verify(businessCentralDmnDataManagementStrategySpy, times(1)).getPropertiesToHide(eq(scenarioGridModelMock));
        assertEquals(factModelTupleLocal, factModelTreeHolderlocal.getFactModelTuple());
        verify(testToolsPresenterMock, times(1)).setDataObjectFieldsMap(isA(SortedMap.class));
        verify(testToolsPresenterMock, times(1)).setSimpleJavaTypeFieldsMap(isA(SortedMap.class));
        verify(testToolsPresenterMock, times(1)).setHiddenFieldsMap(eq(hiddenFactsLocal));
    }
}