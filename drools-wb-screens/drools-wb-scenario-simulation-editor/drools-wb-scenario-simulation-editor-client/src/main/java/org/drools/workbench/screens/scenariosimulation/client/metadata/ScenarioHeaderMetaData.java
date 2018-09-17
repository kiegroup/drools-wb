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
package org.drools.workbench.screens.scenariosimulation.client.metadata;

import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxDOMElement;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.gwtbootstrap3.client.ui.TextBox;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseHeaderMetaData;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridBodyCellEditContext;
import org.uberfire.ext.wires.core.grids.client.widget.dom.single.SingletonDOMElementFactory;

public class ScenarioHeaderMetaData extends BaseHeaderMetaData {

    final SingletonDOMElementFactory<TextBox, ScenarioHeaderTextBoxDOMElement> factory;
    final String columnId;
    final boolean readOnly;
    private final FactMapping factMapping;

    public ScenarioHeaderMetaData(FactMapping factMapping, String columnId, String columnTitle, String columnGroup, final SingletonDOMElementFactory<TextBox, ScenarioHeaderTextBoxDOMElement> factory, boolean readOnly) {
        super(columnTitle, columnGroup);
        this.factMapping = factMapping;
        this.columnId = columnId;
        this.factory = factory;
        this.readOnly = readOnly;
    }

    public ScenarioHeaderMetaData(FactMapping factMapping, String columnId, String columnTitle, String columnGroup, final SingletonDOMElementFactory<TextBox, ScenarioHeaderTextBoxDOMElement> factory) {
        this(factMapping, columnId, columnTitle, columnGroup, factory, false);
    }

    public void edit(final GridBodyCellEditContext context) {
        factory.attachDomElement(context,
                                 (e) -> e.getWidget().setText(getTitle()),
                                 (e) -> e.getWidget().setFocus(true));
    }

    public String getColumnId() {
        return columnId;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    @Override
    public void setTitle(String columnTitle) {
        super.setTitle(columnTitle);
        factMapping.setExpressionAlias(columnTitle);
    }
}