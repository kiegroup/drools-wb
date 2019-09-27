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
package org.drools.workbench.screens.scenariosimulation.client.widgets;

import java.util.List;
import java.util.stream.IntStream;

import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.client.menu.ScenarioContextMenuRegistry;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.renderers.ScenarioGridRenderer;

public class ScenarioMainGrid extends ScenarioGrid<Scenario> {

    public ScenarioMainGrid(ScenarioGridModel model,
                            ScenarioGridLayer scenarioGridLayer,
                            ScenarioGridRenderer renderer,
                            ScenarioContextMenuRegistry scenarioContextMenuRegistry) {
        super(model, scenarioGridLayer, renderer, scenarioContextMenuRegistry);
    }

    @Override
    public void setContent(Simulation simulation) {
        ((ScenarioGridModel) model).clear();
        ((ScenarioGridModel) model).bindContent(simulation);
        setHeaderColumns(simulation);
        appendRows(simulation.getUnmodifiableScenarios());
        ((ScenarioGridModel) model).loadFactMappingsWidth();
        ((ScenarioGridModel) model).forceRefreshWidth();
    }

    @Override
    protected void appendRows(List<Scenario> scenarios) {
        IntStream.range(0, scenarios.size()).forEach(rowIndex -> appendRow(rowIndex, scenarios.get(rowIndex)));
    }

    @Override
    protected void appendRow(int index, Scenario scenario) {
        ((ScenarioGridModel) model).insertRowGridOnly(index, new ScenarioGridRow(), scenario);
    }
}
