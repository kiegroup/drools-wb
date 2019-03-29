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
package org.drools.workbench.screens.scenariosimulation.client.events;

import com.google.gwt.event.shared.GwtEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ReloadSimulationEventHandler;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;

/**
 * <code>GwtEvent</code> to <b>reload</b> the <code>Simulation</code>
 */
public class ReloadSimulationEvent extends GwtEvent<ReloadSimulationEventHandler> {

    public static Type<ReloadSimulationEventHandler> TYPE = new Type<>();
    private final Simulation simulation;

    public ReloadSimulationEvent(Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public Type<ReloadSimulationEventHandler> getAssociatedType() {
        return TYPE;
    }

    public Simulation getSimulation() {
        return simulation;
    }

    @Override
    protected void dispatch(ReloadSimulationEventHandler handler) {
        handler.onEvent(this);
    }
}
