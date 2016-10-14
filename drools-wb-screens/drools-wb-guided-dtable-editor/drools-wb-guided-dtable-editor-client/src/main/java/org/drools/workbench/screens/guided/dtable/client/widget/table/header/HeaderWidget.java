/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.table.header;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.user.client.ui.CellPanel;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.DynamicColumn;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the guts of the widget.
 */
public class HeaderWidget extends CellPanel {

    // Child Widgets used in this Widget
    private List<HeaderSorter> sorters = new ArrayList<HeaderSorter>();
    private HeaderSplitter splitter;

    // UI Components
    private TableRowElement[] rowHeaders = new TableRowElement[ 5 ];

    private List<DynamicColumn<BaseColumn>> visibleCols = new ArrayList<DynamicColumn<BaseColumn>>();
    private List<DynamicColumn<BaseColumn>> visibleConditionCols = new ArrayList<DynamicColumn<BaseColumn>>();
    private List<DynamicColumn<BaseColumn>> visibleActionCols = new ArrayList<DynamicColumn<BaseColumn>>();

    // Constructor
    public HeaderWidget(HeaderSplitter splitter) {
        this.splitter = splitter;

        for ( int iRow = 0; iRow < rowHeaders.length; iRow++ ) {
            rowHeaders[ iRow ] = Document.get().createTRElement();
            getBody().appendChild( rowHeaders[ iRow ] );
        }
        getBody().getParentElement().<TableElement>cast().setCellSpacing( 0 );
        getBody().getParentElement().<TableElement>cast().setCellPadding( 0 );
    }

    public void clear() {
        // Remove existing widgets from the DOM hierarchy
        if ( splitter != null ) {
            remove( splitter );
        }
        for ( HeaderSorter sorter : sorters ) {
            remove( sorter );
        }
        sorters.clear();

        // Extracting visible columns makes life easier
        visibleCols.clear();
        visibleConditionCols.clear();
        visibleActionCols.clear();
    }

    public DynamicColumn<BaseColumn> getVisibleColumn(int column) {
        return visibleCols.get(column);
    }

    public int getIndexOfVisibleColumn(DynamicColumn<BaseColumn> column) {
        return visibleCols.indexOf(column);
    }

    public int getVisibleColumnsCount() {
        return visibleCols.size();
    }

    public void addVisibleColumn(DynamicColumn<BaseColumn> column) {
        visibleCols.add(column);
    }

    public void addVisibleConditionColumn(DynamicColumn<BaseColumn> column) {
        visibleConditionCols.add(column);
    }

    public DynamicColumn<BaseColumn> getVisibleConditionColumn(int column) {
        return visibleConditionCols.get(column);
    }

    public int getVisibleConditionColumnsCount() {
        return visibleConditionCols.size();
    }

    public void addVisibleActionColumn(DynamicColumn<BaseColumn> column) {
        visibleActionCols.add(column);
    }

    public int getVisibleActionColumnsCount() {
        return visibleActionCols.size();
    }

    public DynamicColumn<BaseColumn> getVisibleActionColumn(int column) {
        return visibleActionCols.get(column);
    }


    public int getHeaderRowsCount() {
        return rowHeaders.length;
    }

    public TableRowElement getHeaderRow(int row) {
        return rowHeaders[row];
    }

    public void replaceHeaderRow(int row, TableRowElement rowElement) {
        getBody().replaceChild( rowElement, rowHeaders[ row ] );
        rowHeaders[ row ] = rowElement;
    }

    public void addSplitter(TableCellElement cellElement) {
        splitter.setRowHeaders( rowHeaders );
        add( splitter, cellElement );
    }

    public void addSorter(HeaderSorter sorter, TableCellElement cellElement) {
        sorters.add( sorter );
        add( sorter, cellElement );
    }

    public boolean isSiplitterCollapsed() {
        return splitter.isCollapsed();
    }

}