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

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

/**
 * Class used to mock a *.scesim file provider
 */
@ApplicationScoped
public class ScesimFilesProvider {

    private static final List<String> files = Arrays.asList("populatedScesimDmn", "populatedScesimRule");

    public List<String> getFiles() {
        return files;
    }

    public String getScesimFile(String fileName) {
        switch (fileName) {
            case "populatedScesimDmn":
                return populatedScesimDmn;
            case "populatedScesimRule":
                return populatedScesimRule;
            default:
                return newScesimRule;
        }
    }

    public String getNewScesimRule() {
        return newScesimRule;
    }

    private static final String populatedScesimDmn = "<ScenarioSimulationModel version=\"1.4\">\n" +
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
            "              <step>person</step>\n" +
            "            </ExpressionElement>\n" +
            "            <ExpressionElement>\n" +
            "              <step>address</step>\n" +
            "            </ExpressionElement>\n" +
            "          </expressionElements>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|1</name>\n" +
            "            <type>GIVEN</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier>\n" +
            "            <name>person</name>\n" +
            "            <className>person</className>\n" +
            "          </factIdentifier>\n" +
            "          <className>java.util.List</className>\n" +
            "          <factAlias>person</factAlias>\n" +
            "          <expressionAlias>address</expressionAlias>\n" +
            "          <genericTypes>\n" +
            "            <string>address</string>\n" +
            "          </genericTypes>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements>\n" +
            "            <ExpressionElement>\n" +
            "              <step>person</step>\n" +
            "            </ExpressionElement>\n" +
            "            <ExpressionElement>\n" +
            "              <step>age</step>\n" +
            "            </ExpressionElement>\n" +
            "          </expressionElements>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|2</name>\n" +
            "            <type>GIVEN</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier reference=\"../../FactMapping[3]/factIdentifier\"/>\n" +
            "          <className>age</className>\n" +
            "          <factAlias>person</factAlias>\n" +
            "          <expressionAlias>age</expressionAlias>\n" +
            "          <genericTypes/>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements>\n" +
            "            <ExpressionElement>\n" +
            "              <step>is the first country European?</step>\n" +
            "            </ExpressionElement>\n" +
            "          </expressionElements>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|3</name>\n" +
            "            <type>EXPECT</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier>\n" +
            "            <name>is the first country European?</name>\n" +
            "            <className>is the first country European?</className>\n" +
            "          </factIdentifier>\n" +
            "          <className>boolean</className>\n" +
            "          <factAlias>is the first country European?</factAlias>\n" +
            "          <expressionAlias>value</expressionAlias>\n" +
            "          <genericTypes/>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements>\n" +
            "            <ExpressionElement>\n" +
            "              <step>is young?</step>\n" +
            "            </ExpressionElement>\n" +
            "          </expressionElements>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|4</name>\n" +
            "            <type>EXPECT</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier>\n" +
            "            <name>is young?</name>\n" +
            "            <className>is young?</className>\n" +
            "          </factIdentifier>\n" +
            "          <className>boolean</className>\n" +
            "          <factAlias>is young?</factAlias>\n" +
            "          <expressionAlias>value</expressionAlias>\n" +
            "          <genericTypes/>\n" +
            "        </FactMapping>\n" +
            "      </factMappings>\n" +
            "      <dmnFilePath>src/main/resources/com/dmn-list.dmn</dmnFilePath>\n" +
            "      <type>DMN</type>\n" +
            "      <skipFromBuild>false</skipFromBuild>\n" +
            "    </simulationDescriptor>\n" +
            "    <scenarios>\n" +
            "      <Scenario>\n" +
            "        <factMappingValues>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[5]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[5]/expressionIdentifier\"/>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[6]/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[6]/expressionIdentifier\"/>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>\n" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>\n" +
            "            <rawValue class=\"string\">1</rawValue>\n" +
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

    private final static String populatedScesimRule =
            "<ScenarioSimulationModel version=\"1.3\">\n" +
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

