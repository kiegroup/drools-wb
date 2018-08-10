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

package org.drools.workbench.screens.scenariosimulation.client.editor;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioSimulationViewImplTest extends AbstractScenarioSimulationEditorTest {

    private ScenarioSimulationView scenarioSimulationView;

    @Mock
    private ScenarioGridPanel mockScenarioGridPanel;

    @Mock
    private ScenarioGrid mockScenarioGrid;

    @Before
    public void setup() {
        super.setup();
        when(mockScenarioGridPanel.getScenarioGrid()).thenReturn(mockScenarioGrid);
        scenarioSimulationView = spy(new ScenarioSimulationViewImpl() {
            {
                scenarioGridPanel = mockScenarioGridPanel;
            }
        });
    }

    @Test
    public void testSetContent() {
        scenarioSimulationView.setContent(model.getSimulation());
        verify(mockScenarioGrid, times(1)).setContent(model.getSimulation());
    }
}
