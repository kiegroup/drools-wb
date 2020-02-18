/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.menu.FileMenuBuilder;
import org.mockito.Mock;
import org.uberfire.client.promise.Promises;
import org.uberfire.workbench.model.menu.Menus;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioSimulationEditorKogitoWrapperTest {

    @Mock
    private FileMenuBuilder fileMenuBuilderMock;
    @Mock
    private Menus menusMock;
    @Mock
    private Promises promisesMock;
    @Mock
    private ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenterMock;
    @Mock
    private ScenarioSimulationModel scenarioSimulationModel;

    private ScenarioSimulationEditorKogitoWrapper scenarioSimulationEditorKogitoWrapperSpy;

    @Before
    public void setup() {
        when(fileMenuBuilderMock.build()).thenReturn(menusMock);
        when(scenarioSimulationEditorPresenterMock.getModel()).thenReturn(scenarioSimulationModel);
        scenarioSimulationEditorKogitoWrapperSpy = spy(new ScenarioSimulationEditorKogitoWrapper() {
            {
                this.fileMenuBuilder = fileMenuBuilderMock;
                this.scenarioSimulationEditorPresenter = scenarioSimulationEditorPresenterMock;
                this.promises = promisesMock;

            }
        });
    }

    @Test
    public void buildMenuBar() {
        scenarioSimulationEditorKogitoWrapperSpy.buildMenuBar();
        verify(fileMenuBuilderMock, times(1)).build();


    }

    @Test
    public void getContent() {
        scenarioSimulationEditorKogitoWrapperSpy.getContent();
        verify(scenarioSimulationEditorPresenterMock, times(1)).getModel();
        verify(scenarioSimulationEditorKogitoWrapperSpy, times(1)).transform(eq(scenarioSimulationModel));
    }

    @Test
    public void onEditTabSelected() {
        scenarioSimulationEditorKogitoWrapperSpy.onEditTabSelected();
        verify(scenarioSimulationEditorPresenterMock, times(1)).onEditTabSelected();
    }

    /*
    @Test
    public void addBackgroundPage() {
        scenarioSimulationEditorKogitoWrapperSpy.addBackgroundPage(scenarioGridWidgetSpy);
        verify(multiPageEditorMock, times(1)).addPage(eq(BACKGROUND_TAB_INDEX), isA(PageImpl.class));
    }*/

}
