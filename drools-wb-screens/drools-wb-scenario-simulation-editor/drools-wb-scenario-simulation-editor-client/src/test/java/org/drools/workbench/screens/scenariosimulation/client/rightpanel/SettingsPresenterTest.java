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
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;
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

    private static final String FILE_NAME = "FILE_NAME";
    private static final String KIE_SESSION = "KIE_SESSION";
    private static final String KIE_BASE = "KIE_BASE";
    private static final String RULE_FLOW_GROUP = "RULE_FLOW_GROUP";
    private static final String DMO_SESSION = "DMO_SESSION";
    private static final String DMN_FILE_PATH = "DMN_FILE_PATH";
    private static final String DMN_NAMESPACE = "DMN_NAMESPACE";
    private static final String DMN_NAME = "DMN_NAME";

    @Mock
    private SettingsView settingsViewMock;

    @Mock
    private SimulationDescriptor simulationDescriptorMock;

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
        when(settingsViewMock.getDmoSession()).thenReturn(dmoSessionMock);
        when(settingsViewMock.getDmnSettings()).thenReturn(dmnSettingsMock);
        when(settingsViewMock.getDmnFileLabel()).thenReturn(dmnModelLabelMock);
        when(settingsViewMock.getDmnFilePath()).thenReturn(dmnFilePathMock);
        when(settingsViewMock.getDmnNamespaceLabel()).thenReturn(dmnNamespaceLabelMock);
        when(settingsViewMock.getDmnNamespace()).thenReturn(dmnNamespaceMock);
        when(settingsViewMock.getDmnNameLabel()).thenReturn(dmnNameLabelMock);
        when(settingsViewMock.getDmnName()).thenReturn(dmnNameMock);

        when(simulationDescriptorMock.getFileName()).thenReturn(FILE_NAME);
        when(simulationDescriptorMock.getKieSession()).thenReturn(KIE_SESSION);
        when(simulationDescriptorMock.getKieBase()).thenReturn(KIE_BASE);
        when(simulationDescriptorMock.getRuleFlowGroup()).thenReturn(RULE_FLOW_GROUP);
        when(simulationDescriptorMock.getDmoSession()).thenReturn(DMO_SESSION);
        when(simulationDescriptorMock.getDmnFilePath()).thenReturn(DMN_FILE_PATH);
        when(simulationDescriptorMock.getDmnNamespace()).thenReturn(DMN_NAMESPACE);
        when(simulationDescriptorMock.getDmnName()).thenReturn(DMN_NAME);

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
        settingsPresenter.setScenarioType(ScenarioSimulationModel.Type.RULE, simulationDescriptorMock);
        verify(settingsViewMock, times(1)).getScenarioType();
        verify(scenarioTypeMock, times(1)).setInnerText(eq(ScenarioSimulationModel.Type.RULE.name()));
        verify(settingsPresenter, times(1)).setRuleSettings(simulationDescriptorMock);
    }

    @Test
    public void setScenarioTypeDMN() {
        settingsPresenter.setScenarioType(ScenarioSimulationModel.Type.DMN, simulationDescriptorMock);
        verify(settingsViewMock, times(1)).getScenarioType();
        verify(scenarioTypeMock, times(1)).setInnerText(eq(ScenarioSimulationModel.Type.DMN.name()));
        verify(settingsPresenter, times(1)).setDMNSettings(simulationDescriptorMock);
    }

    @Test
    public void setRuleSettings() {
        settingsPresenter.setRuleSettings(simulationDescriptorMock);
        verify(settingsViewMock, times(1)).getDmnSettings();
        verify(dmnSettingsMock, times(1)).removeFromParent();
        verify(settingsViewMock, times(1)).getDmoSession();
        verify(dmoSessionMock, times(1)).setValue(eq(DMO_SESSION));
        verify(settingsViewMock, times(1)).getKieBase();
        verify(kieBaseMock, times(1)).setValue(eq(KIE_BASE));
        verify(settingsViewMock, times(1)).getKieSession();
        verify(kieSessionMock, times(1)).setValue(eq(KIE_SESSION));
        verify(settingsViewMock, times(1)).getRuleFlowGroup();
        verify(ruleFlowGroupMock, times(1)).setValue(eq(RULE_FLOW_GROUP));
    }

    @Test
    public void setDMNSettings() {
        settingsPresenter.setDMNSettings(simulationDescriptorMock);
        verify(settingsViewMock, times(1)).getRuleSettings();
        verify(ruleSettingsMock, times(1)).removeFromParent();
        verify(settingsViewMock, times(1)).getDmnFilePath();
        verify(dmnFilePathMock, times(1)).setInnerText(eq(DMN_FILE_PATH));
        verify(settingsViewMock, times(1)).getDmnName();
        verify(dmnNameMock, times(1)).setInnerText(eq(DMN_NAME));
        verify(settingsViewMock, times(1)).getDmnNamespace();
        verify(dmnNamespaceMock, times(1)).setInnerText(eq(DMN_NAMESPACE));
    }
}