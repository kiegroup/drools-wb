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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.events.CloseCompositeEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class CollectionViewImplTest extends AbstractCollectionEditorTest {

    private CollectionViewImpl collectionEditorViewImpl;

    @Before
    public void setup() {
        this.collectionEditorViewImpl = spy(new CollectionViewImpl() {
            {
                this.presenter = collectionPresenterMock;
            }
        });
    }

    @Test
    public void setValue() {
        String testValue = "TEST-JSON";
        collectionEditorViewImpl.setValue(testValue);
        verify(collectionPresenterMock, times(1)).setValue(eq(testValue));
    }

    @Test
    public void onCloseCollectionEditorButtonClick() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        collectionEditorViewImpl.onCloseCollectionEditorButtonClick(clickEventMock);
        verify(collectionEditorViewImpl, times(1)).fireEvent(isA(CloseCompositeEvent.class));
    }
}
