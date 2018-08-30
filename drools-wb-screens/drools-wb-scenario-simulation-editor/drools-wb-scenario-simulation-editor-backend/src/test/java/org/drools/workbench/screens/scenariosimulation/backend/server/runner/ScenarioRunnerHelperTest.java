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

package org.drools.workbench.screens.scenariosimulation.backend.server.runner;

import java.util.List;

import org.drools.workbench.screens.scenariosimulation.backend.server.model.Dispute;
import org.drools.workbench.screens.scenariosimulation.backend.server.model.Person;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioInput;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioOutput;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioResult;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.junit.Before;
import org.junit.Test;

import static org.drools.workbench.screens.scenariosimulation.backend.server.runner.ScenarioRunnerHelper.extractExpectedValues;
import static org.drools.workbench.screens.scenariosimulation.backend.server.runner.ScenarioRunnerHelper.extractGivenValues;
import static org.drools.workbench.screens.scenariosimulation.backend.server.runner.ScenarioRunnerHelper.verifyConditions;
import static org.junit.Assert.assertEquals;

public class ScenarioRunnerHelperTest {

    private static final String NAME = "NAME";
    private static final double AMOUNT = 10;
    private Simulation simulation;
    private FactIdentifier personFactIdentifier;
    private ExpressionIdentifier firstNameGivenExpressionIdentifier;
    private FactMapping firstNameGivenFactMapping;
    private Scenario scenario1;
    private Scenario scenario2;
    private ExpressionIdentifier firstNameExpectedExpressionIdentifier;
    private FactMapping firstNameExpectedFactMapping;
    private FactIdentifier disputeFactIdentifier;
    private ExpressionIdentifier amountGivenExpressionIdentifier;
    private FactMapping amountNameGivenFactMapping;
    private ExpressionIdentifier amountExpectedExpressionIdentifier;
    private FactMapping amountNameExpectedFactMapping;

    @Before
    public void setup() {
        simulation = new Simulation();
        personFactIdentifier = FactIdentifier.create("Fact 1", Person.class.getCanonicalName());
        firstNameGivenExpressionIdentifier = ExpressionIdentifier.create("First Name Given", FactMappingType.GIVEN);
        firstNameGivenFactMapping = simulation.getSimulationDescriptor().addFactMapping(personFactIdentifier, firstNameGivenExpressionIdentifier);
        firstNameGivenFactMapping.addExpressionElement("firstName", String.class.getCanonicalName());

        disputeFactIdentifier = FactIdentifier.create("Fact 2", Dispute.class.getCanonicalName());
        amountGivenExpressionIdentifier = ExpressionIdentifier.create("Amount Given", FactMappingType.GIVEN);
        amountNameGivenFactMapping = simulation.getSimulationDescriptor().addFactMapping(disputeFactIdentifier, amountGivenExpressionIdentifier);
        amountNameGivenFactMapping.addExpressionElement("amount", Double.class.getCanonicalName());

        firstNameExpectedExpressionIdentifier = ExpressionIdentifier.create("First Name Expected", FactMappingType.EXPECTED);
        firstNameExpectedFactMapping = simulation.getSimulationDescriptor().addFactMapping(personFactIdentifier, firstNameExpectedExpressionIdentifier);
        firstNameExpectedFactMapping.addExpressionElement("firstName", String.class.getCanonicalName());

        amountExpectedExpressionIdentifier = ExpressionIdentifier.create("Amount Expected", FactMappingType.EXPECTED);
        amountNameExpectedFactMapping = simulation.getSimulationDescriptor().addFactMapping(disputeFactIdentifier, amountExpectedExpressionIdentifier);
        amountNameExpectedFactMapping.addExpressionElement("amount", Double.class.getCanonicalName());

        scenario1 = simulation.addScenario();
        scenario1.addMappingValue(personFactIdentifier, firstNameGivenExpressionIdentifier, NAME);
        scenario1.addMappingValue(personFactIdentifier, firstNameExpectedExpressionIdentifier, NAME);

        scenario2 = simulation.addScenario();
        scenario2.addMappingValue(personFactIdentifier, firstNameGivenExpressionIdentifier, NAME);
        scenario2.addMappingValue(personFactIdentifier, firstNameExpectedExpressionIdentifier, NAME);
        scenario2.addMappingValue(disputeFactIdentifier, amountGivenExpressionIdentifier, AMOUNT);
        scenario2.addMappingValue(disputeFactIdentifier, amountExpectedExpressionIdentifier, AMOUNT);
    }

    @Test
    public void extractGivenValuesTest() {
        List<ScenarioInput> scenario1Inputs = extractGivenValues(simulation.getSimulationDescriptor(), scenario1.getFactMappingValues());
        assertEquals(scenario1Inputs.size(), 1);

        List<ScenarioInput> scenario2Inputs = extractGivenValues(simulation.getSimulationDescriptor(), scenario2.getFactMappingValues());
        assertEquals(scenario2Inputs.size(), 2);
    }

    @Test
    public void extractExpectedValuesTest() {
        List<ScenarioOutput> scenario1Outputs = extractExpectedValues(scenario1.getFactMappingValues());
        assertEquals(scenario1Outputs.size(), 1);

        List<ScenarioOutput> scenario2Outputs = extractExpectedValues(scenario2.getFactMappingValues());
        assertEquals(scenario2Outputs.size(), 2);
    }

    @Test
    public void verifyConditionsTest() {
        List<ScenarioInput> scenario1Inputs = extractGivenValues(simulation.getSimulationDescriptor(), scenario1.getFactMappingValues());
        List<ScenarioOutput> scenario1Outputs = extractExpectedValues(scenario1.getFactMappingValues());

        List<ScenarioResult> scenario1Results = verifyConditions(simulation.getSimulationDescriptor(), scenario1Inputs, scenario1Outputs);
        assertEquals(scenario1Results.size(), 1);

        List<ScenarioInput> scenario2Inputs = extractGivenValues(simulation.getSimulationDescriptor(), scenario2.getFactMappingValues());
        List<ScenarioOutput> scenario2Outputs = extractExpectedValues(scenario2.getFactMappingValues());

        List<ScenarioResult> scenario2Results = verifyConditions(simulation.getSimulationDescriptor(), scenario2Inputs, scenario2Outputs);
        assertEquals(scenario2Results.size(), 2);
    }

//    @Test
//    public void getScenarioResultsTest() {
//        ScenarioRunnerHelper.getScenarioResults(simulation.getSimulationDescriptor(), ):
//    }
//
//    @Test
//    public void validateAssertionTest() {
//        ScenarioRunnerHelper.validateAssertion();
//    }
//
//    @Test
//    public void getParamsForBeanTest() {
//        ScenarioRunnerHelper.getParamsForBean():
//    }
//
//    @Test
//    public void groupByFactIdentifierAndFilterTest() {
//        ScenarioRunnerHelper.getScenarioResults();
//    }
}