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

    private static final String populatedScesimDmn = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<ScenarioSimulationModel version=\"1.5\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:scesim=\"http://www.kie.org/scesim.xsd\">" +
            "  <simulation>" +
            "    <simulationDescriptor>" +
            "      <factMappings>" +
            "        <FactMapping>" +
            "          <expressionElements class=\"linked-list\"/>" +
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
            "          <expressionElements class=\"linked-list\"/>" +
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
            "          <expressionElements class=\"linked-list\">" +
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
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements class=\"linked-list\">" +
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
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements class=\"linked-list\">" +
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
            "        </FactMapping>" +
            "        <FactMapping>" +
            "          <expressionElements class=\"linked-list\">" +
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
            "  <Import>" +
            "        <type>java.util.List</type>" +
            "      </Import>" +
            "  </imports>" +
            "</ScenarioSimulationModel>";
    private final static String populatedScesimRule =
            "<ScenarioSimulationModel version=\"1.5\">" +
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
                    "              <step>Author</step>" +
                    "            </ExpressionElement>" +
                    "            <ExpressionElement>" +
                    "              <step>name</step>" +
                    "            </ExpressionElement>" +
                    "          </expressionElements>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <type>GIVEN</type>" +
                    "          </expressionIdentifier>" +
                    "          <factIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <className>com.Author</className>" +
                    "          </factIdentifier>" +
                    "          <className>java.lang.String</className>" +
                    "          <factAlias>Author</factAlias>" +
                    "          <expressionAlias>name</expressionAlias>" +
                    "        </FactMapping>" +
                    "        <FactMapping>" +
                    "          <expressionElements/>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|2</name>" +
                    "            <type>EXPECT</type>" +
                    "          </expressionIdentifier>" +
                    "          <factIdentifier>" +
                    "            <name>Empty</name>" +
                    "            <className>java.lang.Void</className>" +
                    "          </factIdentifier>" +
                    "          <className>java.lang.Void</className>" +
                    "          <factAlias>INSTANCE 2</factAlias>" +
                    "          <expressionAlias>PROPERTY 2</expressionAlias>" +
                    "        </FactMapping>" +
                    "      </factMappings>" +
                    "      <type>RULE</type>" +
                    "      <skipFromBuild>false</skipFromBuild>" +
                    "    </simulationDescriptor>" +
                    "    <scenarios>" +
                    "      <Scenario>" +
                    "        <factMappingValues>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>" +
                    "            <rawValue class=\"string\">First scenario</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>" +
                    "            <rawValue class=\"string\">Joe Smith</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>" +
                    "          </FactMappingValue>" +
                    "        </factMappingValues>" +
                    "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>" +
                    "      </Scenario>" +
                    "      <Scenario>" +
                    "        <factMappingValues>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping/expressionIdentifier\"/>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[2]/expressionIdentifier\"/>" +
                    "            <rawValue class=\"string\">Second scenario</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[3]/expressionIdentifier\"/>" +
                    "            <rawValue class=\"string\">Jeff Longhorn</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "            <factIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/factIdentifier\"/>" +
                    "            <expressionIdentifier reference=\"../../../../../simulationDescriptor/factMappings/FactMapping[4]/expressionIdentifier\"/>" +
                    "          </FactMappingValue>" +
                    "        </factMappingValues>" +
                    "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>" +
                    "      </Scenario>" +
                    "    </scenarios>" +
                    "  </simulation>" +
                    "  <imports>" +
                    "    <imports>" +
                    "      <Import>" +
                    "        <type>java.lang.Integer</type>" +
                    "      </Import>" +
                    "      <Import>" +
                    "        <type>java.util.List</type>" +
                    "      </Import>" +
                    "    </imports>" +
                    "  </imports>" +
                    "</ScenarioSimulationModel>";
    private static final String newScesimRule =
            "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                    "<ScenarioSimulationModel version=\"1.5\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:scesim=\"http://www.kie.org/scesim.xsd\">" +
                    "  <simulation>" +
                    "    <simulationDescriptor>" +
                    "      <factMappings>" +
                    "        <FactMapping>" +
                    "          <expressionElements class=\"linked-list\"/>" +
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
                    "          <expressionElements class=\"linked-list\"/>" +
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
                    "          <expressionElements class=\"linked-list\"/>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <type>GIVEN</type>" +
                    "          </expressionIdentifier>" +
                    "          <factIdentifier>" +
                    "            <name>Empty</name>" +
                    "            <className>java.lang.Void</className>" +
                    "          </factIdentifier>" +
                    "          <className>java.lang.Void</className>" +
                    "          <factAlias>INSTANCE 1</factAlias>" +
                    "          <expressionAlias>PROPERTY 1</expressionAlias>" +
                    "        </FactMapping>" +
                    "        <FactMapping>" +
                    "          <expressionElements class=\"linked-list\"/>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|2</name>" +
                    "            <type>EXPECT</type>" +
                    "          </expressionIdentifier>" +
                    "          <factIdentifier reference=\"../../FactMapping[3]/factIdentifier\"/>" +
                    "          <className>java.lang.Void</className>" +
                    "          <factAlias>INSTANCE 2</factAlias>" +
                    "          <expressionAlias>PROPERTY 2</expressionAlias>" +
                    "        </FactMapping>" +
                    "      </factMappings>" +
                    "      <type>RULE</type>" +
                    "      <skipFromBuild>false</skipFromBuild>" +
                    "    </simulationDescriptor>" +
                    "    <scenarios class=\"linked-list\">" +
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
                    "        </factMappingValues>" +
                    "        <simulationDescriptor reference=\"../../../simulationDescriptor\"/>" +
                    "      </Scenario>" +
                    "    </scenarios>" +
                    "  </simulation>" +
                    "  <imports>" +
                    "    <imports/>" +
                    "  </imports>" +
                    "</ScenarioSimulationModel>";
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
