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

package org.drools.workbench.screens.scenariosimulation.submarine.client.editor;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import elemental2.promise.Promise;
import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.ScenarioWithIndex;
import org.drools.scenariosimulation.api.model.SimulationDescriptor;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioMenuItem;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorWrapper;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.DataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioSimulationDocksHandler;
import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationSubmarineService;
import org.drools.workbench.screens.scenariosimulation.submarine.client.editor.strategies.SubmarineDMNDataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.submarine.client.editor.strategies.SubmarineDMODataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.submarine.client.fakes.ScesimFilesProvider;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerPresenter;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerView;
import org.kie.workbench.common.widgets.client.docks.AuthoringEditorDock;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.PlaceStatus;
import org.uberfire.ext.widgets.common.client.callbacks.HasBusyIndicatorDefaultErrorCallback;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

import static org.drools.workbench.screens.scenariosimulation.submarine.client.editor.ScenarioSimulationEditorSubmarineWrapper.IDENTIFIER;

@Dependent
@WorkbenchScreen(identifier = IDENTIFIER)
/**
 * Wrapper to be used inside Submarine
 */
public class ScenarioSimulationEditorSubmarineWrapper extends MultiPageEditorContainerPresenter<ScenarioSimulationModel> implements ScenarioSimulationEditorWrapper {

    public static final String IDENTIFIER = "ScenarioSimulationEditorSubmarineWrapper";
    public static final PlaceRequest SCENARIO_SIMULATION_WRAPPER_DEFAULT_REQUEST = new DefaultPlaceRequest(IDENTIFIER);

    protected ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter;
    protected Caller<ScenarioSimulationSubmarineService> service;
    private FileMenuBuilder fileMenuBuilder;
    private ScesimFilesProvider scesimFilesProvider;
    private ScenarioSimulationDocksHandler scenarioSimulationDocksHandler;
    private AuthoringEditorDock authoringWorkbenchDocks;



    public ScenarioSimulationEditorSubmarineWrapper() {
        //Zero-parameter constructor for CDI proxies
    }

    @Inject
    public ScenarioSimulationEditorSubmarineWrapper(final Caller<ScenarioSimulationSubmarineService> service,
                                                    final ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter,
                                                    final FileMenuBuilder fileMenuBuilder,
                                                    final PlaceManager placeManager,
                                                    final MultiPageEditorContainerView multiPageEditorContainerView,
                                                    final ScesimFilesProvider scesimFilesProvider,
                                                    final ScenarioSimulationDocksHandler scenarioSimulationDocksHandler,
                                                    final AuthoringEditorDock authoringWorkbenchDocks) {
        super(scenarioSimulationEditorPresenter.getView(), fileMenuBuilder, placeManager, multiPageEditorContainerView);
        this.service = service;
        this.scenarioSimulationEditorPresenter = scenarioSimulationEditorPresenter;
        this.fileMenuBuilder = fileMenuBuilder;
        this.scesimFilesProvider = scesimFilesProvider;
        this.scenarioSimulationDocksHandler = scenarioSimulationDocksHandler;
        this.authoringWorkbenchDocks = authoringWorkbenchDocks;
    }

    @Override
    protected void buildMenuBar() {
        GWT.log(this.toString() + " buildMenuBar");
        setMenus(fileMenuBuilder.build());
        getMenus().getItemsMap().values().forEach(menuItem -> menuItem.setEnabled(true));
    }

    @Override
    public void setContent(String value) {
        GWT.log(this.toString() + " setContent ");
        // TODO WORKAROUND
        getWidget().init(this);
        service.call((RemoteCallback<ScenarioSimulationModel>) this::getModelSuccessCallbackMethod).unmarshal(value);
    }

    @Override
    public Promise getContent() {
        GWT.log(this.toString() + " getContent");
        return null;
    }

    @Override
    public void resetContentHash() {

    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
        GWT.log(this.toString() + " onStartup");
        super.init(place);
        scenarioSimulationEditorPresenter.init(this, (ObservablePath) place.getPath());
        authoringWorkbenchDocks.setup("AuthoringPerspective", place);
    }

    @OnMayClose
    public boolean mayClose() {
        return !scenarioSimulationEditorPresenter.isDirty();
    }

    @WorkbenchPartTitle
    public String getTitleText() {
        GWT.log(this.toString() + " getTitleText");
        return "Scenario Simulation wrapper";
    }

    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        GWT.log(this.toString() + " getTitle");
        return super.getTitle();
    }

    @WorkbenchPartView
    public MultiPageEditorContainerView getWidget() {
        GWT.log(this.toString() + " getWidget");
        return super.getWidget();
    }

    @WorkbenchMenu
    public void getMenus(final Consumer<Menus> menusConsumer) {
        GWT.log(this.toString() + " getMenus");
        menusConsumer.accept(getMenus());
    }

    @Override
    public void onRunScenario(RemoteCallback<Map<Integer, Scenario>> refreshModelCallback, HasBusyIndicatorDefaultErrorCallback hasBusyIndicatorDefaultErrorCallback, SimulationDescriptor simulationDescriptor, List<ScenarioWithIndex> toRun) {
        throw new UnsupportedOperationException("Not available in Submarine");
    }

    @Override
    public void wrappedSave(String commitMessage) {
//        save(commitMessage);
    }

    @Override
    public Integer getOriginalHash() {
        return super.getOriginalContentHash();
    }

    /**
     * If you want to customize the menu override this method.
     */
    @Override
    protected void makeMenuBar() {
        GWT.log(this.toString() + " makeMenuBar");
        scenarioSimulationEditorPresenter.makeMenuBar(fileMenuBuilder);
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("Load", this::loadAsset));
        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("New", this::createNewAsset));
    }

    @Override
    protected Supplier<ScenarioSimulationModel> getContentSupplier() {
        return scenarioSimulationEditorPresenter.getContentSupplier();
    }

    protected void getModelSuccessCallbackMethod(ScenarioSimulationModel model) {
        scenarioSimulationEditorPresenter.setPackageName("com");
        resetEditorPages();
        DataManagementStrategy dataManagementStrategy;
        if (ScenarioSimulationModel.Type.RULE.equals(model.getSimulation().getSimulationDescriptor().getType())) {
            dataManagementStrategy = new SubmarineDMODataManagementStrategy(scenarioSimulationEditorPresenter.getContext());
        } else {
            dataManagementStrategy = new SubmarineDMNDataManagementStrategy(scenarioSimulationEditorPresenter.getContext(), scenarioSimulationEditorPresenter.getEventBus());
        }
//        dataManagementStrategy.manageScenarioSimulationModelContent(null, content);
        scenarioSimulationEditorPresenter.getModelSuccessCallbackMethod(dataManagementStrategy, model);
    }

    protected void loadAsset() {
        GWT.log(this.toString() + " loadAsset");
        setContent(scesimFilesProvider.getPopulatedScesimRule());
        scenarioSimulationEditorPresenter.showDocks(PlaceStatus.CLOSE);
    }

    protected void createNewAsset() {
        GWT.log(this.toString() + " createNewAsset");
        setContent(scesimFilesProvider.getNewScesimRule());
        scenarioSimulationEditorPresenter.showDocks(PlaceStatus.CLOSE);
    }
}
