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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox.ListEditingBoxPresenter;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

public class CollectionEditorPresenter implements CollectionEditorView.Presenter {

    @Inject
    protected ListEditorElementPresenter listEditorElementPresenter;

    @Inject
    protected ViewsProvider viewsProvider;

    @Inject
    protected ListEditingBoxPresenter listEditingBoxPresenter;

    /**
     * <code>Map</code> used to pair the <code>UListElement</code> elementsContainer with a specific <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     */
    protected Map<String, UListElement> elementsContainerMap = new HashMap<>();

    /**
     * <code>Map</code> used to pair the <code>LIElement</code> objectSeparator with a specific <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     */
    protected Map<String, LIElement> objectSeparatorMap = new HashMap<>();

    /**
     * <code>Map</code> used to pair the <code>Map</code> with instance' properties with a specific <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     */
    protected Map<String, Map<String, String>> instancePropertiesMap = new HashMap<>();

    @Override
    public void initStructure(String key, Map<String, String> instancePropertyMap, CollectionEditorView collectionEditorView) {
        String propertyName = key.substring(key.lastIndexOf("#") + 1);
        collectionEditorView.getEditorTitle().setInnerText(key);
        collectionEditorView.getPropertyTitle().setInnerText(propertyName);
        final ObjectSeparator objectSeparator = viewsProvider.getObjectSeparator();
        objectSeparator.init(this, key);
        final LIElement objectSeparatorLI = objectSeparator.getObjectSeparator();
        objectSeparatorMap.put(key, objectSeparatorLI);
        final UListElement elementsContainer = collectionEditorView.getElementsContainer();
        elementsContainer.appendChild(objectSeparatorLI);
        elementsContainerMap.put(key, elementsContainer);
        instancePropertiesMap.put(key, instancePropertyMap);
        listEditingBoxPresenter.setCollectionEditorPresenter(this);
    }

    @Override
    public void setValue(String jsonString, CollectionEditorView collectionEditorView) {
        GWT.log(jsonString);
        JSONValue jsonValue = getJSONValue(jsonString);
        GWT.log(jsonValue.toString());
        if (collectionEditorView.isListWidget()) {
            populateList(jsonValue, collectionEditorView);
        } else {
            populateMap(jsonValue, collectionEditorView);
        }
    }

    @Override
    public String getValue(CollectionEditorView collectionEditorView) {
        return collectionEditorView.isListWidget() ? getListValue(collectionEditorView) : getMapValue(collectionEditorView);
    }

    @Override
    public void showEditingBox(String key) {
        elementsContainerMap.get(key)
                .appendChild(listEditingBoxPresenter.getEditingBox(key, instancePropertiesMap.get(key)));
    }

    @Override
    public void onToggleRowExpansion(CollectionEditorView collectionEditorView, boolean isShown) {
        collectionEditorView.toggleRowExpansion();
        listEditorElementPresenter.onToggleRowExpansion(isShown);
    }

    @Override
    public void addItem(String key, Map<String, String> propertiesValues) {
        final UListElement elementsContainer = elementsContainerMap.get(key);
        int currentItems = elementsContainer.getChildCount() -1;
        final List<LIElement> properties = listEditorElementPresenter.getProperties(propertiesValues, "0.0." + currentItems);
        final LIElement objectSeparatorLI = objectSeparatorMap.get(key);
        properties.forEach(liElementProperty -> elementsContainer.insertBefore(liElementProperty, objectSeparatorLI));
    }

    protected void populateList(JSONValue jsonValue, CollectionEditorView collectionEditorView) {
    }

    protected void populateMap(JSONValue jsonValue, CollectionEditorView collectionEditorView) {

    }

    protected String getListValue(CollectionEditorView collectionEditorView) {
        return null;
    }

    protected String getMapValue(CollectionEditorView collectionEditorView) {
        return null;
    }

    protected JSONValue getJSONValue(String jsonString) {
        return JSONParser.parseStrict(jsonString);
    }
}
