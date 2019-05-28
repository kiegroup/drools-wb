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
package org.drools.workbench.screens.scenariosimulation.submarine.client.dropdown;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.dropdown.ScenarioSimulationAssetsDropdownProvider;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;

@Dependent
public class ScenarioSimulationDMNAssetsDropdownProviderSubmarineImpl implements ScenarioSimulationAssetsDropdownProvider {

    private static final List<String> packages = Arrays.asList("com", "com.test");

    @Override
    public void getItems(Consumer<List<KieAssetsDropdownItem>> assetListConsumer) {
        List<KieAssetsDropdownItem> toAccept = new ArrayList<>();
        packages.forEach(packageName -> IntStream.range(1, 3).forEach(i -> {
            String asset = packageName + ".dmn-model-" + i + ".dmn";
            toAccept.add(getKieAssetsDropdownItem(asset));
        }));
        assetListConsumer.accept(toAccept);
    }

    protected KieAssetsDropdownItem getKieAssetsDropdownItem(final String asset) {
        return new KieAssetsDropdownItem(asset, "", asset, new HashMap<>());
    }
}
