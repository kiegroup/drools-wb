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


import com.google.gwt.event.shared.EventBus;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.drools.workbench.models.guided.dtable.shared.model.ActionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.AttributeCol52;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionVariableColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.CompositeColumn;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.DescriptionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.models.guided.dtable.shared.model.MetadataCol52;
import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;
import org.drools.workbench.models.guided.dtable.shared.model.RowNumberCol52;
import org.drools.workbench.screens.guided.dtable.client.widget.table.events.SetGuidedDecisionTableModelEvent;
import org.drools.workbench.screens.guided.dtable.client.widget.table.events.SetInternalDecisionTableModelEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.AbstractDecoratedGridHeaderWidget;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.AbstractMergableGridWidget;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.AbstractVerticalDecoratedGridSidebarWidget;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.DecoratedGridCellValueAdaptor;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.ResourcesProvider;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.events.SetModelEvent;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
@WithClassesToStub({AbstractDecoratedGridHeaderWidget.class,
                    AbstractMergableGridWidget.class,
                    AbstractVerticalDecoratedGridSidebarWidget.class,
                    VerticalDecisionTableSidebarWidget.class})
public class VerticalDecoratedDecisionTableGridWidgetTest {

    @Mock
    ResourcesProvider<BaseColumn> resourceProvider;

    @Mock
    EventBus eventBus;

    @Mock
    SetModelEvent<GuidedDecisionTable52> event;

    @Mock
    DecisionTableCellFactory cellFactory;

    @Mock
    GuidedDecisionTable52 model;

    @Mock
    RowNumberCol52 rowNumberColumn;

    @Mock
    DescriptionCol52 descriptionColumn;

    @Mock
    BRLConditionVariableColumn brlConditionVariableColumn;

    @Mock
    ConditionCol52 conditionColumn;

    @Mock
    Pattern52 patternColumn;

    @Mock
    BRLConditionColumn brlConditionColumn;

    @Mock
    ActionCol52 actionColumn;

    @Captor
    ArgumentCaptor<SetInternalDecisionTableModelEvent> setInternalModelEventCaptor;

    VerticalDecoratedDecisionTableGridWidget widget;

    @Before
    public void setUp() throws Exception {

        Map<String, String> preferences = new HashMap<String, String>();
        preferences.put( ApplicationPreferences.DATE_FORMAT, "yyyy-mm-dd" );
        ApplicationPreferences.setUp(preferences);

        when(event.getModel()).thenReturn(model);

        when(model.getRowNumberCol()).thenReturn(rowNumberColumn);
        when(model.getDescriptionCol()).thenReturn(descriptionColumn);
        when(model.getMetadataCols()).thenReturn(new ArrayList<MetadataCol52>());
        when(model.getAttributeCols()).thenReturn(new ArrayList<AttributeCol52>());
        when(model.getConditions()).thenReturn(Arrays.<CompositeColumn<? extends BaseColumn>>asList(brlConditionColumn, patternColumn));
        when(model.getActionCols()).thenReturn(Arrays.asList(actionColumn));

        when(patternColumn.getChildColumns()).thenReturn(Arrays.asList(conditionColumn, conditionColumn));
        when(brlConditionColumn.getChildColumns()).thenReturn(Arrays.asList(brlConditionVariableColumn, brlConditionVariableColumn));

        when(cellFactory.getCell(any(BaseColumn.class))).thenReturn(mock(DecoratedGridCellValueAdaptor.class));

        widget = new VerticalDecoratedDecisionTableGridWidget(resourceProvider,
            cellFactory,
            mock(DecisionTableCellValueFactory.class),
            mock(DecisionTableDropDownManager.class),
            false,
            eventBus);

    }

    @Test
    public void testOnSetModel() throws Exception {

        verify(eventBus).addHandler( SetGuidedDecisionTableModelEvent.TYPE, widget );

        widget.onSetModel(event);
        verify(eventBus).fireEvent(setInternalModelEventCaptor.capture());

        SetInternalDecisionTableModelEvent internalModel = setInternalModelEventCaptor.getValue();
        assertEquals(7, internalModel.getColumns().size());
        assertEquals(rowNumberColumn, internalModel.getColumns().get(0).getModelColumn());
        assertEquals(descriptionColumn, internalModel.getColumns().get(1).getModelColumn());
        assertEquals(brlConditionVariableColumn, internalModel.getColumns().get(2).getModelColumn());
        assertEquals(brlConditionVariableColumn, internalModel.getColumns().get(3).getModelColumn());
        assertEquals(conditionColumn, internalModel.getColumns().get(4).getModelColumn());
        assertEquals(conditionColumn, internalModel.getColumns().get(5).getModelColumn());
        assertEquals(actionColumn, internalModel.getColumns().get(6).getModelColumn());
    }
}
