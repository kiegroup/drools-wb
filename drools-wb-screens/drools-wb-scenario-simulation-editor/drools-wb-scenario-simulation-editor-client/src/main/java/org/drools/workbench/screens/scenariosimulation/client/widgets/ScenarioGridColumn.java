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
package org.drools.workbench.screens.scenariosimulation.client.widgets;

import java.util.List;
import java.util.function.Consumer;

import org.uberfire.ext.wires.core.grids.client.model.GridCell;
import org.uberfire.ext.wires.core.grids.client.model.GridCellValue;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseGridCell;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseGridCellValue;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseGridColumn;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridBodyCellRenderContext;
import org.uberfire.ext.wires.core.grids.client.widget.dom.single.impl.TextBoxSingletonDOMElementFactory;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.columns.GridColumnRenderer;

public class ScenarioGridColumn extends BaseGridColumn<String> {

    private final TextBoxSingletonDOMElementFactory factory;

    public ScenarioGridColumn(HeaderMetaData headerMetaData, GridColumnRenderer<String> columnRenderer, double width, boolean isMovable, TextBoxSingletonDOMElementFactory factory) {
        super(headerMetaData, columnRenderer, width);
        this.setMovable(isMovable);
        this.factory = factory;
    }

    public ScenarioGridColumn(List<HeaderMetaData> headerMetaData, GridColumnRenderer<String> columnRenderer, double width, boolean isMovable, TextBoxSingletonDOMElementFactory factory) {
        super(headerMetaData, columnRenderer, width);
        this.setMovable(isMovable);
        this.factory = factory;
    }

    @Override
    public void edit(GridCell<String> cell, GridBodyCellRenderContext context, Consumer<GridCellValue<String>> callback) {
        factory.attachDomElement(context,
                                 e -> e.getWidget().setValue(assertCell(cell).getValue().getValue()),
                                 e -> e.getWidget().setFocus(true));
    }

    private GridCell<String> assertCell(final GridCell<String> cell) {
        if (cell != null) {
            return cell;
        }
        return new BaseGridCell<>(new BaseGridCellValue<String>(""));
    }
}