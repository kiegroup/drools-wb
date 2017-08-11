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
import java.util.List;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import org.drools.workbench.models.testscenarios.shared.Fixture;
import org.drools.workbench.models.testscenarios.shared.FixtureList;
import org.drools.workbench.models.testscenarios.shared.RetractFact;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.kie.workbench.common.widgets.client.resources.i18n.CommonConstants;

public class RetractWidget extends CellTable<RetractFact> {

    protected final FixtureList retractList;
    protected final Scenario scenario;
    protected final ScenarioParentWidget parent;

    public RetractWidget( final FixtureList retractList,
                          final Scenario scenario,
                          final ScenarioParentWidget parent ) {
        super(Integer.MAX_VALUE);

        this.retractList = retractList;
        this.scenario = scenario;
        this.parent = parent;

        setStriped( true );
        setCondensed( true );
        setBordered( true );
//        setEmptyTableWidget( new Label(EnumEditorConstants.INSTANCE.noEnumsDefined() ) );
        setWidth( "400px" );

        final TextCell nameCell = new TextCell();
        Column<RetractFact, String> nameColumn = new Column<RetractFact, String>(nameCell) {
            @Override
            public String getValue( RetractFact model ) {
                return model.getName();
            }
        };
        addColumn( nameColumn,
                            "Fact to retract" );
        setColumnWidth(nameColumn,
                       80,
                       com.google.gwt.dom.client.Style.Unit.PCT);


        final ButtonCell deleteCell = new ButtonCell(ButtonType.DANGER, IconType.TRASH );
        final Column<RetractFact, String> deleteColumn = new Column<RetractFact, String>(deleteCell) {
            @Override
            public String getValue( RetractFact model ) {
                return "";
            }
        };

        deleteColumn.setFieldUpdater( new FieldUpdater<RetractFact, String>() {
            @Override
            public void update( int index,
                                RetractFact model,
                                String value ) {
                retractList.remove( model );
                scenario.getFixtures().remove( model );
                parent.renderEditor();
            }
        } );

        deleteColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER );

        addColumn(deleteColumn,
                           CommonConstants.INSTANCE.Delete() );

        render();
    }

    private void render() {
        final List<RetractFact> facts = new ArrayList<>();

        for ( Fixture fixture : retractList ) {
            if ( fixture instanceof RetractFact ) {
                final RetractFact retractFact = (RetractFact) fixture;
                facts.add(retractFact);
            }
        }

        setRowData(facts);
//        for ( Fixture fixture : retractList ) {
//            if ( fixture instanceof RetractFact ) {
//                final RetractFact retractFact = (RetractFact) fixture;
//                Row htmlRow = new Row();
//                htmlRow.add( new Label(retractFact.getName() ) );
//                htmlRow.add(new DeleteButton( retractFact ) );
//            }
//        }
    }
}
