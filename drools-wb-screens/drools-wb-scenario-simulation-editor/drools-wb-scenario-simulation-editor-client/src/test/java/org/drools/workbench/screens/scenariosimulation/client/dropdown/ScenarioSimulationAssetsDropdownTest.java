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
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import elemental2.dom.HTMLElement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.mvp.Command;

import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.DEFAULT_VALUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioSimulationAssetsDropdownTest extends AbstractScenarioSimulationAssetsDropdownTest {

    @Mock
    private ScenarioSimulationAssetsDropdownView viewMock;

    @Mock
    private ScenarioSimulationAssetsDropdownProvider dataProviderMock;

    @Mock
    private HTMLElement htmlElementMock;

    @Mock
    private Command onValueChangeHandlerMock;

    @Mock
    private KieAssetsDropdownItem kieAssetsDropdownItemMock;

    private ScenarioSimulationAssetsDropdown scenarioSimulationAssetsDropdown;

    private List<KieAssetsDropdownItem> assetList = IntStream.range(0, 3)
            .mapToObj(i -> new KieAssetsDropdownItem("File_" + i + ".txt", "", DEFAULT_VALUE, new HashMap<>()))
            .collect(Collectors.toList());

    @Before
    public void setup() {
        super.setup();
        when(viewMock.getElement()).thenReturn(htmlElementMock);
        when(viewMock.getValue()).thenReturn(DEFAULT_VALUE);
        scenarioSimulationAssetsDropdown = spy(new ScenarioSimulationAssetsDropdown(viewMock, dataProviderMock) {
            {
                onValueChangeHandler = onValueChangeHandlerMock;
                this.kieAssets.addAll(assetList);
            }
        });
    }

    @Test
    public void init() {
        scenarioSimulationAssetsDropdown.init();
        verify(viewMock, times(1)).init(eq(scenarioSimulationAssetsDropdown));
    }

    @Test
    public void loadAssets() {
        scenarioSimulationAssetsDropdown.loadAssets();
        verify(scenarioSimulationAssetsDropdown, times(1)).clear();
        verify(scenarioSimulationAssetsDropdown, times(1)).initializeDropdown();
    }

    @Test
    public void initialize() {
        scenarioSimulationAssetsDropdown.initialize();
        verify(viewMock, times(1)).refreshSelectPicker();
    }

    @Test
    public void clear() {
        scenarioSimulationAssetsDropdown.clear();
        verify(viewMock, times(1)).clear();
    }

    @Test
    public void getElement() {
        final HTMLElement retrieved = scenarioSimulationAssetsDropdown.getElement();
        verify(viewMock, times(1)).getElement();
        assertEquals(htmlElementMock, retrieved);
    }

    @Test
    public void getValue() {
        when(viewMock.getValue()).thenReturn(DEFAULT_VALUE);
        Optional<KieAssetsDropdownItem> retrieved = scenarioSimulationAssetsDropdown.getValue();
        assertNotNull(retrieved);
        assertTrue(retrieved.isPresent());
        verify(viewMock, times(1)).getValue();
        reset(viewMock);
        when(viewMock.getValue()).thenReturn("UNKNOWN");
        retrieved = scenarioSimulationAssetsDropdown.getValue();
        assertNotNull(retrieved);
        assertFalse(retrieved.isPresent());
    }

    @Test
    public void onValueChanged() {
        scenarioSimulationAssetsDropdown.onValueChanged();
        verify(onValueChangeHandlerMock, times(1)).execute();
    }

    @Test
    public void addValue() {
        scenarioSimulationAssetsDropdown.addValue(kieAssetsDropdownItemMock);
        verify(viewMock, times(1)).addValue(eq(kieAssetsDropdownItemMock));
    }

    @Test
    public void initializeDropdown() {
        scenarioSimulationAssetsDropdown.initializeDropdown();
        verify(dataProviderMock, times(1)).getItems(isA(Consumer.class));
    }
}