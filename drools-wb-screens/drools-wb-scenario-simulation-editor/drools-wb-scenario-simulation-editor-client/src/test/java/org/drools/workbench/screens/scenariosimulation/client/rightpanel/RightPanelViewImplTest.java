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

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class RightPanelViewImplTest {

    private RightPanelViewImpl rightPanelView;

    @Mock
    private RightPanelPresenter rightPanelPresenterMock;

    @Mock
    private InputElement inputSearchMock;

    @Mock
    private InputElement nameFieldMock;

    @Mock
    private ButtonElement clearSearchButtonMock;

    @Mock
    private ButtonElement addButtonMock;

    @Before
    public void setup() {
        this.rightPanelView = spy(new RightPanelViewImpl() {
            {
                this.inputSearch = inputSearchMock;
                this.clearSearchButton = clearSearchButtonMock;
                this.nameField = nameFieldMock;
                this.addButton = addButtonMock;
            }
        });
        rightPanelView.init(rightPanelPresenterMock);
    }

    @Test
    public void onClearSearchButtonClick() {
        reset(rightPanelPresenterMock);
        rightPanelView.onClearSearchButtonClick(mock(ClickEvent.class));
        verify(rightPanelPresenterMock, times(1)).onClearSearch();
    }

    @Test
    public void onInputSearchKeyUpNotEnter() {
        KeyUpEvent eventMock = mock(KeyUpEvent.class);
        when(eventMock.getNativeKeyCode()).thenReturn(KeyCodes.KEY_SPACE);
        rightPanelView.onInputSearchKeyUp(eventMock);
        verify(rightPanelPresenterMock, times(1)).onShowClearButton();
    }

    @Test
    public void onInputSearchKeyUpEnter() {
        KeyUpEvent eventMock = mock(KeyUpEvent.class);
        when(eventMock.getNativeKeyCode()).thenReturn(KeyCodes.KEY_ENTER);
        rightPanelView.onInputSearchKeyUp(eventMock);
        verify(rightPanelPresenterMock, times(1)).onSearchedEvent(any());
        verify(eventMock, times(1)).stopPropagation();
    }

    public void onMainSearchFormKeyUpNotEnter() {
        KeyUpEvent eventMock = mock(KeyUpEvent.class);
        when(eventMock.getNativeKeyCode()).thenReturn(KeyCodes.KEY_SPACE);
        rightPanelView.onMainSearchFormKeyUp(eventMock);
        verify(rightPanelPresenterMock, never()).onSearchedEvent(any());
        verify(eventMock, times(1)).stopPropagation();
    }

    public void onMainSearchFormKeyUpEnter() {
        KeyUpEvent eventMock = mock(KeyUpEvent.class);
        when(eventMock.getNativeKeyCode()).thenReturn(KeyCodes.KEY_ENTER);
        rightPanelView.onMainSearchFormKeyUp(eventMock);
        verify(rightPanelPresenterMock, times(1)).onSearchedEvent(any());
        verify(eventMock, times(1)).stopPropagation();
    }

    public void onSearchButtonClicked() {
        ClickEvent eventMock = mock(ClickEvent.class);
        rightPanelView.onSearchButtonClicked(eventMock);
        verify(rightPanelPresenterMock, times(1)).onSearchedEvent(any());
    }

    public void onAddButtonClicked() {
        ClickEvent eventMock = mock(ClickEvent.class);
        rightPanelView.onAddButtonClicked(eventMock);
        verify(rightPanelPresenterMock, times(1)).onModifyColumn();
        verify(addButtonMock, times(1)).setDisabled(eq(true));
        verify(rightPanelPresenterMock, times(1)).onDisableEditorTab();

    }

    @Test
    public void clearInputSearch() {
        rightPanelView.clearInputSearch();
        verify(inputSearchMock, times(1)).setValue(eq(""));
    }

    @Test
    public void clearNameField() {
        rightPanelView.clearNameField();
        verify(nameFieldMock, times(1)).setValue(eq(""));
    }

    @Test
    public void hideClearButton() {
        reset(clearSearchButtonMock);
        rightPanelView.hideClearButton();
        verify(clearSearchButtonMock, times(1)).setDisabled(eq(true));
        verify(clearSearchButtonMock, times(1)).setAttribute(eq("style"), eq("display: none;"));
    }

    @Test
    public void showClearButton() {
        reset(clearSearchButtonMock);
        rightPanelView.showClearButton();
        verify(clearSearchButtonMock, times(1)).setDisabled(eq(false));
        verify(clearSearchButtonMock, times(1)).removeAttribute(eq("style"));
    }
}