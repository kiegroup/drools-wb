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

import java.util.Date;
import java.util.function.Function;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.models.datamodel.rule.BaseSingleFieldConstraint;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraint;
import org.drools.workbench.screens.guided.rule.client.widget.EnumDropDown;
import org.guvnor.common.services.workingset.client.factconstraints.customform.CustomFormConfiguration;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.kie.soup.project.datamodel.oracle.DataType;
import org.kie.soup.project.datamodel.oracle.DropDownData;
import org.kie.soup.project.datamodel.oracle.OperatorsOracle;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.CEPOracle;
import org.uberfire.backend.vfs.Path;
import org.uberfire.ext.widgets.common.client.common.DatePicker;
import org.uberfire.ext.widgets.common.client.common.DropDownValueChanged;
import org.uberfire.ext.widgets.common.client.common.SmallLabel;

public class LiteralValueConstraintEditor implements IsWidget {

    private static final String DATE_FORMAT = ApplicationPreferences.getDroolsDateFormat();
    private static final DateTimeFormat DATE_FORMATTER = DateTimeFormat.getFormat(DATE_FORMAT);

    private BaseSingleFieldConstraint constraint;

    private String fieldName;

    private String fieldType;

    private String constraintSanitizedValue;

    private boolean readOnly;

    private CustomFormConfiguration customFormConfiguration;

    private DropDownData dropDownData;

    private Path path;

    private Command afterValueChangedCommand;

    private Function<String, TextBox> textBoxProducer;

    private ClickHandler constraintTypeChoiceClickHandler;

    private Widget editorWidget;

    public LiteralValueConstraintEditor(final BaseSingleFieldConstraint constraint,
                                        final String fieldName,
                                        final String fieldType,
                                        final String constraintSanitizedValue,
                                        final boolean readOnly,
                                        final CustomFormConfiguration customFormConfiguration,
                                        final DropDownData dropDownData,
                                        final Path path,
                                        final Command afterValueChangedCommand,
                                        final Function<String, TextBox> textBoxProducer,
                                        final ClickHandler constraintTypeChoiceClickHandler) {
        this.constraint = constraint;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.constraintSanitizedValue = constraintSanitizedValue;
        this.readOnly = readOnly;
        this.customFormConfiguration = customFormConfiguration;
        this.dropDownData = dropDownData;
        this.path = path;
        this.afterValueChangedCommand = afterValueChangedCommand;
        this.textBoxProducer = textBoxProducer;
        this.constraintTypeChoiceClickHandler = constraintTypeChoiceClickHandler;

        //Custom screen
        setupAsCustomFormConfiguration();

        //Label if read-only
        setupAsReadOnlyLabel();

        //Enumeration (these support multi-select for "in" and "not in", so check before comma separated lists)
        setupAsEnumeration();

        //Comma separated value list (this will become a dedicated Widget but for now a TextBox suffices)
        setUpAsTextBox();

        //Date picker
        setUpAsDatePicker();

        //Default editor for all other literals
        setUpAsDefaultTextBox();
    }

    void setupAsCustomFormConfiguration() {
        if (editorWidget == null && constraint instanceof SingleFieldConstraint) {
            final SingleFieldConstraint con = (SingleFieldConstraint) constraint;
            if (customFormConfiguration != null) {
                final Button btnCustom = new Button(con.getValue(),
                                                    constraintTypeChoiceClickHandler);
                btnCustom.setEnabled(!readOnly);
                editorWidget = btnCustom;
            }
        }
    }

    void setupAsReadOnlyLabel() {
        if (editorWidget == null && readOnly) {
            editorWidget = new SmallLabel(constraintSanitizedValue);
        }
    }

    void setupAsEnumeration() {
        if (editorWidget == null && dropDownData != null) {
            final String operator = constraint.getOperator();
            final boolean multipleSelect = OperatorsOracle.operatorRequiresList(operator);
            editorWidget = new EnumDropDown(constraint.getValue(),
                                            new DropDownValueChanged() {

                                                public void valueChanged(String newText,
                                                                         String newValue) {

                                                    //Prevent recursion once value change has been applied
                                                    if (!newValue.equals(constraint.getValue())) {
                                                        constraint.setValue(newValue);
                                                        afterValueChangedCommand.execute();
                                                    }
                                                }
                                            },
                                            dropDownData,
                                            multipleSelect,
                                            path);
        }
    }

    void setUpAsTextBox() {
        if (editorWidget == null && constraint instanceof SingleFieldConstraint) {
            SingleFieldConstraint sfc = (SingleFieldConstraint) this.constraint;
            final String operator = sfc.getOperator();
            if (OperatorsOracle.operatorRequiresList(operator)) {
                editorWidget = textBoxProducer.apply(DataType.TYPE_STRING);
            }
        }
    }

    void setUpAsDatePicker() {
        final boolean isCEPOperator = CEPOracle.isCEPOperator((constraint).getOperator());
        if (DataType.TYPE_DATE.equals(fieldType) || (DataType.TYPE_THIS.equals(fieldName) && isCEPOperator)) {
            if (editorWidget == null && readOnly) {
                editorWidget = new SmallLabel(constraint.getValue());
            }

            final DatePicker datePicker = new DatePicker(false);

            // Wire up update handler
            datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
                @Override
                public void onValueChange(final ValueChangeEvent<Date> event) {
                    final Date date = datePicker.getValue();
                    final String sDate = (date == null ? null : DATE_FORMATTER.format(datePicker.getValue()));
                    final boolean update = constraint.getValue() == null || !constraint.getValue().equals(sDate);

                    constraint.setValue(sDate);

                    if (update) {
                        afterValueChangedCommand.execute();
                    }
                }
            });

            datePicker.setFormat(DATE_FORMAT);
            datePicker.setValue(getSanitizedDateValue());

            if (editorWidget == null) {
                editorWidget = datePicker;
            }
        }
    }

    void setUpAsDefaultTextBox() {
        if (editorWidget == null) {
            editorWidget = textBoxProducer.apply(fieldType);
        }
    }

    private Date getSanitizedDateValue() {
        if (constraint.getValue() == null) {
            return null;
        }

        try {
            return DATE_FORMATTER.parse(constraint.getValue());
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }

    @Override
    public Widget asWidget() {
        return editorWidget;
    }
}
