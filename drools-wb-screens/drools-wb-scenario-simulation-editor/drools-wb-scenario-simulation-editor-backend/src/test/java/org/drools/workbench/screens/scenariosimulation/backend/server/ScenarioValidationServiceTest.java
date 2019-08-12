package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.util.Collections;
import java.util.List;

import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNModel;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.backend.vfs.Path;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioValidationServiceTest {

    @Mock
    private KieContainer kieContainerMock;

    @Mock
    private Path pathMock;

    @Test
    public void validateSimulationStructure() {
        Simulation simulation = new Simulation();

        ScenarioValidationService scenarioValidationServiceSpy = spy(new ScenarioValidationService() {
            @Override
            protected List<FactMappingValidationError> validateDMN(Simulation simulation, KieContainer kieContainer) {
                return Collections.emptyList();
            }

            @Override
            protected List<FactMappingValidationError> validateRULE(Simulation simulation, KieContainer kieContainer) {
                return Collections.emptyList();
            }

            @Override
            protected KieContainer getKieContainer(Path path) {
                return kieContainerMock;
            }
        });

        simulation.getSimulationDescriptor().setType(ScenarioSimulationModel.Type.DMN);
        scenarioValidationServiceSpy.validateSimulationStructure(simulation, pathMock);
        verify(scenarioValidationServiceSpy, timeout(1)).validateDMN(eq(simulation), eq(kieContainerMock));

        reset(scenarioValidationServiceSpy);

        simulation.getSimulationDescriptor().setType(ScenarioSimulationModel.Type.RULE);
        scenarioValidationServiceSpy.validateSimulationStructure(simulation, pathMock);
        verify(scenarioValidationServiceSpy, timeout(1)).validateRULE(eq(simulation), eq(kieContainerMock));

        simulation.getSimulationDescriptor().setType(null);
        assertThatThrownBy(() -> scenarioValidationServiceSpy.validateSimulationStructure(simulation, pathMock))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void validateDMN() {
        ScenarioValidationService scenarioValidationServiceSpy = spy(new ScenarioValidationService() {
            @Override
            protected DMNModel getDMNModel(KieContainer kieContainer, String dmnPath) {
                return null;
            }

            @Override
            protected KieContainer getKieContainer(Path path) {
                return kieContainerMock;
            }
        });

        // FIXME to implement
        // FIXME test with list
        // FIXME test simple type
    }

    @Test
    public void validateRULE() {
        ScenarioValidationService scenarioValidationServiceSpy = spy(new ScenarioValidationService() {
            @Override
            protected KieContainer getKieContainer(Path path) {
                return kieContainerMock;
            }
        });

        Simulation simpleTypeSimulation = new Simulation();
        simpleTypeSimulation.getSimulationDescriptor().addFactMapping(
                        FactIdentifier.create("mySimpleType", int.class.getCanonicalName()),
                        ExpressionIdentifier.create("value", FactMappingType.GIVEN));

        scenarioValidationServiceSpy.validateRULE(simpleTypeSimulation, kieContainerMock);

        // FIXME to implement
        // FIXME test with list
        // FIXME test simple type
    }
}