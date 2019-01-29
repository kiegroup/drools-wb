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
package org.drools.workbench.screens.scenariosimulation.client.editor.strategies;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;

/**
 * Abstract class to provide common methods to be used by actual implementations.
 */
public abstract class AbstractDataManagementStrategy implements DataManagementStrategy {

    protected static FactModelTree getSimpleClassFactModelTree(Class clazz) {
        String key = clazz.getSimpleName();
        Map<String, String> simpleProperties = new HashMap<>();
        String fullName = clazz.getCanonicalName();
        simpleProperties.put("value", fullName);
        String packageName = fullName.substring(0, fullName.lastIndexOf("."));
        return new FactModelTree(key, packageName, simpleProperties);
    }

    protected Map<String, List<String>> getAlreadyAssignedProperties(ScenarioGridModel scenarioGridModel) {
        final Map<String, List<String>> toReturn = new HashMap<>();
        if (scenarioGridModel.getSelectedColumn() != null) {
            final ScenarioGridColumn selectedColumn = (ScenarioGridColumn) scenarioGridModel.getSelectedColumn();
            if (selectedColumn.isInstanceAssigned()) {
                List<ScenarioGridColumn> instanceColumns = scenarioGridModel.getInstanceScenarioGridColumns(selectedColumn);
                final List<String> assignedProperties = instanceColumns.stream()
                        .filter(ScenarioGridColumn::isPropertyAssigned)
                        .map(instanceColumn -> instanceColumn.getPropertyHeaderMetaData().getTitle())
                        .collect(Collectors.toList());
                toReturn.put(selectedColumn.getInformationHeaderMetaData().getTitle(), assignedProperties);
            }
        }
        return toReturn;
    }
}
