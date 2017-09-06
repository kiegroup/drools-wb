/*
 * Copyright 2010 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.testscenario.client;

import java.util.Arrays;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.drools.workbench.models.testscenarios.shared.VerifyFact;
import org.drools.workbench.models.testscenarios.shared.VerifyField;
import org.drools.workbench.screens.testscenario.client.resources.i18n.TestScenarioConstants;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.base.constants.ColorType;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.uberfire.ext.widgets.common.client.common.popups.YesNoCancelPopup;

public class VerifyFactWidget extends CellTable<VerifyField> {

    private boolean showResults;
    private AsyncPackageDataModelOracle oracle;
    private Scenario scenario;
    private ExecutionTrace executionTrace;

    public VerifyFactWidget(final VerifyFact verifyFact,
                            final Scenario sc,
                            final AsyncPackageDataModelOracle oracle,
                            final ExecutionTrace executionTrace,
                            final boolean showResults) {

        this.showResults = showResults;
        this.oracle = oracle;
        this.scenario = sc;
        this.executionTrace = executionTrace;

        setStriped(true);
        setCondensed(true);
        setBordered(true);
        setWidth("100%");

        final TextCell nameCell = new TextCell();
        Column<VerifyField, String> nameColumn = new Column<VerifyField, String>(nameCell) {
            @Override
            public String getValue(VerifyField model) {
                return model.getFieldName();
            }
        };

        addColumn(nameColumn,
                  "Fields of the fact: " + verifyFact.getName());

        SelectionCell operatorCell = new SelectionCell(Arrays.asList(TestScenarioConstants.INSTANCE.equalsScenario(),
                                                                     TestScenarioConstants.INSTANCE.doesNotEqualScenario()));
        Column<VerifyField, String> operatorColumn = new Column<VerifyField, String>(operatorCell) {
            @Override
            public String getValue(VerifyField verifyField) {
                if ("==".compareTo(verifyField.getOperator()) == 0) {
                    return TestScenarioConstants.INSTANCE.equalsScenario();
                } else if ("!=".compareTo(verifyField.getOperator()) == 0) {
                    return TestScenarioConstants.INSTANCE.doesNotEqualScenario();
                } else {
                    return "";
                }
            }
        };

        operatorColumn.setFieldUpdater(new FieldUpdater<VerifyField, String>() {
            @Override
            public void update(int i,
                               VerifyField verifyField,
                               String s) {
                if (TestScenarioConstants.INSTANCE.equalsScenario().compareTo(s) == 0) {
                    verifyField.setOperator("==");
                } else if (TestScenarioConstants.INSTANCE.doesNotEqualScenario().compareTo(s) == 0) {
                    verifyField.setOperator("!=");
                } else {
                    verifyField.setOperator(null);
                }
            }
        });

        addColumn(operatorColumn,
                  "Expect that field");

        EditTextCell expectedValueCell = new EditTextCell();
        Column<VerifyField, String> expectedValueColumn = new Column<VerifyField, String>(expectedValueCell) {
            @Override
            public String getValue(VerifyField verifyField) {
                return  verifyField.getExpected();
            }
        };

        expectedValueColumn.setFieldUpdater(new FieldUpdater<VerifyField, String>() {
            @Override
            public void update(int i,
                               VerifyField verifyField,
                               String s) {
                verifyField.setExpected(s);
            }
        });

        addColumn(expectedValueColumn,
                  "Has a value");

        if (showResults) {
            TextCell actualValueCell = new TextCell();
            Column<VerifyField, String> actualValueColumn = new Column<VerifyField, String>(actualValueCell) {
                @Override
                public String getValue(VerifyField verifyField) {
                    if (verifyField.getSuccessResult() != null && verifyField.getSuccessResult().booleanValue()) {
                        return  verifyField.getExpected();
                    } else {
                        return  verifyField.getActualResult();
                    }
                }
            };

            addColumn(actualValueColumn,
                      "Actual value");
        }

        final ButtonCell deleteCell = new ButtonCell(ButtonType.DANGER,
                                                     IconType.TRASH);
        final Column<VerifyField, String> deleteColumn = new Column<VerifyField, String>(deleteCell) {
            @Override
            public String getValue(VerifyField model) {
                return "";
            }
        };

        deleteColumn.setFieldUpdater(new FieldUpdater<VerifyField, String>() {
            @Override
            public void update(int i,
                               VerifyField verifyField,
                               String s) {
                YesNoCancelPopup.newYesNoCancelPopup("title",
                                                     TestScenarioConstants.INSTANCE.AreYouSureYouWantToRemoveThisFieldExpectation(verifyField.getFieldName()),
                                                     () -> {
                                                         if (verifyFact != null && verifyFact.getFieldValues() != null) {
                                                             verifyFact.getFieldValues().remove(verifyField);
                                                             render(verifyFact);
                                                         }
                                                     },
                                                     null,
                                                     () -> {

                                                     }).show();
            }
        });

        addColumn(deleteColumn,
                  "");

        render(verifyFact);
    }

    private void render(VerifyFact verifyFact) {

        if (verifyFact != null && verifyFact.getFieldValues() != null) {
            setRowData(verifyFact.getFieldValues());

            if (showResults) {
                for(int i = 0; i < verifyFact.getFieldValues().size(); i++) {
                    final VerifyField field = verifyFact.getFieldValues().get(i);
                    if (field.getSuccessResult() == null || !field.getSuccessResult().booleanValue()) {
                        getRowElement(i).addClassName(ColorType.DANGER.getType());
                    } else {
                        getRowElement(i).addClassName(ColorType.SUCCESS.getType());
                    }
                }
            }

//            Widget cellEditor = new VerifyFieldConstraintEditor( type,
//                                                                 new ValueChanged() {
//                                                                     public void valueChanged( String newValue ) {
//                                                                         fld.setExpected( newValue );
//                                                                     }
//
//                                                                 },
//                                                                 fld,
//                                                                 oracle,
//                                                                 this.scenario,
//                                                                 this.executionTrace );
//
        }
    }
}
