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

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationBuilders;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getHeaderBuilder;

/**
 * <code>Command</code> to set the <i>instance</i> level header for a given column
 */
@Dependent
public class SetInstanceHeaderCommand extends AbstractCommand {

    private ScenarioGridModel model;
    private String columnId;
    private String fullClassName;

    public SetInstanceHeaderCommand() {
    }

    /**
     *
     * @param model
     * @param columnId
     * @param fullClassName
     * @param scenarioGridPanel
     * @param scenarioGridLayer
     */
    public SetInstanceHeaderCommand(ScenarioGridModel model, String columnId, String fullClassName, ScenarioGridPanel scenarioGridPanel, ScenarioGridLayer scenarioGridLayer) {
        super(scenarioGridPanel, scenarioGridLayer);
        this.model = model;
        this.columnId = columnId;
        this.fullClassName = fullClassName;
    }

    @Override
    public void execute() {
        ScenarioGridColumn selectedColumn = (ScenarioGridColumn) model.getSelectedColumn();
        if (selectedColumn == null) {
            return;
        }
        int columnIndex = model.getColumns().indexOf(selectedColumn);
        String columnGroup = selectedColumn.getInformationHeaderMetaData().getColumnGroup();
        FactMappingType factMappingType = FactMappingType.valueOf(columnGroup.toUpperCase());
        ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader = getHeaderTextBoxFactoryLocal();
        ScenarioSimulationBuilders.HeaderBuilder headerBuilder = getHeaderBuilderLocal(columnGroup, factMappingType, factoryHeader);
        final ScenarioGridColumn newColumn = getScenarioGridColumnLocal(headerBuilder);
        //selectedColumn.getInformationHeaderMetaData().setTitle(fullClassName);
        newColumn.getPropertyHeaderMetaData().setReadOnly(false);
        model.updateColumnInstance(columnIndex,
                                   newColumn);
    }

    protected ScenarioSimulationBuilders.HeaderBuilder getHeaderBuilderLocal(String columnGroup, FactMappingType factMappingType, ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader) {
        // indirection add for test
        return getHeaderBuilder(fullClassName, columnId, columnGroup, factMappingType, factoryHeader);
    }
}
