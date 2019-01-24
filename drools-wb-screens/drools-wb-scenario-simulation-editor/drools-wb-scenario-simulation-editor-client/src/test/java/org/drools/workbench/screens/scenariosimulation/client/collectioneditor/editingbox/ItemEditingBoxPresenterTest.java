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
package org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.CollectionView;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.PropertyPresenter;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ItemEditingBoxPresenterTest {

    private static final String TEST_KEY = "TEST-CLASSNAME#TEST-PROPERTYNAME";
    private static final String TEST_PROPERTYNAME = "TEST-PROPERTYNAME";

    private Map<String, String> testInstancePropertyMap = Collections.singletonMap("TEST-KEY", "TEST-VALUE");
    private Map<String, String> testPropertiesValues = new HashMap<>();

    @Mock
    private PropertyPresenter propertyPresenterMock;

    @Mock
    private CollectionView.Presenter collectionViewPresenterMock;

    @Mock
    private ViewsProvider viewsProviderMock;

    @Mock
    private LIElement editingBoxMock;

    @Mock LIElement propertyFieldsMock;

    @Mock
    private ItemEditingBox itemEditingBoxMock;

    @Mock
    private HeadingElement editingBoxTitleMock;

    @Mock
    private UListElement propertiesContainerMock;

    private ItemEditingBoxPresenter itemEditingBoxPresenter;

    @Before
    public void setup() {
        when(viewsProviderMock.getItemEditingBox()).thenReturn(itemEditingBoxMock);
        when(itemEditingBoxMock.getEditingBoxTitle()).thenReturn(editingBoxTitleMock);
        when(itemEditingBoxMock.getPropertiesContainer()).thenReturn(propertiesContainerMock);
        when(propertyPresenterMock.getEditingPropertyFields(anyString(), anyString(), anyString())).thenReturn(propertyFieldsMock);
        when(itemEditingBoxMock.getEditingBox()).thenReturn(editingBoxMock);
        this.itemEditingBoxPresenter = spy(new ItemEditingBoxPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.propertyPresenter = propertyPresenterMock;
                this.collectionEditorPresenter = collectionViewPresenterMock;
            }
        });
    }

    @Test
    public void getEditingBox() {
        editingBoxMock = itemEditingBoxPresenter.getEditingBox(TEST_KEY, testInstancePropertyMap);
        verify(viewsProviderMock, times(1)).getItemEditingBox();
        verify(itemEditingBoxMock, times(1)).init(itemEditingBoxPresenter);
        verify(itemEditingBoxMock, times(1)).setKey(TEST_KEY);
        verify(editingBoxTitleMock, times(1)).setInnerText("Edit " + TEST_PROPERTYNAME);
        verify(propertiesContainerMock, times(1)).appendChild(propertyFieldsMock);
        verify(itemEditingBoxMock, times(1)).getEditingBox();
        assertNotNull(editingBoxMock);
    }

    @Test
    public void save() {
        itemEditingBoxPresenter.save();
        verify(propertyPresenterMock, times(1)).updateProperties("value");
        verify(collectionViewPresenterMock, times(1)).addListItem(anyMap());
    }

    @Test
    public void close() {
        itemEditingBoxPresenter.close(itemEditingBoxMock);
        verify(editingBoxMock, times(1)).removeFromParent();
    }


}
