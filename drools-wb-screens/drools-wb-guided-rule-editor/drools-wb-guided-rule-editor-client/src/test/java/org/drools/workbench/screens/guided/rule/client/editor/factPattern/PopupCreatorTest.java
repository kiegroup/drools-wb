/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.guided.rule.client.editor.factPattern;

import org.drools.workbench.models.datamodel.rule.ExpressionFormLine;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraint;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraintEBLeftSide;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PopupCreatorTest {

    private final PopupCreator popup = spy(new PopupCreator());

    @Mock
    private AsyncPackageDataModelOracle oracle;

    @Mock
    private SingleFieldConstraint parentFieldConstraint;

    @Mock
    private ExpressionFormLine formLine;

    @Test
    public void testMakeFieldConstraintWhenParentFieldConstraintIsNull() throws Exception {
        final String fieldName = "fieldName";
        final String fieldType = "fieldType";
        final String factType = "factType";
        final SingleFieldConstraint parentFieldConstraint = null;

        oracleGetFieldTypeMock(fieldName,
                               fieldType,
                               factType);

        final SingleFieldConstraint constraint = popup.makeFieldConstraint(fieldName,
                                                                           factType,
                                                                           parentFieldConstraint);

        assertNotNull(constraint);
    }

    @Test
    public void testMakeFieldConstraintWhenParentFieldConstraintIsNotNull() throws Exception {
        final String fieldName = "fieldName";
        final String parentFieldName = "parentFieldName";
        final String fieldType = "fieldType";
        final String factType = "factType";

        oracleGetFieldTypeMock(fieldName,
                               fieldType,
                               factType);
        doReturn(parentFieldName).when(parentFieldConstraint).getFieldName();
        doReturn(formLine).when(popup).makeLeftSideExpression(fieldName,
                                                              factType,
                                                              fieldType,
                                                              parentFieldConstraint);

        final SingleFieldConstraintEBLeftSide constraint =
                (SingleFieldConstraintEBLeftSide) popup.makeFieldConstraint(fieldName,
                                                                            factType,
                                                                            parentFieldConstraint);

        assertEquals(formLine,
                     constraint.getExpressionLeftSide());
    }

    private void oracleGetFieldTypeMock(final String fieldName,
                                        final String fieldType,
                                        final String factType) {
        doReturn(oracle).when(popup).getDataModelOracle();
        doReturn(fieldType).when(oracle).getFieldType(factType,
                                                      fieldName);
    }
}
