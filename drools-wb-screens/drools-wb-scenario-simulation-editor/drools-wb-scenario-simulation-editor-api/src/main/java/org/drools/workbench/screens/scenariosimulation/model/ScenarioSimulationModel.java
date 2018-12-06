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

package org.drools.workbench.screens.scenariosimulation.model;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.kie.soup.project.datamodel.imports.HasImports;
import org.kie.soup.project.datamodel.imports.Imports;

@Portable
public class ScenarioSimulationModel
        implements HasImports {

    public enum Type {
        DMO,
        DMN
    }

    @XStreamAsAttribute()
    private String version = "1.2";

    private Simulation simulation;

    private Imports imports = new Imports();

    private final String dmoSession;

    private final String dmnFilePath;

    private final Type type;

    /**
     * Default constructor that instantiate a <code>Type.DMO</code> model with <b>default</b> as value
     */
    public ScenarioSimulationModel() {
        this(Type.DMO, "default");
    }

    public ScenarioSimulationModel(Type type, String value) {
        this.type = type;
        switch (type) {
            case DMN:
                this.dmnFilePath = value;
                dmoSession = null;
                setDMNSession();
                break;
            case DMO:
            default:
                this.dmoSession = value;
                dmnFilePath = null;
                setDMOSession();
        }
    }

    public Simulation getSimulation() {
        return simulation;
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public Imports getImports() {
        return imports;
    }

    @Override
    public void setImports(Imports imports) {
        this.imports = imports;
    }

    public String getVersion() {
        return version;
    }

    public String getDmoSession() {
        return dmoSession;
    }

    public String getDmnFilePath() {
        return dmnFilePath;
    }

    public Type getType() {
        return type;
    }

    protected void setDMOSession() {
        simulation = new Simulation();
        SimulationDescriptor simulationDescriptor = simulation.getSimulationDescriptor();

        simulationDescriptor.addFactMapping(FactIdentifier.INDEX.getName(), FactIdentifier.INDEX, ExpressionIdentifier.INDEX);
        simulationDescriptor.addFactMapping(FactIdentifier.DESCRIPTION.getName(), FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);

        Scenario scenario = simulation.addScenario();
        int row = simulation.getUnmodifiableScenarios().indexOf(scenario);
        scenario.setDescription(null);

        // Add GIVEN Fact
        int id = 1;
        ExpressionIdentifier givenExpression = ExpressionIdentifier.create(row + "|" + id, FactMappingType.GIVEN);
        final FactMapping givenFactMapping = simulationDescriptor.addFactMapping(FactMapping.getInstancePlaceHolder(id), FactIdentifier.EMPTY, givenExpression);
        givenFactMapping.setExpressionAlias(FactMapping.getPropertyPlaceHolder(id));
        scenario.addMappingValue(FactIdentifier.EMPTY, givenExpression, null);

        // Add EXPECT Fact
        id = 2;
        ExpressionIdentifier expectedExpression = ExpressionIdentifier.create(row + "|" + id, FactMappingType.EXPECT);
        final FactMapping expectedFactMapping = simulationDescriptor.addFactMapping(FactMapping.getInstancePlaceHolder(id), FactIdentifier.EMPTY, expectedExpression);
        expectedFactMapping.setExpressionAlias(FactMapping.getPropertyPlaceHolder(id));
        scenario.addMappingValue(FactIdentifier.EMPTY, expectedExpression, null);
    }

    protected void setDMNSession() {

    }
}
