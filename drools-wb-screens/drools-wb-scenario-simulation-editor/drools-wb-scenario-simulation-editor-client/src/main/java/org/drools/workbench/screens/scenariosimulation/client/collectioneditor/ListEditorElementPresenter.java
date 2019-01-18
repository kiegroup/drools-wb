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

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.UListElement;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;

public class ListEditorElementPresenter implements ListEditorElementView.Presenter {

    protected CollectionEditorView.Presenter collectionEditorPresenter;

    @Inject
    protected PropertyEditorView.Presenter propertyEditorPresenter;

    @Inject
    protected ViewsProvider viewsProvider;

    /**
     * <code>List</code> of currently present <code>ListEditorElementView</code>s
     */
    protected List<ListEditorElementView> listEditorElementViewList = new ArrayList<>();

    @Override
    public void setCollectionEditorPresenter(CollectionEditorView.Presenter collectionEditorPresenter) {
        this.collectionEditorPresenter = collectionEditorPresenter;
    }

    @Override
    public UListElement getItemContainer(int itemId, Map<String, String> propertiesMap) {
        final ListEditorElementView listEditorElementView = viewsProvider.getListEditorElementView();
        listEditorElementView.init(this);
        listEditorElementView.setItemId(itemId);
        final UListElement toReturn = listEditorElementView.getItemContainer();
        final LIElement saveChange = listEditorElementView.getSaveChange();
        propertiesMap.forEach((propertyName, propertyValue) ->
                                      toReturn.insertBefore(propertyEditorPresenter.getPropertyFields(itemId,propertyName, propertyValue), saveChange));
        listEditorElementViewList.add(listEditorElementView);
        return toReturn;
    }

    @Override
    public void onToggleRowExpansion(boolean isShown) {
        listEditorElementViewList.forEach(listEditorElementView -> onToggleRowExpansion(listEditorElementView, isShown));
    }

    @Override
    public void onToggleRowExpansion(ListEditorElementView listEditorElementView, boolean isShown) {
        CollectionEditorUtils.toggleRowExpansion(listEditorElementView.getFaAngleRight(), !isShown);
        propertyEditorPresenter.onToggleRowExpansion(listEditorElementView.getItemId(), isShown);
    }

    @Override
    public void onEditItem(ListEditorElementViewImpl listEditorElementView) {
        propertyEditorPresenter.editProperties(listEditorElementView.getItemId());
        listEditorElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.VISIBLE);
    }

    @Override
    public void updateItem(ListEditorElementViewImpl listEditorElementView) {
        propertyEditorPresenter.updateProperties(listEditorElementView.getItemId());
        listEditorElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }

    @Override
    public void onStopEditingItem(ListEditorElementViewImpl listEditorElementView) {
        propertyEditorPresenter.stopEditProperties(listEditorElementView.getItemId());
        listEditorElementView.getSaveChange().getStyle().setVisibility(Style.Visibility.HIDDEN);
    }

    @Override
    public void onDeleteItem(ListEditorElementViewImpl listEditorElementView) {
        propertyEditorPresenter.deleteProperties(listEditorElementView.getItemId());
        listEditorElementView.getItemContainer().removeFromParent();
        listEditorElementViewList.remove(listEditorElementView);
        collectionEditorPresenter.deleteItem(listEditorElementView.getItemId());
    }
}
