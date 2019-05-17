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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.drools.workbench.screens.scenariosimulation.submarine.client.editor.ScenarioSimulationEditorSubmarineWrapper;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.mvp.impl.DefaultPlaceRequest;

@ApplicationScoped
//@WorkbenchScreen(identifier = ScenarioSimulationEditorPresenter.IDENTIFIER)
public class ScenarioSimulationEditorSubmarineScreen implements IsWidget {

    @Inject
    private ScenarioSimulationEditorSubmarineWrapper scenarioSimulationEditorSubmarineWrapper;

    private PlaceManager placeManager;

    public ScenarioSimulationEditorSubmarineScreen() {
        //CDI proxy
    }

    @Inject
    public ScenarioSimulationEditorSubmarineScreen(final PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public Widget asWidget() {
        return scenarioSimulationEditorSubmarineWrapper.asWidget().asWidget();
    }

    public void newFile() {
        placeManager.registerOnOpenCallback(new DefaultPlaceRequest(ScenarioSimulationEditorPresenter.IDENTIFIER) {
                                            },
                                            () -> {
                                                scenarioSimulationEditorSubmarineWrapper.setContent("");
                                                placeManager.unregisterOnOpenCallbacks(ScenarioSimulationEditorNavigatorScreen.SCENARIO_SIMULATION);
                                            });

        placeManager.goTo(ScenarioSimulationEditorNavigatorScreen.SCENARIO_SIMULATION);
    }

//    public void openFile(final Path path) {
//        placeManager.registerOnOpenCallback(ScenarioSimulationEditorNavigatorScreen.DIAGRAM_EDITOR,
//                                            () -> {
//                                                clientDiagramService.loadAsXml(path,
//                                                                               new ServiceCallback<String>() {
//                                                                                   @Override
//                                                                                   public void onSuccess(final String xml) {
//                                                                                       scenarioSimulationEditorSubmarineWrapper.setContent(xml);
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
//        final Path path = scenarioSimulationEditorSubmarineWrapper.getCanvasHandler().getDiagram().getMetadata().getPath();
//        scenarioSimulationEditorSubmarineWrapper.getContent().then(xml -> {
//            clientDiagramService.saveAsXml(path,
//                                           (String) xml,
//                                           callback);
//            return null;
//        });
//    }


}