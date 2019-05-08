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

package org.drools.workbench.screens.scenariosimulation.client.dropdown;

import com.google.gwt.event.dom.client.KeyUpEvent;
import elemental2.dom.DOMTokenList;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLOptionElement;
import elemental2.dom.HTMLSelectElement;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.uberfire.client.views.pfly.selectpicker.JQuerySelectPicker;
import org.uberfire.client.views.pfly.selectpicker.JQuerySelectPickerEvent;
import org.uberfire.client.views.pfly.selectpicker.JQuerySelectPickerTarget;

import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.DEFAULT_VALUE;
import static org.drools.workbench.screens.scenariosimulation.client.TestProperties.KIEASSETSDROPDOWNVIEW_SELECT;
import static org.drools.workbench.screens.scenariosimulation.client.dropdown.ScenarioSimulationDropdownView.HIDDEN_CSS_CLASS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.kie.workbench.common.widgets.client.resources.i18n.KieWorkbenchWidgetsConstants.KieAssetsDropdownView_Select;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScenarioSimulationDropdownViewTest extends AbstractScenarioSimulationDropdownTest {

    @Mock
    private HTMLSelectElement nativeSelectMock;

    @Mock
    private DOMTokenList nativeSelectClassListMock;

    @Mock
    private HTMLInputElement fallbackInputMock;

    @Mock
    private DOMTokenList fallbackInputClassListMock;

    @Mock
    private HTMLOptionElement htmlOptionElementMock;

    @Mock
    private HTMLOptionElement htmlOptionElementClonedMock;

    @Mock
    private TranslationService translationServiceMock;

    @Mock
    private ScenarioSimulationDropdown presenterMock;

    @Mock
    private JQuerySelectPicker dropdownMock;

    @Mock
    private JQuerySelectPicker.CallbackFunction onDropdownChangeHandlerMock;

    @Mock
    private HTMLOptionElement entryOptionMock;

    @Mock
    private HTMLOptionElement selectOptionMock;

    private ScenarioSimulationDropdownView scenarioSimulationDropdownView;

    @Before
    public void setup() {
        super.setup();
        fallbackInputMock.classList = fallbackInputClassListMock;
        nativeSelectMock.classList = nativeSelectClassListMock;
        when(htmlOptionElementMock.cloneNode(eq(false))).thenReturn(htmlOptionElementClonedMock);
        when(translationServiceMock.format(eq(KieAssetsDropdownView_Select))).thenReturn(KIEASSETSDROPDOWNVIEW_SELECT);
        scenarioSimulationDropdownView = spy(new ScenarioSimulationDropdownView(nativeSelectMock,
                                                                                fallbackInputMock,
                                                                                htmlOptionElementMock,
                                                                                translationServiceMock) {
            {
                this.presenter = presenterMock;
            }

            @Override
            protected JQuerySelectPicker dropdown() {
                return dropdownMock;
            }

            @Override
            protected JQuerySelectPicker.CallbackFunction getOnDropdownChangeHandler() {
                return onDropdownChangeHandlerMock;
            }

            @Override
            protected HTMLOptionElement entryOption(String entry) {
                return entryOptionMock;
            }

        });
    }

    @Test
    public void init() {
        scenarioSimulationDropdownView.init();
        assertFalse(nativeSelectMock.hidden);
        assertTrue(fallbackInputMock.hidden);
        verify(scenarioSimulationDropdownView, times(1)).dropdown();
        verify(scenarioSimulationDropdownView, times(1)).getOnDropdownChangeHandler();
        verify(dropdownMock, times(1)).on(eq("hidden.bs.select"), eq(onDropdownChangeHandlerMock));
    }

    @Test
    public void addValue() {
        scenarioSimulationDropdownView.addValue(DEFAULT_VALUE);
        verify(scenarioSimulationDropdownView, times(1)).entryOption(eq(DEFAULT_VALUE));
        verify(nativeSelectMock, times(1)).appendChild(eq(entryOptionMock));
    }

    @Test
    public void clear() {
        doReturn(selectOptionMock).when(scenarioSimulationDropdownView).selectOption();
        scenarioSimulationDropdownView.clear();
        verify(scenarioSimulationDropdownView, times(1)).removeChildren(eq(nativeSelectMock));
        verify(scenarioSimulationDropdownView, times(1)).selectOption();
        verify(nativeSelectMock, times(1)).appendChild(eq(selectOptionMock));
        verify(scenarioSimulationDropdownView, times(1)).refreshSelectPicker();
    }

    @Test
    public void initialize() {
        scenarioSimulationDropdownView.initialize();
        assertEquals("", fallbackInputMock.value);
        verify(scenarioSimulationDropdownView, times(1)).dropdown();
        verify(dropdownMock, times(1)).selectpicker(eq("val"), eq(""));
    }

    @Test
    public void refreshSelectPicker() {
        scenarioSimulationDropdownView.refreshSelectPicker();
        verify(scenarioSimulationDropdownView, times(1)).dropdown();
        verify(dropdownMock, times(1)).selectpicker(eq("refresh"));
    }

    @Test
    public void getValue() {
        fallbackInputMock.value = DEFAULT_VALUE;
        assertEquals(DEFAULT_VALUE, scenarioSimulationDropdownView.getValue());
    }

    @Test
    public void enableDropdownMode() {
        scenarioSimulationDropdownView.enableDropdownMode();
        verify(fallbackInputClassListMock, times(1)).add(eq(HIDDEN_CSS_CLASS));
        verify(nativeSelectClassListMock, times(1)).remove(eq(HIDDEN_CSS_CLASS));
        verify(scenarioSimulationDropdownView, times(1)).dropdown();
        verify(dropdownMock, times(1)).selectpicker(eq("show"));
    }

    @Test
    public void onFallbackInputChange() {
        scenarioSimulationDropdownView.onFallbackInputChange(mock(KeyUpEvent.class));
        verify(presenterMock, times(1)).onValueChanged();
    }

    @Test
    public void selectOption() {
        final HTMLOptionElement retrieved = scenarioSimulationDropdownView.selectOption();
        assertNotNull(retrieved);
        assertEquals(KIEASSETSDROPDOWNVIEW_SELECT, retrieved.text);
        assertEquals("", retrieved.value);
    }

    @Test
    public void onDropdownChangeHandlerMethod() {
        JQuerySelectPickerTarget targetMock = mock(JQuerySelectPickerTarget.class);
        targetMock.value = DEFAULT_VALUE;
        JQuerySelectPickerEvent eventMock = mock(JQuerySelectPickerEvent.class);
        eventMock.target = targetMock;
        scenarioSimulationDropdownView.onDropdownChangeHandlerMethod(eventMock);
        assertEquals(targetMock.value, fallbackInputMock.value);
        verify(presenterMock, times(1)).onValueChanged();
    }
}