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

package org.drools.workbench.screens.scenariosimulation.client.domelements;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.google.gwt.user.client.ui.AbsolutePanel;
import org.drools.workbench.screens.scenariosimulation.client.events.SetGridCellValueEvent;
import org.drools.workbench.screens.scenariosimulation.client.factories.AbstractFactoriesTest;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridCell;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.model.GridCellValue;

import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.MULTIPART_VALUE;
import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.TEST;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class ScenarioCellTextAreaDOMElementTest extends AbstractFactoriesTest {

    @Mock
    private ScenarioGridCell scenarioGridCellMock;

    @Mock
    private GridCellValue gridCellValueMock;

    private ScenarioCellTextAreaDOMElement scenarioCellTextAreaDOMElement;

    @Before
    public void setup() {
        super.setup();
        when(scenarioGridLayerMock.getDomElementContainer()).thenReturn(new AbsolutePanel());
        when(scenarioGridCellMock.getValue()).thenReturn(gridCellValueMock);
        scenarioCellTextAreaDOMElement = spy(new ScenarioCellTextAreaDOMElement(textAreaMock, scenarioGridLayerMock, scenarioGridMock) {
            {
                this.context = contextMock;
                this.scenarioGridCell = scenarioGridCellMock;
            }
        });
    }

    @Test
    public void flushSameValue() {
        when(gridCellValueMock.getValue()).thenReturn(MULTIPART_VALUE);
        scenarioCellTextAreaDOMElement.flush(MULTIPART_VALUE);
        verify(scenarioGridCellMock, times(1)).setEditingMode(eq(false));
        verify(scenarioCellTextAreaDOMElement, never()).internalFlush(anyString());
    }

    @Test
    public void flushDifferentValue() {
        when(gridCellValueMock.getValue()).thenReturn(TEST);
        scenarioCellTextAreaDOMElement.flush(MULTIPART_VALUE);
        verify(scenarioGridCellMock, times(1)).setEditingMode(eq(false));
        verify(scenarioCellTextAreaDOMElement, times(1)).internalFlush(eq(MULTIPART_VALUE));
    }

    @Test
    public void flushNullString() {
        when(gridCellValueMock.getValue()).thenReturn("");
        scenarioCellTextAreaDOMElement.flush(null);
        verify(scenarioGridCellMock, times(1)).setEditingMode(eq(false));
        verify(scenarioCellTextAreaDOMElement, times(1)).internalFlush(eq(null));
    }

    @Test
    public void flushEmptyStringToNullConversion() {
        when(gridCellValueMock.getValue()).thenReturn("");
        scenarioCellTextAreaDOMElement.flush("");
        verify(scenarioGridCellMock, times(1)).setEditingMode(eq(false));
        // empty strings are converted to null during flush
        verify(scenarioCellTextAreaDOMElement, times(1)).internalFlush(eq(null));
    }

    @Test
    public void internalFlush() {
        scenarioCellTextAreaDOMElement.internalFlush(MULTIPART_VALUE);
        verify(eventBusMock, times(1)).fireEvent(isA(SetGridCellValueEvent.class));
    }

    @Test
    public void testDetachCancelEditMode() {
        scenarioCellTextAreaDOMElement.detach();

        verify(scenarioGridCellMock).setEditingMode(false);
    }
}