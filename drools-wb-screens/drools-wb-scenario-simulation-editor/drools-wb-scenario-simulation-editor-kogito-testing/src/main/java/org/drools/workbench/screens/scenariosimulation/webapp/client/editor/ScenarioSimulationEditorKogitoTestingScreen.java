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

import com.google.gwt.user.client.ui.IsWidget;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioMenuItem;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.ScenarioSimulationEditorKogitoWrapper;
import org.drools.workbench.screens.scenariosimulation.kogito.client.popup.FileChooserPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.webapp.client.popup.LoadScesimPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.webapp.client.popup.NewScesimPopupPresenter;
import org.drools.workbench.screens.scenariosimulation.webapp.client.workarounds.ScesimFilesProvider;
import org.kie.workbench.common.kogito.webapp.base.client.editor.KogitoScreen;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerView;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

import static org.drools.workbench.screens.scenariosimulation.webapp.client.editor.ScenarioSimulationEditorKogitoTestingScreen.IDENTIFIER;

@ApplicationScoped
@WorkbenchScreen(identifier = IDENTIFIER)
public class ScenarioSimulationEditorKogitoTestingScreen implements KogitoScreen {

    public static final String IDENTIFIER = "ScenarioSimulationEditorKogitoTestingScreen";
    private static final PlaceRequest SCENARIO_SIMULATION_KOGITO_TESTING_SCREEN_DEFAULT_REQUEST = new DefaultPlaceRequest(IDENTIFIER);

    @Inject
    private ScenarioSimulationEditorKogitoWrapper scenarioSimulationEditorKogitoWrapper;

    @Inject
    private ScesimFilesProvider scesimFilesProvider;

    @Inject
    private NewScesimPopupPresenter newScesimPopupPresenter;

    @Inject
    private LoadScesimPopupPresenter loadScesimPopupPresenter;

    @Inject
    private FileChooserPopupPresenter fileChooserPopupPresenter;

    private PlaceManager placeManager;

    public ScenarioSimulationEditorKogitoTestingScreen() {
        //CDI proxy
    }

    @Inject
    public ScenarioSimulationEditorKogitoTestingScreen(final PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public PlaceRequest getPlaceRequest() {
        return SCENARIO_SIMULATION_KOGITO_TESTING_SCREEN_DEFAULT_REQUEST;
    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
        addTestingMenus(scenarioSimulationEditorKogitoWrapper.getFileMenuBuilder());
        scenarioSimulationEditorKogitoWrapper.onStartup(place);
    }

    @OnMayClose
    public boolean mayClose() {
        return scenarioSimulationEditorKogitoWrapper.mayClose();
    }

    @WorkbenchPartTitle
    public String getTitleText() {
        return "Scenario Simulation Submarine Screen";
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

    protected void newFile() {
        Command createCommand = () -> {
            final ScenarioSimulationModel.Type selectedType = newScesimPopupPresenter.getSelectedType();
            if (selectedType != null) {
                String fileName;
                switch (selectedType) {
                    case DMN:
                        fileName = newScesimPopupPresenter.getSelectedPath();
                        break;
                    case RULE:
                    default:
                        fileName = "newScesimRule";
                }
                scenarioSimulationEditorKogitoWrapper.setContent(scesimFilesProvider.getScesimFile(fileName));
                newScesimPopupPresenter.hide();
            }
        };
        newScesimPopupPresenter.show("Choose SCESIM type", createCommand);
    }

    protected void loadFile() {
        Command loadCommand = () -> {
            String fileName = loadScesimPopupPresenter.getSelectedPath();
            scenarioSimulationEditorKogitoWrapper.setContent(scesimFilesProvider.getScesimFile(fileName));
            loadScesimPopupPresenter.hide();
        };
        loadScesimPopupPresenter.show("Choose SCESIM", loadCommand);
    }

    protected void addTestingMenus(FileMenuBuilder fileMenuBuilder) {
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("New", this::newFile));
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("Load", this::loadFile));
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("Save", () -> scenarioSimulationEditorKogitoWrapper.getContent()));
    }

}
