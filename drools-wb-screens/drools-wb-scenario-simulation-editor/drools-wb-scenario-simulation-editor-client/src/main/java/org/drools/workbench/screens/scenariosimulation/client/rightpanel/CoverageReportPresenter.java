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

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
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

    public CoverageReportPresenter() {
        //Zero argument constructor for CDI
        title = ScenarioSimulationEditorConstants.INSTANCE.coverageReport();
    }

    @Inject
    public CoverageReportPresenter(CoverageReportView view) {
        super(view);
        title = ScenarioSimulationEditorConstants.INSTANCE.coverageReport();
    }

    // FIXME to test
    @Override
    public void setSimulationRunMetadata(SimulationRunMetadata simulationRunMetadata) {
        view.getReportAvailable().setInnerText(simulationRunMetadata.getAvailable() + "");
        view.getReportExecuted().setInnerText(simulationRunMetadata.getExecuted() + "");
        view.getReportCoverage().setInnerText(simulationRunMetadata.getCoveragePercentage() + " %");
    }
}
