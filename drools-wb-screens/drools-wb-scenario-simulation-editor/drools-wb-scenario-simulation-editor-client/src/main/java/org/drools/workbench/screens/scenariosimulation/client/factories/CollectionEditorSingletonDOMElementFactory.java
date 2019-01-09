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

package org.drools.workbench.screens.scenariosimulation.client.factories;

import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.CollectionEditorViewImpl;
import org.drools.workbench.screens.scenariosimulation.client.domelements.CollectionEditorDOMElement;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridBodyCellRenderContext;
import org.uberfire.ext.wires.core.grids.client.widget.dom.single.impl.BaseSingletonDOMElementFactory;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;
import org.uberfire.ext.wires.core.grids.client.widget.layer.impl.GridLienzoPanel;

public class CollectionEditorSingletonDOMElementFactory extends BaseSingletonDOMElementFactory<String, CollectionEditorViewImpl, CollectionEditorDOMElement> {

    /**
     * Flag to indicate if the <code>CollectionEditorViewImpl</code> will manage a <code>List</code> or a <code>Map</code>.
     */
    protected boolean listWidget = true;

    public CollectionEditorSingletonDOMElementFactory(final GridLienzoPanel gridPanel,
                                                      final GridLayer gridLayer,
                                                      final GridWidget gridWidget) {
        super(gridPanel,
              gridLayer,
              gridWidget);
    }

    @Override
    public CollectionEditorViewImpl createWidget() {
        CollectionEditorViewImpl toReturn = new CollectionEditorViewImpl();
        toReturn.setListWidget(listWidget);
        return toReturn;
    }

    @Override
    public CollectionEditorDOMElement createDomElement(final GridLayer gridLayer,
                                                               final GridWidget gridWidget,
                                                               final GridBodyCellRenderContext context) {
        this.widget = createWidget();
        this.e = internalCreateDomElement(widget, gridLayer, gridWidget);
        widget.addCloseCompositeEventHandler(event -> {
            destroyResources();
            gridLayer.batch();
            gridPanel.setFocus(true);
        });
        return e;
    }

    /**
     * Flag to indicate if the <code>CollectionEditorViewImpl</code> will manage a <code>List</code> or a <code>Map</code>.
     * Set to <code>true</code> to have the <b>List</b> widget, <code>false</code> for the <b>Map</b> one.
     * @param listWidget
     */
    public void setListWidget(boolean listWidget) {
        this.listWidget = listWidget;
    }

    @Override
    protected String getValue() {
//        if (widget != null) {
//            return widget.getValue();
//        }
        return null;
    }

    protected CollectionEditorDOMElement internalCreateDomElement(CollectionEditorViewImpl widget, GridLayer gridLayer, GridWidget gridWidget) {
       return new CollectionEditorDOMElement(widget, gridLayer, gridWidget);
    }
}

