/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.guided.dtable.client.widget.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.drools.workbench.models.datamodel.rule.BaseSingleFieldConstraint;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;
import org.drools.workbench.screens.guided.dtable.client.resources.GuidedDecisionTableResources;
import org.drools.workbench.screens.guided.dtable.client.resources.images.GuidedDecisionTableImageResources508;
import org.gwtbootstrap3.client.ui.html.Text;
import org.jboss.errai.security.shared.api.identity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.mockito.Mock;

import static org.junit.Assert.*;

@RunWith(GwtMockitoTestRunner.class)
@WithClassesToStub({ Text.class, GuidedDecisionTableImageResources508.class, GuidedDecisionTableResources.class })
public class AbstractDecisionTableWidgetTest {

    @Mock
    private AsyncPackageDataModelOracle oracle;

    @Mock
    private User identity;

    @Mock
    private EventBus eventBus;

    private GuidedDecisionTable52 model;

    private AbstractDecisionTableWidget table;

    private Pattern52 pattern;
    private Pattern52 editedPattern;

    private ConditionCol52 condition;
    private ConditionCol52 editedCondition;

    @Before
    public void setup() {
        ApplicationPreferences.setUp( new HashMap<String, String>() {{
            put( ApplicationPreferences.DATE_FORMAT,
                 "dd-MM-yyyy" );
        }} );

        this.model = new GuidedDecisionTable52();
        this.table = new AbstractDecisionTableWidget( model,
                                                      oracle,
                                                      identity,
                                                      false,
                                                      eventBus ) {
            @Override
            protected void setEnableOtherwiseButton( final boolean isEnabled ) {
                //Do nothing for these Unit Tests
            }
        };
    }

    @Test
    public void checkAddToValueListPreservesDataSamePattern() {
        setupInitialValueListState( "$a" );

        updateValueList( "$a",
                         "A,B,C,D" );

        table.updateColumn( pattern,
                            condition,
                            editedPattern,
                            editedCondition );

        assertEquals( 1,
                      model.getConditions().size() );
        assertEquals( 1,
                      model.getConditions().get( 0 ).getChildColumns().size() );

        assertEquals( "A",
                      model.getData().get( 0 ).get( 2 ).getStringValue() );
        assertEquals( "B",
                      model.getData().get( 1 ).get( 2 ).getStringValue() );
        assertEquals( "C",
                      model.getData().get( 2 ).get( 2 ).getStringValue() );
    }

    @Test
    public void checkAddToValueListPreservesDataDifferentPattern() {
        setupInitialValueListState( "$a" );

        updateValueList( "$a2",
                         "A,B,C,D" );

        table.updateColumn( pattern,
                            condition,
                            editedPattern,
                            editedCondition );

        assertEquals( 1,
                      model.getConditions().size() );
        assertEquals( 1,
                      model.getConditions().get( 0 ).getChildColumns().size() );

        assertEquals( "A",
                      model.getData().get( 0 ).get( 2 ).getStringValue() );
        assertEquals( "B",
                      model.getData().get( 1 ).get( 2 ).getStringValue() );
        assertEquals( "C",
                      model.getData().get( 2 ).get( 2 ).getStringValue() );
    }

    @Test
    public void checkRemoveFromValueListClearsDataSamePattern() {
        setupInitialValueListState( "$a" );

        updateValueList( "$a",
                         "A" );

        table.updateColumn( pattern,
                            condition,
                            editedPattern,
                            editedCondition );

        assertEquals( 1,
                      model.getConditions().size() );
        assertEquals( 1,
                      model.getConditions().get( 0 ).getChildColumns().size() );

        assertEquals( "A",
                      model.getData().get( 0 ).get( 2 ).getStringValue() );
        assertFalse( model.getData().get( 1 ).get( 2 ).hasValue() );
        assertFalse( model.getData().get( 2 ).get( 2 ).hasValue() );
    }

    @Test
    public void checkRemoveFromValueListClearsDataDifferentPattern() {
        setupInitialValueListState( "$a" );

        updateValueList( "$a2",
                         "A" );

        table.updateColumn( pattern,
                            condition,
                            editedPattern,
                            editedCondition );

        assertEquals( 1,
                      model.getConditions().size() );
        assertEquals( 1,
                      model.getConditions().get( 0 ).getChildColumns().size() );

        assertEquals( "A",
                      model.getData().get( 0 ).get( 2 ).getStringValue() );
        assertFalse( model.getData().get( 1 ).get( 2 ).hasValue() );
        assertFalse( model.getData().get( 2 ).get( 2 ).hasValue() );
    }

    private void setupInitialValueListState( final String binding ) {
        pattern = new Pattern52();
        pattern.setBoundName( binding );
        pattern.setFactType( "Applicant" );

        condition = new ConditionCol52();
        condition.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        condition.setHeader( "col1" );
        condition.setFactField( "name" );
        condition.setOperator( "==" );
        condition.setValueList( "A,B,C" );

        table.addColumn( pattern,
                         condition );

        model.getData().add( makeRow() );
        model.getData().add( makeRow() );
        model.getData().add( makeRow() );

        model.getData().get( 0 ).get( 2 ).setStringValue( "A" );
        model.getData().get( 1 ).get( 2 ).setStringValue( "B" );
        model.getData().get( 2 ).get( 2 ).setStringValue( "C" );
    }

    private void updateValueList( final String binding,
                                  final String valueList ) {
        editedPattern = new Pattern52();
        editedPattern.setBoundName( binding );
        editedPattern.setFactType( "Applicant" );

        editedCondition = new ConditionCol52();
        editedCondition.setConstraintValueType( BaseSingleFieldConstraint.TYPE_LITERAL );
        editedCondition.setHeader( "col1" );
        editedCondition.setFactField( "name" );
        editedCondition.setOperator( "==" );
        editedCondition.setValueList( valueList );
    }

    private List<DTCellValue52> makeRow() {
        final List<DTCellValue52> row = new ArrayList<DTCellValue52>();
        row.add( new DTCellValue52( 0 ) );
        row.add( new DTCellValue52( "description" ) );
        row.add( new DTCellValue52( "" ) );
        return row;
    }

}
