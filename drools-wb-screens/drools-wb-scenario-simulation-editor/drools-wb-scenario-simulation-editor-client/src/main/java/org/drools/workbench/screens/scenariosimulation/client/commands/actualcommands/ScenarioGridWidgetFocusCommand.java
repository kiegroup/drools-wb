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
package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationViolation;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridWidget;
import org.kie.workbench.common.command.client.CommandResult;
import org.kie.workbench.common.command.client.CommandResultBuilder;
import org.uberfire.mvp.Command;

/**
 * <code>Command</code> to <b>append</b> (i.e. put in the last position) a row
 */
public class ScenarioGridWidgetFocusCommand extends AbstractScenarioSimulationCommand {

    private final Command redoCommand;
    private final Command undoCommand;

    public ScenarioGridWidgetFocusCommand(Command redoCommand, Command undoCommand) {
        super(true);
        this.redoCommand = redoCommand;
        this.undoCommand = undoCommand;
    }

    @Override
    protected void internalExecute(ScenarioSimulationContext context) {
        // no op
    }

    @Override
    public CommandResult<ScenarioSimulationViolation> execute(ScenarioSimulationContext context) {
        return commonExecution(context);
    }

    @Override
    public CommandResult<ScenarioSimulationViolation> redo(ScenarioSimulationContext context) {
        redoCommand.execute();
        return CommandResultBuilder.SUCCESS;
    }

    @Override
    public CommandResult<ScenarioSimulationViolation> undo(ScenarioSimulationContext context) {
        undoCommand.execute();
        return CommandResultBuilder.SUCCESS;
    }
}
