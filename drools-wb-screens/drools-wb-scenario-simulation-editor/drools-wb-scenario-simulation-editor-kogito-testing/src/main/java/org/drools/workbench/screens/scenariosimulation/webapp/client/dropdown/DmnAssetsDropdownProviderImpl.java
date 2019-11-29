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

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.jboss.errai.bus.client.api.messaging.Message;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.kogito.webapp.base.client.workarounds.TestingVFSService;
import org.uberfire.backend.vfs.Path;

import static org.drools.workbench.screens.scenariosimulation.webapp.client.editor.ScenarioSimulationEditorKogitoTestingScreen.DMN_PATH;

@Dependent
public class DmnAssetsDropdownProviderImpl implements ScenarioSimulationAssetsDropdownProvider {

    private static final String FILE_SUFFIX = "dmn";

    @Inject
    private TestingVFSService testingVFSService;

    @Override
    public void getItems(final RemoteCallback<List<Path>> callback, final ErrorCallback<Message> errorCallback) {
        testingVFSService.getItemsByPath(DMN_PATH, FILE_SUFFIX, callback, errorCallback);
    }
}
