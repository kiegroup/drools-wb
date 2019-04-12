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
import javax.inject.Inject;

import com.ait.lienzo.client.core.types.Point2D;
import com.google.gwt.core.client.GWT;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.popup.ErrorReportPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValue;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;
import org.uberfire.mvp.Command;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getMiddleXYCell;

/**
 * This class is meant to provide common implementations for <b>on hover</b> behavior to be used by both mouse keyboard handler
 */
@Dependent
public class CommonOnHoverHandler extends AbstractScenarioSimulationGridPanelHandler {

    @Inject
    protected ErrorReportPopupPresenter errorReportPopupPresenter;

    public void handleOnHover(final int mx, final int my) {
        manageCoordinates(mx, my);
    }

    @Override
    protected boolean manageGivenExpectHeaderCoordinates(ScenarioHeaderMetaData clickedScenarioHeaderMetadata, ScenarioGridColumn scenarioGridColumn, String group, Integer uiColumnIndex) {
        return false;
    }

    @Override
    protected boolean manageBodyCoordinates(Integer uiRowIndex, Integer uiColumnIndex) {
        final Scenario scenarioByIndex = scenarioGrid.getModel().getSimulation().get().getScenarioByIndex(uiRowIndex);
        final Optional<FactMappingValue> factMappingValueByIndex = scenarioByIndex.getFactMappingValueByIndex(uiColumnIndex);
        factMappingValueByIndex.ifPresent(factMappingValue -> {
            if (factMappingValue.isError()) {
                final GridColumn<?> column = scenarioGrid.getModel().getColumns().get(uiColumnIndex);
                final Point2D middleXYCell = getMiddleXYCell(scenarioGrid, column, false, uiRowIndex, (GridLayer) scenarioGrid.getLayer());
                final Object expectedValue = factMappingValue.getRawValue();
                final Object errorValue = factMappingValue.getErrorValue();
                String errorMessage = "The expected value is \" " + expectedValue + " \" but the actual one is \"" + errorValue + "\".";
                errorReportPopupPresenter.show(ScenarioSimulationEditorConstants.INSTANCE.errorReason(),
                                               errorMessage,
                                               ScenarioSimulationEditorConstants.INSTANCE.keep(),
                                               ScenarioSimulationEditorConstants.INSTANCE.apply(),
                                               new Command() {
                                                   @Override
                                                   public void execute() {
                                                       GWT.log("APPLY CHANGE");
                                                   }
                                               },
                                               (int) middleXYCell.getX(),
                                               (int) middleXYCell.getY());
            }
        });
        return false;
    }
}
