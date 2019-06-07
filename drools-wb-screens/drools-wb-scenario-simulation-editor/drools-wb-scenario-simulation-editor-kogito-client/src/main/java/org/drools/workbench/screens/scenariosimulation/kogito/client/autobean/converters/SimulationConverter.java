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
package org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.converters;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.SimulationAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ScenarioKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.SimulationKM;

public class SimulationConverter extends AbstractAutoBeanConverter<SimulationKM, SimulationAutobeanFactory, Simulation> {

    protected SimulationDescriptorConverter simulationDescriptorConverter = new SimulationDescriptorConverter();
    protected ScenarioConverter scenarioConverter = new ScenarioConverter();

    public SimulationConverter() {
        factory = GWT.create(SimulationAutobeanFactory.class);
        clazz = SimulationKM.class;
    }

    @Override
    public Simulation toApiModel(SimulationKM kogitoModel) {
        List<Scenario> scenarios = kogitoModel.getScenarios().stream()
                .map(km -> scenarioConverter.toApiModel(km))
                .collect(Collectors.toList());
        Simulation toReturn = new Simulation();
        // TODO
        //toReturn.getSimulationDescriptor().
        scenarios.forEach(originalScenario ->
                          {
                              Scenario newScenario = toReturn.addScenario();
                              newScenario.setDescription(originalScenario.getDescription());
                              originalScenario.getUnmodifiableFactMappingValues().forEach(factMappingValue -> newScenario.addMappingValue(factMappingValue.getFactIdentifier(), factMappingValue.getExpressionIdentifier(), factMappingValue.getRawValue()));
                          });
        return toReturn;
    }

    @Override
    public SimulationKM toKogitoModel(Simulation apiModel) {
        List<ScenarioKM> scenarioKMs = apiModel.getUnmodifiableScenarios().stream()
                .map(bm -> scenarioConverter.toKogitoModel(bm))
                .collect(Collectors.toList());
        SimulationKM toReturn = makeKogitoModel();
        toReturn.setScenarios(scenarioKMs);
        toReturn.setSimulationDescriptor(simulationDescriptorConverter.toKogitoModel(apiModel.getSimulationDescriptor()));
        return toReturn;
    }
}
