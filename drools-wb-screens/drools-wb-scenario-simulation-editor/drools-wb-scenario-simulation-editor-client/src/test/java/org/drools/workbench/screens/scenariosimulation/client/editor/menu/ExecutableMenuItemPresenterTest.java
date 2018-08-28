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

package org.drools.workbench.screens.scenariosimulation.client.editor.menu;

import java.util.Map;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Command;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ExecutableMenuItemPresenterTest {

    @Mock
    private ExecutableMenuItemViewImpl mockExecutableMenuItemViewImpl;

    @Mock
    private LIElement mockLIElement;

    @Mock
    private Map<LIElement, Command> mockMenuItemsCommandMap;

    @Mock
    private Command mockCommand;

    private ExecutableMenuItemPresenter executableMenuItemPresenter;

    @Before
    public void setup() {
        when(mockExecutableMenuItemViewImpl.getLIElement()).thenReturn(mockLIElement);
        when(mockMenuItemsCommandMap.containsKey(mockLIElement)).thenReturn(true);
        when(mockMenuItemsCommandMap.get(mockLIElement)).thenReturn(mockCommand);

        this.executableMenuItemPresenter = spy(new ExecutableMenuItemPresenter() {
            {
                menuItemsCommandMap = mockMenuItemsCommandMap;
            }

            @Override
            protected ExecutableMenuItemView getMenuItemView() {
                return mockExecutableMenuItemViewImpl;
            }
        });
    }

    @Test
    public void onClickEvent() {
        ClickEvent mockClickEvent = mock(ClickEvent.class);
        executableMenuItemPresenter.onClickEvent(mockClickEvent);
        verify(mockClickEvent, times(1)).preventDefault();
        verify(mockClickEvent, times(1)).stopPropagation();
    }

    @Test
    public void executeCommand() {
        executableMenuItemPresenter.executeCommand(mockLIElement);
        verify(mockMenuItemsCommandMap, times(1)).containsKey(mockLIElement);
        verify(mockCommand, times(1)).execute();
    }

    @Test
    public void getLIElement() {
        LIElement liElement = executableMenuItemPresenter.getLIElement("TEST-ID", "TEST-LABEL", mockCommand);
        assertNotNull(liElement);
        verify(mockMenuItemsCommandMap, times(1)).put(liElement, mockCommand);
    }
}