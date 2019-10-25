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
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioGridWidgetFocusEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridWidget;

/**
 * <code>GwtEvent</code> fired when <code>ScenarioGridWidget</code> get focus
 */
public class ScenarioGridWidgetFocusEvent extends GwtEvent<ScenarioGridWidgetFocusEventHandler> {

    public static final Type<ScenarioGridWidgetFocusEventHandler> TYPE = new Type<>();

    private final ScenarioGridWidget focused;
    private final ScenarioGridWidget unFocused;

    public ScenarioGridWidgetFocusEvent(ScenarioGridWidget focused, ScenarioGridWidget unFocused) {
        this.focused = focused;
        this.unFocused = unFocused;
    }

    @Override
    public Type<ScenarioGridWidgetFocusEventHandler> getAssociatedType() {
        return TYPE;
    }

    public ScenarioGridWidget getFocused() {
        return focused;
    }

    public ScenarioGridWidget getUnFocused() {
        return unFocused;
    }

    @Override
    protected void dispatch(ScenarioGridWidgetFocusEventHandler handler) {
        handler.onEvent(this);
    }


}
