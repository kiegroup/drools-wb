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

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;

public class CollectionEditorPresenter implements CollectionEditorView.Presenter {

    @Inject
    protected ListEditorElementPresenter listEditorElementPresenter;

    @Override
    public void initStructure(Map<String, String> instancePropertyMap, CollectionEditorView collectionEditorView) {
        Map<String, String> propertiesMap = new HashMap<>();
        instancePropertyMap.forEach((key, value) -> propertiesMap.put(key + "(" + value +")", "(insert " + key + ")"));
        final LIElement propertiesContainer = listEditorElementPresenter.getPropertiesContainer(propertiesMap);
        collectionEditorView.getElementsContainer().appendChild(propertiesContainer);
    }

    @Override
    public void setValue(String jsonString, CollectionEditorView collectionEditorView) {
        GWT.log(jsonString);
        JSONValue jsonValue = getJSONValue(jsonString);
        GWT.log(jsonValue.toString());
        if (collectionEditorView.isListWidget()) {
            populateList(jsonValue, collectionEditorView);
        } else {
            populateMap(jsonValue, collectionEditorView);
        }
    }

    @Override
    public String getValue(CollectionEditorView collectionEditorView) {
        return collectionEditorView.isListWidget() ? getListValue(collectionEditorView) : getMapValue(collectionEditorView);
    }

    protected void populateList(JSONValue jsonValue, CollectionEditorView collectionEditorView) {
    }

    protected void populateMap(JSONValue jsonValue, CollectionEditorView collectionEditorView) {

    }

    protected String getListValue(CollectionEditorView collectionEditorView) {
        return null;
    }

    protected String getMapValue(CollectionEditorView collectionEditorView) {
        return null;
    }

    protected JSONValue getJSONValue(String jsonString) {
        return JSONParser.parseStrict(jsonString);
    }
}
