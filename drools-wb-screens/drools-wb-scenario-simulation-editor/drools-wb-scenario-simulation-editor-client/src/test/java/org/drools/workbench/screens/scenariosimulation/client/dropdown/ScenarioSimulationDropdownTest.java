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
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import elemental2.dom.HTMLElement;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.mvp.Command;

import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.DEFAULT_VALUE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioSimulationDropdownTest extends AbstractScenarioSimulationDropdownTest {

    @Mock
    private ScenarioSimulationDropdown.View viewMock;

    @Mock
    private ScenarioSimulationAssetsDropdownProvider dataProviderMock;

    @Mock
    private HTMLElement htmlElementMock;

    @Mock
    private Command onValueChangeHandlerMock;

    private ScenarioSimulationDropdown scenarioSimulationDropdown;

    private List<String> assetList = IntStream.range(0, 3)
            .mapToObj(i -> "File_" + i + ".txt")
            .collect(Collectors.toList());

    @Before
    public void setup() {
        super.setup();
        when(viewMock.getElement()).thenReturn(htmlElementMock);
        when(viewMock.getValue()).thenReturn(DEFAULT_VALUE);
        scenarioSimulationDropdown = spy(new ScenarioSimulationDropdown(viewMock, dataProviderMock) {
            {
                onValueChangeHandler = onValueChangeHandlerMock;
            }
        });
    }

    @Test
    public void init() {
        scenarioSimulationDropdown.init();
        verify(viewMock, times(1)).init(eq(scenarioSimulationDropdown));
    }

    @Test
    public void registerOnChangeHandler() {
        Command onChangeHandlerMock = mock(Command.class);
        scenarioSimulationDropdown.registerOnChangeHandler(onChangeHandlerMock);
        assertEquals(scenarioSimulationDropdown.onValueChangeHandler, onChangeHandlerMock);
    }

    @Test
    public void loadAssets() {
        scenarioSimulationDropdown.loadAssets();
        verify(scenarioSimulationDropdown, times(1)).clear();
        verify(scenarioSimulationDropdown, times(1)).initializeDropdown();
    }

    @Test
    public void initialize() {
        scenarioSimulationDropdown.initialize();
        verify(viewMock, times(1)).refreshSelectPicker();
    }

    @Test
    public void clear() {
        scenarioSimulationDropdown.clear();
        verify(viewMock, times(1)).clear();
    }

    @Test
    public void getElement() {
        final HTMLElement retrieved = scenarioSimulationDropdown.getElement();
        verify(viewMock, times(1)).getElement();
        assertEquals(htmlElementMock, retrieved);
    }

    @Test
    public void getValue() {
        final String retrieved = scenarioSimulationDropdown.getValue();
        verify(viewMock, times(1)).getValue();
        assertEquals(DEFAULT_VALUE, retrieved);
    }

    @Test
    public void assetListConsumerMethod() {
        scenarioSimulationDropdown.assetListConsumerMethod(assetList);
        assetList.forEach(asset ->
                                  verify(scenarioSimulationDropdown, times(1)).addValue(eq(asset)));
        verify(viewMock, times(1)).refreshSelectPicker();
        verify(viewMock, times(1)).initialize();
    }

    @Test
    public void onValueChanged() {
        scenarioSimulationDropdown.onValueChanged();
        verify(onValueChangeHandlerMock, times(1)).execute();
    }

    @Test
    public void initializeDropdown() {
        scenarioSimulationDropdown.initializeDropdown();
        verify(viewMock, times(1)).enableDropdownMode();
        verify(scenarioSimulationDropdown, times(1)).getAssetListConsumer();
        verify(dataProviderMock, times(1)).getItems(isA(Consumer.class));
    }

    @Test
    public void addValue() {
        scenarioSimulationDropdown.addValue(DEFAULT_VALUE);
        verify(viewMock, times(1)).addValue(eq(DEFAULT_VALUE));
    }
}