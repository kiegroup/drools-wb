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
import jsinterop.base.Js;
import org.drools.scenariosimulation.api.model.AbstractScesimData;
import org.drools.scenariosimulation.api.model.AbstractScesimModel;
import org.drools.scenariosimulation.api.model.AuditLog;
import org.drools.scenariosimulation.api.model.Background;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.ScenarioWithIndex;
import org.drools.scenariosimulation.api.model.ScesimModelDescriptor;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.SCESIMMainJs;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.callbacks.SCESIMMarshallCallback;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.callbacks.SCESIMUnmarshallCallback;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenarioSimulationModelType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.SCESIM;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorWrapper;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.DataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioSimulationHasBusyIndicatorDefaultErrorCallback;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridWidget;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.strategies.KogitoDMNDataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.strategies.KogitoDMODataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.kogito.client.util.KogitoDMNService;
import org.drools.workbench.screens.scenariosimulation.model.SimulationRunResult;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.MainJs;
import org.kie.workbench.common.kogito.client.editor.MultiPageEditorContainerPresenter;
import org.kie.workbench.common.kogito.client.editor.MultiPageEditorContainerView;
import org.kie.workbench.common.widgets.client.docks.AuthoringEditorDock;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.impl.ObservablePathImpl;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.PlaceStatus;
import org.uberfire.client.promise.Promises;
import org.uberfire.client.views.pfly.multipage.MultiPageEditorViewImpl;
import org.uberfire.client.views.pfly.multipage.PageImpl;
import org.uberfire.lifecycle.GetContent;
import org.uberfire.lifecycle.SetContent;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

import static org.drools.workbench.screens.scenariosimulation.kogito.client.converters.scesim.ApiJSInteropConverter.getJSIScenarioSimulationModelType;
import static org.drools.workbench.screens.scenariosimulation.kogito.client.converters.scesim.JSInteropApiConverter.getScenarioSimulationModel;

/**
 * Wrapper to be used inside Kogito
 */
@Dependent
public class ScenarioSimulationEditorKogitoWrapper extends MultiPageEditorContainerPresenter<ScenarioSimulationModel> implements ScenarioSimulationEditorWrapper {

    protected ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter;
    private FileMenuBuilder fileMenuBuilder;
    private AuthoringEditorDock authoringWorkbenchDocks;
    private SCESIM scesimContainer;
    private Promises promises;
    private Path currentPath;
    private KogitoDMNService dmnTypeService;

    private ScenarioSimulationEditorKogitoWrapper() {
        //Zero-parameter constructor for CDI proxies
    }

    @Inject
    public ScenarioSimulationEditorKogitoWrapper(
            final ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter,
            final FileMenuBuilder fileMenuBuilder,
            final PlaceManager placeManager,
            final MultiPageEditorContainerView multiPageEditorContainerView,
            final AuthoringEditorDock authoringWorkbenchDocks,
            final Promises promises,
            final KogitoDMNService dmnTypeService) {
        super(scenarioSimulationEditorPresenter.getView(), placeManager, multiPageEditorContainerView);
        this.scenarioSimulationEditorPresenter = scenarioSimulationEditorPresenter;
        this.fileMenuBuilder = fileMenuBuilder;
        this.authoringWorkbenchDocks = authoringWorkbenchDocks;
        this.promises = promises;
        this.dmnTypeService = dmnTypeService;
    }

    @Override
    protected void buildMenuBar() {
        setMenus(fileMenuBuilder.build());
        getMenus().getItemsMap().values().forEach(menuItem -> menuItem.setEnabled(true));
    }

    @Override
    @GetContent
    public Promise<String> getContent() {
        return transform(scenarioSimulationEditorPresenter.getModel());
    }

    @Override
    @SetContent
    public void setContent(String value) {
        unmarshallContent(value);
    }

    @Override
    public void wrappedRegisterDock(String id, IsWidget widget) {
        //
    }

