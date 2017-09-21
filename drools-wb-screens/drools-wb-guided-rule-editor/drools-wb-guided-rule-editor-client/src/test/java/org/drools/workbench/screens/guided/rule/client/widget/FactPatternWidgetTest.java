/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.rule.client.widget;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.appformer.project.datamodel.oracle.DataType;
import org.appformer.project.datamodel.oracle.FieldAccessorsAndMutators;
import org.appformer.project.datamodel.oracle.ModelField;
import org.appformer.project.datamodel.oracle.OperatorsOracle;
import org.drools.workbench.models.datamodel.rule.FactPattern;
import org.drools.workbench.models.datamodel.rule.FieldConstraint;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraint;
import org.drools.workbench.screens.guided.rule.client.editor.CEPOperatorsDropdown;
import org.drools.workbench.screens.guided.rule.client.editor.RuleModeller;
import org.drools.workbench.screens.guided.rule.client.editor.factPattern.Connectives;
import org.drools.workbench.screens.guided.rule.client.resources.images.GuidedRuleEditorImages508;
import org.jboss.errai.common.client.api.Caller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.datamodel.service.IncrementalDataModelService;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracleImpl;
import org.kie.workbench.common.widgets.client.datamodel.OracleUtils;
import org.mockito.Mock;
import org.uberfire.mocks.CallerMock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@WithClassesToStub({FlexTable.class, GuidedRuleEditorImages508.class, CEPOperatorsDropdown.class})
@RunWith(GwtMockitoTestRunner.class)
public class FactPatternWidgetTest {

    private Caller<IncrementalDataModelService> serviceCaller;

    @Mock
    private CEPOperatorsDropdown operatorsDropdown;

    @Mock
    private IncrementalDataModelService service;

    @Mock
    private AsyncPackageDataModelOracleImpl oracle;

    @Mock
    private RuleModeller ruleModeller;

    @Mock
    private EventBus eventBus;

    @Mock
    private FactPattern pattern;

    @Mock
    private SingleFieldConstraint fieldConstraint;

    private boolean canBind = true;

    private boolean readOnly = false;

    private FactPatternWidget factPatternWidget;

    private Connectives connectives;

    @Before
    public void setUp() throws Exception {

        ApplicationPreferences.setUp(new HashMap() {{
            put(ApplicationPreferences.DATE_FORMAT,
                "dd/MM/YYYY");
        }});

        serviceCaller = new CallerMock<>(service);
        oracle = new AsyncPackageDataModelOracleImpl(serviceCaller,
                                                     null);

        final ModelField[] modelFields = new ModelField[]{
                new ModelField("street",
                               String.class.getName(),
                               ModelField.FIELD_CLASS_TYPE.REGULAR_CLASS,
                               ModelField.FIELD_ORIGIN.DECLARED,
                               FieldAccessorsAndMutators.BOTH,
                               DataType.TYPE_STRING),
                new ModelField("number",
                               Integer.class.getName(),
                               ModelField.FIELD_CLASS_TYPE.REGULAR_CLASS,
                               ModelField.FIELD_ORIGIN.DECLARED,
                               FieldAccessorsAndMutators.BOTH,
                               DataType.TYPE_NUMERIC_INTEGER)};

        Map<String, ModelField[]> fields = new HashMap<>();
        fields.put("org.Address",
                   modelFields);

        oracle.addModelFields(fields);

        connectives = spy(new Connectives(ruleModeller,
                                          eventBus,
                                          pattern,
                                          false));

        doReturn("org.Address").when(fieldConstraint).getFactType();
        doReturn("street").when(fieldConstraint).getFieldName();
        doReturn(oracle).when(connectives).getDataModelOracle();
        doReturn(oracle).when(ruleModeller).getDataModelOracle();
        doReturn(Stream.of(fieldConstraint).toArray(FieldConstraint[]::new)).when(pattern).getFieldConstraints();

        factPatternWidget = spy(new FactPatternWidget(ruleModeller,
                                                      eventBus,
                                                      pattern,
                                                      canBind,
                                                      readOnly));

        doReturn(connectives).when(factPatternWidget).getConnectives();
        doReturn(operatorsDropdown).when(factPatternWidget).getNewOperatorDropdown(any(),
                                                                                   eq(fieldConstraint));
    }

    @Test
    public void testOperatorCompletionsString() throws Exception {
        doReturn("org.Address").when(fieldConstraint).getFactType();
        doReturn("street").when(fieldConstraint).getFieldName();

        factPatternWidget.drawConstraints(Collections.singletonList(fieldConstraint),
                                          pattern);

        verify(factPatternWidget).getNewOperatorDropdown(OracleUtils.joinArrays(OperatorsOracle.STRING_OPERATORS,
                                                                                OperatorsOracle.EXPLICIT_LIST_OPERATORS),
                                                         fieldConstraint);
    }

    @Test
    public void testOperatorCompletionsInteger() throws Exception {
        doReturn("org.Address").when(fieldConstraint).getFactType();
        doReturn("number").when(fieldConstraint).getFieldName();

        factPatternWidget.drawConstraints(Collections.singletonList(fieldConstraint),
                                          pattern);

        verify(factPatternWidget).getNewOperatorDropdown(OracleUtils.joinArrays(OperatorsOracle.COMPARABLE_OPERATORS,
                                                                                OperatorsOracle.EXPLICIT_LIST_OPERATORS),
                                                         fieldConstraint);
    }
}
