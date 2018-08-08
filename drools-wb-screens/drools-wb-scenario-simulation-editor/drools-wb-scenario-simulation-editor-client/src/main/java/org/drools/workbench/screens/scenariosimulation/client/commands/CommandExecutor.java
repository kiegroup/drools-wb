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

import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridBodyDoubleClickEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridBodyRightClickEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridHeaderDoubleClickEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridHeaderRightClickEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioGridBodyDoubleClickHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioGridBodyRightClickHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioGridHeaderDoubleClickHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioGridHeaderRightClickHandler;

/**
 * This class is meant to be a centralized listener for events fired up by UI, responding to them with specific <code>Command</code>s.
 *
 * It follows the GWT standard Event/Handler mechanism
 */
public class CommandExecutor implements ScenarioGridHeaderRightClickHandler,
                                        ScenarioGridBodyRightClickHandler,
                                        ScenarioGridHeaderDoubleClickHandler,
                                        ScenarioGridBodyDoubleClickHandler {

//    private ScenarioGridPanel scenarioGridPanel;
//    private ScenarioGridLayer scenarioGridLayer;
//    private ScenarioGrid scenarioGrid;

    public CommandExecutor() {
        // CDI
    }

//    @Inject
//    public CommandExecutor(ScenarioGridPanel scenarioGridPanel, ScenarioGridLayer scenarioGridLayer) {
//        this.scenarioGridPanel = scenarioGridPanel;
//        this.scenarioGridLayer = scenarioGridLayer;
//        this.scenarioGrid = scenarioGridPanel.getScenarioGrid();
//    }

    @Override
    public void onRightClick(ScenarioGridHeaderRightClickEvent event) {
        event.getScenarioGrid().select();
        new ShowHeaderMenuCommand(event.getScenarioGrid(), event.getLeft(), event.getTop()).execute();
    }

    @Override
    public void onRightClick(ScenarioGridBodyRightClickEvent event) {
        new AddNewColumnCommand(event.getScenarioGridPanel(), event.getScenarioGridLayer()).execute();
    }

    @Override
    public void onDoubleClick(ScenarioGridBodyDoubleClickEvent event) {
        new EditBodyCellCommand(event.getScenarioGridPanel(), event.getScenarioGridLayer()).execute();
    }

    @Override
    public void onDoubleClick(ScenarioGridHeaderDoubleClickEvent event) {
        new EditHeaderCellCommand(event.getScenarioGridPanel(), event.getScenarioGridLayer()).execute();
    }
}
