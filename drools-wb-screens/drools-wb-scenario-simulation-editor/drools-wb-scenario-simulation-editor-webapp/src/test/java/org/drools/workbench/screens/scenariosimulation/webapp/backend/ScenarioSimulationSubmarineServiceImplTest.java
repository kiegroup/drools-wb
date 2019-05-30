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

package org.drools.workbench.screens.scenariosimulation.webapp.backend;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.backend.TestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class ScenarioSimulationSubmarineServiceImplTest {

    private static ScenarioSimulationSubmarineServiceServerImpl instance = new ScenarioSimulationSubmarineServiceServerImpl();

    private static String ASSETS_DIRECTORY = "assets";

    @Test
    public void marshal() {
        ScenarioSimulationModel toMarshal = new ScenarioSimulationModel();
        final String retrieved = instance.marshal(toMarshal);
        assertNotNull(retrieved);
        assertFalse(retrieved.isEmpty());
    }

    @Test
    public void unmarshal() {
        try {
            String toUnmarshal = TestUtils.getFileContent("scesim-dmn.scesim");
            final ScenarioSimulationModel retrieved = instance.unmarshal(toUnmarshal);
            assertEquals(retrieved.getSimulation().getSimulationDescriptor().getType(), ScenarioSimulationModel.Type.DMN);
            assertNotNull(retrieved.getSimulation().getSimulationDescriptor().getDmnFilePath());
            assertNull(retrieved.getSimulation().getSimulationDescriptor().getDmoSession());
        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void getAssetsFromExistingDirectory() {
        try {
            List<String> retrieved = instance.getAssets(ASSETS_DIRECTORY, "dmn", "com.test");
            assertNotNull(retrieved);
            assertFalse(retrieved.isEmpty());
            retrieved = instance.getAssets(ASSETS_DIRECTORY, "not_existing", "com.test");
            assertNotNull(retrieved);
            assertTrue(retrieved.isEmpty());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test(expected = Exception.class)
    public void getAssetsFromNotExistingDirectory() throws Exception {
        instance.getAssets("NOT_EXISTING_DIR", "dmn", "com.test");
    }

    @Test
    public void getAssetsDirectory() {
        try {
            File retrieved = instance.getAssetsDirectory(ASSETS_DIRECTORY);
            assertNotNull(retrieved);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}