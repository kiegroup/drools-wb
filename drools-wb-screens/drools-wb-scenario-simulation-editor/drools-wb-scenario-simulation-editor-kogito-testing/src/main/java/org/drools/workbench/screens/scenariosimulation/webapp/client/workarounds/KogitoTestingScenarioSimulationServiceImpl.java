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
package org.drools.workbench.screens.scenariosimulation.webapp.client.workarounds;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.scenariosimulation.api.model.Background;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.ScenarioWithIndex;
import org.drools.scenariosimulation.api.model.ScesimModelDescriptor;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModelContent;
import org.drools.workbench.screens.scenariosimulation.model.SimulationRunResult;
import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationService;
import org.guvnor.common.services.shared.metadata.model.Metadata;
import org.uberfire.backend.vfs.Path;

@ApplicationScoped
public class KogitoTestingScenarioSimulationServiceImpl implements ScenarioSimulationService {

    @Inject
    private TestingVFSService testingVFSService;

    @Override
    public ScenarioSimulationModel load(Path path) {
        return null;
    }

    @Override
    public Path create(Path context, String fileName, ScenarioSimulationModel content, String comment, ScenarioSimulationModel.Type type, String value) {
        return null;
    }

    @Override
    public Path create(Path context, String fileName, ScenarioSimulationModel content, String comment) {
        return null;
    }

    @Override
    public Path save(Path path, ScenarioSimulationModel content, Metadata metadata, String comment) {
        return null;
    }

    @Override
    public ScenarioSimulationModelContent loadContent(Path path) {
        // TODO {gcardosi} to implement
        return null;
    }

    @Override
    public SimulationRunResult runScenario(Path path, ScesimModelDescriptor simulationDescriptor, List<ScenarioWithIndex> scenarios, Settings settings, Background background) {
        // TODO {gcardosi} to implement
        return null;
    }

    @Override
    public List<FactMappingValidationError> validate(Simulation simulation, Settings settings, Path path) {
        // TODO {gcardosi} to implement
        return null;
    }

    @Override
    public Path copy(Path path, String newName, String comment) {
        // TODO {gcardosi} to implement
        return null;
    }

    @Override
    public Path copy(Path path, String newName, Path targetDirectory, String comment) {
        // TODO {gcardosi} to implement
        return null;
    }

    @Override
    public void delete(Path path, String comment) {
        // TODO {gcardosi} to implement
    }

    @Override
    public Path saveAndRename(Path path, String newFileName, Metadata metadata, ScenarioSimulationModel content, String comment) {

        // TODO {gcardosi} to implement
        return null;
    }

    @Override
    public Path rename(Path path, String newName, String comment) {
        // TODO {gcardosi} to implement
        return null;
    }
}
