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
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.ScenarioSimulationEditorKogitoWrapper;
import org.drools.workbench.screens.scenariosimulation.webapp.client.workarounds.ScesimFilesProvider;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerView;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

import static org.drools.workbench.screens.scenariosimulation.webapp.client.editor.ScenarioSimulationEditorKogitoScreen.IDENTIFIER;

@ApplicationScoped
@WorkbenchScreen(identifier = IDENTIFIER)
public class ScenarioSimulationEditorKogitoScreen {

    public static final String IDENTIFIER = "ScenarioSimulationEditorKogitoScreen";
    public static final PlaceRequest SCENARIO_SIMULATION_SUBMARINE_SCREEN_DEFAULT_REQUEST = new DefaultPlaceRequest(IDENTIFIER);

    @Inject
    private ScenarioSimulationEditorKogitoWrapper scenarioSimulationEditorKogitoWrapper;

    @Inject
    private ScesimFilesProvider scesimFilesProvider;

    private PlaceManager placeManager;

    public ScenarioSimulationEditorKogitoScreen() {
        //CDI proxy
    }

    @Inject
    public ScenarioSimulationEditorKogitoScreen(final PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
        GWT.log(this.toString() + " onStartup " + place);
        scenarioSimulationEditorKogitoWrapper.onStartup(place);
        scenarioSimulationEditorKogitoWrapper.setContent(scesimFilesProvider.getNewScesimRule());
    }

    @OnMayClose
    public boolean mayClose() {
        GWT.log(this.toString() + " mayClose");
        int x = 34;
        switch (x) {
            case 2:
                break;
            default:
                break;
        }
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

//    @Override
//    public Widget asWidget() {
//        return scenarioSimulationEditorKogitoWrapper.asWidget().asWidget();
//    }

//    public void newFile() {
//        GWT.log(this.toString() + " newFile");
//        scenarioSimulationEditorKogitoWrapper.setContent(scesimFilesProvider.getScesimContent());
//
////        placeManager.registerOnOpenCallback(  new DefaultPlaceRequest(ScenarioSimulationEditorPresenter.IDENTIFIER) {
////                                            },
////                                            () -> {
////                                                scenarioSimulationEditorKogitoWrapper.setContent(scesimFilesProvider.getScesimContent());
////                                                placeManager.unregisterOnOpenCallbacks(ScenarioSimulationEditorNavigatorScreen.SCENARIO_SIMULATION_NAVIGATOR_DEFAULT_REQUEST);
////                                            });
//
//    }

//    public void goToScreen() {
//        GWT.log(this.toString() + " goToScreen");
//        placeManager.registerOnOpenCallback(new DefaultPlaceRequest(ScenarioSimulationEditorKogitoWrapper.IDENTIFIER) {
//                                            },
//                                            () -> {
//                                                scenarioSimulationEditorKogitoWrapper.setContent(scesimFilesProvider.getPopulatedScesimRule());
//                                                placeManager.unregisterOnOpenCallbacks(ScenarioSimulationEditorNavigatorScreen.SCENARIO_SIMULATION_NAVIGATOR_DEFAULT_REQUEST);
//                                            });
//        placeManager.goTo(ScenarioSimulationEditorKogitoWrapper.IDENTIFIER);
//    }

//    public void openFile(final Path path) {
//        placeManager.registerOnOpenCallback(ScenarioSimulationEditorNavigatorScreen.DIAGRAM_EDITOR,
//                                            () -> {
//                                                clientDiagramService.loadAsXml(path,
//                                                                               new ServiceCallback<String>() {
//                                                                                   @Override
//                                                                                   public void onSuccess(final String xml) {
//                                                                                       scenarioSimulationEditorKogitoWrapper.setContent(xml);
//                                                                                       placeManager.unregisterOnOpenCallbacks(ScenarioSimulationEditorNavigatorScreen.DIAGRAM_EDITOR);
//                                                                                   }
//
//                                                                                   @Override
//                                                                                   public void onError(final ClientRuntimeError error) {
//                                                                                       placeManager.unregisterOnOpenCallbacks(ScenarioSimulationEditorNavigatorScreen.DIAGRAM_EDITOR);
//                                                                                   }
//                                                                               });
//                                            });
//
//        placeManager.goTo(ScenarioSimulationEditorNavigatorScreen.DIAGRAM_EDITOR);
//    }
//
//    @SuppressWarnings("unchecked")
//    public void saveFile(final ServiceCallback<String> callback) {
//        final Path path = scenarioSimulationEditorKogitoWrapper.getCanvasHandler().getDiagram().getMetadata().getPath();
//        scenarioSimulationEditorKogitoWrapper.getContent().then(xml -> {
//            clientDiagramService.saveAsXml(path,
//                                           (String) xml,
//                                           callback);
//            return null;
//        });
//    }
}
