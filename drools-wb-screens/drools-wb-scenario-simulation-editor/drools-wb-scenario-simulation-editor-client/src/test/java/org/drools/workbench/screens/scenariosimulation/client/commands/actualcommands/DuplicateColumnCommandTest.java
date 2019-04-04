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

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalMatchers.and;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class DuplicateColumnCommandTest extends AbstractScenarioSimulationCommandTest {

    @Before
    public void setup() {
        super.setup();
        command = spy(new DuplicateColumnCommand());
        scenarioSimulationContextLocal.getStatus().setColumnId(COLUMN_ID);
        scenarioSimulationContextLocal.getStatus().setColumnIndex(COLUMN_INDEX);
        assertTrue(command.isUndoable());
    }

    @Test
    public void internalExecute() {
        command.execute(scenarioSimulationContextLocal);
        int columnPosition = scenarioSimulationContextLocal.getModel().getInstanceLimits(scenarioSimulationContextLocal.getModel().getColumns().indexOf(gridColumnMock)).getMaxRowIndex() + 1;
        FactIdentifier factIdentifier = new FactIdentifier(scenarioSimulationContextLocal.getStatus().getColumnId(), factIdentifierMock.getClassName());
        verify((DuplicateColumnCommand) command, times(1)).duplicateSingleColumn(eq(scenarioSimulationContextLocal), eq(columnPosition), eq(gridColumnMock), eq(1), eq(factIdentifier));
        verify((DuplicateColumnCommand) command, times(1)).duplicateSingleColumn(eq(scenarioSimulationContextLocal), eq(columnPosition + 1), eq(gridColumnMock), eq(1), eq(factIdentifier));
    }

    @Test
    public void duplicateSingleColumn() {
        int instancesCount = 1;
        int columnPosition = scenarioSimulationContextLocal.getModel().getInstanceLimits(scenarioSimulationContextLocal.getModel().getColumns().indexOf(gridColumnMock)).getMaxRowIndex() + 1;
        FactIdentifier factIdentifier = new FactIdentifier(scenarioSimulationContextLocal.getStatus().getColumnId(), factIdentifierMock.getClassName());
        ((DuplicateColumnCommand) command).duplicateSingleColumn(scenarioSimulationContextLocal, columnPosition, gridColumnMock, instancesCount, factIdentifier);
        verify(command, times(1)).getScenarioGridColumnLocal(eq(gridColumnMock.getInformationHeaderMetaData().getTitle() + DuplicateColumnCommand.COPY_LABEL + "_" + instancesCount),
                                                             anyString(), anyString(), eq(COLUMN_GROUP), eq(factMappingType),
                                                             eq(scenarioHeaderTextBoxSingletonDOMElementFactoryTest), eq(scenarioCellTextAreaSingletonDOMElementFactoryTest),
                                                             eq(ScenarioSimulationEditorConstants.INSTANCE.defineValidType()));
        verify(scenarioGridModelMock, times(1)).duplicateSingleColumn(eq(gridColumnMock), and(not(eq(gridColumnMock)), notNull(ScenarioGridColumn.class)), eq(columnPosition));
    }

}