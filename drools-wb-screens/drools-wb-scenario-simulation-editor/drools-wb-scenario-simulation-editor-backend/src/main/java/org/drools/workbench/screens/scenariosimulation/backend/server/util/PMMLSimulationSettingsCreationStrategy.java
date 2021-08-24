/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.backend.server.util;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.drools.workbench.screens.scenariosimulation.service.PMMLTypeService;
import org.uberfire.backend.vfs.Path;

import static org.drools.scenariosimulation.api.model.FactMappingType.EXPECT;
import static org.drools.scenariosimulation.api.model.FactMappingType.GIVEN;
import static org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree.Type;

@ApplicationScoped
public class PMMLSimulationSettingsCreationStrategy extends AbstractSimulationSettingsCreationStrategy {

    @Inject
    protected PMMLTypeService pmmlTypeService;

    @Override
    public Simulation createSimulation(Path context, String pmmlFilePath) {
        return super.createSimulation(context, pmmlFilePath);
    }

    @Override
    public Settings createSettings(Path context, String pmmlFilePath) {
        Settings toReturn = new Settings();
        toReturn.setType(ScenarioSimulationModel.Type.PMML);
        toReturn.setPmmlFilePath(pmmlFilePath);
        return toReturn;
    }

    // Indirection for test
    @Override
    protected FactModelTuple getFactModelTuple(Path context, String pmmlFilePath) {
        return pmmlTypeService.retrieveFactModelTuple(context, pmmlFilePath);
    }

    @Override
    protected FactMappingType convert(Type modelTreeType) {
        switch (modelTreeType) {
            case INPUT:
                return GIVEN;
            case PREDICTION:
                return EXPECT;
            default:
                throw new IllegalArgumentException("Impossible to map");
        }
    }

}
