/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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

import java.util.function.Consumer;

import com.google.gwt.event.shared.GwtEvent;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.workbench.screens.scenariosimulation.client.handlers.UpdateSettingsDataEventHandler;

public class UpdateSettingsDataEvent extends GwtEvent<UpdateSettingsDataEventHandler> {

    public static final Type<UpdateSettingsDataEventHandler> TYPE = new Type<>();

    private final Consumer<Settings> settingsConsumer;

    public UpdateSettingsDataEvent(Consumer<Settings> settingsConsumer) {
        this.settingsConsumer = settingsConsumer;
    }

    @Override
    public Type<UpdateSettingsDataEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateSettingsDataEventHandler handler) {
        handler.onEvent(this);
    }

    public Consumer<Settings> getSettingsConsumer() {
        return settingsConsumer;
    }
}
