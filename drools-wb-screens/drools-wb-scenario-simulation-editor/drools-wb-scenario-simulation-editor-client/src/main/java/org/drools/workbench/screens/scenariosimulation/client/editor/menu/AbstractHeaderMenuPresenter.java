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
package org.drools.workbench.screens.scenariosimulation.client.editor.menu;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.screens.scenariosimulation.client.commands.CommandExecutor;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;

/**
 * This is the first <i>ScenaraioSimulation</i> specific abstract class - i.e. it is bound to a specific use case. Not every implementation
 * would need this. Menu initialization may be done in other different ways. It is provided to avoid code duplication in concrete implementations
 */
public abstract class AbstractHeaderMenuPresenter extends BaseMenu implements HeaderMenuPresenter {

    protected ScenarioSimulationEditorConstants constants = ScenarioSimulationEditorConstants.INSTANCE;

    protected ScenarioGridModel model;
    protected ScenarioGridPanel scenarioGridPanel;
    protected ScenarioGridLayer scenarioGridLayer;

    protected AppendRowEvent appendRowEvent = new AppendRowEvent();

    @Inject
    protected CommandExecutor commandExecutor;

    @Inject
    protected EventBus eventBus;

    @PostConstruct
    public void registerHandler() {
        eventBus.addHandler(AppendRowEvent.TYPE, commandExecutor);
        eventBus.addHandler(AppendColumnEvent.TYPE, commandExecutor);
    }
}
