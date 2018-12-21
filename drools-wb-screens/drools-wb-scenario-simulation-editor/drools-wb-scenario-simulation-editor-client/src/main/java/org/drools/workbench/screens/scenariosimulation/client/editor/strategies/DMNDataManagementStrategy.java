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
package org.drools.workbench.screens.scenariosimulation.client.editor.strategies;

import java.util.TreeMap;

import com.google.gwt.core.client.GWT;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.RightPanelView;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModelContent;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.backend.vfs.Path;

public class DMNDataManagementStrategy implements DataManagementStrategy {

    private final Caller<DMNTypeService> dmnTypeService;
    private Path currentPath;
    private ScenarioSimulationModel model;
    private ThreadLocal<FactModelTuple> factModelTupleThreadLocal = new ThreadLocal<>();

    public DMNDataManagementStrategy(Caller<DMNTypeService> dmnTypeService) {
        this.dmnTypeService = dmnTypeService;
    }

    @Override
    public void populateRightPanel(final RightPanelView.Presenter rightPanelPresenter, final ScenarioGridModel scenarioGridModel) {
        GWT.log("LALALALALALA scenarioGridModel.getSimulation().isPresent() = " + scenarioGridModel.getSimulation().isPresent());
        String dmnFilePath = model.getSimulation().getSimulationDescriptor().getDmnFilePath();
        if(factModelTupleThreadLocal.get() != null) {
            getSuccessCallback(rightPanelPresenter).callback(factModelTupleThreadLocal.get());
        }
        else {
            dmnTypeService.call(getSuccessCallback(rightPanelPresenter),
                                getErrorCallback(rightPanelPresenter))
                    .retrieveType(currentPath, dmnFilePath);
        }
    }

    @Override
    public void manageScenarioSimulationModelContent(ObservablePath currentPath, ScenarioSimulationModelContent toManage) {
        this.currentPath = currentPath.getOriginal();
        model = toManage.getModel();
    }

    private RemoteCallback<FactModelTuple> getSuccessCallback(RightPanelView.Presenter rightPanelPresenter) {
        return factMappingTuple -> {
            factModelTupleThreadLocal.set(factMappingTuple);
            rightPanelPresenter.setDataObjectFieldsMap(factMappingTuple.getVisibleFacts());
            rightPanelPresenter.setHiddenFieldsMap(factMappingTuple.getHiddenFacts());

        };
    }

    private ErrorCallback<Object> getErrorCallback(RightPanelView.Presenter rightPanelPresenter) {
        return (error, exception) -> {
            rightPanelPresenter.setDataObjectFieldsMap(new TreeMap<>());
            return false;
        };
    }
}
