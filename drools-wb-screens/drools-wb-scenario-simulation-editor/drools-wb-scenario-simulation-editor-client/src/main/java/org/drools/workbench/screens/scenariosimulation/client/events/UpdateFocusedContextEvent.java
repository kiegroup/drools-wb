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
package org.drools.workbench.screens.scenariosimulation.client.events;

import com.google.gwt.event.shared.GwtEvent;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.handlers.UpdateFocusedContextEventHandler;

/**
 * <code>GwtEvent</code> to <b>append</b> (i.e. put in the last position) a row
 */
public class UpdateFocusedContextEvent extends GwtEvent<UpdateFocusedContextEventHandler> {

    public static final Type<UpdateFocusedContextEventHandler> TYPE = new Type<>();

    private final ScenarioSimulationContext context;

    public UpdateFocusedContextEvent(ScenarioSimulationContext context) {
        this.context = context;
    }

    @Override
    public Type<UpdateFocusedContextEventHandler> getAssociatedType() {
        return TYPE;
    }

    public ScenarioSimulationContext getContext() {
        return context;
    }

    @Override
    protected void dispatch(UpdateFocusedContextEventHandler handler) {
        handler.onEvent(this);
    }
}
