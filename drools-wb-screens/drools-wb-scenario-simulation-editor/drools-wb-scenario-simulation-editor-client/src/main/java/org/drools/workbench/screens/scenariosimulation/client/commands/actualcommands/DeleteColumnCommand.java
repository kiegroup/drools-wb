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

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.enterprise.context.Dependent;

import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;

/**
 * <code>Command</code> to <b>delete</b> a column. <b>Eventually</b> add a ne column if the deleted one is the last of its group.
 */
@Dependent
public class DeleteColumnCommand extends AbstractScenarioSimulationCommand {

    public DeleteColumnCommand() {
        super(true);
    }

    @Override
    protected void internalExecute(ScenarioSimulationContext context) {
        final ScenarioSimulationContext.Status status = context.getStatus();
        final List<Double> widthsToRestore = context.getModel().getColumns().stream().map(GridColumn::getWidth).collect(Collectors.toList());
        context.getModel().deleteColumn(status.getColumnIndex());
        if (context.getModel().getGroupSize(status.getColumnGroup()) < 1) {
            FactMappingType factMappingType = FactMappingType.valueOf(status.getColumnGroup().toUpperCase());
            Map.Entry<String, String> validPlaceholders = context.getModel().getValidPlaceholders();
            String instanceTitle = validPlaceholders.getKey();
            String propertyTitle = validPlaceholders.getValue();
            context.getModel().insertColumn(status.getColumnIndex(), getScenarioGridColumnLocal(instanceTitle,
                                                                                                propertyTitle,
                                                                                                String.valueOf(new Date().getTime()),
                                                                                                status.getColumnGroup(),
                                                                                                factMappingType,
                                                                                                context.getScenarioHeaderTextBoxSingletonDOMElementFactory(),
                                                                                                context.getScenarioCellTextAreaSingletonDOMElementFactory(),
                                                                                                ScenarioSimulationEditorConstants.INSTANCE.defineValidType()));
            /* Restoring the expected columns dimension, overriding the automatic resizing */
            IntStream.range(0, widthsToRestore.size())
                    .forEach(index -> context.getModel().updateColumnWidth(context.getModel().getColumns().get(index), widthsToRestore.get(index)));

        }
        new ReloadTestToolsCommand().execute(context);
    }
}
