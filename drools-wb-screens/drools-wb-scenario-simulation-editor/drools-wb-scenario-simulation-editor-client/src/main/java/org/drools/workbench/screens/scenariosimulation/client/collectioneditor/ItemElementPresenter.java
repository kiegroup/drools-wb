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
import java.util.stream.Collectors;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.UListElement;

public class ItemElementPresenter extends ElementPresenter<ItemElementView> implements ItemElementView.Presenter {

    @Override
    public LIElement getItemContainer(String itemId, Map<String, String> simplePropertiesMap, Map<String, Map<String, String>> expandablePropertiesValues) {
        final ItemElementView itemElementView = viewsProvider.getListEditorElementView();
        itemElementView.init(this);
        itemElementView.setItemId(itemId);
        final UListElement innerItemContainer = itemElementView.getInnerItemContainer();
        final LIElement saveChange = itemElementView.getSaveChange();
        simplePropertiesMap.forEach((propertyName, propertyValue) ->
                                      innerItemContainer.insertBefore(propertyPresenter.getPropertyFields(itemId, propertyName, propertyValue), saveChange));
        expandablePropertiesValues.forEach((nestedPropertyName, nestedPropertiesValues) -> {
            addNestedItemElementView(itemElementView, nestedPropertiesValues, itemId);
        });
        elementViewList.add(itemElementView);
        return itemElementView.getItemContainer();
    }

    @Override
    public void onToggleRowExpansion(ItemElementView itemElementView, boolean isShown) {
        CollectionEditorUtils.toggleRowExpansion(itemElementView.getFaAngleRight(), !isShown);
        propertyPresenter.onToggleRowExpansion(itemElementView.getItemId(), isShown);
        updateCommonToggleStatus(isShown);
    }

    @Override
    public void onEditItem(ItemElementView itemElementView) {
        if (!itemElementView.isShown()) {
            onToggleRowExpansion(itemElementView, false);
        }
        propertyPresenter.editProperties(itemElementView.getItemId());
        itemElementView.getSaveChange().getStyle().setDisplay(Style.Display.INLINE);
        collectionEditorPresenter.toggleEditingStatus(true);
    }

    @Override
    public void onStopEditingItem(ItemElementView itemElementView) {
        propertyPresenter.stopEditProperties(itemElementView.getItemId());
        itemElementView.getSaveChange().getStyle().setDisplay(Style.Display.NONE);
        collectionEditorPresenter.toggleEditingStatus(false);
    }

    @Override
    public void updateItem(ItemElementView itemElementView) {
        propertyPresenter.updateProperties(itemElementView.getItemId());
        itemElementView.getSaveChange().getStyle().setDisplay(Style.Display.NONE);
        collectionEditorPresenter.toggleEditingStatus(false);
    }

    @Override
    public void onDeleteItem(ItemElementView itemElementView) {
        propertyPresenter.deleteProperties(itemElementView.getItemId());
        itemElementView.getItemContainer().removeFromParent();
        elementViewList.remove(itemElementView);
        collectionEditorPresenter.toggleEditingStatus(false);
    }

    @Override
    public Map<String, Map<String, String>> getSimpleItemsProperties() {
        return elementViewList.stream()
                .collect(Collectors.toMap(ElementView::getItemId,
                                          itemElementView -> propertyPresenter.getSimpleProperties(itemElementView.getItemId())));
    }

    @Override
    public Map<String, Map<String, Map<String, String>>> getNestedItemsProperties() {
        return elementViewList.stream()
                .collect(Collectors.toMap(ElementView::getItemId,
                                          itemElementView -> propertyPresenter.getNestedProperties(itemElementView.getItemId())));
    }

    protected void addNestedItemElementView(ItemElementView containerItemElementView, Map<String, String> propertiesMap, String parentItemId) {
        final ItemElementView itemElementView = viewsProvider.getListEditorElementView();
        itemElementView.init(this);
        final UListElement innerItemContainer = itemElementView.getInnerItemContainer();
        itemElementView.getSaveChange().removeFromParent();
        String itemId = parentItemId + "." + innerItemContainer.getChildCount();
        itemElementView.setItemId(itemId);
        propertiesMap.forEach((propertyName, propertyValue) ->
                                            innerItemContainer.appendChild(propertyPresenter.getPropertyFields(itemId, propertyName, propertyValue)));
        containerItemElementView.getInnerItemContainer().insertBefore(itemElementView.getItemContainer(), containerItemElementView.getSaveChange());
    }
}
