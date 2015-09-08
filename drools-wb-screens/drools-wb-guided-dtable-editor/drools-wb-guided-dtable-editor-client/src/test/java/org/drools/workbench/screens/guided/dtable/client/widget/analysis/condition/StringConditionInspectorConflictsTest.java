/*
 * Copyright 2015 JBoss Inc
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

import static java.lang.String.format;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;

@RunWith( Parameterized.class )
public class StringConditionInspectorConflictsTest {

    private final String value1;
    private final String value2;
    private final String operator1;
    private final String operator2;
    private final boolean conflictExpected;

    @Test
    public void parametrizedTest() {
        StringConditionInspector a = getCondition( value1, operator1 );
        StringConditionInspector b = getCondition( value2, operator2 );

        assertEquals( getAssertDescription(a, b, conflictExpected), conflictExpected, a.conflicts( b ) );
        assertEquals( getAssertDescription(b, a, conflictExpected), conflictExpected, b.conflicts( a ) );
    }

    public StringConditionInspectorConflictsTest( String operator1,
                                                              String value1,
                                                              String operator2,
                                                              String value2,
                                                              boolean conflictExpected) {
        this.value1 = value1;
        this.value2 = value2;
        this.operator1 = operator1;
        this.operator2 = operator2;
        this.conflictExpected = conflictExpected;
    }

    @Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList( new Object[][]{
            // matches and soundslike are probably not doable...
            // op1, val1, op2, val2, conflicts
            { "==", "a", "==", "a", false },
            { "!=", "a", "!=", "a", false },
            { ">", "a", ">", "a", false },
            { ">=", "a", ">=", "a", false },
            { "<", "a", "<", "a", false },
            { "<=", "a", "<=", "a", false },
            { "in", "a,b", "in", "a,b", false },
            { "not in", "a,b", "not in", "a,b", false },
            { "matches", "a", "matches", "a", false },
            { "soundslike", "a", "soundslike", "a", false },

            { "==", "a", "!=", "b", false },
            { "==", "a", ">", " ", false },
            { "==", "a", ">=", "a", false },
            { "==", "a", "<", "b", false },
            { "==", "a", "<=", "a", false },
            { "==", "a", "in", "a,b", false },
            { "==", "a", "not in", "b,c,d", false },
            { "==", "a", "matches", "a", false },
            { "==", "a", "soundslike", "a", false },

            { "==", "a", "!=", "a", true },
            { "==", "a", ">", "a", true },
            { "==", "a", ">=", "b", true },
            { "==", "a", "<", "a", true },
            { "==", "a", "<=", " ", true },
            { "==", "a", "in", "b,c,d", true },
            { "==", "a", "not in", "a,b", true },
            { "==", "a", "matches", "b", true },
            { "==", "a", "soundslike", "b", true },

            { "!=", "a", "!=", "b", false },
            { "!=", "a", ">", " ", false },
            { "!=", "a", ">=", "a", false },
            { "!=", "a", "<", "b", false },
            { "!=", "a", "<=", "a", false },
            { "!=", "a", "in", "a,b", false },
            { "!=", "a", "in", "b,c,d", false },
            { "!=", "a", "not in", "b,c,d", false },
            { "!=", "a", "matches", "b", false },
            { "!=", "a", "soundslike", "b", false },

            { "!=", "a", "in", "a", true },
            { "!=", "a", "matches", "a", true },
            { "!=", "a", "soundslike", "a", true },

            { ">", "a", ">", "b", false },
            { ">", "a", ">=", "a", false },
            { ">", "a", "<", "c", false },
            { ">", "a", "<=", "b", false },
            { ">", "a", "in", "a,b", false },
            { ">", "a", "not in", "b,c,d", false },
            { ">", "a", "matches", "b", false },
            { ">", "a", "soundslike", "b", false },

            { ">", "a", "<", "a", true },
            { ">", "a", "<=", "a", true },
            { ">", "a", "in", "0,1,A,B,a", true },
            { ">", "a", "matches", "a", true },
            { ">", "a", "soundslike", "", true },

            { ">=", "a", ">=", "b", false },
            { ">=", "a", "<", "b", false },
            { ">=", "a", "<=", "a", false },
            { ">=", "a", "in", "a", false },
            { ">=", "a", "not in", "b,c,d", false },
            { ">=", "a", "matches", "a", false },
            { ">=", "a", "soundslike", "a", false },

            { ">=", "a", "<", " ", true },
            { ">=", "a", "<=", " ", true },
            { ">=", "a", "in", "0,1,A,B", true },
            { ">=", "a", "matches", "A", true },
            { ">=", "a", "soundslike", "", true },

            { "<", "a", "<", "b", false },
            { "<", "a", "<=", "a", false },
            { "<", "a", "in", "A,B,a,b", false },
            { "<", "a", "not in", "b,c,d", false },
            { "<", "a", "matches", "A", false },
            { "<", "a", "soundslike", "", false },

            { "<", "a", "in", "b,c,d", true },
            { "<", "a", "matches", "b", true },
            { "<", "a", "soundslike", "b", true },

            { "<=", "a", "<=", "b", false },
            { "<=", "a", "in", "A,B,a,b", false },
            { "<=", "a", "not in", "b,c,d", false },
            { "<=", "a", "matches", "A", false },
            { "<=", "a", "soundslike", "", false },

            { "<=", "a", "in", "b,c,d", true },
            { "<=", "a", "matches", "b", true },
            { "<=", "a", "soundslike", "b", true },

            { "in", "a,b", "in", "b,c,d", false },
            { "in", "a,b", "not in", "b,c,d", false },
            { "in", "a,b", "matches", "a", false },
            { "in", "a,b", "soundslike", "a", false },

            { "in", "a,b", "in", "c,d", true },
            { "in", "a,b", "not in", "a,b", true },
            { "in", "a,b", "matches", "c", true },
            { "in", "a,b", "soundslike", "c", true },

            { "not in", "a,b", "matches", "c", false },
            { "not in", "a,b", "soundslike", "c", false },

            { "not in", "a,b", "matches", "a", true },

            { "matches", "a", "soundslike", "a", false },

            { "matches", "a", "soundslike", "b", true },
        } );
    }

    private StringConditionInspector getCondition( String value,
                                                   String operator ) {
        return new StringConditionInspector( mock( Pattern52.class ), "name", value, operator );
    }

    private String getAssertDescription( StringConditionInspector a,
                                         StringConditionInspector b,
                                         boolean conflictExpected ) {
        return format( "Expected condition '%s' %sto conflict with condition '%s':",
                       a.toHumanReadableString(),
                       conflictExpected ? "" : "not ",
                       b.toHumanReadableString() );
    }
}