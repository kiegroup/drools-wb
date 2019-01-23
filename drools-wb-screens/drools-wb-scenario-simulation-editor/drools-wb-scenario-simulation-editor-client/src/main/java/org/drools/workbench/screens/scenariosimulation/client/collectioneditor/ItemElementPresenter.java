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
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Style;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

public class ItemElementPresenter implements ItemElementView.Presenter {

    protected CollectionView.Presenter collectionEditorPresenter;

    @Inject
    protected PropertyView.Presenter propertyPresenter;

    @Inject
    protected ViewsProvider viewsProvider;

    /**
     * <code>List</code> of currently present <code>ListEditorElementView</code>s
     */
    protected List<ItemElementView> itemElementViewList = new ArrayList<>();

    @Override
    public void setCollectionEditorPresenter(CollectionView.Presenter collectionEditorPresenter) {
        this.collectionEditorPresenter = collectionEditorPresenter;
    }

    @Override
    public LIElement getItemContainer(String itemId, Map<String, String> propertiesMap) {
        final ItemElementView itemElementView = viewsProvider.getListEditorElementView();
        itemElementView.init(this);
        itemElementView.setItemId(itemId);
        final LIElement toReturn = itemElementView.getItemContainer();
        final LIElement saveChange = itemElementView.getSaveChange();
        propertiesMap.forEach((propertyName, propertyValue) ->
                                      toReturn.insertBefore(propertyPresenter.getPropertyFields(itemId, propertyName, propertyValue), saveChange));
        itemElementViewList.add(itemElementView);
        return toReturn;
    }

    @Override
    public void onToggleRowExpansion(boolean isShown) {
        itemElementViewList.forEach(itemElementView -> onToggleRowExpansion(itemElementView, isShown));
    }

    @Override
    public void onToggleRowExpansion(ItemElementView itemElementView, boolean isShown) {
        CollectionEditorUtils.toggleRowExpansion(itemElementView.getFaAngleRight(), !isShown);
        propertyPresenter.onToggleRowExpansion(itemElementView.getItemId(), isShown);
    }

    @Override
    public void onEditItem(ItemElementView itemElementView) {
        propertyPresenter.editProperties(itemElementView.getItemId());
        itemElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    @Override
    public void updateItem(ItemElementView itemElementView) {
        propertyPresenter.updateProperties(itemElementView.getItemId());
        itemElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }

    @Override
    public void onStopEditingItem(ItemElementView itemElementView) {
        propertyPresenter.stopEditProperties(itemElementView.getItemId());
        itemElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }

    @Override
    public void onDeleteItem(ItemElementView itemElementView) {
        propertyPresenter.deleteProperties(itemElementView.getItemId());
        itemElementView.getItemContainer().removeFromParent();
        itemElementViewList.remove(itemElementView);
    }

    @Override
    public List<Map<String, String>> getItemsProperties() {
        return itemElementViewList.stream()
                .map(itemElementView -> propertyPresenter.getProperties(itemElementView.getItemId()))
                .collect(Collectors.toList());

    }
}
