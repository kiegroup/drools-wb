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

import com.ait.lienzo.client.core.types.Point2D;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.grids.impl.BaseGridRendererHelper;

import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.convertDOMToGridCoordinate;
import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.getUiColumnIndex;
import static org.uberfire.ext.wires.core.grids.client.util.CoordinateUtilities.getUiRowIndex;

public abstract class AbstractScenarioSimulationGridPanelHandler  {

    protected ScenarioGrid scenarioGrid;
    protected BaseGridRendererHelper rendererHelper;

    public AbstractScenarioSimulationGridPanelHandler() {
    }

    public void setScenarioGrid(ScenarioGrid scenarioGrid) {
        this.scenarioGrid = scenarioGrid;
        this.rendererHelper = scenarioGrid.getRendererHelper();
    }

    /**
     * @param canvasX
     * @param canvasY
     * @return
     */
    protected boolean manageCoordinates(final int canvasX, final int canvasY) {
        final Point2D gridClickPoint = convertDOMToGridCoordinateLocal(canvasX, canvasY);
        Integer uiRowIndex = getUiHeaderRowIndexLocal(gridClickPoint);
        boolean isHeader = true;
        if (uiRowIndex == null) {
            uiRowIndex = getUiRowIndexLocal(gridClickPoint.getY());
            isHeader = false;
        }
        if (uiRowIndex == null) {
            return false;
        }

        final Integer uiColumnIndex = getUiColumnIndexLocal(gridClickPoint.getX());
        if (uiColumnIndex == null) {
            return false;
        }
        ScenarioGridColumn scenarioGridColumn = (ScenarioGridColumn) scenarioGrid.getModel().getColumns().get(uiColumnIndex);
        if (scenarioGridColumn == null) {
            return false;
        }
        if (isHeader) {
            return manageHeaderCoordinates(uiColumnIndex, scenarioGridColumn, gridClickPoint);
        } else {
            return manageGridCoordinates(uiRowIndex, uiColumnIndex);
        }
    }

    /**
     * This method check if the click happened on an <b>second level header</b> (i.e. the header of a specific column) cell. If it is so, manage it and returns <code>true</code>,
     * otherwise returns <code>false</code>
     * @param uiColumnIndex
     * @param scenarioGridColumn
     * @param clickPoint - coordinates relative to the grid top left corner
     * @return
     */
    protected boolean manageHeaderCoordinates(Integer uiColumnIndex, ScenarioGridColumn scenarioGridColumn, Point2D
            clickPoint) {
        //Get row index
        final Integer uiHeaderRowIndex = getUiHeaderRowIndexLocal(clickPoint);
        if (uiHeaderRowIndex == null) {
            return false;
        }
        ScenarioHeaderMetaData clickedScenarioHeaderMetadata = getColumnScenarioHeaderMetaDataLocal(clickPoint);
        if (clickedScenarioHeaderMetadata == null) {
            return false;
        }
        String group = clickedScenarioHeaderMetadata.getColumnGroup();
        if (group.contains("-")) {
            group = group.substring(0, group.indexOf("-"));
        }
        switch (group) {
            case "GIVEN":
            case "EXPECT":
                return manageGivenExpectHeaderCoordinates(clickedScenarioHeaderMetadata,
                                                        scenarioGridColumn,
                                                        group,
                                                        uiColumnIndex);
            default:
                return false;
        }
    }

        /**
     * This method manage the click happened on an <i>GIVEN</i> or <i>EXPECT</i> header, starting editing it if not already did.
     * @param clickedScenarioHeaderMetadata
     * @param scenarioGridColumn
     * @param group
     * @param uiColumnIndex
     * @return
     */
    protected abstract boolean manageGivenExpectHeaderCoordinates(ScenarioHeaderMetaData clickedScenarioHeaderMetadata,
                                                       ScenarioGridColumn scenarioGridColumn,
                                                       String group,
                                                       Integer uiColumnIndex);

    /**
     * This method check if the click happened on an column of a <b>grid row</b>. If it is so, select the cell,
     * otherwise returns <code>false</code>
     * @param uiRowIndex
     * @param uiColumnIndex
     * @return
     */
    protected abstract boolean manageGridCoordinates(Integer uiRowIndex, Integer uiColumnIndex);


    // Indirection add for test
    protected Integer getUiHeaderRowIndexLocal(Point2D clickPoint) {
        return CommonEditHandler.getUiHeaderRowIndexLocal(scenarioGrid, clickPoint);
    }

    // Indirection add for test
    protected Integer getUiRowIndexLocal(double relativeY) {
        return getUiRowIndex(scenarioGrid, relativeY);
    }

    // Indirection add for test
    protected Integer getUiColumnIndexLocal(double relativeX) {
        return getUiColumnIndex(scenarioGrid, relativeX);
    }

    // Indirection add for test
    protected Point2D convertDOMToGridCoordinateLocal(double canvasX, double canvasY) {
        return convertDOMToGridCoordinate(scenarioGrid,
                                          new Point2D(canvasX,
                                                      canvasY));
    }

    // Indirection add for test
    protected ScenarioHeaderMetaData getColumnScenarioHeaderMetaDataLocal(Point2D clickPoint) {
        return CommonEditHandler.getColumnScenarioHeaderMetaDataLocal(scenarioGrid, clickPoint);
    }
}
