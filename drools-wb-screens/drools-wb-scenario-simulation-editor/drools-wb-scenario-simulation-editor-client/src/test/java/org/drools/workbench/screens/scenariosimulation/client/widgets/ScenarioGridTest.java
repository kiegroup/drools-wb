/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.client.widgets;

import java.util.stream.IntStream;

import com.ait.lienzo.client.core.event.NodeMouseDoubleClickHandler;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.renderers.ScenarioGridRenderer;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationBuilders;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionElement;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridSelectionManager;
import org.uberfire.ext.wires.core.grids.client.widget.layer.pinning.GridPinnedModeManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class ScenarioGridTest {

    @Mock
    private ScenarioGridModel mockScenarioGridModel;
    @Mock
    private ScenarioGridLayer mockScenarioGridLayer;
    @Mock
    private ScenarioGridRenderer mockScenarioGridRenderer;
    @Mock
    private ScenarioGridPanel mockScenarioGridPanel;
    @Mock
    private ScenarioHeaderTextBoxSingletonDOMElementFactory scenarioHeaderTextBoxSingletonDOMElementFactoryMock;
    @Mock
    private ScenarioSimulationBuilders.HeaderBuilder headerBuilderMock;
    @Mock
    private ScenarioGridColumn scenarioGridColumnMock;
    @Mock
    private ScenarioHeaderMetaData propertyHeaderMetadataMock;

    private final FactMappingType factMappingType = FactMappingType.valueOf("OTHER");
    private final String EXPRESSION_ALIAS = "EXPRESSION_ALIAS";

    private FactMapping factMapping;

    private Simulation simulation = new Simulation();

    private final int COLUMNS = 6;

    private ScenarioGrid scenarioGrid;

    @Before
    public void setup() {
        when(scenarioGridColumnMock.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetadataMock);
        factMapping = new FactMapping(EXPRESSION_ALIAS, FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);
        simulation = getSimulation();
        scenarioGrid = spy(new ScenarioGrid(mockScenarioGridModel, mockScenarioGridLayer, mockScenarioGridRenderer, mockScenarioGridPanel) {

            @Override
            protected void appendRow(int rowIndex, Scenario scenario) {
                // do nothing
            }

            @Override
            protected ScenarioHeaderTextBoxSingletonDOMElementFactory getScenarioHeaderTextBoxSingletonDOMElementFactory() {
                return scenarioHeaderTextBoxSingletonDOMElementFactoryMock;
            }

            @Override
            protected ScenarioSimulationBuilders.HeaderBuilder getHeaderBuilderLocal(String columnTitle, String columnId, String columnGroup, FactMappingType factMappingType, ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader) {
                return headerBuilderMock;
            }

            @Override
            protected ScenarioGridColumn getScenarioGridColumnLocal(ScenarioSimulationBuilders.HeaderBuilder headerBuilder, boolean readOnly, String placeHolder) {
                return scenarioGridColumnMock;
            }
        });
    }

    @Test
    public void getGridMouseDoubleClickHandler() {
        NodeMouseDoubleClickHandler retrieved = scenarioGrid.getGridMouseDoubleClickHandler(mock(GridSelectionManager.class), mock(GridPinnedModeManager.class));
        assertNotNull(retrieved);
    }

    @Test
    public void setContent() {
        scenarioGrid.setContent(simulation);
        verify(mockScenarioGridModel, times(1)).clear();
        verify(mockScenarioGridModel, times(1)).bindContent(eq(simulation));
        verify(scenarioGrid, times(1)).setHeaderColumns(eq(simulation));
        verify(scenarioGrid, times(1)).appendRows(eq(simulation));
    }

    @Test
    public void setHeaderColumns() {
        scenarioGrid.setHeaderColumns(simulation);
        verify(scenarioGrid, times(COLUMNS)).setHeaderColumn(anyInt(), isA(FactMapping.class));
    }

    @Test
    public void setHeaderColumn() {
        String columnId = factMapping.getExpressionIdentifier().getName();
        final FactMappingType type = factMapping.getExpressionIdentifier().getType();
        String columnGroup = type.name();
        scenarioGrid.setHeaderColumn(1, factMapping);
        verify(scenarioGrid, times(1)).getTitle(eq(factMapping.getFactIdentifier()), eq(factMapping));
        verify(scenarioGrid, times(1)).isReadOnly(eq(factMapping.getFactIdentifier()));
        verify(scenarioGrid, times(1)).getPlaceholder(eq(false));
        verify(scenarioGrid, times(1)).getScenarioGridColumnLocal(eq(EXPRESSION_ALIAS),
                                                                  eq(columnId),
                                                                  eq(columnGroup),
                                                                  eq(type),
                                                                  eq(false),
                                                                  eq(ScenarioSimulationEditorConstants.INSTANCE.insertValue()));
        verify(scenarioGrid, times(1)).conditionalPopulatePropertyHeader(eq(factMapping.getFactIdentifier()),
                                                                         eq(factMapping),
                                                                         eq(scenarioGridColumnMock));
    }

    @Test
    public void conditionalPopulatePropertyHeader() {
        scenarioGrid.conditionalPopulatePropertyHeader(factMapping.getFactIdentifier(), factMapping, scenarioGridColumnMock);
        verify(scenarioGridColumnMock, never()).getPropertyHeaderMetaData();
        FactIdentifier testFactIdentifier = new FactIdentifier("TEST", String.class.getName());
        ExpressionIdentifier testExpressionIdentifier = new ExpressionIdentifier("TEST", FactMappingType.GIVEN);
        FactMapping toTest = new FactMapping(testFactIdentifier, testExpressionIdentifier);
        toTest.getExpressionElements().add(0, new ExpressionElement("age"));
        reset(scenarioGridColumnMock);
        when(scenarioGridColumnMock.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetadataMock);
        scenarioGrid.conditionalPopulatePropertyHeader(toTest.getFactIdentifier(), toTest, scenarioGridColumnMock);
        verify(scenarioGridColumnMock, times(2)).getPropertyHeaderMetaData();
        verify(propertyHeaderMetadataMock, times(1)).setTitle(eq("age"));
        verify(propertyHeaderMetadataMock, times(1)).setReadOnly(eq(false));
        reset(scenarioGridColumnMock);
        when(scenarioGridColumnMock.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetadataMock);
        reset(propertyHeaderMetadataMock);
        toTest.getExpressionElements().set(0, new ExpressionElement("address"));
        toTest.getExpressionElements().add(1, new ExpressionElement("street"));
        scenarioGrid.conditionalPopulatePropertyHeader(toTest.getFactIdentifier(), toTest, scenarioGridColumnMock);
        verify(scenarioGridColumnMock, times(2)).getPropertyHeaderMetaData();
        verify(propertyHeaderMetadataMock, times(1)).setTitle(eq("address.street"));
        verify(propertyHeaderMetadataMock, times(1)).setReadOnly(eq(false));
    }

    @Test
    public void getScenarioGridColumnLocal() {
        String columnId = factMapping.getExpressionIdentifier().getName();
        String columnTitle = factMapping.getFactIdentifier().getName();
        final FactMappingType type = factMapping.getExpressionIdentifier().getType();
        String columnGroup = type.name();
        scenarioGrid.getScenarioGridColumnLocal(columnTitle, columnId, columnGroup, type, false, ScenarioSimulationEditorConstants.INSTANCE.insertValue());
        verify(scenarioGrid, times(1)).getScenarioHeaderTextBoxSingletonDOMElementFactory();
        verify(scenarioGrid, times(1)).getHeaderBuilderLocal(eq(columnTitle),
                                                             eq(columnId),
                                                             eq(columnGroup),
                                                             eq(type),
                                                             eq(scenarioHeaderTextBoxSingletonDOMElementFactoryMock));
    }

    @Test
    public void getTitle() {
        FactIdentifier toTest = FactIdentifier.EMPTY;
        assertEquals(EXPRESSION_ALIAS, scenarioGrid.getTitle(toTest, factMapping));
    }

    @Test
    public void isReadOnly() {
        FactIdentifier toTest = FactIdentifier.DESCRIPTION;
        assertFalse(scenarioGrid.isReadOnly(toTest));
        toTest = FactIdentifier.EMPTY;
        assertTrue(scenarioGrid.isReadOnly(toTest));
        toTest = FactIdentifier.INDEX;
        assertTrue(scenarioGrid.isReadOnly(toTest));
    }

    @Test
    public void getPlaceholder() {
        assertEquals(ScenarioSimulationEditorConstants.INSTANCE.defineValidType(), scenarioGrid.getPlaceholder(true));
        assertEquals(ScenarioSimulationEditorConstants.INSTANCE.insertValue(), scenarioGrid.getPlaceholder(false));
    }

    @Test
    public void appendRows() {
        scenarioGrid.appendRows(simulation);
        verify(scenarioGrid, times(1)).appendRow(anyInt(), isA(Scenario.class));
    }

    private Simulation getSimulation() {
        Simulation toReturn = new Simulation();
        SimulationDescriptor simulationDescriptor = toReturn.getSimulationDescriptor();

        simulationDescriptor.addFactMapping(FactIdentifier.INDEX.getName(), FactIdentifier.INDEX, ExpressionIdentifier.INDEX);
        simulationDescriptor.addFactMapping(FactIdentifier.DESCRIPTION.getName(), FactIdentifier.DESCRIPTION, ExpressionIdentifier.DESCRIPTION);

        Scenario scenario = toReturn.addScenario();
        int row = toReturn.getUnmodifiableScenarios().indexOf(scenario);
        scenario.setDescription(null);

        // Add GIVEN Facts
        IntStream.range(2, 4).forEach(id -> {
            ExpressionIdentifier givenExpression = ExpressionIdentifier.create(row + "|" + id, FactMappingType.GIVEN);
            simulationDescriptor.addFactMapping(FactMapping.getPlaceHolder(FactMappingType.GIVEN, id), FactIdentifier.EMPTY, givenExpression);
            scenario.addMappingValue(FactIdentifier.EMPTY, givenExpression, null);
        });

        // Add EXPECTED Facts
        IntStream.range(2, 4).forEach(id -> {
            id += 2; // This is to have consistent labels/names even when adding columns at runtime
            ExpressionIdentifier expectedExpression = ExpressionIdentifier.create(row + "|" + id, FactMappingType.EXPECTED);
            simulationDescriptor.addFactMapping(FactMapping.getPlaceHolder(FactMappingType.EXPECTED, id), FactIdentifier.EMPTY, expectedExpression);
            scenario.addMappingValue(FactIdentifier.EMPTY, expectedExpression, null);
        });
        return toReturn;
    }
}