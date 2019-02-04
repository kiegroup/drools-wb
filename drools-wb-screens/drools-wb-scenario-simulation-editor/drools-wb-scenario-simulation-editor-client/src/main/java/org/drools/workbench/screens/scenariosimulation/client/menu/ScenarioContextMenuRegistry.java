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

package org.drools.workbench.screens.scenariosimulation.client.menu;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.ait.lienzo.client.core.types.Point2D;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.ExpectedContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.GivenContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.GridContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.HeaderExpectedContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.HeaderGivenContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.OtherContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.editor.menu.UnmodifiableColumnGridContextMenu;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationGridHeaderUtilities;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities;

import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.convertDOMToGridCoordinate;
import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.getRelativeXOfEvent;
import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.getRelativeYOfEvent;
import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.getUiColumnIndex;
import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.getUiRowIndex;

@Dependent
public class ScenarioContextMenuRegistry {

    protected OtherContextMenu otherContextMenu;
    protected HeaderGivenContextMenu headerGivenContextMenu;
    protected HeaderExpectedContextMenu headerExpectedContextMenu;
    protected GivenContextMenu givenContextMenu;
    protected ExpectedContextMenu expectedContextMenu;
    protected GridContextMenu gridContextMenu;
    protected UnmodifiableColumnGridContextMenu unmodifiableColumnGridContextMenu;

    @Inject
    public ScenarioContextMenuRegistry(final OtherContextMenu otherContextMenu,
                                       final HeaderGivenContextMenu headerGivenContextMenu,
                                       final HeaderExpectedContextMenu headerExpectedContextMenu,
                                       final GivenContextMenu givenContextMenu,
                                       final ExpectedContextMenu expectedContextMenu,
                                       final GridContextMenu gridContextMenu,
                                       final UnmodifiableColumnGridContextMenu unmodifiableColumnGridContextMenu) {
        this.otherContextMenu = otherContextMenu;
        this.headerGivenContextMenu = headerGivenContextMenu;
        this.headerExpectedContextMenu = headerExpectedContextMenu;
        this.givenContextMenu = givenContextMenu;
        this.expectedContextMenu = expectedContextMenu;
        this.gridContextMenu = gridContextMenu;
        this.unmodifiableColumnGridContextMenu = unmodifiableColumnGridContextMenu;
    }

    public void setEventBus(final EventBus eventBus) {
        otherContextMenu.setEventBus(eventBus);
        headerGivenContextMenu.setEventBus(eventBus);
        headerExpectedContextMenu.setEventBus(eventBus);
        givenContextMenu.setEventBus(eventBus);
        expectedContextMenu.setEventBus(eventBus);
        gridContextMenu.setEventBus(eventBus);
        unmodifiableColumnGridContextMenu.setEventBus(eventBus);
    }

    public void hideMenus() {
        otherContextMenu.hide();
        headerGivenContextMenu.hide();
        headerExpectedContextMenu.hide();
        givenContextMenu.hide();
        expectedContextMenu.hide();
        gridContextMenu.hide();
        unmodifiableColumnGridContextMenu.hide();
    }

    public boolean manageRightClick(final ScenarioGrid scenarioGrid,
                                    final ContextMenuEvent event) {
        final int canvasX = getRelativeXOfEvent(event);
        final int canvasY = getRelativeYOfEvent(event);
        final Point2D gridClickPoint = convertDOMToGridCoordinate(scenarioGrid,
                                                                  new Point2D(canvasX,
                                                                              canvasY));
        final Integer uiColumnIndex = getUiColumnIndex(scenarioGrid,
                                                       gridClickPoint.getX());
        if (uiColumnIndex == null) {
            return false;
        }
        if (!manageHeaderRightClick(scenarioGrid,
                                    event.getNativeEvent().getClientX(),
                                    event.getNativeEvent().getClientY(),
                                    gridClickPoint,
                                    uiColumnIndex)) {
            return manageBodyRightClickLocal(scenarioGrid,
                                             event.getNativeEvent().getClientX(),
                                             event.getNativeEvent().getClientY(),
                                             gridClickPoint.getY(),
                                             uiColumnIndex);
        } else {
            return true;
        }
    }

