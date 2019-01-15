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

import javax.inject.Inject;

import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LIElement;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

public class PropertyEditingElementPresenter implements PropertyEditingElement.Presenter {

    @Inject
    protected ViewsProvider viewsProvider;

    protected Map<String, InputElement> propertyInputElementMap = new HashMap<>();

    @Override
    public LIElement getPropertyContainer(String propertyName, String nodeId) {
        final PropertyEditingElement propertyEditingElement = viewsProvider.getPropertyEditingElement();
        propertyEditingElement.getPropertyName().setInnerText(propertyName);
        propertyEditingElement.getPropertyValue().setAttribute("placeholder", "#" + propertyName);
        propertyEditingElement.getPropertyContainer().setAttribute("data-nodeid", nodeId);
        propertyInputElementMap.put(propertyName, propertyEditingElement.getPropertyValue());
        return propertyEditingElement.getPropertyContainer();
    }
}
