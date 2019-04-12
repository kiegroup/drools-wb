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
package org.drools.workbench.screens.scenariosimulation.client.popup;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.client.views.pfly.widgets.JQueryProducer;
import org.uberfire.client.views.pfly.widgets.Popover;
import org.uberfire.mvp.Command;

@ApplicationScoped
@Templated
public class ErrorReportPopupView extends AbstractPopoverView implements ErrorReportPopup {

    protected Command applyCommand;

    @DataField("keepButton")
    protected ButtonElement keepButton = Document.get().createButtonElement();

    @DataField("applyButton")
    protected ButtonElement applyButton = Document.get().createButtonElement();



    public ErrorReportPopupView() {
        //CDI proxy
    }

    @Inject
    public ErrorReportPopupView(final Div popoverElement,
                                final Div popoverContainerElement,
                                final Div popoverContentElement,
                                final JQueryProducer.JQuery<Popover> jQueryPopover) {
        super(popoverElement,
              popoverContainerElement,
              popoverContentElement,
              jQueryPopover);
    }

    @Override
    public void show(final String errorTitleText,
                     final String errorContentText,
                     final String keepText,
                     final String applyText,
                     final Command applyCommand,
                     final int mx,
                     final int my) {

        this.applyCommand = applyCommand;
        popoverContentElement.setTextContent(errorContentText);
        keepButton.setInnerText(keepText);
        applyButton.setInnerText(applyText);
        super.show(Optional.of(errorTitleText), mx, my);
    }

    @EventHandler("keepButton")
    public void onKeepButtonClicked(ClickEvent clickEvent) {
        hide();
    }

    @EventHandler("applyButton")
    public void onApplyButtonClicked(ClickEvent clickEvent) {
        applyCommand.execute();
        hide();
    }
}
