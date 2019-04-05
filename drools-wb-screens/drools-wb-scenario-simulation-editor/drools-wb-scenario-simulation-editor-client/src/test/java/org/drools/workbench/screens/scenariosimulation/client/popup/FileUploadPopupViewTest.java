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
import org.junit.Before;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.spy;

@RunWith(LienzoMockitoTestRunner.class)
public class FileUploadPopupViewTest extends AbstractScenarioPopupViewTest {


    @Before
    public void setup() {
        super.commonSetup();
        popupView = spy(new FileUploadPopupView() {
            {
                this.mainTitle = mainTitleMock;
                this.cancelButton = cancelButtonMock;
                this.okButton = okButtonMock;
                this.modal = modalMock;
                this.translationService = translationServiceMock;
            }
        });
    }

}