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
import org.uberfire.mvp.Command;

@Dependent
public class SettingsScenarioSimulationDropdown extends AbstractKieAssetsDropdown {

    protected String value;
    protected Command onMissingValueHandler = () -> {/*Nothing/*/};

    @Inject
    public SettingsScenarioSimulationDropdown(SettingsScenarioSimulationDropdownView view,
                                              ScenarioSimulationAssetsDropdownProvider dataProvider) {
        super(view, dataProvider);
    }

    public void loadAssets(String value) {
        this.value = value;
        super.loadAssets();
    }

    public IsWidget asWidget() {
        return ((SettingsScenarioSimulationDropdownView) view).asWidget();
    }

    public void registerOnMissingValueHandler(final Command onMissingValueHandler) {
        this.onMissingValueHandler = onMissingValueHandler;
    }

    @Override
    protected void assetListConsumerMethod(final List<KieAssetsDropdownItem> assetList) {
        assetList.forEach(this::addValue);
        view.refreshSelectPicker();
        if (isValuePresentInKieAssets(value)) {
            ((SettingsScenarioSimulationDropdownView) view).initialize(value);
        } else {
            view.initialize();
            onMissingValueHandler.execute();
        }
        value = null;
    }

    protected boolean isValuePresentInKieAssets(String value) {
        return kieAssets.stream().anyMatch(asset -> asset.getValue().equals(value));
    }
}