    @Override
    public void onImport(String fileContents, RemoteCallback<AbstractScesimModel> importCallBack, ErrorCallback<Object> importErrorCallback, AbstractScesimModel<? extends AbstractScesimData> scesimModel) {
        //
    }

    @Override
    public void onExportToCsv(RemoteCallback<Object> exportCallBack, ScenarioSimulationHasBusyIndicatorDefaultErrorCallback scenarioSimulationHasBusyIndicatorDefaultErrorCallback, AbstractScesimModel<? extends AbstractScesimData> scesimModel) {
        //
    }

    @Override
    public void onDownloadReportToCsv(RemoteCallback<Object> exportCallBack, ScenarioSimulationHasBusyIndicatorDefaultErrorCallback scenarioSimulationHasBusyIndicatorDefaultErrorCallback, AuditLog auditLog) {
        //
    }

    @Override
    public void validate(Simulation simulation, Settings settings, RemoteCallback<?> callback) {
        //
    }

    /**
     * This method is called when the main grid tab (Model) is focused
     */
    @Override
    public void onEditTabSelected() {
        super.onEditTabSelected();
        scenarioSimulationEditorPresenter.onEditTabSelected();
    }

    /**
     * This method adds specifically the Background grid and its related onFocus behavior
     * @param scenarioGridWidget
     */
    @Override
    public void addBackgroundPage(final ScenarioGridWidget scenarioGridWidget) {
        addPage(new PageImpl(scenarioGridWidget, ScenarioSimulationEditorConstants.INSTANCE.backgroundTabTitle()) {
            @Override
            public void onFocus() {
                super.onFocus();
                onBackgroundTabSelected();
            }
        });
    }

    @Override
    public void selectSimulationTab() {
        final TabListItem item = (TabListItem) ((MultiPageEditorViewImpl) getWidget().getMultiPage().getView()).getTabBar().getWidget(SIMULATION_TAB_INDEX);
        if (item != null) {
            item.showTab(false);
        }
    }

    @Override
    public void selectBackgroundTab() {
        final TabListItem item = (TabListItem) ((MultiPageEditorViewImpl) getWidget().getMultiPage().getView()).getTabBar().getWidget(BACKGROUND_TAB_INDEX);
        if (item != null) {
            item.showTab(false);
        }
    }

    @Override
    public void resetContentHash() {
        //
    }

    public void onStartup(final PlaceRequest place) {
        super.init(place);
        resetEditorPages();
        authoringWorkbenchDocks.setup("AuthoringPerspective", place);
        SCESIMMainJs.initializeJsInteropConstructors(SCESIMMainJs.getConstructorsMap());
        MainJs.initializeJsInteropConstructors(MainJs.getConstructorsMap());
    }

    public void gotoPath(Path path) {
        resetEditorPages();
        currentPath = path;
        scenarioSimulationEditorPresenter.init(this, new ObservablePathImpl().wrap(path));
//        scenarioSimulationEditorPresenter.showDocks(PlaceStatus.CLOSE);
    }

    public Path getCurrentPath() {
        return currentPath;
    }

    public boolean mayClose() {
        return !scenarioSimulationEditorPresenter.isDirty();
    }

    public IsWidget getTitle() {
        return super.getTitle();
    }

    public MultiPageEditorContainerView getWidget() {
        return super.getWidget();
    }

    public FileMenuBuilder getFileMenuBuilder() {
        return fileMenuBuilder;
    }

    public void setMenus(final Consumer<Menus> menusConsumer) {
        menusConsumer.accept(getMenus());
    }

    @Override
    public void onRunScenario(RemoteCallback<SimulationRunResult> refreshModelCallback, ScenarioSimulationHasBusyIndicatorDefaultErrorCallback scenarioSimulationHasBusyIndicatorDefaultErrorCallback, ScesimModelDescriptor simulationDescriptor, Settings settings, List<ScenarioWithIndex> toRun, Background background) {
        throw new UnsupportedOperationException("Not available in Submarine");
    }

