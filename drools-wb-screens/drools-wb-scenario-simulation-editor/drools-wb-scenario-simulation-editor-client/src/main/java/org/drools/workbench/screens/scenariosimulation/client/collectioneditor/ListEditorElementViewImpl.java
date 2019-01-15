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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

/**
 * This class is used as <code>ListElement</code> <b>editor</b>
 *
 * It represent a single item of the List
 */
@Templated
public class ListEditorElementViewImpl implements ListEditorElementView {

    protected ListEditorElementView.Presenter presenter;

    @DataField("itemSeparator")
    protected LIElement itemSeparator = Document.get().createLIElement();

    @DataField("faAngleRight")
    protected SpanElement faAngleRight = Document.get().createSpanElement();

    @Override
    public void init(ListEditorElementView.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public LIElement getItemSeparator() {
        return itemSeparator;
    }

    @Override
    public SpanElement getFaAngleRight() {
        return faAngleRight;
    }

    @EventHandler("faAngleRight")
    public void onFaAngleRightClick(ClickEvent event) {
        presenter.onToggleRowExpansion(this, isShown());
    }

    protected boolean isShown() {
        return CollectionEditorUtils.isShown(faAngleRight);
    }

    protected void toggleRowExpansion(boolean toExpand) {
        CollectionEditorUtils.toggleRowExpansion(faAngleRight, toExpand);
    }
}
