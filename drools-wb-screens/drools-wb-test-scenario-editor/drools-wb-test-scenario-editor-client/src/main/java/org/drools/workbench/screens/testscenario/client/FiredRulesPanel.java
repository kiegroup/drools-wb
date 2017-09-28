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

import com.google.gwt.cell.client.TextCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.drools.workbench.screens.testscenario.client.resources.i18n.TestScenarioConstants;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import org.uberfire.ext.widgets.common.client.common.SmallLabel;

public class FiredRulesPanel extends HorizontalPanel {

    private final ExecutionTrace executionTrace;

    private final Button show;

    private final Button hide;

    private final CellTable<String> firedRulesTable;

    public FiredRulesPanel( final ExecutionTrace executionTrace ) {
        this.executionTrace = executionTrace;
        firedRulesTable = new CellTable<>();
        firedRulesTable.setStriped( true );
        firedRulesTable.setCondensed( true );
        firedRulesTable.setBordered( true );
        firedRulesTable.setWidth( "100%" );

        TextColumn<String> column = new TextColumn<String>() {
            @Override
            public String getValue(String s) {
                return s;
            }
        };

        firedRulesTable.addColumn(column, TestScenarioConstants.INSTANCE.property0RulesFiredIn1Ms(
                executionTrace.getNumberOfRulesFired(), executionTrace.getExecutionTimeResult() ));

        show = createShowButton();
        hide = createHideButton();

        hide.setVisible(false);
        firedRulesTable.setVisible(false);

        add(firedRulesTable);
        add( show );
        add( hide );
    }

    private Button createShowButton() {
        final Button button = new Button( TestScenarioConstants.INSTANCE.ShowRulesFired() );
        button.setIcon(IconType.ANGLE_RIGHT);
        button.addClickHandler( new ClickHandler() {

            public void onClick( ClickEvent event ) {
                firedRulesTable.setVisible(true);
                hide.setVisible(true);
                show.setVisible(false);
                render();
            }
        } );

        return button;
    }

    private Button createHideButton() {
        final Button button = new Button( "Hide fired rules" );
        button.setIcon(IconType.ANGLE_LEFT);
        button.addClickHandler( new ClickHandler() {

            public void onClick( ClickEvent event ) {
                firedRulesTable.setVisible(false);
                hide.setVisible(false);
                show.setVisible(true);
                render();
            }
        } );

        return button;
    }

    private void render() {
        firedRulesTable.setRowData(Arrays.asList(executionTrace.getRulesFired()));
    }
}
