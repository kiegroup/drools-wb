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

package org.drools.workbench.screens.scenariosimulation.kogito.client.editor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import elemental2.promise.Promise;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.ScenarioWithIndex;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.scenariosimulation.api.model.SimulationDescriptor;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorWrapper;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.DataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioSimulationHasBusyIndicatorDefaultErrorCallback;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.converters.ScenarioSimulationModelContainerConverter;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.converters.ScenarioSimulationModelConverter;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ScenarioSimulationModelKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.strategies.KogitoDMNDataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.strategies.KogitoDMODataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.model.SimulationRunResult;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerPresenter;
import org.kie.workbench.common.submarine.client.editor.MultiPageEditorContainerView;
import org.kie.workbench.common.widgets.client.docks.AuthoringEditorDock;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.backend.vfs.impl.ObservablePathImpl;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.PlaceStatus;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.Menus;


@Dependent
/**
 * Wrapper to be used inside Submarine
 */
public class ScenarioSimulationEditorKogitoWrapper extends MultiPageEditorContainerPresenter<ScenarioSimulationModel> implements ScenarioSimulationEditorWrapper {

    protected ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter;
    private FileMenuBuilder fileMenuBuilder;
    private AuthoringEditorDock authoringWorkbenchDocks;

    public ScenarioSimulationEditorKogitoWrapper() {
        //Zero-parameter constructor for CDI proxies
    }

    @Inject
    public ScenarioSimulationEditorKogitoWrapper(
            final ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter,
            final FileMenuBuilder fileMenuBuilder,
            final PlaceManager placeManager,
            final MultiPageEditorContainerView multiPageEditorContainerView,
            final AuthoringEditorDock authoringWorkbenchDocks) {
        super(scenarioSimulationEditorPresenter.getView(), fileMenuBuilder, placeManager, multiPageEditorContainerView);
        this.scenarioSimulationEditorPresenter = scenarioSimulationEditorPresenter;
        this.fileMenuBuilder = fileMenuBuilder;
        this.authoringWorkbenchDocks = authoringWorkbenchDocks;
    }

    @Override
    protected void buildMenuBar() {
        GWT.log(this.toString() + " buildMenuBar");
        setMenus(fileMenuBuilder.build());
        getMenus().getItemsMap().values().forEach(menuItem -> menuItem.setEnabled(true));
    }

    @Override
    public Promise getContent() {
        GWT.log(this.toString() + " getContent");
        return null;
    }

    @Override
    public void setContent(String value) {
        GWT.log(this.toString() + " setContent ");
        getWidget().init(this);
        Path path = new PathFactory.PathImpl("new.scesim", "file:///new.scesim");
        scenarioSimulationEditorPresenter.init(this, new ObservablePathImpl().wrap(path));
        scenarioSimulationEditorPresenter.showDocks(PlaceStatus.CLOSE);
        unmarshallContent(value);
    }

    @Override
    public void wrappedRegisterDock(String id, IsWidget widget) {
        GWT.log(this.toString() + " setContent ");
    }

    @Override
    public void onImport(String fileContents, RemoteCallback<Simulation> importCallBack, ErrorCallback<Object> importErrorCallback, Simulation simulation) {
        GWT.log(this.toString() + " onImport ");
    }

    @Override
    public void onExportToCsv(RemoteCallback<Object> exportCallBack, ScenarioSimulationHasBusyIndicatorDefaultErrorCallback scenarioSimulationHasBusyIndicatorDefaultErrorCallback, Simulation simulation) {
        GWT.log(this.toString() + " onExportToCsv ");
    }

    @Override
    public void resetContentHash() {
        GWT.log(this.toString() + " resetContentHash");
    }

    public void onStartup(final PlaceRequest place) {
        GWT.log(this.toString() + " onStartup " + place);
        super.init(place);
        authoringWorkbenchDocks.setup("AuthoringPerspective", place);
    }

    public boolean mayClose() {
        GWT.log(this.toString() + " mayClose");
        return !scenarioSimulationEditorPresenter.isDirty();
    }

    public IsWidget getTitle() {
        GWT.log(this.toString() + " getTitle");
        return super.getTitle();
    }

    public MultiPageEditorContainerView getWidget() {
        GWT.log(this.toString() + " getWidget");
        return super.getWidget();
    }

