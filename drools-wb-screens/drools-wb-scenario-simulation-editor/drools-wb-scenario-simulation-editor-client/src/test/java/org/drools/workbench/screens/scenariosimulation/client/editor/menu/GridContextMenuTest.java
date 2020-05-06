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
package org.drools.workbench.screens.scenariosimulation.client.editor.menu;

import com.google.gwt.dom.client.LIElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.events.DeleteRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DuplicateRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.drools.workbench.screens.scenariosimulation.client.editor.menu.GridContextMenu.GRIDCONTEXTMENU_DELETE_ROW;
import static org.drools.workbench.screens.scenariosimulation.client.editor.menu.GridContextMenu.GRIDCONTEXTMENU_DUPLICATE_ROW;
import static org.drools.workbench.screens.scenariosimulation.client.editor.menu.GridContextMenu.GRIDCONTEXTMENU_GRID_TITLE;
import static org.drools.workbench.screens.scenariosimulation.client.editor.menu.GridContextMenu.GRIDCONTEXTMENU_INSERT_ROW_ABOVE;
import static org.drools.workbench.screens.scenariosimulation.client.editor.menu.GridContextMenu.GRIDCONTEXTMENU_INSERT_ROW_BELOW;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public abstract class GridContextMenuTest {

    @Mock
    protected LIElement insertRowAboveLIElementMock;
    @Mock
    protected LIElement insertRowBelowLIElementMock;
    @Mock
    protected LIElement duplicateRowLIElementMock;
    @Mock
    protected LIElement deleteRowLIElementMock;
    @Mock
    protected LIElement gridTitleElementMock;

    protected void initMenu(GridContextMenu gridContextMenu) {
        gridContextMenu.initMenu();
        verify(gridContextMenu, times(1)).addExecutableMenuItem(eq(GRIDCONTEXTMENU_INSERT_ROW_ABOVE), eq(ScenarioSimulationEditorConstants.INSTANCE.insertRowAbove()), eq("insertRowAbove"));
        verify(gridContextMenu, times(1)).addExecutableMenuItem(eq(GRIDCONTEXTMENU_INSERT_ROW_BELOW), eq(ScenarioSimulationEditorConstants.INSTANCE.insertRowBelow()), eq("insertRowBelow"));
        verify(gridContextMenu, times(1)).addExecutableMenuItem(eq(GRIDCONTEXTMENU_DUPLICATE_ROW), eq(ScenarioSimulationEditorConstants.INSTANCE.duplicateRow()), eq("duplicateRow"));
        verify(gridContextMenu, times(1)).addExecutableMenuItem(eq(GRIDCONTEXTMENU_DELETE_ROW), eq(ScenarioSimulationEditorConstants.INSTANCE.deleteRow()), eq("deleteRow"));
    }

    protected void show(GridContextMenu gridContextMenu, GridWidget gridWidget, int mx, int my, int rowIndex) {
        String expectedLabel;
        String expectedI18n;
        if (GridWidget.BACKGROUND.equals(gridWidget)) {
            expectedLabel = ScenarioSimulationEditorConstants.INSTANCE.background();
            expectedI18n = "background";
        } else {
            expectedLabel = ScenarioSimulationEditorConstants.INSTANCE.scenario();
            expectedI18n = "scenario";
        }
        gridContextMenu.show(gridWidget, mx, my, rowIndex);
        verify(gridContextMenu, times(1)).show(eq(gridWidget), eq(0), eq(0));
        verify(gridContextMenu, times(1)).mapEvent(eq(insertRowAboveLIElementMock), isA(InsertRowEvent.class));
        verify(gridContextMenu, times(1)).mapEvent(eq(insertRowBelowLIElementMock), isA(InsertRowEvent.class));
        verify(gridContextMenu, times(1)).mapEvent(eq(duplicateRowLIElementMock), isA(DuplicateRowEvent.class));
        verify(gridContextMenu, times(1)).mapEvent(eq(deleteRowLIElementMock), isA(DeleteRowEvent.class));
        verify(gridContextMenu, times(1)).updateMenuItemAttributes(eq(gridTitleElementMock), eq(GRIDCONTEXTMENU_GRID_TITLE), eq(expectedLabel), eq(expectedI18n));
    }
}
