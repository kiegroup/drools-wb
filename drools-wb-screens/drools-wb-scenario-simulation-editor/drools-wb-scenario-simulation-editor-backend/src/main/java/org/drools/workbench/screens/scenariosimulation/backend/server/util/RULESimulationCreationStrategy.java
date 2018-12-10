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
package org.drools.workbench.screens.scenariosimulation.backend.server.util;

import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;

public class RULESimulationCreationStrategy implements SimulationCreationStrategy {

    @Override
    public Simulation createSimulation(String value) {
        Simulation toReturn = new Simulation();
        SimulationDescriptor simulationDescriptor = toReturn.getSimulationDescriptor();

        simulationDescriptor.addFactMapping(FactIdentifier.INDEX.getName(), FactIdentifier.INDEX, ExpressionIdentifier.INDEX);
        simulationDescriptor.addFactMapping(FactIdentifier.DESCRIPTION.getName(), FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);

        Scenario scenario = toReturn.addScenario();
        int row = toReturn.getUnmodifiableScenarios().indexOf(scenario);
        scenario.setDescription(null);

        // Add GIVEN Fact
        int id = 1;
        ExpressionIdentifier givenExpression = ExpressionIdentifier.create(row + "|" + id, FactMappingType.GIVEN);
        final FactMapping givenFactMapping = simulationDescriptor.addFactMapping(FactMapping.getInstancePlaceHolder(id), FactIdentifier.EMPTY, givenExpression);
        givenFactMapping.setExpressionAlias(FactMapping.getPropertyPlaceHolder(id));
        scenario.addMappingValue(FactIdentifier.EMPTY, givenExpression, null);

        // Add EXPECT Fact
        id = 2;
        ExpressionIdentifier expectedExpression = ExpressionIdentifier.create(row + "|" + id, FactMappingType.EXPECT);
        final FactMapping expectedFactMapping = simulationDescriptor.addFactMapping(FactMapping.getInstancePlaceHolder(id), FactIdentifier.EMPTY, expectedExpression);
        expectedFactMapping.setExpressionAlias(FactMapping.getPropertyPlaceHolder(id));
        scenario.addMappingValue(FactIdentifier.EMPTY, expectedExpression, null);
        return toReturn;
    }
}
