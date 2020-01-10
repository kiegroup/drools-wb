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

package org.drools.workbench.screens.scenariosimulation.webapp.client.editor;

import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import elemental2.promise.Promise;
import org.kie.workbench.common.kogito.client.editor.MultiPageEditorContainerView;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.client.annotations.WorkbenchClientEditor;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.lifecycle.GetContent;
import org.uberfire.lifecycle.IsDirty;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.lifecycle.SetContent;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

import static org.drools.workbench.screens.scenariosimulation.webapp.client.editor.ScenarioSimulationEditorKogitoRuntimeScreen.IDENTIFIER;

@ApplicationScoped
@WorkbenchClientEditor(identifier = IDENTIFIER)
public class ScenarioSimulationEditorKogitoRuntimeScreen extends AbstractScenarioSimulationEditorKogitoScreen {

    public static final String IDENTIFIER = "ScenarioSimulationEditorKogitoRuntimeScreen";
    private static final PlaceRequest SCENARIO_SIMULATION_KOGITO_RUNTIME_SCREEN_DEFAULT_REQUEST = new DefaultPlaceRequest(IDENTIFIER);

    private PlaceManager placeManager;

    public ScenarioSimulationEditorKogitoRuntimeScreen() {
        //CDI proxy
    }

    @Inject
    public ScenarioSimulationEditorKogitoRuntimeScreen(final PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public PlaceRequest getPlaceRequest() {
        return SCENARIO_SIMULATION_KOGITO_RUNTIME_SCREEN_DEFAULT_REQUEST;
    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
        GWT.log(this.toString() + " onStartup " + place);
        scenarioSimulationEditorKogitoWrapper.onStartup(place);
    }

    @OnMayClose
    public boolean mayClose() {
        GWT.log(this.toString() + " mayClose");
        return scenarioSimulationEditorKogitoWrapper.mayClose();
    }

    @WorkbenchPartTitle
    public String getTitleText() {
        GWT.log(this.toString() + " getTitleText");
        return "Scenario Simulation Submarine Screen";
    }

    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        GWT.log(this.toString() + " getTitle");
        return scenarioSimulationEditorKogitoWrapper.getTitle();
    }

    @WorkbenchPartView
    public MultiPageEditorContainerView getWidget() {
        GWT.log(this.toString() + " getWidget");
        return scenarioSimulationEditorKogitoWrapper.getWidget();
    }

    @WorkbenchMenu
    public void setMenus(final Consumer<Menus> menusConsumer) {
        GWT.log(this.toString() + " setMenus " + menusConsumer);
        scenarioSimulationEditorKogitoWrapper.setMenus(menusConsumer);
    }

    @GetContent
    public Promise getContent() {
        return scenarioSimulationEditorKogitoWrapper.getContent();
    }

    @SetContent
    public void setContent(String value) {
        if (value == null || value.isEmpty()) {
            GWT.log("Create new RULE scesim ");
            newFile("");

//            final Path path = PathFactory.newPath("fileName", "savedFileName.scesim");
//            final String dmoSession = "default";
//            scenarioSimulationBuilder.populateScenarioSimulationModel(path, new ScenarioSimulationModel(), ScenarioSimulationModel.Type.RULE, dmoSession, content -> {
//                GWT.log(this.toString() + " gotoPath " + path);
//                scenarioSimulationEditorKogitoWrapper.gotoPath(path);
//                GWT.log(this.toString() + " setContent");
//                scenarioSimulationEditorKogitoWrapper.setContent(content);
//            });
        } else {
            final Path path = PathFactory.newPath("fileName", "existingFileName.scesim");
            scenarioSimulationEditorKogitoWrapper.gotoPath(path);
            scenarioSimulationEditorKogitoWrapper.setContent(value);
        }
    }

    @IsDirty
    public boolean isDirty() {
        return scenarioSimulationEditorKogitoWrapper.isDirty();
    }

}
