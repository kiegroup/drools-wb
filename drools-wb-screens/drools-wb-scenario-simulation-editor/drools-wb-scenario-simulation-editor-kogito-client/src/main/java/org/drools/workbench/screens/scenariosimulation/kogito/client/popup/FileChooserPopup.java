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
package org.drools.workbench.screens.scenariosimulation.kogito.client.popup;

import org.drools.workbench.screens.scenariosimulation.client.popup.AbstractScenarioPopup;
import org.uberfire.client.mvp.HasPresenter;
import org.uberfire.mvp.Command;

public interface FileChooserPopup extends AbstractScenarioPopup,
                                          HasPresenter<FileChooserPopup.Presenter> {

    /**
     * Makes this popup container(and the main content along with it) invisible. Has no effect if the popup is not
     * already showing.
     */
    void hide();

    String getFileName();

    interface Presenter extends AbstractScenarioPopup.Presenter {

        /**
         * Same as default show method but with list of accepted extensions
         * @param mainTitleText
         * @param okButtonText
         * @param okCommand
         */
        void show(final String mainTitleText,
                  final String okButtonText,
                  final Command okCommand);

        String getFileName();
    }
}