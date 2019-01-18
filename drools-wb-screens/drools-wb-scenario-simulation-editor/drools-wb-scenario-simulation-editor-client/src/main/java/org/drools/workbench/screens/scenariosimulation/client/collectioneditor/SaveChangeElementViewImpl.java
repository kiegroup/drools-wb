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
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

/**
 * This class is used as <code>ListElement</code> <b>editor</b>
 *
 * It represent a single item of the List
 */
@Templated
public class SaveChangeElementViewImpl implements SaveChangeElementView {

    protected Presenter presenter;

    @DataField("saveChange")
    protected LIElement saveChange = Document.get().createLIElement();

    @DataField("saveChangeButton")
    protected ButtonElement saveChangeButton = Document.get().createButtonElement();

    @DataField("cancelChangeButton")
    protected ButtonElement cancelChangeButton = Document.get().createButtonElement();

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }

    public LIElement getSaveChange() {
        return saveChange;
    }
}
