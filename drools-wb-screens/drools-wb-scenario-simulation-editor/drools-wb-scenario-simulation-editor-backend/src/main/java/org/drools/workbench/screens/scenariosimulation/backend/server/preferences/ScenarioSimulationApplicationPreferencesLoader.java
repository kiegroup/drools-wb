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

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationService;
import org.guvnor.common.services.backend.preferences.ApplicationPreferencesLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class ScenarioSimulationApplicationPreferencesLoader
        implements ApplicationPreferencesLoader {

    private static final Logger log = LoggerFactory.getLogger(ScenarioSimulationApplicationPreferencesLoader.class);

    @Override
    public Map<String, String> load() {
        final Map<String, String> preferences = new HashMap<>();

        final String property = getProperty();
        log.info("Setting preference '" + ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED + "' to '" + property + "'.");
        preferences.put(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED,
                        property);

        return preferences;
    }

    private String getProperty() {
        final String property = System.getProperty(ScenarioSimulationService.SCENARIO_SIMULATION_ENABLED);
        if (property == null) {
            return "false";
        } else {
            return property;
        }
    }
}
