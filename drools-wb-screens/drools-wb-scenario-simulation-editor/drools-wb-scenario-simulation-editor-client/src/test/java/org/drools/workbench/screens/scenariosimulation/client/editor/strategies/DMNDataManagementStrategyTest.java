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

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.editor.AbstractScenarioSimulationEditorTest;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModelContent;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.backend.vfs.Path;
import org.uberfire.mocks.CallerMock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class DMNDataManagementStrategyTest extends AbstractScenarioSimulationEditorTest {

    @Mock
    protected DMNTypeService dmnTypeServiceMock;

    private DMNDataManagementStrategy dmnDataManagementStrategy;

    @Before
    public void setup() {
        super.setup();
        when(dmnTypeServiceMock.retrieveType(any(), anyString())).thenReturn(mock(FactModelTuple.class));
        modelLocal.getSimulation().getSimulationDescriptor().setDmnFilePath("dmn_file_path");
        dmnDataManagementStrategy = spy(new DMNDataManagementStrategy(new CallerMock<>(dmnTypeServiceMock), scenarioSimulationContextLocal) {
            {
                this.currentPath = mock(Path.class);
                this.model = modelLocal;
                this.scenarioSimulationContext = scenarioSimulationContextLocal;
            }
        });
    }

    @Test
    public void populateRightPanel() {
        dmnDataManagementStrategy.populateRightPanel(rightPanelPresenterMock, scenarioGridModelMock);
        verify(dmnTypeServiceMock, times(1)).retrieveType(any(), anyString());
        verify(dmnDataManagementStrategy, times(1)).getSuccessCallback(rightPanelPresenterMock);
    }

    @Test
    public void manageScenarioSimulationModelContent() {
        final ScenarioSimulationModelContent contentMock = spy(content);
        dmnDataManagementStrategy.manageScenarioSimulationModelContent(observablePathMock, contentMock);
        verify(observablePathMock, times(1)).getOriginal();
        verify(contentMock, times(1)).getModel();
    }

    @Test
    public void successCallbackContent() {
        scenarioSimulationContextLocal.setDataObjectFieldsMap(null);
        dmnDataManagementStrategy.successCallbackContent(mock(FactModelTuple.class), rightPanelPresenterMock);
        assertNotNull(scenarioSimulationContextLocal.getDataObjectFieldsMap());
    }

}