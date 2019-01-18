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

import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.FocusWidget;
import org.drools.workbench.screens.scenariosimulation.client.events.CloseCompositeEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.SaveEditorEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.CloseCompositeEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.HasCloseCompositeHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.HasSaveEditorHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.SaveEditorEventHandler;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

/**
 * This class is used as <code>Collection</code> <b>editor</b>
 * <p>
 * The overall architecture is:
 * <p>this widget contains a series of elements</p>
 * <p>if this widget represent a list, each element will show a single item of it, represented by a <code>ListEditorElementViewImpl</code></p>
 * <p>if this widget represent a map, each element will show a single entry (key/value) of it, represented by a <code>MapEditorElementViewImpl</code></p>
 * <p><code>PropertyEditorViewImpl</code> represents a single property (label with name and textbox for value)</p>
 * <p>each item/key/value could be a simple java object or a complex one</p>
 * <p>for complex java object, for each property a <code>PropertyEditorViewImpl</code> will be created</p>
 * <p>the presenter will be responsible to choose which kind of elements are to be populated</p>
 */
@Templated
public class CollectionEditorViewImpl extends FocusWidget implements HasCloseCompositeHandler,
                                                                     HasSaveEditorHandler,
                                                                     CollectionEditorView {

    @Inject
    protected CollectionEditorPresenter presenter;

    @DataField("collectionEditor")
    protected DivElement collectionEditor = Document.get().createDivElement();

    @DataField("elementsContainer")
    protected UListElement elementsContainer = Document.get().createULElement();

    @DataField("closeCollectionEditorButton")
    protected ButtonElement closeCollectionEditorButton = Document.get().createButtonElement();

    @DataField("objectSeparator")
    protected LIElement objectSeparator = Document.get().createLIElement();

    @DataField("cancelButton")
    protected ButtonElement cancelButton = Document.get().createButtonElement();

    @DataField("saveButton")
    protected ButtonElement saveButton = Document.get().createButtonElement();

    @DataField("editorTitle")
    protected HeadingElement editorTitle = Document.get().createHElement(4);

    @DataField("faAngleRight")
    protected SpanElement faAngleRight = Document.get().createSpanElement();

    @DataField("propertyTitle")
    protected SpanElement propertyTitle = Document.get().createSpanElement();

    /**
     * Flag to indicate if this <code>CollectionEditorViewImpl</code> will manage a <code>List</code> or a <code>Map</code>.
     */
    protected boolean listWidget;

    /**
     * The <b>json</b> representation of the values of this editor
     */
    protected String value;

    public CollectionEditorViewImpl() {
        setElement(collectionEditor);
    }

    /**
     * @param listWidget set to <code>true</code> if the current instance will manage a <code>List</code>,
     * <code>false</code> for a <code>Map</code>.
     */
    @Override
    public void setListWidget(boolean listWidget) {
        this.listWidget = listWidget;
    }

    /**
     * Set the <b>name</b> of the property and the <code>Map</code> to be used to create the skeleton of the current <code>CollectionEditorViewImpl</code> editor
     * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
     * @param instancePropertyMap
     */
    public void initStructure(String key, Map<String, String> instancePropertyMap) {
        presenter.initStructure(key, instancePropertyMap, this);
    }

    @Override
    public HandlerRegistration addCloseCompositeEventHandler(CloseCompositeEventHandler handler) {
        return addDomHandler(handler, CloseCompositeEvent.getType());
    }

    @Override
    public HandlerRegistration addSaveEditorEventHandler(SaveEditorEventHandler handler) {
        return addDomHandler(handler, SaveEditorEvent.getType());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String key, String jsonString) {
        presenter.setValue(key, jsonString, this);
    }

    @Override
    public boolean isListWidget() {
        return listWidget;
    }

    @Override
    public UListElement getElementsContainer() {
        return elementsContainer;
    }

    @Override
    public LIElement getObjectSeparator() {
        return objectSeparator;
    }

    @Override
    public HeadingElement getEditorTitle() {
        return editorTitle;
    }

    @Override
    public SpanElement getPropertyTitle() {
        return propertyTitle;
    }

    @EventHandler("closeCollectionEditorButton")
    public void onCloseCollectionEditorButtonClick(ClickEvent clickEvent) {
        close();
    }

    @EventHandler("cancelButton")
    public void onCancelButton(ClickEvent clickEvent) {
        close();
    }

    @EventHandler("saveButton")
    public void onSaveButton(ClickEvent clickEvent) {
        presenter.save(this);
    }

    @EventHandler("faAngleRight")
    public void onFaAngleRightClick(ClickEvent event) {
        presenter.onToggleRowExpansion(this, isShown());
    }

    @Override
    public void toggleRowExpansion() {
        toggleRowExpansion(!isShown());
    }

    public void updateValue(String value) {
        this.value = value;
        fireEvent(new SaveEditorEvent());
    }

    protected boolean isShown() {
        return CollectionEditorUtils.isShown(faAngleRight);
    }

    protected void toggleRowExpansion(boolean toExpand) {
        CollectionEditorUtils.toggleRowExpansion(faAngleRight, toExpand);
    }

    protected void close() {
        fireEvent(new CloseCompositeEvent());
    }
}
