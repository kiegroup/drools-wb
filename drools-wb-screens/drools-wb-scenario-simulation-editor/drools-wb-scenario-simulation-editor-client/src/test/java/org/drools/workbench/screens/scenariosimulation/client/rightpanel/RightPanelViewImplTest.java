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
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class RightPanelViewImplTest {

    private RightPanelViewImpl rightPanelView;

    @Before
    public void setup() {
        this.rightPanelView = spy(new RightPanelViewImpl() {
            {
            }
        });
    }

    @Test
    public void onEditorTabClick() {
        rightPanelView.onEditorTabClick(mock(ClickEvent.class));
        verifyShowTab(rightPanelView.editorTab, rightPanelView.editorTabContent);
        verifyHideTab(rightPanelView.cheatSheetTab, rightPanelView.cheatSheetTabContent);
    }

    @Test
    public void onCheatSheetTabClick() {
        rightPanelView.onCheatSheetTabClick(mock(ClickEvent.class));
        verifyShowTab(rightPanelView.cheatSheetTab, rightPanelView.cheatSheetTabContent);
        verifyHideTab(rightPanelView.editorTab, rightPanelView.editorTabContent);
    }

    private void verifyShowTab(LIElement tab, DivElement content) {
        verify(rightPanelView, times(1)).showTab(tab, content);
    }

    private void verifyHideTab(LIElement tab, DivElement content) {
        verify(rightPanelView, times(1)).hideTab(tab, content);
    }
}