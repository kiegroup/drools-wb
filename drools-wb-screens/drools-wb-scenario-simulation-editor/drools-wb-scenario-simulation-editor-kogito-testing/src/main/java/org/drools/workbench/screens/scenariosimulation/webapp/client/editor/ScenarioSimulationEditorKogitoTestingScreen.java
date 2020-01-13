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
import java.util.function.Consumer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioMenuItem;
import org.drools.workbench.screens.scenariosimulation.client.popup.FileUploadPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.webapp.client.popup.LoadScesimPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.webapp.client.workarounds.TestingVFSService;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.kie.workbench.common.kogito.client.editor.MultiPageEditorContainerView;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.promise.Promises;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

import static org.drools.workbench.screens.scenariosimulation.webapp.client.editor.ScenarioSimulationEditorKogitoTestingScreen.IDENTIFIER;

@ApplicationScoped
@WorkbenchScreen(identifier = IDENTIFIER)
public class ScenarioSimulationEditorKogitoTestingScreen extends AbstractScenarioSimulationEditorKogitoScreen {

    public static final String IDENTIFIER = "ScenarioSimulationEditorKogitoTestingScreen";
    private static final String BASE_URI = "git://master@system/system/";
    public static final String BASE_DMN_URI = BASE_URI + "stunner/diagrams/";
    public static final String BASE_SCESIM_URI = BASE_URI + "scesim/";
    public static final Path DMN_PATH = PathFactory.newPath("DMN", BASE_DMN_URI);
    public static final Path SCESIM_PATH = PathFactory.newPath("SCESIM", BASE_SCESIM_URI);
    private static final PlaceRequest SCENARIO_SIMULATION_KOGITO_TESTING_SCREEN_DEFAULT_REQUEST = new DefaultPlaceRequest(IDENTIFIER);

    @Inject
    private LoadScesimPopupPresenter loadScesimPopupPresenter;

    @Inject
    private FileUploadPopupPresenter fileUploadPopupPresenter;

    @Inject
    private TestingVFSService testingVFSService;

    @Inject
    private Promises promises;

    @Override
    public PlaceRequest getPlaceRequest() {
        return SCENARIO_SIMULATION_KOGITO_TESTING_SCREEN_DEFAULT_REQUEST;
    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
        testingVFSService.getItemsByPath(SCESIM_PATH, response -> {
            // do nothing
        }, (message, throwable) -> {
            if (throwable instanceof org.uberfire.java.nio.file.NotDirectoryException) {
                testingVFSService.createDirectory(SCESIM_PATH);
                return true;
            } else {
                GWT.log(message.toString(), throwable);
                return false;
            }
        });
        testingVFSService.getItemsByPath(DMN_PATH, response -> {
            // do nothing
        }, (message, throwable) -> {
            if (throwable instanceof org.uberfire.java.nio.file.NotDirectoryException) {
                testingVFSService.createDirectory(DMN_PATH);
                return true;
            } else {
                GWT.log(message.toString(), throwable);
                return false;
            }
        });
        addTestingMenus(scenarioSimulationEditorKogitoWrapper.getFileMenuBuilder());
        scenarioSimulationEditorKogitoWrapper.onStartup(place);
    }

    @OnMayClose
    public boolean mayClose() {
        return scenarioSimulationEditorKogitoWrapper.mayClose();
    }

    @WorkbenchPartTitle
    public String getTitleText() {
        return "Scenario Simulation Kogito Screen";
    }

    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        return scenarioSimulationEditorKogitoWrapper.getTitle();
    }

    @WorkbenchPartView
    public MultiPageEditorContainerView getWidget() {
        return scenarioSimulationEditorKogitoWrapper.getWidget();
    }

    @WorkbenchMenu
    public void setMenus(final Consumer<Menus> menusConsumer) {
        scenarioSimulationEditorKogitoWrapper.setMenus(menusConsumer);
    }

    protected void loadFile() {
        Command loadCommand = () -> {
            String fullUri = loadScesimPopupPresenter.getSelectedPath();
            String fileName = fullUri.substring(fullUri.lastIndexOf('/') + 1);
            final Path path = PathFactory.newPath(fileName, fullUri);
            testingVFSService.loadFile(path, content -> {
                scenarioSimulationEditorKogitoWrapper.gotoPath(path);
                scenarioSimulationEditorKogitoWrapper.setContent(content);
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

    @Override
    protected void saveFile(final Path path, final String content) {
        testingVFSService.saveFile(path, content,
                                   item -> GWT.log("Saved " + item),
                                   getErrorCallback("Failed to save"));
    }

    protected void addTestingMenus(FileMenuBuilder fileMenuBuilder) {
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("New", () -> newFile(BASE_SCESIM_URI)));
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("Load", this::loadFile));
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("Save", () -> scenarioSimulationEditorKogitoWrapper.getContent().then(xml -> {
            saveFile(scenarioSimulationEditorKogitoWrapper.getCurrentPath(), xml);
            return promises.resolve();
        })));
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("Import DMN", this::importDMN));
    }

    protected ErrorCallback<String> getErrorCallback(String prependMessage) {
        return (message, throwable) -> {
            GWT.log(prependMessage + ": " + message, throwable);
            return false;
        };
    }
}
