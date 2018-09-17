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
package org.drools.workbench.screens.scenariosimulation.client.utils;

import java.util.ArrayList;
import java.util.List;

import org.drools.workbench.screens.scenariosimulation.client.factories.FactoryProvider;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioTextBoxDOMElement;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.renderers.ScenarioGridColumnRenderer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.gwtbootstrap3.client.ui.TextBox;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.widget.dom.single.SingletonDOMElementFactory;

public class ScenarioSimulationUtils {

    public static ScenarioGridColumn getScenarioGridColumn(FactMapping factMapping, ScenarioGridPanel scenarioGridPanel, ScenarioGridLayer gridLayer) {
        return getScenarioGridColumn(getColumnBuilder(factMapping), scenarioGridPanel, gridLayer);
    }

    public static ScenarioGridColumn getScenarioGridColumn(ColumnBuilder columnBuilder, ScenarioGridPanel scenarioGridPanel, ScenarioGridLayer gridLayer) {
        ScenarioTextBoxSingletonDOMElementFactory factory = FactoryProvider.getHeaderHasNameTextBoxFactory(scenarioGridPanel, gridLayer);
        return new ScenarioGridColumn(columnBuilder.build(factory), new ScenarioGridColumnRenderer(), 100, false, factory);
    }

    public static ColumnBuilder getColumnBuilder(FactMapping fact) {
        ColumnBuilder columnBuilder = ColumnBuilder.get();

        columnBuilder.setColumnId(fact.getExpressionIdentifier().getName());
        columnBuilder.setColumnTitle(fact.getExpressionAlias());

        String columnGroup = fact.getExpressionIdentifier().getType().name();
        columnBuilder.setReadOnly(true);

        if (isOther(fact)) {
            columnBuilder.setColumnGroup(columnGroup);
            return columnBuilder;
        }

        columnBuilder.newLevel()
                .setColumnGroup(columnGroup).setReadOnly(false);

        return columnBuilder;
    }

    private static boolean isOther(FactMapping fact) {
        return FactMappingType.OTHER.equals(fact.getExpressionIdentifier().getType());
    }

    public static class ColumnBuilder {

        String columnId;
        String columnTitle;
        String columnGroup = "";
        boolean readOnly = false;
        ColumnBuilder nestedLevel;

        public static ColumnBuilder get() {
            return new ColumnBuilder();
        }

        public ColumnBuilder setColumnId(String columnId) {
            this.columnId = columnId;
            return this;
        }

        public ColumnBuilder setColumnTitle(String columnTitle) {
            this.columnTitle = columnTitle;
            return this;
        }

        public ColumnBuilder setColumnGroup(String columnGroup) {
            this.columnGroup = columnGroup;
            return this;
        }

        public ColumnBuilder setReadOnly(boolean readOnly) {
            this.readOnly = readOnly;
            return this;
        }

        public ColumnBuilder newLevel() {
            this.nestedLevel = ColumnBuilder.get()
                    .setColumnId(columnId)
                    .setColumnTitle(columnTitle)
                    .setColumnGroup(columnGroup)
                    .setReadOnly(readOnly);
            return this.nestedLevel;
        }

        public List<GridColumn.HeaderMetaData> build(SingletonDOMElementFactory<TextBox, ScenarioTextBoxDOMElement> factory) {
            List<GridColumn.HeaderMetaData> toReturn = new ArrayList<>();
            ColumnBuilder current = this;
            do {
                toReturn.add(current.internalBuild(factory));
                current = current.nestedLevel;
            } while (current != null);
            return toReturn;
        }

        private GridColumn.HeaderMetaData internalBuild(SingletonDOMElementFactory<TextBox, ScenarioTextBoxDOMElement> factory) {
            return new ScenarioHeaderMetaData(columnId, columnTitle, columnGroup, factory, readOnly);
        }
    }
}
