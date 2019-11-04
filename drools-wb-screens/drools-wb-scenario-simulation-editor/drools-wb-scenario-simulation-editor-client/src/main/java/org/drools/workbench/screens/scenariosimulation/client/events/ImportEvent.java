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
import org.drools.workbench.screens.scenariosimulation.client.enums.GRID_WIDGET;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ImportEventHandler;

/**
 * <code>GwtEvent</code> to execute <b>import</b> from file
 */
public class ImportEvent extends GwtEvent<ImportEventHandler> {

    public static final Type<ImportEventHandler> TYPE = new Type<>();

    private final GRID_WIDGET gridWidget;

    /**
     *
     * @param gridWidget
     */
    public ImportEvent(GRID_WIDGET gridWidget) {
        this.gridWidget = gridWidget;
    }

    @Override
    public Type<ImportEventHandler> getAssociatedType() {
        return TYPE;
    }

    public GRID_WIDGET getGridWidget() {
        return gridWidget;
    }

    @Override
    protected void dispatch(ImportEventHandler handler) {
        handler.onEvent(this);
    }


}
