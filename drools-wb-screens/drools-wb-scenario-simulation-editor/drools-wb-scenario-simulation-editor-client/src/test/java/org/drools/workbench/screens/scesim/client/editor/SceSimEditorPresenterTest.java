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
import java.util.Optional;

import javax.enterprise.event.Event;

import com.google.gwt.core.client.GWT;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scesim.client.type.SceSimResourceType;
import org.drools.workbench.screens.scesim.model.SceSimModel;
import org.drools.workbench.screens.scesim.model.SceSimModelContent;
import org.drools.workbench.screens.scesim.service.SceSimService;
import org.guvnor.common.services.project.client.context.WorkspaceProjectContext;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.guvnor.common.services.shared.metadata.model.Overview;
import org.guvnor.messageconsole.client.console.widget.button.AlertsButtonMenuItemBuilder;
import org.jboss.errai.common.client.api.Caller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.kie.workbench.common.widgets.client.popups.validation.ValidationPopup;
import org.kie.workbench.common.widgets.client.source.ViewDRLSourceWidget;
import org.kie.workbench.common.widgets.metadata.client.KieEditorWrapperView;
import org.kie.workbench.common.widgets.metadata.client.widget.OverviewWidgetPresenter;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.ext.editor.commons.client.history.VersionRecordManager;
import org.uberfire.ext.editor.commons.client.menu.common.SaveAndRenameCommandBuilder;
import org.uberfire.ext.editor.commons.client.validation.DefaultFileNameValidator;
import org.uberfire.mocks.EventSourceMock;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.events.NotificationEvent;
import org.uberfire.workbench.model.menu.MenuItem;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class SceSimEditorPresenterTest {

    @Mock
    private SceSimEditorView view;
    @Mock
    private KieEditorWrapperView mockKieView;

    @Mock
    private OverviewWidgetPresenter mockOverviewWidget;

    @Mock
    private VersionRecordManager mockVersionRecordManager;

    @Mock
    private FileMenuBuilder mockFileMenuBuilder;

    @Mock
    private DefaultFileNameValidator mockFileNameValidator;

    @Mock
    private SceSimService sceSimService;

    private Caller<SceSimService> enumServiceCaller;

    @Mock
    private ObservablePath path;

    @Mock
    private PlaceRequest place;

    @Mock
    private Overview overview;

    @Mock
    private WorkspaceProjectContext mockWorkbenchContext;

    @Mock
    private SaveAndRenameCommandBuilder<String, Metadata> mockSaveAndRenameCommandBuilder;

    @Mock
    private ValidationPopup validationPopup;

    @Mock
    private AlertsButtonMenuItemBuilder mockAlertsButtonMenuItemBuilder;

    @GwtMock
    private ViewDRLSourceWidget sourceWidget;

    @Captor
    private ArgumentCaptor<List<SceSimRow>> enumsArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> enumStringArgumentCaptor;

    private Event<NotificationEvent> mockNotification = new EventSourceMock<NotificationEvent>() {
        @Override
        public void fire(final NotificationEvent event) {
            //Do nothing. Default implementation throws a RuntimeException
        }
    };

    private SceSimResourceType type;

    private SceSimEditorPresenter presenter;

    private SceSimModelContent content;
    private SceSimModel model;

    @Before
    public void setup() {
        //Mock SceSimResourceType
        this.type = GWT.create(SceSimResourceType.class);

        //Mock FileMenuBuilder usage since we cannot use FileMenuBuilderImpl either
        when(mockFileMenuBuilder.addSave(any(MenuItem.class))).thenReturn(mockFileMenuBuilder);
        when(mockFileMenuBuilder.addCopy(any(ObservablePath.class), any(DefaultFileNameValidator.class))).thenReturn(mockFileMenuBuilder);
        when(mockFileMenuBuilder.addRename(any(Command.class))).thenReturn(mockFileMenuBuilder);
        when(mockFileMenuBuilder.addDelete(any(ObservablePath.class))).thenReturn(mockFileMenuBuilder);
        when(mockFileMenuBuilder.addValidate(any(Command.class))).thenReturn(mockFileMenuBuilder);
        when(mockFileMenuBuilder.addNewTopLevelMenu(any(MenuItem.class))).thenReturn(mockFileMenuBuilder);

        when(mockVersionRecordManager.getCurrentPath()).thenReturn(path);
        when(mockVersionRecordManager.getPathToLatest()).thenReturn(path);

        when(mockWorkbenchContext.getActiveWorkspaceProject()).thenReturn(Optional.empty());

        this.model = new SceSimModel("'Fact.field' : ['a', 'b']");
        this.content = new SceSimModelContent(model,
                                              overview);

        when(sceSimService.loadContent(path)).thenReturn(content);
        // TODO GC

        /*when(view.getContent()).thenReturn(new ArrayList<SceSimRow>() {{
            add(new SceSimRow("Fact",
                              "field",
                              "['a', 'b']"));
        }});

        this.enumServiceCaller = new CallerMock<SceSimService>(sceSimService);

        this.presenter = new SceSimEditorPresenter(view,
                                                   enumServiceCaller,
                                                   type,
                                                   validationPopup) {
            {
                //Yuck, yuck, yuck... the class hierarchy is really a mess
                this.kieView = mockKieView;
                this.overviewWidget = mockOverviewWidget;
                this.fileMenuBuilder = mockFileMenuBuilder;
                this.fileNameValidator = mockFileNameValidator;
                this.versionRecordManager = mockVersionRecordManager;
                this.notification = mockNotification;
                this.workbenchContext = mockWorkbenchContext;
                this.saveAndRenameCommandBuilder = mockSaveAndRenameCommandBuilder;
                this.alertsButtonMenuItemBuilder = mockAlertsButtonMenuItemBuilder;
            }

            @Override
            protected Command getSaveAndRename() {
                return mock(Command.class);
            }
        };*/
    }

    @Test
    public void testOnStartup() {
        assertTrue(true); // TODO: Set the tests up.
    }
}
