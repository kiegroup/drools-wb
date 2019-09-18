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

import org.drools.scenariosimulation.api.ConstantsHolder;
import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AbstractScenarioValidationTest {

    @Test
    public void isToSkip() {
        FactMapping otherFactMapping = new FactMapping(FactIdentifier.INDEX,
                                                       ExpressionIdentifier.create("value", FactMappingType.OTHER));
        assertTrue(AbstractScenarioValidation.isToSkip(otherFactMapping));
        FactMapping descriptionFactMapping = new FactMapping(FactIdentifier.DESCRIPTION,
                                                             ExpressionIdentifier.create("value", FactMappingType.OTHER));
        assertTrue(AbstractScenarioValidation.isToSkip(descriptionFactMapping));
        FactMapping emptyFactMapping = new FactMapping(FactIdentifier.EMPTY,
                                                       ExpressionIdentifier.create("value", FactMappingType.GIVEN));
        assertTrue(AbstractScenarioValidation.isToSkip(emptyFactMapping));
        FactMapping expressionFactMapping = new FactMapping(FactIdentifier.create("myType", "anyClass"),
                                                            ExpressionIdentifier.create(ConstantsHolder.EXPRESSION_CLASSNAME,
                                                                                        FactMappingType.GIVEN));
        assertTrue(AbstractScenarioValidation.isToSkip(expressionFactMapping));
        FactMapping simpleTypeFactMapping = new FactMapping(FactIdentifier.create("mySimpleType", "com.mySimpleType"),
                                                                      ExpressionIdentifier.create("value", FactMappingType.GIVEN));
        assertTrue(AbstractScenarioValidation.isToSkip(simpleTypeFactMapping));
        FactMapping complexTypeFactMapping = new FactMapping(FactIdentifier.create("myComplexType", "com.myComplexType"),
                                                                        ExpressionIdentifier.create("value", FactMappingType.GIVEN));
        complexTypeFactMapping.addExpressionElement("name", "com.lang.String");
        assertFalse(AbstractScenarioValidation.isToSkip(complexTypeFactMapping));
    }
}