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

package org.drools.workbench.screens.scenariosimulation.client.popup;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.mvp.Command;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class FileUploadPopupPresenterTest {

    @Mock
    private FileUploadPopupView fileUploadPopupViewMock;

    private FileUploadPopupPresenter fileUploadPopupPresenter;

    @Mock
    private ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenterMock;

    @Before
    public void setup() {
        fileUploadPopupPresenter = spy(new FileUploadPopupPresenter() {
            {
                this.fileUploadPopupView = fileUploadPopupViewMock;
            }
        });
    }

    @Test
    public void show() {
        Command importCommandMock = mock(Command.class);
        when(fileUploadPopupPresenter.getImportCommand(eq(scenarioSimulationEditorPresenterMock))).thenReturn(importCommandMock);
        fileUploadPopupPresenter.show(scenarioSimulationEditorPresenterMock);
        verify(fileUploadPopupPresenter, times(1)).getImportCommand(eq(scenarioSimulationEditorPresenterMock));
        verify(fileUploadPopupViewMock, times(1)).show(importCommandMock);
    }

    @Test
    public void hide() {
        fileUploadPopupPresenter.hide();
        verify(fileUploadPopupViewMock, times(1)).hide();
    }
}