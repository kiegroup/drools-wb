/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioWithIndex;
import org.drools.workbench.screens.scenariosimulation.model.SimulationRunMetadata;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.mvp.Command;

import static org.drools.workbench.screens.scenariosimulation.client.rightpanel.CoverageReportPresenter.DEFAULT_PREFERRED_WIDHT;
import static org.drools.workbench.screens.scenariosimulation.client.rightpanel.CoverageReportPresenter.IDENTIFIER;

@ApplicationScoped
@WorkbenchScreen(identifier = IDENTIFIER, preferredWidth = DEFAULT_PREFERRED_WIDHT)
public class CoverageReportPresenter extends AbstractSubDockPresenter<CoverageReportView> implements CoverageReportView.Presenter {

    public static final int DEFAULT_PREFERRED_WIDHT = 300;

    public static final String IDENTIFIER = "org.drools.scenariosimulation.CoverageReport";

    protected Command saveCommand;

    @Inject
    protected CoverageReportDonutPresenter coverageReportDonutPresenter;

    @Inject
    protected CoverageDecisionElementPresenter coverageDecisionElementPresenter;

    @Inject
    protected CoverageScenarioListPresenter coverageScenarioListPresenter;

    public CoverageReportPresenter() {
        //Zero argument constructor for CDI
        title = ScenarioSimulationEditorConstants.INSTANCE.coverageReport();
    }

    @Inject
    public CoverageReportPresenter(CoverageReportView view) {
        super(view);
        title = ScenarioSimulationEditorConstants.INSTANCE.coverageReport();
    }

    @PostConstruct
    public void init() {
        coverageReportDonutPresenter.init(view.getDonutChart());
        coverageDecisionElementPresenter.initDecisionList(view.getDecisionList());
        coverageScenarioListPresenter.initScenarioList(view.getScenarioList());
    }

    // FIXME to test
    @Override
    public void setSimulationRunMetadata(SimulationRunMetadata simulationRunMetadata) {
        populateSummary(simulationRunMetadata.getAvailable(),
                        simulationRunMetadata.getExecuted(),
                        simulationRunMetadata.getCoveragePercentage());

        populateDecisionList(simulationRunMetadata.getOutputCounter());

        populateScenarioList(simulationRunMetadata.getScenarioCounter());
    }

    protected void populateSummary(int available, int executed, double coveragePercentage) {
        view.getReportAvailable().textContent = available + "";
        view.getReportExecuted().textContent = executed + "";
        view.getReportCoverage().textContent = coveragePercentage + " %";

        // donut chart
        coverageReportDonutPresenter
                .showCoverageReport(executed,
                                    available - executed);

    }

    protected void populateDecisionList(Map<String, Integer> outputCounter) {
        coverageDecisionElementPresenter.clear();
        List<String> decisions = new ArrayList<>(outputCounter.keySet());
        decisions.sort(Comparator.naturalOrder());
        for (String decision : decisions) {
            coverageDecisionElementPresenter.addDecisionElementView(decision, outputCounter.get(decision) + "");
        }
    }

    protected void populateScenarioList(Map<ScenarioWithIndex, List<String>> scenarioCounter) {
        coverageScenarioListPresenter.clear();
        List<ScenarioWithIndex> scenarioIndexes = new ArrayList<>(scenarioCounter.keySet());
        scenarioIndexes.sort(Comparator.comparingInt(ScenarioWithIndex::getIndex));
        for (ScenarioWithIndex scenarioWithIndex : scenarioIndexes) {
            coverageScenarioListPresenter.addScenarioGroup(scenarioWithIndex, scenarioCounter.get(scenarioWithIndex));
        }
    }


}
