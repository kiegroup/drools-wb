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

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.AppendColumnEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.AppendRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;

/**
 * This class is meant to be a centralized listener for events fired up by UI, responding to them with specific <code>Command</code>s.
 * <p>
 * It follows the GWT standard Event/Handler mechanism
 */
@Dependent
public class CommandExecutor implements AppendRowEventHandler,
                                        AppendColumnEventHandler {

    private ScenarioGridModel model;
    private ScenarioGridPanel scenarioGridPanel;
    private ScenarioGridLayer scenarioGridLayer;

    private EventBus eventBus;

    private HandlerRegistration appendRowEventHandlerRegistration;
    private HandlerRegistration appendColumnEventHandlerRegistration;

    public CommandExecutor() {
        // CDI
    }

    @Inject
    public CommandExecutor(EventBus eventBus) {
        GWT.log("CommandExecutor with event");
        this.eventBus = eventBus;
        GWT.log("CommandExecutor " + toString() + " With eventBus " + eventBus.toString());
    }

    @PostConstruct
    public void registerHandlers() {
        if (appendRowEventHandlerRegistration == null) {
            appendRowEventHandlerRegistration = eventBus.addHandler(AppendRowEvent.TYPE, this);
        }
        if (appendColumnEventHandlerRegistration == null) {
            appendColumnEventHandlerRegistration = eventBus.addHandler(AppendColumnEvent.TYPE, this);
        }
    }

    @PreDestroy
    public void unregisterHandlers() {
        if (appendRowEventHandlerRegistration != null) {
            appendRowEventHandlerRegistration.removeHandler();
        }
        if (appendColumnEventHandlerRegistration != null) {
            appendColumnEventHandlerRegistration.removeHandler();
        }
    }

    public void setScenarioGridPanel(ScenarioGridPanel scenarioGridPanel) {
        this.scenarioGridPanel = scenarioGridPanel;
    }

    public void setScenarioGridLayer(ScenarioGridLayer scenarioGridLayer) {
        this.scenarioGridLayer = scenarioGridLayer;
        this.model = scenarioGridLayer.getScenarioGrid().getModel();
    }

    @Override
    public void onEvent(AppendRowEvent event) {
        AppendRowCommand command = new AppendRowCommand(model);
        command.execute();
    }

    @Override
    public void onEvent(AppendColumnEvent event) {
        AppendColumnCommand command = new AppendColumnCommand(model, event.getColumnId(), event.getColumnTitle(), scenarioGridPanel, scenarioGridLayer);
        command.execute();
    }
}
