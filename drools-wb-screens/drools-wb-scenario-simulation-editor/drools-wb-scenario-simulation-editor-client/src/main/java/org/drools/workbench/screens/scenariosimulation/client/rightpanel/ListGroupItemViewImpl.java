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

import javax.enterprise.context.Dependent;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Widget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Dependent
@Templated
public class ListGroupItemViewImpl implements ListGroupItemView {

    @DataField("listGroupItem")
    DivElement listGroupItem = Document.get().createDivElement();

    @DataField("listGroupItemHeader")
    DivElement listGroupItemHeader = Document.get().createDivElement();

    @DataField("listGroupItemContainer")
    DivElement listGroupItemContainer = Document.get().createDivElement();

    @DataField("faAngleRight")
    SpanElement faAngleRight = Document.get().createSpanElement();

    Presenter presenter;

    int id;

    @Override
    public Widget asWidget() {
        return null;
    }

    @EventHandler("listGroupItemHeader")
    public void onListGroupItemHeaderClick(ClickEvent event) {
        presenter.toggleRowExpansion(id, listGroupItemHeader.getClassName().contains("list-view-pf-expand-active"));
    }

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public DivElement getDivElement() {
        return listGroupItem;
    }

    @Override
    public void closeRow() {
        listGroupItemHeader.removeClassName("list-view-pf-expand-active");
        listGroupItemContainer.addClassName("hidden");
        faAngleRight.removeClassName("fa-angle-down");
    }

    @Override
    public void expandRow() {
        listGroupItemHeader.addClassName("list-view-pf-expand-active");
        listGroupItemContainer.removeClassName("hidden");
        faAngleRight.addClassName("fa-angle-down");
    }
}
