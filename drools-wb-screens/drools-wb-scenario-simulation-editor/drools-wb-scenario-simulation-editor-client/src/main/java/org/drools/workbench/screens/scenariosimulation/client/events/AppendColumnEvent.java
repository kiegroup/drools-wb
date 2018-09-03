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
import org.drools.workbench.screens.scenariosimulation.client.handlers.AppendColumnEventHandler;

public class AppendColumnEvent extends GwtEvent<AppendColumnEventHandler> {

    public static Type<AppendColumnEventHandler> TYPE = new Type<>();

    private String columnTitle;
    private String columnGroup;

    public AppendColumnEvent(String columnTitle, String columnGroup) {
        this.columnTitle = columnTitle;
        this.columnGroup = columnGroup;
    }

    @Override
    public Type<AppendColumnEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AppendColumnEventHandler handler) {
        handler.onEvent(this);
    }

    public String getColumnTitle() {
        return columnTitle;
    }

    public String getColumnGroup() {
        return columnGroup;
    }
}
