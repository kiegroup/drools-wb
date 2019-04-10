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

package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import java.util.ArrayList;
import java.util.List;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioCellTextAreaSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionElement;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.model.GridData;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(GwtMockitoTestRunner.class)
public class DuplicateInstanceCommandTest extends AbstractSelectedColumnCommandTest {

    protected String GRID_COLUMN_ID_1 = GRID_COLUMN_ID + "_1";
    protected String GRID_PROPERTY_TITLE_1 = GRID_PROPERTY_TITLE + "_1";
    protected String FACT_ALIAS_1 = FACT_ALIAS + "_1";
    protected String FULL_CLASS_NAME_1 = FULL_CLASS_NAME + "_1";
    protected String FACT_IDENTIFIER_NAME_1 = FACT_IDENTIFIER_NAME + "_1";
    protected String VALUE_1 = VALUE + "_1";

    @Mock
    protected ScenarioGridColumn scenarioGridColumnMock1;
    @Mock
    protected FactMapping factMappingMock1;
    @Mock
    protected FactMappingValue factMappingValueMock1;
    @Mock
    protected FactIdentifier factIdentifierMock1;
    @Mock
    protected List<GridColumn.HeaderMetaData> headerMetaDatasMock1;
    @Mock
    protected ScenarioHeaderMetaData informationHeaderMetaDataMock1;
    @Mock
    protected ScenarioHeaderMetaData propertyHeaderMetaDataMock1;

    @Before
    public void setup() {
        super.setup();
        command = spy(new DuplicateInstanceCommand() {
            @Override
            protected void setInstanceHeader(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String alias, String fullClassName) {
                //Do nothing
            }

            @Override
            protected ScenarioGridColumn getScenarioGridColumnLocal(String instanceTitle, String propertyTitle, String columnId, String columnGroup, FactMappingType factMappingType, ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader,
                                                                    ScenarioCellTextAreaSingletonDOMElementFactory factoryCell, String placeHolder) {
                return gridColumnMock;
            }
        });
        assertTrue(command.isUndoable());

        when(scenarioGridColumnMock1.getHeaderMetaData()).thenReturn(headerMetaDatasMock1);
        when(scenarioGridColumnMock1.getInformationHeaderMetaData()).thenReturn(informationHeaderMetaDataMock1);
        when(scenarioGridColumnMock1.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetaDataMock1);
        when(scenarioGridColumnMock1.getFactIdentifier()).thenReturn(factIdentifierMock1);
        when(scenarioGridColumnMock1.isInstanceAssigned()).thenReturn(Boolean.TRUE);
        when(scenarioGridColumnMock1.isPropertyAssigned()).thenReturn(Boolean.TRUE);

        GridData.Range range = new GridData.Range(COLUMN_NUMBER, COLUMN_NUMBER);
        when(scenarioGridModelMock.getInstanceLimits(COLUMN_NUMBER)).thenReturn(range);

        when(headerMetaDatasMock1.get(COLUMN_NUMBER + 1)).thenReturn(informationHeaderMetaDataMock1);

        when(informationHeaderMetaDataMock1.getTitle()).thenReturn(VALUE_1);
        when(informationHeaderMetaDataMock1.getColumnGroup()).thenReturn(COLUMN_GROUP);

        when(propertyHeaderMetaDataMock1.getMetadataType()).thenReturn(ScenarioHeaderMetaData.MetadataType.PROPERTY);
        when(propertyHeaderMetaDataMock1.getTitle()).thenReturn(GRID_PROPERTY_TITLE_1);
        when(propertyHeaderMetaDataMock1.getColumnGroup()).thenReturn(COLUMN_GROUP);
        when(propertyHeaderMetaDataMock1.getColumnId()).thenReturn(GRID_COLUMN_ID_1);

        when(factMappingMock1.getFactIdentifier()).thenReturn(factIdentifierMock1);
        when(factMappingMock1.getFactAlias()).thenReturn(FACT_ALIAS_1);
        when(factMappingMock1.getClassName()).thenReturn(VALUE_CLASS_NAME);

        List<ExpressionElement> expressionElements = new ArrayList<>();
        expressionElements.add(new ExpressionElement(FACT_ALIAS_1));
        expressionElements.add(new ExpressionElement("test"));
        when(factMappingMock1.getExpressionElements()).thenReturn(expressionElements);

        when(factIdentifierMock1.getClassName()).thenReturn(FULL_CLASS_NAME_1);
        when(factIdentifierMock1.getName()).thenReturn(FACT_IDENTIFIER_NAME_1);

        gridColumns.add(scenarioGridColumnMock1);
        factMappingValuesLocal.add(factMappingValueMock1);
        factIdentifierSet.add(factIdentifierMock1);
        factMappingLocal.add(factMappingMock1);
        when(simulationDescriptorMock.getFactMappingByIndex(COLUMN_NUMBER)).thenReturn(factMappingMock1);
        scenarioGridModelMock.bindContent(simulationMock);
    }

