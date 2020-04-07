/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.client.handlers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.CheatSheetPresenter;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.SettingsPresenter;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.TestToolsPresenter;
import org.kie.workbench.common.widgets.client.docks.AbstractWorkbenchDocksHandler;
import org.kie.workbench.common.widgets.client.docks.AuthoringEditorDock;
import org.uberfire.client.workbench.docks.UberfireDock;
import org.uberfire.client.workbench.docks.UberfireDockPosition;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;

/**
 * Abstract Handler used to register docks in ScenarioSimulation. Subclasses have to be defined in
 * Business Cental and Kogito Contest
 */
public abstract class AbstractScenarioSimulationDocksHandler extends AbstractWorkbenchDocksHandler {

    public static final String SCESIMEDITOR_ID = "scesimeditorid";

    @Inject
    protected AuthoringEditorDock authoringWorkbenchDocks;

    private UberfireDock settingsDock;
    private UberfireDock toolsDock;
    private UberfireDock cheatSheetDock;

    @Override
    public Collection<UberfireDock> provideDocks(final String perspectiveIdentifier) {
        List<UberfireDock> result = new ArrayList<>();
        settingsDock = new UberfireDock(UberfireDockPosition.EAST,
                                        "SLIDERS",
                                        new DefaultPlaceRequest(SettingsPresenter.IDENTIFIER),
                                        perspectiveIdentifier);
        result.add(settingsDock.withSize(450).withLabel(ScenarioSimulationEditorConstants.INSTANCE.settings()));
        toolsDock = new UberfireDock(UberfireDockPosition.EAST,
                                     "INFO_CIRCLE",
                                     new DefaultPlaceRequest(TestToolsPresenter.IDENTIFIER),
                                     perspectiveIdentifier);
        result.add(toolsDock.withSize(450).withLabel(ScenarioSimulationEditorConstants.INSTANCE.testTools()));
        cheatSheetDock = new UberfireDock(UberfireDockPosition.EAST,
                                          "FILE_TEXT",
                                          new DefaultPlaceRequest(CheatSheetPresenter.IDENTIFIER),
                                          perspectiveIdentifier);
        result.add(cheatSheetDock.withSize(450).withLabel(ScenarioSimulationEditorConstants.INSTANCE.scenarioCheatSheet()));
        return result;
    }

    public void addDocks() {
        refreshDocks(true, false);
    }

    public void removeDocks() {
        refreshDocks(true, true);
    }

    public void expandToolsDock() {
        authoringWorkbenchDocks.expandAuthoringDock(toolsDock);
    }

    public abstract void expandTestResultsDock();

    public void setScesimEditorId(String scesimEditorId) {
        settingsDock.getPlaceRequest().addParameter(SCESIMEDITOR_ID, scesimEditorId);
        toolsDock.getPlaceRequest().addParameter(SCESIMEDITOR_ID, scesimEditorId);
        cheatSheetDock.getPlaceRequest().addParameter(SCESIMEDITOR_ID, scesimEditorId);
    }

    public Optional<UberfireDock> getSettingsDock(PlaceRequest placeRequest) {
        return Objects.equals(settingsDock.getPlaceRequest(), placeRequest) ? Optional.of(settingsDock) : Optional.empty();
    }

    public Optional<UberfireDock> getToolsDock(PlaceRequest placeRequest) {
        return Objects.equals(toolsDock.getPlaceRequest(), placeRequest) ? Optional.of(toolsDock) : Optional.empty();
    }

    public Optional<UberfireDock> getCheatSheetDock(PlaceRequest placeRequest) {
        return Objects.equals(cheatSheetDock.getPlaceRequest(), placeRequest) ? Optional.of(cheatSheetDock) : Optional.empty();
    }

}