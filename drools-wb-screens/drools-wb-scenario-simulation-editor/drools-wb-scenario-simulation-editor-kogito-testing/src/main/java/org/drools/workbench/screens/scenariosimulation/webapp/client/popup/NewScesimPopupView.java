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
package org.drools.workbench.screens.scenariosimulation.webapp.client.popup;

import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import elemental2.dom.Event;
import elemental2.dom.EventListener;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.InputEvent;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.client.popup.AbstractScenarioPopupView;
import org.drools.workbench.screens.scenariosimulation.webapp.client.dropdown.NewScenarioSimulationDropdown;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;
import org.uberfire.mvp.Command;

@Dependent
public class NewScesimPopupView extends AbstractScenarioPopupView implements NewScesimPopup,
                                                                             EventListener {

    @Inject
    @DataField("rule-button")
    protected HTMLInputElement ruleButton;

    @Inject
    @DataField("dmn-button")
    protected HTMLInputElement dmnButton;

    @Inject
    @DataField("dmn-assets")
    protected HTMLDivElement divElement;

    protected ScenarioSimulationModel.Type selectedType = null;

    protected String selectedPath = null;

    @Inject
    protected NewScenarioSimulationDropdown newScenarioSimulationDropdown;

    @Override
    public void show(String mainTitleText, Command okCommand) {
        super.show(mainTitleText, "CREATE", okCommand);
        cancelButton.setText("CANCEL");
        divElement.appendChild(newScenarioSimulationDropdown.getElement());
        newScenarioSimulationDropdown.init();
        newScenarioSimulationDropdown.registerOnChangeHandler(() -> {
            final Optional<KieAssetsDropdownItem> value = newScenarioSimulationDropdown.getValue();
            selectedPath = value.map(KieAssetsDropdownItem::getValue).orElse(null);
        });
        ruleButton.addEventListener("InputEvent", this);
    }

    @Override
    public ScenarioSimulationModel.Type getSelectedType() {
        return selectedType;
    }

    @Override
    public String getSelectedPath() {
        return selectedPath;
    }

    @Override
    public void handleEvent(Event evt) {
        GWT.log("handleEvent " + evt);
        if (evt instanceof InputEvent) {
            GWT.log("InputEvent " + evt);
            boolean hideDMNDropdown = true;
            if (ruleButton.checked) {
                selectedType = ScenarioSimulationModel.Type.RULE;
            } else if (dmnButton.checked) {
                selectedType = ScenarioSimulationModel.Type.DMN;
                hideDMNDropdown = false;
                newScenarioSimulationDropdown.init();
            } else {
                selectedType = null;
            }
            divElement.setAttribute("hidden", hideDMNDropdown);
        }
    }
}
