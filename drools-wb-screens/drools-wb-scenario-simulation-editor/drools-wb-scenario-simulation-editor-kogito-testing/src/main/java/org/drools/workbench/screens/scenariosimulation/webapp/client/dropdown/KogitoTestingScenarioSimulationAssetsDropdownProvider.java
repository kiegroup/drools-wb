/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

import com.google.gwt.core.client.GWT;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItemsProvider;
import org.uberfire.backend.vfs.Path;

/**
 * Using this to specialize injected instance
 */
public interface KogitoTestingScenarioSimulationAssetsDropdownProvider extends KieAssetsDropdownItemsProvider {


    void getItems(final RemoteCallback<List<Path>> callback, final ErrorCallback<Message> errorCallback);

    @Override
    default void getItems(Consumer<List<KieAssetsDropdownItem>> assetListConsumer) {
        getItems(response -> {
            List<KieAssetsDropdownItem> toAccept = response.stream()
                    .map(this::getKieAssetsDropdownItem)
                    .collect(Collectors.toList());
            assetListConsumer.accept(toAccept);
        }, (message, throwable) -> {
            GWT.log(message.getCommandType() + " " + message.toString(), throwable);
            return false;
        });
    }

    default KieAssetsDropdownItem getKieAssetsDropdownItem(final Path asset) {
        return new KieAssetsDropdownItem(asset.getFileName(), "", asset.toURI(), new HashMap<>());
    }

}