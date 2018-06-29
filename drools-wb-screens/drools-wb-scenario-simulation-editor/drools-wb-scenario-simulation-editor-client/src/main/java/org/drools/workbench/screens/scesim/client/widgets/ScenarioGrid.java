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
package org.drools.workbench.screens.scesim.client.widgets;

import org.drools.workbench.screens.scesim.client.handlers.ScenarioGridDoubleClickHandler;
import org.drools.workbench.screens.scesim.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scesim.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scesim.client.renderers.ScenarioGridColumnRenderer;
import org.drools.workbench.screens.scesim.client.renderers.ScenarioGridRenderer;
import org.drools.workbench.screens.scesim.client.values.ScenarioGridCellValue;
import org.uberfire.ext.wires.core.grids.client.widget.grid.impl.BaseGridWidget;

public class ScenarioGrid extends BaseGridWidget {

    public ScenarioGrid(ScenarioGridModel model, ScenarioGridLayer gridLayer, ScenarioGridRenderer renderer) {
        super(model, gridLayer, gridLayer, renderer);
        model.appendColumn(new ScenarioGridColumn(new ScenarioHeaderMetaData("T"), new ScenarioGridColumnRenderer(), 100));
        model.appendColumn(new ScenarioGridColumn(new ScenarioHeaderMetaData(""), new ScenarioGridColumnRenderer(), 100));
        model.appendColumn(new ScenarioGridColumn(new ScenarioHeaderMetaData("Given"), new ScenarioGridColumnRenderer(), 100));
        model.appendColumn(new ScenarioGridColumn(new ScenarioHeaderMetaData("Expect"), new ScenarioGridColumnRenderer(), 100));

        model.appendRow(new ScenarioGridRow());
        model.appendRow(new ScenarioGridRow());
        model.appendRow(new ScenarioGridRow());

        model.setCell(0, 0, () -> new ScenarioGridCell(new ScenarioGridCellValue("T")));
        model.setCell(0, 1, () -> new ScenarioGridCell(new ScenarioGridCellValue("")));
        model.setCell(0, 2, () -> new ScenarioGridCell(new ScenarioGridCellValue("BindingGiven")));
        model.setCell(0, 3, () -> new ScenarioGridCell(new ScenarioGridCellValue("BindingExpect")));

        model.setCell(1, 0, () -> new ScenarioGridCell(new ScenarioGridCellValue("1")));
        model.setCell(1, 1, () -> new ScenarioGridCell(new ScenarioGridCellValue("Scenario Description")));
        model.setCell(1, 2, () -> new ScenarioGridCell(new ScenarioGridCellValue("1")));
        model.setCell(1, 3, () -> new ScenarioGridCell(new ScenarioGridCellValue("2")));

        model.setCell(2, 0, () -> new ScenarioGridCell(new ScenarioGridCellValue("2")));
        model.setCell(2, 1, () -> new ScenarioGridCell(new ScenarioGridCellValue("BindingGiven")));
        model.setCell(2, 2, () -> new ScenarioGridCell(new ScenarioGridCellValue("Scenario Description")));
        model.setCell(2, 3, () -> new ScenarioGridCell(new ScenarioGridCellValue("2")));

        this.addNodeMouseDoubleClickHandler(new ScenarioGridDoubleClickHandler(gridLayer));
    }
}