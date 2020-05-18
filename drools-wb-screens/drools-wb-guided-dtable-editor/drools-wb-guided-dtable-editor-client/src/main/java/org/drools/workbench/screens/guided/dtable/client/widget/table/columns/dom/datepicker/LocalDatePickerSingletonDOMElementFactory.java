/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtable.client.widget.table.columns.dom.datepicker;

import java.util.Date;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.KeyDownEvent;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTableView;
import org.drools.workbench.screens.guided.dtable.client.widget.table.columns.dom.textbox.SingleValueSingletonDOMElementFactory;
import org.drools.workbench.screens.guided.dtable.client.widget.table.keyboard.KeyDownHandlerDatePicker;
import org.uberfire.ext.widgets.common.client.common.DatePicker;
import org.uberfire.ext.wires.core.grids.client.widget.grid.GridWidget;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;
import org.uberfire.ext.wires.core.grids.client.widget.layer.impl.GridLienzoPanel;

import static org.kie.workbench.common.widgets.client.util.TimeZoneUtils.FORMATTER;

/**
 * This class is a clone of 'DatePickerSingletonDOMElementFactory' to support 'LocalDate' cells
 * This class needs to be used for 'LocalDate' cells as 'LocalDate' has no GWT support in client modules
 */
public class LocalDatePickerSingletonDOMElementFactory extends SingleValueSingletonDOMElementFactory<String, DatePicker, LocalDatePickerDOMElement> {

    public LocalDatePickerSingletonDOMElementFactory(final GridLienzoPanel gridPanel,
                                                     final GridLayer gridLayer,
                                                     final GuidedDecisionTableView gridWidget) {
        super(gridPanel,
              gridLayer,
              gridWidget);
    }

    @Override
    public DatePicker createWidget() {
        return new DatePicker() {
            @Override
            protected void onLoad() {
                super.onLoad();
                if (getElement().getParentElement() != null) {
                    getElement().getParentElement().getStyle().setPosition(Style.Position.ABSOLUTE);
                }
            }
        };
    }

    private void doValueUpdate() {
        flush();
        destroyResources();
        gridLayer.batch();
        gridPanel.setFocus(true);
    }

    @Override
    public String convert(final String value) {
        if (value != null && !value.isEmpty()) {
            return value;
        } else {
            return FORMATTER.format(new Date());
        }
    }

    @Override
    protected String getValue() {
        if (getWidget() != null) {
            return FORMATTER.format(getWidget().getValue());
        }
        return null;
    }

    DatePicker getWidget() {
        return widget;
    }

    @Override
    protected LocalDatePickerDOMElement createDomElementInternal(final DatePicker widget,
                                                                 final GridLayer gridLayer,
                                                                 final GridWidget gridWidget) {
        return new LocalDatePickerDOMElement(widget,
                                             gridLayer,
                                             gridWidget);
    }

    @Override
    public void registerHandlers(final DatePicker widget, final LocalDatePickerDOMElement widgetDomElement) {
        widget.addChangeDateHandler((e) -> doValueUpdate());
        widget.addDomHandler(new KeyDownHandlerDatePicker(gridPanel,
                                                          gridLayer,
                                                          gridWidget,
                                                          this),
                             KeyDownEvent.getType());
    }
}
