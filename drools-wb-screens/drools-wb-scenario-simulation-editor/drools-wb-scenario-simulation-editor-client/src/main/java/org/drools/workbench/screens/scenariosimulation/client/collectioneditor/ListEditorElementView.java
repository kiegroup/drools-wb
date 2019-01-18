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

public interface ListEditorElementView extends HasPresenter<ListEditorElementView.Presenter> {


    interface Presenter {

        void setCollectionEditorPresenter(CollectionEditorView.Presenter collectionEditorPresenter);

        /**
         *
         * @param itemId the id of the current item
         * @param propertiesMap the properties to be put inside the <code>UListElement</code>
         * representing a single item of the list
         *
         * @return the <code>UListElement</code> representing all the items of the list
         */
        UListElement getItemContainer(int itemId, Map<String, String> propertiesMap);

        void onToggleRowExpansion(boolean isShown);

        void onToggleRowExpansion(ListEditorElementView listEditorElementView, boolean shown);

        /**
         * Start editing properties of the given <code>listEditorElementView</code>
         * @param listEditorElementView
         */
        void onEditItem(ListEditorElementViewImpl listEditorElementView);

        /**
         * Update the values of the properties shown in the given <code>listEditorElementView</code>
         * and stop the editing mode
         * @param listEditorElementView
         */
        void updateItem(ListEditorElementViewImpl listEditorElementView);

        /**
         * Stop editing properties of the given <code>listEditorElementView</code>
         * <b>without</b> updating the properties
         *
         * @param listEditorElementView
         */
        void onStopEditingItem(ListEditorElementViewImpl listEditorElementView);

        /**
         * Delete the item and its properties shown on the given <code>listEditorElementView</code>
         * @param listEditorElementView
         */
        void onDeleteItem(ListEditorElementViewImpl listEditorElementView);
    }

    /**
     * Set the <b>id</b> of the item shown by the current <code><ListEditorElementView/code>
     * @param itemId
     */
    void setItemId(int itemId);

    /**
     *
     * @return the <b>id</b> of the item shown by the current <code><ListEditorElementView/code>
     */
    int getItemId();

    /**
     *
     * @return the <code>UListElement</code> containing all the item properties
     */
    UListElement getItemContainer();

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
