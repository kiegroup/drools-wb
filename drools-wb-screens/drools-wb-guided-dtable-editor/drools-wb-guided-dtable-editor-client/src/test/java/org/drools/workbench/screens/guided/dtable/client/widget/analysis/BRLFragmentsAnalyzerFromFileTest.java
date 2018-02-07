/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.screens.guided.dtable.client.resources.i18n.AnalysisConstants;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.reporting.Issue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.CellValue;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.data.Coordinate;

import static org.drools.workbench.screens.guided.dtable.client.widget.analysis.DecisionTableAnalyzerFromFileTest.loadResource;
import static org.junit.Assert.assertTrue;

@RunWith(GwtMockitoTestRunner.class)
public class BRLFragmentsAnalyzerFromFileTest extends BaseDecisionTableAnalyzerTest {

    @GwtMock
    AnalysisConstants analysisConstants;

    @GwtMock
    DateTimeFormat dateTimeFormat;

    @Before
    public void setUp() throws Exception {
        Map<String, String> preferences = new HashMap<String, String>();
        preferences.put(ApplicationPreferences.DATE_FORMAT, "dd-MMM-yyyy");
        ApplicationPreferences.setUp(preferences);
    }

    @Test
    public void testRuleTable() throws Exception {
        String xml = loadResource("RuleTable.gdst");

        DecisionTableAnalyzer analyzer = getDecisionTableAnalyzer(GuidedDTXMLPersistence.getInstance().unmarshal(xml));

        analyzer.onValidate(new ValidateEvent(new HashMap<Coordinate, List<List<CellValue<? extends Comparable<?>>>>>()));

        final List<Issue> analysisData = analysisReport.getAnalysisData();

        for (final Issue issue : analysisData) {
            System.out.println(issue.getTitle());
        }

        assertTrue(analysisData.isEmpty());
    }
}
