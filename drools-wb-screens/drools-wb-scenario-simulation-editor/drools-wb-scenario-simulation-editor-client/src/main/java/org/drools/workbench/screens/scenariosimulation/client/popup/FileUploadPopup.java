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

import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.uberfire.mvp.Command;

public interface FileUploadPopup {

    interface Presenter {

        /**
         * Makes the <code>FileUploadPopup</code> visible with OK/Cancel buttons.
         *
         * @param currentPresenter
         *
         */
        void show(ScenarioSimulationEditorPresenter currentPresenter);

        /**
         * Makes this popup container(and the main content along with it) invisible. Has no effect if the popup is not
         * already showing.
         */
        void hide();
    }

    /**
     * Makes the <code>FileUploadPopup</code> visible with OK/Cancel buttons.
     *
     */
    void show(Command importCommand);

    HTMLElement getElement();

    String getFileName();

    /**
     * Makes this popup container(and the main content along with it) invisible. Has no effect if the popup is not
     * already showing.
     */
    void hide();
}
