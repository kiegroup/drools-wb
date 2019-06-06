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
package org.drools.workbench.screens.scenariosimulation.kogito.client.piriti;

import com.google.gwt.core.client.GWT;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.ExpressionElementReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.ExpressionIdentifierReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.FactIdentifierReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.FactMappingReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.FactMappingValueReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.ScenarioReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.ScenarioSimulationModelReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.SimulationDescriptorReader;
import org.drools.workbench.screens.scenariosimulation.kogito.client.piriti.readers.SimulationReader;

public class ReaderWriterFactory {

    public static final ExpressionElementReader EXPRESSION_ELEMENT_READER = GWT.create(ExpressionElementReader.class);
    public static final ExpressionIdentifierReader EXPRESSION_IDENTIFIER_READER = GWT.create(ExpressionIdentifierReader.class);
    public static final FactIdentifierReader FACT_IDENTIFIER_READER = GWT.create(FactIdentifierReader.class);
    public static final FactMappingReader FACT_MAPPING_READER = GWT.create(FactMappingReader.class);
    public static final FactMappingValueReader FACT_MAPPING_VALUE_READER = GWT.create(FactMappingValueReader.class);
    public static final ScenarioReader SCENARIO_READER = GWT.create(ScenarioReader.class);
    public static final ScenarioSimulationModelReader SCENARIO_SIMULATION_MODEL_READER = GWT.create(ScenarioSimulationModelReader.class);
    public static final SimulationReader SIMULATION_READER = GWT.create(SimulationReader.class);
    public static final SimulationDescriptorReader SIMULATION_DESCRIPTOR_READER = GWT.create(SimulationDescriptorReader.class);


}
