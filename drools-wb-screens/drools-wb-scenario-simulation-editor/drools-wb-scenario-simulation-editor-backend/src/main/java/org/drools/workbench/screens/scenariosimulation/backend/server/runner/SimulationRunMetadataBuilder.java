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

package org.drools.workbench.screens.scenariosimulation.backend.server.runner;

import org.drools.workbench.screens.scenariosimulation.backend.server.runner.model.ScenarioResultMetadata;
import org.drools.workbench.screens.scenariosimulation.model.SimulationRunMetadata;

public class SimulationRunMetadataBuilder {

    // FIXME to test
    public SimulationRunMetadataBuilder addScenarioResultMetadata(ScenarioResultMetadata scenarioResultMetadata) {
        // FIXME
        return this;
    }

    // FIXME to test
    public SimulationRunMetadata build() {
        // FIXME
        return new SimulationRunMetadata();
    }

    public static SimulationRunMetadataBuilder create() {
        return new SimulationRunMetadataBuilder();
    }
}
