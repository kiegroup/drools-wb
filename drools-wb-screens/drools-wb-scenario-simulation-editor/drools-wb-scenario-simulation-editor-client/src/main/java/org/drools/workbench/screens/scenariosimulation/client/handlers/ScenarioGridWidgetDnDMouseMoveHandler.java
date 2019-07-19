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

import com.ait.lienzo.client.core.event.NodeMouseMoveEvent;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.widget.dnd.GridWidgetDnDHandlersState;
import org.uberfire.ext.wires.core.grids.client.widget.dnd.GridWidgetDnDMouseMoveHandler;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;

public class ScenarioGridWidgetDnDMouseMoveHandler extends GridWidgetDnDMouseMoveHandler {

    public ScenarioGridWidgetDnDMouseMoveHandler(GridLayer layer, GridWidgetDnDHandlersState state) {
        super(layer, state);
    }

    @Override
    public void onNodeMouseMove(final NodeMouseMoveEvent event) {
        super.onNodeMouseMove(event);
        /*if (GridWidgetDnDHandlersState.GridWidgetHandlersOperation.COLUMN_RESIZE.equals(state.getOperation())) {
            ScenarioGridModel model = ((ScenarioGridLayer) layer).getScenarioGrid().getModel();
        } */
    }

    protected double adjustColumnWidth(double columnNewWidth, GridColumn<?> activeGridColumn, GridWidget activeGridWidget) {
        double columnWidth = super.adjustColumnWidth(columnNewWidth, activeGridColumn, activeGridWidget);
        final ScenarioGridModel model = ((ScenarioGridLayer) layer).getScenarioGrid().getModel();
        final ScenarioGridColumn resizedColumn = ((ScenarioGridColumn) activeGridColumn);
        int resizedColumnIndex = model.getColumns().indexOf(resizedColumn);
        final FactMapping factMapping = model.getSimulation().get().getSimulationDescriptor().getFactMappingByIndex(resizedColumnIndex);
        factMapping.setColumnWidth(columnWidth);
        return columnWidth;
    }
}
