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

package org.drools.workbench.screens.scenariosimulation.client.popup;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.mvp.Command;

import static org.drools.workbench.screens.scenariosimulation.client.popup.ErrorReportPopupViewTest.APPLY_TEXT;
import static org.drools.workbench.screens.scenariosimulation.client.popup.ErrorReportPopupViewTest.ERROR_CONTENT_TEXT;
import static org.drools.workbench.screens.scenariosimulation.client.popup.ErrorReportPopupViewTest.ERROR_TITLE_TEXT;
import static org.drools.workbench.screens.scenariosimulation.client.popup.ErrorReportPopupViewTest.KEEP_TEXT;
import static org.drools.workbench.screens.scenariosimulation.client.popup.ErrorReportPopupViewTest.MX;
import static org.drools.workbench.screens.scenariosimulation.client.popup.ErrorReportPopupViewTest.MY;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class ErrorReportPopupPresenterTest {

    private ErrorReportPopupPresenter errorReportPopupPresenter;

    @Mock
    private ViewsProvider viewsProviderMock;

    @Mock
    private ErrorReportPopupView errorReportPopupViewMock;

    @Mock
    private Command applyCommandMock;

    @Mock
    private Command keepCommandMock;

    @Before
    public void setUp() {
        when(viewsProviderMock.getErrorReportPopupView()).thenReturn(errorReportPopupViewMock);
        errorReportPopupPresenter = spy(new ErrorReportPopupPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
            }
        });
    }

    @Test
    public void show() {
        errorReportPopupPresenter.show(ERROR_TITLE_TEXT, ERROR_CONTENT_TEXT, KEEP_TEXT, APPLY_TEXT, applyCommandMock, keepCommandMock, MX, MY, PopoverView.Position.RIGHT);
        verify(viewsProviderMock, times(1)).getErrorReportPopupView();
        verify(errorReportPopupViewMock, times(1)).show(eq(ERROR_TITLE_TEXT),
                                                        eq(ERROR_CONTENT_TEXT),
                                                        eq(KEEP_TEXT),
                                                        eq(APPLY_TEXT),
                                                        eq(applyCommandMock),
                                                        eq(keepCommandMock),
                                                        eq(MX),
                                                        eq(MY),
                                                        eq(PopoverView.Position.RIGHT));
    }

    @Test
    public void hide() {
        errorReportPopupPresenter.errorReportPopupView = errorReportPopupViewMock;
        errorReportPopupPresenter.hide();
        verify(errorReportPopupViewMock, times(1)).hide();
    }
}