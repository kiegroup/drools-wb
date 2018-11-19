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
import java.util.Deque;
import java.util.NoSuchElementException;

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands.AbstractScenarioSimulationCommand;
import org.kie.workbench.common.command.client.registry.command.CommandRegistryImpl;

/**
 * This class is used to store <code>Queue</code>es of <b>executed/undone</b> <code>Command</code>s
 */
@Dependent
public class ScenarioCommandRegistry extends CommandRegistryImpl<AbstractScenarioSimulationCommand> {

    protected final Deque<AbstractScenarioSimulationCommand> undoneCommands = new ArrayDeque<>();

    /**
     * Calls <b>undo</b> on the last executed <code>Command</code>
     */
    public void undo(ScenarioSimulationContext scenarioSimulationContext) throws NoSuchElementException {
        final AbstractScenarioSimulationCommand toUndo = pop();
        toUndo.undo(scenarioSimulationContext);
        undoneCommands.push(toUndo);

    }

    /**
     * Re-execute the last <b>undone</b> <code>Command</code>
     */
    public void redo(ScenarioSimulationContext scenarioSimulationContext) throws NoSuchElementException  {
        final AbstractScenarioSimulationCommand toRedo = undoneCommands.pop();
        toRedo.execute(scenarioSimulationContext);
        register(toRedo);
    }



}
