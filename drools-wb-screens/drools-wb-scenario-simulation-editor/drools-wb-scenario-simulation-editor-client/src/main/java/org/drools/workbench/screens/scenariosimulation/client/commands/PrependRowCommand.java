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
package org.drools.workbench.screens.scenariosimulation.client.commands;

import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridRow;
import org.kie.workbench.common.command.CommandResult;

/**
 *  <code>Command</code> to <b>prepend</b> (i.e. put in the first position) a row
 */
public class PrependRowCommand extends AbstractScenarioSimulationCommand {

    @Override
    public CommandResult<ScenarioSimulationViolation> execute(ScenarioSimulationContext context) {
        context.getModel().insertRow(0, new ScenarioGridRow());
        return commonExecution(context);
    }
}
