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

package org.drools.workbench.screens.scenariosimulation.model;

import java.util.stream.IntStream;

import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.soup.project.datamodel.imports.HasImports;
import org.kie.soup.project.datamodel.imports.Imports;

@Portable
public class ScenarioSimulationModel
        implements HasImports {

    private Simulation simulation;

    private Imports imports = new Imports();

    public ScenarioSimulationModel() {
        simulation = new Simulation();
        SimulationDescriptor simulationDescriptor = simulation.getSimulationDescriptor();

        simulationDescriptor.addFactMapping(FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);

        ExpressionIdentifier givenExpression = ExpressionIdentifier.create("GIVEN", FactMappingType.GIVEN);
        ExpressionIdentifier expectedExpression = ExpressionIdentifier.create("EXPECTED", FactMappingType.EXPECTED);

        Scenario scenario = simulation.addScenario();
        scenario.setDescription("Scenario example");

        // Add GIVEN Facts
        IntStream.range(1, 3).forEach(id -> {
            FactIdentifier givenFact = FactIdentifier.create("GIVEN-" + id, String.class.getCanonicalName());
            simulationDescriptor.addFactMapping(givenFact, givenExpression);
            scenario.addMappingValue(givenFact, givenExpression, "given-sample-" + id);
        });

        // Add EXPECTED Facts
        IntStream.range(1, 3).forEach(id -> {
            FactIdentifier expectFact = FactIdentifier.create("EXPECTED-" + id, String.class.getCanonicalName());
            simulationDescriptor.addFactMapping(expectFact, expectedExpression);
            scenario.addMappingValue(expectFact, expectedExpression, "expected-sample-" + id);
        });
    }

    public ScenarioSimulationModel(Simulation simulation) {
        this.simulation = simulation;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public Imports getImports() {
        return imports;
    }

    @Override
    public void setImports(Imports imports) {
        this.imports = imports;
    }
}