    private static final String newScesimRule =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<scesim:ScenarioSimulationModel version=\"1.5\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:scesim=\"http://www.kie.org/scesim\">\n" +
            "  <scesim:simulation>\n" +
            "    <scesim:simulationDescriptor>\n" +
            "      <scesim:factMappings>\n" +
            "        <scesim:factMapping>\n" +
            "          <scesim:expressionElements class=\"linked-list\"/>\n" +
            "          <scesim:expressionIdentifier>\n" +
            "            <name>Index</name>\n" +
            "            <scesim:type>OTHER</scesim:type>\n" +
            "          </scesim:expressionIdentifier>\n" +
            "          <scesim:factIdentifier>\n" +
            "            <name>#</name>\n" +
            "            <className>java.lang.Integer</className>\n" +
            "          </scesim:factIdentifier>\n" +
            "          <className>java.lang.Integer</className>\n" +
            "          <scesim:factAlias>#</scesim:factAlias>\n" +
            "        </scesim:factMapping>\n" +
            "        <scesim:factMapping>\n" +
            "          <scesim:expressionElements class=\"linked-list\"/>\n" +
            "          <scesim:expressionIdentifier>\n" +
            "            <name>Description</name>\n" +
            "            <scesim:type>OTHER</scesim:type>\n" +
            "          </scesim:expressionIdentifier>\n" +
            "          <scesim:factIdentifier>\n" +
            "            <name>Scenario description</name>\n" +
            "            <className>java.lang.String</className>\n" +
            "          </scesim:factIdentifier>\n" +
            "          <className>java.lang.String</className>\n" +
            "          <scesim:factAlias>Scenario description</scesim:factAlias>\n" +
            "        </scesim:factMapping>\n" +
            "        <scesim:factMapping>\n" +
            "          <scesim:expressionElements class=\"linked-list\"/>\n" +
            "          <scesim:expressionIdentifier>\n" +
            "            <name>0|1</name>\n" +
            "            <scesim:type>GIVEN</scesim:type>\n" +
            "          </scesim:expressionIdentifier>\n" +
            "          <scesim:factIdentifier>\n" +
            "            <name>Empty</name>\n" +
            "            <className>java.lang.Void</className>\n" +
            "          </scesim:factIdentifier>\n" +
            "          <className>java.lang.Void</className>\n" +
            "          <scesim:factAlias>INSTANCE 1</scesim:factAlias>\n" +
            "          <scesim:expressionAlias>PROPERTY 1</scesim:expressionAlias>\n" +
            "        </scesim:factMapping>\n" +
            "        <scesim:factMapping>\n" +
            "          <scesim:expressionElements class=\"linked-list\"/>\n" +
            "          <scesim:expressionIdentifier>\n" +
            "            <name>0|2</name>\n" +
            "            <scesim:type>EXPECT</scesim:type>\n" +
            "          </scesim:expressionIdentifier>\n" +
            "          <scesim:factIdentifier reference=\"../../FactMapping[3]/factIdentifier\"/>\n" +
            "          <className>java.lang.Void</className>\n" +
            "          <scesim:factAlias>INSTANCE 2</scesim:factAlias>\n" +
            "          <scesim:expressionAlias>PROPERTY 2</scesim:expressionAlias>\n" +
            "        </scesim:factMapping>\n" +
            "      </scesim:factMappings>\n" +
            "      <scesim:type>RULE</scesim:type>\n" +
            "      <skipFromBuild>false</skipFromBuild>\n" +
            "    </scesim:simulationDescriptor>\n" +
            "    <scesim:scenarios class=\"linked-list\">\n" +
            "      <scesim:Scenario>\n" +
            "        <scesim:factMappingValues>\n" +
            "          <scesim:factMappingValue>\n" +
            "            <scesim:factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
            "            <scesim:expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
            "          </scesim:factMappingValue>\n" +
            "          <scesim:factMappingValue>\n" +
            "            <scesim:factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <scesim:expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
            "          </scesim:factMappingValue>\n" +
            "          <scesim:factMappingValue>\n" +
            "            <scesim:factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
            "            <scesim:expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
            "          </scesim:factMappingValue>\n" +
            "        </scesim:factMappingValues>\n" +
            "        <scesim:simulationDescriptor reference=\"../../../simulationDescriptor\"/>\n" +
            "      </scesim:Scenario>\n" +
            "    </scesim:scenarios>\n" +
            "  </scesim:simulation>\n" +
            "  <scesim:imports>\n" +
            "    <scesim:imports/>\n" +
            "  </scesim:imports>\n" +
            "</scesim:ScenarioSimulationModel>";
}
