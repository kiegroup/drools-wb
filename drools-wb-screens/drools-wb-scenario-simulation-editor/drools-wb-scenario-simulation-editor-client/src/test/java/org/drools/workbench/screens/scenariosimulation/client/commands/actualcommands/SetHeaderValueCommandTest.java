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

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class SetHeaderValueCommandTest extends AbstractScenarioSimulationCommandTest {

    private SetHeaderValueCommand setHeaderValueCommand;

    @Before
    public void setup() {
        super.setup();
        setHeaderValueCommand = spy(new SetHeaderValueCommand());
        scenarioSimulationContext.setRowIndex(ROW_INDEX);
        scenarioSimulationContext.setColumnIndex(COLUMN_INDEX);
        scenarioSimulationContext.setCellValue(VALUE);
    }

    @Test
    public void executeNotValid() {
        when(scenarioGridModelMock.validateHeaderUpdate(eq(VALUE), eq(ROW_INDEX), eq(COLUMN_INDEX))).thenReturn(false);
        setHeaderValueCommand.execute(scenarioSimulationContext);
        verify(scenarioGridModelMock, never()).updateHeader(eq(COLUMN_INDEX), eq(ROW_INDEX), eq(VALUE));
    }

    @Test
    public void executeValid() {
        when(scenarioGridModelMock.validateHeaderUpdate(eq(VALUE), eq(ROW_INDEX), eq(COLUMN_INDEX))).thenReturn(true);
        setHeaderValueCommand.execute(scenarioSimulationContext);
        verify(scenarioGridModelMock, times(1)).updateHeader(eq(COLUMN_INDEX), eq(ROW_INDEX), eq(VALUE));
    }
}