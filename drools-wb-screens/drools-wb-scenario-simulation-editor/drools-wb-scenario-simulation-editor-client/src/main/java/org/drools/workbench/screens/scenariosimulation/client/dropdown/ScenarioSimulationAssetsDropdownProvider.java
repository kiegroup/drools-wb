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

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationService;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItemsProvider;

@Dependent
public class ScenarioSimulationAssetsDropdownProvider implements KieAssetsDropdownItemsProvider {

    protected Caller<ScenarioSimulationService> scenarioSimulationService;

    @Inject
    public ScenarioSimulationAssetsDropdownProvider(Caller<ScenarioSimulationService> scenarioSimulationService) {
        super();
        this.scenarioSimulationService = scenarioSimulationService;
    }

    @Override
    public void getItems(Consumer<List<KieAssetsDropdownItem>> assetListConsumer) {
        updateAssets(response -> {
            final List<KieAssetsDropdownItem> kieAssetsDropdownItems = response.stream().map(item -> new KieAssetsDropdownItem(item,
                                                                                                                "",
                                                                                                                item,
                                                                                                                new HashMap<>())).collect(Collectors.toList());
            assetListConsumer.accept(kieAssetsDropdownItems);
        });
    }

    protected void updateAssets(RemoteCallback<List<String>> callback) {
        try {
            scenarioSimulationService.call(callback).getAssets(".", "dmn", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
