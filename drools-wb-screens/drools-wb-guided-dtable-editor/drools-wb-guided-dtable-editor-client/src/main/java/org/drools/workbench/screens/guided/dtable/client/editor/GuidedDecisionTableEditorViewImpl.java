/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.guided.dtable.client.editor;

import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.screens.guided.dtable.client.resources.i18n.GuidedDecisionTableConstants;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTableModellerView;
import org.drools.workbench.screens.guided.dtable.shared.XLSConversionResultMessage;
import org.kie.workbench.common.widgets.client.popups.list.MessageType;
import org.kie.workbench.common.widgets.client.popups.list.PopupListWidget;
import org.kie.workbench.common.widgets.metadata.client.KieEditorViewImpl;
import org.uberfire.ext.widgets.common.client.common.popups.errors.ErrorPopup;

/**
 * Guided Decision Table Editor View implementation
 */
@Dependent
public class GuidedDecisionTableEditorViewImpl
        extends KieEditorViewImpl
        implements GuidedDecisionTableEditorPresenter.View {

    private static GuidedDecisionTableEditorViewImplUiBinder uiBinder = GWT.create(GuidedDecisionTableEditorViewImplUiBinder.class);
    @UiField
    SimpleLayoutPanel container;

    @Inject
    private PopupListWidget popupListWidget;

    public GuidedDecisionTableEditorViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));

        //Disable LockManager's default "Lock on Demand" behaviour
        container.getElement().setAttribute("data-uf-lock",
                                            "false");

        addAttachHandler(event -> {
            if (event.isAttached()) {
                getElement().getParentElement().getStyle().setHeight(100.0, Style.Unit.PCT);
                getElement().getParentElement().getStyle().setWidth(100.0, Style.Unit.PCT);
            }
        });
    }

    @Override
    public void onResize() {
        container.onResize();
    }

    @Override
    public void setModellerView(final GuidedDecisionTableModellerView view) {
        container.setWidget(view);
    }

    @Override
    public void showConversionSuccess(final Set<XLSConversionResultMessage> infoMessages) {
        popupListWidget.clear();
        popupListWidget.setTitle(GuidedDecisionTableConstants.INSTANCE.TableConvertedSuccessfully());
        popupListWidget.addListMessage(MessageType.INFO, GuidedDecisionTableConstants.INSTANCE.TableConvertedSuccessfully());
        for (XLSConversionResultMessage infoMessage : infoMessages) {
            popupListWidget.addListMessage(MessageType.INFO, ConversionInfoMessageTranslator.translate(infoMessage));
        }
        popupListWidget.show();
    }

    @Override
    public void showConversionErrorMessage(final String errorMessage) {
        ErrorPopup.showMessage(errorMessage);
    }

    interface GuidedDecisionTableEditorViewImplUiBinder extends UiBinder<Widget, GuidedDecisionTableEditorViewImpl> {

    }
}