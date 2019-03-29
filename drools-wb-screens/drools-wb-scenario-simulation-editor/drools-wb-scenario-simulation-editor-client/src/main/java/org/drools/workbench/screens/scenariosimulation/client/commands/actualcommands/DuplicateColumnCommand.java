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
package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;

/**
 * <code>Command</code> to <b>duplicate</b> a column.
 */
@Dependent
public class DuplicateColumnCommand extends AbstractScenarioSimulationCommand {

    public final static String COPY_LABEL = "_copy";

    public DuplicateColumnCommand() {
        super(true);
    }

    protected void internalExecute(ScenarioSimulationContext context) {
        final List<GridColumn<?>> columns = context.getModel().getColumns();
        final ScenarioSimulationContext.Status status = context.getStatus();
        final ScenarioGridColumn selectedColumn = (ScenarioGridColumn) columns.get(status.getColumnIndex());
        int columnPosition = context.getModel().getInstanceLimits(context.getModel().getColumns().indexOf(selectedColumn)).getMaxRowIndex() + 1;
        int instancesCount = context.getModel().getInstancesCount(selectedColumn.getFactIdentifier().getClassName());
        AtomicInteger columnPositionAtomic = new AtomicInteger(columnPosition);
        context.getModel().getInstanceScenarioGridColumns(selectedColumn).forEach(originalColumn ->
            duplicateSingleColumn(context, columnPositionAtomic.getAndIncrement(), originalColumn, instancesCount)
        );
    }

    protected void duplicateSingleColumn(ScenarioSimulationContext context, int newColumnPosition, ScenarioGridColumn originalColumn, int instancesCount) {
        final ScenarioHeaderMetaData selectedInformationHeaderMetaData = originalColumn.getInformationHeaderMetaData();
        String columnGroup = selectedInformationHeaderMetaData.getColumnGroup();
        FactMappingType factMappingType = FactMappingType.valueOf(columnGroup.toUpperCase());
        StringBuilder instanceTitle = new StringBuilder();
        instanceTitle.append(originalColumn.getInformationHeaderMetaData().getTitle().split(COPY_LABEL)[0]);
        instanceTitle.append(COPY_LABEL);
        instanceTitle.append("_").append(instancesCount);
        String propertyTitle = originalColumn.getPropertyHeaderMetaData().getTitle();
        String placeHolder = ScenarioSimulationEditorConstants.INSTANCE.defineValidType();
        final ScenarioGridColumn scenarioGridColumnLocal = getScenarioGridColumnLocal(instanceTitle.toString(),
                                                                                      propertyTitle,
                                                                                      context.getStatus().getColumnId(),
                                                                                      columnGroup,
                                                                                      factMappingType,
                                                                                      context.getScenarioHeaderTextBoxSingletonDOMElementFactory(),
                                                                                      context.getScenarioCellTextAreaSingletonDOMElementFactory(),
                                                                                      placeHolder);
        scenarioGridColumnLocal.setInstanceAssigned(originalColumn.isInstanceAssigned());
        scenarioGridColumnLocal.setPropertyAssigned(originalColumn.isPropertyAssigned());
        scenarioGridColumnLocal.setFactory(originalColumn.getFactory());
        scenarioGridColumnLocal.setPlaceHolder(originalColumn.getPlaceHolder());

        context.getModel().duplicateSingleColumn(originalColumn, scenarioGridColumnLocal, newColumnPosition);
    }
}
