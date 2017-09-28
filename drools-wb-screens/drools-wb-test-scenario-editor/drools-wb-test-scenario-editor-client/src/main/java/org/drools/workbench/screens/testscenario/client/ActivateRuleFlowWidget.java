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
import org.drools.workbench.models.testscenarios.shared.ActivateRuleFlowGroup;
import org.drools.workbench.models.testscenarios.shared.Fixture;
import org.drools.workbench.models.testscenarios.shared.FixtureList;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.kie.workbench.common.widgets.client.resources.i18n.CommonConstants;

public class ActivateRuleFlowWidget
        extends CellTable<ActivateRuleFlowGroup> {

    private final ScenarioParentWidget parent;

    public ActivateRuleFlowWidget( final FixtureList ruleflowGroups,
                                   final Scenario scenario,
                                   final ScenarioParentWidget parent ) {
        super(Integer.MAX_VALUE);
        this.parent = parent;

        setStriped( true );
        setCondensed( true );
        setBordered( true );
//        setEmptyTableWidget( new Label(EnumEditorConstants.INSTANCE.noEnumsDefined() ) );
        setWidth( "400px" );

        final TextCell nameCell = new TextCell();
        Column<ActivateRuleFlowGroup, String> nameColumn = new Column<ActivateRuleFlowGroup, String>(nameCell) {
            @Override
            public String getValue( ActivateRuleFlowGroup model ) {
                return model.getName();
            }
        };
        addColumn( nameColumn,
                            "Activate ruleflow group" );
        setColumnWidth(nameColumn,
                       80,
                       com.google.gwt.dom.client.Style.Unit.PCT );


        final ButtonCell deleteCell = new ButtonCell(ButtonType.DANGER, IconType.TRASH );
        final Column<ActivateRuleFlowGroup, String> deleteColumn = new Column<ActivateRuleFlowGroup, String>(deleteCell) {
            @Override
            public String getValue( ActivateRuleFlowGroup model ) {
                return "";
            }
        };

        deleteColumn.setFieldUpdater( new FieldUpdater<ActivateRuleFlowGroup, String>() {
            @Override
            public void update( int index,
                                ActivateRuleFlowGroup model,
                                String value ) {
                ruleflowGroups.remove( model );
                scenario.getFixtures().remove( model );
                parent.renderEditor();
            }
        } );

        deleteColumn.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER );

        addColumn(deleteColumn,
                           CommonConstants.INSTANCE.Delete() );

        render( ruleflowGroups );
    }

    private void render( final FixtureList ruleflowGroups) {

        final List<ActivateRuleFlowGroup> groups = new ArrayList<>();

        for ( Fixture fixture : ruleflowGroups ) {
            if ( fixture instanceof ActivateRuleFlowGroup ) {
                final ActivateRuleFlowGroup group = (ActivateRuleFlowGroup) fixture;
                groups.add(group);
            }
        }

        setRowData(groups);
    }
}