    @Test
    public void executeIfSelectedColumn_WithoutInstanceAndProperty() {
        when(scenarioGridColumnMock1.isInstanceAssigned()).thenReturn(Boolean.FALSE);
        when(scenarioGridColumnMock1.isPropertyAssigned()).thenReturn(Boolean.FALSE);
        ((DuplicateInstanceCommand) command).executeIfSelectedColumn(scenarioSimulationContextLocal, scenarioGridColumnMock1);
        verify(scenarioGridModelMock, times(1)).getInstancesCount(eq(scenarioGridColumnMock1.getFactIdentifier().getClassName()));
        verify((DuplicateInstanceCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(scenarioGridColumnMock1), eq(COLUMN_NUMBER + 1), eq(Boolean.FALSE));
        verify((DuplicateInstanceCommand) command, never()).setInstanceHeader(any(), any(), any(), any());
        verify((DuplicateInstanceCommand) command, never()).setPropertyHeader(any(), any(), any(), any());
        verify(scenarioGridModelMock, never()).duplicateColumnValues(anyInt(), anyInt());
    }

    @Test
    public void executeIfSelectedColumn_WithInstanceOnly() {
        when(scenarioGridColumnMock1.isPropertyAssigned()).thenReturn(Boolean.FALSE);
        ((DuplicateInstanceCommand) command).executeIfSelectedColumn(scenarioSimulationContextLocal, scenarioGridColumnMock1);
        verify(scenarioGridModelMock, times(1)).getInstancesCount(eq(scenarioGridColumnMock1.getFactIdentifier().getClassName()));
        verify((DuplicateInstanceCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(scenarioGridColumnMock1), eq(COLUMN_NUMBER + 1), eq(Boolean.FALSE));
        verify((DuplicateInstanceCommand) command, times(1)).setInstanceHeader(eq(scenarioSimulationContextLocal), notNull(ScenarioGridColumn.class), eq(VALUE_1 + DuplicateInstanceCommand.COPY_LABEL + "1"), eq(FULL_CLASS_NAME_1));
        verify((DuplicateInstanceCommand) command, never()).setPropertyHeader(any(), any(), any(), any());
        verify(scenarioGridModelMock, never()).duplicateColumnValues(anyInt(), anyInt());
    }

    @Test
    public void executeIfSelectedColumn_WithInstanceAndProperty() {
        ((DuplicateInstanceCommand) command).executeIfSelectedColumn(scenarioSimulationContextLocal, scenarioGridColumnMock1);
        verify(scenarioGridModelMock, times(1)).getInstancesCount(eq(scenarioGridColumnMock1.getFactIdentifier().getClassName()));
        verify((DuplicateInstanceCommand) command, times(1)).insertNewColumn(eq(scenarioSimulationContextLocal), eq(scenarioGridColumnMock1), eq(COLUMN_NUMBER + 1), eq(Boolean.FALSE));
        verify((DuplicateInstanceCommand) command, times(1)).setInstanceHeader(eq(scenarioSimulationContextLocal), notNull(ScenarioGridColumn.class), eq(VALUE_1 + DuplicateInstanceCommand.COPY_LABEL + "1"), eq(FULL_CLASS_NAME_1));
        verify((DuplicateInstanceCommand) command, times(1)).setPropertyHeader(eq(scenarioSimulationContextLocal), notNull(ScenarioGridColumn.class), eq("value_1_copy_1.test"), eq(VALUE_CLASS_NAME), eq(GRID_PROPERTY_TITLE_1));
        verify(scenarioGridModelMock, times(1)).duplicateColumnValues(eq(COLUMN_NUMBER), anyInt());
    }

}