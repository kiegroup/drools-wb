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

package org.drools.workbench.screens.scenariosimulation.client.handlers;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class SourceTypeSelectorTest {

    @Mock
    private TitledAttachmentFileWidget uploadWidgetMock;

    private SourceTypeSelector sourceTypeSelector;

    @Before
    public void setUp() throws Exception {
        sourceTypeSelector = spy(new SourceTypeSelector(uploadWidgetMock));
    }

    @Test
    public void onValueChange() {
        reset(uploadWidgetMock);
        ValueChangeEvent eventMock = mock(ValueChangeEvent.class);
        doReturn(false).when(sourceTypeSelector).isDMNOrPMMLSelected();
        sourceTypeSelector.onValueChange(eventMock);
        verify(uploadWidgetMock, never()).updateAssetList("dmn");
        verify(uploadWidgetMock, never()).updateAssetList("pmml");
        doReturn(true).when(sourceTypeSelector).isDMNOrPMMLSelected();
        doReturn(ScenarioSimulationModel.Type.DMN).when(sourceTypeSelector).getSelectedType();
        sourceTypeSelector.onValueChange(eventMock);
        verify(uploadWidgetMock, times(1)).updateAssetList("dmn");
        doReturn(ScenarioSimulationModel.Type.PMML).when(sourceTypeSelector).getSelectedType();
        sourceTypeSelector.onValueChange(eventMock);
        verify(uploadWidgetMock, times(1)).updateAssetList("pmml");
    }

    @Test
    public void validateDMO() {
        commonValidate(ScenarioSimulationModel.Type.RULE, false, true);
    }

    @Test
    public void validateInvalidDMN() {
        commonValidate(ScenarioSimulationModel.Type.DMN, false, false);
    }

    @Test
    public void validateValidDMN() {
        commonValidate(ScenarioSimulationModel.Type.DMN, true, true);
    }

    @Test
    public void validateInvalidPMML() {
        commonValidate(ScenarioSimulationModel.Type.PMML, false, false);
    }

    @Test
    public void validateValidPMML() {
        commonValidate(ScenarioSimulationModel.Type.PMML, true, true);
    }

    @Test
    public void addRadioButtons() {
        for (ScenarioSimulationModel.Type type : ScenarioSimulationModel.Type.values()) {
            reset(uploadWidgetMock);
            reset(sourceTypeSelector);
            boolean dmnOrPMMLSelected = !type.equals(ScenarioSimulationModel.Type.RULE);
            doReturn(dmnOrPMMLSelected).when(sourceTypeSelector).isDMNOrPMMLSelected();
            doReturn(type).when(sourceTypeSelector).getSelectedType();
            sourceTypeSelector.addRadioButtons();
            assertEquals(3, sourceTypeSelector.radioButtonList.size());
            verify(uploadWidgetMock, times(1)).setVisible(type.name().toLowerCase(), dmnOrPMMLSelected);
        }
    }

    private void commonValidate(ScenarioSimulationModel.Type type, boolean validate, boolean expected) {
        boolean isDMNSelected = !type.equals(ScenarioSimulationModel.Type.RULE);
        doReturn(isDMNSelected).when(sourceTypeSelector).isDMNOrPMMLSelected();
        doReturn(type).when(sourceTypeSelector).getSelectedType();
        doReturn(validate).when(uploadWidgetMock).validate(type.name().toLowerCase());
        boolean retrieved = sourceTypeSelector.validate();
        if (isDMNSelected) {
            verify(uploadWidgetMock, times(1)).validate(type.name().toLowerCase());
        }
        if (expected) {
            assertTrue(retrieved);
        } else {
            assertFalse(retrieved);
        }
    }
}