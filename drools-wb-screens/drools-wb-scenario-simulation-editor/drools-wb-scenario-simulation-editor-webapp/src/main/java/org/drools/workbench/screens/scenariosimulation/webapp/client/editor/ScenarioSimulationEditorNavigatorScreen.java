/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.webapp.client.editor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.Menus;

@Dependent
@WorkbenchScreen(identifier = ScenarioSimulationEditorNavigatorScreen.SCREEN_ID)
public class ScenarioSimulationEditorNavigatorScreen {

    public static final String SCREEN_ID = "ScenarioSimulationEditorNavigatorScreen";

    public static final PlaceRequest SCENARIO_SIMULATION = new DefaultPlaceRequest(ScenarioSimulationEditorNavigatorScreen.SCREEN_ID);

    private ScenarioSimulationEditorSubmarineScreen stateHolder;

    private Menus menu = null;

    public ScenarioSimulationEditorNavigatorScreen() {
        //CDI proxy
    }

    @Inject
    public ScenarioSimulationEditorNavigatorScreen(final ScenarioSimulationEditorSubmarineScreen stateHolder) {
        this.stateHolder = stateHolder;
    }

    @PostConstruct
    public void init() {
        GWT.log(this.toString() + ": init");
    }

    @OnStartup
    @SuppressWarnings("unused")
    public void onStartup(final PlaceRequest placeRequest) {
        this.menu = makeMenuBar();
        clear();
    }

    private Menus makeMenuBar() {
        final MenuFactory.TopLevelMenusBuilder<MenuFactory.MenuBuilder> m =
                MenuFactory
                        .newTopLevelMenu("Create")
                        .respondsWith(() -> stateHolder.newFile())
                        .endMenu();
        return m.build();
    }

    private void clear() {
        GWT.log(this.toString() + ": clear");
    }

    @OnClose
    public void onClose() {
        clear();
    }

    @WorkbenchMenu
    public Menus getMenu() {
        return menu;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Scenario Simulation Title";
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        return stateHolder.asWidget();
    }

}