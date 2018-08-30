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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.drools.workbench.screens.scenariosimulation.backend.server.OperatorEvaluator;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ExpectedResult;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioInput;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioOutput;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioResult;
import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioRunnerData;
import org.drools.workbench.screens.scenariosimulation.backend.server.util.ScenarioBeanUtil;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionElement;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValue;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValueOperator;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.RequestContext;

import static java.util.stream.Collectors.toList;
import static org.drools.workbench.screens.scenariosimulation.backend.server.runner.ScenarioExecutableBuilder.createBuilder;
import static org.drools.workbench.screens.scenariosimulation.backend.server.util.ScenarioBeanUtil.fillBean;

public class ScenarioRunner extends Runner {

    private final Description desc = Description.createSuiteDescription("Scenario Simulation tests");
    private final KieContainer kieContainer;
    private final SimulationDescriptor simulationDescriptor;
    private List<Scenario> scenarios;

    public ScenarioRunner(KieContainer kieContainer, Simulation simulation) {
        this(kieContainer, simulation.getSimulationDescriptor(), simulation.getScenarios());
    }

    public ScenarioRunner(KieContainer kieContainer, SimulationDescriptor simulationDescriptor, List<Scenario> scenarios) {
        this.kieContainer = kieContainer;
        this.simulationDescriptor = simulationDescriptor;
        this.scenarios = scenarios;
    }

    @Override
    public void run(RunNotifier notifier) {

        for (Scenario scenario : scenarios) {

            internalRunScenario(scenario, getSingleNotifier(notifier, scenario));
        }
    }

    protected List<ScenarioResult> internalRunScenario(Scenario scenario, EachTestNotifier singleNotifier) {
        ScenarioRunnerData scenarioRunnerData = new ScenarioRunnerData();

        singleNotifier.fireTestStarted();

        try {
            extractGivenValues(simulationDescriptor, scenario).forEach(scenarioRunnerData::addInput);
            extractExpectedValues(scenario).forEach(scenarioRunnerData::addOutput);

            executeScenario(kieContainer, scenarioRunnerData.getInputData());
            List<ScenarioResult> scenarioResults = checkResult(scenarioRunnerData);
            validateAssertion(scenarioResults, scenario, singleNotifier);

            scenarioResults.forEach(scenarioRunnerData::addResult);
        } catch (ReflectiveOperationException e) {
            singleNotifier.addFailure(
                    new IllegalStateException(new StringBuilder().append("Scenario '").append(scenario.getDescription())
                                                      .append("' has wrong mappings").toString(), e));
        } catch (Throwable e) {
            singleNotifier.addFailure(new IllegalStateException(new StringBuilder().append("Unexpected test error in scenario '")
                                                                        .append(scenario.getDescription()).append("'").toString(), e));
        }

        singleNotifier.fireTestFinished();
        return scenarioRunnerData.getResultData();
    }

    protected List<ScenarioInput> extractGivenValues(SimulationDescriptor simulationDescriptor, Scenario scenario) throws ReflectiveOperationException {
        List<ScenarioInput> scenarioInput = new ArrayList<>();

        Map<FactIdentifier, List<FactMappingValue>> groupByFactIdentifier = groupByFactIdentifierAndFilter(scenario, FactMappingType.GIVEN);

        for (Map.Entry<FactIdentifier, List<FactMappingValue>> entry : groupByFactIdentifier.entrySet()) {

            FactIdentifier factIdentifier = entry.getKey();

            // for each fact, create a map of path to fields and values to set filtered by type
            Map<List<String>, Object> paramsForBean = getParamsForBean(simulationDescriptor,
                                                                       factIdentifier,
                                                                       entry.getValue());

            Object bean = fillBean(factIdentifier.getClassName(), paramsForBean);

            scenarioInput.add(new ScenarioInput(factIdentifier, bean));
        }

        return scenarioInput;
    }

    protected List<ScenarioOutput> extractExpectedValues(Scenario scenario) {
        List<ScenarioOutput> scenarioOutput = new ArrayList<>();

        Map<FactIdentifier, List<FactMappingValue>> groupByFactIdentifier = groupByFactIdentifierAndFilter(scenario, FactMappingType.EXPECTED);

        for (Map.Entry<FactIdentifier, List<FactMappingValue>> entry : groupByFactIdentifier.entrySet()) {

            FactIdentifier factIdentifier = entry.getKey();

            List<ExpectedResult> expectedResults = entry.getValue().stream()
                    .map(fmv -> new ExpectedResult(factIdentifier, fmv)).collect(toList());

            scenarioOutput.add(new ScenarioOutput(factIdentifier, expectedResults));
        }

        return scenarioOutput;
    }

    protected RequestContext executeScenario(KieContainer kieContainer, List<ScenarioInput> given) {
        ScenarioExecutableBuilder scenarioExecutableBuilder = createBuilder(kieContainer);
        given.stream().map(ScenarioInput::getValue).forEach(scenarioExecutableBuilder::insert);
        return scenarioExecutableBuilder.run();
    }

