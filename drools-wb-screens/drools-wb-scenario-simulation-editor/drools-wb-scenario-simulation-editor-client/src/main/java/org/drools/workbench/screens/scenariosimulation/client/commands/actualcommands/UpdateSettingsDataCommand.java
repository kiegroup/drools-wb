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

import java.util.Collections;
import java.util.Optional;
import java.util.function.Consumer;

import org.drools.scenariosimulation.api.model.Settings;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationViolation;
import org.kie.workbench.common.command.client.CommandResult;
import org.kie.workbench.common.command.client.CommandResultBuilder;
import org.kie.workbench.common.command.client.impl.CommandResultImpl;

public class UpdateSettingsDataCommand extends AbstractScenarioSimulationUndoableCommand<Settings> {

    private final Consumer<Settings> settingsConsumer;

    public UpdateSettingsDataCommand(Consumer<Settings> settingsConsumer) {
        this.settingsConsumer = settingsConsumer;
    }

    @Override
    protected Settings setRestorableStatusPreExecution(ScenarioSimulationContext context) {
        return context.getStatus().getSettings().cloneSettings();
    }

    @Override
    public Optional<CommandResult<ScenarioSimulationViolation>> commonUndoRedoPreexecution(ScenarioSimulationContext context) {
        context.getScenarioSimulationEditorPresenter().expandSettingsDock();
        return Optional.of(CommandResultBuilder.SUCCESS);
    }

    protected CommandResult<ScenarioSimulationViolation> setCurrentContext(ScenarioSimulationContext context) {
        try {
            if (restorableStatus == null) {
                throw new IllegalStateException("restorableSettings is null");
            }
            final Settings originalSettings = context.getStatus().getSettings().cloneSettings();
            context.getScenarioSimulationEditorPresenter().getModel().setSettings(restorableStatus);
            context.getStatus().setSettings(restorableStatus);
            restorableStatus = originalSettings;
            context.getScenarioSimulationEditorPresenter().reloadSettings();
            return commonExecution(context);
        } catch (Exception e) {
            return new CommandResultImpl<>(CommandResult.Type.ERROR, Collections.singleton(new ScenarioSimulationViolation(e.getMessage())));
        }
    }

    @Override
    protected void internalExecute(ScenarioSimulationContext context)  {
        settingsConsumer.accept(context.getStatus().getSettings());
        context.getScenarioSimulationEditorPresenter().reloadSettings();
    }

}
