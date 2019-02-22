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

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioCellTextAreaSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class DuplicateColumnCommandTest extends AbstractScenarioSimulationCommandTest {

    @Mock
    List<ScenarioGridColumn> scenarioGridColumnListMock;

    @Before
    public void setup() {
        super.setup();
        command = spy(new DuplicateColumnCommand() {
            @Override
            protected ScenarioGridColumn getScenarioGridColumnLocal(String instanceTitle, String propertyTitle, String columnId, String columnGroup, FactMappingType factMappingType, ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader,
                                                                    ScenarioCellTextAreaSingletonDOMElementFactory factoryCell, String placeHolder) {
                return gridColumnMock;
            }
        });
        assertTrue(command.isUndoable());
        when(scenarioGridModelMock.getInstanceScenarioGridColumns(gridColumnMock)).thenReturn(scenarioGridColumnListMock);
    }

    @Test
    public void execute() {
        scenarioSimulationContextLocal.getStatus().setColumnId(COLUMN_ID);
        scenarioSimulationContextLocal.getStatus().setColumnIndex(COLUMN_INDEX);
        command.execute(scenarioSimulationContextLocal);
        assertNotNull(scenarioGridColumnListMock);
        scenarioGridColumnListMock.forEach(scenarioGridColumn -> {
            verify((DuplicateColumnCommand) command, times(1)).duplicateSingleColumn(eq(scenarioSimulationContextLocal), scenarioGridColumn.getIndex() + 1, eq(scenarioGridColumn), any());
        });
        //verify(command, times(1)).getScenarioGridColumnLocal(anyString(), anyString(), eq(COLUMN_ID), eq(COLUMN_GROUP), eq(factMappingType), eq(scenarioHeaderTextBoxSingletonDOMElementFactoryTest), eq(scenarioCellTextAreaSingletonDOMElementFactoryTest), eq(ScenarioSimulationEditorConstants.INSTANCE.defineValidType()));
        //verify(scenarioGridModelMock, times(1)).insertColumn(eq(3), eq(gridColumnMock));
    }
}