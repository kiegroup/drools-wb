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
package org.drools.workbench.screens.scenariosimulation.client.handlers;

import java.util.Optional;

import javax.enterprise.context.Dependent;

import com.ait.lienzo.client.core.types.Point2D;
import org.drools.workbench.screens.scenariosimulation.client.events.SetGridCellValueEvent;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.popover.ErrorReportPopoverPresenter;
import org.drools.workbench.screens.scenariosimulation.client.popover.PopoverView;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValue;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;

/**
 * This class is meant to provide common implementations for <b>on hover</b> behavior to be used by both mouse keyboard handler
 */
@Dependent
public class CommonOnMoveHandler extends AbstractScenarioSimulationGridPanelHandler {

    protected ErrorReportPopoverPresenter errorReportPopupPresenter;

    /* This parameter must be synchronized with POPOVER_WIDTH static variable in ErrorReportPopoverView.less */
    private static int POPOVER_WIDTH = 200;

    protected Integer currentlyShownHeaderRowIndex = -1;
    protected Integer currentlyShownHeaderColumnIndex = -1;
    protected Integer currentlyShownBodyRowIndex = -1;
    protected Integer currentlyShownBodyColumnIndex = -1;

    public void handleOnMove(final int mx, final int my) {
        manageCoordinates(mx, my);
    }

    public void hidePopover() {
        errorReportPopupPresenter.hide();
        resetCurrentlyShowBody();
    }

    public void setErrorReportPopupPresenter(ErrorReportPopoverPresenter errorReportPopupPresenter) {
        this.errorReportPopupPresenter = errorReportPopupPresenter;
    }

    @Override
    protected boolean manageGivenExpectHeaderCoordinates(ScenarioHeaderMetaData clickedScenarioHeaderMetadata, ScenarioGridColumn scenarioGridColumn, String group, Integer uiColumnIndex) {
        return false;
    }

    @Override
    protected boolean manageBodyCoordinates(Integer uiRowIndex, Integer uiColumnIndex) {
        /* If the mouse position is the same of the previous one, do nothig */
        if (uiRowIndex.equals(currentlyShownBodyRowIndex) && uiColumnIndex.equals(currentlyShownBodyColumnIndex)) {
            return false;
        }
        /* In this case, the mouse is out ot the GridLayer, then it resets the coordinates to default */
        if (uiColumnIndex == -1 || uiRowIndex == -1) {
            return resetCurrentlyShowBody();
        }
        currentlyShownBodyRowIndex = uiRowIndex;
        currentlyShownBodyColumnIndex = uiColumnIndex;
        final Scenario scenarioByIndex = scenarioGrid.getModel().getSimulation().get().getScenarioByIndex(uiRowIndex);
        final FactMapping factMapping = scenarioGrid.getModel().getSimulation().get().getSimulationDescriptor().getFactMappingByIndex(uiColumnIndex);
        final Optional<FactMappingValue> factMappingValueOptional = scenarioByIndex.getFactMappingValue(factMapping.getFactIdentifier(), factMapping.getExpressionIdentifier());
        factMappingValueOptional.ifPresent(factMappingValue -> {
            if (factMappingValue.isError()) {
                final GridColumn<?> column = scenarioGrid.getModel().getColumns().get(uiColumnIndex);
                Point2D xYCell = ScenarioSimulationUtils.getMiddleXYCell(scenarioGrid, column, false, uiRowIndex, (GridLayer) scenarioGrid.getLayer());
                PopoverView.Position position = PopoverView.Position.RIGHT;
                int xMiddleWidth = (int) column.getWidth() / 2;
                int xPosition = (int) xYCell.getX() + xMiddleWidth;
                if (xPosition  + POPOVER_WIDTH > scenarioGrid.getLayer().getWidth()) {
                    xPosition = (int) xYCell.getX() - xMiddleWidth;
                    position = PopoverView.Position.LEFT;
                }
                final Object expectedValue = factMappingValue.getRawValue();
                final Object errorValue = factMappingValue.getErrorValue();
                String errorMessage = "The expected value is \" " + expectedValue + " \" but the actual one is \"" + errorValue + "\".";
                errorReportPopupPresenter.show(ScenarioSimulationEditorConstants.INSTANCE.errorReason(),
                                               errorMessage,
                                               ScenarioSimulationEditorConstants.INSTANCE.keep(),
                                               ScenarioSimulationEditorConstants.INSTANCE.apply(),
                                               () -> {
                                                        scenarioGrid.getEventBus().fireEvent(
                                                                new SetGridCellValueEvent(uiRowIndex,
                                                                                          uiColumnIndex,
                                                                                          errorValue.toString().equals("undefined") ? "" : errorValue.toString()));
                                                        CommonOnMoveHandler.this.resetCurrentlyShowBody();
                                               },
                                               () -> CommonOnMoveHandler.this.resetCurrentlyShowBody(),
                                               xPosition,
                                               (int) xYCell.getY(),
                                               position);
            }
        });
        return false;
    }

    protected boolean resetCurrentlyShowBody() {
        currentlyShownBodyColumnIndex = -1;
        currentlyShownBodyRowIndex = -1;
        return false;
    }

}