    /**
     * This method check if the click happened on an <b>body</b> cell. If it is so, manage it and returns <code>true</code>,
     * otherwise returns <code>false</code>
     * @param scenarioGrid
     * @param left
     * @param top
     * @param gridY
     * @param uiColumnIndex
     * @return
     */
    private boolean manageBodyRightClickLocal(final ScenarioGrid scenarioGrid,
                                              final int left,
                                              final int top,
                                              final double gridY,
                                              final Integer uiColumnIndex) {

        final Integer uiRowIndex = getUiRowIndex(scenarioGrid, gridY);
        if (uiRowIndex == null) {
            return false;
        }
        return manageScenarioBodyRightClick(scenarioGrid, left, top, uiRowIndex, uiColumnIndex);
    }

    /**
     * This method check if the click happened on an <b>body</b> cell. If it is so, manage it and returns <code>true</code>,
     * otherwise returns <code>false</code>
     * @param scenarioGrid
     * @param left
     * @param top
     * @param uiRowIndex
     * @param uiColumnIndex
     * @return
     */
    public boolean manageScenarioBodyRightClick(final ScenarioGrid scenarioGrid,
                                                final int left,
                                                final int top,
                                                final int uiRowIndex,
                                                final int uiColumnIndex) {
        scenarioGrid.deselect();
        ScenarioGridColumn scenarioGridColumn = (ScenarioGridColumn) scenarioGrid.getModel().getColumns().get(uiColumnIndex);
        if (scenarioGridColumn == null) {
            return false;
        }
        String group = scenarioGridColumn.getInformationHeaderMetaData().getColumnGroup();
        switch (group) {
            case "GIVEN":
            case "EXPECT":
                gridContextMenu.show(left, top, uiColumnIndex, uiRowIndex, group, true);
                break;
            default:
                unmodifiableColumnGridContextMenu.show(left, top, uiRowIndex);
        }
        return true;
    }

    /**
     * This method check if the click happened on an <b>header</b> cell. If it is so, manage it and returns <code>true</code>,
     * otherwise returns <code>false</code>
     * @param scenarioGrid
     * @param left
     * @param top
     * @param clickPoint - coordinates relative to the grid top left corner
     * @param uiColumnIndex
     * @return
     */
    private boolean manageHeaderRightClick(final ScenarioGrid scenarioGrid,
                                           final int left,
                                           final int top,
                                           final Point2D clickPoint,
                                           final Integer uiColumnIndex) {
        ScenarioHeaderMetaData columnMetadata = ScenarioSimulationGridHeaderUtilities.getColumnScenarioHeaderMetaData(scenarioGrid, clickPoint);
        if (columnMetadata == null) {
            return false;
        }
        //Get row index
        final Integer uiHeaderRowIndex = CoordinateUtilities.getUiHeaderRowIndex(scenarioGrid, clickPoint);
        if (uiHeaderRowIndex == null) {
            return false;
        }
        String group = columnMetadata.getColumnGroup();
        if (group.contains("-")) {
            group = group.substring(0, group.indexOf("-"));
        }
        switch (group) {
            case "":
                switch (columnMetadata.getTitle()) {
                    case "GIVEN":
                        headerGivenContextMenu.show(left, top);
                        break;
                    case "EXPECT":
                        headerExpectedContextMenu.show(left, top);
                        break;
                    default:
                        otherContextMenu.show(left, top);
                }
                break;
            case "GIVEN":
                givenContextMenu.show(left, top, uiColumnIndex, group, columnMetadata.isPropertyHeader());
                break;
            case "EXPECT":
                expectedContextMenu.show(left, top, uiColumnIndex, group, columnMetadata.isPropertyHeader());
                break;
            default:
                otherContextMenu.show(left, top);
        }
        scenarioGrid.setSelectedColumnAndHeader(uiHeaderRowIndex, uiColumnIndex);
        return true;
    }
}
