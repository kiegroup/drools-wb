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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.UListElement;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

public class KeyValueElementPresenter implements KeyValueElementView.Presenter {

    protected CollectionView.Presenter collectionEditorPresenter;

    @Inject
    protected PropertyView.Presenter propertyEditorPresenter;

    @Inject
    protected ViewsProvider viewsProvider;

    /**
     * <code>List</code> of currently present <code>KeyValueElementView</code>s
     */
    protected List<KeyValueElementView> keyValueElementViewList = new ArrayList<>();

    @Override
    public void setCollectionEditorPresenter(CollectionView.Presenter collectionEditorPresenter) {
        this.collectionEditorPresenter = collectionEditorPresenter;
    }

    @Override
    public UListElement getKeyValueContainer(String itemId, Map<String, String> keyPropertiesValues, Map<String, String> valuePropertiesValues) {
        final KeyValueElementView keyValueElementView = viewsProvider.getKeyValueElementView();
        keyValueElementView.init(this);
        keyValueElementView.setItemId(itemId);
        final UListElement keyContainer = keyValueElementView.getKeyContainer();
        String keyId = itemId+ "#key";
        String valueId = itemId + "#value";
        keyPropertiesValues.forEach((propertyName, propertyValue) ->
                                            keyContainer.appendChild(propertyEditorPresenter.getPropertyFields(keyId, propertyName, propertyValue)));

        final UListElement valueContainer = keyValueElementView.getValueContainer();
        valuePropertiesValues.forEach((propertyName, propertyValue) ->
                                              valueContainer.appendChild(propertyEditorPresenter.getPropertyFields(valueId, propertyName, propertyValue)));
        keyValueElementViewList.add(keyValueElementView);
        return keyValueElementView.getItemContainer();
    }

    @Override
    public void onToggleRowExpansion(boolean isShown) {
        keyValueElementViewList.forEach(keyValueElementView -> onToggleRowExpansion(keyValueElementView, isShown));
    }

    @Override
    public void onToggleRowExpansion(KeyValueElementView keyValueElementView, boolean isShown) {
        CollectionEditorUtils.toggleRowExpansion(keyValueElementView.getFaAngleRight(), !isShown);
        propertyEditorPresenter.onToggleRowExpansion(keyValueElementView.getItemId(), isShown);
    }

    @Override
    public void onEditItem(KeyValueElementView keyValueElementView) {
        String itemId = keyValueElementView.getItemId();
        String keyId = itemId+ "#key";
        String valueId = itemId + "#value";
        propertyEditorPresenter.editProperties(keyId);
        propertyEditorPresenter.editProperties(valueId);
        keyValueElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    @Override
    public void updateItem(KeyValueElementView keyValueElementView) {
        String itemId = keyValueElementView.getItemId();
        String keyId = itemId+ "#key";
        String valueId = itemId + "#value";
        final Map<String, String> keyPropertiesValues = propertyEditorPresenter.updateProperties(keyId);
        final Map<String, String> valuePropertiesValues = propertyEditorPresenter.updateProperties(valueId);
        keyValueElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.HIDDEN);
        collectionEditorPresenter.updateMapItem(itemId, keyPropertiesValues, valuePropertiesValues);
    }

    @Override
    public void onStopEditingItem(KeyValueElementView keyValueElementView) {
        propertyEditorPresenter.stopEditProperties(keyValueElementView.getItemId());
        keyValueElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }

    @Override
    public void onDeleteItem(KeyValueElementView keyValueElementView) {
        propertyEditorPresenter.deleteProperties(keyValueElementView.getItemId());
        keyValueElementView.getItemContainer().removeFromParent();
        keyValueElementViewList.remove(keyValueElementView);
        collectionEditorPresenter.deleteItem(keyValueElementView.getItemId());
    }
}
