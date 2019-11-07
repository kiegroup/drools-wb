/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.client.factories;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.AbstractScenarioSimulationTest;
import org.gwtbootstrap3.client.ui.TextArea;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioExpressionCellTextAreaSingletonDOMElementFactoryTest extends AbstractScenarioSimulationTest {

    @Mock
    private TextArea textAreaMock;

    private ScenarioExpressionCellTextAreaSingletonDOMElementFactory factory;

    @Before
    public void setup() {
       /* factory = spy(ScenarioExpressionCellTextAreaSingletonDOMElementFactory.class);
        when(spy((AbstractTextBoxSingletonDOMElementFactory) factory).createWidget()).thenReturn(textAreaMock);*/
    }

    @Test
    public void test() {

    }
}
