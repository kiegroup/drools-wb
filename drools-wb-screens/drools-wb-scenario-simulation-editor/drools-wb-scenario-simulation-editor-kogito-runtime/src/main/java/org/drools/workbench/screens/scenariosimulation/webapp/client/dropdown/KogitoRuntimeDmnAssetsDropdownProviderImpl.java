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
package org.drools.workbench.screens.scenariosimulation.webapp.client.dropdown;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.kogito.webapp.base.client.workarounds.KogitoResourceContentService;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;

@Dependent
public class KogitoRuntimeDmnAssetsDropdownProviderImpl implements ScenarioKogitoCreationAssetsDropdownProvider {

    private static final String FILE_SUFFIX = "dmn";

    @Inject
    private KogitoResourceContentService resourceContentService;

    @Override
    public void getItems(Consumer<List<KieAssetsDropdownItem>> assetListConsumer) {
        getItems(response -> {
            List<KieAssetsDropdownItem> toAccept = response.stream()
                    .map(this::getKieAssetsDropdownItem)
                    .collect(Collectors.toList());
            assetListConsumer.accept(toAccept);
        }, (message, throwable) -> {
            GWT.log(message.toString(), throwable);
            return false;
        });
    }

    protected void getItems(final RemoteCallback<List<String>> callback, final ErrorCallback<Object> errorCallback) {
        resourceContentService.getFilteredItems(FILE_SUFFIX, callback, errorCallback);
    }

    protected KieAssetsDropdownItem getKieAssetsDropdownItem(final String asset) {
        return new KieAssetsDropdownItem(asset, "", asset, new HashMap<>());
    }
}
