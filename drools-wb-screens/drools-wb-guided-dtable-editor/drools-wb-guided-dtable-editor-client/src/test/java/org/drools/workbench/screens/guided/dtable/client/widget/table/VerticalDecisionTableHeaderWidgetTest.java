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

package org.drools.workbench.screens.guided.dtable.client.widget.table;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.TableCellElement;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.shared.EventBus;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.drools.workbench.models.guided.dtable.shared.model.ActionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.DescriptionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.models.guided.dtable.shared.model.RowNumberCol52;
import org.drools.workbench.screens.guided.dtable.client.widget.table.events.SetInternalDecisionTableModelEvent;
import org.drools.workbench.screens.guided.dtable.client.widget.table.header.HeaderWidget;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.AbstractDecoratedGridHeaderWidget;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.DynamicColumn;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.ResourcesProvider;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.SortDirection;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.events.SetInternalModelEvent;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
@WithClassesToStub(AbstractDecoratedGridHeaderWidget.class)
public class VerticalDecisionTableHeaderWidgetTest {

    private static final String HEADER_TEXT = "classHeaderText";
    private static final String HEADER_ROW_INTERMEDIATE = "classHeaderRowIntermediate";
    private static final String CELL_TABLE_COLUMN = "classCellTableColumn";


    private VerticalDecisionTableHeaderWidget header;

    @Mock
    ResourcesProvider<BaseColumn> resourceProvider;

    @Mock
    EventBus eventBus;

    @Mock
    SetInternalModelEvent<GuidedDecisionTable52, BaseColumn> setInternalModelEvent;

    @Mock
    HeaderWidget widget;

    @Mock
    DynamicColumn<BaseColumn> rowNumberColumn;

    @Mock
    DynamicColumn<BaseColumn> descriptionColumn;

    @Mock
    DynamicColumn<BaseColumn> conditionColumnOne;

    @Mock
    DynamicColumn<BaseColumn> conditionColumnTwo;

    @Mock
    DynamicColumn<BaseColumn> actionColumn;

    @Mock
    RowNumberCol52 rowNumber;

    @Mock
    DescriptionCol52 description;

    @Mock
    ConditionCol52 conditionOne;

    @Mock
    BRLConditionColumn conditionTwo;

    @Mock
    ActionCol52 action;

    @Captor
    ArgumentCaptor<TableRowElement> rowCaptor;

    @Captor
    ArgumentCaptor<TableCellElement> cellCaptor;

    @Captor
    ArgumentCaptor<DivElement> divCaptor;

    @Before
    public void setUp() throws Exception {
        List<DynamicColumn<BaseColumn>> columns = new ArrayList<DynamicColumn<BaseColumn>>();
        columns.add(rowNumberColumn);
        columns.add(descriptionColumn);
        columns.add(conditionColumnOne);
        columns.add(conditionColumnTwo);
        columns.add(actionColumn);
        when(setInternalModelEvent.getColumns()).thenReturn(columns);

        when(rowNumberColumn.isVisible()).thenReturn(true);
        when(descriptionColumn.isVisible()).thenReturn(true);
        when(conditionColumnOne.isVisible()).thenReturn(true);
        when(conditionColumnTwo.isVisible()).thenReturn(true);
        when(actionColumn.isVisible()).thenReturn(true);

        when(rowNumberColumn.getModelColumn()).thenReturn(rowNumber);
        when(descriptionColumn.getModelColumn()).thenReturn(description);
        when(conditionColumnOne.getModelColumn()).thenReturn(conditionOne);
        when(conditionColumnTwo.getModelColumn()).thenReturn(conditionTwo);
        when(actionColumn.getModelColumn()).thenReturn(action);

        when(rowNumberColumn.getSortDirection()).thenReturn(SortDirection.ASCENDING);
        when(descriptionColumn.getSortDirection()).thenReturn(SortDirection.ASCENDING);
        when(conditionColumnOne.getSortDirection()).thenReturn(SortDirection.ASCENDING);
        when(conditionColumnTwo.getSortDirection()).thenReturn(SortDirection.ASCENDING);
        when(actionColumn.getSortDirection()).thenReturn(SortDirection.ASCENDING);

        when(widget.getVisibleColumnsCount()).thenReturn(5);
        when(widget.getVisibleColumn(0)).thenReturn(rowNumberColumn);
        when(widget.getVisibleColumn(1)).thenReturn(descriptionColumn);
        when(widget.getVisibleColumn(2)).thenReturn(conditionColumnOne);
        when(widget.getVisibleColumn(3)).thenReturn(conditionColumnTwo);
        when(widget.getVisibleColumn(4)).thenReturn(actionColumn);

        when(conditionOne.isBound()).thenReturn(true);
        when(conditionOne.getBinding()).thenReturn("p");
        when(conditionOne.getHeader()).thenReturn("Person");

        when(conditionTwo.isBound()).thenReturn(false);
        when(conditionTwo.getHeader()).thenReturn("Car");

        when(action.getHeader()).thenReturn("print person");

        when(widget.getHeaderRowsCount()).thenReturn(5);

        when(resourceProvider.headerText()).thenReturn(HEADER_TEXT);
        when(resourceProvider.headerRowIntermediate()).thenReturn(HEADER_ROW_INTERMEDIATE);
        when(resourceProvider.cellTableColumn(any(BaseColumn.class))).thenReturn(CELL_TABLE_COLUMN);

        Map<String, String> preferences = new HashMap<String, String>();
        preferences.put( ApplicationPreferences.DATE_FORMAT, "yyyy-mm-dd" );
        ApplicationPreferences.setUp(preferences);
        header = new TestableVerticalDecisionTableHeaderWidget(resourceProvider, false, eventBus, new ArrayList<DynamicColumn<BaseColumn>>());
        header.widget = widget;
    }

