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

package org.drools.workbench.screens.guided.rule.client.widget.constraint;

import java.util.function.Function;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraint;
import org.drools.workbench.screens.guided.rule.client.widget.EnumDropDown;
import org.guvnor.common.services.workingset.client.factconstraints.customform.CustomFormConfiguration;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.html.Text;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.soup.project.datamodel.oracle.DataType;
import org.kie.soup.project.datamodel.oracle.DropDownData;
import org.mockito.Mock;
import org.uberfire.backend.vfs.Path;
import org.uberfire.ext.widgets.common.client.common.DatePicker;
import org.uberfire.ext.widgets.common.client.common.SmallLabel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@WithClassesToStub({DateTimeFormat.class, DatePicker.class, Text.class})
@RunWith(GwtMockitoTestRunner.class)
public class LiteralValueConstraintEditorTest {

    private final String SANITIZED_VALUE = "abcd";

    @Mock
    private SingleFieldConstraint constraint;

    @Mock
    private CustomFormConfiguration customFormConfiguration;

    @Mock
    private DropDownData dropDownData;

    @Mock
    private Path path;

    @Mock
    private Command afterValueChangedCommand;

    @Mock
    private Function<String, TextBox> textBoxProducer;

    @Mock
    private ClickHandler constraintTypeChoiceClickHandler;

    private LiteralValueConstraintEditor testedEditor;

    private void mockEditor() {
        mockEditor(false);
    }

    private void mockEditor(final boolean readOnly) {
        mockEditor("", "", readOnly);
    }

    private void mockEditor(final String fieldName,
                            final String fieldType,
                            final boolean readOnly) {

        if (dropDownData != null) {
            doReturn(new String[]{}).when(dropDownData).getFixedList();
        }

        testedEditor = new LiteralValueConstraintEditor(constraint,
                                                        fieldName,
                                                        fieldType,
                                                        SANITIZED_VALUE,
                                                        readOnly,
                                                        customFormConfiguration,
                                                        dropDownData,
                                                        path,
                                                        afterValueChangedCommand,
                                                        textBoxProducer,
                                                        constraintTypeChoiceClickHandler);
    }

    @Test
    public void testCustomConfiguration() throws Exception {
        mockEditor();

        assertThat(testedEditor.asWidget()).isInstanceOf(Button.class);
    }

    @Test
    public void testReadOnly() throws Exception {
        customFormConfiguration = null;
        mockEditor(true);

        assertThat(testedEditor.asWidget()).isInstanceOf(SmallLabel.class);
    }

    @Test
    public void testEnumDropDwon() throws Exception {
        customFormConfiguration = null;
        mockEditor();

        assertThat(testedEditor.asWidget()).isInstanceOf(EnumDropDown.class);
    }

    @Test
    public void testTextBoxRequiresList() throws Exception {
        customFormConfiguration = null;
        dropDownData = null;
        final String operator = "in";
        doReturn(operator).when(constraint).getOperator();
        mockEditor("age", "Integer", false);

        verify(textBoxProducer).apply(DataType.TYPE_STRING);
    }

    @Test
    public void testTextBoxNotRequiresList() throws Exception {
        customFormConfiguration = null;
        dropDownData = null;
        final String operator = "==";
        doReturn(operator).when(constraint).getOperator();
        mockEditor("age", DataType.TYPE_NUMERIC_INTEGER, false);

        verify(textBoxProducer).apply(DataType.TYPE_NUMERIC_INTEGER);
    }

    @Test
    public void testReadOnlyDate() throws Exception {
        customFormConfiguration = null;
        dropDownData = null;
        final String operator = "==";
        doReturn(operator).when(constraint).getOperator();
        mockEditor("age", DataType.TYPE_DATE, true);

        assertThat(testedEditor.asWidget()).isInstanceOf(SmallLabel.class);
    }

    @Test
    public void testDate() throws Exception {
        customFormConfiguration = null;
        dropDownData = null;
        final String operator = "==";
        doReturn(operator).when(constraint).getOperator();
        mockEditor("age", DataType.TYPE_DATE, false);

        assertThat(testedEditor.asWidget()).isInstanceOf(DatePicker.class);
    }
}
