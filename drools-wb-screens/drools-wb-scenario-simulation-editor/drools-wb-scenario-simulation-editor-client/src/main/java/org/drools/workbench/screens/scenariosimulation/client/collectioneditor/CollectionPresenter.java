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
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox.ItemEditingBoxPresenter;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox.KeyValueEditingBoxPresenter;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

public class CollectionPresenter implements CollectionView.Presenter {

    @Inject
    protected ItemElementPresenter listElementPresenter;

    @Inject
    protected KeyValueElementPresenter mapElementPresenter;

    @Inject
    protected ViewsProvider viewsProvider;

    @Inject
    protected ItemEditingBoxPresenter listEditingBoxPresenter;

    @Inject
    protected KeyValueEditingBoxPresenter mapEditingBoxPresenter;

    /**
     * <code>Map</code> used to pair the <code>UListElement</code> elementsContainer with a specific <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     */
    protected Map<String, UListElement> elementsContainerMap = new HashMap<>();

    /**
     * <code>Map</code> used to pair the <code>LIElement</code> objectSeparator with a specific <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     */
    protected Map<String, LIElement> objectSeparatorMap = new HashMap<>();

    /**
     * <code>Map</code> used to pair the <code>Map</code> with instance' properties classes with a specific <b>key</b> representing the property, i.e Classname#propertyname (e.g Author#books)
     */
    protected Map<String, Map<String, String>> instancePropertiesMap = new HashMap<>();

    /**
     * <code>Map</code> used to pair the <code>Map</code> with the items' properties values with an <b>index</b>
     */
    protected Map<String, Map<String, String>> propertiesValuesMap = new HashMap<>();

    /**
     * <code>Map</code> used to pair the <code>Map</code> with the items' key properties values with an <b>index</b>
     */
    protected Map<String, Map<String, String>> keyPropertiesValuesMap = new HashMap<>();

    @Override
    public void initListStructure(String key, Map<String, String> instancePropertyMap, CollectionView collectionView) {
        commonInit(key, collectionView);
        instancePropertiesMap.put(key, instancePropertyMap);
        listEditingBoxPresenter.setCollectionEditorPresenter(this);
        listElementPresenter.setCollectionEditorPresenter(this);
    }

    @Override
    public void initMapStructure(String key, Map<String, String> keyPropertyMap, Map<String, String> valuePropertyMap, CollectionView collectionView) {
        commonInit(key, collectionView);
        instancePropertiesMap.put(key + "#key", keyPropertyMap);
        instancePropertiesMap.put(key + "#value", valuePropertyMap);
        mapEditingBoxPresenter.setCollectionEditorPresenter(this);
        mapElementPresenter.setCollectionEditorPresenter(this);
    }

    @Override
    public void setValue(String key, String jsonString, CollectionView collectionEditorView) {
        if (jsonString == null || jsonString.isEmpty()) {
            return;
        }
        JSONValue jsonValue = getJSONValue(jsonString);
        if (collectionEditorView.isListWidget()) {
            populateList(key, jsonValue);
        } else {
            populateMap(key, jsonValue);
        }
    }

    @Override
    public void showEditingBox(CollectionView collectionEditorView) {
        String key = collectionEditorView.getEditorTitle().getInnerText();
        if (collectionEditorView.isListWidget()) {
            collectionEditorView.getElementsContainer()
                    .appendChild(listEditingBoxPresenter.getEditingBox(key, instancePropertiesMap.get(key)));
        } else {
            collectionEditorView.getElementsContainer()
                    .appendChild(mapEditingBoxPresenter.getEditingBox(key, instancePropertiesMap.get(key + "#key"), instancePropertiesMap.get(key + "#value")));
        }
    }

    @Override
    public void onToggleRowExpansion(CollectionView collectionEditorView, boolean isShown) {
        collectionEditorView.toggleRowExpansion();
        listElementPresenter.onToggleRowExpansion(isShown);
    }

    @Override
    public void addListItem(String key, Map<String, String> propertiesValues) {
        final UListElement elementsContainer = elementsContainerMap.get(key);
        String itemId = String.valueOf(elementsContainer.getChildCount() - 1);
        propertiesValuesMap.put(itemId, propertiesValues);
        final UListElement itemElement = listElementPresenter.getItemContainer(itemId, propertiesValues);
        final LIElement objectSeparatorLI = objectSeparatorMap.get(key);
        elementsContainer.insertBefore(itemElement, objectSeparatorLI);
    }

