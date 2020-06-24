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

package org.drools.workbench.screens.scenariosimulation.client.commands;

import java.util.Optional;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.AbstractScenarioSimulationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.command.client.CommandResultBuilder;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioCommandRegistryManagerTest extends AbstractScenarioSimulationTest {

    private ScenarioCommandRegistryManager scenarioCommandRegistryManagerSpy;

    @Before
    public void setup() {
        super.setup();
        scenarioCommandRegistryManagerSpy = spy(new ScenarioCommandRegistryManager() {

        });
    }

    @Test
    public void register() {
        /*scenarioCommandRegistryManagerSpy.undoneCommands.add(mock(AbstractScenarioGridCommand.class));
        assertFalse(scenarioCommandRegistryManagerSpy.undoneCommands.isEmpty());
        scenarioCommandRegistryManagerSpy.register(scenarioSimulationContextLocal, mock(AbstractScenarioGridCommand.class));
        assertTrue(scenarioCommandRegistryManagerSpy.undoneCommands.isEmpty());
        verify(scenarioCommandRegistryManagerSpy, times(1)).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));*/
    }

    @Test
    public void undoEmpty() {
       /* scenarioCommandRegistryManagerSpy.undoneCommands.clear();
        final CommandResult<ScenarioSimulationViolation> retrieved = scenarioCommandRegistryManagerSpy.undo(scenarioSimulationContextLocal);
        assertEquals(CommandResult.Type.WARNING, retrieved.getType());
        verify(scenarioCommandRegistryManagerSpy, never()).commonUndoRedoOperation(eq(scenarioSimulationContextLocal), eq(appendRowCommandMock), eq(true));
        verify(scenarioCommandRegistryManagerSpy, times(1)).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));*/
    }

    @Test
    public void undoNotEmptySameGrid() {
        /*
        scenarioCommandRegistryManagerSpy.undoneCommands.clear();
        AbstractScenarioGridCommand commandMock = mock(AbstractScenarioGridCommand.class);
        //scenarioCommandRegistryManagerSpy.register(commandMock);
        doReturn(CommandResultBuilder.SUCCESS).when(scenarioCommandRegistryManagerSpy).commonUndoRedoOperation(any(), any(), anyBoolean());
        doReturn(Optional.empty()).when(scenarioCommandRegistryManagerSpy).commonUndoRedoPreexecution(any(), any());
        final CommandResult<ScenarioSimulationViolation> retrieved = scenarioCommandRegistryManagerSpy.undo(scenarioSimulationContextLocal);
        assertEquals(CommandResult.Type.INFO, retrieved.getType());
        verify(scenarioCommandRegistryManagerSpy, times(1)).commonUndoRedoOperation(eq(scenarioSimulationContextLocal), eq(commandMock), eq(true));
        verify(scenarioCommandRegistryManagerSpy, times(1)).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));*/
    }

    @Test
    public void undoNotEmptyDifferentGrid() {
        /*int currentSize = scenarioCommandRegistryManagerSpy.undoneCommands.size();
        scenarioCommandRegistryManagerSpy.register(scenarioSimulationContextLocal, appendRowCommandMock);
        verify(scenarioCommandRegistryManagerSpy, times(1)).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));
        doReturn(Optional.of(CommandResultBuilder.SUCCESS)).when(appendRowCommandMock).commonUndoRedoPreexecution(eq(scenarioSimulationContextLocal));
        scenarioCommandRegistryManagerSpy.undo(scenarioSimulationContextLocal);
        //assertEquals(currentSize, scenarioCommandRegistryManagerSpy.undoneCommands.size());
        verify(scenarioCommandRegistryManagerSpy, never()).commonUndoRedoOperation(eq(scenarioSimulationContextLocal), eq(appendRowCommandMock), eq(true));
        verify(scenarioCommandRegistryManagerSpy, times(1)).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));
        */
    }

    @Test
    public void redoEmpty() {
        /*
        scenarioCommandRegistryManagerSpy.undoneCommands.clear();
        scenarioCommandRegistryManagerSpy.redo(scenarioSimulationContextLocal);
        verify(scenarioCommandRegistryManagerSpy, never()).commonUndoRedoOperation(eq(scenarioSimulationContextLocal), eq(appendRowCommandMock), eq(true));
        verify(scenarioCommandRegistryManagerSpy, times(1)).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));*/
    }

    @Test
    public void redoNotEmptySameGrid() {
        /*
        scenarioCommandRegistryManagerSpy.undoneCommands.clear();
        AbstractScenarioGridCommand commandMock = mock(AbstractScenarioGridCommand.class);
        //scenarioCommandRegistryManagerSpy.undoneCommands.add(commandMock);
        doReturn(CommandResultBuilder.SUCCESS).when(scenarioCommandRegistryManagerSpy).commonUndoRedoOperation(any(), any(), anyBoolean());
        doReturn(Optional.empty()).when(scenarioCommandRegistryManagerSpy).commonUndoRedoPreexecution(any(), any());
        final CommandResult<ScenarioSimulationViolation> retrieved = scenarioCommandRegistryManagerSpy.redo(scenarioSimulationContextLocal);
        assertEquals(CommandResult.Type.INFO, retrieved.getType());
        verify(scenarioCommandRegistryManagerSpy, never()).commonUndoRedoOperation(eq(scenarioSimulationContextLocal), eq(commandMock), eq(true));
        verify(scenarioCommandRegistryManagerSpy, times(1)).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));*/
    }

    @Test
    public void redoNotEmptyDifferentGrid() {
        //scenarioCommandRegistryManagerSpy.undoneCommands.push(appendRowCommandMock);
        doReturn(Optional.of(CommandResultBuilder.SUCCESS)).when(appendRowCommandMock).commonUndoRedoPreexecution(eq(scenarioSimulationContextLocal));
        //int currentSize = scenarioCommandRegistryManagerSpy.undoneCommands.size();
        scenarioCommandRegistryManagerSpy.redo(scenarioSimulationContextLocal);
        //assertEquals(currentSize, scenarioCommandRegistryManagerSpy.undoneCommands.size());
        verify(scenarioCommandRegistryManagerSpy, never()).commonUndoRedoOperation(eq(scenarioSimulationContextLocal), eq(appendRowCommandMock), eq(false));
        verify(scenarioCommandRegistryManagerSpy, never()).setUndoRedoButtonStatus(eq(scenarioSimulationContextLocal));
    }

    @Test
    public void setUndoRedoButtonStatus() {
        //scenarioCommandRegistryManagerSpy.clear();
        //scenarioCommandRegistryManagerSpy.undoneCommands.clear();
        scenarioCommandRegistryManagerSpy.setUndoRedoButtonStatus(scenarioSimulationContextLocal);
        verify(scenarioSimulationEditorPresenterMock, times(1)).setUndoButtonEnabledStatus(eq(false));
        verify(scenarioSimulationEditorPresenterMock, times(1)).setRedoButtonEnabledStatus(eq(false));
        //
        reset(scenarioSimulationEditorPresenterMock);
        //scenarioCommandRegistryManagerSpy.register(appendRowCommandMock);
        scenarioCommandRegistryManagerSpy.setUndoRedoButtonStatus(scenarioSimulationContextLocal);
        verify(scenarioSimulationEditorPresenterMock, times(1)).setUndoButtonEnabledStatus(eq(true));
        verify(scenarioSimulationEditorPresenterMock, times(1)).setRedoButtonEnabledStatus(eq(false));
        //
        reset(scenarioSimulationEditorPresenterMock);
        //scenarioCommandRegistryManagerSpy.undoneCommands.push(appendRowCommandMock);
        scenarioCommandRegistryManagerSpy.setUndoRedoButtonStatus(scenarioSimulationContextLocal);
        verify(scenarioSimulationEditorPresenterMock, times(1)).setUndoButtonEnabledStatus(eq(true));
        verify(scenarioSimulationEditorPresenterMock, times(1)).setRedoButtonEnabledStatus(eq(true));
    }

    @Test
    public void commonOperationUndo() {
        scenarioCommandRegistryManagerSpy.commonUndoRedoOperation(scenarioSimulationContextLocal, appendRowCommandMock, true);
        verify(appendRowCommandMock, times(1)).undo(eq(scenarioSimulationContextLocal));
        verify(appendRowCommandMock, never()).redo(eq(scenarioSimulationContextLocal));
    }

    @Test
    public void commonOperationRedo() {
        scenarioCommandRegistryManagerSpy.commonUndoRedoOperation(scenarioSimulationContextLocal, appendRowCommandMock, false);
        verify(appendRowCommandMock, times(1)).redo(eq(scenarioSimulationContextLocal));
        verify(appendRowCommandMock, never()).undo(eq(scenarioSimulationContextLocal));
    }
}