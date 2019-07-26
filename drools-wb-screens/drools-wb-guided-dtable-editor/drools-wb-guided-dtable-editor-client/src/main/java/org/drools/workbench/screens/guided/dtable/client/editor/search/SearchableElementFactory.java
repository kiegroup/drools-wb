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

package org.drools.workbench.screens.guided.dtable.client.editor.search;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.i18n.client.DateTimeFormat;
import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTableModellerView;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;

public class SearchableElementFactory {

    private final DateTimeFormat formatter = getFormat();

    private final GuidedDecisionTableGridHighlightHelper highlightHelper;

    @Inject
    public SearchableElementFactory(final GuidedDecisionTableGridHighlightHelper highlightHelper) {
        this.highlightHelper = highlightHelper;
    }

    public GuidedDecisionTableSearchableElement makeSearchableElement(final Integer row,
                                                                      final Integer column,
                                                                      final DTCellValue52 cellValue52,
                                                                      final GuidedDecisionTableModellerView.Presenter modeller) {

        final GuidedDecisionTableSearchableElement searchableElement = new GuidedDecisionTableSearchableElement();

        searchableElement.setHighlightHelper(highlightHelper);
        searchableElement.setModeller(modeller);
        searchableElement.setValue(convertDTCellValueToString(cellValue52));
        searchableElement.setRow(row);
        searchableElement.setColumn(column);

        return searchableElement;
    }

    String convertDTCellValueToString(final DTCellValue52 cellValue52) {
        switch (cellValue52.getDataType()) {
            case NUMERIC:
            case NUMERIC_BIGDECIMAL:
            case NUMERIC_BIGINTEGER:
            case NUMERIC_BYTE:
            case NUMERIC_DOUBLE:
            case NUMERIC_FLOAT:
            case NUMERIC_INTEGER:
            case NUMERIC_LONG:
            case NUMERIC_SHORT:
                return getStringValue(cellValue52.getNumericValue());
            case DATE:
                return getStringValue(cellValue52.getDateValue());
            case BOOLEAN:
                return getStringValue(cellValue52.getBooleanValue());
            default:
                return cellValue52.getStringValue();
        }
    }

    private String getStringValue(final Number number) {
        return number == null ? null : number.toString();
    }

    private String getStringValue(final Boolean bool) {
        return bool == null ? null : bool.toString();
    }

    private String getStringValue(final Date date) {
        return date == null ? null : formatter.format(date);
    }

    private DateTimeFormat getFormat() {
        return DateTimeFormat.getFormat(ApplicationPreferences.getDroolsDateFormat());
    }
}
