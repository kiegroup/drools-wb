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
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.common.client.dom.Div;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.client.views.pfly.widgets.Modal;
import org.uberfire.mvp.Command;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(LienzoMockitoTestRunner.class)
public class ErrorReportPopupViewTest {

    public final static String ERROR_TITLE_TEXT = "ERROR_TITLE_TEXT";
    public final static String ERROR_CONTENT_TEXT = "ERROR_CONTENT_TEXT";
    public final static String KEEP_TEXT = "KEEP_TEXT";
    public final static String APPLY_TEXT = "APPLY_TEXT";
    public final static int MX = 36;
    public final static int MY = 52;
    public final static String TOP_PX = MY + " px" ;
    public final static String LEFT_PX = MX + " px";

    private ErrorReportPopupView errorReportPopupView;

    @Mock
    private SpanElement errorpopupTitleMock;

    @Mock
    private Div errorContentMock;

    @Mock
    private ButtonElement keepButtonMock;

    @Mock
    private ButtonElement applyButtonMock;

    @Mock
    private Modal modalMock;

    @Mock
    private Command applyCommandMock;

    @Mock
    private Command keepCommandMock;

    @Before
    public void setup() {
        errorReportPopupView = spy(new ErrorReportPopupView() {
            {
                this.keepButton = keepButtonMock;
                this.applyButton = applyButtonMock;
                this.applyCommand = applyCommandMock;
                this.keepCommand = keepCommandMock;
                this.errorContent = errorContentMock;
            }
        });
    }

    @Test
    public void show() {
        errorReportPopupView.show(ERROR_TITLE_TEXT, ERROR_CONTENT_TEXT, KEEP_TEXT, APPLY_TEXT, applyCommandMock, keepCommandMock, MX, MY, PopoverView.Position.RIGHT);
        verify(errorContentMock, times(1)).setTextContent(eq(ERROR_CONTENT_TEXT));
        verify(keepButtonMock, times(1)).setInnerText(eq(KEEP_TEXT));
        verify(applyButtonMock, times(1)).setInnerText(eq(APPLY_TEXT));

        /* Abstract */


        /*
        verify(errorpopupTitleMock, times(1)).setInnerText(eq(ERROR_TITLE_TEXT));
        verify(errorContentMock, times(1)).setInnerText(eq(ERROR_CONTENT_TEXT));
        verify(keepButtonMock, times(1)).setInnerText(eq(KEEP_TEXT));
        verify(applyButtonMock, times(1)).setInnerText(eq(APPLY_TEXT));
        verify(modalMock, times(1)).getElement();
        verify(htmlElementMock, times(1)).getStyle();
        verify(modalStyleMock, times(1)).setProperty(eq(LEFT), eq(LEFT_PX));
        verify(modalStyleMock, times(1)).setProperty(eq(TOP), eq(TOP_PX));
        verify(modalMock, times(1)).show(); */
    }

    public void hide() {
        errorReportPopupView.hide();
    }

    @Test
    public void onKeepButtonClicked() {
        errorReportPopupView.onKeepButtonClicked(mock(ClickEvent.class));
        verify(keepCommandMock, times(1)).execute();
        verify(errorReportPopupView, times(1)).hide();
    }

    @Test
    public void onApplyButtonClicked() {
        errorReportPopupView.onApplyButtonClicked(mock(ClickEvent.class));
        verify(applyCommandMock, times(1)).execute();
        verify(errorReportPopupView, times(1)).hide();
    }
}