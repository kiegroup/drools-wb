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
import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class ObjectSeparator {

    @DataField("objectSeparator")
    protected LIElement objectSeparator = Document.get().createLIElement();

    @DataField("addItemButton")
    protected ButtonElement addItemButton = Document.get().createButtonElement();

    protected CollectionEditorPresenter collectionEditorPresenter;

    protected String key;

    public void setObjectSeparator(LIElement objectSeparator) {
        this.objectSeparator = objectSeparator;
    }

    public LIElement getObjectSeparator() {
        return objectSeparator;
    }

    public void init(CollectionEditorPresenter collectionEditorPresenter, String key) {
        this.collectionEditorPresenter = collectionEditorPresenter;
        this.key = key;
    }

    @EventHandler("addItemButton")
    public void onAddItemButtonClick(ClickEvent clickEvent) {
        collectionEditorPresenter.showEditingBox(key);
    }
}
