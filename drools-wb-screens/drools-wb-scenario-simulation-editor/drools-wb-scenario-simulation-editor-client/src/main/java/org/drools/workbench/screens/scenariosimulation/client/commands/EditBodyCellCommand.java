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

import com.google.gwt.core.client.GWT;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.uberfire.mvp.Command;

public class EditBodyCellCommand implements Command {

    private final ScenarioGridPanel scenarioGridPanel;
    private final ScenarioGridLayer gridLayer;
    private final ScenarioGrid scenarioGrid;


    public EditBodyCellCommand(ScenarioGridPanel scenarioGridPanel, ScenarioGridLayer gridLayer) {
        this.scenarioGridPanel = scenarioGridPanel;
        this.gridLayer = gridLayer;
        this.scenarioGrid = gridLayer.getScenarioGrid();
    }

    @Override
    public void execute() {
        GWT.log("EditBodyCellCommand execute");
    }
}
