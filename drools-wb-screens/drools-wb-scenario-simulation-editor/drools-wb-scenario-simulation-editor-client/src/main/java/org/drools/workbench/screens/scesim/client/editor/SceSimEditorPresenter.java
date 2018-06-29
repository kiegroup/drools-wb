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

package org.drools.workbench.screens.scesim.client.editor;

import java.util.List;
import java.util.function.Supplier;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import org.drools.workbench.screens.scesim.client.type.SceSimResourceType;
import org.drools.workbench.screens.scesim.model.SceSimModelContent;
import org.drools.workbench.screens.scesim.service.SceSimService;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.widgets.client.popups.validation.ValidationPopup;
import org.kie.workbench.common.widgets.metadata.client.KieEditor;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.annotations.WorkbenchEditor;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.ext.editor.commons.service.support.SupportsSaveAndRename;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

/**
 * Enum Editor Presenter
 */
@Dependent
@WorkbenchEditor(identifier = "SceSimEditor", supportedTypes = {SceSimResourceType.class})
public class SceSimEditorPresenter
        extends KieEditor<String> {

    private SceSimEditorView view;

    private Caller<SceSimService> enumService;

    private SceSimResourceType type;

    private ValidationPopup validationPopup;

    public SceSimEditorPresenter() {
        //Zero-parameter constructor for CDI proxies
    }

    @Inject
    public SceSimEditorPresenter(final SceSimEditorView baseView,
                                 final Caller<SceSimService> enumService,
                                 final SceSimResourceType type,
                                 final ValidationPopup validationPopup) {
        super(baseView);
        this.view = baseView;
        this.enumService = enumService;
        this.type = type;
        this.validationPopup = validationPopup;
    }

    @OnStartup
    public void onStartup(final ObservablePath path,
                          final PlaceRequest place) {
        super.init(path,
                   place,
                   type);

       /* final ScenarioGridLayer scenarioGridLayer = new ScenarioGridLayer();
        final ScenarioGrid scenarioGrid = new ScenarioGrid(new ScenarioGridModel(), scenarioGridLayer, new ScenarioGridRenderer(false));

        scenarioGridLayer.add(scenarioGrid);

        ScenarioGridPanel scenarioGridPanel = new ScenarioGridPanel(1000, 1000);

        scenarioGridPanel.add(scenarioGridLayer);
        GWT.log("scenarioGridPanel " + scenarioGridPanel);*/

        // view.setWidget(new Label("PUPPA"));
    }

    protected void loadContent() {
        GWT.log("loadContent");
        //    view.showLoading();
        enumService.call(getModelSuccessCallback(),
                         getNoSuchFileExceptionErrorCallback()).loadContent(versionRecordManager.getCurrentPath());
    }

    @Override
    protected Supplier<String> getContentSupplier() {
//        return () -> SceSimParser.toString(view.getContent());
        return () -> view.getContent().toString();
    }

    @Override
    protected Caller<? extends SupportsSaveAndRename<String, Metadata>> getSaveAndRenameServiceCaller() {
        return enumService;
    }

    private RemoteCallback<SceSimModelContent> getModelSuccessCallback() {
        return content -> {
            //Path is set to null when the Editor is closed (which can happen before async calls complete).
            if (versionRecordManager.getCurrentPath() == null) {
                return;
            }

            resetEditorPages(content.getOverview());
            addSourcePage();

            final List<SceSimRow> enumDefinitions = SceSimParser.fromString(content.getModel().getEnumDefinitions());
            view.setContent(enumDefinitions);
            view.hideBusyIndicator();

            createOriginalHash(SceSimParser.toString(enumDefinitions));
        };
    }

    @Override
    protected void onValidate(final Command finished) {
        GWT.log("onValidate " + finished);
        /*enumService.call(
                validationPopup.getValidationCallback(finished),
                new CommandErrorCallback(finished)).validate(versionRecordManager.getCurrentPath(),
                                                             SceSimParser.toString(view.getContent()));*/
    }

    @Override
    protected void save(final String commitMessage) {
        GWT.log("save " + commitMessage);
        /*final List<SceSimRow> content = view.getContent();
        enumService.call(getSaveSuccessCallback(content.hashCode()),
                         new HasBusyIndicatorDefaultErrorCallback(view)).save(versionRecordManager.getCurrentPath(),
                                                                              SceSimParser.toString(content),
                                                                              metadata,
                                                                              commitMessage);*/
    }

    @Override
    public void onSourceTabSelected() {
        GWT.log("onSourceTabSelected");
//        updateSource(SceSimParser.toString(view.getContent()));
    }

    @OnClose
    public void onClose() {
        this.versionRecordManager.clear();
    }

    @OnMayClose
    public boolean mayClose() {
        return super.mayClose(view.getContent());
    }

    @WorkbenchPartTitle
    public String getTitleText() {
        return super.getTitleText();
    }

    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        return super.getTitle();
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        return super.getWidget();
    }

    @WorkbenchMenu
    public Menus getMenus() {
        return menus;
    }
}
