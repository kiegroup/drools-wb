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
package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import org.drools.workbench.screens.scenariosimulation.client.events.CloseCompositeEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.CloseCompositeEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.HasCloseCompositeHandler;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

/**
 * This class is used as <code>Collection</code> <b>editor</b>
 *
 * The overall architecture is:
 * <p>this widget contains a series of elements</p>
 * <p>if this widget represent a list, each element will show a single item of the it</p>
 * <p>if this widget represent a map, each element will show a single entry (key/value) of it</p>
 * <p>the presenter will be responsible to choose which kind of elements are to be populated</p>
 */
@Templated
public class CollectionEditorViewImpl extends Composite implements HasCloseCompositeHandler,
                                                                   CollectionEditorView {

    @DataField("elementsContainer")
    protected DivElement elementsContainer = Document.get().createDivElement();


    protected CollectionEditorView.Presenter presenter;

    /**
     * Flag to indicate if this <code>CollectionEditorViewImpl</code> will manage a <code>List</code> or a <code>Map</code>.
     */
    protected boolean listWidget;

    /**
     *
     *
     * @param listWidget set to <code>true</code> if the current instance will manage a <code>List</code>,
     * <code>false</code> for a <code>Map</code>.
     */
    public CollectionEditorViewImpl(boolean listWidget) {
        this.listWidget = listWidget;
    }

    @Override
    public HandlerRegistration addCloseCompositeEventHandler(CloseCompositeEventHandler handler) {
        return addDomHandler(handler, CloseCompositeEvent.getType());
    }

    @Override
    public String getValue() {
        return presenter.getValue(this);
    }

    @Override
    public void setValue(String jsonString) {
        presenter.initStructure();
        presenter.setValue(jsonString, this);
    }

    @Override
    public boolean isListWidget() {
        return listWidget;
    }

    @Override
    public DivElement getElementsContainer() {
        return elementsContainer;
    }
}
