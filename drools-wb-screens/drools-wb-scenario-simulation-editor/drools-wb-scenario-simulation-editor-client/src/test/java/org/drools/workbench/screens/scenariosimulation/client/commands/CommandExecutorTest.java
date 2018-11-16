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

import java.util.List;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands.AbstractScenarioSimulationCommandTest;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DeleteColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DeleteRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DisableRightPanelEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DuplicateRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.EnableRightPanelEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ReloadRightPanelEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridReloadEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.SetInstanceHeaderEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.SetPropertyHeaderEvent;
import org.drools.workbench.screens.scenariosimulation.client.popup.DeletePopupPresenter;
import org.drools.workbench.screens.scenariosimulation.client.popup.PreserveDeletePopupPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class CommandExecutorTest extends AbstractScenarioSimulationCommandTest {

    @Mock
    private List<HandlerRegistration> handlerRegistrationListMock;
    @Mock
    private HandlerRegistration appendColumnHandlerRegistrationMock;
    @Mock
    private HandlerRegistration appendRowHandlerRegistrationMock;
    @Mock
    private HandlerRegistration deleteColumnHandlerRegistrationMock;
    @Mock
    private HandlerRegistration deleteRowHandlerRegistrationMock;
    @Mock
    private HandlerRegistration disableRightPanelEventHandlerMock;
    @Mock
    private HandlerRegistration duplicateHandlerRegistrationMock;
    @Mock
    private HandlerRegistration enableRightPanelEventHandlerMock;
    @Mock
    private HandlerRegistration insertColumnHandlerRegistrationMock;
    @Mock
    private HandlerRegistration insertRowHandlerRegistrationMock;
    @Mock
    private HandlerRegistration prependColumnHandlerRegistrationMock;
    @Mock
    private HandlerRegistration prependRowHandlerRegistrationMock;
    @Mock
    private HandlerRegistration reloadRightPanelHandlerRegistrationMock;
    @Mock
    private HandlerRegistration scenarioGridReloadHandlerRegistrationMock;
    @Mock
    private HandlerRegistration setInstanceHeaderEventHandlerMock;
    @Mock
    private HandlerRegistration setPropertyHeaderEventHandlerMock;

    @Mock
    private DeletePopupPresenter deletePopupPresenterMock;
    @Mock
    private PreserveDeletePopupPresenter preserveDeletePopupPresenterMock;

    private CommandExecutor commandExecutor;

    @Before
    public void setup() {
        super.setup();
        when(eventBusMock.addHandler(eq(AppendColumnEvent.TYPE), isA(CommandExecutor.class))).thenReturn(appendColumnHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(AppendRowEvent.TYPE), isA(CommandExecutor.class))).thenReturn(appendRowHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(DeleteColumnEvent.TYPE), isA(CommandExecutor.class))).thenReturn(deleteColumnHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(DeleteRowEvent.TYPE), isA(CommandExecutor.class))).thenReturn(deleteRowHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(DisableRightPanelEvent.TYPE), isA(CommandExecutor.class))).thenReturn(disableRightPanelEventHandlerMock);
        when(eventBusMock.addHandler(eq(DuplicateRowEvent.TYPE), isA(CommandExecutor.class))).thenReturn(duplicateHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(EnableRightPanelEvent.TYPE), isA(CommandExecutor.class))).thenReturn(enableRightPanelEventHandlerMock);
        when(eventBusMock.addHandler(eq(InsertColumnEvent.TYPE), isA(CommandExecutor.class))).thenReturn(insertColumnHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(InsertRowEvent.TYPE), isA(CommandExecutor.class))).thenReturn(insertRowHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(PrependColumnEvent.TYPE), isA(CommandExecutor.class))).thenReturn(prependColumnHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(PrependRowEvent.TYPE), isA(CommandExecutor.class))).thenReturn(prependRowHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(ReloadRightPanelEvent.TYPE), isA(CommandExecutor.class))).thenReturn(reloadRightPanelHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(ScenarioGridReloadEvent.TYPE), isA(CommandExecutor.class))).thenReturn(scenarioGridReloadHandlerRegistrationMock);
        when(eventBusMock.addHandler(eq(SetInstanceHeaderEvent.TYPE), isA(CommandExecutor.class))).thenReturn(setInstanceHeaderEventHandlerMock);
        when(eventBusMock.addHandler(eq(SetPropertyHeaderEvent.TYPE), isA(CommandExecutor.class))).thenReturn(setPropertyHeaderEventHandlerMock);
        commandExecutor = spy(new CommandExecutor() {
            {
                this.eventBus = eventBusMock;
                this.handlerRegistrationList = handlerRegistrationListMock;
                this.deletePopupPresenter = deletePopupPresenterMock;
                this.preserveDeletePopupPresenter = preserveDeletePopupPresenterMock;
                this.context = scenarioSimulationContext;
            }
        });
    }

    @Test
    public void setEventBus() {
        commandExecutor.setEventBus(eventBusMock);
        verify(commandExecutor, times(1)).registerHandlers();
        assertEquals(eventBusMock, commandExecutor.eventBus);
    }

    @Test
    public void unregisterHandlers() {
        commandExecutor.unregisterHandlers();
        verify(handlerRegistrationListMock, times(1)).forEach(anyObject());
    }

    @Test
    public void onAppendColumnEvent() {
        AppendColumnEvent event = new AppendColumnEvent(COLUMN_GROUP);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onAppendRowEvent() {
        AppendRowEvent event = new AppendRowEvent();
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onDeleteColumnEvent() {
        DeleteColumnEvent event = new DeleteColumnEvent(COLUMN_INDEX, COLUMN_GROUP);
        when(scenarioGridModelMock.getSelectedColumn()).thenReturn(null);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onDeleteRowEvent() {
        DeleteRowEvent event = new DeleteRowEvent(ROW_INDEX);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onDisableRightPanelEvent() {
        DisableRightPanelEvent event = new DisableRightPanelEvent();
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onDuplicateRowEvent() {
        DuplicateRowEvent event = new DuplicateRowEvent(ROW_INDEX);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onEnableRightPanelEvent() {
        EnableRightPanelEvent event = new EnableRightPanelEvent();
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onInsertColumnEvent() {
        InsertColumnEvent event = new InsertColumnEvent(COLUMN_INDEX, true, false);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onInsertRowEvent() {
        InsertRowEvent event = new InsertRowEvent(ROW_INDEX);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onPrependColumnEvent() {
        PrependColumnEvent event = new PrependColumnEvent(COLUMN_GROUP);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onPrependRowEvent() {
        PrependRowEvent event = new PrependRowEvent();
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onReloadRightPanelEvent() {
        ReloadRightPanelEvent event = new ReloadRightPanelEvent(true);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onScenarioGridReloadEvent() {
        ScenarioGridReloadEvent event = new ScenarioGridReloadEvent();
        commandExecutor.handle(event);
        verify(scenarioGridPanelMock, times(1)).onResize();
    }

    @Test
    public void onSetInstanceHeaderEvent() {
        SetInstanceHeaderEvent event = new SetInstanceHeaderEvent(FULL_PACKAGE, FULL_CLASS_NAME);
        when(scenarioGridModelMock.getSelectedColumn()).thenReturn(null);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void onSetPropertyHeaderEvent() {
        SetPropertyHeaderEvent event = new SetPropertyHeaderEvent(FULL_PACKAGE, VALUE, VALUE_CLASS_NAME);
        when(scenarioGridModelMock.getSelectedColumn()).thenReturn(null);
        commandExecutor.onEvent(event);
        // TODO verify
    }

    @Test
    public void registerHandlers() {
        commandExecutor.registerHandlers();
        verify(eventBusMock, times(1)).addHandler(eq(AppendColumnEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(appendColumnHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(AppendRowEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(appendRowHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(DeleteColumnEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(deleteColumnHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(DeleteRowEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(deleteRowHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(DisableRightPanelEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(disableRightPanelEventHandlerMock));
        verify(eventBusMock, times(1)).addHandler(eq(DuplicateRowEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(duplicateHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(EnableRightPanelEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(enableRightPanelEventHandlerMock));
        verify(eventBusMock, times(1)).addHandler(eq(InsertColumnEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(insertColumnHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(InsertRowEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(insertRowHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(PrependColumnEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(prependColumnHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(PrependRowEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(prependRowHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(ReloadRightPanelEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(reloadRightPanelHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(ScenarioGridReloadEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(scenarioGridReloadHandlerRegistrationMock));
        verify(eventBusMock, times(1)).addHandler(eq(SetInstanceHeaderEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(setInstanceHeaderEventHandlerMock));
        verify(eventBusMock, times(1)).addHandler(eq(SetPropertyHeaderEvent.TYPE), isA(CommandExecutor.class));
        verify(handlerRegistrationListMock, times(1)).add(eq(setPropertyHeaderEventHandlerMock));
    }
}