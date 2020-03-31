/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.businesscentral.client.handlers;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import org.drools.workbench.screens.scenariosimulation.client.handlers.AbstractScenarioSimulationDocksHandler;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.CoverageReportPresenter;
import org.kie.workbench.common.widgets.client.docks.DockPlaceHolderPlace;
import org.uberfire.client.workbench.docks.UberfireDock;
import org.uberfire.client.workbench.docks.UberfireDockPosition;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;

@ApplicationScoped
public class ScenarioSimulationBusinessCentralDocksHandler extends AbstractScenarioSimulationDocksHandler {

    public static final String TEST_RUNNER_REPORTING_PANEL = "testRunnerReportingPanel";

    private UberfireDock reportDock;
    private UberfireDock coverageDock;

    @Override
    public Collection<UberfireDock> provideDocks(String perspectiveIdentifier) {
        Collection<UberfireDock> result = super.provideDocks(perspectiveIdentifier);
        reportDock = new UberfireDock(UberfireDockPosition.EAST,
                                      "PLAY_CIRCLE",
                                      new DockPlaceHolderPlace(TEST_RUNNER_REPORTING_PANEL),
                                      perspectiveIdentifier);
        result.add(reportDock.withSize(450).withLabel(ScenarioSimulationEditorConstants.INSTANCE.testReport()));
        coverageDock = new UberfireDock(UberfireDockPosition.EAST,
                                        "BAR_CHART",
                                        new DefaultPlaceRequest(CoverageReportPresenter.IDENTIFIER),
                                        perspectiveIdentifier);
        result.add(coverageDock.withSize(450).withLabel(ScenarioSimulationEditorConstants.INSTANCE.coverageReport()));
        return result;
    }

    public void expandTestResultsDock() {
        authoringWorkbenchDocks.expandAuthoringDock(reportDock);
    }

    @Override
    public void setScesimEditorId(String scesimEditorId) {
        super.setScesimEditorId(scesimEditorId);
        reportDock.getPlaceRequest().addParameter(SCESIMEDITOR_ID, scesimEditorId);
        coverageDock.getPlaceRequest().addParameter(SCESIMEDITOR_ID, scesimEditorId);
    }

    public Optional<UberfireDock> getReportDock(PlaceRequest placeRequest) {
        return Objects.equals(reportDock.getPlaceRequest(), placeRequest) ? Optional.of(reportDock) : Optional.empty();
    }

    public Optional<UberfireDock> getCoverageReportDock(PlaceRequest placeRequest) {
        return Objects.equals(coverageDock.getPlaceRequest(), placeRequest) ? Optional.of(coverageDock) : Optional.empty();
    }

}
