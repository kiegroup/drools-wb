/*
 * Copyright 2011 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtable.client.widget.table.model.converters.column.impl;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;

import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionVariableColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTablePresenter;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTableView;
import org.kie.soup.project.datamodel.oracle.DataType;
import org.kie.soup.project.datamodel.oracle.OperatorsOracle;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseHeaderMetaData;

@Dependent
public class BRLConditionVariableColumnConverter extends BaseColumnConverterImpl {

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean handles(final BaseColumn column) {
        return column instanceof BRLConditionVariableColumn;
    }

    @Override
    public GridColumn<?> convertColumn(final BaseColumn column,
                                       final GuidedDecisionTablePresenter.Access access,
                                       final GuidedDecisionTableView gridWidget) {
        return convertColumn((BRLConditionVariableColumn) column,
                             access,
                             gridWidget);
    }

    private GridColumn<?> convertColumn(final BRLConditionVariableColumn column,
                                        final GuidedDecisionTablePresenter.Access access,
                                        final GuidedDecisionTableView gridWidget) {
        //Check if the column has an enumeration
        final String factType = column.getFactType();
        final String factField = column.getFactField();
        final DataType.DataTypes dataType = columnUtilities.getDataType(column);
        if (oracle.hasEnums(factType,
                            factField)) {
            if (OperatorsOracle.operatorRequiresList(column.getOperator())) {
                return newMultipleSelectEnumColumn(factType,
                                                   factField,
                                                   column,
                                                   access,
                                                   gridWidget);
            } else {
                return newSingleSelectionEnumColumn(factType,
                                                    factField,
                                                    dataType,
                                                    column,
                                                    access,
                                                    gridWidget);
            }
        }

        return newColumn(column,
                         access,
                         gridWidget);
    }

    @Override
    public List<GridColumn.HeaderMetaData> makeHeaderMetaData(final BaseColumn column) {
        return new ArrayList<GridColumn.HeaderMetaData>() {{
            add(new BaseHeaderMetaData(column.getHeader(),
                                       ConditionCol52.class.getName()));
            if (column instanceof BRLConditionVariableColumn) {
                BRLConditionVariableColumn brlConditionColumn = (BRLConditionVariableColumn) column;
                if (brlConditionColumn.getVarName() != null && !brlConditionColumn.getVarName().isEmpty()) {
                    add(new BaseHeaderMetaData(brlConditionColumn.getVarName(),
                                               column.getHeader())
                    );
                }
            }
        }};
    }
}
