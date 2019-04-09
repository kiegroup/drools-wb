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
package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioCellTextAreaSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class InsertColumnCommandTest extends AbstractSelectedColumnCommandTest {

    @Before
    public void setup() {
        super.setup();
        command = spy(new InsertColumnCommand() {
            @Override
            protected ScenarioGridColumn getScenarioGridColumnLocal(String instanceTitle, String propertyTitle, String columnId, String columnGroup, FactMappingType factMappingType, ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader,
                                                                    ScenarioCellTextAreaSingletonDOMElementFactory factoryCell, String placeHolder) {
                return gridColumnMock;
            }
        });
        assertTrue(command.isUndoable());
    }

    @Test
    public void executeIfSelectedColumn_NotIsRightIsAsProperty() {
        scenarioSimulationContextLocal.getStatus().setColumnId(COLUMN_ID);
        scenarioSimulationContextLocal.getStatus().setColumnIndex(COLUMN_INDEX);
        scenarioSimulationContextLocal.getStatus().setRight(false);
        scenarioSimulationContextLocal.getStatus().setAsProperty(true);
        ((InsertColumnCommand)command).executeIfSelectedColumn(scenarioSimulationContextLocal, gridColumnMock);
        verify((InsertColumnCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(gridColumnMock), eq(3), eq(Boolean.TRUE));
    }

    @Test
    public void executeIfSelectedColumn_IsRightIsAsProperty() {
        scenarioSimulationContextLocal.getStatus().setColumnId(COLUMN_ID);
        scenarioSimulationContextLocal.getStatus().setColumnIndex(COLUMN_INDEX);
        scenarioSimulationContextLocal.getStatus().setRight(false);
        scenarioSimulationContextLocal.getStatus().setAsProperty(true);
        ((InsertColumnCommand)command).executeIfSelectedColumn(scenarioSimulationContextLocal, gridColumnMock);
        verify((InsertColumnCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(gridColumnMock), eq(3), eq(Boolean.TRUE));
    }

    @Test
    public void executeIfSelectedColumn_NotIsRightNotIsAsProperty() {
        scenarioSimulationContextLocal.getStatus().setColumnId(COLUMN_ID);
        scenarioSimulationContextLocal.getStatus().setColumnIndex(COLUMN_INDEX);
        scenarioSimulationContextLocal.getStatus().setRight(false);
        scenarioSimulationContextLocal.getStatus().setAsProperty(false);
        command.execute(scenarioSimulationContextLocal);
        verify((InsertColumnCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(gridColumnMock), eq(2), eq(Boolean.FALSE));
    }

    @Test
    public void executeIfSelectedColumn_NotIsAsProperty() {
        scenarioSimulationContextLocal.getStatus().setColumnId(COLUMN_ID);
        scenarioSimulationContextLocal.getStatus().setColumnIndex(COLUMN_INDEX);
        scenarioSimulationContextLocal.getStatus().setRight(true);
        scenarioSimulationContextLocal.getStatus().setAsProperty(false);
        command.execute(scenarioSimulationContextLocal);
        verify((InsertColumnCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(gridColumnMock), eq(4), eq(Boolean.FALSE));
    }

}