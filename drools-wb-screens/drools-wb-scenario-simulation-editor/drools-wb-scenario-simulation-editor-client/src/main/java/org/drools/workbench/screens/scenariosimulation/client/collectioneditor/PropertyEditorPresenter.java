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

import javax.inject.Inject;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ConstantHolder.NODE_HIDDEN;

public class PropertyEditorPresenter implements PropertyEditorView.Presenter {

    @Inject
    protected ViewsProvider viewsProvider;

    protected Map<String, SpanElement> propertySpanElementMap = new HashMap<>();

    protected Map<String, PropertyEditorView> propertyViewMap = new HashMap<>();

    @Override
    public String getPropertyValue(String propertyName) throws Exception {
        if (propertySpanElementMap.containsKey(propertyName)) {
            return propertySpanElementMap.get(propertyName).getInnerText();
        } else {
            throw new Exception(propertyName + " not found");
        }
    }

    @Override
    public LIElement getPropertyFields(String propertyName, String propertyValue, String baseNodeId, int currentId) {
        final PropertyEditorView propertyEditorView = viewsProvider.getPropertyEditorView();

        propertyEditorView.getPropertyName().setInnerText(propertyName);
        final SpanElement propertyTextArea = propertyEditorView.getPropertyValue();
        propertyTextArea.setInnerText(propertyValue);
        propertySpanElementMap.put(propertyName, propertyTextArea);
        final LIElement propertyFields = propertyEditorView.getPropertyFields();
        String nodeId = baseNodeId + "." + currentId;
        propertyViewMap.put(nodeId, propertyEditorView);
        propertyFields.setAttribute("data-nodeid", nodeId);
        return propertyFields;
    }

    @Override
    public void onToggleRowExpansion(String baseNodeId, boolean isShown) {
        propertyViewMap.keySet().stream()
                .filter(key -> key.startsWith(baseNodeId))
                .forEach(key -> onToggleRowExpansion(propertyViewMap.get(key).getPropertyFields(), isShown));
    }

    protected void onToggleRowExpansion(final LIElement liElement, boolean isShown) {
        if (isShown) {
            liElement.addClassName(NODE_HIDDEN);
            liElement.getStyle().setDisplay(Style.Display.NONE);
        } else {
            liElement.removeClassName(NODE_HIDDEN);
            liElement.getStyle().setDisplay(Style.Display.BLOCK);
        }
    }
}
