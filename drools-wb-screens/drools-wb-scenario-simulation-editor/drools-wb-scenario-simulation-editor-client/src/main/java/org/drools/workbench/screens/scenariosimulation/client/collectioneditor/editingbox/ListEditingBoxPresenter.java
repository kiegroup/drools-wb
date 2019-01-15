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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.InputElement;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

public class ListEditingBoxPresenter implements ListEditingBox.Presenter {

    @Inject
    protected ViewsProvider viewsProvider;

    @Inject
    protected PropertyEditingElementPresenter propertyEditingElementPresenter;

    protected Map<String, InputElement> propertyInputElementMap = new HashMap<>();

    @Override
    public DivElement getEditingBox(String propertyName, Map<String, String> instancePropertyMap) {
        final ListEditingBox listEditingBox = viewsProvider.getListEditingBox();
        listEditingBox.getEditingBoxTitle().setInnerText("Edit " + propertyName);
        AtomicInteger counter = new AtomicInteger(0);
        instancePropertyMap.forEach((key, value) -> {
            String nodeId = "0." + counter.getAndIncrement();
            listEditingBox.getPropertiesContainer().appendChild(propertyEditingElementPresenter.getPropertyContainer(key, nodeId));
        });
        return listEditingBox.getEditingBox();
    }
}
