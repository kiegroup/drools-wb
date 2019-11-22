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
package org.drools.workbench.screens.scenariosimulation.kogito.client.editor.strategies;

import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.AbstractDMNDataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.TestToolsView;
import org.drools.workbench.screens.scenariosimulation.kogito.client.fakes.KogitoDMNTypeService;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;

public class KogitoDMNDataManagementStrategy extends AbstractDMNDataManagementStrategy {

    private final KogitoDMNTypeService kogitoDmnTypeService = new KogitoDMNTypeService();

    public KogitoDMNDataManagementStrategy(EventBus eventBus) {
        super(eventBus);
    }

    @Override
    protected void retrieveFactModelTuple(TestToolsView.Presenter testToolsPresenter, ScenarioSimulationContext context, GridWidget gridWidget, String dmnFilePath) {
        final FactModelTuple factMappingTuple = kogitoDmnTypeService.retrieveFactModelTuple(currentPath, dmnFilePath);
        getSuccessCallback(testToolsPresenter, context, gridWidget);
    }

}