    protected List<ScenarioResult> checkResult(ScenarioRunnerData scenarioRunnerData)
            throws ReflectiveOperationException {
        List<ScenarioResult> scenarioResult = new ArrayList<>();

        List<ScenarioInput> inputData = scenarioRunnerData.getInputData();
        List<ScenarioOutput> outputData = scenarioRunnerData.getOutputData();

        for (ScenarioInput input : inputData) {
            FactIdentifier factIdentifier = input.getFactIdentifier();
            Object factInstance = input.getValue();
            List<ScenarioOutput> assertionOnFact = outputData.stream().filter(elem -> elem.getFactIdentifier().equals(factIdentifier)).collect(toList());

            // check if this fact has something to check
            if (assertionOnFact.size() < 1) {
                continue;
            }

            scenarioResult.addAll(getScenarioResults(assertionOnFact, factInstance, factIdentifier));
        }

        return scenarioResult;
    }

    protected List<ScenarioResult> getScenarioResults(List<ScenarioOutput> scenarioOutputsPerFact, Object factInstance, FactIdentifier factIdentifier)
            throws ReflectiveOperationException {
        List<ScenarioResult> scenarioResults = new ArrayList<>();
        for (ScenarioOutput scenarioOutput : scenarioOutputsPerFact) {
            List<ExpectedResult> expectedResults = scenarioOutput.getExpectedResult();

            for (ExpectedResult expectedResult : expectedResults) {
                FactMappingValue factMappingValue = expectedResult.getFactMappingValue();
                FactMappingValueOperator operator = factMappingValue.getOperator();

                ExpressionIdentifier expressionIdentifier = factMappingValue.getExpressionIdentifier();

                FactMapping factMapping = simulationDescriptor.getFactMapping(factIdentifier, expressionIdentifier)
                        .orElseThrow(() -> new IllegalStateException("Wrong expression, this should not happen"));

                List<String> pathToValue = factMapping.getExpressionElements().stream().map(ExpressionElement::getStep).collect(toList());
                Object expectedValue = expectedResult.getFactMappingValue().getRawValue();
                Object resultValue = ScenarioBeanUtil.navigateToObject(factInstance, pathToValue);

                Boolean conditionResult = new OperatorEvaluator().evaluate(operator, resultValue, expectedValue);

                scenarioResults.add(new ScenarioResult(factIdentifier, factMappingValue, conditionResult));
            }
        }
        return scenarioResults;
    }

    protected void validateAssertion(List<ScenarioResult> scenarioResults, Scenario scenario, EachTestNotifier singleNotifier) {
        boolean isScenarioSatisfied = scenarioResults.stream().allMatch(ScenarioResult::getResult);
        if (!isScenarioSatisfied) {
            scenario.getDescription();
            singleNotifier.addFailedAssumption(
                    new AssumptionViolatedException(new StringBuilder().append("Scenario '").append(scenario.getDescription())
                                                            .append("' has wrong assertion").toString()));
        }
    }

    @Override
    public Description getDescription() {
        return this.desc;
    }

    protected Map<List<String>, Object> getParamsForBean(SimulationDescriptor simulationDescriptor, FactIdentifier factIdentifier, List<FactMappingValue> factMappingValues) {
        Map<List<String>, Object> paramsForBean = new HashMap<>();

        for (FactMappingValue factMappingValue : factMappingValues) {
            ExpressionIdentifier expressionIdentifier = factMappingValue.getExpressionIdentifier();

            FactMapping factMapping = simulationDescriptor.getFactMapping(factIdentifier, expressionIdentifier)
                    .orElseThrow(() -> new IllegalStateException("Wrong expression, this should not happen"));

            List<String> pathToField = factMapping.getExpressionElements().stream()
                    .map(ExpressionElement::getStep).collect(toList());

            paramsForBean.put(pathToField, factMappingValue.getRawValue());
        }

        return paramsForBean;
    }

    protected Map<FactIdentifier, List<FactMappingValue>> groupByFactIdentifierAndFilter(Scenario scenario, FactMappingType type) {
        Map<FactIdentifier, List<FactMappingValue>> groupByFactIdentifier = new HashMap<>();
        for (FactMappingValue factMappingValue : scenario.getFactMappingValues()) {

            FactIdentifier factIdentifier = factMappingValue.getFactIdentifier();

            if (!factMappingValue.getExpressionIdentifier().getType().equals(type)) {
                continue;
            }

            groupByFactIdentifier.computeIfAbsent(factIdentifier, key -> new ArrayList<>())
                    .add(factMappingValue);
        }
        return groupByFactIdentifier;
    }

    protected EachTestNotifier getSingleNotifier(RunNotifier notifier, Scenario scenario) {
        Description childDescription = Description.createTestDescription(getClass(),
                                                                         scenario.getDescription());
        desc.addChild(childDescription);
        return new EachTestNotifier(notifier, childDescription);
    }
}
