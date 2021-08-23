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
package org.drools.workbench.screens.scenariosimulation.businesscentral.client.editor.strategies;

import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.AbstractDMNDataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.TestToolsView;
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.jboss.errai.common.client.api.Caller;

public class BusinessCentralDMNDataManagementStrategy extends AbstractDMNDataManagementStrategy {

    private final Caller<DMNTypeService> dmnTypeService;

    public BusinessCentralDMNDataManagementStrategy(Caller<DMNTypeService> dmnTypeService,
                                                    EventBus eventBus) {
        super(eventBus);
        this.dmnTypeService = dmnTypeService;
    }

    @Override
    protected void retrieveFactModelTuple(final TestToolsView.Presenter testToolsPresenter,
                                          final ScenarioSimulationContext context,
                                          final GridWidget gridWidget) {
        dmnTypeService.call(getSuccessCallback(testToolsPresenter, context, gridWidget),
                            getErrorCallback())
                .retrieveFactModelTuple(currentPath, dmnFilePath);
    }
}
