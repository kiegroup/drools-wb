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
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ListGroupItemViewImplTest {

    @Mock
    private ListGroupItemPresenter mockListGroupItemPresenter;

    @Mock
    private DivElement mockListGroupItemHeader;

    @Mock
    private DivElement mockListGroupItemContainer;

    @Mock
    private SpanElement mockFaAngleRight;

    private ListGroupItemViewImpl listGroupItemView;

    private final int ID = 3;

    @Before
    public void setup() {
        this.listGroupItemView = spy(new ListGroupItemViewImpl() {
            {
                this.id = ID;
                this.listGroupItemHeader = mockListGroupItemHeader;
                this.listGroupItemContainer = mockListGroupItemContainer;
                this.faAngleRight = mockFaAngleRight;
            }
        });
        listGroupItemView.init(mockListGroupItemPresenter);
    }

    @Test()
    public void onListGroupItemHeaderClick() {
        when(mockListGroupItemHeader.getClassName()).thenReturn("list-group-item list-view-pf-expand-active");
        listGroupItemView.onListGroupItemHeaderClick(mock(ClickEvent.class));
        verify(mockListGroupItemPresenter, times(1)).toggleRowExpansion(eq(ID), eq(true));
        when(mockListGroupItemHeader.getClassName()).thenReturn("list-group-item");
        listGroupItemView.onListGroupItemHeaderClick(mock(ClickEvent.class));
        verify(mockListGroupItemPresenter, times(1)).toggleRowExpansion(eq(ID), eq(false));
    }

    @Test
    public void closeRow() {
        listGroupItemView.closeRow();
        verify(mockListGroupItemHeader, times(1)).removeClassName(eq("list-view-pf-expand-active"));
        verify(mockListGroupItemContainer, times(1)).addClassName(eq("hidden"));
        verify(mockFaAngleRight, times(1)).removeClassName(eq("fa-angle-down"));
    }

    @Test
    public void expandRow() {
        listGroupItemView.expandRow();
        verify(mockListGroupItemHeader, times(1)).addClassName(eq("list-view-pf-expand-active"));
        verify(mockListGroupItemContainer, times(1)).removeClassName(eq("hidden"));
        verify(mockFaAngleRight, times(1)).addClassName(eq("fa-angle-down"));
    }
}