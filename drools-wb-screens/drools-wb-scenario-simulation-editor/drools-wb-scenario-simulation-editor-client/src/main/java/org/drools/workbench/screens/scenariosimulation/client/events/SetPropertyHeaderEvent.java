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

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;
import org.drools.workbench.screens.scenariosimulation.client.enums.GRID_WIDGET;
import org.drools.workbench.screens.scenariosimulation.client.handlers.SetPropertyHeaderEventHandler;

/**
 * <code>GwtEvent</code> to set the <i>property</i> level header for a given column
 */
public class SetPropertyHeaderEvent extends GwtEvent<SetPropertyHeaderEventHandler> {

    public static final Type<SetPropertyHeaderEventHandler> TYPE = new Type<>();

    private final GRID_WIDGET gridWidget;
    private final String fullPackage;
    private final List<String> propertyNameElements;
    private final String valueClassName;

    /**
     * Use this constructor to modify the <i>property</i> level header
     *
     * @param gridWidget
     * @param fullPackage
     * @param propertyNameElements
     * @param valueClassName
     */
    public SetPropertyHeaderEvent(GRID_WIDGET gridWidget, String fullPackage, List<String> propertyNameElements, String valueClassName) {
        this.gridWidget = gridWidget;
        this.fullPackage = fullPackage;
        this.propertyNameElements = propertyNameElements;
        this.valueClassName = valueClassName;
    }

    @Override
    public Type<SetPropertyHeaderEventHandler> getAssociatedType() {
        return TYPE;
    }

    public GRID_WIDGET getGridWidget() {
        return gridWidget;
    }

    public String getFullPackage() {
        return fullPackage;
    }

    public List<String> getPropertyNameElements() {
        return propertyNameElements;
    }

    public String getValueClassName() {
        return valueClassName;
    }

    @Override
    protected void dispatch(SetPropertyHeaderEventHandler handler) {
        handler.onEvent(this);
    }
}
