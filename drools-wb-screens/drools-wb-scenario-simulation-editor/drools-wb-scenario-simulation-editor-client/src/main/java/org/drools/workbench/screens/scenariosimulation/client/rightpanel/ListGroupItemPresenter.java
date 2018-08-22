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

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import com.google.gwt.dom.client.DivElement;

@Dependent
public class ListGroupItemPresenter implements ListGroupItemView.Presenter {

    @Inject
    private Instance<ListGroupItemView> instance;

    Map<Integer, ListGroupItemView> listGroupItemViewMap = new HashMap<>();

    @Override
    public DivElement getDivElement(int id) {
        ListGroupItemView listGroupItemView = getListGroupItemView();
        listGroupItemView.setId(id);
        listGroupItemView.init(this);
        listGroupItemViewMap.put(id, listGroupItemView);
        return listGroupItemView.getDivElement();
    }

    @Override
    public void toggleRowExpansion(int id, boolean currentlyShown) {
        if (listGroupItemViewMap.containsKey(id)) {
            if (currentlyShown) {
                listGroupItemViewMap.get(id).closeRow();
            } else {
                listGroupItemViewMap.get(id).expandRow();
            }
        }
    }

    protected ListGroupItemView getListGroupItemView() {  // This is needed for test because Mockito can not mock Instance
        return instance.get();
    }
}
