/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.util.List;

import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.kie.pmml.api.enums.DATA_TYPE;
import org.kie.pmml.api.models.PMMLModel;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.drools.scenariosimulation.api.utils.ConstantsHolder.VALUE;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class PMMLScenarioValidationTest extends AbstractScenarioValidationTest {

    @Mock
    private PMMLModel pmmlModelMock;

    private Settings settingsLocal;

    @Before
    public void init() {
        settingsLocal = new Settings();
        settingsLocal.setType(ScenarioSimulationModel.Type.PMML);
    }

    @Test
    public void validate() {
        PMMLScenarioValidation validationSpy = spy(new PMMLScenarioValidation() {
            @Override
            protected PMMLModel getPMMLModel(KieContainer kieContainer, String pmmlModelName) {
                return pmmlModelMock;
            }
        });

        // Test 0 - skip empty or not GIVEN/EXPECT columns
        Simulation test0 = new Simulation();
        test0.getScesimModelDescriptor().addFactMapping(
                FactIdentifier.DESCRIPTION,
                ExpressionIdentifier.create(VALUE, FactMappingType.OTHER));
        test0.getScesimModelDescriptor().addFactMapping(
                FactIdentifier.EMPTY,
                ExpressionIdentifier.create(VALUE, FactMappingType.GIVEN));

        List<FactMappingValidationError> errorsTest0 = validationSpy.validate(test0, settingsLocal, null);
        checkResult(errorsTest0);

        // Test 1 - simple types
        for (DATA_TYPE dataType : DATA_TYPE.values()) {
            Simulation test1 = new Simulation();
            String identifierName = String.format("my%sType", dataType.getName());
            test1.getScesimModelDescriptor().addFactMapping(
                    FactIdentifier.create(identifierName, dataType.getName()),
                    ExpressionIdentifier.create(VALUE, FactMappingType.GIVEN));
            List<FactMappingValidationError> errorsTest1 = validationSpy.validate(test1, settingsLocal, null);
            checkResult(errorsTest1);
        }
  }

}