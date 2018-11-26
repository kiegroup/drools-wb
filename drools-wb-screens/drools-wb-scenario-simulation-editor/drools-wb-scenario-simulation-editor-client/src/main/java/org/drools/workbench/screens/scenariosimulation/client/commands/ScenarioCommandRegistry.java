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

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands.AbstractScenarioSimulationCommand;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.kie.workbench.common.command.client.CommandResult;
import org.kie.workbench.common.command.client.CommandResultBuilder;
import org.kie.workbench.common.command.client.impl.CommandResultImpl;
import org.kie.workbench.common.command.client.registry.command.CommandRegistryImpl;

/**
 * This class is used to store <code>Queue</code>es of <b>executed/undone</b> <code>Command</code>s
 */
@Dependent
public class ScenarioCommandRegistry extends CommandRegistryImpl<AbstractScenarioSimulationCommand> {

    protected final Deque<AbstractScenarioSimulationCommand> undoneCommands = new ArrayDeque<>();

    /**
     * Map used to pair an <code>AbstractScenarioSimulationCommand</code> (by its id) to the <code>ScenarioSimulationContext</code>
     * existing <b>soon before</b> command execution (by its json serialization)
     */
    private Map<Long, ScenarioSimulationContext.Status> commandIdContextMap = new HashMap<>();

    /**
     * Method to register the status as it was soon before the command execution,
     * to be used for undo/redo
     * @param previousStatus
     * @param command
     */
    public void register(ScenarioSimulationContext.Status previousStatus, AbstractScenarioSimulationCommand command) {
        super.register(command);
        commandIdContextMap.put(command.getId(), previousStatus);
    }

    /**
     * Calls <b>undo</b> on the last executed <code>Command</code>
     * @param scenarioSimulationContext
     * @throws NoSuchElementException
     */
    public CommandResult<ScenarioSimulationViolation> undo(ScenarioSimulationContext scenarioSimulationContext) throws NoSuchElementException {
        CommandResult<ScenarioSimulationViolation> toReturn;
        if (!getCommandHistory().isEmpty()) {
            final AbstractScenarioSimulationCommand toUndo = pop();
            final ScenarioSimulationContext.Status originalStatus = commandIdContextMap.get(toUndo.getId());
            final ScenarioSimulationContext.Status previousStatus = originalStatus.cloneStatus();
            scenarioSimulationContext.setStatus(originalStatus);
            toReturn = toUndo.undo(scenarioSimulationContext);
            if (Objects.equals(CommandResultBuilder.SUCCESS, toReturn)) {
                undoneCommands.push(toUndo);
                commandIdContextMap.put(toUndo.getId(), previousStatus);
            }
        } else {
            toReturn = new CommandResultImpl<>(CommandResult.Type.WARNING, Collections.singletonList(new ScenarioSimulationViolation("No commands to undo")));
        }
        return toReturn;
    }

    /**
     * Re-execute the last <b>undone</b> <code>Command</code>
     * @param scenarioSimulationContext
     * @throws NoSuchElementException
     */
    public CommandResult<ScenarioSimulationViolation> redo(ScenarioSimulationContext scenarioSimulationContext) throws NoSuchElementException {
        CommandResult<ScenarioSimulationViolation> toReturn;
        if (!undoneCommands.isEmpty()) {
            final AbstractScenarioSimulationCommand toRedo = undoneCommands.pop();
            final ScenarioSimulationContext.Status originalStatus = commandIdContextMap.get(toRedo.getId());
            final ScenarioSimulationContext.Status previousStatus = originalStatus.cloneStatus();
            scenarioSimulationContext.setStatus(previousStatus);
            final Simulation toRestore = scenarioSimulationContext.getStatus().getSimulation();
            if (toRestore != null) {
                scenarioSimulationContext.getScenarioSimulationEditorPresenter().getView().refreshContent(toRestore);
                scenarioSimulationContext.getScenarioSimulationEditorPresenter().getModel().setSimulation(toRestore);
            }
            toReturn = toRedo.execute(scenarioSimulationContext);
            if (Objects.equals(CommandResultBuilder.SUCCESS, toReturn)) {
                register(toRedo);
            }
        } else {
            toReturn = new CommandResultImpl<>(CommandResult.Type.WARNING, Collections.singletonList(new ScenarioSimulationViolation("No commands to redo")));
        }
        return toReturn;
    }
}
