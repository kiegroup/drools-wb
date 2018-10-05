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

package org.drools.workbench.screens.scenariosimulation.backend.server.expression;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class BaseExpressionEvaluatorTest {

    private final static ClassLoader classLoader = BaseExpressionEvaluatorTest.class.getClassLoader();

    @Test
    public void evaluate() {
        BaseExpressionEvaluator baseExpressionEvaluator = new BaseExpressionEvaluator(classLoader);

        assertTrue(baseExpressionEvaluator.evaluate(1, 1));
        assertTrue(baseExpressionEvaluator.evaluate("1", 1));
        assertTrue(baseExpressionEvaluator.evaluate("!= 1", 2));
        assertFalse(baseExpressionEvaluator.evaluate("<> Test", "Test"));
        assertTrue(baseExpressionEvaluator.evaluate("= Test", "Test"));
    }

    @Test
    public void extractSingleValueTest() {
        BaseExpressionEvaluator baseExpressionEvaluator = new BaseExpressionEvaluator(classLoader);

        Object raw = new Object();
        assertEquals(raw, baseExpressionEvaluator.extractSingleValue(raw));

        raw = "SimpleString";
        assertEquals(raw, baseExpressionEvaluator.extractSingleValue(raw));

        raw = "= SimpleString";
        assertEquals("SimpleString", baseExpressionEvaluator.extractSingleValue(raw));

        assertNull(baseExpressionEvaluator.extractSingleValue(null));

        // FIXME test operator with multiple results
    }
}