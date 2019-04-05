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
package org.drools.workbench.screens.scenariosimulation.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Aggregation of all metadata information about a simulation run
 */
@Portable
public class SimulationRunMetadata {

    protected int available;
    protected int executed;
    protected double coveragePercentage;

    protected SortedMap<String, Integer> outputCounter = new TreeMap<>();

    protected SortedMap<ScenarioWithIndex, List<String>> scenarioCounter = new TreeMap<>(Comparator.comparingInt(a -> a.index));

    public SimulationRunMetadata() {
        // CDI
    }

    public SimulationRunMetadata(int available, int executed, SortedMap<String, Integer> outputCounter, SortedMap<ScenarioWithIndex, List<String>> scenarioCounter) {
        this.available = available;
        this.executed = executed;
        this.outputCounter = outputCounter;
        this.scenarioCounter = scenarioCounter;
        this.coveragePercentage = (double) executed / available;
    }

    public int getAvailable() {
        return available;
    }

    public int getExecuted() {
        return executed;
    }

    public double getCoveragePercentage() {
        return (double) executed / available;
    }

    public SortedMap<String, Integer> getOutputCounter() {
        return Collections.unmodifiableSortedMap(outputCounter);
    }

    public SortedMap<ScenarioWithIndex, List<String>> getScenarioCounter() {
        return Collections.unmodifiableSortedMap(scenarioCounter);
    }
}
