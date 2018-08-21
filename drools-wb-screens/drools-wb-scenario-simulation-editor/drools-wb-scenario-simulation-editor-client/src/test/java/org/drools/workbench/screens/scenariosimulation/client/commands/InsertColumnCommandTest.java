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

package org.drools.workbench.screens.scenariosimulation.client.commands;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class InsertColumnCommandTest {

    @Mock
    private ScenarioGridModel mockScenarioGridModel;

    @Mock
    private ScenarioGridPanel mockScenarioGridPanel;
    @Mock
    private ScenarioGridLayer mockScenarioGridLayer;

    private final int COLUMN_INDEX = 3;

    private final String COLUMN_ID = "COLUMN ID";

    private final String COLUMN_TITLE = "COLUMN TITLE";

    private InsertColumnCommand insertColumnCommand;

    @Before
    public void setup() {
        insertColumnCommand = new InsertColumnCommand(COLUMN_INDEX, mockScenarioGridModel, COLUMN_ID, COLUMN_TITLE, mockScenarioGridPanel, mockScenarioGridLayer);
    }

    @Test
    public void execute() {
        insertColumnCommand.execute();
        verify(mockScenarioGridModel, times(1)).insertColumn(eq(COLUMN_INDEX), anyObject());
    }
}