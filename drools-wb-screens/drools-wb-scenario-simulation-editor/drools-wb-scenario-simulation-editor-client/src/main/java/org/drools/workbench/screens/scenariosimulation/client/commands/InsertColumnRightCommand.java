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

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.uberfire.mvp.Command;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getScenarioGridColumn;

/**
 * <code>Command</code> to <b>insert</b> a column to the right of the selected one
 */
@Dependent
public class InsertColumnRightCommand implements Command {

    private ScenarioGridModel model;
    private String columnId;
    private ScenarioGridPanel scenarioGridPanel;
    private ScenarioGridLayer scenarioGridLayer;

    public InsertColumnRightCommand() {
    }

    public InsertColumnRightCommand(ScenarioGridModel model, String columnId, ScenarioGridPanel scenarioGridPanel, ScenarioGridLayer scenarioGridLayer) {
        this.model = model;
        this.columnId = columnId;
        this.scenarioGridPanel = scenarioGridPanel;
        this.scenarioGridLayer = scenarioGridLayer;
    }

    @Override
    public void execute() {
        final int index = model.getSelectedCellsOrigin().getColumnIndex() + 1;
        String columnGroup = model.getColumns().get(index).getHeaderMetaData().get(1).getColumnGroup();
        String columnTitle = columnGroup.toUpperCase() + "-" + (model.getGroupSize(columnGroup) + 1);
        model.insertColumn(index, getScenarioGridColumn(columnId, columnTitle, columnGroup, scenarioGridPanel, scenarioGridLayer));
    }
}
