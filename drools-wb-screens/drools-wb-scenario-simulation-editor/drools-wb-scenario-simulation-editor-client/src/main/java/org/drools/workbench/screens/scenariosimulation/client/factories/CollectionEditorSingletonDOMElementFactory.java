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

import java.util.Map;

import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.CollectionEditorViewImpl;
import org.drools.workbench.screens.scenariosimulation.client.domelements.CollectionEditorDOMElement;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridBodyCellRenderContext;
import org.uberfire.ext.wires.core.grids.client.widget.dom.single.impl.BaseSingletonDOMElementFactory;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;
import org.uberfire.ext.wires.core.grids.client.widget.layer.impl.GridLienzoPanel;

public class CollectionEditorSingletonDOMElementFactory extends BaseSingletonDOMElementFactory<String, CollectionEditorViewImpl, CollectionEditorDOMElement> {

    protected ViewsProvider viewsProvider;

    /**
     * Flag to indicate if the <code>CollectionEditorViewImpl</code> will manage a <code>List</code> or a <code>Map</code>.
     */
    protected boolean listWidget = true;

    /**
     * The <code>Map</code> to be used to create the skeleton of the <code>CollectionEditorViewImpl</code> editor
     */
    protected Map<String, String> instancePropertyMap;

    /**
     * The <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     */
    protected String key;


    public CollectionEditorSingletonDOMElementFactory(final GridLienzoPanel gridPanel,
                                                      final GridLayer gridLayer,
                                                      final GridWidget gridWidget,
                                                      ViewsProvider viewsProvider) {
        super(gridPanel,
              gridLayer,
              gridWidget);
        this.viewsProvider = viewsProvider;
    }

    @Override
    public CollectionEditorViewImpl createWidget() {
        CollectionEditorViewImpl toReturn = (CollectionEditorViewImpl) viewsProvider.getCollectionEditorView();
        toReturn.setListWidget(listWidget);
        toReturn.initStructure(key, instancePropertyMap);
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

    /**
     * Set the <code>Map</code> to be used to create the skeleton of the <code>CollectionEditorViewImpl</code> editor
     * @param instancePropertyMap
     */
    public void setInstancePropertyMap(Map<String, String> instancePropertyMap) {
        this.instancePropertyMap = instancePropertyMap;
    }

    /**
     * Set the <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    protected String getValue() {
        return widget != null ? widget.getValue() : null;
    }

    protected CollectionEditorDOMElement internalCreateDomElement(CollectionEditorViewImpl widget, GridLayer gridLayer, GridWidget gridWidget) {
        return new CollectionEditorDOMElement(widget, gridLayer, gridWidget);
    }
}

