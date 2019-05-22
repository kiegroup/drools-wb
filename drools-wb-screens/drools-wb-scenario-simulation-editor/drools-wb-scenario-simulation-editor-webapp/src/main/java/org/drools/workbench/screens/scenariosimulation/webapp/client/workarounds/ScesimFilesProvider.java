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

import javax.enterprise.context.ApplicationScoped;

/**
 * Class used to mock a *.scesim file provider
 */
@ApplicationScoped
public class ScesimFilesProvider {

    public String getScesimContent() {
       return scesimRule;
    }


    private final static String scesimRule = "<ScenarioSimulationModel version=\"1.3\">\n" +
            "  <simulation>\n" +
            "    <simulationDescriptor>\n" +
            "      <factMappings>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements/>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>Index</name>\n" +
            "            <type>OTHER</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier>\n" +
            "            <name>#</name>\n" +
            "            <className>java.lang.Integer</className>\n" +
            "          </factIdentifier>\n" +
            "          <className>java.lang.Integer</className>\n" +
            "          <factAlias>#</factAlias>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements/>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>Description</name>\n" +
            "            <type>OTHER</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier>\n" +
            "            <name>Scenario description</name>\n" +
            "            <className>java.lang.String</className>\n" +
            "          </factIdentifier>\n" +
            "          <className>java.lang.String</className>\n" +
            "          <factAlias>Scenario description</factAlias>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements>\n" +
            "            <ExpressionElement>\n" +
            "              <step>Book</step>\n" +
            "            </ExpressionElement>\n" +
            "            <ExpressionElement>\n" +
            "              <step>numberOfCopies</step>\n" +
            "            </ExpressionElement>\n" +
            "          </expressionElements>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>1549029930045</name>\n" +
            "            <type>GIVEN</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier>\n" +
            "            <name>1549029930045</name>\n" +
            "            <className>com.Book</className>\n" +
            "          </factIdentifier>\n" +
            "          <className>java.lang.Integer</className>\n" +
            "          <factAlias>Book</factAlias>\n" +
            "          <expressionAlias>numberOfCopies</expressionAlias>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements>\n" +
            "            <ExpressionElement>\n" +
            "              <step>Book</step>\n" +
            "            </ExpressionElement>\n" +
            "            <ExpressionElement>\n" +
            "              <step>isAvailable</step>\n" +
            "            </ExpressionElement>\n" +
            "          </expressionElements>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|2</name>\n" +
            "            <type>EXPECT</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier reference=\"../../FactMapping[3]/factIdentifier\"/>\n" +
            "          <className>java.lang.Boolean</className>\n" +
            "          <factAlias>Book</factAlias>\n" +
            "          <expressionAlias>isAvailable</expressionAlias>\n" +
            "        </FactMapping>\n" +
            "      </factMappings>\n" +
            "      <dmoSession>default</dmoSession>\n" +
            "      <type>RULE</type>\n" +
            "    </simulationDescriptor>\n" +
            "    <scenarios>\n" +
            "      <Scenario>\n" +
            "        <factMappingValues>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">te1</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">1</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">0</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">true</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "        </factMappingValues>\n" +
            "        <simulationDescriptor>\n" +
            "          <factMappings/>\n" +
            "          <dmoSession>default</dmoSession>\n" +
            "          <type>RULE</type>\n" +
            "        </simulationDescriptor>\n" +
            "      </Scenario>\n" +
            "      <Scenario>\n" +
            "        <factMappingValues>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">te2</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">0</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">false</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">2</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "        </factMappingValues>\n" +
            "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>\n" +
            "      </Scenario>\n" +
            "      <Scenario>\n" +
            "        <factMappingValues>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">te3</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">1</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">true</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">3</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "        </factMappingValues>\n" +
            "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>\n" +
            "      </Scenario>\n" +
            "      <Scenario>\n" +
            "        <factMappingValues>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">te4</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">1</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">false</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">4</rawValue>\n" +
            "          </FactMappingValue>\n" +
            "        </factMappingValues>\n" +
            "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>\n" +
            "      </Scenario>\n" +
            "    </scenarios>\n" +
            "  </simulation>\n" +
            "  <imports>\n" +
            "    <imports/>\n" +
            "  </imports>\n" +
            "</ScenarioSimulationModel>";
}
