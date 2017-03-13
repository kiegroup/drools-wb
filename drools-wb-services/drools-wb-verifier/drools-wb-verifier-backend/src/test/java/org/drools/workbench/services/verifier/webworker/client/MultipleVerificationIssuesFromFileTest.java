/*
 * Copyright 2017 JBoss, by Red Hat, Inc
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
package org.drools.workbench.services.verifier.webworker.client;

import java.util.HashSet;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwtmockito.GwtMock;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.services.verifier.api.client.index.DataType;
import org.drools.workbench.services.verifier.api.client.reporting.ExplanationProvider;
import org.drools.workbench.services.verifier.api.client.reporting.Issue;
import org.drools.workbench.services.verifier.api.client.resources.i18n.AnalysisConstants;
import org.drools.workbench.services.verifier.core.main.Analyzer;
import org.drools.workbench.services.verifier.plugin.client.api.FactTypes;
import org.drools.workbench.services.verifier.webworker.client.testutil.AnalyzerProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.drools.workbench.services.verifier.webworker.client.testutil.TestUtil.assertContains;
import static org.drools.workbench.services.verifier.webworker.client.testutil.TestUtil.loadResource;

@RunWith(GwtMockitoTestRunner.class)
public class MultipleVerificationIssuesFromFileTest {

    @GwtMock
    AnalysisConstants analysisConstants;

    @GwtMock
    DateTimeFormat dateTimeFormat;

    private AnalyzerProvider analyzerProvider;

    @Before
    public void setUp() throws
            Exception {
        analyzerProvider = new AnalyzerProvider();

        analyzerProvider.getFactTypes().add(new FactTypes.FactType("Applicant",
                                                                   new HashSet<FactTypes.Field>() {{
                                                                       add(new FactTypes.Field("age",
                                                                                               DataType.TYPE_NUMERIC_INTEGER));
                                                                       add(new FactTypes.Field("creditRating",
                                                                                               DataType.TYPE_STRING));
                                                                       add(new FactTypes.Field("name",
                                                                                               DataType.TYPE_STRING));
                                                                   }}));
    }

    @Test
    public void testFileGuvnor2934() throws
            Exception {
        final String xml = loadResource("GUVNOR-2934 reproducer.gdst");

        final GuidedDecisionTable52 table52 = GuidedDTXMLPersistence.getInstance()
                .unmarshal(xml);

        final Analyzer analyzer = analyzerProvider.makeAnalyser(table52);

        analyzer.resetChecks();
        analyzer.analyze();

        print();

        assertContains("SingleHitLost",
                       analyzerProvider.getAnalysisReport());

        assertContains("RuleHasNoAction",
                       analyzerProvider.getAnalysisReport(),
                       1);

        assertContains("SubsumptantRows",
                       analyzerProvider.getAnalysisReport(),
                       2,
                       3);

        assertContains("RedundantRows",
                       analyzerProvider.getAnalysisReport(),
                       4,
                       5);

        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       2,
                       7);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       2,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       2,
                       9);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       3,
                       6);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       3,
                       7);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       3,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       3,
                       9);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       4,
                       7);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       4,
                       9);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       5,
                       7);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       5,
                       9);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       6,
                       7);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       6,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       7,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       8,
                       9);
    }

    @Test
    public void testFileGuvnor2934DifferentValues() throws
            Exception {
        final String xml = loadResource("GUVNOR-2934 reproducer 2.gdst");

        final GuidedDecisionTable52 table52 = GuidedDTXMLPersistence.getInstance()
                .unmarshal(xml);

        final Analyzer analyzer = analyzerProvider.makeAnalyser(table52);

        analyzer.resetChecks();
        analyzer.analyze();

        print();

        assertContains("SingleHitLost",
                       analyzerProvider.getAnalysisReport());

        assertContains("RuleHasNoAction",
                       analyzerProvider.getAnalysisReport(),
                       1);

        assertContains("MissingRangeTitle",
                       analyzerProvider.getAnalysisReport(),
                       3);
        assertContains("MissingRangeTitle",
                       analyzerProvider.getAnalysisReport(),
                       6);
        assertContains("MissingRangeTitle",
                       analyzerProvider.getAnalysisReport(),
                       7);

        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       2,
                       9);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       2,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       3,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       4,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       4,
                       9);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       5,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       5,
                       9);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       6,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       7,
                       8);
        assertContains("ConflictingRows",
                       analyzerProvider.getAnalysisReport(),
                       8,
                       9);
    }

    private void print() {
        for (Issue issue : analyzerProvider.getAnalysisReport()) {
            System.out.print("[ ");
            for (Integer integer : issue.getRowNumbers()) {
                System.out.print(integer + ", ");
            }
            System.out.print("] ");

            System.out.println(ExplanationProvider.toTitle(issue));
        }
    }
}
