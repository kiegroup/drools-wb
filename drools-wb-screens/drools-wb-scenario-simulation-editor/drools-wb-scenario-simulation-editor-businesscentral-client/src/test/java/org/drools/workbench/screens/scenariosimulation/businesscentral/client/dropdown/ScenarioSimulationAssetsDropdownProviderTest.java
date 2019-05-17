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

package org.drools.workbench.screens.scenariosimulation.businesscentral.client.dropdown;

import java.util.List;
import java.util.function.Consumer;

import org.drools.workbench.screens.scenariosimulation.client.dropdown.AbstractScenarioSimulationDropdownTest;
import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationService;
import org.guvnor.common.services.project.model.WorkspaceProject;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.screens.library.api.AssetQueryResult;
import org.kie.workbench.common.screens.library.client.screens.assets.AssetQueryService;
import org.kie.workbench.common.screens.library.client.util.LibraryPlaces;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioSimulationAssetsDropdownProviderTest extends AbstractScenarioSimulationDropdownTest {

    @Mock
    private Caller<ScenarioSimulationService> scenarioSimulationServiceCallerMock;

    @Mock
    private ScenarioSimulationService scenarioSimulationServiceMock;

    @Mock
    private LibraryPlaces libraryPlacesMock;
    @Mock
    private  AssetQueryService assetQueryServiceMock;
    @Mock
    private  WorkspaceProject workspaceProjectMock;

    private ScenarioSimulationAssetsDropdownProviderBCImpl scenarioSimulationAssetsDropdownProvider;

    @Before
    public void setUp() throws Exception {
        super.setup();
        when(scenarioSimulationServiceCallerMock.call(any())).thenReturn(scenarioSimulationServiceMock);
        when(libraryPlacesMock.getActiveWorkspace()).thenReturn(workspaceProjectMock);
        scenarioSimulationAssetsDropdownProvider = spy(new ScenarioSimulationAssetsDropdownProviderBCImpl(scenarioSimulationServiceCallerMock, libraryPlacesMock, assetQueryServiceMock) {

        });
    }

    @Test
    public void getItems() {
        Consumer<List<KieAssetsDropdownItem>> assetListConsumerMock = mock(Consumer.class);
        scenarioSimulationAssetsDropdownProvider.getItems(assetListConsumerMock);
        verify(scenarioSimulationAssetsDropdownProvider, times(1)).updateAssets(isA(RemoteCallback.class));
    }

    @Test
    public void updateAssets() throws Exception {
        RemoteCallback<AssetQueryResult> remoteCallbackMock = mock(RemoteCallback.class);
        scenarioSimulationAssetsDropdownProvider.updateAssets(remoteCallbackMock);
        verify(scenarioSimulationServiceCallerMock, times(1)).call(eq(remoteCallbackMock));
        verify(scenarioSimulationServiceMock, times(1)).getAssets(eq("."), eq("dmn"), eq(""));
    }
}