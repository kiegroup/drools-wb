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

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

/**
 * Class used to mock a *.scesim file provider
 */
@ApplicationScoped
public class ScesimFilesProvider {

    private static final String populatedScesimDmn = "<ScenarioSimulationModel version=\"1.4\">" +
            "  <simulation>" +
            "    <simulationDescriptor>" +
            "      <factMappings>" +
            "        <FactMapping>" +
            "          <expressionElements/>" +
            "          <expressionIdentifier>" +
            "            <name>Index</name>" +
            "            <type>OTHER</type>" +
            "          </expressionIdentifier>" +
            "          <factIdentifier>" +
            "            <name>#</name>" +
            "            <className>java.lang.Integer</className>" +
            "          </factIdentifier>" +
            "          <className>java.lang.Integer</className>" +
            "          <factAlias>#</factAlias>" +
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements/>" +
            "          <expressionIdentifier>" +
            "            <name>Description</name>" +
            "            <type>OTHER</type>" +
            "          </expressionIdentifier>" +
            "          <factIdentifier>" +
            "            <name>Scenario description</name>" +
            "            <className>java.lang.String</className>" +
            "          </factIdentifier>" +
            "          <className>java.lang.String</className>" +
            "          <factAlias>Scenario description</factAlias>" +
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements>" +
            "            <ExpressionElement>" +
            "              <step>person</step>" +
            "            </ExpressionElement>" +
            "            <ExpressionElement>" +
            "              <step>address</step>" +
            "            </ExpressionElement>" +
            "          </expressionElements>" +
            "          <expressionIdentifier>" +
            "            <name>0|1</name>" +
            "            <type>GIVEN</type>" +
            "          </expressionIdentifier>" +
            "          <factIdentifier>" +
            "            <name>person</name>" +
            "            <className>person</className>" +
            "          </factIdentifier>" +
            "          <className>java.util.List</className>" +
            "          <factAlias>person</factAlias>" +
            "          <expressionAlias>address</expressionAlias>" +
            "          <genericTypes>" +
            "            <string>address</string>" +
            "          </genericTypes>" +
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements>" +
            "            <ExpressionElement>" +
            "              <step>person</step>" +
            "            </ExpressionElement>" +
            "            <ExpressionElement>" +
            "              <step>age</step>" +
            "            </ExpressionElement>" +
            "          </expressionElements>" +
            "          <expressionIdentifier>" +
            "            <name>0|2</name>" +
            "            <type>GIVEN</type>" +
            "          </expressionIdentifier>" +
            "          <factIdentifier reference=\"../../FactMapping[3]/factIdentifier\"/>" +
            "          <className>age</className>" +
            "          <factAlias>person</factAlias>" +
            "          <expressionAlias>age</expressionAlias>" +
            "          <genericTypes/>" +
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements>" +
            "            <ExpressionElement>" +
            "              <step>is the first country European?</step>" +
            "            </ExpressionElement>" +
            "          </expressionElements>" +
            "          <expressionIdentifier>" +
            "            <name>0|3</name>" +
            "            <type>EXPECT</type>" +
            "          </expressionIdentifier>" +
            "          <factIdentifier>" +
            "            <name>is the first country European?</name>" +
            "            <className>is the first country European?</className>" +
            "          </factIdentifier>" +
            "          <className>boolean</className>" +
            "          <factAlias>is the first country European?</factAlias>" +
            "          <expressionAlias>value</expressionAlias>" +
            "          <genericTypes/>" +
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements>" +
            "            <ExpressionElement>" +
            "              <step>is young?</step>" +
            "            </ExpressionElement>" +
            "          </expressionElements>" +
            "          <expressionIdentifier>" +
            "            <name>0|4</name>" +
            "            <type>EXPECT</type>" +
            "          </expressionIdentifier>" +
            "          <factIdentifier>" +
            "            <name>is young?</name>" +
            "            <className>is young?</className>" +
            "          </factIdentifier>" +
            "          <className>boolean</className>" +
            "          <factAlias>is young?</factAlias>" +
            "          <expressionAlias>value</expressionAlias>" +
            "          <genericTypes/>" +
            "        </FactMapping>" +
            "      </factMappings>" +
            "      <dmnFilePath>src/main/resources/com/dmn-list.dmn</dmnFilePath>" +
            "      <type>DMN</type>" +
            "      <skipFromBuild>false</skipFromBuild>" +
            "    </simulationDescriptor>" +
            "    <scenarios>" +
            "      <Scenario>" +
            "        <factMappingValues>" +
            "          <FactMappingValue>" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>" +
            "          </FactMappingValue>" +
            "          <FactMappingValue>" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>" +
            "          </FactMappingValue>" +
            "          <FactMappingValue>" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>" +
            "          </FactMappingValue>" +
            "          <FactMappingValue>" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[5]/factIdentifier\"/>" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[5]/expressionIdentifier\"/>" +
            "          </FactMappingValue>" +
            "          <FactMappingValue>" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[6]/factIdentifier\"/>" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[6]/expressionIdentifier\"/>" +
            "          </FactMappingValue>" +
            "          <FactMappingValue>" +
            "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>" +
            "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>" +
            "            <rawValue class=\"string\">1</rawValue>" +
            "          </FactMappingValue>" +
            "        </factMappingValues>" +
            "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>" +
            "      </Scenario>" +
            "    </scenarios>" +
            "  </simulation>" +
            "  <imports>" +
            "    <imports/>" +
            "  </imports>" +
            "</ScenarioSimulationModel>";
    private final static String populatedScesimRule =
            "<ScenarioSimulationModel version=\"1.5\">\n" +
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
                    "              <step>Author</step>\n" +
                    "            </ExpressionElement>\n" +
                    "            <ExpressionElement>\n" +
                    "              <step>name</step>\n" +
                    "            </ExpressionElement>\n" +
                    "          </expressionElements>\n" +
                    "          <expressionIdentifier>\n" +
                    "            <name>0|1</name>\n" +
                    "            <type>GIVEN</type>\n" +
                    "          </expressionIdentifier>\n" +
                    "          <factIdentifier>\n" +
                    "            <name>0|1</name>\n" +
                    "            <className>com.Author</className>\n" +
                    "          </factIdentifier>\n" +
                    "          <className>java.lang.String</className>\n" +
                    "          <factAlias>Author</factAlias>\n" +
                    "          <expressionAlias>name</expressionAlias>\n" +
                    "        </FactMapping>\n" +
                    "        <FactMapping>\n" +
                    "          <expressionElements/>\n" +
                    "          <expressionIdentifier>\n" +
                    "            <name>0|2</name>\n" +
                    "            <type>EXPECT</type>\n" +
                    "          </expressionIdentifier>\n" +
                    "          <factIdentifier>\n" +
                    "            <name>Empty</name>\n" +
                    "            <className>java.lang.Void</className>\n" +
                    "          </factIdentifier>\n" +
                    "          <className>java.lang.Void</className>\n" +
                    "          <factAlias>INSTANCE 2</factAlias>\n" +
                    "          <expressionAlias>PROPERTY 2</expressionAlias>\n" +
                    "        </FactMapping>\n" +
                    "      </factMappings>\n" +
                    "      <type>RULE</type>\n" +
                    "      <skipFromBuild>false</skipFromBuild>\n" +
                    "    </simulationDescriptor>\n" +
                    "    <scenarios>\n" +
                    "      <Scenario>\n" +
                    "        <factMappingValues>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
                    "            <rawValue class=\"string\">First scenario</rawValue>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
                    "            <rawValue class=\"string\">Joe Smith</rawValue>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
                    "          </FactMappingValue>\n" +
                    "        </factMappingValues>\n" +
                    "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>\n" +
                    "      </Scenario>\n" +
                    "      <Scenario>\n" +
                    "        <factMappingValues>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>\n" +
                    "            <rawValue class=\"string\">Second scenario</rawValue>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>\n" +
                    "            <rawValue class=\"string\">Jeff Longhorn</rawValue>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/factIdentifier\"/>\n" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>\n" +
                    "          </FactMappingValue>\n" +
                    "        </factMappingValues>\n" +
                    "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>\n" +
                    "      </Scenario>\n" +
                    "    </scenarios>\n" +
                    "  </simulation>\n" +
                    "  <imports>\n" +
                    "    <imports>\n" +
                    "      <Import>\n" +
                    "        <type>java.lang.Integer</type>\n" +
                    "      </Import>\n" +
                    "      <Import>\n" +
                    "        <type>java.util.List</type>\n" +
                    "      </Import>\n" +
                    "    </imports>\n" +
                    "  </imports>\n" +
                    "</ScenarioSimulationModel>";
    private static final String newScesimRule =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<scesim:ScenarioSimulationModel version=\"1.5\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:scesim=\"http://www.kie.org/scesim.xsd\">" +
                    "  <scesim:simulation>" +
                    "    <scesim:simulationDescriptor>" +
                    "      <scesim:factMappings>" +
                    "        <scesim:factMapping>" +
                    "          <scesim:expressionElements class=\"linked-list\"/>" +
                    "          <scesim:expressionIdentifier>" +
                    "            <name>Index</name>" +
                    "            <scesim:type>OTHER</scesim:type>" +
                    "          </scesim:expressionIdentifier>" +
                    "          <scesim:factIdentifier>" +
                    "            <name>#</name>" +
                    "            <className>java.lang.Integer</className>" +
                    "          </scesim:factIdentifier>" +
                    "          <className>java.lang.Integer</className>" +
                    "          <scesim:factAlias>#</scesim:factAlias>" +
                    "        </scesim:factMapping>" +
                    "        <scesim:factMapping>" +
                    "          <scesim:expressionElements class=\"linked-list\"/>" +
                    "          <scesim:expressionIdentifier>" +
                    "            <name>Description</name>" +
                    "            <scesim:type>OTHER</scesim:type>" +
                    "          </scesim:expressionIdentifier>" +
                    "          <scesim:factIdentifier>" +
                    "            <name>Scenario description</name>" +
                    "            <className>java.lang.String</className>" +
                    "          </scesim:factIdentifier>" +
                    "          <className>java.lang.String</className>" +
                    "          <scesim:factAlias>Scenario description</scesim:factAlias>" +
                    "        </scesim:factMapping>" +
                    "        <scesim:factMapping>" +
                    "          <scesim:expressionElements class=\"linked-list\"/>" +
                    "          <scesim:expressionIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <scesim:type>GIVEN</scesim:type>" +
                    "          </scesim:expressionIdentifier>" +
                    "          <scesim:factIdentifier>" +
                    "            <name>Empty</name>" +
                    "            <className>java.lang.Void</className>" +
                    "          </scesim:factIdentifier>" +
                    "          <className>java.lang.Void</className>" +
                    "          <scesim:factAlias>INSTANCE 1</scesim:factAlias>" +
                    "          <scesim:expressionAlias>PROPERTY 1</scesim:expressionAlias>" +
                    "        </scesim:factMapping>" +
                    "        <scesim:factMapping>" +
                    "          <scesim:expressionElements class=\"linked-list\"/>" +
                    "          <scesim:expressionIdentifier>" +
                    "            <name>0|2</name>" +
                    "            <scesim:type>EXPECT</scesim:type>" +
                    "          </scesim:expressionIdentifier>" +
                    "          <scesim:factIdentifier reference=\"../../FactMapping[3]/factIdentifier\"/>" +
                    "          <className>java.lang.Void</className>" +
                    "          <scesim:factAlias>INSTANCE 2</scesim:factAlias>" +
                    "          <scesim:expressionAlias>PROPERTY 2</scesim:expressionAlias>" +
                    "        </scesim:factMapping>" +
                    "      </scesim:factMappings>" +
                    "      <scesim:type>RULE</scesim:type>" +
                    "      <skipFromBuild>false</skipFromBuild>" +
                    "    </scesim:simulationDescriptor>" +
                    "    <scesim:scenarios class=\"linked-list\">" +
                    "      <scesim:Scenario>" +
                    "        <scesim:factMappingValues>" +
                    "          <scesim:factMappingValue>" +
                    "            <scesim:factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>" +
                    "            <scesim:expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>" +
                    "          </scesim:factMappingValue>" +
                    "          <scesim:factMappingValue>" +
                    "            <scesim:factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>" +
                    "            <scesim:expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>" +
                    "          </scesim:factMappingValue>" +
                    "          <scesim:factMappingValue>" +
                    "            <scesim:factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>" +
                    "            <scesim:expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>" +
                    "          </scesim:factMappingValue>" +
                    "        </scesim:factMappingValues>" +
                    "        <scesim:simulationDescriptor reference=\"../../../simulationDescriptor\"/>" +
                    "      </scesim:Scenario>" +
                    "    </scesim:scenarios>" +
                    "  </scesim:simulation>" +
                    "  <scesim:imports>" +
                    "    <scesim:imports/>" +
                    "  </scesim:imports>" +
                    "</scesim:ScenarioSimulationModel>";
    public static final Map<String, String> fileMap = new HashMap<String, String>() {
        {
            put("populatedScesimADmn", populatedScesimDmn);
            put("populatedScesimBDmn", populatedScesimDmn);
            put("populatedScesimCDmn", populatedScesimDmn);
            put("populatedScesimARule", populatedScesimRule);
            put("populatedScesimBRule", populatedScesimRule);
            put("populatedScesimCRule", populatedScesimRule);
            put("newScesimRule", newScesimRule);
        }
    };

    public String getScesimFile(String fileName) {
        if (fileName.contains(fileName)) {
            return fileMap.get(fileName);
        } else {
            return newScesimRule;
        }
    }
}
