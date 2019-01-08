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

import java.util.stream.IntStream;

import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel;
import org.uberfire.ext.wires.core.grids.client.model.GridData;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getPropertyMetaDataGroup;
import static org.drools.workbench.screens.scenariosimulation.model.FactMapping.getPropertyPlaceHolder;

/**
 * <b>Abstract</b> <code>Command</code> class to provide common methods used by actual implementations
 */
public abstract class AbstractSetHeaderCommand extends AbstractScenarioSimulationCommand {
    
    private FactIdentifier factIdentifier;
    private AbstractSetHeaderCommandHelper commandHelper;

    public AbstractSetHeaderCommand() {
        super(true);
    }

    private class AbstractSetHeaderCommandHelper {

        private ScenarioGridColumn selectedColumn;
        private int columnIndex;

        private AbstractSetHeaderCommandHelper(ScenarioSimulationContext context) {
            ScenarioGridColumn selectedColumn = (ScenarioGridColumn) context.getModel().getSelectedColumn();
            if (selectedColumn == null) {
                return;
            }
            this.selectedColumn = selectedColumn;
            this.columnIndex = context.getModel().getColumns().indexOf(selectedColumn);
        }
    }

    private void setEditableHeadersAndSetFactIdentifier(ScenarioSimulationContext context, String className) {
        String fullPackage = context.getStatus().getFullPackage();
        if (!fullPackage.endsWith(".")) {
            fullPackage += ".";
        }
        String canonicalClassName = fullPackage + className;
        final ScenarioSimulationModel.Type simulationModelType = context.getModel().getSimulation().get().getSimulationDescriptor().getType();
        commandHelper.selectedColumn.setEditableHeaders(!simulationModelType.equals(ScenarioSimulationModel.Type.DMN));
        String nameToUseForCreation = simulationModelType.equals(ScenarioSimulationModel.Type.DMN) ? className : commandHelper.selectedColumn.getInformationHeaderMetaData().getColumnId();
        this.factIdentifier = getFactIdentifierByColumnTitle(className, context).orElse(FactIdentifier.create(nameToUseForCreation, canonicalClassName));
    }

    private void setInstance(String className){
        final ScenarioHeaderMetaData informationHeaderMetaData = commandHelper.selectedColumn.getInformationHeaderMetaData();
        informationHeaderMetaData.setTitle(className);
        commandHelper.selectedColumn.setInstanceAssigned(true);
        commandHelper.selectedColumn.setFactIdentifier(factIdentifier);
    }

    private void setPropertyHeaderMetaData(String placeHolder, String title) {
        final ScenarioHeaderMetaData propertyHeaderMetaData = commandHelper.selectedColumn.getPropertyHeaderMetaData();
        commandHelper.selectedColumn.setPlaceHolder(placeHolder);
        propertyHeaderMetaData.setTitle(title);
        propertyHeaderMetaData.setReadOnly(false);
    }

    protected void setInstanceHeader(ScenarioSimulationContext context) {
        commandHelper = new AbstractSetHeaderCommandHelper(context);

        String className = context.getStatus().getClassName();
        String title = getPropertyPlaceHolder(commandHelper.columnIndex);
        String placeHolder = ScenarioSimulationEditorConstants.INSTANCE.defineValidType();

        setEditableHeadersAndSetFactIdentifier(context, className);
        setInstance(className);
        setPropertyHeaderMetaData(placeHolder, title);
        context.getModel().updateColumnInstance(commandHelper.columnIndex, commandHelper.selectedColumn);
    }

    protected void setPropertyHeader(ScenarioSimulationContext context) {
        commandHelper = new AbstractSetHeaderCommandHelper(context);

        String value = context.getStatus().getValue();
        String title = value.contains(".") ? value.substring(value.indexOf(".") + 1) : "value";
        String className = value.split("\\.")[0];
        String placeHolder = ScenarioSimulationEditorConstants.INSTANCE.insertValue();

        setEditableHeadersAndSetFactIdentifier(context, className);

        final GridData.Range instanceLimits = context.getModel().getInstanceLimits(commandHelper.columnIndex);
        IntStream.range(instanceLimits.getMinRowIndex(), instanceLimits.getMaxRowIndex() + 1)
                .forEach(index -> {
                    final ScenarioGridColumn scenarioGridColumn = (ScenarioGridColumn) context.getModel().getColumns().get(index);
                    if (!scenarioGridColumn.isInstanceAssigned()) { // We have not defined the instance, yet
                        setInstance(className);
                    }
                });

        setPropertyHeaderMetaData(placeHolder, title);

        commandHelper.selectedColumn.getPropertyHeaderMetaData().setColumnGroup(getPropertyMetaDataGroup(commandHelper.selectedColumn.getInformationHeaderMetaData().getColumnGroup()));
        commandHelper.selectedColumn.setPropertyAssigned(true);
        context.getModel().updateColumnProperty(commandHelper.columnIndex,
                                                commandHelper.selectedColumn,
                                                value,
                                                context.getStatus().getValueClassName(), context.getStatus().isKeepData());
        if (context.getScenarioSimulationEditorPresenter() != null) {
            context.getScenarioSimulationEditorPresenter().reloadRightPanel(false);
        }
    }
}
