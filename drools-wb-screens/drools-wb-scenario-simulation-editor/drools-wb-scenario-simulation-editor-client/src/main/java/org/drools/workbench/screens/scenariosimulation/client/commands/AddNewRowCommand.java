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
package org.drools.workbench.screens.scenariosimulation.client.commands;

import java.util.stream.IntStream;

import com.google.gwt.core.client.GWT;
import org.drools.workbench.screens.scenariosimulation.client.values.ScenarioGridCellValue;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridCell;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridRow;
import org.uberfire.ext.wires.core.grids.client.model.GridData;
import org.uberfire.mvp.Command;

public class AddNewRowCommand implements Command {

    private final ScenarioGrid scenarioGrid;


    public AddNewRowCommand(ScenarioGrid scenarioGrid) {
        this.scenarioGrid = scenarioGrid;
    }

    @Override
    public void execute() {
        GWT.log("AddNewRowCommand execute " + scenarioGrid);
        GridData model = scenarioGrid.getModel();
        model.appendRow(new ScenarioGridRow());
        int rowIndex = model.getRowCount() -1;
        IntStream.range(0, model.getColumnCount())
                .forEach(columnIndex -> model.setCell(rowIndex, columnIndex, () -> new ScenarioGridCell(new ScenarioGridCellValue(""))));

    }
}
