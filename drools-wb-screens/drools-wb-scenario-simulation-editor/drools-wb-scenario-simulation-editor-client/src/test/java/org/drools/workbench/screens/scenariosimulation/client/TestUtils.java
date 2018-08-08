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
package org.drools.workbench.screens.scenariosimulation.client;

import java.util.stream.IntStream;

import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;

/**
 * Class used to provide common methods used by different classes
 */
public class TestUtils {

    public static final int NUMBER_OF_ROWS = 3;
    public static final int NUMBER_OF_COLUMNS = 3;

    public static Simulation getSimulation() {
        Simulation simulation = new Simulation();
        SimulationDescriptor simulationDescriptor = simulation.getSimulationDescriptor();
        simulationDescriptor.addFactMapping(FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);
        // generate simulationDescriptor
        IntStream.range(0, NUMBER_OF_COLUMNS).forEach(columnIndex -> {
            simulationDescriptor.addFactMapping(FactIdentifier.create("GROUP_COL-" + columnIndex, String.class.getCanonicalName()), ExpressionIdentifier.create("COL-" + columnIndex, FactMappingType.EXPECTED)
            );
        });
        // generate scenarios
        IntStream.range(0, NUMBER_OF_ROWS).forEach(rowIndex -> {
            final Scenario scenario = simulation.addScenario();
            scenario.setDescription("ROW-" + rowIndex);
            IntStream.range(0, NUMBER_OF_COLUMNS).forEach( columnIndex -> {
                scenario.addMappingValue(FactIdentifier.create("GROUP_COL-" + columnIndex, String.class.getCanonicalName()),
                                         ExpressionIdentifier.create("COL-" + columnIndex, FactMappingType.EXPECTED),
                                         "VAL_COL-" + columnIndex + "-ROW-" + rowIndex);
            });
        });
        return simulation;
    }
}
