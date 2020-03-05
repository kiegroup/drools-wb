package org.drools.workbench.screens.scenariosimulation.webapp.client.editor;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.kogito.client.dmn.KogitoScenarioSimulationBuilder;
import org.drools.workbench.screens.scenariosimulation.kogito.client.editor.ScenarioSimulationEditorKogitoWrapper;
import org.drools.workbench.screens.scenariosimulation.webapp.client.popup.KogitoScesimPopupPresenter;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.uberfire.backend.vfs.Path;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class AbstractScenarioSimulationEditorKogitoScreenTest {

    @Mock
    private ScenarioSimulationEditorKogitoWrapper scenarioSimulationEditorKogitoWrapperMock;
    @Mock
    private KogitoScenarioSimulationBuilder scenarioSimulationBuilderMock;
    @Mock
    private KogitoScesimPopupPresenter kogitoScesimPopupPresenterMock;
    @Captor
    private ArgumentCaptor<Command> createCommandCaptor;
    @Captor
    private ArgumentCaptor<RemoteCallback> remoteCallbackArgumentCaptor;
    @Captor
    private ArgumentCaptor<Path> pathArgumentCaptor;

    private AbstractScenarioSimulationEditorKogitoScreen abstractScenarioSimulationEditorKogitoScreenSpy;

    @Before
    public void setup() {
        abstractScenarioSimulationEditorKogitoScreenSpy = spy(new AbstractScenarioSimulationEditorKogitoScreen() {
            {
                kogitoScesimPopupPresenter = kogitoScesimPopupPresenterMock;
                scenarioSimulationBuilder = scenarioSimulationBuilderMock;
                scenarioSimulationEditorKogitoWrapper = scenarioSimulationEditorKogitoWrapperMock;
            }

            @Override
            public PlaceRequest getPlaceRequest() {
                return null;
            }

            @Override
            protected void showPopover(String title, String content) {
                //Do nothing
            }
        });
        when(kogitoScesimPopupPresenterMock.getSelectedPath()).thenReturn("selected");
    }

    @Test
    public void newFileEmptySelectedType() {
        abstractScenarioSimulationEditorKogitoScreenSpy.newFile("fake/path/");
        verify(kogitoScesimPopupPresenterMock, times(1)).show(isA(String.class), createCommandCaptor.capture());
        createCommandCaptor.getValue().execute();
        verify(kogitoScesimPopupPresenterMock, times(1)).getSelectedType();
        verify(abstractScenarioSimulationEditorKogitoScreenSpy, times(1)).showPopover(eq("ERROR"), eq("Missing selected type"));

        verify(scenarioSimulationBuilderMock, never()).populateScenarioSimulationModel(isA(ScenarioSimulationModel.class),
                                                                                        eq(ScenarioSimulationModel.Type.RULE),
                                                                                        eq(""),
                                                                                        remoteCallbackArgumentCaptor.capture());
    }

    @Test
    public void newFileEmptySelectedDMNPath() {
        when(kogitoScesimPopupPresenterMock.getSelectedPath()).thenReturn(null);
        when(kogitoScesimPopupPresenterMock.getSelectedType()).thenReturn(ScenarioSimulationModel.Type.DMN);
        abstractScenarioSimulationEditorKogitoScreenSpy.newFile("fake/path/");
        verify(kogitoScesimPopupPresenterMock, times(1)).show(isA(String.class), createCommandCaptor.capture());
        createCommandCaptor.getValue().execute();
        verify(kogitoScesimPopupPresenterMock, times(1)).getSelectedType();
        verify(abstractScenarioSimulationEditorKogitoScreenSpy, times(1)).showPopover(eq("ERROR"), eq("Missing dmn path"));
        verify(scenarioSimulationBuilderMock, never()).populateScenarioSimulationModel(isA(ScenarioSimulationModel.class),
                                                                                       eq(ScenarioSimulationModel.Type.RULE),
                                                                                       eq(""),
                                                                                       remoteCallbackArgumentCaptor.capture());
    }

    @Test
    public void newFileRule() {
        when(kogitoScesimPopupPresenterMock.getSelectedType()).thenReturn(ScenarioSimulationModel.Type.RULE);
        abstractScenarioSimulationEditorKogitoScreenSpy.newFile("fake/path/");
        verify(kogitoScesimPopupPresenterMock, times(1)).show(isA(String.class), createCommandCaptor.capture());
        createCommandCaptor.getValue().execute();
        verify(kogitoScesimPopupPresenterMock, times(1)).getSelectedType();
        verify(scenarioSimulationBuilderMock, times(1)).populateScenarioSimulationModel(isA(ScenarioSimulationModel.class),
                                                                                                             eq(ScenarioSimulationModel.Type.RULE),
                                                                                                             eq(""),
                                                                                                             remoteCallbackArgumentCaptor.capture());
        remoteCallbackArgumentCaptor.getValue().callback("");
        verify(abstractScenarioSimulationEditorKogitoScreenSpy, times(1)).saveFile(pathArgumentCaptor.capture(), isA(String.class));
        verify(scenarioSimulationEditorKogitoWrapperMock, times(1)).gotoPath(eq(pathArgumentCaptor.getValue()));
        verify(scenarioSimulationEditorKogitoWrapperMock, times(1)).setContent(eq("fake/path/created.scesim"), isA(String.class));
        assertEquals("fake/path/created.scesim", pathArgumentCaptor.getValue().toURI());
        assertEquals("created.scesim", pathArgumentCaptor.getValue().getFileName());
    }

    @Test
    public void newFileDMN() {
        when(kogitoScesimPopupPresenterMock.getSelectedType()).thenReturn(ScenarioSimulationModel.Type.DMN);
        abstractScenarioSimulationEditorKogitoScreenSpy.newFile("fake/path/");
        verify(kogitoScesimPopupPresenterMock, times(1)).show(isA(String.class), createCommandCaptor.capture());
        createCommandCaptor.getValue().execute();
        verify(kogitoScesimPopupPresenterMock, times(1)).getSelectedType();
        verify(scenarioSimulationBuilderMock, times(1)).populateScenarioSimulationModel(isA(ScenarioSimulationModel.class),
                                                                                        eq(ScenarioSimulationModel.Type.DMN),
                                                                                        eq("selected"),
                                                                                        remoteCallbackArgumentCaptor.capture());
        remoteCallbackArgumentCaptor.getValue().callback("");
        verify(abstractScenarioSimulationEditorKogitoScreenSpy, times(1)).saveFile(pathArgumentCaptor.capture(), isA(String.class));
        verify(scenarioSimulationEditorKogitoWrapperMock, times(1)).gotoPath(eq(pathArgumentCaptor.getValue()));
        verify(scenarioSimulationEditorKogitoWrapperMock, times(1)).setContent(eq("fake/path/created.scesim"), isA(String.class));
        assertEquals("fake/path/created.scesim", pathArgumentCaptor.getValue().toURI());
        assertEquals("created.scesim", pathArgumentCaptor.getValue().getFileName());
    }
}
