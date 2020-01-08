/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

import javax.inject.Inject;

import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.ScenarioSimulationEditorKogitoWrapper;
import org.drools.workbench.screens.scenariosimulation.kogito.client.util.KogitoScenarioSimulationBuilder;
import org.drools.workbench.screens.scenariosimulation.webapp.client.popup.NewScesimPopupPresenter;
import org.gwtbootstrap3.client.ui.Popover;
import org.kie.workbench.common.kogito.webapp.base.client.editor.KogitoScreen;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.mvp.Command;

/**
 * Abstract class to be extended by concrete <b>ScenarioSimulationEditorKogitoScreen</b>s
 */
public abstract class AbstractScenarioSimulationEditorKogitoScreen implements KogitoScreen {

    @Inject
    protected ScenarioSimulationEditorKogitoWrapper scenarioSimulationEditorKogitoWrapper;

    @Inject
    private KogitoScenarioSimulationBuilder scenarioSimulationBuilder;

    @Inject
    private NewScesimPopupPresenter newScesimPopupPresenter;


    /**
     *
     * @param baseUri the folder/position into which the file will be saved
     */
    protected void newFile(String baseUri) {
        Command createCommand = () -> {
            final String fileName = newScesimPopupPresenter.getFileName();
            if (fileName == null || fileName.isEmpty()) {
                showPopover("ERROR", "Missing file name");
                return;
            }
            final ScenarioSimulationModel.Type selectedType = newScesimPopupPresenter.getSelectedType();
            String value = "";
            if (selectedType == null) {
                showPopover("ERROR", "Missing selected type");
                return;
            }
            if (ScenarioSimulationModel.Type.DMN.equals(selectedType)) {
                value = newScesimPopupPresenter.getSelectedPath();
                if (value == null || value.isEmpty()) {
                    showPopover("ERROR", "Missing dmn path");
                    return;
                }
            }
            String savedFileName = fileName.trim() + ".scesim";
            final Path path = PathFactory.newPath(fileName, baseUri + savedFileName);
            scenarioSimulationBuilder.populateScenarioSimulationModel(path, new ScenarioSimulationModel(), selectedType, value, content -> {
                saveFile(path, content);
                scenarioSimulationEditorKogitoWrapper.gotoPath(path);
                scenarioSimulationEditorKogitoWrapper.setContent(content);
            });
            newScesimPopupPresenter.hide();
        };
        newScesimPopupPresenter.show("Choose SCESIM type", createCommand);
    }

    protected void showPopover(String title, String content) {
        new Popover(title, content).show();
    }

    protected void saveFile(final Path path, final String content) {
        // TO BE OVERRIDDEN
    }
}
