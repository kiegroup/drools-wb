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

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import org.uberfire.client.mvp.HasPresenter;

public interface KeyValueElementView extends HasPresenter<KeyValueElementView.Presenter> {

    interface Presenter {

        void setCollectionEditorPresenter(CollectionView.Presenter collectionEditorPresenter);

        /**
         * @param itemId the id of the current item
         * @param keyPropertiesValues the properties to be put inside the <code>UListElement</code>
         * representing the key of a single item of the map
         * @param keyPropertiesValues the properties to be put inside the <code>UListElement</code>
         * representing the value of a single item of the map
         * @return the <code>UListElement</code> representing all the items of the map
         */
        UListElement getKeyValueContainer(String itemId, Map<String, String> keyPropertiesValues, Map<String, String> valuePropertiesValues);

        void onToggleRowExpansion(boolean isShown);

        void onToggleRowExpansion(KeyValueElementView itemElementView, boolean shown);

        /**
         * Start editing properties of the given <code>itemElementView</code>
         * @param itemElementView
         */
        void onEditItem(KeyValueElementView itemElementView);

        /**
         * Update the values of the properties shown in the given <code>itemElementView</code>
         * and stop the editing mode
         * @param itemElementView
         */
        void updateItem(KeyValueElementView itemElementView);

        /**
         * Stop editing properties of the given <code>itemElementView</code>
         * <b>without</b> updating the properties
         * @param itemElementView
         */
        void onStopEditingItem(KeyValueElementView itemElementView);

        /**
         * Delete the item and its properties shown on the given <code>itemElementView</code>
         * @param itemElementView
         */
        void onDeleteItem(KeyValueElementView itemElementView);
    }

    /**
     * Set the <b>id</b> of the item shown by the current <code><ListEditorElementView/code>
     * @param itemId
     */
    void setItemId(String itemId);

    /**
     * @return the <b>id</b> of the item shown by the current <code><ListEditorElementView/code>
     */
    String getItemId();

    /**
     * @return the <code>UListElement</code> containing all the item properties
     */
    UListElement getItemContainer();

    /**
     * @return the <code>LIElement</code> containing all the key/value properties
     */
    LIElement getKeyValueContainer();

    /**
     * @return the <code>UListElement</code> containing the <b>key</b> properties
     */
    UListElement getKeyContainer();

    /**
     * @return the <code>UListElement</code> containing the <b>value</b> properties
     */
    UListElement getValueContainer();

    /**
     * @return the <code>LIElement</code> separating each item
     */
    LIElement getItemSeparator();

    /**
     * @return the <code>LIElement</code> with the item' save/cancel buttons
     */
    LIElement getSaveChange();

    /**
     * @return the <code>SpanElement</code> with the angle arrow
     */
    SpanElement getFaAngleRight();
}
