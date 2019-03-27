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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class SettingsPresenterTest extends AbstractSettingsTest {

    private SettingsPresenter settingsPresenter;

    @Mock
    private SettingsView settingsViewMock;


    @Before
    public void setup() {
        when(settingsViewMock.getNameLabel()).thenReturn(nameLabelMock);
        when(settingsViewMock.getFileName()).thenReturn(fileNameMock);
        when(settingsViewMock.getTypeLabel()).thenReturn(typeLabelMock);
        when(settingsViewMock.getScenarioType()).thenReturn(scenarioTypeMock);
        when(settingsViewMock.getRuleSettings()).thenReturn(ruleSettingsMock);
        when(settingsViewMock.getKieSession()).thenReturn(kieSessionMock);
        when(settingsViewMock.getKieBase()).thenReturn(kieBaseMock);
        when(settingsViewMock.getRuleFlowGroup()).thenReturn(ruleFlowGroupMock);
        when(settingsViewMock.getDmnSettings()).thenReturn(dmnSettingsMock);
        when(settingsViewMock.getDmnFileLabel()).thenReturn(dmnModelLabelMock);
        when(settingsViewMock.getDmnFilePath()).thenReturn(dmnModelPathMock);
        when(settingsViewMock.getDmnNamespaceLabel()).thenReturn(dmnNamespaceLabelMock);
        when(settingsViewMock.getDmnNamespace()).thenReturn(dmnNamespaceMock);
        when(settingsViewMock.getDmnNameLabel()).thenReturn(dmnNameLabelMock);
        when(settingsViewMock.getDmnName()).thenReturn(dmnNameMock);

        this.settingsPresenter = spy(new SettingsPresenter(settingsViewMock) {
            {
            }
        });
    }

    @Test
    public void onSetup() {
        settingsPresenter.setup();
        verify(settingsViewMock, times(1)).init(settingsPresenter);
    }

    @Test
    public void getTitle() {
        assertEquals(ScenarioSimulationEditorConstants.INSTANCE.settings(), settingsPresenter.getTitle());
    }

    @Test
    public void setScenarioTypeRULE() {
        settingsPresenter.setScenarioType(ScenarioSimulationModel.Type.RULE);
        verify(settingsViewMock, times(1)).getScenarioType();
        verify(scenarioTypeMock, times(1)).setInnerText(eq(ScenarioSimulationModel.Type.RULE.name()));
        verify(settingsPresenter, times(1)).setRuleSettings();
    }

    @Test
    public void setScenarioTypeDMN() {
        settingsPresenter.setScenarioType(ScenarioSimulationModel.Type.DMN);
        verify(settingsViewMock, times(1)).getScenarioType();
        verify(scenarioTypeMock, times(1)).setInnerText(eq(ScenarioSimulationModel.Type.DMN.name()));
        verify(settingsPresenter, times(1)).setDMNSettings();
    }

    @Test
    public void setRuleSettings() {
        settingsPresenter.setRuleSettings();
        verify(settingsViewMock, times(1)).getDmnSettings();
        verify(dmnSettingsMock, times(1)).removeFromParent();
    }

    @Test
    public void setDMNSettings() {
        settingsPresenter.setDMNSettings();
        verify(settingsViewMock, times(1)).getRuleSettings();
        verify(ruleSettingsMock, times(1)).removeFromParent();
    }

}