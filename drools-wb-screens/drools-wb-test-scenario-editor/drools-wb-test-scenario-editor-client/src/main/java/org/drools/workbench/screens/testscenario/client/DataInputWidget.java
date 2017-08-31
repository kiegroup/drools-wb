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
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.Command;
import org.appformer.project.datamodel.oracle.DataType;
import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.drools.workbench.models.testscenarios.shared.FactData;
import org.drools.workbench.models.testscenarios.shared.Field;
import org.drools.workbench.models.testscenarios.shared.FieldData;
import org.drools.workbench.models.testscenarios.shared.FieldPlaceHolder;
import org.drools.workbench.models.testscenarios.shared.Fixture;
import org.drools.workbench.models.testscenarios.shared.FixtureList;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.drools.workbench.models.testscenarios.shared.VerifyFact;
import org.drools.workbench.models.testscenarios.shared.VerifyField;
import org.drools.workbench.screens.testscenario.client.resources.i18n.TestScenarioConstants;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.uberfire.ext.widgets.common.client.common.DatePicker;
import org.uberfire.ext.widgets.common.client.common.popups.FormStylePopup;
import org.uberfire.ext.widgets.common.client.common.popups.YesNoCancelPopup;
import org.uberfire.ext.widgets.common.client.common.popups.errors.ErrorPopup;
import org.uberfire.ext.widgets.common.client.common.popups.footers.ModalFooterOKButton;

