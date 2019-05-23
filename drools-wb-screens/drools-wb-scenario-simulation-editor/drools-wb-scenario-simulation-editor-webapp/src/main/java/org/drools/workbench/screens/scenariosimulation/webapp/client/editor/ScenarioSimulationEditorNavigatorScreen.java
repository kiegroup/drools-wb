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
import com.google.gwt.user.client.ui.Widget;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;

@Dependent
@WorkbenchScreen(identifier = ScenarioSimulationEditorNavigatorScreen.SCREEN_ID)
public class ScenarioSimulationEditorNavigatorScreen {

    public static final String SCREEN_ID = "ScenarioSimulationEditorNavigatorScreen";

    public static final PlaceRequest SCENARIO_SIMULATION_NAVIGATOR_DEFAULT_REQUEST = new DefaultPlaceRequest(ScenarioSimulationEditorNavigatorScreen.SCREEN_ID);

    private ScenarioSimulationEditorSubmarineScreen stateHolder;

//    @Inject
//    private ScenarioSimulationEditorSubmarineWrapper scenarioSimulationEditorSubmarineWrapper;
//
//    @Inject
//    private ScesimFilesProvider scesimFilesProvider;

//    private Menus menu = null;

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
//        /*stateHolder.*/newFile();
    }

    @OnStartup
    @SuppressWarnings("unused")
    public void onStartup(final PlaceRequest placeRequest) {
        GWT.log(this.toString() + " onStartup " + placeRequest.asString());
        clear();
    }

    @OnClose
    public void onClose() {
        clear();
    }

    @WorkbenchPartTitle
    public String getTitle() {
        GWT.log(this.toString() + " getTitle");
        return "Scenario Simulation Title";
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        GWT.log(this.toString() + " getWidget");
        final Widget toReturn = stateHolder.asWidget();
        stateHolder.goToScreen();
        return toReturn;
    }


    private void clear() {
        GWT.log(this.toString() + ": clear");
    }

}