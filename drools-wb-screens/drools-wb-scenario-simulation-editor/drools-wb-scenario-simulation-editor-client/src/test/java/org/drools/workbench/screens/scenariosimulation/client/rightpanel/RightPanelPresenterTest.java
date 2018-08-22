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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import com.google.gwt.dom.client.DivElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class RightPanelPresenterTest {

    private RightPanelPresenter rightPanelPresenter;

    @Mock
    private RightPanelView mockRightPanelView;

    @Mock
    private DivElement mockListContainer;

    @Mock
    private ListGroupItemPresenter mockListGroupItemPresenter;

    @Before
    public void setup() {
        when(mockRightPanelView.getListContainer()).thenReturn(mockListContainer);
        this.rightPanelPresenter = new RightPanelPresenter(mockRightPanelView, mockListGroupItemPresenter);
    }

    @Test
    public void onSetup() {
        rightPanelPresenter.setup();
        verify(mockRightPanelView, times(1)).init(rightPanelPresenter);
    }

    @Test
    public void getTitle() {
        assertEquals(ScenarioSimulationEditorConstants.INSTANCE.testTools(), rightPanelPresenter.getTitle());
    }

    @Test
    public void clearSearch() {
        rightPanelPresenter.clearSearch();
        verify(mockRightPanelView, times(1)).clearInputSearch();
        verify(mockRightPanelView, times(1)).hideClearButton();
    }

    @Test
    public void showClearButton() {
        rightPanelPresenter.showClearButton();
        verify(mockRightPanelView, times(1)).showClearButton();
    }

    @Test
    public void addListGroupItemView() {
        int id = 3;
        rightPanelPresenter.addListGroupItemView(id);
        verify(mockRightPanelView, times(1)).getListContainer();
        verify(mockListGroupItemPresenter, times(1)).getDivElement(eq(id));
        verify(mockListContainer, times(1)).appendChild(anyObject());
    }
}