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
            "<ScenarioSimulationModel version=\"1.8\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:scesim=\"http://www.kie.org/scesim.xsd\">" +
            " <simulation>\n" +
            "    <scesimModelDescriptor>\n" +
            "      <factMappings>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements class=\"linked-list\"/>\n" +
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
            "          <expressionElements class=\"linked-list\"/>\n" +
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
            "          <expressionElements class=\"linked-list\">\n" +
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
            "          <genericTypes class=\"singleton-list\">\n" +
            "            <string>address</string>\n" +
            "          </genericTypes>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements class=\"linked-list\">\n" +
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
            "           <factIdentifier>\n" +
            "            <name>person</name>\n" +
            "            <className>person</className>\n" +
            "          </factIdentifier>" +
            "          <className>age</className>\n" +
            "          <factAlias>person</factAlias>\n" +
            "          <expressionAlias>age</expressionAlias>\n" +
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements class=\"linked-list\">\n" +
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
            "        </FactMapping>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements class=\"linked-list\">\n" +
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
            "        </FactMapping>\n" +
            "      </factMappings>\n" +
            "    </scesimModelDescriptor>\n" +
            "    <scesimData class=\"linked-list\">\n" +
            "      <Scenario>\n" +
            "        <factMappingValues>\n" +
            "          <FactMappingValue>\n" +
            "          <factIdentifier>\n" +
            "            <name>#</name>\n" +
            "            <className>java.lang.Integer</className>\n" +
            "          </factIdentifier>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>Index</name>\n" +
            "            <type>OTHER</type>\n" +
            "          </expressionIdentifier>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "          <factIdentifier>\n" +
            "            <name>Scenario description</name>\n" +
            "            <className>java.lang.String</className>\n" +
            "          </factIdentifier>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>Description</name>\n" +
            "            <type>OTHER</type>\n" +
            "          </expressionIdentifier>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "          <factIdentifier>\n" +
            "            <name>person</name>\n" +
            "            <className>person</className>\n" +
            "          </factIdentifier>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|1</name>\n" +
            "            <type>GIVEN</type>\n" +
            "          </expressionIdentifier>\n" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|2</name>\n" +
            "            <type>GIVEN</type>\n" +
            "          </expressionIdentifier>\n" +
            "           <factIdentifier>\n" +
            "            <name>person</name>\n" +
            "            <className>person</className>\n" +
            "          </factIdentifier>" +
            "          </FactMappingValue>\n" +
            "          <FactMappingValue>\n" +
            "          <expressionIdentifier>\n" +
            "            <name>0|3</name>\n" +
            "            <type>EXPECT</type>\n" +
            "          </expressionIdentifier>\n" +
            "          <factIdentifier>\n" +
            "            <name>is the first country European?</name>\n" +
            "            <className>is the first country European?</className>\n" +
            "          </factIdentifier>\n" +
            "          </FactMappingValue>\n" +
            "        </factMappingValues>\n" +
            "      </Scenario>\n" +
            "    </scesimData>\n" +
            "  </simulation>\n" +
            "  <background>\n" +
            "    <scesimModelDescriptor>\n" +
            "      <factMappings>\n" +
            "        <FactMapping>\n" +
            "          <expressionElements class=\"linked-list\">\n" +
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
            "          <genericTypes class=\"singleton-list\">\n" +
            "            <string>address</string>\n" +
            "          </genericTypes>\n" +
            "        </FactMapping>\n" +
            "      </factMappings>\n" +
            "    </scesimModelDescriptor>\n" +
            "    <scesimData class=\"linked-list\">\n" +
            "    </scesimData>\n" +
            "  </background>\n" +
            "  <settings>\n" +
            "      <dmnFilePath>src/main/resources/com/list.dmn</dmnFilePath>\n" +
            "      <type>DMN</type>\n" +
            "      <dmnNamespace>https://github.com/kiegroup/drools/kie-dmn/_CC8924B0-D729-4D70-9588-039B5824FFE9</dmnNamespace>\n" +
            "      <dmnName>a1Collection</dmnName>\n" +
            "      <skipFromBuild>false</skipFromBuild>\n" +
            "  </settings>" +
            "  <imports>\n" +
            "    <imports/>\n" +
            "  </imports>" +
            "</ScenarioSimulationModel>";
    private final static String populatedScesimRule =
            "<ScenarioSimulationModel version=\"1.8\">\n" +
                    "  <simulation>\n" +
                    "    <scesimModelDescriptor>" +
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
                    "    </scesimModelDescriptor>" +
                    "    <scesimData class=\"linked-list\">" +
                    "      <Scenario>" +
                    "        <factMappingValues>" +
                    "          <FactMappingValue>" +
                    "             <factIdentifier>" +
                    "            <name>#</name>" +
                    "            <className>java.lang.Integer</className>" +
                    "          </factIdentifier>" +
                    "          <expressionIdentifier>" +
                    "            <name>Index</name>" +
                    "            <type>OTHER</type>" +
                    "          </expressionIdentifier>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "          <factIdentifier>" +
                    "            <name>Scenario description</name>" +
                    "            <className>java.lang.String</className>" +
                    "          </factIdentifier>" +
                    "            <expressionIdentifier>" +
                    "            <name>Description</name>" +
                    "            <type>OTHER</type>" +
                    "          </expressionIdentifier>" +
                    "            <rawValue class=\"string\">First scenario</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "          <factIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <className>com.Author</className>" +
                    "          </factIdentifier>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <type>GIVEN</type>" +
                    "          </expressionIdentifier>" +
                    "            <rawValue class=\"string\">Joe Smith</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "          <factIdentifier>" +
                    "            <name>Empty</name>" +
                    "            <className>java.lang.Void</className>" +
                    "          </factIdentifier>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|2</name>" +
                    "            <type>EXPECT</type>" +
                    "          </expressionIdentifier>" +
                    "          </FactMappingValue>" +
                    "        </factMappingValues>" +
                    "      </Scenario>" +
                    "      <Scenario>" +
                    "        <factMappingValues>" +
                    "          <FactMappingValue>" +
                    "             <factIdentifier>" +
                    "            <name>#</name>" +
                    "            <className>java.lang.Integer</className>" +
                    "          </factIdentifier>" +
                    "          <expressionIdentifier>" +
                    "            <name>Index</name>" +
                    "            <type>OTHER</type>" +
                    "          </expressionIdentifier>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "          <factIdentifier>" +
                    "            <name>Scenario description</name>" +
                    "            <className>java.lang.String</className>" +
                    "          </factIdentifier>" +
                    "            <expressionIdentifier>" +
                    "            <name>Description</name>" +
                    "            <type>OTHER</type>" +
                    "          </expressionIdentifier>" +
                    "            <rawValue class=\"string\">Second scenario</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "          <factIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <className>com.Author</className>" +
                    "          </factIdentifier>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <type>GIVEN</type>" +
                    "          </expressionIdentifier>" +
                    "            <rawValue class=\"string\">Bubba Black</rawValue>" +
                    "          </FactMappingValue>" +
                    "          <FactMappingValue>" +
                    "          <factIdentifier>" +
                    "            <name>Empty</name>" +
                    "            <className>java.lang.Void</className>" +
                    "          </factIdentifier>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|2</name>" +
                    "            <type>EXPECT</type>" +
                    "          </expressionIdentifier>" +
                    "          </FactMappingValue>" +
                    "        </factMappingValues>" +
                    "      </Scenario>" +
                    "    </scesimData>" +
                    "  </simulation>" +
                    "  <background>\n" +
                    "    <scesimModelDescriptor>\n" +
                    "      <factMappings>\n" +
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
                    "      </factMappings>\n" +
                    "    </scesimModelDescriptor>\n" +
                    "    <scesimData class=\"linked-list\">\n" +
                    "      <BackgroundData>\n" +
                    "        <factMappingValues>\n" +
                    "          <FactMappingValue>" +
                    "          <factIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <className>com.Author</className>" +
                    "          </factIdentifier>" +
                    "          <expressionIdentifier>" +
                    "            <name>0|1</name>" +
                    "            <type>GIVEN</type>" +
                    "          </expressionIdentifier>" +
                    "            <rawValue class=\"string\">Arthur C. Doyle</rawValue>" +
                    "          </FactMappingValue>" +
                    "        </factMappingValues>\n" +
                    "      </BackgroundData>\n" +
                    "    </scesimData>\n" +
                    "  </background>" +
                    "  <settings>\n" +
                    "    <type>RULE</type>\n" +
                    "    <skipFromBuild>false</skipFromBuild>\n" +
                    "    <stateless>false</stateless>\n" +
                    "  </settings>" +
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
            "<ScenarioSimulationModel version=\"1.8\">\n" +
                    "  <simulation>\n" +
                    "    <scesimModelDescriptor>\n" +
                    "      <factMappings>\n" +
                    "        <FactMapping>\n" +
                    "          <expressionElements class=\"linked-list\"/>\n" +
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
                    "          <factMappingValueType>NOT_EXPRESSION</factMappingValueType>\n" +
                    "        </FactMapping>\n" +
                    "        <FactMapping>\n" +
                    "          <expressionElements class=\"linked-list\"/>\n" +
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
                    "          <factMappingValueType>NOT_EXPRESSION</factMappingValueType>\n" +
                    "        </FactMapping>\n" +
                    "        <FactMapping>\n" +
                    "          <expressionElements class=\"linked-list\"/>\n" +
                    "          <expressionIdentifier>\n" +
                    "            <name>1|1</name>\n" +
                    "            <type>GIVEN</type>\n" +
                    "          </expressionIdentifier>\n" +
                    "          <factIdentifier>\n" +
                    "            <name>Empty</name>\n" +
                    "            <className>java.lang.Void</className>\n" +
                    "          </factIdentifier>\n" +
                    "          <className>java.lang.Void</className>\n" +
                    "          <factAlias>INSTANCE 1</factAlias>\n" +
                    "          <expressionAlias>PROPERTY 1</expressionAlias>\n" +
                    "          <factMappingValueType>NOT_EXPRESSION</factMappingValueType>\n" +
                    "        </FactMapping>\n" +
                    "        <FactMapping>\n" +
                    "          <expressionElements class=\"linked-list\"/>\n" +
                    "          <expressionIdentifier>\n" +
                    "            <name>1|2</name>\n" +
                    "            <type>EXPECT</type>\n" +
                    "          </expressionIdentifier>\n" +
                    "          <factIdentifier>\n" +
                    "            <name>Empty</name>\n" +
                    "            <className>java.lang.Void</className>\n" +
                    "          </factIdentifier>\n" +
                    "          <className>java.lang.Void</className>\n" +
                    "          <factAlias>INSTANCE 2</factAlias>\n" +
                    "          <expressionAlias>PROPERTY 2</expressionAlias>\n" +
                    "          <factMappingValueType>NOT_EXPRESSION</factMappingValueType>\n" +
                    "        </FactMapping>\n" +
                    "      </factMappings>\n" +
                    "    </scesimModelDescriptor>\n" +
                    "    <scesimData class=\"linked-list\">\n" +
                    "      <Scenario>\n" +
                    "        <factMappingValues>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier>\n" +
                    "              <name>Scenario description</name>\n" +
                    "              <className>java.lang.String</className>\n" +
                    "            </factIdentifier>\n" +
                    "            <expressionIdentifier>\n" +
                    "              <name>Description</name>\n" +
                    "              <type>OTHER</type>\n" +
                    "            </expressionIdentifier>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier>\n" +
                    "              <name>Empty</name>\n" +
                    "              <className>java.lang.Void</className>\n" +
                    "            </factIdentifier>\n" +
                    "            <expressionIdentifier>\n" +
                    "              <name>1|1</name>\n" +
                    "              <type>GIVEN</type>\n" +
                    "            </expressionIdentifier>\n" +
                    "          </FactMappingValue>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier>\n" +
                    "              <name>Empty</name>\n" +
                    "              <className>java.lang.Void</className>\n" +
                    "            </factIdentifier>\n" +
                    "            <expressionIdentifier>\n" +
                    "              <name>1|2</name>\n" +
                    "              <type>EXPECT</type>\n" +
                    "            </expressionIdentifier>\n" +
                    "          </FactMappingValue>\n" +
                    "        </factMappingValues>\n" +
                    "      </Scenario>\n" +
                    "    </scesimData>\n" +
                    "  </simulation>\n" +
                    "  <background>\n" +
                    "    <scesimModelDescriptor>\n" +
                    "      <factMappings>\n" +
                    "        <FactMapping>\n" +
                    "          <expressionElements class=\"linked-list\"/>\n" +
                    "          <expressionIdentifier>\n" +
                    "            <name>1|1</name>\n" +
                    "            <type>GIVEN</type>\n" +
                    "          </expressionIdentifier>\n" +
                    "          <factIdentifier>\n" +
                    "            <name>Empty</name>\n" +
                    "            <className>java.lang.Void</className>\n" +
                    "          </factIdentifier>\n" +
                    "          <className>java.lang.Void</className>\n" +
                    "          <factAlias>INSTANCE 1</factAlias>\n" +
                    "          <expressionAlias>PROPERTY 1</expressionAlias>\n" +
                    "          <factMappingValueType>NOT_EXPRESSION</factMappingValueType>\n" +
                    "        </FactMapping>\n" +
                    "      </factMappings>\n" +
                    "    </scesimModelDescriptor>\n" +
                    "    <scesimData class=\"linked-list\">\n" +
                    "      <BackgroundData>\n" +
                    "        <factMappingValues>\n" +
                    "          <FactMappingValue>\n" +
                    "            <factIdentifier>\n" +
                    "              <name>Empty</name>\n" +
                    "              <className>java.lang.Void</className>\n" +
                    "            </factIdentifier>\n" +
                    "            <expressionIdentifier>\n" +
                    "              <name>1|1</name>\n" +
                    "              <type>GIVEN</type>\n" +
                    "            </expressionIdentifier>\n" +
                    "          </FactMappingValue>\n" +
                    "        </factMappingValues>\n" +
                    "      </BackgroundData>\n" +
                    "    </scesimData>\n" +
                    "  </background>\n" +
                    "  <settings>\n" +
                    "    <type>RULE</type>\n" +
                    "    <skipFromBuild>false</skipFromBuild>\n" +
                    "    <stateless>false</stateless>\n" +
                    "  </settings>\n" +
                    "  <imports>\n" +
                    "    <imports/>\n" +
                    "  </imports>\n" +
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
        if (fileMap.get(fileName) != null) {
            return fileMap.get(fileName);
        } else {
            return newScesimRule;
        }
    }
}