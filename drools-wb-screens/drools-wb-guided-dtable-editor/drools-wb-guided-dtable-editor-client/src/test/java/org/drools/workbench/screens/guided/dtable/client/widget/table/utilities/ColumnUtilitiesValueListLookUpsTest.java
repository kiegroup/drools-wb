/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtable.client.widget.table.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.models.guided.dtable.shared.model.ActionInsertFactCol52;
import org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.CompositeColumn;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.soup.project.datamodel.oracle.DataType;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ColumnUtilitiesValueListLookUpsTest {

    @Mock
    private GuidedDecisionTable52 model;

    @Mock
    private AsyncPackageDataModelOracle oracle;

    private ColumnUtilities utilities;

    @Before
    public void setUp() {
        utilities = new ColumnUtilities(model,
                                        oracle);

        final HashMap<String, String> result = new HashMap<>();
        result.put("f", "female");
        result.put("m", "male");
        result.put("o", "other");

        utilities.addEnumLookUp("Person", "gender", result);
    }

    @Test
    public void testConditionCol52() {
        final ConditionCol52 column = new ConditionCol52();
        final Pattern52 pattern = new Pattern52();
        pattern.setFactType("Person");
        when(model.getPattern(column)).thenReturn(pattern);
        column.setFactField("gender");
        column.setFieldType(DataType.TYPE_NUMERIC_INTEGER);

        check(utilities.getValueListLookups(column));
    }

    @Test
    public void testActionSetFieldCol52() {
        final ActionSetFieldCol52 column = new ActionSetFieldCol52();
        column.setBoundName("p");
        column.setFactField("gender");

        final ArrayList<CompositeColumn<? extends BaseColumn>> conditions = new ArrayList<>();
        final Pattern52 pattern = new Pattern52();
        pattern.setFactType("Person");
        pattern.setBoundName("p");
        conditions.add(pattern);
        doReturn(conditions).when(model).getConditions();

        check(utilities.getValueListLookups(column));
    }

    @Test
    public void testActionInsertFactCol52() {
        final ActionInsertFactCol52 column = new ActionInsertFactCol52();
        column.setBoundName("p");
        column.setFactField("gender");

        final ArrayList<CompositeColumn<? extends BaseColumn>> conditions = new ArrayList<>();
        final Pattern52 pattern = new Pattern52();
        pattern.setFactType("Person");
        pattern.setBoundName("p");
        conditions.add(pattern);
        doReturn(conditions).when(model).getConditions();

        check(utilities.getValueListLookups(column));
    }

    @Test
    public void testEmptyConditionCol52() {
        final ConditionCol52 column = new ConditionCol52();
        final Pattern52 pattern = new Pattern52();
        pattern.setFactType("Person");
        when(model.getPattern(column)).thenReturn(pattern);
        column.setFactField("name");
        column.setFieldType(DataType.TYPE_NUMERIC_INTEGER);

        final Map<String, String> valueListLookups = utilities.getValueListLookups(column);
        assertTrue(valueListLookups.isEmpty());
    }

    @Test
    public void testEmptyActionSetFieldCol52() {
        final ActionSetFieldCol52 column = new ActionSetFieldCol52();

        final Map<String, String> valueListLookups = utilities.getValueListLookups(column);
        assertTrue(valueListLookups.isEmpty());
    }

    @Test
    public void testEmptyActionInsertFactCol52() {
        final ActionInsertFactCol52 column = new ActionInsertFactCol52();

        final Map<String, String> valueListLookups = utilities.getValueListLookups(column);
        assertTrue(valueListLookups.isEmpty());
    }

    private void check(final Map<String, String> valueListLookups) {
        assertEquals(3, valueListLookups.size());
        assertEquals("male", valueListLookups.get("m"));
        assertEquals("female", valueListLookups.get("f"));
        assertEquals("other", valueListLookups.get("o"));
    }
}