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

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;

/**
 * Interface defining the contract for actual implementations
 */
public interface CollectionView {

    interface Presenter {

        /**
         * Actual implementations should invoke this method first to retrieve information about the collection
         * generic type and the structure of such type
         * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
         * @param instancePropertyMap
         * @param collectionEditorView
         */
        void initListStructure(String key, Map<String, String> instancePropertyMap, CollectionView collectionEditorView);

        /**
         * Actual implementations should invoke this method first to retrieve information about the collection
         * generic type and the structure of such type
         * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
         * @param keyPropertyMap
         * @param valuePropertyMap
         * @param collectionEditorView
         */
        void initMapStructure(String key, Map<String, String> keyPropertyMap, Map<String, String> valuePropertyMap, CollectionView collectionEditorView);

        /**
         * Actual implementations are meant to transform that json representation to a <code>com.google.gwt.json.client.JSONValue</code> and use that to populate the
         * given <code>CollectionEditorView</code>
         * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
         * @param jsonString
         * @param collectionEditorView
         */
        void setValue(String key, String jsonString, CollectionView collectionEditorView);

        /**
         * Show the editing box in the given <code>CollectionEditorView</code>
         * @param collectionEditorView
         */
        void showEditingBox(CollectionView collectionEditorView);

        /**
         * Toggle the expansion of the collection.
         * @param collectionEditorView
         * @param isShown the <b>current</b> expansion status of the collection
         */
        void onToggleRowExpansion(CollectionView collectionEditorView, boolean isShown);

        /**
         * Creates a new single <b>item</b> element with values taken from given <code>Map</code>
         * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
         * @param propertiesValues
         */
        void addListItem(String key, Map<String, String> propertiesValues);

        /**
         * Creates a new <b>key/value</b> <b>item</b> element with values taken from given <code>Map</code>
         * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
         * @param keyPropertiesValues
         * @param valuePropertiesValues
         */
        void addMapItem(String key, Map<String, String> keyPropertiesValues, Map<String, String> valuePropertiesValues);

        /**
         * Update the values in the local <code>propertiesValuesMap</code> for the given <b>key</b>
         * @param itemId
         * @param propertiesValues
         */
        void updateListItem(String itemId, Map<String, String> propertiesValues);

        /**
         * Update the values in the locals <code>keyPropertiesValuesMap</code> and <code>propertiesValuesMap</code> for the given <b>key</b>
         * @param itemId
         * @param keyPropertiesValues
         * @param valuePropertiesValues
         */
        void updateMapItem(String itemId, Map<String, String> keyPropertiesValues, Map<String, String> valuePropertiesValues);

        /**
         * Delete from local <code>Map</code>s and final <b>value</b> data belonging to the <b>item</b> with the given <b>itemId</b>
         * @param itemId
         */
        void deleteItem(String itemId);

        /**
         * Actual implementations are meant to retrieve the json representation of the content of the
         * given <code>CollectionEditorView</code> Save the <b>json</b> representation of the values of the given <code>CollectionEditorView</code>
         * @param collectionEditorView
         */
        void save(CollectionView collectionEditorView);
    }

    /**
     * Actual implementations are meant to call the <code>Presenter</code> to be populated by this json representation
     * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
     * @param jsonString
     */
    void setValue(String key, String jsonString);

    /**
     * Actual implementations are meant to call the <code>Presenter</code> to retrieve the json representation of their contents
     * @return the json representation of the current content
     */
    String getValue();

    /**
     * @param listWidget set to <code>true</code> if the current instance will manage a <code>List</code>,
     * <code>false</code> for a <code>Map</code>.
     */
    void setListWidget(boolean listWidget);

    /**
     * Returns <code>true</code> if the current instance will manage a <code>List</code>,
     * <code>false</code> for a <code>Map</code>.
     * @return
     */
    boolean isListWidget();

    UListElement getElementsContainer();

    LIElement getObjectSeparator();

    HeadingElement getEditorTitle();

    SpanElement getPropertyTitle();

    void toggleRowExpansion();

    /**
     * Updates the <b>json</b> representation of the values shown by this editor
     * @param toString
     */
    void updateValue(String toString);

    /**
     * Set the <b>name</b> of the property and the <code>Map</code> to be used to create the skeleton of the current <code>CollectionViewImpl</code> editor
     * showing a <b>List</b> of elements
     * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
     * @param instancePropertyMap
     */

    void initListStructure(String key, Map<String, String> instancePropertyMap);

    /**
     * Set the <b>name</b> of the property and the <code>Map</code>s to be used to create the skeleton of the current <code>CollectionViewImpl</code> editor
     * showing a <b>Map</b> of elements
     * @param key The key representing the property, i.e Classname#propertyname (e.g Author#books)
     * @param keyPropertyMap
     * @param valuePropertyMap
     */
    void initMapStructure(String key, Map<String, String> keyPropertyMap, Map<String, String> valuePropertyMap);
}
