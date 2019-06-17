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
package org.drools.workbench.screens.scenariosimulation.kogito.client.converters;

import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.Scenario;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.scenariosimulation.api.model.SimulationDescriptor;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIExpressionIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactIdentifierType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIFactMappingType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIImportType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIImportsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenarioSimulationModelType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenarioType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSISimulationDescriptorType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSISimulationType;
import org.kie.soup.project.datamodel.imports.Import;
import org.kie.soup.project.datamodel.imports.Imports;

/**
 * Class used to convert from <b>JSInterop</b> bean to <b>api</b> one
 */
public class JSInteropApiConverter {

    public static ScenarioSimulationModel getScenarioSimulationModel(JSIScenarioSimulationModelType source) {
        ScenarioSimulationModel toReturn = new ScenarioSimulationModel();
        toReturn.setImports(getImports(source.getImports()));
        toReturn.setSimulation(getSimulation(source.getSimulation()));
        return toReturn;
    }

    protected static Imports getImports(JSIImportsType source) {
        Imports toReturn = new Imports();
//        final JSIInnerImportsType imports = source.getImports();
//        if (imports != null) {
//            final JSIImportType[] importArray = imports.getImport();
//            if (importArray != null && importArray.length > 0) {
//                for (int i = 0; i < importArray.length; i++) {
//                    toReturn.addImport(getImport(importArray[i]));
//                }
//            }
//        }
        return toReturn;
    }

    protected static Import getImport(JSIImportType source) {
        Import toReturn = new Import();
        toReturn.setType(source.getType());
        return toReturn;
    }

    protected static Simulation getSimulation(JSISimulationType source) {
        Simulation toReturn = new Simulation();
        populateSimulationDescriptor(toReturn.getSimulationDescriptor(), source.getSimulationDescriptor());
        Scenario added = toReturn.addScenario();
        populateScenario(added, source.getScenarios().getScenario());
        return toReturn;
    }

    protected static void populateScenario(Scenario toPopulate, JSIScenarioType source) {
//        for(JSIFactMappingValueType jsiFactMappingValueType : source.getFactMappingValues().getFactMappingValue()) {
//            toPopulate.addMappingValue(getFactIdentifier(jsiFactMappingValueType.getFactIdentifier()), getExpressionIdentifier(jsiFactMappingValueType.getExpressionIdentifier()),jsiFactMappingValueType.getRawValue() ) )
//        }
    }

    protected static void populateSimulationDescriptor(SimulationDescriptor toPopulate, JSISimulationDescriptorType source) {
        for (JSIFactMappingType jsiFactMappingType : source.getFactMappings().getFactMapping()) {
            final FactMapping added = toPopulate.addFactMapping(getFactIdentifier(jsiFactMappingType.getFactIdentifier()), getExpressionIdentifier(jsiFactMappingType.getExpressionIdentifier()));
            added.setFactAlias(jsiFactMappingType.getFactAlias());
            added.setExpressionAlias(jsiFactMappingType.getExpressionAlias());
//            added.setGenericTypes();
        }
        toPopulate.setDmoSession(source.getDmoSession());
        toPopulate.setDmnFilePath("????");
        toPopulate.setType(ScenarioSimulationModel.Type.valueOf(source.getType()));
        toPopulate.setDmnFilePath(source.getFileName());
        toPopulate.setKieSession(source.getKieSession());
        toPopulate.setKieBase(source.getKieBase());
        toPopulate.setRuleFlowGroup(source.getRuleFlowGroup());
        toPopulate.setDmnNamespace("????");
        toPopulate.setDmnName("????");
//        toPopulate.setSkipFromBuild(source.);
    }

    protected static ExpressionIdentifier getExpressionIdentifier(JSIExpressionIdentifierType source) {
        return new ExpressionIdentifier(source.getName(), FactMappingType.valueOf(source.getType()));
    }

    protected static FactIdentifier getFactIdentifier(JSIFactIdentifierType source) {
        return new FactIdentifier(source.getName(), source.getClassName());
    }
}
