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
package org.drools.workbench.screens.guided.dtable.client.widget.table.model.converters.column.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;
import org.assertj.core.api.Assertions;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTablePresenter;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTableView;
import org.drools.workbench.screens.guided.dtable.client.widget.table.columns.DateUiColumn;
import org.drools.workbench.screens.guided.dtable.client.widget.table.columns.EnumSingleSelectBooleanUiColumn;
import org.drools.workbench.screens.guided.dtable.client.widget.table.columns.LocalDateUiColumn;
import org.drools.workbench.screens.guided.dtable.client.widget.table.utilities.ColumnUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.soup.project.datamodel.oracle.DataType;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@PrepareForTest(DateTimeFormat.class)
@RunWith(PowerMockRunner.class)
public class BaseColumnConverterImplTest {

    @Mock
    private ColumnUtilities columnUtilities;

    @Before
    public void setUp() throws Exception {
        mockStatic(DateTimeFormat.class);
        PowerMockito.when(DateTimeFormat.getFormat(anyString())).thenReturn(mock(DateTimeFormat.class));
    }

    @Test
    public void newSingleSelectionEnumColumnReturnsEnumSingleSelectBooleanUiColumn() {
        assertTrue(getConverter().newSingleSelectionEnumColumn("FactType",
                                                               "factField",
                                                               DataType.DataTypes.BOOLEAN,
                                                               mock(BaseColumn.class),
                                                               mock(GuidedDecisionTablePresenter.Access.class),
                                                               mock(GuidedDecisionTableView.class)) instanceof EnumSingleSelectBooleanUiColumn);
    }

    @Test
    public void testGetDateColumn() {
        final BaseColumn column = mock(BaseColumn.class);
        when(columnUtilities.getType(column)).thenReturn(DataType.TYPE_DATE);
        Assertions.assertThat(getConverter()
                                      .initialiseMocks(columnUtilities)
                                      .newColumn(column,
                                                 mock(GuidedDecisionTablePresenter.Access.class),
                                                 mock(GuidedDecisionTableView.class)))
                .isInstanceOf(DateUiColumn.class);
    }

    @Test
    public void testGetLocalDateColumn() {
        final BaseColumn column = mock(BaseColumn.class);
        when(columnUtilities.getType(column)).thenReturn(DataType.TYPE_LOCAL_DATE);
        Assertions.assertThat(getConverter()
                                      .initialiseMocks(columnUtilities)
                                      .newColumn(column,
                                                 mock(GuidedDecisionTablePresenter.Access.class),
                                                 mock(GuidedDecisionTableView.class)))
                .isInstanceOf(LocalDateUiColumn.class);
    }

    private BaseColumnConverterImplMock getConverter() {
        return new BaseColumnConverterImplMock() {

            @Override
            public boolean handles(BaseColumn column) {
                return false;
            }

            @Override
            public GridColumn<?> convertColumn(BaseColumn column, GuidedDecisionTablePresenter.Access access, GuidedDecisionTableView gridWidget) {
                return null;
            }

            @Override
            public List<GridColumn.HeaderMetaData> makeHeaderMetaData(BaseColumn column) {
                return new ArrayList<>();
            }
        };
    }

    private abstract class BaseColumnConverterImplMock extends BaseColumnConverterImpl {

        public BaseColumnConverterImplMock initialiseMocks(final ColumnUtilities columnUtilitiesMock) {
            BaseColumnConverterImplMock.this.columnUtilities = columnUtilitiesMock;
            return BaseColumnConverterImplMock.this;
        }
    }
}