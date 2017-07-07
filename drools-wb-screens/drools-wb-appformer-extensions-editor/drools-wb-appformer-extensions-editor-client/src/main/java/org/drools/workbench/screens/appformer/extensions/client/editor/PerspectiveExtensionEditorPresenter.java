/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.appformer.extensions.client.editor;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import org.drools.workbench.screens.appformer.extensions.client.type.PerspectiveExtensionResourceType;
import org.drools.workbench.screens.appformer.extensions.model.PerspectiveExtensionContent;
import org.drools.workbench.screens.appformer.extensions.service.PerspectiveExtensionService;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.widgets.metadata.client.KieEditor;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.annotations.WorkbenchEditor;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartTitleDecoration;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.ext.layout.editor.api.LayoutServices;
import org.uberfire.ext.layout.editor.api.editor.LayoutTemplate;
import org.uberfire.ext.layout.editor.client.LayoutEditorPresenter;
import org.uberfire.ext.layout.editor.client.api.LayoutDragComponentGroup;
import org.uberfire.ext.plugin.client.perspective.editor.api.PerspectiveDragComponentUtils;
import org.uberfire.ext.widgets.common.client.callbacks.HasBusyIndicatorDefaultErrorCallback;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnMayClose;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.Menus;

/**
 * This is the default perspective extension editor based on Uberfire Layout Editor.
 */
@Dependent
@WorkbenchEditor(identifier = "PerspectiveExtensionEditor", supportedTypes = {PerspectiveExtensionResourceType.class})
public class PerspectiveExtensionEditorPresenter
        extends KieEditor {

    private PerspectiveExtensionEditorView view;

    private PerspectiveExtensionResourceType perspectiveExtensionResourceType;

    private Caller<PerspectiveExtensionService> perspectiveExtensionService;

    private Caller<LayoutServices> layoutServices;

    private LayoutEditorPresenter layoutEditorPresenter;

    @Inject
    public PerspectiveExtensionEditorPresenter(final PerspectiveExtensionEditorView view,
                                               PerspectiveExtensionResourceType perspectiveExtensionResourceType,
                                               Caller<PerspectiveExtensionService> perspectiveExtensionService,
                                               Caller<LayoutServices> layoutServices,
                                               LayoutEditorPresenter layoutEditorPresenter) {
        super(view);
        this.view = view;
        this.perspectiveExtensionResourceType = perspectiveExtensionResourceType;
        this.perspectiveExtensionService = perspectiveExtensionService;
        this.layoutServices = layoutServices;
        this.layoutEditorPresenter = layoutEditorPresenter;
    }

    @PostConstruct
    public void init() {
        view.init(this);
    }

    @OnStartup
    public void onStartup(final ObservablePath path,
                          final PlaceRequest place) {
        super.init(path,
                   place,
                   perspectiveExtensionResourceType);
        this.view.setContent(layoutEditorPresenter.getView().getElement());
    }

    void setupLayoutEditor() {
        layoutEditorPresenter.clear();
        layoutEditorPresenter.addDraggableComponentGroup(lookupPerspectiveDragComponents());
        layoutEditorPresenter.setPageStyle(LayoutTemplate.Style.PAGE);
    }

    LayoutDragComponentGroup lookupPerspectiveDragComponents() {
        return PerspectiveDragComponentUtils.lookupPerspectiveDragComponents();
    }

    protected void loadContent() {
        view.showLoading();
        perspectiveExtensionService.call(getLoadContentSuccessCallback(),
                                         getNoSuchFileExceptionErrorCallback()).loadContent(getCurrentPath());
    }

    ObservablePath getCurrentPath() {
        return versionRecordManager.getCurrentPath();
    }

    RemoteCallback<PerspectiveExtensionContent> getLoadContentSuccessCallback() {
        return content -> {
            //Path is set to null when the Editor is closed (which can happen before async calls complete).
            if (!thereIsAPath()) {
                view.hideBusyIndicator();
                return;
            }

            resetEditorPages(content);
            setupLayoutEditor();
            layoutEditorPresenter.loadLayout(content.getContent(),
                                             view.emptyTitleText(),
                                             view.emptySubTitleText());

            view.hideBusyIndicator();
            createOriginalHash(content.getContent());
        };
    }

    void resetEditorPages(PerspectiveExtensionContent content) {
        resetEditorPages(content.getOverview());
    }

    boolean thereIsAPath() {
        return getCurrentPath() != null;
    }

    @Override
    protected void save(String commitMessage) {
        LayoutTemplate layout = layoutEditorPresenter.getLayout();

        layoutServices.call(new RemoteCallback<String>() {
            @Override
            public void callback(String stringLayout) {
                perspectiveExtensionService.call(getSaveSuccessCallback(layout.hashCode()),
                                                 new HasBusyIndicatorDefaultErrorCallback(view)).save(getCurrentPath(),
                                                                                                      stringLayout,
                                                                                                      metadata,
                                                                                                      commitMessage
                );
            }
        }).convertLayoutToString(layout);
    }

    @OnClose
    public void onClose() {
        versionRecordManager.clear();
    }

    @OnMayClose
    public boolean mayClose() {
        return super.mayClose(layoutEditorPresenter.getLayout());
    }

    @WorkbenchPartTitleDecoration
    public IsWidget getTitle() {
        return super.getTitle();
    }

    @WorkbenchPartTitle
    public String getTitleText() {
        return super.getTitleText();
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