    @Test
    public void testSetModelEventHandlerRegistration() throws Exception {
        verify(eventBus).addHandler(SetInternalDecisionTableModelEvent.TYPE, header);
    }

    @Test
    public void testRedrawNoColumns() throws Exception {
        header.redraw();

        verify(widget, never()).addVisibleColumn(any(DynamicColumn.class));
        verify(widget, never()).addVisibleConditionColumn(any(DynamicColumn.class));
        verify(widget, never()).addVisibleActionColumn(any(DynamicColumn.class));
    }

    @Test
    public void testRedrawFirstRow() throws Exception {
        header.onSetInternalModel(setInternalModelEvent);

        verify(widget).addVisibleConditionColumn(conditionColumnOne);
        verify(widget).addVisibleConditionColumn(conditionColumnTwo);
        verify(widget).addVisibleActionColumn(actionColumn);

        verify(widget).replaceHeaderRow(eq(0), rowCaptor.capture());

        TableRowElement firstRow = rowCaptor.getValue();
        verify(firstRow, times(5)).appendChild(cellCaptor.capture());
        assertEquals(5, cellCaptor.getAllValues().size());

        verify(cellCaptor.getAllValues().get(0)).setColSpan(1);
        verify(cellCaptor.getAllValues().get(0)).setRowSpan(4);
        verify(cellCaptor.getAllValues().get(0)).addClassName(HEADER_TEXT);
        verify(cellCaptor.getAllValues().get(0)).addClassName(HEADER_ROW_INTERMEDIATE);
        verify(cellCaptor.getAllValues().get(0)).addClassName(CELL_TABLE_COLUMN);
        verify(cellCaptor.getAllValues().get(0)).appendChild(divCaptor.capture());
        verify(divCaptor.getValue()).setInnerText("#");

        verify(cellCaptor.getAllValues().get(1)).setColSpan(1);
        verify(cellCaptor.getAllValues().get(1)).addClassName(HEADER_TEXT);
        verify(cellCaptor.getAllValues().get(1)).addClassName(HEADER_ROW_INTERMEDIATE);
        verify(cellCaptor.getAllValues().get(1)).addClassName(CELL_TABLE_COLUMN);
        verify(cellCaptor.getAllValues().get(1)).appendChild(divCaptor.capture());
        verify(divCaptor.getValue()).setInnerText("Description");

        verify(cellCaptor.getAllValues().get(2)).setColSpan(1);
        verify(cellCaptor.getAllValues().get(2)).addClassName(HEADER_TEXT);
        verify(cellCaptor.getAllValues().get(2), never()).addClassName(HEADER_ROW_INTERMEDIATE);
        verify(cellCaptor.getAllValues().get(2)).addClassName(CELL_TABLE_COLUMN);
        verify(cellCaptor.getAllValues().get(2)).appendChild(divCaptor.capture());
        verify(divCaptor.getValue()).setInnerText("p : Person");

        verify(cellCaptor.getAllValues().get(3)).setColSpan(1);
        verify(cellCaptor.getAllValues().get(3)).addClassName(HEADER_TEXT);
        verify(cellCaptor.getAllValues().get(3), never()).addClassName(HEADER_ROW_INTERMEDIATE);
        verify(cellCaptor.getAllValues().get(3)).addClassName(CELL_TABLE_COLUMN);
        verify(cellCaptor.getAllValues().get(3)).appendChild(divCaptor.capture());
        verify(divCaptor.getValue()).setInnerText("Car");

        verify(cellCaptor.getAllValues().get(4)).setColSpan(1);
        verify(cellCaptor.getAllValues().get(4)).addClassName(HEADER_TEXT);
        verify(cellCaptor.getAllValues().get(4), never()).addClassName(HEADER_ROW_INTERMEDIATE);
        verify(cellCaptor.getAllValues().get(4)).addClassName(CELL_TABLE_COLUMN);
        verify(cellCaptor.getAllValues().get(4)).appendChild(divCaptor.capture());
        verify(divCaptor.getValue()).setInnerText("print person");

    }

    private class TestableVerticalDecisionTableHeaderWidget extends VerticalDecisionTableHeaderWidget {
        public TestableVerticalDecisionTableHeaderWidget(ResourcesProvider<BaseColumn> resources,
                                                         boolean isReadOnly,
                                                         EventBus eventBus,
                                                         List<DynamicColumn<BaseColumn>> sortableColumns) {
            super(resources, isReadOnly, eventBus);
            this.resources = resources;
            this.sortableColumns = sortableColumns;
        }
    }
}
