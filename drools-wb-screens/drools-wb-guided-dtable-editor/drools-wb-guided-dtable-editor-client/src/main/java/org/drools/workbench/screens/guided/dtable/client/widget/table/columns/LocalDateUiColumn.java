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
package org.drools.workbench.screens.guided.dtable.client.widget.table.columns;

import java.util.List;
import java.util.function.Consumer;

import com.ait.lienzo.client.core.shape.Text;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTablePresenter;
import org.drools.workbench.screens.guided.dtable.client.widget.table.columns.dom.datepicker.LocalDatePickerDOMElement;
import org.drools.workbench.screens.guided.dtable.client.widget.table.columns.dom.datepicker.LocalDatePickerSingletonDOMElementFactory;
import org.uberfire.ext.widgets.common.client.common.DatePicker;
import org.uberfire.ext.wires.core.grids.client.model.GridCell;
import org.uberfire.ext.wires.core.grids.client.model.GridCellValue;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridBodyCellRenderContext;

/**
 * This class in a clone of 'DateUiColumn' to bring 'LocalDate' support
 * This class needs to be used for 'LocalDate' columns as 'LocalDate' has no GWT support in client modules
 */
public class LocalDateUiColumn extends BaseSingletonDOMElementUiColumn<String, DatePicker, LocalDatePickerDOMElement, LocalDatePickerSingletonDOMElementFactory> {

    public LocalDateUiColumn(final List<HeaderMetaData> headerMetaData,
                             final double width,
                             final boolean isResizable,
                             final boolean isVisible,
                             final GuidedDecisionTablePresenter.Access access,
                             final LocalDatePickerSingletonDOMElementFactory factory) {
        super(headerMetaData,
              makeColumnRenderer(factory),
              width,
              isResizable,
              isVisible,
              access,
              factory);
    }

    static CellRenderer<String, DatePicker, LocalDatePickerDOMElement> makeColumnRenderer(final LocalDatePickerSingletonDOMElementFactory factory) {
        return new CellRenderer<String, DatePicker, LocalDatePickerDOMElement>(factory) {
            @Override
            protected void doRenderCellContent(final Text text,
                                               final String value,
                                               final GridBodyCellRenderContext context) {
                text.setText(value);
            }
        };
    }

    @Override
    public void doEdit(final GridCell<String> cell,
                       final GridBodyCellRenderContext context,
                       final Consumer<GridCellValue<String>> callback) {
        factory.attachDomElement(context,
                                 ConsumerFactory.makeOnCreationCallbackLocalDate(cell),
                                 ConsumerFactory.makeOnDisplayDatePickerCallbackLocalDate());
    }
}
