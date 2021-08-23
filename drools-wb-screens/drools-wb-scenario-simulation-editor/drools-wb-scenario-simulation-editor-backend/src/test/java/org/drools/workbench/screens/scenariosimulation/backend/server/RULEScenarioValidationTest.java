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
package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.drools.workbench.screens.scenariosimulation.utils.ScenarioSimulationI18nServerMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.runtime.KieContainer;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.drools.scenariosimulation.api.utils.ConstantsHolder.VALUE;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RULEScenarioValidationTest extends AbstractScenarioValidationTest {

    @Mock
    private KieContainer kieContainerMock;

    private Settings settingsLocal;

    @Before
    public void init() {
        settingsLocal = new Settings();
        settingsLocal.setType(ScenarioSimulationModel.Type.RULE);
        when(kieContainerMock.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
        when(kieContainerMock.getClassLoader()).thenReturn(Thread.currentThread().getContextClassLoader());
    }

    @Test
    public void validate() {
        RULEScenarioValidation validation = new RULEScenarioValidation();

        // Test 0 - skip empty or not GIVEN/EXPECT columns
        Simulation test0 = new Simulation();
        test0.getScesimModelDescriptor().addFactMapping(
                FactIdentifier.DESCRIPTION,
                ExpressionIdentifier.create(VALUE, FactMappingType.OTHER));
        test0.getScesimModelDescriptor().addFactMapping(
                FactIdentifier.EMPTY,
                ExpressionIdentifier.create(VALUE, FactMappingType.GIVEN));

        List<FactMappingValidationError> errorsTest0 = validation.validate(test0, settingsLocal, kieContainerMock);
        checkResult(errorsTest0);

        // Test 1 - simple type
        Simulation test1 = new Simulation();
        test1.getScesimModelDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", int.class.getCanonicalName()),
                ExpressionIdentifier.create(VALUE, FactMappingType.GIVEN));

        List<FactMappingValidationError> errorsTest1 = validation.validate(test1, settingsLocal, kieContainerMock);
        checkResult(errorsTest1);

        FactMapping mySimpleType = test1.getScesimModelDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", "notValidClass"),
                ExpressionIdentifier.create(VALUE, FactMappingType.GIVEN));
        mySimpleType.addExpressionElement("notValidClass", "notValidClass");

        errorsTest1 = validation.validate(test1, settingsLocal, kieContainerMock);
        checkResult(errorsTest1, new ExpectedError("Impossible to load class notValidClass"));

        // Test 2 - nested field
        Simulation test2 = new Simulation();
        // nameFM is valid
        FactIdentifier myFactIdentifier = FactIdentifier.create("mySimpleType", SampleBean.class.getCanonicalName());
        FactMapping nameFM = test2.getScesimModelDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("name", FactMappingType.GIVEN));
        nameFM.addExpressionElement("SampleBean", String.class.getCanonicalName());
        nameFM.addExpressionElement("name", String.class.getCanonicalName());

        // parentFM is valid
        FactMapping parentFM = test2.getScesimModelDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("parent", FactMappingType.EXPECT));
        parentFM.addExpressionElement("SampleBean", SampleBean.class.getCanonicalName());
        parentFM.addExpressionElement("parent", SampleBean.class.getCanonicalName());

        List<FactMappingValidationError> errorsTest2 = validation.validate(test2, settingsLocal, kieContainerMock);
        checkResult(errorsTest2);

        // parentFM is not valid anymore
        parentFM.addExpressionElement("notExisting", String.class.getCanonicalName());
        errorsTest2 = validation.validate(test2, settingsLocal, kieContainerMock);
        checkResult(errorsTest2, new ExpectedError("Impossible to find field with name 'notExisting' in class org.drools.workbench.screens.scenariosimulation.backend.server.SampleBean"));

        // nameWrongTypeFM has a wrong type
        FactMapping nameWrongTypeFM = test2.getScesimModelDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("parent2", FactMappingType.EXPECT));
        nameWrongTypeFM.addExpressionElement("SampleBean", Integer.class.getCanonicalName());
        nameWrongTypeFM.addExpressionElement("name", Integer.class.getCanonicalName());
        errorsTest2 = validation.validate(test2, settingsLocal, kieContainerMock);
        checkResult(errorsTest2,
                    new ExpectedError("Impossible to find field with name 'notExisting' in class org.drools.workbench.screens.scenariosimulation.backend.server.SampleBean"),
                    new ExpectedError(ScenarioSimulationI18nServerMessage.SCENARIO_VALIDATION_FIELD_CHANGED_ERROR, Arrays.asList("java.lang.Integer", "java.lang.String")));

        // Test 3 - list
        Simulation test3 = new Simulation();
        // topLevelListFM is valid
        FactMapping topLevelListFM = test3.getScesimModelDescriptor().addFactMapping(
                FactIdentifier.create("mySimpleType", List.class.getCanonicalName()),
                ExpressionIdentifier.create("name", FactMappingType.GIVEN));
        topLevelListFM.addExpressionElement("List", List.class.getCanonicalName());
        topLevelListFM.setGenericTypes(Collections.singletonList(String.class.getCanonicalName()));

        // addressesFM is valid
        FactMapping addressesFM = test3.getScesimModelDescriptor().addFactMapping(
                myFactIdentifier,
                ExpressionIdentifier.create("addresses", FactMappingType.EXPECT));
        addressesFM.addExpressionElement("SampleBean", List.class.getCanonicalName());
        addressesFM.addExpressionElement("addresses", List.class.getCanonicalName());
        addressesFM.setGenericTypes(Collections.singletonList(String.class.getCanonicalName()));

        List<FactMappingValidationError> errorsTest3 = validation.validate(test3, settingsLocal, kieContainerMock);
        checkResult(errorsTest3);
    }

}