    @Override
    public void addMapItem(String key, Map<String, String> keyPropertiesValues, Map<String, String> valuePropertiesValues) {
        final UListElement elementsContainer = elementsContainerMap.get(key);
        String itemId = String.valueOf(elementsContainer.getChildCount() - 1);
        keyPropertiesValuesMap.put(itemId, keyPropertiesValues);
        propertiesValuesMap.put(itemId, valuePropertiesValues);
        final UListElement itemElement = mapElementPresenter.getKeyValueContainer(itemId, keyPropertiesValues, valuePropertiesValues);
        final LIElement objectSeparatorLI = objectSeparatorMap.get(key);
        elementsContainer.insertBefore(itemElement, objectSeparatorLI);
    }

    @Override
    public void updateListItem(String itemId, Map<String, String> propertiesValues) {
        propertiesValuesMap.put(itemId, propertiesValues);
    }

    @Override
    public void updateMapItem(String itemId, Map<String, String> keyPropertiesValues, Map<String, String> valuePropertiesValues) {
        keyPropertiesValuesMap.put(itemId, keyPropertiesValues);
        propertiesValuesMap.put(itemId, valuePropertiesValues);
    }

    @Override
    public void deleteItem(String itemId) {
        propertiesValuesMap.remove(itemId);
    }

    @Override
    public void save(CollectionView collectionEditorView) {
        String updatedValue;
        if (collectionEditorView.isListWidget()) {
            updatedValue = getListValue();
        } else {
            updatedValue = getMapValue();
        }
        collectionEditorView.updateValue(updatedValue);
    }

    protected void commonInit(String key, CollectionView collectionEditorView) {
        String propertyName = key.substring(key.lastIndexOf("#") + 1);
        collectionEditorView.getEditorTitle().setInnerText(key);
        collectionEditorView.getPropertyTitle().setInnerText(propertyName);
        final LIElement objectSeparatorLI = collectionEditorView.getObjectSeparator();
        objectSeparatorMap.put(key, objectSeparatorLI);
        final UListElement elementsContainer = collectionEditorView.getElementsContainer();
        elementsContainerMap.put(key, elementsContainer);
    }

    protected void populateList(String key, JSONValue jsonValue) {
        final JSONArray array = jsonValue.isArray();
        for (int i = 0; i < array.size(); i++) {
            Map<String, String> propertiesValues = new HashMap<>();
            final JSONObject jsonObject = array.get(i).isObject();
            jsonObject.keySet().forEach(propertyName -> propertiesValues.put(propertyName, jsonObject.get(propertyName).isString().stringValue())
            );
            addListItem(key, propertiesValues);
        }
    }

    protected void populateMap(String key, JSONValue jsonValue) {
        final JSONArray array = jsonValue.isArray();
        for (int i = 0; i < array.size(); i++) {
            Map<String, String> keyPropertiesValues = new HashMap<>();
            Map<String, String> valuePropertiesValues = new HashMap<>();
            final JSONObject jsonObject = array.get(i).isObject();
            JSONObject nestedKey = jsonObject.get("key").isObject();
            nestedKey.keySet().forEach(propertyName ->
                                               keyPropertiesValues.put(propertyName, nestedKey.get(propertyName).isString().stringValue()));
            JSONObject nestedValue = jsonObject.get("value").isObject();
            nestedValue.keySet().forEach(propertyName -> valuePropertiesValues.put(propertyName, nestedValue.get(propertyName).isString().stringValue()));
            addMapItem(key, keyPropertiesValues, valuePropertiesValues);
        }
    }

    protected JSONValue getJSONValue(String jsonString) {
        return JSONParser.parseStrict(jsonString);
    }

    protected String getListValue() {
        JSONArray jsonArray = new JSONArray();
        AtomicInteger counter = new AtomicInteger();
        propertiesValuesMap.values().forEach(stringStringMap -> {
            JSONObject nestedObject = new JSONObject();
            stringStringMap.forEach((propertyName, propertyValue) -> nestedObject.put(propertyName, new JSONString(propertyValue)));
            jsonArray.set(counter.getAndIncrement(), nestedObject);
        });
        return jsonArray.toString();
    }

    protected String getMapValue() {
        JSONArray jsonArray = new JSONArray();
        AtomicInteger counter = new AtomicInteger();
        keyPropertiesValuesMap.forEach((itemId, keyPropertiesValues) -> {
            final Map<String, String> valuePropertiesMap = propertiesValuesMap.get(itemId);
            JSONObject nestedKey = new JSONObject();
            keyPropertiesValues.forEach((propertyName, propertyValue) -> nestedKey.put(propertyName, new JSONString(propertyValue)));
            JSONObject nestedValue = new JSONObject();
            valuePropertiesMap.forEach((propertyName, propertyValue) -> nestedValue.put(propertyName, new JSONString(propertyValue)));
            JSONObject nestedObject = new JSONObject();
            nestedObject.put("key", nestedKey);
            nestedObject.put("value", nestedValue);
            jsonArray.set(counter.getAndIncrement(), nestedObject);
        });
        return jsonArray.toString();
    }
}
