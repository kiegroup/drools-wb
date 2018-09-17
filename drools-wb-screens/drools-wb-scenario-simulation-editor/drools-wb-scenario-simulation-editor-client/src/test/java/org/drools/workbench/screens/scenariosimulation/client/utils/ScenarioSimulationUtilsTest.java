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

import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
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
    private ScenarioTextBoxSingletonDOMElementFactory factory;

    @Test
    public void getColumnBuilder() {

        String alias = "Alias";
        String factName = "FactName";
        String expressionName = "ExpressionName";

        FactMapping factOther = testFactMapping(alias, factName, expressionName, FactMappingType.OTHER);
        ScenarioSimulationUtils.ColumnBuilder columnBuilderOther = ScenarioSimulationUtils.getColumnBuilder(factOther);
        assertEquals(1, columnBuilderOther.build(factory).size());
        assertEquals(FactMappingType.OTHER.name(), columnBuilderOther.columnGroup);
        assertNull(columnBuilderOther.nestedLevel);
        assertEquals(alias, columnBuilderOther.columnTitle);

        FactMapping factGiven = testFactMapping(alias, factName, expressionName, FactMappingType.GIVEN);
        ScenarioSimulationUtils.ColumnBuilder columnBuilderGiven = ScenarioSimulationUtils.getColumnBuilder(factGiven);
        assertEquals(2, columnBuilderGiven.build(factory).size());
        assertEquals("", columnBuilderGiven.columnGroup);
        assertEquals(alias, columnBuilderGiven.columnTitle);
        assertEquals(alias, columnBuilderGiven.nestedLevel.columnTitle);
        assertEquals(FactMappingType.GIVEN.name(), columnBuilderGiven.nestedLevel.columnGroup);
    }

    private FactMapping testFactMapping(String expressionAlias, String factName, String expressionName, FactMappingType type) {
        return new FactMapping(expressionAlias, FactIdentifier.create(factName, String.class.getCanonicalName()), ExpressionIdentifier.create(expressionName, type));
    }
}