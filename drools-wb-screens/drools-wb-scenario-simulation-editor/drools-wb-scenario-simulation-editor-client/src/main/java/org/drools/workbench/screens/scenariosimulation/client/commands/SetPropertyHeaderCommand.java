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

import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;

/**
 * <code>Command</code> to to set the <i>property</i> level header for a given column
 */
@Dependent
public class SetPropertyHeaderCommand extends AbstractCommand {

    private ScenarioGridModel model;
    private String fullPackage;
    private String value;
    private String valueClassName;
    protected boolean keepData;

    public SetPropertyHeaderCommand() {
    }

    /**
     * @param model
     * @param columnId
     * @param fullPackage
     * @param value
     * @param valueClassName
     * @param scenarioGridPanel
     * @param scenarioGridLayer
     */
    public SetPropertyHeaderCommand(ScenarioGridModel model, String fullPackage, String value, String valueClassName, ScenarioGridPanel scenarioGridPanel, ScenarioGridLayer scenarioGridLayer, boolean keepData) {
        super(scenarioGridPanel, scenarioGridLayer);
        this.model = model;
        this.fullPackage = fullPackage;
        this.value = value;
        this.valueClassName = valueClassName;
        this.keepData = keepData;
    }

    @Override
    public void execute() {
        ScenarioGridColumn selectedColumn = (ScenarioGridColumn) model.getSelectedColumn();
        if (selectedColumn == null) {
            return;
        }
        int columnIndex = model.getColumns().indexOf(selectedColumn);
        String columnGroup = selectedColumn.getInformationHeaderMetaData().getColumnGroup();
//        FactMappingType factMappingType = FactMappingType.valueOf(columnGroup.toUpperCase());
//        ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader = getHeaderTextBoxFactoryLocal();
//        ScenarioSimulationBuilders.HeaderBuilder headerBuilder = getHeaderBuilderLocal(columnGroup, factMappingType, factoryHeader);

        selectedColumn.getPropertyHeaderMetaData().setColumnGroup(columnGroup);
        selectedColumn.getPropertyHeaderMetaData().setTitle(value);

        model.updateColumnProperty(columnIndex,
                                   selectedColumn
                               /*getScenarioGridColumnLocal(headerBuilder)*/,
                                   fullPackage,
                                   value,
                                   valueClassName, keepData);
    }

//    protected ScenarioSimulationBuilders.HeaderBuilder getHeaderBuilderLocal(String columnGroup, FactMappingType factMappingType, ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader) {
//        // indirection add for test
//        return getHeaderBuilder(value, columnId, columnGroup, factMappingType, factoryHeader);
//    }

}
