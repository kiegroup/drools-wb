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
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import org.drools.workbench.models.testscenarios.shared.FixtureList;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.drools.workbench.models.testscenarios.shared.VerifyRuleFired;
import org.drools.workbench.screens.testscenario.client.resources.i18n.TestScenarioConstants;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.base.constants.ColorType;
import org.uberfire.ext.widgets.common.client.common.NumericTextBox;
import org.uberfire.ext.widgets.common.client.common.popups.FormStylePopup;
import org.uberfire.ext.widgets.common.client.common.popups.YesNoCancelPopup;
import org.uberfire.ext.widgets.common.client.common.popups.footers.ModalFooterOKButton;

public class VerifyRulesFiredWidget extends CellTable<VerifyRuleFired> {

    //    private Grid outer;
    private boolean showResults;

    /**
     * @param rfl List<VeryfyRuleFired>
     * @param scenario = the scenario to add/remove from
     */
    public VerifyRulesFiredWidget(final FixtureList rfl,
                                  final Scenario scenario,
                                  final boolean showResults) {
        setStriped(true);
        setCondensed(true);
        setBordered(true);
        setWidth("100%");

        final TextCell nameCell = new TextCell();
        Column<VerifyRuleFired, String> nameColumn = new Column<VerifyRuleFired, String>(nameCell) {
            @Override
            public String getValue(VerifyRuleFired model) {
                return model.getRuleName();
            }
        };

        addColumn(nameColumn,
                  "Expected rule that was fired");

        final TextCell howManyTimesCell = new TextCell() {
            @Override
            public Set<String> getConsumedEvents() {
                return new HashSet<String>() {{
                    add(BrowserEvents.CLICK);
                }};
            }
        };

        final Column<VerifyRuleFired, String> howManyTimesColumn = new Column<VerifyRuleFired, String>(howManyTimesCell) {
            @Override
            public String getValue(VerifyRuleFired verifyRuleFired) {
                if (verifyRuleFired.getExpectedFire() != null) {
                    return verifyRuleFired.getExpectedFire() ? TestScenarioConstants.INSTANCE.firedAtLeastOnce() : TestScenarioConstants.INSTANCE.didNotFire();
                } else if (verifyRuleFired.getExpectedCount() != null) {
                    return TestScenarioConstants.INSTANCE.firedThisManyTimes() + "; " + verifyRuleFired.getExpectedCount();
                } else {
                    return "";
                }
            }

            @Override
            public void onBrowserEvent(Cell.Context context,
                                       Element elem,
                                       VerifyRuleFired verifyRuleFired,
                                       NativeEvent event) {
                super.onBrowserEvent(context,
                                     elem,
                                     verifyRuleFired,
                                     event);
                if (event.getType() != null && BrowserEvents.CLICK.compareTo(event.getType()) == 0) {
                    final NumericTextBox specifyExactNumber = new NumericTextBox(false);
                    if (verifyRuleFired.getExpectedCount() != null) {
                        specifyExactNumber.setValue(verifyRuleFired.getExpectedCount().toString());
                    }

                    final ListBox occurrence = new ListBox();
                    occurrence.addItem(TestScenarioConstants.INSTANCE.firedAtLeastOnce());
                    occurrence.addItem(TestScenarioConstants.INSTANCE.didNotFire());
                    occurrence.addItem(TestScenarioConstants.INSTANCE.firedThisManyTimes());

                    final FormStylePopup wasFiredTimesPopup = new FormStylePopup("The rule was fired times");

                    if (verifyRuleFired.getExpectedFire() != null) {
                        if (verifyRuleFired.getExpectedFire()) {
                            occurrence.setSelectedIndex(0);
                        } else {
                            occurrence.setSelectedIndex(1);
                        }
                        wasFiredTimesPopup.setAttributeVisibility(1, false);
                    } else {
                        occurrence.setSelectedIndex(2);
                        wasFiredTimesPopup.setAttributeVisibility(1, true);
                    }

                    wasFiredTimesPopup.addAttribute("How many times the rule was fired",
                                                    occurrence);
                    wasFiredTimesPopup.addAttribute("Specify exact number",
                                                    specifyExactNumber,
                                                    false);
                    occurrence.addChangeHandler(new ChangeHandler() {
                        @Override
                        public void onChange(ChangeEvent changeEvent) {
                            String newValue = occurrence.getSelectedItemText();

                            if (TestScenarioConstants.INSTANCE.firedAtLeastOnce().compareTo(newValue) == 0) {
                                wasFiredTimesPopup.setAttributeVisibility(1,
                                                                          false);
                            } else if (TestScenarioConstants.INSTANCE.didNotFire().compareTo(newValue) == 0) {
                                wasFiredTimesPopup.setAttributeVisibility(1,
                                                                          false);
                            } else {
                                wasFiredTimesPopup.setAttributeVisibility(1,
                                                                          true);
                            }
                        }
                    });

                    wasFiredTimesPopup.add(new ModalFooterOKButton(new Command() {
                        @Override
                        public void execute() {
                            String newValue = occurrence.getSelectedItemText();

                            if (TestScenarioConstants.INSTANCE.firedAtLeastOnce().compareTo(newValue) == 0) {
                                verifyRuleFired.setExpectedFire(true);
                                verifyRuleFired.setExpectedCount(null);
                            } else if (TestScenarioConstants.INSTANCE.didNotFire().compareTo(newValue) == 0) {
                                verifyRuleFired.setExpectedFire(false);
                                verifyRuleFired.setExpectedCount(null);
                            } else {
                                verifyRuleFired.setExpectedFire(null);
                                verifyRuleFired.setExpectedCount(Integer.valueOf(specifyExactNumber.getValue()));
                            }
                            wasFiredTimesPopup.hide();
                            VerifyRulesFiredWidget.this.redraw();
                        }
                    }));

                    wasFiredTimesPopup.show();
                }
            }
        };

        addColumn(howManyTimesColumn,
                  "How many times the rule was fired");

        final ButtonCell deleteCell = new ButtonCell(ButtonType.DANGER,
                                                     IconType.TRASH);
        final Column<VerifyRuleFired, String> deleteColumn = new Column<VerifyRuleFired, String>(deleteCell) {
            @Override
            public String getValue(VerifyRuleFired model) {
                return "";
            }
        };

        deleteColumn.setFieldUpdater(new FieldUpdater<VerifyRuleFired, String>() {
            @Override
            public void update(int i,
                               VerifyRuleFired verifyRuleFired,
                               String s) {
                YesNoCancelPopup.newYesNoCancelPopup("title",
                                                     TestScenarioConstants.INSTANCE.AreYouSureYouWantToRemoveThisRuleExpectation(),
                                                     () -> {
                                                         rfl.remove(verifyRuleFired);
                                                         scenario.removeFixture(verifyRuleFired);
                                                         render(rfl);
                                                     },
                                                     null,
                                                     () -> {

                                                     }).show();
            }
        });

        addColumn(deleteColumn,
                  "");

        this.showResults = showResults;
        render(rfl);
    }

    private void render(final FixtureList rfl) {

        List<VerifyRuleFired> rules = new ArrayList<>();

        for (int i = 0; i < rfl.size(); i++) {
            final VerifyRuleFired v = (VerifyRuleFired) rfl.get(i);
            rules.add(v);
        }
        setRowData(rules);

        if (showResults) {
            for (int i = 0; i < rules.size(); i++) {
                final VerifyRuleFired rule = rules.get(i);
                if(rule.wasSuccessful()) {
                    getRowElement(i).addClassName(ColorType.SUCCESS.getType());
                } else {
                    getRowElement(i).addClassName(ColorType.DANGER.getType());
                }
            }
        }
    }
}
