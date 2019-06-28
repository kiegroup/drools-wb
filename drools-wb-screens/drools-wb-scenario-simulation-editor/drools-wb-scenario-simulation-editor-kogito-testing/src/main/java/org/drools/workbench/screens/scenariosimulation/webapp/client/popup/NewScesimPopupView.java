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
import org.jboss.errai.common.client.dom.MouseEvent;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.ForEvent;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;
import org.uberfire.mvp.Command;

@Dependent
@Templated
public class NewScesimPopupView extends AbstractScenarioPopupView implements NewScesimPopup {

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
        cancelButton.setText("CANCEL");
        divElement.appendChild(newScenarioSimulationDropdown.getElement());
        newScenarioSimulationDropdown.clear();
        newScenarioSimulationDropdown.init();
        newScenarioSimulationDropdown.initializeDropdown();
        newScenarioSimulationDropdown.registerOnChangeHandler(() -> {
            final Optional<KieAssetsDropdownItem> value = newScenarioSimulationDropdown.getValue();
            selectedPath = value.map(KieAssetsDropdownItem::getValue).orElse(null);
        });
        super.show(mainTitleText, "CREATE", okCommand);
    }

    @Override
    public ScenarioSimulationModel.Type getSelectedType() {
        return selectedType;
    }

    @Override
    public String getSelectedPath() {
        return selectedPath;
    }

    @EventHandler("dmn-button")
    public void onDmnClick(final @ForEvent("click") MouseEvent event) {
        if (dmnButton.checked) {
            selectedType = ScenarioSimulationModel.Type.DMN;
            divElement.removeAttribute("hidden");
        }
    }

    @EventHandler("rule-button")
    public void onRuleClick(final @ForEvent("click") MouseEvent event) {
        if (ruleButton.checked) {
            selectedType = ScenarioSimulationModel.Type.RULE;
            divElement.setAttribute("hidden", "");
        }
    }
}
