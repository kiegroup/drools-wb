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

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.uberfire.client.mvp.PlaceStatus;

/**
 * <code>Command</code> to <b>enable</b> the <code>TestToolsView</code>
 */
@Dependent
public class EnableTestToolsCommand extends AbstractScenarioSimulationCommand {

    public EnableTestToolsCommand() {
        super(false);
    }

    @Override
    protected void internalExecute(ScenarioSimulationContext context) {
        final ScenarioSimulationContext.Status status = context.getStatus();
        if (context.getScenarioSimulationEditorPresenter() != null) {
            context.getScenarioSimulationEditorPresenter().expandToolsDock(PlaceStatus.OPEN);
            context.getScenarioSimulationEditorPresenter().reloadTestTools(false);
        }
        if (context.getTestToolsPresenter() != null) {
            if (status.getFilterTerm() == null) {
                context.getTestToolsPresenter().onEnableEditorTab();
            } else {
                context.getTestToolsPresenter().onEnableEditorTab(status.getFilterTerm(), status.getPropertyNameElements(), status.isNotEqualsSearch());
            }

            final Set<String> assignedInstanceTitle = new HashSet<>();
            String columnGroup = ((ScenarioGridColumn) context.getModel().getSelectedColumn()).getInformationHeaderMetaData().getColumnGroup();
            String groupName = columnGroup.contains("-") ? columnGroup.substring(0, columnGroup.indexOf("-")) : columnGroup;
            context.getModel().getColumns().stream().
                    filter(c -> groupName.equals(((ScenarioGridColumn) c).getInformationHeaderMetaData().getColumnGroup())).
                    forEach(c -> assignedInstanceTitle.add(((ScenarioGridColumn) c).getInformationHeaderMetaData().getTitle()));
            context.getTestToolsPresenter().setAssignedInstanceTitleSet(assignedInstanceTitle);

/*
            final Map<String, Set<String>> assignedInstanceAndPropertiesMap = new TreeMap<>();
            context.getModel().getColumns().stream().
                    filter(c -> groupName.equals(((ScenarioGridColumn) c).getInformationHeaderMetaData().getColumnGroup())).
                    forEach(c -> assignedInstanceTitle.add(((ScenarioGridColumn) c).getInformationHeaderMetaData().getTitle()));
            context.getTestToolsPresenter().setAssignedInstanceTitleAndPropertiesMap(assignedInstanceAndPropertiesMap); */
        }
    }
}
