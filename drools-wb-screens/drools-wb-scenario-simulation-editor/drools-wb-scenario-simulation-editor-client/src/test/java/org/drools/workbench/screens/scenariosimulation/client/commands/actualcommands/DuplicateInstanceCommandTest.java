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

package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(GwtMockitoTestRunner.class)
public class DuplicateInstanceCommandTest extends AbstractSelectedColumnCommandTest {

    @Before
    public void setup() {
        super.setup();
        command = spy(new DuplicateInstanceCommand() {
            @Override
            protected void setInstanceHeader(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String alias, String fullClassName) {
                //Do nothing
            }

            @Override
            protected void setPropertyHeader(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String value, String propertyClass, String propertyHeaderTitle) {
                //Do nothing
            }
        });
        assertTrue(command.isUndoable());
    }

    @Test
    public void executeIfSelectedColumn_WithoutInstanceAndProperty() {
        when(scenarioGridColumnMock1.isInstanceAssigned()).thenReturn(Boolean.FALSE);
        when(scenarioGridColumnMock1.isPropertyAssigned()).thenReturn(Boolean.FALSE);
        ((DuplicateInstanceCommand) command).executeIfSelectedColumn(scenarioSimulationContextLocal, scenarioGridColumnMock1);
        verify(scenarioGridModelMock, times(1)).getInstancesCount(eq(scenarioGridColumnMock1.getFactIdentifier().getClassName()));
        verify((DuplicateInstanceCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(scenarioGridColumnMock1), eq(COLUMN_NUMBER + 1), eq(Boolean.FALSE));
        verify((DuplicateInstanceCommand) command, never()).setInstanceHeader(any(), any(), any(), any());
        verify((DuplicateInstanceCommand) command, never()).setPropertyHeader(any(), any(), any(), any());
        verify(scenarioGridModelMock, never()).duplicateColumnValues(anyInt(), anyInt());
    }

    @Test
    public void executeIfSelectedColumn_WithInstanceOnly() {
        when(scenarioGridColumnMock1.isPropertyAssigned()).thenReturn(Boolean.FALSE);
        ((DuplicateInstanceCommand) command).executeIfSelectedColumn(scenarioSimulationContextLocal, scenarioGridColumnMock1);
        verify(scenarioGridModelMock, times(1)).getInstancesCount(eq(scenarioGridColumnMock1.getFactIdentifier().getClassName()));
        verify((DuplicateInstanceCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(scenarioGridColumnMock1), eq(COLUMN_NUMBER + 1), eq(Boolean.FALSE));
        verify((DuplicateInstanceCommand) command, times(1)).setInstanceHeader(eq(scenarioSimulationContextLocal), notNull(ScenarioGridColumn.class), eq(VALUE_1 + DuplicateInstanceCommand.COPY_LABEL + "1"), eq(FULL_CLASS_NAME_1));
        verify((DuplicateInstanceCommand) command, never()).setPropertyHeader(any(), any(), any(), any());
        verify(scenarioGridModelMock, never()).duplicateColumnValues(anyInt(), anyInt());
    }

    @Test
    public void executeIfSelectedColumn_WithInstanceAndProperty() {
        ((DuplicateInstanceCommand) command).executeIfSelectedColumn(scenarioSimulationContextLocal, scenarioGridColumnMock1);
        verify(scenarioGridModelMock, times(1)).getInstancesCount(eq(scenarioGridColumnMock1.getFactIdentifier().getClassName()));
        verify((DuplicateInstanceCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(scenarioGridColumnMock1), eq(COLUMN_NUMBER + 1), eq(Boolean.FALSE));
        verify((DuplicateInstanceCommand) command, times(1)).setInstanceHeader(eq(scenarioSimulationContextLocal), notNull(ScenarioGridColumn.class), eq(VALUE_1 + DuplicateInstanceCommand.COPY_LABEL + "1"), eq(FULL_CLASS_NAME_1));
        verify((DuplicateInstanceCommand) command, times(1)).setPropertyHeader(eq(scenarioSimulationContextLocal), notNull(ScenarioGridColumn.class), eq("value_1_copy_1.test"), eq(VALUE_CLASS_NAME), eq(GRID_PROPERTY_TITLE_1));
        verify(scenarioGridModelMock, times(1)).duplicateColumnValues(eq(COLUMN_NUMBER), anyInt());
    }

}