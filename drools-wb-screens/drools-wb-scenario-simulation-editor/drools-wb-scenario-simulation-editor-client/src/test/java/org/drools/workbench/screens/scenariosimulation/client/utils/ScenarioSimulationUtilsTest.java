/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.scenariosimulation.client.utils;

import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioSimulationUtilsTest {

    @Mock
    private ScenarioHeaderTextBoxSingletonDOMElementFactory factory;

    @Test
    public void getColumnBuilder() {

        String alias = "Alias";
        String factName = "FactName";

        ScenarioSimulationBuilders.HeaderBuilder headerBuilderOther =
                ScenarioSimulationUtils.getHeaderBuilder(alias, factName, FactMappingType.OTHER.name(), FactMappingType.OTHER, factory);
        assertEquals(1, headerBuilderOther.build().size());
        assertEquals(FactMappingType.OTHER.name(), headerBuilderOther.columnGroup);
        assertNull(headerBuilderOther.nestedLevel);
        assertEquals(alias, headerBuilderOther.columnTitle);

        ScenarioSimulationBuilders.HeaderBuilder headerBuilderGiven =
                ScenarioSimulationUtils.getHeaderBuilder(alias, factName, FactMappingType.GIVEN.name(), FactMappingType.GIVEN, factory);
        assertEquals(2, headerBuilderGiven.build().size());
        assertEquals("", headerBuilderGiven.columnGroup);
        assertEquals(FactMappingType.GIVEN.name(), headerBuilderGiven.columnTitle);
        assertEquals(alias, headerBuilderGiven.nestedLevel.columnTitle);
        assertEquals(FactMappingType.GIVEN.name(), headerBuilderGiven.nestedLevel.columnGroup);
    }
}