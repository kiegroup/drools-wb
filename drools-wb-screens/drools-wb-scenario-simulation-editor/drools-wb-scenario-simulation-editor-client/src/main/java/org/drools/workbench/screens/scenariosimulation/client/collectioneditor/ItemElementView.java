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

import java.util.List;
import java.util.Map;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import org.uberfire.client.mvp.HasPresenter;

public interface ItemElementView extends HasPresenter<ItemElementView.Presenter> {


    interface Presenter {

        void setCollectionEditorPresenter(CollectionView.Presenter collectionEditorPresenter);

        /**
         *
         * @param itemId the id of the current item
         * @param propertiesMap the properties to be put inside the <code>UListElement</code>
         * representing a single item of the list
         *
         * @return the <code>LIElement</code> representing all the items of the list
         */
        LIElement getItemContainer(String itemId, Map<String, String> propertiesMap);

        void onToggleRowExpansion(boolean isShown);

        void onToggleRowExpansion(ItemElementView itemElementView, boolean shown);

        /**
         * Start editing properties of the given <code>itemElementView</code>
         * @param itemElementView
         */
        void onEditItem(ItemElementView itemElementView);

        /**
         * Update the values of the properties shown in the given <code>itemElementView</code>
         * and stop the editing mode
         * @param itemElementView
         */
        void updateItem(ItemElementView itemElementView);

        /**
         * Stop editing properties of the given <code>itemElementView</code>
         * <b>without</b> updating the properties
         *
         * @param itemElementView
         */
        void onStopEditingItem(ItemElementView itemElementView);

        /**
         * Delete the item and its properties shown on the given <code>itemElementView</code>
         * @param itemElementView
         */
        void onDeleteItem(ItemElementView itemElementView);

        /**
         * Retrieves a <code>List</code> with the <code>Map</code>s of all the items' properties
         * @return
         */
        List<Map<String, String>> getItemsProperties();
    }

    /**
     * Set the <b>id</b> of the item shown by the current <code><ListEditorElementView/code>
     * @param itemId
     */
    void setItemId(String itemId);

    /**
     *
     * @return the <b>id</b> of the item shown by the current <code><ListEditorElementView/code>
     */
    String getItemId();

    /**
     *
     * @return the <code>LIElement</code> containing all the item properties
     */
    LIElement getItemContainer();

    /**
     *
     * @return the <code>LIElement</code> separating each item
     */
    LIElement getItemSeparator();

    /**
     *
     * @return the <code>LIElement</code> with the item' save/cancel buttons
     */
    LIElement getSaveChange();

    /**
     *
     * @return the <code>SpanElement</code> with the angle arrow
     */
    SpanElement getFaAngleRight();

}
