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

import java.util.Collections;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BaseExpressionOperatorTest {

    private static final ClassLoader classLoader = BaseExpressionOperatorTest.class.getClassLoader();

    @Test
    public void cleanValueFromOperator() {
        String rawValue = "Test";
        assertEquals(1, BaseExpressionOperator.cleanValueFromOperator(rawValue).size());
        assertEquals(rawValue, BaseExpressionOperator.cleanValueFromOperator(rawValue).get(0));

        rawValue = " Test ";
        assertEquals(rawValue.trim(), BaseExpressionOperator.cleanValueFromOperator(rawValue).get(0));

        rawValue = "= Test ";
        assertEquals("Test", BaseExpressionOperator.cleanValueFromOperator(rawValue).get(0));

        rawValue = " != Test ";
        assertEquals("Test", BaseExpressionOperator.cleanValueFromOperator(rawValue).get(0));

        rawValue = " ";
        assertEquals(0, BaseExpressionOperator.cleanValueFromOperator(rawValue).size());

        rawValue = "= ";
        assertEquals("", BaseExpressionOperator.cleanValueFromOperator(rawValue).get(0));
    }

    @Test
    public void cleanValueEmptyAndNullString() {
        String rawValue = "";
        assertEquals(Collections.emptyList(), BaseExpressionOperator.cleanValueFromOperator(rawValue));

        assertEquals(0, BaseExpressionOperator.cleanValueFromOperator(null).size());

        rawValue = " =  ";
        assertEquals("", BaseExpressionOperator.cleanValueFromOperator(rawValue).get(0));
    }

    @Test
    public void findOperator() {
        String rawValue = "Test";
        assertEquals(BaseExpressionOperator.EQUALS, BaseExpressionOperator.findOperator(rawValue));

        rawValue = " Test ";
        assertEquals(BaseExpressionOperator.EQUALS, BaseExpressionOperator.findOperator(rawValue));

        rawValue = "= Test ";
        assertEquals(BaseExpressionOperator.EQUALS, BaseExpressionOperator.findOperator(rawValue));

        rawValue = "!= Test ";
        assertEquals(BaseExpressionOperator.NOT_EQUALS, BaseExpressionOperator.findOperator(rawValue));
    }

    @Test
    public void equalsTest() {
        MyTestClass test1 = new MyTestClass();
        MyTestClass test2 = new MyTestClass();
        MyComparableTestClass comparableTest1 = new MyComparableTestClass();

        // Tested via Objects.equals
        assertTrue(BaseExpressionOperator.EQUALS.eval(test1, test1, classLoader));
        assertFalse(BaseExpressionOperator.EQUALS.eval(test1, test2, classLoader));
        // Tested via Comparable.compareTo
        assertTrue(BaseExpressionOperator.EQUALS.eval(comparableTest1, comparableTest1, classLoader));

        assertTrue(BaseExpressionOperator.EQUALS.eval("1", 1, classLoader));
    }

    @Test
    public void notEqualsTest() {
        MyTestClass test1 = new MyTestClass();
        MyTestClass test2 = new MyTestClass();
        MyComparableTestClass comparableTest1 = new MyComparableTestClass();

        // Tested via Objects.equals
        assertFalse(BaseExpressionOperator.NOT_EQUALS.eval(test1, test1, classLoader));
        assertTrue(BaseExpressionOperator.NOT_EQUALS.eval(test1, test2, classLoader));
        // Tested via Comparable.compareTo
        assertFalse(BaseExpressionOperator.NOT_EQUALS.eval(comparableTest1, comparableTest1, classLoader));

        assertTrue(BaseExpressionOperator.NOT_EQUALS.eval("<> 1", 2, classLoader));
    }

    private class MyTestClass {

    }

    private class MyComparableTestClass implements Comparable<MyComparableTestClass> {

        @Override
        public int compareTo(MyComparableTestClass o) {
            return 0;
        }
    }
}