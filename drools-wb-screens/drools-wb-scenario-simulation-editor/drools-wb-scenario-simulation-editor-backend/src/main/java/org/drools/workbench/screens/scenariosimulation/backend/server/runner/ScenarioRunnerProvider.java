package org.drools.workbench.screens.scenariosimulation.backend.server.runner;

import java.util.Map;

import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;
import org.kie.api.runtime.KieContainer;

@FunctionalInterface
public interface ScenarioRunnerProvider {

    AbstractScenarioRunner create(KieContainer kieContainer,
                                  SimulationDescriptor simulationDescriptor,
                                  Map<Integer, Scenario> scenarioMap);
}
