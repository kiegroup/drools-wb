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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertColumnLeftEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertColumnRightEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridReloadEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.AppendColumnEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.AppendRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.InsertColumnLeftEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.InsertColumnRightEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.PrependColumnEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.PrependRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioGridReloadEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;

/**
 * This class is meant to be a centralized listener for events fired up by UI, responding to them with specific <code>Command</code>s.
 * <p>
 * It follows the GWT standard Event/Handler mechanism
 */
@Dependent
public class CommandExecutor implements AppendColumnEventHandler,
                                        AppendRowEventHandler,
                                        InsertColumnLeftEventHandler,
                                        InsertColumnRightEventHandler,
                                        PrependColumnEventHandler,
                                        PrependRowEventHandler,
                                        ScenarioGridReloadEventHandler {

    private ScenarioGridModel model;
    private ScenarioGridPanel scenarioGridPanel;
    private ScenarioGridLayer scenarioGridLayer;

    private EventBus eventBus;

    private List<HandlerRegistration> handlerRegistrationList = new ArrayList<>();

    public CommandExecutor() {
        // CDI
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
        GWT.log("CommandExecutor " + this.toString() + " setEventBus " + eventBus.toString());
    }

    /**
     * This method set <code>ScenarioGridPanel</code>, <code>ScenarioGridLayer</code> and <code>ScenarioGridModel</code>
     * from the give <code>ScenarioGridPanel</code>
     * @param scenarioGridPanel
     */
    public void setScenarioGridPanel(ScenarioGridPanel scenarioGridPanel) {
        this.scenarioGridPanel = scenarioGridPanel;
        this.scenarioGridLayer = scenarioGridPanel.getScenarioGridLayer();
        this.model = scenarioGridLayer.getScenarioGrid().getModel();
        GWT.log("CommandExecutor " + this.toString() + " scenarioGridPanel " + scenarioGridPanel.hashCode() + " scenarioGridLayer " + scenarioGridLayer.hashCode() + " model " + model.toString());
    }

    @PreDestroy
    public void unregisterHandlers() {
        handlerRegistrationList.forEach(HandlerRegistration::removeHandler);
    }

    @Override
    public void onEvent(AppendColumnEvent event) {
        AppendColumnCommand command = new AppendColumnCommand(model, String.valueOf(new Date().getTime()), event.getColumnGroup(), scenarioGridPanel, scenarioGridLayer);
        command.execute();
        scenarioGridPanel.onResize();
    }

    @Override
    public void onEvent(AppendRowEvent event) {
        AppendRowCommand command = new AppendRowCommand(model);
        command.execute();
        scenarioGridPanel.onResize();
    }

    @Override
    public void onEvent(InsertColumnLeftEvent event) {
        InsertColumnLeftCommand command = new InsertColumnLeftCommand(model, String.valueOf(new Date().getTime()), scenarioGridPanel, scenarioGridLayer);
        command.execute();
        scenarioGridPanel.onResize();
    }

    @Override
    public void onEvent(InsertColumnRightEvent event) {
        InsertColumnRightCommand command = new InsertColumnRightCommand(model, String.valueOf(new Date().getTime()), scenarioGridPanel, scenarioGridLayer);
        command.execute();
        scenarioGridPanel.onResize();
    }

    @Override
    public void onEvent(PrependColumnEvent event) {
        PrependColumnCommand command = new PrependColumnCommand(model, String.valueOf(new Date().getTime()), event.getColumnGroup(), scenarioGridPanel, scenarioGridLayer);
        command.execute();
        scenarioGridPanel.onResize();
    }

    @Override
    public void onEvent(PrependRowEvent event) {
        PrependRowCommand command = new PrependRowCommand(model);
        command.execute();
        scenarioGridPanel.onResize();
    }

    @Override
    public void handle(ScenarioGridReloadEvent event) {
        scenarioGridPanel.onResize();
    }

    private void registerHandlers() {
        // LET'S DO THE RISKY THING: NOT CHECKING FOR ACTUAL REGISTRATIONS
        handlerRegistrationList.add(eventBus.addHandler(AppendColumnEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(AppendRowEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(PrependColumnEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(PrependRowEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(ScenarioGridReloadEvent.TYPE, this));
    }
}
