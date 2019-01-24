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
package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.Map;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class PropertyPresenterTest extends AbstractCollectionEditorTest {

    @Mock
    private PropertyView propertyViewMock;

    @Mock
    private PropertyViewImpl propertyViewImplMock;

    @Mock
    private LIElement liElementMock;

    @Mock
    private SpanElement spanElementMock;

    @Mock
    private SpanElement propertyTextAreaMock;

    @Mock
    private LIElement propertyFieldsMock;

    // FIXME - no need to mock map/list
    @Mock
    private Map<String, SpanElement> propertySpanElementMapMock;

    @Mock
    private Style styleMock;

    private PropertyPresenter propertyEditorPresenter;

    @Before
    public void setup() {
        when(liElementMock.getStyle()).thenReturn(styleMock);
        this.propertyEditorPresenter = spy(new PropertyPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.propertySpanElementMap = propertySpanElementMapMock;
            }
        });
    }

    @Test
    public void getPropertyValueContainsKey() {
        String testPropertyName = "TEST-PROPERTYNAME";
        propertySpanElementMapMock.put(testPropertyName, spanElementMock);
        //when(propertySpanElementMapMock.get(anyString())).thenReturn(mockPropertyEditorView);

    }

    @Test
    public void getPropertyFields() {
        when(viewsProviderMock.getPropertyEditorView()).thenReturn(propertyViewMock);
        when(propertyViewMock.getPropertyName()).thenReturn(spanElementMock);
//        when(propertyViewMock.getPropertyValue()).thenReturn(propertyTextAreaMock);
        when(propertyViewMock.getPropertyFields()).thenReturn(propertyFieldsMock);
        when(propertyViewImplMock.getPropertyFields()).thenReturn(liElementMock);
        LIElement propertyFields = propertyEditorPresenter.getPropertyFields("TEST-PROPERTYNAME", "TEST-PROPERTYVALUE", "TEST-NODEID");
        assertNotNull(propertyFields);
    }

    @Test
    public void onToggleRowExpansionTrue() {
//        propertyEditorPresenter.toggleRowExpansion(liElementMock, true);
//        verify(liElementMock, times(1)).addClassName(ConstantHolder.NODE_HIDDEN);
//        verify(styleMock, times(1)).setDisplay(Style.Display.NONE);
    }

    @Test
    public void onToggleRowExpansionFalse() {
//        propertyEditorPresenter.toggleRowExpansion(liElementMock, false);
//        verify(liElementMock, times(1)).removeClassName(ConstantHolder.NODE_HIDDEN);
//        verify(styleMock, times(1)).setDisplay(Style.Display.BLOCK);
    }
}