    public void setMenus(final Consumer<Menus> menusConsumer) {
        GWT.log(this.toString() + " setMenus " + menusConsumer);
        menusConsumer.accept(getMenus());
    }

    @Override
    public void onRunScenario(RemoteCallback<SimulationRunResult> refreshModelCallback, ScenarioSimulationHasBusyIndicatorDefaultErrorCallback scenarioSimulationHasBusyIndicatorDefaultErrorCallback, SimulationDescriptor simulationDescriptor, List<ScenarioWithIndex> toRun) {
        throw new UnsupportedOperationException("Not available in Submarine");
    }

    @Override
    public void wrappedSave(String commitMessage) {
        GWT.log(this.toString() + " wrappedSave " + commitMessage);
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
//        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("Load", this::loadAsset));
//        fileMenuBuilder.addNewTopLevelMenu(new ScenarioMenuItem("New", this::createNewAsset));
    }

    @Override
    protected Supplier<ScenarioSimulationModel> getContentSupplier() {
        return scenarioSimulationEditorPresenter.getContentSupplier();
    }

    protected void unmarshallContent(String toUnmarshal) {
        GWT.log(this.toString() + " unmarshallContent");
        try {
            final ScenarioSimulationModelContainerConverter scenarioSimulationModelContainerConverter = new ScenarioSimulationModelContainerConverter();
            final org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ScenarioSimulationModelContainer scenarioSimulationModelContainer = scenarioSimulationModelContainerConverter.deserializeFromJson(toUnmarshal);
            GWT.log(scenarioSimulationModelContainer.toString() +" " + scenarioSimulationModelContainer.getScenarioSimulationModel());
            final ScenarioSimulationModelKM scenarioSimulationModelKM = scenarioSimulationModelContainer.getScenarioSimulationModel();
            GWT.log(scenarioSimulationModelKM.toString() +" " + scenarioSimulationModelKM.getVersion());
            ScenarioSimulationModel model = new ScenarioSimulationModelConverter().toApiModel(scenarioSimulationModelKM);
            GWT.log(model.toString());
            getModelSuccessCallbackMethod(model);
        } catch (Exception e) {
            GWT.log(e.getMessage(), e);
        }
    }

    protected void getModelSuccessCallbackMethod(ScenarioSimulationModel model) {
        scenarioSimulationEditorPresenter.setPackageName("com");
        resetEditorPages();
        DataManagementStrategy dataManagementStrategy;
        if (ScenarioSimulationModel.Type.RULE.equals(model.getSimulation().getSimulationDescriptor().getType())) {
            dataManagementStrategy = new KogitoDMODataManagementStrategy(scenarioSimulationEditorPresenter.getContext());
        } else {
            dataManagementStrategy = new KogitoDMNDataManagementStrategy(scenarioSimulationEditorPresenter.getContext(), scenarioSimulationEditorPresenter.getEventBus());
        }
        // TODO CHECK
//        dataManagementStrategy.manageScenarioSimulationModelContent(null, content);
        scenarioSimulationEditorPresenter.getModelSuccessCallbackMethod(dataManagementStrategy, model);
    }

//    protected void loadAsset() {
//        GWT.log(this.toString() + " loadAsset");
//        fileChooserPopupPresenter.show("Choose", "Choose", this::loadAssetCommand);
//    }
//
//    protected void loadAssetCommand() {
//        String fileName = fileChooserPopupPresenter.getFileName();
//        setContent(scesimFilesProvider.getScesimFile(fileChooserPopupPresenter.getFileName()));
//        Path path = new PathFactory.PathImpl(fileName, "file:///" + fileName);
//        scenarioSimulationEditorPresenter.init(this, new ObservablePathImpl().wrap(path));
//        scenarioSimulationEditorPresenter.showDocks(PlaceStatus.CLOSE);
//    }
//
//    protected void createNewAsset() {
//        GWT.log(this.toString() + " createNewAsset");
//        setContent(scesimFilesProvider.getNewScesimRule());
//        Path path = new PathFactory.PathImpl("new.scesim", "file:///new.scesim");
//        scenarioSimulationEditorPresenter.init(this, new ObservablePathImpl().wrap(path));
//        scenarioSimulationEditorPresenter.showDocks(PlaceStatus.CLOSE);
//    }
}