    @Override
    public void wrappedSave(String commitMessage) {
        synchronizeColumnsDimension(scenarioSimulationEditorPresenter.getContext().getScenarioGridPanelByGridWidget(GridWidget.SIMULATION),
                                    scenarioSimulationEditorPresenter.getContext().getScenarioGridPanelByGridWidget(GridWidget.BACKGROUND));
        final ScenarioSimulationModel model = scenarioSimulationEditorPresenter.getModel();
    }

    @Override
    public Integer getOriginalHash() {
        return super.getOriginalContentHash();
    }

    protected Promise<String> transform(final ScenarioSimulationModel resource) {
        return promises.create((resolveCallbackFn, rejectCallbackFn) -> {
            marshallContent(resource, resolveCallbackFn);
        });
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
        return () -> scenarioSimulationEditorPresenter.getModel();
    }

    protected void marshallContent(ScenarioSimulationModel scenarioSimulationModel, Promise.PromiseExecutorCallbackFn.ResolveCallbackFn<String> resolveCallbackFn) {
        final JSIScenarioSimulationModelType jsiScenarioSimulationModelType = getJSIScenarioSimulationModelType(scenarioSimulationModel);
        JsUtils.setValueOnWrapped(scesimContainer, jsiScenarioSimulationModelType);
        SCESIMMainJs.marshall(scesimContainer, "scesim", getJSInteropMarshallCallback(resolveCallbackFn));
    }

    protected void unmarshallContent(String toUnmarshal) {
        SCESIMMainJs.unmarshall(toUnmarshal, "scesim", getJSInteropUnmarshallCallback());
    }

    protected SCESIMMarshallCallback getJSInteropMarshallCallback(Promise.PromiseExecutorCallbackFn.ResolveCallbackFn<String> resolveCallbackFn) {
        return xml -> {
            GWT.log("xml " + xml);
            resolveCallbackFn.onInvoke(xml);
        };
    }

    protected SCESIMUnmarshallCallback getJSInteropUnmarshallCallback() {
        return scesim -> {
            this.scesimContainer = scesim;
            final JSIScenarioSimulationModelType scenarioSimulationModelType = Js.uncheckedCast(JsUtils.getUnwrappedElement(scesim));
            try {
                final ScenarioSimulationModel scenarioSimulationModel = getScenarioSimulationModel(scenarioSimulationModelType);
                getModelSuccessCallbackMethod(scenarioSimulationModel);
            } catch (Throwable t) {
                GWT.log("Failed to transform scesim", t);
            }
        };
    }

    protected void getModelSuccessCallbackMethod(ScenarioSimulationModel model) {
        scenarioSimulationEditorPresenter.setPackageName("com");
        DataManagementStrategy dataManagementStrategy;
        if (ScenarioSimulationModel.Type.RULE.equals(model.getSettings().getType())) {
            dataManagementStrategy = new KogitoDMODataManagementStrategy();
        } else {
            dataManagementStrategy = new KogitoDMNDataManagementStrategy(scenarioSimulationEditorPresenter.getEventBus(), dmnTypeService);
        }
        dataManagementStrategy.setModel(model);
        setOriginalContentHash(scenarioSimulationEditorPresenter.getJsonModel(model).hashCode());
        scenarioSimulationEditorPresenter.getModelSuccessCallbackMethod(dataManagementStrategy, model);
        showDocks();
    }

    protected void onBackgroundTabSelected() {
        scenarioSimulationEditorPresenter.onBackgroundTabSelected();
    }

    protected void onImportsTabSelected() {
        scenarioSimulationEditorPresenter.onImportsTabSelected();
    }

    protected void showDocks() {
//        final DefaultPlaceRequest placeRequest = new DefaultPlaceRequest(TestToolsPresenter.IDENTIFIER);
        scenarioSimulationEditorPresenter.showDocks(PlaceStatus.CLOSE);
//        registerTestToolsCallback();
    }
}
