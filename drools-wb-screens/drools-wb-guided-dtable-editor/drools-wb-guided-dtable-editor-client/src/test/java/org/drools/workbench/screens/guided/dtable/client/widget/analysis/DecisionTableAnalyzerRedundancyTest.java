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

package org.drools.workbench.screens.guided.dtable.client.widget.analysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.models.datamodel.imports.Import;
import org.drools.workbench.models.datamodel.oracle.DataType;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.screens.guided.dtable.client.resources.i18n.AnalysisConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.CellValue;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.data.Coordinate;
import org.mockito.Mock;

import static org.drools.workbench.screens.guided.dtable.client.widget.analysis.TestUtil.*;
import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class DecisionTableAnalyzerRedundancyTest {

    @GwtMock AnalysisConstants analysisConstants;
    @GwtMock DateTimeFormat dateTimeFormat;

    @Mock AsyncPackageDataModelOracle oracle;

    EventBusMock eventBus;

    @Before
    public void setUp() throws Exception {
        Map<String, String> preferences = new HashMap<String, String>();
        preferences.put(ApplicationPreferences.DATE_FORMAT, "dd-MMM-yyyy");
        ApplicationPreferences.setUp(preferences);

        when(oracle.getFieldType("LoanApplication", "amount")).thenReturn(DataType.TYPE_NUMERIC_INTEGER);
        when(oracle.getFieldType("LoanApplication", "lengthYears")).thenReturn(DataType.TYPE_NUMERIC_INTEGER);
        when(oracle.getFieldType("LoanApplication", "deposit")).thenReturn(DataType.TYPE_NUMERIC_INTEGER);
        when(oracle.getFieldType("LoanApplication", "approved")).thenReturn(DataType.TYPE_BOOLEAN);
        when(oracle.getFieldType("LoanApplication", "insuranceCost")).thenReturn(DataType.TYPE_NUMERIC_INTEGER);
        when(oracle.getFieldType("LoanApplication", "approvedRate")).thenReturn(DataType.TYPE_NUMERIC_INTEGER);
        when(oracle.getFieldType("IncomeSource", "type")).thenReturn(DataType.TYPE_STRING);
        when(oracle.getFieldType("Person", "name")).thenReturn(DataType.TYPE_STRING);

        eventBus = new EventBusMock();
    }

    @Test
    public void testNoIssues() throws Exception {
        GuidedDecisionTable52 table52 = new ExtendedGuidedDecisionTableBuilder("org.test",
                                                                               new ArrayList<Import>(),
                                                                               "mytable")
                .withIntegerColumn("application", "LoanApplication", "amount", ">")
                .withIntegerColumn("application", "LoanApplication", "amount", "<=")
                .withIntegerColumn("application", "LoanApplication", "lengthYears", "==")
                .withIntegerColumn("application", "LoanApplication", "deposit", "<")
                .withStringColumn("income", "IncomeSource", "type", "==")
                .withActionSetField("application", "approved", DataType.TYPE_BOOLEAN)
                .withActionSetField("application", "insuranceCost", DataType.TYPE_NUMERIC_INTEGER)
                .withActionSetField("application", "approvedRate", DataType.TYPE_NUMERIC_INTEGER)
                .withData(new Object[][]{
                        {1, "description", 131000, 200000, 30, 20000, "Asset", true, 0, 2},
                        {2, "description", 10000, 100000, 20, 2000, "Job", true, 0, 4},
                        {3, "description", 100001, 130000, 20, 3000, "Job", true, 10, 6}})
                .build();

        DecisionTableAnalyzer analyzer = new DecisionTableAnalyzer(oracle,
                                                                   table52,
                                                                   eventBus);

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        List<CellValue<? extends Comparable<?>>> result = eventBus.getUpdateColumnDataEvent().getColumnData();
        assertDoesNotContain("ThisRowIsRedundantTo(1)", result);
        assertDoesNotContain("ThisRowIsRedundantTo(2)", result);
        assertDoesNotContain("ThisRowIsRedundantTo(3)", result);

    }

    @Test
    public void testNoIssues2() throws Exception {
        GuidedDecisionTable52 table52 = new ExtendedGuidedDecisionTableBuilder("org.test",
                                                                               new ArrayList<Import>(),
                                                                               "mytable")
                .withIntegerColumn("application", "LoanApplication", "amount", ">")
                .withIntegerColumn("application", "LoanApplication", "amount", "<=")
                .withIntegerColumn("application", "LoanApplication", "lengthYears", "==")
                .withIntegerColumn("application", "LoanApplication", "deposit", "<")
                .withStringColumn("income", "IncomeSource", "type", "==")
                .withActionSetField("application", "approved", DataType.TYPE_BOOLEAN)
                .withActionSetField("application", "insuranceCost", DataType.TYPE_NUMERIC_INTEGER)
                .withActionSetField("application", "approvedRate", DataType.TYPE_NUMERIC_INTEGER)
                .withData(new Object[][]{
                        {1, "description", 131000, 200000, 30, 20000, "Asset", true, 0, 2},
                        {2, "description", 1000,   200000, 30, 20000, "Asset", true, 0, 2},
                        {3, "description", 100001, 130000, 20, 3000,  "Job",   true, 10, 6}})
                .build();

        DecisionTableAnalyzer analyzer = new DecisionTableAnalyzer(oracle,
                                                                   table52,
                                                                   eventBus);

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        List<CellValue<? extends Comparable<?>>> result = eventBus.getUpdateColumnDataEvent().getColumnData();
        assertDoesNotContain("ThisRowIsRedundantTo(1)", result);
        assertDoesNotContain("ThisRowIsRedundantTo(2)", result);
        assertDoesNotContain("ThisRowIsRedundantTo(3)", result);

    }

    @Test
    public void testRedundantRows001() throws Exception {
        GuidedDecisionTable52 table52 = new ExtendedGuidedDecisionTableBuilder("org.test",
                                                                               new ArrayList<Import>(),
                                                                               "mytable")
                .withNumericColumn("application", "LoanApplication", "amount", ">")
                .withNumericColumn("application", "LoanApplication", "amount", "<=")
                .withNumericColumn("application", "LoanApplication", "lengthYears", "==")
                .withNumericColumn("application", "LoanApplication", "deposit", "<")
                .withStringColumn("income", "IncomeSource", "type", "==")
                .withActionSetField("application", "approved", DataType.TYPE_BOOLEAN)
                .withActionSetField("application", "insuranceCost", DataType.TYPE_NUMERIC)
                .withActionSetField("application", "approvedRate", DataType.TYPE_NUMERIC)
                .withData(new Object[][]{
                        {1, "description", 131000, 200000, 30, 20000, "Asset", true, 0, 2},
                        {2, "description", 131000, 200000, 30, 20000, "Asset", true, 0, 2},
                        {3, "description", 100001, 130000, 20, 3000,  "Job",   true, 10, 6}})
                .build();

        DecisionTableAnalyzer analyzer = new DecisionTableAnalyzer(oracle,
                                                                   table52,
                                                                   eventBus);

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        List<CellValue<? extends Comparable<?>>> result = eventBus.getUpdateColumnDataEvent().getColumnData();
        assertContains("ThisRowIsRedundantTo(1)", result);
        assertContains("ThisRowIsRedundantTo(2)", result);

    }

    @Test
    public void testRedundantRows002() throws Exception {
        GuidedDecisionTable52 table52 = new ExtendedGuidedDecisionTableBuilder("org.test",
                                                                               new ArrayList<Import>(),
                                                                               "mytable")
                .withStringColumn("application", "LoanApplication", "amount", ">")
                .withStringColumn("person", "Person", "name", "==")
                .withStringColumn("income", "IncomeSource", "type", "==")
                .withActionSetField("application", "approved", DataType.TYPE_STRING)
                .withData(new Object[][]{
                        {1, "description", "131000", "Toni", "Asset", "true"},
                        {2, "description", "131000", "Toni", "Asset", "true"},
                        {3, "description", "100001", "Michael", "Job", "true"}})
                .build();

        DecisionTableAnalyzer analyzer = new DecisionTableAnalyzer(oracle,
                                                                   table52,
                                                                   eventBus);

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        List<CellValue<? extends Comparable<?>>> result = eventBus.getUpdateColumnDataEvent().getColumnData();
        assertContains("ThisRowIsRedundantTo(1)", result);
        assertContains("ThisRowIsRedundantTo(2)", result);

    }

    @Test
    public void testRedundantRows003() throws Exception {
        GuidedDecisionTable52 table52 = new ExtendedGuidedDecisionTableBuilder("org.test",
                                                                               new ArrayList<Import>(),
                                                                               "mytable")
                .withStringColumn("application", "LoanApplication", "amount", ">")
                .withStringColumn("person", "Person", "name", "==")
                .withEnumColumn("income", "IncomeSource", "type", "==", "Asset,Job")
                .withActionSetField("application", "approved", DataType.TYPE_STRING)
                .withData(new Object[][]{
                        {1, "description", "131000", "Toni", "Asset", "true"},
                        {2, "description", "131000", "Toni", "Asset", "true"},
                        {3, "description", "100001", "Michael", "Job", "true"}})
                .build();

        DecisionTableAnalyzer analyzer = new DecisionTableAnalyzer(oracle,
                                                                   table52,
                                                                   eventBus);

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        List<CellValue<? extends Comparable<?>>> result = eventBus.getUpdateColumnDataEvent().getColumnData();
        assertContains("ThisRowIsRedundantTo(1)", result);
        assertContains("ThisRowIsRedundantTo(2)", result);

    }

    @Test
    public void testRedundantRowsWithConflict() throws Exception {
        GuidedDecisionTable52 table52 = new ExtendedGuidedDecisionTableBuilder("org.test",
                                                                               new ArrayList<Import>(),
                                                                               "mytable")
                .withIntegerColumn("a", "Person", "age", ">")
                .withIntegerColumn("d", "Account", "deposit", "<")
                .withActionSetField("a", "approved", DataType.TYPE_BOOLEAN)
                .withActionSetField("a", "approved", DataType.TYPE_BOOLEAN)
                .withData(new Object[][]{
                        {1, "description", 100, 0, true, true},
                        {2, "description", 100, 0, true, false}})
                .build();

        DecisionTableAnalyzer analyzer = new DecisionTableAnalyzer(oracle,
                                                                   table52,
                                                                   eventBus);

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        List<CellValue<? extends Comparable<?>>> result = eventBus.getUpdateColumnDataEvent().getColumnData();
        assertDoesNotContain("ThisRowIsRedundantTo(1)", result);
        assertDoesNotContain("ThisRowIsRedundantTo(2)", result);

    }

}