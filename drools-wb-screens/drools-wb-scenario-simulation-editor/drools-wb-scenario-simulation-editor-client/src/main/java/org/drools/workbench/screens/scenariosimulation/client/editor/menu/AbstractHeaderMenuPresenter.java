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
package org.drools.workbench.screens.scenariosimulation.client.editor.menu;

import javax.inject.Inject;

import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;

/**
 * This is the first <i>ScenaraioSimulation</i> specific abstract class - i.e. it is bound to a specific use case. Not every implementation
 * would need this. Menu initialization may be done in other different ways. It is provided to avoid code duplication in concrete implementations
 */
public abstract class AbstractHeaderMenuPresenter extends BaseMenu implements HeaderMenuPresenter {

    protected ScenarioSimulationEditorConstants constants = ScenarioSimulationEditorConstants.INSTANCE;

    protected ScenarioGridModel model;

    protected AppendRowEvent appendRowEvent = new AppendRowEvent();

    protected AppendColumnEvent appendColumnEvent = new AppendColumnEvent("new_column_id", "NEW_COLUMN_TITLE");

    @Inject
    protected EventBus eventBus;

}
