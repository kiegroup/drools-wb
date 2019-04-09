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
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationBuilders;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class SetPropertyHeaderCommandTest extends AbstractSelectedColumnCommandTest {

    @Before
    public void setup() {
        super.setup();
        command = spy(new SetPropertyHeaderCommand() {

            @Override
            protected ScenarioGridColumn getScenarioGridColumnLocal(ScenarioSimulationBuilders.HeaderBuilder headerBuilder, ScenarioSimulationContext context) {
                return gridColumnMock;
            }

            @Override
            protected void setPropertyHeader(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String value, String propertyClass) {
                //Do nothing
            }
        });
        scenarioSimulationContextLocal.getStatus().setFullPackage(FULL_PACKAGE);
        scenarioSimulationContextLocal.getStatus().setValue(VALUE);
        scenarioSimulationContextLocal.getStatus().setValueClassName(VALUE_CLASS_NAME);
        assertTrue(command.isUndoable());
     }

    @Test
    public void executeIfSelected() {
        ((SetPropertyHeaderCommand) command).executeIfSelectedColumn(scenarioSimulationContextLocal, gridColumnMock);
        verify((SetPropertyHeaderCommand) command, times(1)).setPropertyHeader(eq(scenarioSimulationContextLocal), eq(gridColumnMock), eq(VALUE), eq(VALUE_CLASS_NAME));
    }

}