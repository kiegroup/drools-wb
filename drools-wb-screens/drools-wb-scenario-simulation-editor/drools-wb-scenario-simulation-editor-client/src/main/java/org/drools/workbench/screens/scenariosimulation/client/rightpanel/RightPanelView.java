/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import java.util.SortedMap;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.IsWidget;
import org.drools.workbench.screens.scenariosimulation.client.models.FactModelTree;
import org.uberfire.client.mvp.HasPresenter;

public interface RightPanelView
        extends IsWidget,
                HasPresenter<RightPanelView.Presenter> {

    void clearInputSearch();

    void clearNameField();

    void hideClearButton();

    void showClearButton();

    DivElement getDataObjectListContainer();

    DivElement getInstanceListContainer();

    Presenter getPresenter();

    /**
     * By default the <b>Editor Tab</b> is disabled (no user interaction allowed).
     * It is enabled only by click on grid' header
     */
    void enableEditorTab();

    /**
     * By default the <b>Editor Tab</b> must be disabled (no user interaction allowed).
     * It is enabled only by click on grid' header
     */
    void disableEditorTab();

    /**
     * By default the <b>Add</b> button is disabled (no user interaction allowed).
     * It is enabled only by selection of a property/data object
     */
    void enableAddButton();

    interface Presenter {

        void onClearSearch();

        void onClearNameField();

        void onClearStatus();

        void onShowClearButton();

        void onSearchedEvent(String search);

        void clearDataObjectList();

        void clearInstanceList();

        void addDataObjectListGroupItemView(String factName, FactModelTree factModelTree);

        void addInstanceListGroupItemView(String factName, FactModelTree factModelTree);

        void setDataObjectFieldsMap(SortedMap<String, FactModelTree> dataObjectFieldsMap);

        void setInstanceFieldsMap(SortedMap<String, FactModelTree> factTypeFieldsMap);

        void setEventBus(EventBus eventBus);

        FactModelTree getFactModelTreeFromFactTypeMap(String factName);

        FactModelTree getFactModelTreeFromInstanceMap(String factName);

        /**
         * By default the <b>Editor Tab</b> is disabled (no user interaction allowed).
         * Use this when click on grid' <i>instance</i> header.
         * Call this method to show all the first-level data models <b>enabled</b> (i.e. <b>double-clickable</b> to map to an <i>instance</i> header/column)
         * and their properties <b>disabled</b> (i.e. <b>not double-clickable</b>)
         */
        void onEnableEditorTab();

        /**
         * By default the <b>Editor Tab</b> is disabled (no user interaction allowed).
         * Use this when click on grid' <i>property</i> header.
         * Call this method to show only the data model with the given name, <b>disabled</b> (i.e. <b>not double-clickable</b>)
         * and their properties <b>enabled</b> (i.e. <b>double-clickable</b> to map to a <i>property</i> header/column below the belonging data model instance one)
         *
         * @param factName
         */
        void onEnableEditorTab(String factName);

        /**
         * By default the <b>Editor Tab</b> must be disabled (no user interaction allowed).
         * It is enabled only by click on grid' header
         */
        void onDisableEditorTab();

        /**
         * Method to fire a <code>SetPropertyHeaderCommand</code>  or <code>SetPropertyHeaderCommand</code>,
         * depending on the element currently selected
         */
        void onModifyColumn();

        /**
         * Method to set the "selected" information - use this to set the <i>instance</i> level header
         * @param selected
         */
        void setSelectedElement(ListGroupItemView selected);

        /**
         * Method to set the "selected" information - use this to set the <i>property</i> level header
         * @param selected
         */
        void setSelectedElement(FieldItemView selected);
    }
}
