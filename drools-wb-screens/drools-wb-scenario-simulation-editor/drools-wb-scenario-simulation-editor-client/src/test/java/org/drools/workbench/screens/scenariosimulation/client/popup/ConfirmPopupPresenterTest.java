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

package org.drools.workbench.screens.scenariosimulation.client.popup;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.client.views.pfly.widgets.Button;
import org.uberfire.client.views.pfly.widgets.ConfirmPopup;
import org.uberfire.client.views.pfly.widgets.InlineNotification;
import org.uberfire.mvp.Command;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(LienzoMockitoTestRunner.class)
public class ConfirmPopupPresenterTest {

    @Mock
    ConfirmPopup confirmPopupMock;

    @Mock
    Command okCommandMock;

    private final String TITLE = "TITLE";
    private final String OK_BUTTON_TEXT = "OK_BUTTON_TEXT";
    private final String CONFIRM_MESSAGE = "CONFIRM_MESSAGE";
    private final String INLINE_NOTIFICATION_MESSAGE = "INLINE_NOTIFICATION_MESSAGE";
    private final Button.ButtonStyleType BUTTON_STYLE_TYPE = Button.ButtonStyleType.SUCCESS;
    private final InlineNotification.InlineNotificationType INLINE_NOTIFICATION_TYPE = InlineNotification.InlineNotificationType.SUCCESS;


    private ConfirmPopupPresenter confirmPopupPresenter;

    @Before
    public void setup() {
        confirmPopupPresenter = spy(new ConfirmPopupPresenter() {
            {
                this.confirmPopup = confirmPopupMock;
            }
        });
    }

    @Test
    public void show() {
        confirmPopupPresenter.show(TITLE, OK_BUTTON_TEXT, CONFIRM_MESSAGE, okCommandMock);
        verify(confirmPopupPresenter, times(1)).show(TITLE, null,null, OK_BUTTON_TEXT, Button.ButtonStyleType.DANGER, CONFIRM_MESSAGE, okCommandMock);
    }

    @Test
    public void show1() {
        confirmPopupPresenter.show(TITLE, INLINE_NOTIFICATION_MESSAGE, INLINE_NOTIFICATION_TYPE, OK_BUTTON_TEXT, BUTTON_STYLE_TYPE, CONFIRM_MESSAGE, okCommandMock);
        verify(confirmPopupMock, times(1)).show(eq(TITLE), eq(INLINE_NOTIFICATION_MESSAGE), eq(INLINE_NOTIFICATION_TYPE), eq(OK_BUTTON_TEXT), eq(BUTTON_STYLE_TYPE), eq(CONFIRM_MESSAGE), eq(okCommandMock));
    }

    @Test
    public void hide() {
        confirmPopupPresenter.hide();
        verify(confirmPopupMock, times(1)).hide();
    }
}