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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import org.appformer.project.datamodel.oracle.MethodInfo;
import org.appformer.project.datamodel.oracle.ModelField;
import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.drools.workbench.models.testscenarios.shared.Fixture;
import org.drools.workbench.models.testscenarios.shared.FixtureList;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.drools.workbench.models.testscenarios.shared.VerifyFact;
import org.drools.workbench.models.testscenarios.shared.VerifyField;
import org.drools.workbench.screens.testscenario.client.resources.i18n.TestScenarioConstants;
import org.drools.workbench.screens.testscenario.client.resources.images.TestScenarioAltedImages;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.base.constants.ColorType;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.uberfire.client.callbacks.Callback;
import org.uberfire.ext.widgets.common.client.common.popups.FormStylePopup;
import org.uberfire.ext.widgets.common.client.common.popups.YesNoCancelPopup;
import org.uberfire.ext.widgets.common.client.common.popups.footers.GenericModalFooter;
import org.uberfire.ext.widgets.common.client.common.popups.footers.ModalFooterOKCancelButtons;

public class VerifyFactsPanel extends CellTable<VerifyFact> {

    private final Scenario scenario;

    private final ScenarioParentWidget parent;

    public VerifyFactsPanel( FixtureList verifyFacts,
                             ExecutionTrace executionTrace,
                             final Scenario scenario,
                             ScenarioParentWidget scenarioWidget,
                             boolean showResults,
                             AsyncPackageDataModelOracle oracle ) {

        setStriped(true);
        setCondensed(true);
        setBordered(true);
        setWidth("100%");

        this.scenario = scenario;
        this.parent = scenarioWidget;

        final TextCell factCell = new TextCell();
        final Column<VerifyFact, String> factNameColumn = new Column<VerifyFact, String>(factCell) {
            @Override
            public String getValue(VerifyFact model) {
                return model.getName();
            }
        };

        addColumn(factNameColumn,
                  "The fact");

        final TextCell fieldsCell = new TextCell() {
            @Override
            public Set<String> getConsumedEvents() {
                return new HashSet<String>() {{
                    add(BrowserEvents.CLICK);
                }};
            }
        };

        final Column<VerifyFact, String> fieldsColumn = new Column<VerifyFact, String>(fieldsCell) {
            @Override
            public String getValue(VerifyFact model) {
                StringBuilder builder = new StringBuilder();
                if (model.getFieldValues() != null) {
                    for(VerifyField field : model.getFieldValues()) {
                        builder.append(field.getFieldName())
                               .append(field.getOperator())
                               .append(field.getExpected())
                               .append(";");

                    }
                }
                return builder.toString();
            }

            @Override
            public void onBrowserEvent(Cell.Context context,
                                       Element elem,
                                       VerifyFact verifyFact,
                                       NativeEvent event) {
                super.onBrowserEvent(context,
                                     elem,
                                     verifyFact,
                                     event);

                final VerifyFactWidget factFields = new VerifyFactWidget(verifyFact,
                                                                         scenario,
                                                                         oracle,
                                                                         executionTrace,
                                                                         showResults);

                final FormStylePopup fieldsDetails = new FormStylePopup("Expected field values");
                fieldsDetails.addAttribute("Field values", factFields);
                final GenericModalFooter footer = new GenericModalFooter();
                footer.addButton("OK",
                                 new org.uberfire.mvp.Command() {
                                     @Override
                                     public void execute() {
                                         fieldsDetails.hide();
                                     }
                                 },
                                 ButtonType.PRIMARY);

                footer.addButton("Add Field",
                                 new org.uberfire.mvp.Command() {
                                     @Override
                                     public void execute() {
                                         final ListBox fieldsListBox = new ListBox();
                                         final String type = verifyFact.anonymous ? verifyFact.getName() : (String) scenario.getVariableTypes().get(verifyFact.getName());
                                         oracle.getFieldCompletions(type,
                                                                    new Callback<ModelField[]>() {
                                                                        @Override
                                                                        public void callback(final ModelField[] fields) {

                                                                            // Add fields
                                                                            for (int i = 0; i < fields.length; i++) {
                                                                                fieldsListBox.addItem(fields[i].getName());
                                                                            }

                                                                            // Add methods
                                                                            oracle.getMethodInfos(type,
                                                                                                  new Callback<List<MethodInfo>>() {
                                                                                                      @Override
                                                                                                      public void callback(List<MethodInfo> result) {
                                                                                                          for (MethodInfo info : result) {
                                                                                                              if (info.getParams().isEmpty() && !"void".equals(info.getReturnClassType())) {
                                                                                                                  fieldsListBox.addItem(info.getName());
                                                                                                              }
                                                                                                          }
                                                                                                      }
                                                                                                  });
                                                                        }
                                                                    });

                                         final FormStylePopup pop = new FormStylePopup(TestScenarioAltedImages.INSTANCE.RuleAsset(),
                                                                                       TestScenarioConstants.INSTANCE.ChooseAFieldToAdd());
                                         pop.addRow(fieldsListBox);
                                         pop.add(new ModalFooterOKCancelButtons(new Command() {
                                             @Override
                                             public void execute() {
                                                 String f = fieldsListBox.getItemText(fieldsListBox.getSelectedIndex());
                                                 verifyFact.getFieldValues().add(new VerifyField(f,
                                                                                                 "",
                                                                                                 "=="));
                                                 factFields.redraw();

                                                 pop.hide();
                                             }
                                         },
                                                                                new Command() {
                                                                                    @Override
                                                                                    public void execute() {
                                                                                        pop.hide();
                                                                                    }
                                                                                }
                                         ));

                                         pop.show();
                                     }
                                 },
                                 IconType.PLUS,
                                 ButtonType.DEFAULT);

                fieldsDetails.add(footer);
                fieldsDetails.setWidth("1000px");
                fieldsDetails.show();
            }
        };

        addColumn(fieldsColumn,
                  "Has Fields");

        final ButtonCell deleteCell = new ButtonCell(ButtonType.DANGER,
                                                     IconType.TRASH);
        final Column<VerifyFact, String> deleteColumn = new Column<VerifyFact, String>(deleteCell) {
            @Override
            public String getValue(VerifyFact model) {
                return "";
            }
        };

        deleteColumn.setFieldUpdater(new FieldUpdater<VerifyFact, String>() {
            @Override
            public void update(int i,
                               VerifyFact verifyFact,
                               String s) {
                YesNoCancelPopup.newYesNoCancelPopup("title",
                                                     TestScenarioConstants.INSTANCE.AreYouSureYouWantToRemoveThisExpectation(),
                                                     () -> {
                                                         scenario.removeFixture( verifyFact );
                                                         parent.renderEditor();
                                                     },
                                                     null,
                                                     () -> {

                                                     }).show();
            }
        });

        addColumn(deleteColumn, "");

        List<VerifyFact> facts = new ArrayList<>();
        for ( Fixture fixture : verifyFacts ) {
            if ( fixture instanceof VerifyFact ) {
                VerifyFact verifyFact = (VerifyFact) fixture;
                facts.add(verifyFact);
//                HorizontalPanel column = new HorizontalPanel();
//                CellTable<VerifyField> verifyFieldDetails = new VerifyFactWidget( verifyFact,
//                                                                                  scenario,
//                                                                                  oracle,
//                                                                                  executionTrace,
//                                                                                  showResults );
//                column.add( verifyFieldDetails );
//
//                column.add( new AddVerifyFieldButton(oracle, verifyFact, verifyFieldDetails));
//
//                column.add( new DeleteButton( verifyFact ) );
//
//                add( column );
            }
        }
        setRowData(facts);

        if (showResults) {
            for (int i = 0; i < facts.size(); i++) {
                final VerifyFact fact = facts.get(i);
                if (fact.wasSuccessful()) {
                    getRowElement(i).addClassName(ColorType.SUCCESS.getType());
                } else {
                    getRowElement(i).addClassName(ColorType.DANGER.getType());
                }
            }
        }
    }
}
