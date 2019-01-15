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
package org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.UListElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class ListEditingBoxImpl implements ListEditingBox {

    @DataField("editingBox")
    protected DivElement editingBox = Document.get().createDivElement();

    @DataField("editingBoxTitle")
    protected HeadingElement editingBoxTitle = Document.get().createHElement(3);

    @DataField("propertiesContainer")
    protected UListElement propertiesContainer = Document.get().createULElement();

    @Override
    public DivElement getEditingBox() {
        return editingBox;
    }

    @Override
    public HeadingElement getEditingBoxTitle() {
        return editingBoxTitle;
    }

    @Override
    public UListElement getPropertiesContainer() {
        return propertiesContainer;
    }
}
