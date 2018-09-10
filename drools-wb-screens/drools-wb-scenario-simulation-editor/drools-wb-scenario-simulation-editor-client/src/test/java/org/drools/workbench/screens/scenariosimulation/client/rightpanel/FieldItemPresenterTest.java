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

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class FieldItemPresenterTest extends AbstractRightPanelTest {

    @Mock
    protected FieldItemView mockFieldItemView;

    private FieldItemPresenter fieldItemPresenter;

    @Before
    public void setup() {
        super.setup();
        when(mockViewsProvider.getFieldItemView()).thenReturn(mockFieldItemView);
        when(mockFieldItemView.getLIElement()).thenReturn(mockLIElement);
        this.fieldItemPresenter = spy(new FieldItemPresenter() {
            {
                viewsProvider = mockViewsProvider;
            }
        });
    }

    @Test
    public void getLIElement() {
        fieldItemPresenter.getLIElement(FACT_NAME, FACT_NAME, FACT_MODEL_TREE.getFactName());
        verify(mockViewsProvider, times(1)).getFieldItemView();
        verify(mockFieldItemView, times(1)).setFieldData(eq(FACT_NAME), eq(FACT_NAME), eq(FACT_MODEL_TREE.getFactName()));
        verify(mockFieldItemView, times(1)).setPresenter(eq(fieldItemPresenter));
    }
}