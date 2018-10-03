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

package org.drools.workbench.screens.scenariosimulation.client.handlers;

import java.util.Collections;
import java.util.Set;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.AbstractHeaderMenuPresenter;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.ExpectedContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.GivenContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.GridContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.HeaderExpectedContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.HeaderGivenContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.OtherContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.UnmodifiableColumnGridContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.events.EnableRightPanelEvent;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.grids.GridRenderer;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.grids.impl.BaseGridRendererHelper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class ScenarioSimulationGridPanelClickHandlerTest {

    final Double GRID_WIDTH = 100.0;
    final Double HEADER_HEIGHT = 10.0;
    final Double HEADER_ROW_HEIGHT = 10.0;
    final int UI_COLUMN_INDEX = 0;
    final int CLICK_POINT_X = 5;
    final int CLICK_POINT_Y = 5;
    final boolean SHIFT_PRESSED = false;
    final boolean CTRL_PRESSED = false;

    private ScenarioSimulationGridPanelClickHandler scenarioSimulationGridPanelClickHandler;

    @Mock
    private ScenarioGridPanel mockScenarioGridPanel;

    @Mock
    private ScenarioGrid mockScenarioGrid;

    @Mock
    private ScenarioGridModel scenarioGridModel;

    @Mock
    private GridRenderer scenarioGridRenderer;

    @Mock
    private BaseGridRendererHelper scenarioGridRendererHelper;

    @Mock
    private BaseGridRendererHelper.RenderingInformation scenarioRenderingInformation;

    @Mock
    private ScenarioHeaderMetaData headerMetaData;

    @Mock
    private OtherContextMenu mockOtherContextMenu;
    @Mock
    private HeaderGivenContextMenu mockHeaderGivenContextMenu;
    @Mock
    private HeaderExpectedContextMenu mockHeaderExpectedContextMenu;
    @Mock
    private GivenContextMenu mockGivenContextMenu;
    @Mock
    private ExpectedContextMenu mockExpectedContextMenu;
    @Mock
    private GridContextMenu mockGridContextMenu;
    @Mock
    private UnmodifiableColumnGridContextMenu mockUnmodifiableColumnGridContextMenu;

    @Mock
    private Element mockTarget;

    @Mock
    private NativeEvent mockNativeEvent;

    @Mock
    private Document mockDocument;

    @Mock
    private ContextMenuEvent mockContextMenuEvent;

    @Mock
    private Set<AbstractHeaderMenuPresenter> mockManagedMenus;

    @Mock
    private EventBus mockEventBus;

    @Before
    public void setUp() throws Exception {
        when(mockScenarioGridPanel.getScenarioGrid()).thenReturn(mockScenarioGrid);
        when(mockScenarioGrid.getWidth()).thenReturn(GRID_WIDTH);
        when(mockScenarioGrid.getModel()).thenReturn(scenarioGridModel);
        when(mockScenarioGrid.getRenderer()).thenReturn(scenarioGridRenderer);
        when(mockScenarioGrid.getRendererHelper()).thenReturn(scenarioGridRendererHelper);
        when(scenarioGridRenderer.getHeaderHeight()).thenReturn(HEADER_HEIGHT);
        when(scenarioGridRenderer.getHeaderRowHeight()).thenReturn(HEADER_ROW_HEIGHT);
        when(scenarioGridRendererHelper.getRenderingInformation()).thenReturn(scenarioRenderingInformation);

        // mock single column in grid
        ScenarioGridColumn column = mock(ScenarioGridColumn.class);
        when(scenarioGridModel.getColumns()).thenReturn(Collections.singletonList(column));
        when(scenarioGridModel.getColumnCount()).thenReturn(1);

        // presence of header metadata is prerequisite to handle header click
        // to simplify test, return just one header metadata
        // it simulates just one row in column header rows
        when(column.getHeaderMetaData()).thenReturn(Collections.singletonList(headerMetaData));
        when(headerMetaData.getColumnGroup()).thenReturn("GIVEN");

        // mock that column to index 0
        BaseGridRendererHelper.ColumnInformation columnInformation =
                new BaseGridRendererHelper.ColumnInformation(column, UI_COLUMN_INDEX, 0);
        when(scenarioGridRendererHelper.getColumnInformation(CLICK_POINT_X)).thenReturn(columnInformation);

        scenarioSimulationGridPanelClickHandler = spy(new ScenarioSimulationGridPanelClickHandler() {
            {
                scenarioGrid = mockScenarioGrid;
                otherContextMenu = mockOtherContextMenu;
                headerGivenContextMenu = mockHeaderGivenContextMenu;
                headerExpectedContextMenu = mockHeaderExpectedContextMenu;
                givenContextMenu = mockGivenContextMenu;
                expectedContextMenu = mockExpectedContextMenu;
                gridContextMenu = mockGridContextMenu;
                unmodifiableColumnGridContextMenu = mockUnmodifiableColumnGridContextMenu;
                managedMenus.add(mockOtherContextMenu);
                managedMenus.add(mockHeaderGivenContextMenu);
                managedMenus.add(mockHeaderExpectedContextMenu);
                managedMenus.add(mockGivenContextMenu);
                managedMenus.add(mockExpectedContextMenu);
                managedMenus.add(mockGridContextMenu);
                managedMenus.add(mockUnmodifiableColumnGridContextMenu);
            }

            @Override
            protected boolean manageRightClick(ContextMenuEvent event) {
                return true;
            }
        });
        mockManagedMenus = spy(scenarioSimulationGridPanelClickHandler.managedMenus);

        when(mockNativeEvent.getClientX()).thenReturn(100);
        when(mockNativeEvent.getClientY()).thenReturn(100);

        when(mockTarget.getOwnerDocument()).thenReturn(mockDocument);
        when(mockTarget.getAbsoluteLeft()).thenReturn(50);
        when(mockTarget.getScrollLeft()).thenReturn(20);
        when(mockTarget.getAbsoluteTop()).thenReturn(50);
        when(mockTarget.getScrollTop()).thenReturn(20);

        when(mockDocument.getScrollLeft()).thenReturn(10);
        when(mockDocument.getScrollTop()).thenReturn(10);

        when(mockContextMenuEvent.getNativeEvent()).thenReturn(mockNativeEvent);
        when(mockContextMenuEvent.getRelativeElement()).thenReturn(mockTarget);
    }

    @Test
    public void setScenarioGrid() {
        scenarioSimulationGridPanelClickHandler.setScenarioGrid(mockScenarioGrid);
        assertEquals(mockScenarioGrid, scenarioSimulationGridPanelClickHandler.scenarioGrid);
    }

    @Test
    public void setOtherContextMenu() {
        scenarioSimulationGridPanelClickHandler.setOtherContextMenu(mockOtherContextMenu);
        assertEquals(mockOtherContextMenu, scenarioSimulationGridPanelClickHandler.otherContextMenu);
    }

    @Test
    public void setHeaderGivenContextMenu() {
        scenarioSimulationGridPanelClickHandler.setHeaderGivenContextMenu(mockHeaderGivenContextMenu);
        assertEquals(mockHeaderGivenContextMenu, scenarioSimulationGridPanelClickHandler.headerGivenContextMenu);
    }

    @Test
    public void setHeaderExpectedContextMenu() {
        scenarioSimulationGridPanelClickHandler.setHeaderExpectedContextMenu(mockHeaderExpectedContextMenu);
        assertEquals(mockHeaderExpectedContextMenu, scenarioSimulationGridPanelClickHandler.headerExpectedContextMenu);
    }

    @Test
    public void setGivenContextMenu() {
        scenarioSimulationGridPanelClickHandler.setGivenContextMenu(mockGivenContextMenu);
        assertEquals(mockGivenContextMenu, scenarioSimulationGridPanelClickHandler.givenContextMenu);
    }

    @Test
    public void setExpectedContextMenu() {
        scenarioSimulationGridPanelClickHandler.setExpectedContextMenu(mockExpectedContextMenu);
        assertEquals(mockExpectedContextMenu, scenarioSimulationGridPanelClickHandler.expectedContextMenu);
    }

    @Test
    public void setGridContextMenu() {
        scenarioSimulationGridPanelClickHandler.setGridContextMenu(mockGridContextMenu);
        assertEquals(mockGridContextMenu, scenarioSimulationGridPanelClickHandler.gridContextMenu);
    }

    @Test
    public void setUnmodifiableColumnGridContextMenu() {
        scenarioSimulationGridPanelClickHandler.setUnmodifiableColumnGridContextMenu(mockUnmodifiableColumnGridContextMenu);
        assertEquals(mockUnmodifiableColumnGridContextMenu, scenarioSimulationGridPanelClickHandler.unmodifiableColumnGridContextMenu);
    }

    @Test
    public void setEventBus() {
        scenarioSimulationGridPanelClickHandler.setEventBus(mockEventBus);
        verify(mockOtherContextMenu, times(1)).setEventBus(eq(mockEventBus));
        verify(mockHeaderGivenContextMenu, times(1)).setEventBus(eq(mockEventBus));
        verify(mockHeaderExpectedContextMenu, times(1)).setEventBus(eq(mockEventBus));
        verify(mockGivenContextMenu, times(1)).setEventBus(eq(mockEventBus));
        verify(mockExpectedContextMenu, times(1)).setEventBus(eq(mockEventBus));
        verify(mockGridContextMenu, times(1)).setEventBus(eq(mockEventBus));
        verify(mockUnmodifiableColumnGridContextMenu, times(1)).setEventBus(eq(mockEventBus));
    }

    @Test
    public void getRelativeX() {
        int retrieved = scenarioSimulationGridPanelClickHandler.getRelativeX(mockContextMenuEvent);
        assertEquals(80, retrieved);
    }

    @Test
    public void getRelativeY() {
        int retrieved = scenarioSimulationGridPanelClickHandler.getRelativeY(mockContextMenuEvent);
        assertEquals(80, retrieved);
    }

    @Test
    public void commonClickManagement() {
        scenarioSimulationGridPanelClickHandler.hideMenus();
        verify(mockOtherContextMenu, times(1)).hide();
        verify(mockHeaderGivenContextMenu, times(1)).hide();
        verify(mockHeaderExpectedContextMenu, times(1)).hide();
        verify(mockGivenContextMenu, times(1)).hide();
        verify(mockExpectedContextMenu, times(1)).hide();
        verify(mockGridContextMenu, times(1)).hide();
        verify(mockUnmodifiableColumnGridContextMenu, times(1)).hide();
    }

    @Test
    public void onContextMenu() {
        scenarioSimulationGridPanelClickHandler.onContextMenu(mockContextMenuEvent);
        verify(mockContextMenuEvent, times(1)).preventDefault();
        verify(mockContextMenuEvent, times(1)).stopPropagation();
        commonCheck();
    }

    @Test
    public void testManageLeftClick() {
        scenarioSimulationGridPanelClickHandler.setEventBus(mockEventBus);
        assertTrue("Click to only header cell of the only present column.",
                   scenarioSimulationGridPanelClickHandler.manageLeftClick(CLICK_POINT_X,
                                                                           CLICK_POINT_Y,
                                                                           SHIFT_PRESSED,
                                                                           CTRL_PRESSED));
        verify(mockScenarioGrid).selectColumn(UI_COLUMN_INDEX);
        verify(mockEventBus).fireEvent(any(EnableRightPanelEvent.class));
    }

    @Test
    public void testManageLeftClick_ReadOnly() {
        when(headerMetaData.isReadOnly()).thenReturn(true);

        scenarioSimulationGridPanelClickHandler.setEventBus(mockEventBus);
        assertFalse("Click to readonly header cell.",
                    scenarioSimulationGridPanelClickHandler.manageLeftClick(CLICK_POINT_X,
                                                                            CLICK_POINT_Y,
                                                                            SHIFT_PRESSED,
                                                                            CTRL_PRESSED));
        verify(mockScenarioGrid, never()).selectColumn(anyInt());
        verify(mockEventBus, never()).fireEvent(any(EnableRightPanelEvent.class));
    }

    @Test
    public void testManageLeftClick_NextToGrid() {
        assertFalse("Click to point next to table.",
                    scenarioSimulationGridPanelClickHandler.manageLeftClick(GRID_WIDTH.intValue() + CLICK_POINT_X,
                                                                            CLICK_POINT_Y,
                                                                            SHIFT_PRESSED,
                                                                            CTRL_PRESSED));
        verify(mockScenarioGrid, never()).selectColumn(anyInt());
        verify(mockEventBus, never()).fireEvent(any(EnableRightPanelEvent.class));
    }

    @Test
    public void testManageLeftClick_BelowHeader() {
        assertFalse("Click to point below header.",
                    scenarioSimulationGridPanelClickHandler.manageLeftClick(CLICK_POINT_X,
                                                                            HEADER_HEIGHT.intValue() + CLICK_POINT_X,
                                                                            SHIFT_PRESSED,
                                                                            CTRL_PRESSED));
        verify(mockScenarioGrid, never()).selectColumn(anyInt());
        verify(mockEventBus, never()).fireEvent(any(EnableRightPanelEvent.class));
    }

    private void commonCheck() {
        verify(scenarioSimulationGridPanelClickHandler, times(1)).hideMenus();
    }
}