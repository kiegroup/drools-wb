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
import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.ScenarioAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ScenarioKM;

public class ScenarioConverter extends AbstractAutoBeanConverter<ScenarioKM, ScenarioAutobeanFactory, Scenario> {

//    protected FactMappingValueConverter factMappingValueConverter = new FactMappingValueConverter();
    protected SimulationDescriptorConverter simulationDescriptorConverter = new SimulationDescriptorConverter();

    public ScenarioConverter() {
        factory = GWT.create(ScenarioAutobeanFactory.class);
        clazz = ScenarioKM.class;
    }

    @Override
    public Scenario toApiModel(ScenarioKM kogitoModel) {
        Scenario toReturn = new Scenario(simulationDescriptorConverter.toApiModel(kogitoModel.getSimulationDescriptor()));
//        List<FactMappingValue> factMappingValues = kogitoModel.getFactMappingValues().stream()
//                .map(km -> factMappingValueConverter.toApiModel(km))
//                .collect(Collectors.toList());
//        factMappingValues.forEach(factMappingValue ->
//                toReturn.addMappingValue(factMappingValue.getFactIdentifier(), factMappingValue.getExpressionIdentifier(), factMappingValue.getRawValue()));
        toReturn.setDescription(kogitoModel.getDescription());
        return toReturn;
    }

    @Override
    public ScenarioKM toKogitoModel(Scenario apiModel) {
//        List<FactMappingValueKM> factMappingValueKMs = apiModel.getUnmodifiableFactMappingValues().stream()
//                .map(bm -> factMappingValueConverter.toKogitoModel(bm))
//                .collect(Collectors.toList());
        ScenarioKM toReturn = makeKogitoModel();
//        toReturn.setFactMappingValues(factMappingValueKMs);
        toReturn.setDescription(apiModel.getDescription());
        // TODO TO FIX
        toReturn.setSimulationDescriptor(simulationDescriptorConverter.makeKogitoModel());
        return toReturn;
    }
}
