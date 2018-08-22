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

import java.util.Map;

import com.google.gwt.dom.client.DivElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ListGroupItemPresenterTest {

    private ListGroupItemPresenter listGroupItemPresenter;

    @Mock
    private ListGroupItemView mockListGroupItemView;

    @Mock
    private DivElement mockDivElement;

    @Mock
    private Map<Integer, ListGroupItemView> mockListGroupItemViewMap;

    @Before
    public void setup() {
        when(mockListGroupItemView.getDivElement()).thenReturn(mockDivElement);
        this.listGroupItemPresenter = spy(new ListGroupItemPresenter() {
            {
                listGroupItemViewMap = mockListGroupItemViewMap;
            }

            @Override
            protected ListGroupItemView getListGroupItemView() {
                return mockListGroupItemView;
            }
        });
    }

    @Test
    public void getDivElement() {
        int id = 3;
        DivElement retrieved = listGroupItemPresenter.getDivElement(id);
        verify(listGroupItemPresenter, times(1)).getListGroupItemView();
        verify(mockListGroupItemView, times(1)).setId(eq(id));
        verify(mockListGroupItemView, times(1)).init(eq(listGroupItemPresenter));
        verify(mockListGroupItemViewMap, times(1)).put(eq(id), eq(mockListGroupItemView));
        assertNotNull(retrieved);
        assertEquals(mockDivElement, retrieved);
    }

    @Test
    public void toggleRowExpansion() {
        int id = 3;
        reset(mockListGroupItemViewMap);
        when(mockListGroupItemViewMap.containsKey(id)).thenReturn(true);
        when(mockListGroupItemViewMap.get(id)).thenReturn(mockListGroupItemView);
        listGroupItemPresenter.toggleRowExpansion(id, true);
        verify(mockListGroupItemViewMap, times(1)).get(eq(id));
        verify(mockListGroupItemView, times(1)).closeRow();
        reset(mockListGroupItemViewMap);
        when(mockListGroupItemViewMap.containsKey(id)).thenReturn(true);
        when(mockListGroupItemViewMap.get(id)).thenReturn(mockListGroupItemView);
        reset(mockListGroupItemView);
        listGroupItemPresenter.toggleRowExpansion(id, false);
        verify(mockListGroupItemViewMap, times(1)).get(eq(id));
        verify(mockListGroupItemView, times(1)).expandRow();
    }
}