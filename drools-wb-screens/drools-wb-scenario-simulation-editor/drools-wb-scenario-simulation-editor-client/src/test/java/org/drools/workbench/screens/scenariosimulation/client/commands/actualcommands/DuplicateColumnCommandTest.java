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

package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioCellTextAreaSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class DuplicateColumnCommandTest extends AbstractScenarioSimulationCommandTest {

    private List<ScenarioGridColumn> scenarioGridColumnList = new ArrayList<>();

    @Mock
    private ScenarioGridColumn scenarioGridColumn1Mock;

    @Mock
    private ScenarioGridColumn scenarioGridColumn2Mock;

    @Mock
    private ScenarioHeaderMetaData informationHeaderMetaDataMock;

    @Mock
    private ScenarioHeaderMetaData propertyHeaderMetaDataMock;

    @Mock
    private List<GridColumn<?>> gridColumnsMock;

    @Mock
    private ScenarioSimulationContext scenarioSimulationContextMock;

    @Mock
    private ScenarioSimulationContext.Status scenarioSimulationContextStatusMock;

    @Mock
    private FactIdentifier factIdentifierMock;

    private final String TITLE = "TEST-TITLE";
    private final String COLUMN_GROUP = "EXPECT";
    private final String COLUMN_ID = "COLUMN_ID";

    @Before
    public void setup() {
        super.setup();
        command = spy(new DuplicateColumnCommand() {
            @Override
            protected ScenarioGridColumn getScenarioGridColumnLocal(String instanceTitle, String propertyTitle, String columnId, String columnGroup, FactMappingType factMappingType, ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader,
                                                                    ScenarioCellTextAreaSingletonDOMElementFactory factoryCell, String placeHolder) {
                return gridColumnMock;
            }
        });
        scenarioGridColumnList.add(scenarioGridColumn1Mock);
        //scenarioGridColumnList.add(scenarioGridColumn2);
        assertTrue(command.isUndoable());
        when(scenarioGridModelMock.getInstanceScenarioGridColumns(gridColumnMock)).thenReturn(scenarioGridColumnList);
        when(scenarioGridColumn1Mock.getInformationHeaderMetaData()).thenReturn(informationHeaderMetaDataMock);
        when(scenarioGridColumn1Mock.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetaDataMock);
        when(scenarioGridColumn2Mock.getInformationHeaderMetaData()).thenReturn(informationHeaderMetaDataMock);
        when(scenarioGridColumn2Mock.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetaDataMock);
        when(informationHeaderMetaDataMock.getColumnGroup()).thenReturn(COLUMN_GROUP);
        when(informationHeaderMetaDataMock.getTitle()).thenReturn(TITLE);
        when(propertyHeaderMetaDataMock.getTitle()).thenReturn(TITLE);
        when(scenarioGridModelMock.getColumns()).thenReturn(gridColumnsMock);
        when(scenarioSimulationContextMock.getStatus()).thenReturn(scenarioSimulationContextStatusMock);
        when(scenarioSimulationContextStatusMock.getColumnId()).thenReturn(COLUMN_ID);
        when(scenarioGridColumn1Mock.getFactIdentifier()).thenReturn(factIdentifierMock);
    }

    @Test
    public void execute() {
        scenarioSimulationContextLocal.getStatus().setColumnId(COLUMN_ID);
        scenarioSimulationContextLocal.getStatus().setColumnIndex(COLUMN_INDEX);
        command.execute(scenarioSimulationContextLocal);
        int columnPosition = scenarioSimulationContextLocal.getModel().getInstanceLimits(scenarioSimulationContextLocal.getModel().getColumns().indexOf(gridColumnMock)).getMaxRowIndex() + 1;
        AtomicInteger columnPositionAtomic = new AtomicInteger(columnPosition);
        scenarioGridColumnList.forEach(scenarioGridColumn -> {
            System.out.println("Before incrementing:" + columnPositionAtomic);
            verify((DuplicateColumnCommand) command, times(1)).duplicateSingleColumn(scenarioSimulationContextLocal, columnPositionAtomic.getAndIncrement(), scenarioGridColumn);
            System.out.println("After incrementing:" + columnPositionAtomic);
        });
    }

    @Test
    public void duplicateSingleColumn() {
        ((DuplicateColumnCommand) command).duplicateSingleColumn(scenarioSimulationContextLocal, 4, scenarioGridColumn1Mock);
        verify(command, times(1)).getScenarioGridColumnLocal(anyString(), anyString(), anyString(), eq(COLUMN_GROUP), eq(factMappingType), eq(scenarioHeaderTextBoxSingletonDOMElementFactoryTest), eq(scenarioCellTextAreaSingletonDOMElementFactoryTest), eq(ScenarioSimulationEditorConstants.INSTANCE.defineValidType()));
        verify(scenarioGridModelMock, times(1)).duplicateSingleColumn(eq(scenarioGridColumn1Mock), eq(gridColumnMock), eq(4));
    }
}