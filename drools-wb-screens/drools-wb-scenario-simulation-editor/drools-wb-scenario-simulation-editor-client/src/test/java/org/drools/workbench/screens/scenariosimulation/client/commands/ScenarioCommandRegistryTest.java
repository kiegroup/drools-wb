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

import java.util.NoSuchElementException;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.AbstractScenarioSimulationTest;
import org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands.AppendRowCommand;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.command.client.CommandResult;
import org.kie.workbench.common.command.client.CommandResultBuilder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioCommandRegistryTest extends AbstractScenarioSimulationTest {


    private ScenarioCommandRegistry scenarioCommandRegistry;

    private AppendRowCommand appendRowCommandMock;


    @Before
    public void setup() {
        super.setup();
        scenarioCommandRegistry = spy(new ScenarioCommandRegistry() {

        });

        appendRowCommandMock = spy(new AppendRowCommand() {
            @Override
            public CommandResult<ScenarioSimulationViolation> execute(ScenarioSimulationContext context) {
                return CommandResultBuilder.SUCCESS;
            }

            @Override
            public CommandResult<ScenarioSimulationViolation> undo(ScenarioSimulationContext context) {
                return CommandResultBuilder.SUCCESS;
            }
        });
    }


    @Test(expected = NoSuchElementException.class)
    public void undoFailing() {
        scenarioCommandRegistry.undoneCommands.clear();
        scenarioCommandRegistry.undo(scenarioSimulationContext);
    }

    @Test
    public void undoNotFailing() {
        int currentSize = scenarioCommandRegistry.undoneCommands.size();
        scenarioCommandRegistry.register(scenarioSimulationContext.getStatus(), appendRowCommandMock);
        scenarioCommandRegistry.undo(scenarioSimulationContext);
        assertEquals(currentSize +1, scenarioCommandRegistry.undoneCommands.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void redoFailing() {
        scenarioCommandRegistry.undoneCommands.clear();
        scenarioCommandRegistry.redo(scenarioSimulationContext);
    }

    @Test
    public void redoNotFailing() {
        scenarioCommandRegistry.undoneCommands.push(appendRowCommandMock);
        int currentSize = scenarioCommandRegistry.undoneCommands.size();
        scenarioCommandRegistry.redo(scenarioSimulationContext);
        assertEquals(currentSize -1, scenarioCommandRegistry.undoneCommands.size());
    }



}