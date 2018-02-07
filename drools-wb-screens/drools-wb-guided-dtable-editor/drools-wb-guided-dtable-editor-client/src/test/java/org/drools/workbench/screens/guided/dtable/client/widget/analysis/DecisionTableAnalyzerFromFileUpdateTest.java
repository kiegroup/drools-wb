/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.models.datamodel.oracle.DataType;
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.CellValue;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.data.Coordinate;

import static org.drools.workbench.screens.guided.dtable.client.widget.analysis.TestUtil.assertContains;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class DecisionTableAnalyzerFromFileUpdateTest extends BaseDecisionTableAnalyzerTest {

    @Before
    public void setUp() throws Exception {
        Map<String, String> preferences = new HashMap<String, String>();
        preferences.put(ApplicationPreferences.DATE_FORMAT, "dd-MMM-yyyy");
        ApplicationPreferences.setUp(preferences);

        when(oracle.getFieldType("LoanApplication", "approved")).thenReturn(DataType.TYPE_BOOLEAN);
        when(oracle.getFieldType("Applicant", "age")).thenReturn(DataType.TYPE_NUMERIC_INTEGER);
    }

    @Test
    public void testFileUpdateTestTable() throws Exception {
        final String xml = loadResource("Update Test Table.gdst");

        final GuidedDecisionTable52 table52 = GuidedDTXMLPersistence.getInstance().unmarshal(xml);

        final DecisionTableAnalyzer analyzer = getDecisionTableAnalyzer(table52);

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        assertContains("RedundantRows", analysisReport);

        table52.getData().get(0).get(3).setBooleanValue(false);
        update(analyzer, new Coordinate(0, 3));

        assertContains("ConflictingRows", analysisReport);

        table52.getData().get(0).get(3).setBooleanValue(true);
        update(analyzer, new Coordinate(0, 3));

        assertContains("RedundantRows", analysisReport);
        assertEquals(1, analysisReport.getAnalysisData().size());
    }

    public static String loadResource(final String name) throws Exception {
        final InputStream in = DecisionTableAnalyzerFromFileUpdateTest.class.getResourceAsStream(name);
        final Reader reader = new InputStreamReader(in);
        final StringBuilder text = new StringBuilder();
        final char[] buf = new char[1024];
        int len = 0;
        while ((len = reader.read(buf)) >= 0) {
            text.append(buf,
                        0,
                        len);
        }
        return text.toString();
    }

    private void update(final DecisionTableAnalyzer analyzer,
                        final Coordinate coordinate) {
        HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>> updates = new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>();
        updates.put(coordinate, new ArrayList<List<CellValue<? extends Comparable<?>>>>());
        analyzer.onValidate(new ValidateEvent(updates));
    }
}