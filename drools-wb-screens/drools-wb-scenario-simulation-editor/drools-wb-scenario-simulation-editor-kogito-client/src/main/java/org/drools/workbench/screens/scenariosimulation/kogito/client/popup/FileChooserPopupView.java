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

import javax.inject.Inject;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import org.drools.workbench.screens.scenariosimulation.client.popup.AbstractScenarioPopupView;
import org.drools.workbench.screens.scenariosimulation.kogito.client.dropdown.ScenarioSimulationScesimFilesDropdown;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;

@Templated
public class FileChooserPopupView extends AbstractScenarioPopupView implements FileChooserPopup {

    @DataField("filesDropDown")
    protected DivElement filesDropDown = Document.get().createDivElement();

    @Inject
    protected ScenarioSimulationScesimFilesDropdown scenarioSimulationScesimFilesDropdown;

    protected FileChooserPopup.Presenter presenter;

    @Override
    public void show(final String mainTitleText,
                     final String okButtonText,
                     final Command okCommand) {
//        scenarioSimulationScesimFilesDropdown.clear();
//        scenarioSimulationScesimFilesDropdown.initialize();
//        scenarioSimulationScesimFilesDropdown.initializeDropdown();
//        filesDropDown.appendChild(scenarioSimulationScesimFilesDropdown.asWidget().asWidget().getElement());
        super.show(mainTitleText,
                   okButtonText, okCommand);
    }

    @Override
    public void init(FileChooserPopup.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public String getFileName() {
        return null;
//        return scenarioSimulationScesimFilesDropdown.getValue().isPresent() ?scenarioSimulationScesimFilesDropdown.getValue().get().getValue() : null;
    }
}
