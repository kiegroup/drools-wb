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
package org.drools.workbench.screens.scesim.client.handlers;

import java.util.List;
import java.util.stream.Collectors;

import com.ait.lienzo.client.core.event.NodeMouseDoubleClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseDoubleClickHandler;
import com.ait.lienzo.client.core.shape.Layer;
import org.drools.workbench.screens.scesim.client.utils.Tooltipper;
import org.drools.workbench.screens.scesim.client.widgets.ScenarioGrid;
import org.uberfire.ext.wires.core.grids.client.model.GridCell;
import org.uberfire.ext.wires.core.grids.client.model.GridData;

public class ScenarioGridDoubleClickHandler implements NodeMouseDoubleClickHandler {

    /**
     * The Lienzo container
     */
    private Layer parent;

    public ScenarioGridDoubleClickHandler(Layer parent) {
        this.parent = parent;
    }

    @Override
    public void onNodeMouseDoubleClick(NodeMouseDoubleClickEvent event) {
        String toShow;
        if (event.getSource() instanceof ScenarioGrid) {
            toShow = getScenarioGridEventString((ScenarioGrid) event.getSource());
        } else {
            toShow = event.getMouseEvent().getSource() + " - " + event.getNodeEvent().toDebugString();
        }
        Tooltipper.showToolTip(toShow, parent);
    }

    private String getScenarioGridEventString(final ScenarioGrid source) {
        List<GridData.SelectedCell> selectedCells = source.getModel().getSelectedCells();
        return selectedCells.stream()
                .map(selectedCell -> (GridCell<String>) source.getModel().getCell(selectedCell.getRowIndex(), selectedCell.getColumnIndex()))
                .map(selectedCell -> selectedCell.getValue().getValue())
                .collect(Collectors.joining(","));
    }
}
