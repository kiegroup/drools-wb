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

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.uberfire.backend.server.util.Paths;
import org.uberfire.backend.vfs.Path;

@ApplicationScoped
public class DMNSimulationCreationStrategy implements SimulationCreationStrategy {

    @Inject
    protected DMNTypeService dmnTypeService;

    @Override
    public Simulation createSimulation(Path context, String dmnFilePath) throws Exception {
        final FactModelTuple factModelTuple = getFactModelTuple(context, dmnFilePath);
        Simulation toReturn = new Simulation();
        SimulationDescriptor simulationDescriptor = toReturn.getSimulationDescriptor();
        simulationDescriptor.setType(ScenarioSimulationModel.Type.DMN);
        simulationDescriptor.setDmnFilePath(dmnFilePath);
        Scenario scenario = createScenario(toReturn, simulationDescriptor);

        int row = toReturn.getUnmodifiableScenarios().indexOf(scenario);
        AtomicInteger id = new AtomicInteger(1);
        final Collection<FactModelTree> visibleFactTrees = factModelTuple.getVisibleFacts().values();

        final Map<FactModelTree.Type, List<FactModelTree>> groupedFactModelTrees = visibleFactTrees.stream()
                .collect(Collectors.groupingBy(FactModelTree::getType));
        // Add INPUT data
        groupedFactModelTrees.get(FactModelTree.Type.INPUT).forEach(factModelTree -> {
            addToScenario(row, id.getAndIncrement(), FactMappingType.GIVEN, factModelTree, simulationDescriptor, scenario);
        });
        // Add DECISION data
        groupedFactModelTrees.get(FactModelTree.Type.DECISION).forEach(factModelTree -> {
            addToScenario(row, id.getAndIncrement(), FactMappingType.EXPECT, factModelTree, simulationDescriptor, scenario);
        });
        return toReturn;
    }

    protected FactModelTuple getFactModelTuple(Path context, String dmnFilePath) throws Exception {
        final org.uberfire.java.nio.file.Path nioPath = Paths.convert(context).resolve(dmnFilePath);
        final Path path = Paths.convert(nioPath);
        return dmnTypeService.retrieveType(path, dmnFilePath);
    }

    private void addToScenario(int row, int id, FactMappingType type, FactModelTree factModelTree, SimulationDescriptor simulationDescriptor, Scenario scenario) {
        ExpressionIdentifier expressionIdentifier = ExpressionIdentifier.create(row + "|" + id, type);
        FactIdentifier factIdentifier = new FactIdentifier(factModelTree.getFactName(), factModelTree.getSimpleProperties().get("value"));
        final FactMapping factMapping = simulationDescriptor.addFactMapping(factModelTree.getFactName(), factIdentifier, expressionIdentifier);
        factMapping.setExpressionAlias(factModelTree.getFactName());
        scenario.addMappingValue(factIdentifier, expressionIdentifier, null);
    }
}
