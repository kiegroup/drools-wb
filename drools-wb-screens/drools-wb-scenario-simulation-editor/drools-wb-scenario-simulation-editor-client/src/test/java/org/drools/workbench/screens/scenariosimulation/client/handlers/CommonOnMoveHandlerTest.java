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

import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.popover.ErrorReportPopoverPresenter;
import org.drools.workbench.screens.scenariosimulation.client.popover.PopoverView;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValue;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.mvp.Command;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class CommonOnMoveHandlerTest extends AbstractScenarioSimulationGridHandlerTest {

    private final static Integer DX = 325;
    private final static Integer DY = 250;
    private final static Double CELL_WIDTH = 50d;
    private final static Integer LARGE_LAYER = 1000;
    private final static Integer TINY_LAYER = 100;
    private final static String RAW_VALUE = "raw";
    private final static String ERROR_VALUE = "error";

    private CommonOnMoveHandler commonOnMoveHandler;

    @Mock
    private ErrorReportPopoverPresenter errorReportPopupPresenterMock;
    @Mock
    private Scenario scenarioMock;
    @Mock
    private FactMappingValue factMappingValueMock;

    @Before
    public void setup() {
        super.setup();
        commonOnMoveHandler = spy(new CommonOnMoveHandler() {
            {
                errorReportPopupPresenter = errorReportPopupPresenterMock;
                scenarioGrid = scenarioGridMock;
            }

            @Override
            protected Point2D retrieveCellMiddleXYPosition(GridColumn<?> column, int uiRowIndex) {
                return new Point2D(DX, DY);
            }

            @Override
            protected Point2D convertDOMToGridCoordinateLocal(double canvasX, double canvasY) {
                return new Point2D(MX, MY);
            }
        });
        when(simulationMock.getScenarioByIndex(isA(Integer.class))).thenReturn(scenarioMock);
        when(scenarioMock.getFactMappingValue(any())).thenReturn(Optional.of(factMappingValueMock));
        when(factMappingValueMock.isError()).thenReturn(true);
        when(factMappingValueMock.getRawValue()).thenReturn(RAW_VALUE);
        when(factMappingValueMock.getErrorValue()).thenReturn(ERROR_VALUE);
        when(scenarioGridLayerMock.getWidth()).thenReturn(LARGE_LAYER);
        when(gridColumnMock.getWidth()).thenReturn(CELL_WIDTH);
    }

    @Test
    public void handleOnMove() {
        commonOnMoveHandler.manageCoordinates(MX, MY);
        verify(commonOnMoveHandler, times(1)).manageCoordinates(eq(MX), eq(MY));
    }

    @Test
    public void manageBodyCoordinates_Right() {
        commonOnMoveHandler.manageBodyCoordinates(ROW_INDEX, COLUMN_INDEX);
        verify(commonOnMoveHandler, never()).resetCurrentlyShowBodyCoordinates();
        verify(simulationMock, times(1)).getScenarioByIndex(eq(ROW_INDEX));
        verify(simulationDescriptorMock, times(1)).getFactMappingByIndex(eq(COLUMN_INDEX));
        verify(scenarioMock, times(1)).getFactMappingValue(eq(factMappingMock));
        verify(commonOnMoveHandler, times(1)).retrieveCellMiddleXYPosition(gridColumnMock, ROW_INDEX);
        verify(errorReportPopupPresenterMock, times(1)).show(
                eq(ScenarioSimulationEditorConstants.INSTANCE.errorReason()),
                eq(ScenarioSimulationEditorConstants.INSTANCE.errorPopoverMessage(RAW_VALUE, ERROR_VALUE)),
                eq(ScenarioSimulationEditorConstants.INSTANCE.keep()),
                eq(ScenarioSimulationEditorConstants.INSTANCE.apply()),
                isA(Command.class),
                isA(Command.class),
                eq((int) (CELL_WIDTH / 2) + DX),
                eq(DY),
                eq(PopoverView.Position.RIGHT));
    }

    @Test
    public void manageBodyCoordinates_Left() {
        when(scenarioGridLayerMock.getWidth()).thenReturn(TINY_LAYER);
        commonOnMoveHandler.manageBodyCoordinates(ROW_INDEX, COLUMN_INDEX);
        verify(commonOnMoveHandler, never()).resetCurrentlyShowBodyCoordinates();
        verify(simulationMock, times(1)).getScenarioByIndex(eq(ROW_INDEX));
        verify(simulationDescriptorMock, times(1)).getFactMappingByIndex(eq(COLUMN_INDEX));
        verify(scenarioMock, times(1)).getFactMappingValue(eq(factMappingMock));
        verify(commonOnMoveHandler, times(1)).retrieveCellMiddleXYPosition(gridColumnMock, ROW_INDEX);
        verify(errorReportPopupPresenterMock, times(1)).show(
                eq(ScenarioSimulationEditorConstants.INSTANCE.errorReason()),
                eq(ScenarioSimulationEditorConstants.INSTANCE.errorPopoverMessage(RAW_VALUE, ERROR_VALUE)),
                eq(ScenarioSimulationEditorConstants.INSTANCE.keep()),
                eq(ScenarioSimulationEditorConstants.INSTANCE.apply()),
                isA(Command.class),
                isA(Command.class),
                eq((int) (DX - (CELL_WIDTH / 2))),
                eq(DY),
                eq(PopoverView.Position.LEFT));
    }

    @Test
    public void manageBodyCoordinates_NoError() {
        when(factMappingValueMock.isError()).thenReturn(false);
        commonOnMoveHandler.manageBodyCoordinates(ROW_INDEX, COLUMN_INDEX);
        verify(commonOnMoveHandler, never()).resetCurrentlyShowBodyCoordinates();
        verify(simulationMock, times(1)).getScenarioByIndex(eq(ROW_INDEX));
        verify(simulationDescriptorMock, times(1)).getFactMappingByIndex(eq(COLUMN_INDEX));
        verify(scenarioMock, times(1)).getFactMappingValue(eq(factMappingMock));
        verify(commonOnMoveHandler, never()).retrieveCellMiddleXYPosition(any(), isA(Integer.class));
        verify(errorReportPopupPresenterMock, never()).show(any(), any(), any(), any(), any(), any(), isA(Integer.class), isA(Integer.class), any());
    }

    @Test
    public void manageBodyCoordinates_sameCell() {
        commonOnMoveHandler.manageBodyCoordinates(-1, -1);
        verify(commonOnMoveHandler, never()).resetCurrentlyShowBodyCoordinates();
        verify(simulationMock, never()).getScenarioByIndex(isA(Integer.class));
        verify(simulationDescriptorMock, never()).getFactMappingByIndex(isA(Integer.class));
        verify(scenarioMock, never()).getFactMappingValue(any());
        verify(commonOnMoveHandler, never()).retrieveCellMiddleXYPosition(any(), isA(Integer.class));
        verify(errorReportPopupPresenterMock, never()).show(any(), any(), any(), any(), any(), any(), isA(Integer.class), isA(Integer.class), any());
    }

    @Test
    public void manageBodyCoordinates_awayFromGrid() {
        commonOnMoveHandler = spy(new CommonOnMoveHandler() {
            {
                currentlyShownBodyColumnIndex = 0;
                currentlyShownBodyRowIndex = 0;
                errorReportPopupPresenter = errorReportPopupPresenterMock;
                scenarioGrid = scenarioGridMock;
            }
        });
        commonOnMoveHandler.manageBodyCoordinates(-1, -1);
        verify(commonOnMoveHandler, times(1)).resetCurrentlyShowBodyCoordinates();
        verify(simulationMock, never()).getScenarioByIndex(isA(Integer.class));
        verify(simulationDescriptorMock, never()).getFactMappingByIndex(isA(Integer.class));
        verify(scenarioMock, never()).getFactMappingValue(any());
        verify(commonOnMoveHandler, never()).retrieveCellMiddleXYPosition(any(), isA(Integer.class));
        verify(errorReportPopupPresenterMock, never()).show(any(), any(), any(), any(), any(), any(), isA(Integer.class), isA(Integer.class), any());
    }

    @Test
    public void hidePopover() {
        commonOnMoveHandler.hidePopover();
        verify(errorReportPopupPresenterMock, times(1)).hide();
        verify(commonOnMoveHandler, times(1)).resetCurrentlyShowBodyCoordinates();
    }

    @Test
    public void resetCurrentlyShowBodyCoordinates() {
        commonOnMoveHandler.resetCurrentlyShowBodyCoordinates();
        assertTrue(commonOnMoveHandler.currentlyShownBodyColumnIndex == -1);
        assertTrue(commonOnMoveHandler.currentlyShownBodyRowIndex == -1);
    }

}
