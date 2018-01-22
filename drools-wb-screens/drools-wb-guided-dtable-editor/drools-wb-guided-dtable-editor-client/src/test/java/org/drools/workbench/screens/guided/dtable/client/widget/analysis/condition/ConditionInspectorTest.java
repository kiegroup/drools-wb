/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.analysis.condition;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class ConditionInspectorTest {

    private static final List<String> ALL_VALUE_LIST = Arrays.asList("value", "val01", "val02");

    private static final Pattern52 PATTERN = mock(Pattern52.class);

    private final FieldConditionInspector a;
    private final FieldConditionInspector b;
    private final boolean inspectorsEqual;
    private final boolean inspectorsKeyEqual;

    @Test
    public void testEquals() {
        assertEquals(getDescription(), inspectorsEqual, a.equals(b));
        assertEquals(getDescription(), inspectorsEqual, b.equals(a));
    }

    @Test
    public void testKeyEquals() {
        assertEquals("FieldConditionInspectorKey.equals():" + getDescription(),
                     inspectorsKeyEqual,
                     a.getKey().equals(b.getKey()));
        assertEquals("FieldConditionInspectorKey.equals(): " + getDescription(),
                     inspectorsKeyEqual,
                     b.getKey().equals(a.getKey()));
    }

    @Test
    public void testKeyHashCode() {
        if (inspectorsKeyEqual) {
            assertEquals("FieldConditionInspectorKey.hashCode(): " + getDescription(),
                         a.getKey().hashCode(),
                         b.getKey().hashCode());
        } else {
            assertNotEquals("FieldConditionInspectorKey.hashCode(): " + getDescription(),
                            a.getKey().hashCode(),
                            b.getKey().hashCode());
        }
    }

    public ConditionInspectorTest(FieldConditionInspector a,
                                  FieldConditionInspector b,
                                  boolean inspectorsEqual,
                                  boolean inspectorsKeyEqual) {
        this.a = a;
        this.b = b;
        this.inspectorsEqual = inspectorsEqual;
        this.inspectorsKeyEqual = inspectorsKeyEqual;
    }

    @Parameters
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {getStringCondition("strField", "value", "=="), getStringCondition("strField", "value", "=="), true, true},
                {getStringCondition("strField", "value", "=="), getStringCondition("strField", "value", "!="), false, true},
                {getStringCondition("strField", "val01", "=="), getStringCondition("strField", "val02", "=="), false, true},
                {getStringCondition("strFld01", "value", "=="), getStringCondition("strFld02", "value", "=="), false, false},

                {getBooleanCondition("boolField", true, "=="), getBooleanCondition("boolField", true, "=="), true, true},
                {getBooleanCondition("boolField", true, "=="), getBooleanCondition("boolField", true, "!="), false, true},
                {getBooleanCondition("boolField", true, "=="), getBooleanCondition("boolField", false, "=="), false, true},
                {getBooleanCondition("boolFld01", true, "=="), getBooleanCondition("boolFld02", true, "=="), false, false},

                {getComparableCondition("comparable", 0, "=="), getComparableCondition("comparable", 0, "=="), true, true},
                {getComparableCondition("comparable", 0, "=="), getComparableCondition("comparable", 0, "!="), false, true},
                {getComparableCondition("comparable", 0, "=="), getComparableCondition("comparable", 1, "=="), false, true},
                {getComparableCondition("comparab01", 0, "=="), getComparableCondition("comparab02", 0, "=="), false, false},

                {getEnumCondition("enumField", "value", "=="), getEnumCondition("enumField", "value", "=="), true, true},
                {getEnumCondition("enumField", "value", "=="), getEnumCondition("enumField", "value", "!="), false, true},
                {getEnumCondition("enumField", "val01", "=="), getEnumCondition("enumField", "val02", "=="), false, true},
                {getEnumCondition("enumFld01", "value", "=="), getEnumCondition("enumFld02", "value", "=="), false, false},

                {getNumericIntegerCondition("comparable", 0, "=="), getNumericIntegerCondition("comparable", 0, "=="), true, true},
                {getNumericIntegerCondition("comparable", 0, "=="), getNumericIntegerCondition("comparable", 0, "!="), false, true},
                {getNumericIntegerCondition("comparable", 0, "=="), getNumericIntegerCondition("comparable", 1, "=="), false, true},
                {getNumericIntegerCondition("comparab01", 0, "=="), getNumericIntegerCondition("comparab02", 0, "=="), false, false},

                {getStringCondition("strField", "value", "=="), getBooleanCondition("boolField", true, "=="), false, false},
                {getStringCondition("strField", "value", "=="), getComparableCondition("comparable", 0, "=="), false, false},
                {getStringCondition("strField", "value", "=="), getEnumCondition("enumField", "value", "=="), false, false},
                {getStringCondition("strField", "value", "=="), getNumericIntegerCondition("comparable", 0, "=="), false, false},
                {getStringCondition("strField", "value", "=="), getUnrecognizedCondition("randomField", "=="), false, false},
                {getBooleanCondition("boolField", true, "=="), getComparableCondition("comparable", 0, "=="), false, false},
                {getBooleanCondition("boolField", true, "=="), getEnumCondition("enumField", "value", "=="), false, false},
                {getBooleanCondition("boolField", true, "=="), getNumericIntegerCondition("comparable", 0, "=="), false, false},
                {getBooleanCondition("boolField", true, "=="), getUnrecognizedCondition("randomField", "=="), false, false},
                {getComparableCondition("comparable", 0, "=="), getEnumCondition("enumField", "value", "=="), false, false},
                {getComparableCondition("comparable", 0, "=="), getNumericIntegerCondition("comparable", 0, "=="), false, true},
                {getComparableCondition("comparable", 0, "=="), getUnrecognizedCondition("randomField", "=="), false, false},
                {getEnumCondition("enumField", "value", "=="), getNumericIntegerCondition("comparable", 0, "=="), false, false},
                {getEnumCondition("enumField", "value", "=="), getUnrecognizedCondition("randomField", "=="), false, false},

                {getStringCondition(null, "eval(true)", ""), getStringCondition(null, "eval(true)", ""), true, true},
                {getStringCondition(null, "eval(true)", ""), getStringCondition("strField", "value", "=="), false, false}
        });
    }

    public String getDescription() {
        return format("Expected '%s' %sto be equal to '%s'.",
                      a.toHumanReadableString(),
                      inspectorsEqual ? "" : "not ",
                      b.toHumanReadableString());
    }

    private static StringConditionInspector getStringCondition(String field,
                                                               String value,
                                                               String operator) {
        return new StringConditionInspector(PATTERN, field, value, operator);
    }

    private static BooleanConditionInspector getBooleanCondition(String field,
                                                                 Boolean value,
                                                                 String operator) {
        return new BooleanConditionInspector(PATTERN, field, value, operator);
    }

    @SuppressWarnings("unchecked")
    private static ComparableConditionInspector getComparableCondition(String field,
                                                                       Comparable value,
                                                                       String operator) {
        return new ComparableConditionInspector(PATTERN, field, value, operator);
    }

    private static EnumConditionInspector getEnumCondition(String field,
                                                           String value,
                                                           String operator) {
        return new EnumConditionInspector(PATTERN, field, ALL_VALUE_LIST, value, operator);
    }

    private static NumericIntegerConditionInspector getNumericIntegerCondition(String field,
                                                                               Integer value,
                                                                               String operator) {
        return new NumericIntegerConditionInspector(PATTERN, field, value, operator);
    }

    private static UnrecognizedConditionInspector getUnrecognizedCondition(String field,
                                                                           String operator) {
        return new UnrecognizedConditionInspector(PATTERN, field, operator);
    }
}