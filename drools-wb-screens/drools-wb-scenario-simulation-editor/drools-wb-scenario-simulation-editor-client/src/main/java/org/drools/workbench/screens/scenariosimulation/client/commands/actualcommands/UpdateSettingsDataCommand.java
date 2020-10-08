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
package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import java.util.Optional;
import java.util.function.Consumer;

import org.drools.scenariosimulation.api.model.Settings;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationViolation;
import org.kie.workbench.common.command.client.CommandResult;

public class UpdateSettingsDataCommand extends AbstractScenarioGridCommand {

    private final Consumer<Settings> settingsConsumer;

    public UpdateSettingsDataCommand(Consumer<Settings> settingsConsumer) {
        this.settingsConsumer = settingsConsumer;
    }

    @Override
    protected void internalExecute(ScenarioSimulationContext context)  {
        settingsConsumer.accept(context.getStatus().getSettings());
        context.getScenarioSimulationEditorPresenter().reloadSettings();
    }


    @Override
    public Optional<CommandResult<ScenarioSimulationViolation>> commonUndoRedoPreexecution(ScenarioSimulationContext context) {
        return Optional.empty();
    }
}
