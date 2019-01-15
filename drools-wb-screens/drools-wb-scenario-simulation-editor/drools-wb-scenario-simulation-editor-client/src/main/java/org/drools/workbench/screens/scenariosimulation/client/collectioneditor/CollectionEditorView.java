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
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;

/**
 * Interface defining the contract for actual implementations
 */
public interface CollectionEditorView {

    interface Presenter {

        /**
         * Actual implementations should invoke this method first to retrieve information about the collection
         * generic type and the structure of such type
         *
         * @param className
         * @param propertyName
         * @param instancePropertyMap
         * @param collectionEditorView
         */
        void initStructure(String className, String propertyName, Map<String, String> instancePropertyMap, CollectionEditorView collectionEditorView);

        /**
         * Actual implementations are meant to transform that json representation to a <code>com.google.gwt.json.client.JSONValue</code> and use that to populate the
         * given <code>CollectionEditorView</code>
         * @param jsonString
         * @param collectionEditorView
         */
        void setValue(String jsonString, CollectionEditorView collectionEditorView);

        /**
         * Actual implementations are meant to retrieve the json representation of the content of the
         * given <code>CollectionEditorView</code>
         * @param collectionEditorView
         *
         * @return the json representation of the <code>CollectionEditorView</code>' content
         */
        String getValue(CollectionEditorView collectionEditorView);

        /**
         * Show the editing box
         *
         * @param key
         */
        void showEditingBox(String key);

        /**
         * Toggle the expansion of the collection.
         *
         * @param collectionEditorView
         * @param isShown the <b>current</b> expansion status of the collection
         */
        void onToggleRowExpansion(CollectionEditorView collectionEditorView, boolean isShown);
    }

    /**
     * Actual implementations are meant to call the <code>Presenter</code> to be populated by this json representation
     * @param jsonString
     */
    void setValue(String jsonString);

    /**
     * Actual implementations are meant to call the <code>Presenter</code> to retrieve the json representation of their contents
     *
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

    HeadingElement getEditorTitle();

    SpanElement getPropertyTitle();

    void toggleRowExpansion();
}
