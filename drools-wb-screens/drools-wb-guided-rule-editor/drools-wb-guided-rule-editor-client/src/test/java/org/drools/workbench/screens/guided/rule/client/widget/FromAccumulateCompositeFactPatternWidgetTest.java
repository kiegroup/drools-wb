/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.guided.rule.client.widget;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.drools.workbench.models.datamodel.rule.FromAccumulateCompositeFactPattern;
import org.drools.workbench.models.datamodel.rule.RuleModel;
import org.drools.workbench.screens.guided.rule.client.editor.RuleModeller;
import org.drools.workbench.screens.guided.rule.client.resources.GuidedRuleEditorResources;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.html.Text;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.soup.project.datamodel.imports.Import;
import org.kie.soup.project.datamodel.imports.Imports;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.mockito.Mock;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WithClassesToStub({RootPanel.class, Text.class, AnchorElement.class})
@RunWith(GwtMockitoTestRunner.class)
public class FromAccumulateCompositeFactPatternWidgetTest {

    private String[] factTypes;

    private RuleModel ruleModel;

    @Mock
    private AsyncPackageDataModelOracle oracle;

    @Mock
    private RuleModeller ruleModeller;

    @Mock
    private EventBus eventBus;

    @Mock
    private FromAccumulateCompositeFactPattern pattern;

    @Mock
    private ListBox listBox;

    private FromAccumulateCompositeFactPatternWidget fromAccumulateWidget;

    @Before
    public void setUp() throws Exception {
        GwtMockito.useProviderForType(ListBox.class, aClass -> listBox);

        ruleModel = new RuleModel();
        factTypes = new String[2];
        factTypes[0] = "Person";
        factTypes[1] = "Car";

        when(pattern.useFunctionOrCode()).thenReturn(FromAccumulateCompositeFactPattern.USE_FUNCTION);
        when(ruleModeller.getDataModelOracle()).thenReturn(oracle);
        when(ruleModeller.getModel()).thenReturn(ruleModel);
        when(oracle.getFactTypes()).thenReturn(factTypes);

        fromAccumulateWidget = new FromAccumulateCompositeFactPatternWidget(ruleModeller,
                                                                            eventBus,
                                                                            pattern);
    }

    @Test
    public void testShowFactTypeSelectorNoAdditionalImport() throws Exception {
        fromAccumulateWidget.showFactTypeSelector();

        verify(listBox).addItem(GuidedRuleEditorResources.CONSTANTS.Choose());
        verify(listBox).addItem("Person");
        verify(listBox).addItem("Car");
        verify(listBox, times(3)).addItem(anyString());
    }

    @Test
    public void testShowFactTypeSelectorAdditionalImport() throws Exception {
        ruleModel.setImports(new Imports() {{
            addImport(new Import("java.lang.Number"));
        }});

        fromAccumulateWidget.showFactTypeSelector();

        verify(listBox).addItem(GuidedRuleEditorResources.CONSTANTS.Choose());
        verify(listBox).addItem("Person");
        verify(listBox).addItem("Car");
        verify(listBox).addItem("Number");
        verify(listBox, times(4)).addItem(anyString());
    }

    @Test
    public void testShowFactTypeSelectorAdditionalImportNotUnique() throws Exception {
        ruleModel.setImports(new Imports() {{
            addImport(new Import("com.redhat.Person"));
        }});

        fromAccumulateWidget.showFactTypeSelector();

        verify(listBox).addItem(GuidedRuleEditorResources.CONSTANTS.Choose());
        verify(listBox).addItem("Person");
        verify(listBox).addItem("Car");
        verify(listBox, times(3)).addItem(anyString());
    }
}
