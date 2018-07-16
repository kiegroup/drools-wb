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
package org.drools.workbench.screens.scenariosimulation.backend.server.preferences;

import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationService;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScenarioSimulationApplicationPreferencesLoaderTest {

    @After
    public void tearDown() throws Exception {
        System.clearProperty(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED);
    }

    @Test
    public void notSet() throws Exception {

        assertEquals("false", new ScenarioSimulationApplicationPreferencesLoader().load().get(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED));
    }

    @Test
    public void setTrue() throws Exception {
        System.setProperty(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED, "true");

        assertEquals("true", new ScenarioSimulationApplicationPreferencesLoader().load().get(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED));
    }

    @Test
    public void setFalse() throws Exception {
        System.setProperty(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED, "false");

        assertEquals("false", new ScenarioSimulationApplicationPreferencesLoader().load().get(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED));
    }
}