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

package org.drools.workbench.screens.scenariosimulation.client.factories;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.events.SetCellValueEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(LienzoMockitoTestRunner.class)
public class ScenarioCellTextAreaDOMElementTest extends AbstractFactoriesTest {

    private ScenarioCellTextAreaDOMElement scenarioCellTextAreaDOMElement;

    @Before
    public void setup() {
        super.setup();
        scenarioCellTextAreaDOMElement = spy(new ScenarioCellTextAreaDOMElement(textAreaMock, scenarioGridLayerMock, scenarioGridMock) {
            {
                this.context = contextMock;
            }
        });
    }

    @Test
    public void flushSameValue() {
        scenarioCellTextAreaDOMElement.originalValue = "";
        scenarioCellTextAreaDOMElement.flush("");
        verify(eventBusMock, never()).fireEvent(isA(SetCellValueEvent.class));
    }

    @Test
    public void flushDifferentValue() {
        scenarioCellTextAreaDOMElement.originalValue = "";
        scenarioCellTextAreaDOMElement.flush("TEST");
        verify(eventBusMock, times(1)).fireEvent(isA(SetCellValueEvent.class));
    }
}