public class DataInputWidget
        extends CellTable<FactData>
        implements ScenarioParentWidget {

    private final Scenario scenario;
    private final AsyncPackageDataModelOracle oracle;
    protected final String factType;
    private final ScenarioParentWidget parent;
    private final ExecutionTrace executionTrace;
    private final FixtureList definitionList;
    private final String headerText;

    public DataInputWidget( String factType,
                            FixtureList definitionList,
                            Scenario scenario,
                            AsyncPackageDataModelOracle oracle,
                            ScenarioParentWidget parent,
                            ExecutionTrace executionTrace,
                            String headerText ) {
        super(Integer.MAX_VALUE);
        this.scenario = scenario;
        this.oracle = oracle;
        this.factType = factType;
        this.parent = parent;
        this.executionTrace = executionTrace;
        this.definitionList = definitionList;
        this.headerText = headerText;

        setStriped( true );
        setCondensed( true );
        setBordered( true );
//        setEmptyTableWidget( new Label(EnumEditorConstants.INSTANCE.noEnumsDefined() ) );
        setWidth( "100%" );
        setTitle(headerText);

        final TextCell nameCell = new TextCell();
        Column<FactData, String> nameColumn = new Column<FactData, String>(nameCell) {
            @Override
            public String getValue( FactData model ) {
                return factType + " [" + model.getName() + "]";
            }
        };

        addColumn( nameColumn,
                   "The fact" );

        final EditTextCell fieldsCell = new EditTextCell() {
            @Override
            public Set<String> getConsumedEvents() {
                return new HashSet<String>() {{
                    add(BrowserEvents.DBLCLICK);
                }};
            }
        };

        final Column<FactData, String> fieldsColumn = new Column<FactData, String>(fieldsCell) {
            @Override
            public String getValue(FactData model) {
                StringBuilder builder = new StringBuilder();
                if (model.getFieldData() != null) {
                    for(Field field : model.getFieldData()) {
                        if(field instanceof FieldData) {
                            final FieldData fieldData = (FieldData) field;
                            builder.append(fieldData.getName())
                                    .append(" = ")
                                    .append(fieldData.getValue())
                                    .append(";");
                        }
                    }
                }
                return builder.toString();
            }

            @Override
            public void onBrowserEvent(Cell.Context context,
                                       Element elem,
                                       FactData object,
                                       NativeEvent event) {
                super.onBrowserEvent(context,
                                     elem,
                                     object,
                                     event);
                if(BrowserEvents.DBLCLICK.compareTo(event.getType()) == 0) {

                }
                final FormStylePopup fieldsDetails = new FormStylePopup("Expected field values");
                fieldsDetails.addAttribute("Field values", new FieldDataValues(object,
                                                                                scenario,
                                                                                oracle));
                fieldsDetails.add(new ModalFooterOKButton(new Command() {
                    @Override
                    public void execute() {
                        fieldsDetails.hide();
                    }
                }));
                fieldsDetails.show();
            }
        };

        addColumn(fieldsColumn,
                  "Has Fields");


        final ButtonCell deleteCell = new ButtonCell(ButtonType.DANGER, IconType.TRASH );
        final Column<FactData, String> deleteColumn = new Column<FactData, String>(deleteCell) {
            @Override
            public String getValue( FactData model ) {
                return "";
            }
        };

        deleteColumn.setFieldUpdater( new FieldUpdater<FactData, String>() {
            @Override
            public void update( int index,
                                FactData model,
                                String value ) {
                if ( scenario.isFactDataReferenced( model ) ) {
                    ErrorPopup.showMessage(TestScenarioConstants.INSTANCE.CanTRemoveThisColumnAsTheName0IsBeingUsed(model.getName() ) );
                } else {
                    YesNoCancelPopup.newYesNoCancelPopup("title",
                                                         TestScenarioConstants.INSTANCE.AreYouSureYouWantToRemoveColumn0(model.getName()),
                                                         () -> {
                                                             scenario.removeFixture(model);
                                                             definitionList.remove( model );

                                                             parent.renderEditor();
                                                         },
                                                         null,
                                                         () -> {

                                                         }).show();
                }
            }
        } );

        addColumn(deleteColumn, "");

        renderEditor();
    }

    public void renderEditor() {

//        clear();

        if ( definitionList.size() == 0 ) {
            parent.renderEditor();
        }

        //This will work out what row is for what field, adding labels and remove icons
//        FactDataWidgetFactory factDataWidgetFactory = new FactDataWidgetFactory( scenario,
//                                                                                 oracle,
//                                                                                 definitionList,
//                                                                                 executionTrace,
//                                                                                 this,
//                                                                                 this );

        List<FactData> rows = new ArrayList<>();
        List<String> alreadyUsedFields = new ArrayList<>();

        for ( Fixture fixture : definitionList ) {
            if ( fixture != null && fixture instanceof FactData ) {
                rows.add((FactData) fixture);
//                for (Field field : ((FactData) fixture).getFieldData()) {
//
//                    if (!alreadyUsedFields.contains(field.getName())) {
//                        alreadyUsedFields.add(field.getName());
//
//                        if(field instanceof FieldData) {
//
//                            final Cell fieldCell;
//                            FieldConstraintHelper helper = new FieldConstraintHelper(scenario, executionTrace, oracle, factType, field, (FactData)fixture);
//                            String fieldType = helper.getFieldType();
//                            if(fieldType == DataType.TYPE_BOOLEAN) {
//                                fieldCell = new SelectionCell(Arrays.asList("true",
//                                                                            "false"));
//                            } else if(helper.getEnums() != null){
//                                fieldCell = new SelectionCell(Arrays.asList(helper.getEnums().getFixedList()));
//                            } else if(helper.isThereABoundVariableToSet()) {
//                                List<String> vars = new ArrayList<>();
//                                for ( String var : helper.getFactNamesInScope() ) {
//                                    if ( helper.getFactTypeByVariableName( var ).getType().equals( helper.resolveFieldType() ) ) {
//                                        vars.add(var);
//                                    }
//                                }
//
//                                fieldCell = new SelectionCell(vars);
//                            } else {
//                                fieldCell = new EditTextCell();
//                            }
//                            Column<FactData, String> fieldColumn = new Column<FactData, String>(fieldCell) {
//                                @Override
//                                public String getValue(FactData model) {
//                                    for(Field rowModelField : model.getFieldData()) {
//                                        if(rowModelField.getName().compareTo(field.getName()) == 0) {
//                                            if (((FieldData) rowModelField).getValue() != null) {
//                                                return ((FieldData) rowModelField).getValue();
//                                            }
//                                        }
//                                    }
//
//                                    // this fact is not using this field
//                                    return "";
//                                }
//                            };
//
//                            fieldColumn.setFieldUpdater(new FieldUpdater<FactData, String>() {
//                                @Override
//                                public void update(int i,
//                                                   FactData factData,
//                                                   String newFieldValue) {
//                                    for(Field rowModelField : factData.getFieldData()) {
//                                        if(rowModelField.getName().compareTo(field.getName()) == 0) {
//                                            ((FieldData) rowModelField).setValue(newFieldValue);
//                                        }
//                                    }
//
//                                }
//                            });
//
//                            addColumn(fieldColumn,
//                                      field.getName());
//                        }
//                    }
//                }
            }
        }

        setRowData(rows);
    }

    private class FieldDataValues extends CellTable<FieldData> {

        private FactData factData;
        private Scenario scenario;
        private AsyncPackageDataModelOracle oracle;

        public FieldDataValues(final FactData factData,
                               final Scenario scenario,
                               final AsyncPackageDataModelOracle oracle) {
            setStriped( true );
            setCondensed( true );
            setBordered( true );
            setWidth( "100%" );

            this.factData = factData;
            this.scenario = scenario;
            this.oracle = oracle;

            final TextCell nameCell = new TextCell();
            Column<FieldData, String> nameColumn = new Column<FieldData, String>(nameCell) {
                @Override
                public String getValue( FieldData model ) {
                    return factType + " [" + model.getName() + "]";
                }
            };

            addColumn( nameColumn,
                       "The field" );

            final EditTextCell valueCell = new EditTextCell();
            Column<FieldData, String> valueColumn = new Column<FieldData, String>(valueCell) {
                @Override
                public String getValue( FieldData model ) {
                    return model.getName();
                }
            };

            valueColumn.setFieldUpdater(new FieldUpdater<FieldData, String>() {
                @Override
                public void update(int i,
                                   FieldData fieldData,
                                   String s) {
                    fieldData.setValue(s);
                }
            });

            addColumn( valueColumn,
                       "Value" );

            List<FieldData> fields = new ArrayList<>();
            for(Field field : factData.getFieldData()) {
                if(field instanceof FieldData) {
                    fields.add((FieldData) field);
                }
            }
            setRowData(fields);
        }
    }
}
