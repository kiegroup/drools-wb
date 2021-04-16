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
package org.drools.workbench.screens.guided.dtable.backend.server.conversion.util;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CommaSeparatedListValueConverterTest {

    private final String given;
    private final String expected;

    public CommaSeparatedListValueConverterTest(final String given,
                                                final String expected) {
        this.given = given;
        this.expected = expected;
    }

    @Test
    public void testConversion() {
        assertEquals(expected, new CommaSeparatedListValueConverter(given).convert());
    }

    @Parameterized.Parameters
    public static Collection testParameters() {
        return Arrays.asList(new Object[][]{
                {"hello",
                        "'hello'"},
                {"  ",
                        "'  '"},
                {"Toni's pizzeria",
                        "'Toni\\\'s pizzeria'"},
                {"Toni, Wife",
                        "'Toni', 'Wife'"},
                {"\" \", \"  \"",
                        "' ', '  '"},
                {"Toni's Wife, Wife",
                        "'Toni\\\'s Wife', 'Wife'"},
                {"\"Toni's Wife\", \"Wife\"",
                        "'Toni\\\'s Wife', 'Wife'"},
                {"\"Cat, or dog\", \"Dog\"",
                        "'Cat, or dog', 'Dog'"},
                {"\"Peter's cat, or dog\", \"Dog\"",
                        "'Peter\\\'s cat, or dog', 'Dog'"},
        });
    }
}