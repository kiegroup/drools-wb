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

import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.SimulationDescriptor;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.SimulationDescriptorAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.FactMappingKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ScenarioSimulationModelKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.SimulationDescriptorKM;

public class SimulationDescriptorConverter extends AbstractAutoBeanConverter<SimulationDescriptorKM, SimulationDescriptorAutobeanFactory, SimulationDescriptor> {

    protected FactMappingConverter factMappingConverter = new FactMappingConverter();

    public SimulationDescriptorConverter() {
        factory = GWT.create(SimulationDescriptorAutobeanFactory.class);
        clazz = SimulationDescriptorKM.class;
    }

    @Override
    public SimulationDescriptor toApiModel(SimulationDescriptorKM kogitoModel) {
        List<FactMapping> factMappings = kogitoModel.getFactMappings().stream()
                .map(km -> factMappingConverter.toApiModel(km))
                .collect(Collectors.toList());
        SimulationDescriptor toReturn = new SimulationDescriptor();
        toReturn.getFactMappings().addAll(factMappings);
        toReturn.setDmoSession(kogitoModel.getDmoSession());
        toReturn.setDmnFilePath(kogitoModel.getDmnFilePath());
        toReturn.setType(ScenarioSimulationModel.Type.valueOf(kogitoModel.getType().name()));
        toReturn.setRuleFlowGroup(kogitoModel.getFileName());
        toReturn.setKieSession(kogitoModel.getKieSession());
        toReturn.setKieBase(kogitoModel.getKieBase());
        toReturn.setRuleFlowGroup(kogitoModel.getRuleFlowGroup());
        toReturn.setDmnNamespace(kogitoModel.getDmnNamespace());
        toReturn.setDmnName(kogitoModel.getDmnName());
        toReturn.setSkipFromBuild(kogitoModel.isSkipFromBuild());
        return toReturn;
    }

    @Override
    public SimulationDescriptorKM toKogitoModel(SimulationDescriptor apiModel) {
        List<FactMappingKM> factMappingKMs = apiModel.getFactMappings().stream()
                .map(bm -> factMappingConverter.toKogitoModel(bm))
                .collect(Collectors.toList());
        SimulationDescriptorKM toReturn = makeKogitoModel();
        toReturn.setFactMappings(factMappingKMs);
        toReturn.setDmoSession(apiModel.getDmoSession());
        toReturn.setDmnFilePath(apiModel.getDmnFilePath());
        toReturn.setType(ScenarioSimulationModelKM.Type.valueOf(apiModel.getType().name()));
        toReturn.setRuleFlowGroup(apiModel.getFileName());
        toReturn.setKieSession(apiModel.getKieSession());
        toReturn.setKieBase(apiModel.getKieBase());
        toReturn.setRuleFlowGroup(apiModel.getRuleFlowGroup());
        toReturn.setDmnNamespace(apiModel.getDmnNamespace());
        toReturn.setDmnName(apiModel.getDmnName());
        toReturn.setSkipFromBuild(apiModel.isSkipFromBuild());
        return toReturn;
    }
}
