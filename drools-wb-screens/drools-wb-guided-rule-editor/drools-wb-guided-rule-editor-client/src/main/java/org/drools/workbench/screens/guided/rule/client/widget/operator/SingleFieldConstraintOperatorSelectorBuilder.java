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

package org.drools.workbench.screens.guided.rule.client.widget.operator;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraint;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraintEBLeftSide;
import org.drools.workbench.screens.guided.rule.client.editor.CEPOperatorsDropdown;
import org.drools.workbench.screens.guided.rule.client.editor.ConstraintValueEditor;
import org.drools.workbench.screens.guided.rule.client.editor.OperatorSelection;
import org.drools.workbench.screens.guided.rule.client.resources.GuidedRuleEditorResources;
import org.drools.workbench.screens.guided.rule.client.widget.FactPatternWidget;
import org.kie.soup.project.datamodel.oracle.OperatorsOracle;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.kie.workbench.common.widgets.client.resources.i18n.HumanReadableConstants;

public class SingleFieldConstraintOperatorSelectorBuilder {

    private SingleFieldConstraint constraint;

    private Supplier<ConstraintValueEditor> constraintValueEditor;

    private FlexTable wrapper;

    private int rowIndex;

    private int columnIndex;

    private FactPatternWidget parent;

    private HorizontalPanel placeholderForDropdown;

    private AsyncPackageDataModelOracle oracle;

    private Function<SingleFieldConstraint, ConstraintValueEditor> constraintValueEditorProducer;

    public void hasConstraint(final SingleFieldConstraint constraint) {
        this.constraint = constraint;
    }

    public void hasConstraintValueEditor(final Supplier<ConstraintValueEditor> constraintValueEditor) {
        this.constraintValueEditor = constraintValueEditor;
    }

    public void hasConstraintValueEditorProducer(final Function<SingleFieldConstraint, ConstraintValueEditor> constraintValueEditorProducer) {
        this.constraintValueEditorProducer = constraintValueEditorProducer;
    }

    public void hasParentWidgetWidget(final FactPatternWidget parent) {
        this.parent = parent;
    }

    public void hasAsyncPackageDataModel(final AsyncPackageDataModelOracle oracle) {
        this.oracle = oracle;
    }

    public void hasPlaceholderForOperatorsDropdown(final HorizontalPanel placeholderForDropdown) {
        this.placeholderForDropdown = placeholderForDropdown;
    }

    public void hasConstraintValueEditorInWrapperAtPosition(final FlexTable wrapper, final int rowIndex, final int columnIndex) {
        this.wrapper = wrapper;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public void build() {

        String fieldName;
        String factType;

        //Connectives Operators are handled in class Connectives
        if (constraint instanceof SingleFieldConstraintEBLeftSide) {
            SingleFieldConstraintEBLeftSide sfexp = (SingleFieldConstraintEBLeftSide) constraint;
            factType = sfexp.getExpressionLeftSide().getPreviousClassType();
            if (factType == null) {
                factType = sfexp.getExpressionLeftSide().getClassType();
            }
            fieldName = sfexp.getExpressionLeftSide().getFieldName();
        } else {
            factType = constraint.getFactType();
            fieldName = constraint.getFieldName();
        }

        oracle.getOperatorCompletions(factType,
                                      fieldName,
                                      operators -> {
                                          final CEPOperatorsDropdown operatorsDropdown = getNewOperatorDropdown(operators,
                                                                                                                constraint);

                                          operatorsDropdown.addPlaceholder(GuidedRuleEditorResources.CONSTANTS.pleaseChoose(),
                                                                           "");

                                          placeholderForDropdown.add(operatorsDropdown);

                                          operatorsDropdown.addValueChangeHandler(event -> onDropDownValueChanged(event));
                                      });
    }

    private void onDropDownValueChanged(ValueChangeEvent<OperatorSelection> event) {
        parent.setModified(true);
        final String selected = event.getValue().getValue();
        final String selectedText = event.getValue().getDisplayText();
        final String originalOperator = constraint.getOperator();

        //Prevent recursion once operator change has been applied
        if (Objects.equals(selected, constraint.getOperator())) {
            return;
        }

        if (Objects.equals(selected, "")) {
            constraint.setOperator(null);
        } else {
            constraint.setOperator(selected);
        }

        if (isValueMissing(selected, constraint.getValue())) {
            constraintValueEditor.get().showError();
        } else {
            constraintValueEditor.get().hideError();
        }

        if (wrapper != null) {
            if (isWidgetForValueNeeded(selectedText)) {
                wrapper.getWidget(rowIndex,
                                  columnIndex).setVisible(true);
            } else {
                wrapper.getWidget(rowIndex,
                                  columnIndex).setVisible(false);
            }
        }

        //If new operator requires a comma separated list and old did not, or vice-versa
        //we need to redraw the ConstraintValueEditor for the constraint
        if (OperatorsOracle.operatorRequiresList(selected) != OperatorsOracle.operatorRequiresList(originalOperator)) {
            if (OperatorsOracle.operatorRequiresList(selected) == false) {
                final String[] oldValueList = constraint.getValue().split(",");
                if (oldValueList.length > 0) {
                    constraint.setValue(oldValueList[0]);
                }
            }

            //Redraw ConstraintValueEditor
            wrapper.setWidget(rowIndex,
                              columnIndex,
                              constraintValueEditorProducer.apply(constraint));
        }
    }

    CEPOperatorsDropdown getNewOperatorDropdown(final String[] operators,
                                                final SingleFieldConstraint constraint) {
        return new CEPOperatorsDropdown(operators,
                                        constraint);
    }

    static boolean isWidgetForValueNeeded(final String constraintOperator) {
        return constraintOperator != null && !constraintOperator.isEmpty() &&
                !Objects.equals(constraintOperator, HumanReadableConstants.INSTANCE.isEqualToNull()) &&
                !Objects.equals(constraintOperator, HumanReadableConstants.INSTANCE.isNotEqualToNull());
    }

    static boolean isValueMissing(final String constraintOperator, final String constraintValue) {
        return OperatorsOracle.isValueRequired(constraintOperator) &&
                (constraintValue == null || constraintValue.isEmpty());
    }
}
