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

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

/**
 * This class is used as <code>ListElement</code> <b>editor</b>
 * <p>
 * It represent a single item of the List
 */
@Templated
public class ListEditorElementViewImpl implements ListEditorElementView {

    protected ListEditorElementView.Presenter presenter;

    protected int itemId;

    @DataField("itemContainer")
    protected UListElement itemContainer = Document.get().createULElement();

    @DataField("itemSeparator")
    protected LIElement itemSeparator = Document.get().createLIElement();

    @DataField("saveChange")
    protected LIElement saveChange = Document.get().createLIElement();

    @DataField("faAngleRight")
    protected SpanElement faAngleRight = Document.get().createSpanElement();

    @DataField("editItemButton")
    protected ButtonElement editItemButton = Document.get().createButtonElement();

    @DataField("deleteItemButton")
    protected ButtonElement deleteItemButton = Document.get().createButtonElement();

    @DataField("saveChangeButton")
    protected ButtonElement saveChangeButton = Document.get().createButtonElement();

    @DataField("cancelChangeButton")
    protected ButtonElement cancelChangeButton = Document.get().createButtonElement();

    @Override
    public void init(ListEditorElementView.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public int getItemId() {
        return itemId;
    }

    @Override
    public UListElement getItemContainer() {
        return itemContainer;
    }

    @Override
    public LIElement getItemSeparator() {
        return itemSeparator;
    }

    @Override
    public LIElement getSaveChange() {
        return saveChange;
    }

    @Override
    public SpanElement getFaAngleRight() {
        return faAngleRight;
    }

    @EventHandler("faAngleRight")
    public void onFaAngleRightClick(ClickEvent event) {
        presenter.onToggleRowExpansion(this, isShown());
    }

    @EventHandler("editItemButton")
    public void onEditItemButtonClick(ClickEvent event) {
        presenter.onEditItem(this);
    }

    @EventHandler("deleteItemButton")
    public void onDeleteItemButtonClick(ClickEvent event) {
        presenter.onDeleteItem(this);
    }

    @EventHandler("saveChangeButton")
    public void onSaveChangeButtonClick(ClickEvent event) {
        presenter.updateItem(this);
    }

    @EventHandler("cancelChangeButton")
    public void onCancelChangeButton(ClickEvent event) {
        presenter.onStopEditingItem(this);
    }

    protected boolean isShown() {
        return CollectionEditorUtils.isShown(faAngleRight);
    }

    protected void toggleRowExpansion(boolean toExpand) {
        CollectionEditorUtils.toggleRowExpansion(faAngleRight, toExpand);
    }
}
