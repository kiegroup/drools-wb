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

import java.util.Collections;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.client.popup.FileUploadPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.ScenarioSimulationEditorKogitoWrapper;
import org.drools.workbench.screens.scenariosimulation.kogito.client.util.KogitoScenarioSimulationBuilder;
import org.drools.workbench.screens.scenariosimulation.webapp.client.popup.LoadScesimPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.webapp.client.popup.NewScesimPopupPresenter;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Popover;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.kie.workbench.common.kogito.webapp.base.client.workarounds.TestingVFSService;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.mvp.Command;

import static org.drools.workbench.screens.scenariosimulation.webapp.client.editor.ScenarioSimulationEditorKogitoTestingScreen.BASE_DMN_URI;
import static org.drools.workbench.screens.scenariosimulation.webapp.client.editor.ScenarioSimulationEditorKogitoTestingScreen.BASE_SCESIM_URI;

/**
 * <b>Widget</b> used to contain a <b>testing environment</b> specific menu, and the scenario simulation kogito <b>wrapper</b>
 */
@Dependent
public class ScenarioSimulationEditorKogitoTestingWidget extends VerticalPanel {

    @Inject
    private NewScesimPopupPresenter newScesimPopupPresenter;

    @Inject
    private LoadScesimPopupPresenter loadScesimPopupPresenter;

    @Inject
    private FileUploadPopupPresenter fileUploadPopupPresenter;

    @Inject
    private KogitoScenarioSimulationBuilder scenarioSimulationBuilder;

    @Inject
    private TestingVFSService testingVFSService;

    private ScenarioSimulationEditorKogitoWrapper scenarioSimulationEditorKogitoWrapper;


    public void init(ScenarioSimulationEditorKogitoWrapper scenarioSimulationEditorKogitoWrapper) {
        this.scenarioSimulationEditorKogitoWrapper = scenarioSimulationEditorKogitoWrapper;
        HorizontalPanel menuPanel = getMenuPanel();
        this.add(menuPanel);
        this.add(scenarioSimulationEditorKogitoWrapper.getWidget());
    }

    private HorizontalPanel getMenuPanel() {
        HorizontalPanel toReturn = new HorizontalPanel();
        toReturn.add(new Button("New", event -> newFile()));
        toReturn.add(new Button("Load", event -> loadFile()));
        toReturn.add(new Button("Import DMN", event -> importDMN()));
        return toReturn;
    }

    protected void newFile() {
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
            final Path path = PathFactory.newPath(fileName, BASE_SCESIM_URI + savedFileName);
            scenarioSimulationBuilder.populateScenarioSimulationModel(path, new ScenarioSimulationModel(), selectedType, value, content -> {
                saveFile(path, content);
                scenarioSimulationEditorKogitoWrapper.setContent(content);
                scenarioSimulationEditorKogitoWrapper.gotoPath(path);
            });

            newScesimPopupPresenter.hide();
        };
        newScesimPopupPresenter.show("Choose SCESIM type", createCommand);
    }

    protected void loadFile() {
        Command loadCommand = () -> {
            String fullUri = loadScesimPopupPresenter.getSelectedPath();
            String fileName = fullUri.substring(fullUri.lastIndexOf('/') + 1);
            final Path path = PathFactory.newPath(fileName, fullUri);
            testingVFSService.loadFile(path, content -> {
                scenarioSimulationEditorKogitoWrapper.setContent(content);
                scenarioSimulationEditorKogitoWrapper.gotoPath(path);
            }, getErrorCallback("Failed to load"));
            loadScesimPopupPresenter.hide();
        };
        loadScesimPopupPresenter.show("Choose SCESIM", loadCommand);
    }

    protected void importDMN() {
        Command okImportCommand = () -> {
            String fileName = fileUploadPopupPresenter.getFileName();
            if (fileName == null || fileName.isEmpty()) {
                showPopover("ERROR", "Missing file name");
                return;
            }
            fileName = fileName.replaceAll("\\s+", "_");
            String content = fileUploadPopupPresenter.getFileContents();
            final Path path = PathFactory.newPath(fileName, BASE_DMN_URI + fileName);
            saveFile(path, content);
        };
        fileUploadPopupPresenter.show(Collections.singletonList("dmn"),
                                      "Choose a DMN file",
                                      "Import",
                                      okImportCommand);
    }

    protected void saveFile(final Path path, final String content) {
        testingVFSService.saveFile(path, content,
                                   item -> GWT.log("Saved " + item),
                                   getErrorCallback("Failed to save"));
    }

    protected void showPopover(String title, String content) {
        new Popover(title, content).show();
    }

    protected ErrorCallback<String> getErrorCallback(String prependMessage) {
        return (message, throwable) -> {
            GWT.log(prependMessage + ": " + message, throwable);
            return false;
        };
    }
}
