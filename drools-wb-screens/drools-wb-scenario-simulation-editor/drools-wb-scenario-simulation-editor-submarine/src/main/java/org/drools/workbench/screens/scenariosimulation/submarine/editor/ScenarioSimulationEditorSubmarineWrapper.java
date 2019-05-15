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

package org.drools.workbench.screens.scenariosimulation.submarine.editor;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import elemental2.promise.Promise;
import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.ScenarioWithIndex;
import org.drools.scenariosimulation.api.model.SimulationDescriptor;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorWrapper;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.DataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.SubmarineDMNDataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.SubmarineDMODataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModelContent;
import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationSubmarineService;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerPresenter;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerView;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.ext.widgets.common.client.callbacks.HasBusyIndicatorDefaultErrorCallback;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

import static org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter.IDENTIFIER;

@Dependent
@WorkbenchScreen(identifier = IDENTIFIER)
/**
 * Wrapper to be used inside Submarine
 */
public class ScenarioSimulationEditorSubmarineWrapper extends MultiPageEditorContainerPresenter<ScenarioSimulationModel> implements ScenarioSimulationEditorWrapper {

    protected ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter;
    protected Caller<ScenarioSimulationSubmarineService> service;
    private FileMenuBuilder fileMenuBuilder;

    public ScenarioSimulationEditorSubmarineWrapper() {
        //Zero-parameter constructor for CDI proxies
    }

    @Inject
    public ScenarioSimulationEditorSubmarineWrapper(final Caller<ScenarioSimulationSubmarineService> service,
                                                    final ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter,
                                                    final FileMenuBuilder fileMenuBuilder,
                                                    final PlaceManager placeManager,
                                                    final MultiPageEditorContainerView multiPageEditorContainerView) {
        super(scenarioSimulationEditorPresenter.getView(), fileMenuBuilder, placeManager, multiPageEditorContainerView);
        this.service = service;
        this.scenarioSimulationEditorPresenter = scenarioSimulationEditorPresenter;
        this.fileMenuBuilder = fileMenuBuilder;
    }

    @Override
    protected void buildMenuBar() {

    }

    @Override
    public void setContent(String value) {
        service.call((RemoteCallback<ScenarioSimulationModelContent>) this::getModelSuccessCallbackMethod).unmarshal(value);
    }

    @Override
    public Promise getContent() {
        return null;
    }

    @Override
    public void resetContentHash() {

    }

    @OnStartup
    public void onStartup(final PlaceRequest place) {
        super.init(place);
        scenarioSimulationEditorPresenter.init(this, (ObservablePath) place.getPath());
    }

    @OnMayClose
    public boolean mayClose() {
        return !scenarioSimulationEditorPresenter.isDirty();
    }


    @WorkbenchPartTitle
    public String getTitleText() {
        return super.getTitle().toString();
    }


    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        return super.getTitle();
    }

    @WorkbenchPartView
    public MultiPageEditorContainerView getWidget() {
        return super.getWidget();
    }

    @WorkbenchMenu
    public Menus getMenus() {
        return super.getMenus();
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
        scenarioSimulationEditorPresenter.makeMenuBar(fileMenuBuilder);
    }

    @Override
    protected Supplier<ScenarioSimulationModel> getContentSupplier() {
        return scenarioSimulationEditorPresenter.getContentSupplier();
    }

    protected void getModelSuccessCallbackMethod(ScenarioSimulationModelContent content) {
        scenarioSimulationEditorPresenter.setPackageName(content.getDataModel().getPackageName());
        resetEditorPages();
        DataManagementStrategy dataManagementStrategy;
        if (ScenarioSimulationModel.Type.RULE.equals(content.getModel().getSimulation().getSimulationDescriptor().getType())) {
            dataManagementStrategy = new SubmarineDMODataManagementStrategy(scenarioSimulationEditorPresenter.getContext());
        } else {
            dataManagementStrategy = new SubmarineDMNDataManagementStrategy(scenarioSimulationEditorPresenter.getContext(), scenarioSimulationEditorPresenter.getEventBus());
        }
        dataManagementStrategy.manageScenarioSimulationModelContent(null, content);
        ScenarioSimulationModel model = content.getModel();
        scenarioSimulationEditorPresenter.getModelSuccessCallbackMethod(dataManagementStrategy, model);
    }
}
