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

public interface ListEditorElementView extends HasPresenter<ListEditorElementView.Presenter> {


    interface Presenter {

        /**
         *
         * @param propertiesMap the properties to be put inside the <code>LIElement</code>
         * representing a single item of the list
         * @param nodeId
         * @return the <code>List&lt;LIElement&gt;</code> representing all the items of the list
         */
        List<LIElement> getProperties(Map<String, String> propertiesMap, String nodeId);

        void onToggleRowExpansion(boolean isShown);

        void onToggleRowExpansion(ListEditorElementView listEditorElementView, boolean shown);
    }

    /**
     *
     * @return the <code>LIElement</code> separating each item
     */
    LIElement getItemSeparator();

    /**
     *
     * @return the <code>SpanElement</code> with the angle arrow
     */
    SpanElement getFaAngleRight();

}
