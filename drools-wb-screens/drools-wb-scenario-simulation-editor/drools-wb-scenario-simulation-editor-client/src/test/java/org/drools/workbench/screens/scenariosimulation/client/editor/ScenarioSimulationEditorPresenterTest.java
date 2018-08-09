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

package org.drools.workbench.screens.scenariosimulation.client.editor;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioSimulationGridPanelClickHandler;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.RightPanelPresenter;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.guvnor.messageconsole.client.console.widget.button.AlertsButtonMenuItemBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracleFactory;
import org.kie.workbench.common.widgets.configresource.client.widget.bound.ImportsWidgetPresenter;
import org.kie.workbench.common.widgets.metadata.client.KieEditorWrapperView;
import org.kie.workbench.common.widgets.metadata.client.widget.OverviewWidgetPresenter;
import org.mockito.Mock;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.PlaceStatus;
import org.uberfire.client.workbench.events.PlaceGainFocusEvent;
import org.uberfire.client.workbench.events.PlaceHiddenEvent;
import org.uberfire.ext.editor.commons.client.validation.DefaultFileNameValidator;
import org.uberfire.mocks.CallerMock;
import org.uberfire.mocks.EventSourceMock;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.events.NotificationEvent;
import org.uberfire.workbench.model.menu.MenuItem;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioSimulationEditorPresenterTest extends AbstractScenarioSimulationEditorTest {

    private ScenarioSimulationEditorPresenter presenter;

    @Mock
    private KieEditorWrapperView mockKieView;

    @Mock
    private OverviewWidgetPresenter mockOverviewWidget;

    @Mock
    private DefaultFileNameValidator mockFileNameValidator;

    @Mock
    private AlertsButtonMenuItemBuilder mockAlertsButtonMenuItemBuilder;

    @Mock
    private EventSourceMock<NotificationEvent> mockNotification;

    @Mock
    private ScenarioGridLayer mockScenarioGridLayer;

    @Mock
    private ScenarioSimulationView mockScenarioSimulationView;

    @Mock
    private ScenarioSimulationGridPanelClickHandler mockScenarioSimulationGridPanelClickHandler;

    @Mock
    private ImportsWidgetPresenter mockImportsWidget;

    @Mock
    private AsyncPackageDataModelOracleFactory mockOracleFactory;

    @Mock
    private PlaceManager mockPlaceManager;

    @Mock
    private PlaceRequest mockPlaceRequest;

    @Mock
    private HandlerRegistration mockHandler;

    @Before
    public void setup() {
        super.setup();
        when(mockScenarioSimulationView.getScenarioGridPanel()).thenReturn(mockScenarioGridPanel);

        when(mockScenarioGridPanel.getDefaultGridLayer()).thenReturn(mockScenarioGridLayer);

        when(mockPlaceRequest.getIdentifier()).thenReturn(ScenarioSimulationEditorPresenter.IDENTIFIER);

        this.presenter = spy(new ScenarioSimulationEditorPresenter(new CallerMock<>(scenarioSimulationService),
                                                                   type,
                                                                   mockImportsWidget,
                                                                   mockOracleFactory,
                                                                   mockPlaceManager,
                                                                   mockGridContextMenu,
                                                                   mockHeaderContextMenu) {
            {
                this.kieView = mockKieView;
                this.overviewWidget = mockOverviewWidget;
                this.fileMenuBuilder = mockFileMenuBuilder;
                this.fileNameValidator = mockFileNameValidator;
                this.versionRecordManager = mockVersionRecordManager;
                this.notification = mockNotification;
                this.workbenchContext = mockWorkbenchContext;
                this.alertsButtonMenuItemBuilder = mockAlertsButtonMenuItemBuilder;
                this.clickHandlerRegistration = mockHandler;
            }

            @Override
            protected void registerClickHandler() {
                //
            }

            @Override
            protected MenuItem downloadMenuItem() {
                return mock(MenuItem.class);
            }

            @Override
            protected Command getSaveAndRename() {
                return mock(Command.class);
            }

            @Override
            protected ScenarioGridLayer newScenarioGridLayer() {
                return mockScenarioGridLayer;
            }

            @Override
            protected ScenarioSimulationGridPanelClickHandler newScenarioSimulationGridPanelClickHandler(final ScenarioGrid scenarioGrid) {
                return mockScenarioSimulationGridPanelClickHandler;
            }

            @Override
            protected ScenarioGridPanel newScenarioGridPanel(ScenarioGridLayer scenarioGridLayer) {
                return mockScenarioGridPanel;
            }

            @Override
            protected ScenarioSimulationView newScenarioSimulationView(ScenarioGridPanel newScenarioGridPanel) {
                return mockScenarioSimulationView;
            }

            @Override
            protected void makeMenuBar() {

            }
        });
    }

    @Test
    public void testOnStartup() {

        final AsyncPackageDataModelOracle oracle = mock(AsyncPackageDataModelOracle.class);
        when(mockOracleFactory.makeAsyncPackageDataModelOracle(any(),
                                                               eq(model),
                                                               eq(content.getDataModel()))).thenReturn(oracle);
        presenter.onStartup(mock(ObservablePath.class),
                            mock(PlaceRequest.class));
        verify(mockImportsWidget).setContent(oracle,
                                             model.getImports(),
                                             false);
        verify(mockKieView).addImportsTab(mockImportsWidget);
        verify(presenter.getView()).showLoading();
        verify(presenter.getView()).hideBusyIndicator();
        verify(mockScenarioGridLayer, times(1)).enterPinnedMode(any(), any());
    }

    @Test
    public void testInitComponents() {
        presenter.initComponents();
        verify(presenter, times(1)).newScenarioGridLayer();
        verify(presenter, times(1)).newScenarioSimulationGridPanelClickHandler(mockScenarioGrid);
        verify(presenter, times(1)).newScenarioGridPanel(mockScenarioGridLayer);
        verify(presenter, times(1)).newScenarioSimulationView(mockScenarioGridPanel);
    }

    @Test
    public void validateButtonShouldNotBeAdded() {
        presenter.onStartup(mock(ObservablePath.class),
                            mock(PlaceRequest.class));

        verify(presenter, never()).getValidateCommand();
    }

    @Test
    public void save() {
        presenter.onStartup(mock(ObservablePath.class),
                            mock(PlaceRequest.class));
        reset(presenter.getView());

        presenter.save("save message");

        verify(presenter.getView()).hideBusyIndicator();
        verify(mockNotification).fire(any(NotificationEvent.class));
        verify(mockVersionRecordManager).reloadVersions(any(Path.class));
    }

    @Test
    public void onPlaceGainFocusEvent() {
        PlaceGainFocusEvent mockPlaceGainFocusEvent = mock(PlaceGainFocusEvent.class);
        when(mockPlaceGainFocusEvent.getPlace()).thenReturn(mockPlaceRequest);
        when(mockPlaceManager.getStatus(RightPanelPresenter.IDENTIFIER)).thenReturn(PlaceStatus.CLOSE);
        presenter.onPlaceGainFocusEvent(mockPlaceGainFocusEvent);
        verify(mockPlaceManager, times(1)).goTo(RightPanelPresenter.IDENTIFIER);
    }

    @Test
    public void onPlaceHiddenEvent() {
        PlaceHiddenEvent mockPlaceHiddenEvent = mock(PlaceHiddenEvent.class);
        when(mockPlaceHiddenEvent.getPlace()).thenReturn(mockPlaceRequest);
        when(mockPlaceManager.getStatus(RightPanelPresenter.IDENTIFIER)).thenReturn(PlaceStatus.OPEN);
        presenter.onPlaceHiddenEvent(mockPlaceHiddenEvent);
        verify(mockPlaceManager, times(1)).closePlace(RightPanelPresenter.IDENTIFIER);
    }

    @Test
    public void onClose() {
        when(mockPlaceManager.getStatus(RightPanelPresenter.IDENTIFIER)).thenReturn(PlaceStatus.OPEN);
        presenter.onClose();
        onClosePlaceStatusOpen();
        reset(mockVersionRecordManager);
        reset(mockPlaceManager);
        reset(mockHandler);

        when(mockPlaceManager.getStatus(RightPanelPresenter.IDENTIFIER)).thenReturn(PlaceStatus.CLOSE);
        presenter.onClose();
        onClosePlaceStatusClose();
        reset(mockVersionRecordManager);
        reset(mockPlaceManager);
        reset(mockHandler);
    }

    private void onClosePlaceStatusOpen() {
        verify(mockVersionRecordManager, times(1)).clear();
        verify(mockPlaceManager, times(1)).closePlace(RightPanelPresenter.IDENTIFIER);
        verify(presenter.getView()).showLoading();
        verify(mockHandler, times(1)).removeHandler();
    }

    private void onClosePlaceStatusClose() {
        verify(mockVersionRecordManager, times(1)).clear();
        verify(mockPlaceManager, times(0)).closePlace(RightPanelPresenter.IDENTIFIER);
        verify(mockHandler, times(1)).removeHandler();
    }
}
