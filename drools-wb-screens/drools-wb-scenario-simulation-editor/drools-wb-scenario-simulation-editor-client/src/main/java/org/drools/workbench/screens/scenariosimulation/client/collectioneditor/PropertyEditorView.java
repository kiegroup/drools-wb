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

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;

public interface PropertyEditorView {

    interface Presenter {

        /**
         * Get the text shown in the property value
         * @param propertyName the property fro which we are retrieving the value
         * @return
         * @throws Exception if the given property value is not found
         */
        String getPropertyValue(String propertyName) throws Exception;

        /**
         * @param propertyName
         * @param propertyValue
         * @param baseNodeId the nodeId for the given item
         * @param currentId the specific id-part for the current property
         * @return the <code>LIElement</code> containing the property' <b>fields</b>
         */
        LIElement getPropertyFields(String propertyName, String propertyValue, String baseNodeId, int currentId);

        void onToggleRowExpansion(String baseNodeId, boolean isShown);
    }

    /**
     * @return the <code>LIElement</code> containing the property' <b>fields</b>
     */
    LIElement getPropertyFields();

    /**
     * @return the <code>SpanElement</code> showing the property' <b>name</b>
     */
    SpanElement getPropertyName();

    /**
     * @return the <code>SpanElement</code> showing the property' <b>value</b>
     */
    SpanElement getPropertyValue();
}
