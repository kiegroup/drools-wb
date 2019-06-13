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
package org.drools.workbench.screens.scenariosimulation.client.dropdown;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import org.kie.workbench.common.widgets.client.assets.dropdown.AbstractKieAssetsDropdown;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;

@Dependent
public class ScenarioSimulationDropdown extends AbstractKieAssetsDropdown {

    String value = "";

    @Inject
    public ScenarioSimulationDropdown(ScenarioSimulationDropdownView view, ScenarioSimulationAssetsDropdownProvider dataProvider) {
        super(view, dataProvider);
    }

    public IsWidget asWidget() {
        return ((ScenarioSimulationDropdownView) view).asWidget();
    }

    public void selectValue(String value) {
       this.value = value;
    }

    @Override
    protected void assetListConsumerMethod(final List<KieAssetsDropdownItem> assetList) {
        assetList.forEach(this::addValue);
        view.refreshSelectPicker();
        ((ScenarioSimulationDropdownView) view).selectValue(value);
    }

}