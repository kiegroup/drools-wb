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
package org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.converters;

import com.google.gwt.core.client.GWT;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.ScenarioSimulationModelAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ScenarioSimulationModelKM;

public class ScenarioSimulationModelConverter extends AbstractAutoBeanConverter<ScenarioSimulationModelKM, ScenarioSimulationModelAutobeanFactory, ScenarioSimulationModel> {

    protected SimulationConverter simulationConverter = new SimulationConverter();

    protected ImportsConverter importsConverter = new ImportsConverter();

    public ScenarioSimulationModelConverter() {
        factory = GWT.create(ScenarioSimulationModelAutobeanFactory.class);
        clazz = ScenarioSimulationModelKM.class;
    }

    @Override
    public ScenarioSimulationModel toApiModel(ScenarioSimulationModelKM kogitoModel) {
        ScenarioSimulationModel toReturn = new ScenarioSimulationModel();
        toReturn.setSimulation(simulationConverter.toApiModel(kogitoModel.getSimulation()));
        toReturn.setImports(importsConverter.toApiModel(kogitoModel.getImports()));
        return toReturn;
    }

    @Override
    public ScenarioSimulationModelKM toKogitoModel(ScenarioSimulationModel apiModel) {
        ScenarioSimulationModelKM toReturn = makeKogitoModel();
        toReturn.setSimulation(simulationConverter.toKogitoModel(apiModel.getSimulation()));
        toReturn.setImports(importsConverter.toKogitoModel(apiModel.getImports()));
        return toReturn;
    }
}
