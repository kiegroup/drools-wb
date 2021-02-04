/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtable.backend.server.conversion;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.drools.workbench.models.datamodel.rule.ActionCallMethod;
import org.drools.workbench.models.datamodel.rule.ActionFieldValue;
import org.drools.workbench.models.datamodel.rule.ActionInsertFact;
import org.drools.workbench.models.datamodel.rule.ActionSetField;
import org.drools.workbench.models.datamodel.rule.FieldNatureType;
import org.drools.workbench.models.datamodel.rule.IAction;
import org.drools.workbench.models.guided.dtable.shared.model.ActionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.BRLActionColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLActionVariableColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionVariableColumn;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.screens.guided.dtable.backend.server.conversion.util.ColumnContext;
import org.drools.workbench.screens.guided.dtable.backend.server.conversion.util.FromTo;
import org.drools.workbench.screens.guided.dtable.backend.server.conversion.util.ValuePlaceHolder;

/**
 * Splits the BRL column so that each value has a column.
 */
public class BRLColumnSubHeaderBuilderByPatterns
        implements BRLColumnSubHeaderBuilder {

    private SubHeaderBuilder subHeaderBuilder;
    private ColumnContext columnContext;

    private GuidedDecisionTable52 dtable;

    public BRLColumnSubHeaderBuilderByPatterns(final SubHeaderBuilder subHeaderBuilder,
                                               final ColumnContext columnContext,
                                               final GuidedDecisionTable52 dtable) {
        this.subHeaderBuilder = subHeaderBuilder;
        this.columnContext = columnContext;
        this.dtable = dtable;
    }

    @Override
    public void buildBrlActions(final BRLActionColumn brlColumn) {

        final Iterator<IAction> definitionIterator = brlColumn.getDefinition().iterator();

        while (definitionIterator.hasNext()) {
            final IAction iAction = definitionIterator.next();
            final Iterator<ValuePlaceHolder> valuesIterator = columnContext.getVariablesInOrderOfUse(iAction).iterator();

            final String boundName = getBoundName(iAction);

            if (iAction instanceof ActionInsertFact) {
                final ActionInsertFact actionInsertFact = (ActionInsertFact) iAction;
                subHeaderBuilder.getColumnContext().put(brlColumn,
                                                        FromTo.makePlaceHolder(dtable.getExpandedColumns().indexOf(brlColumn.getChildColumns().get(0)),
                                                                               subHeaderBuilder.getTargetColumnIndex()));
                final boolean madeInsert = subHeaderBuilder.makeInsert(boundName,
                                                                       actionInsertFact.getFactType());
                if (madeInsert && ((ActionInsertFact) iAction).getFieldValues().length != 0) {
                    subHeaderBuilder.incrementTargetIndex();
                }
            } else if (iAction instanceof ActionCallMethod && hasNoVariables(valuesIterator)) {
                subHeaderBuilder.addMethodCallWithoutParameters(brlColumn.getHeader(),
                                                                (ActionCallMethod) iAction);

                updateColumnContext(brlColumn,
                                    brlColumn.getChildColumns().get(0));
            }

            while (valuesIterator.hasNext()) {
                final ValuePlaceHolder value = valuesIterator.next();

                final ActionCol52 childColumn = getChildActionColumn(value,
                                                                     brlColumn.getChildColumns());

                if (iAction instanceof ActionCallMethod) {
                    final StringBuilder params = new StringBuilder();
                    final int methodParameterCount = ((ActionCallMethod) iAction).getFieldValues().length;
                    if (methodParameterCount == 1) {
                        params.append(wrapParameter(childColumn,
                                                    "$param"));
                    } else if (valuesIterator.hasNext()) {
                        if (isThereJustOneVariableInParameters(((ActionCallMethod) iAction).getFieldValues())) {
                            params.append(wrapParameter(childColumn,
                                                        "$param"));
                        } else {
                            params.append(wrapParameter(childColumn,
                                                        "$1"));
                        }
                        for (int i = 1; i < methodParameterCount; i++) {
                            final ValuePlaceHolder next = valuesIterator.next();

                            params.append(", ");
                            switch (next.getType()) {
                                case VARIABLE:
                                    params.append(wrapParameter(childColumn,
                                                                "$" + (i + 1)));
                                    updateColumnContext(brlColumn,
                                                        getChildActionColumn(next,
                                                                             brlColumn.getChildColumns()));
                                    break;
                                case VALUE:
                                    params.append(next.getValue());
                                    break;
                            }
                        }
                    }

                    subHeaderBuilder.addMethodCallWithParameters(brlColumn.getHeader(),
                                                                 (ActionCallMethod) iAction,
                                                                 params.toString());
                    updateColumnContext(brlColumn,
                                        childColumn);
                    if (valuesIterator.hasNext()) {
                        subHeaderBuilder.incrementTargetIndex();
                    }
                } else {
                    if (childColumn instanceof BRLActionVariableColumn) {
                        addBRLActionVariableColumn(brlColumn,
                                                   boundName,
                                                   (BRLActionVariableColumn) childColumn);
                        updateColumnContext(brlColumn,
                                            childColumn);
                        if (valuesIterator.hasNext()) {
                            subHeaderBuilder.incrementTargetIndex();
                        }
                    }
                }
            }
            if (definitionIterator.hasNext()) {
                subHeaderBuilder.incrementTargetIndex();
            }
        }
    }

    private String wrapParameter(final ActionCol52 childColumn,
                                 final String var) {
        if (childColumn instanceof BRLActionVariableColumn) {
            return subHeaderBuilder.getRHSParamWithWrapper(((BRLActionVariableColumn) childColumn).getFieldType(),
                                                           var);
        } else {
            return var;
        }
    }

    private boolean isThereJustOneVariableInParameters(final ActionFieldValue[] actionFieldValues) {
        boolean result = false;
        for (final ActionFieldValue actionFieldValue : actionFieldValues) {
            if (actionFieldValue.getNature() == FieldNatureType.TYPE_TEMPLATE) {
                if (result) {
                    return false;
                } else {
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean hasNoVariables(final Iterator<ValuePlaceHolder> variablesIterator) {
        return !variablesIterator.hasNext();
    }

    public void addBRLActionVariableColumn(final BRLActionColumn brlColumn,
                                           final String boundName,
                                           final BRLActionVariableColumn childColumn) {
        subHeaderBuilder.addBRLActionVariableColumn(brlColumn.getHeader(),
                                                    boundName,
                                                    childColumn.getFactType(),
                                                    childColumn.getFactField(),
                                                    "");
    }

    /**
     * Adds a column that creates a new fact and inserts it.
     */
    public void addInsertColumn(final BRLActionColumn brlColumn,
                                final BRLActionVariableColumn childColumn) {
        subHeaderBuilder.getColumnContext().put(brlColumn,
                                                FromTo.makePlaceHolder(dtable.getExpandedColumns().indexOf(childColumn),
                                                                       subHeaderBuilder.getTargetColumnIndex() - 1));
    }

    private String getBoundName(final IAction iAction) {
        if (iAction instanceof ActionInsertFact && StringUtils.isNotEmpty(((ActionInsertFact) iAction).getBoundName())) {
            return ((ActionInsertFact) iAction).getBoundName();
        } else if (iAction instanceof ActionSetField && StringUtils.isNotEmpty(((ActionSetField) iAction).getVariable())) {
            return ((ActionSetField) iAction).getVariable();
        } else {
            return columnContext.getNextFreeColumnFactName();
        }
    }

    @Override
    public void buildBrlConditions(final BRLConditionColumn brlColumn) {
        final Iterator<String> variablesIterator = columnContext.getVariablesInOrderOfUse(brlColumn).iterator();
        while (variablesIterator.hasNext()) {
            final ConditionCol52 childColumn = getChildConditionColumn(variablesIterator.next(),
                                                                       brlColumn.getChildColumns());
            subHeaderBuilder.addCondition(childColumn);
            updateColumnContext(brlColumn,
                                childColumn);

            if (variablesIterator.hasNext()) {
                subHeaderBuilder.incrementTargetIndex();
            }
        }
    }

    private ActionCol52 getChildActionColumn(final ValuePlaceHolder value,
                                             final List<BRLActionVariableColumn> childColumns) {
        for (BRLActionVariableColumn childColumn : childColumns) {
            if (Objects.equals(childColumn.getVarName(), value.getValue())) {
                return childColumn;
            }
        }

        throw new IllegalArgumentException("Found a variable for a column that does not exist");
    }

    private ConditionCol52 getChildConditionColumn(final String varName,
                                                   final List<BRLConditionVariableColumn> childColumns) {
        for (BRLConditionVariableColumn childColumn : childColumns) {
            if (Objects.equals(childColumn.getVarName(), varName)) {
                return childColumn;
            }
        }

        throw new IllegalArgumentException("Found a variable for a column that does not exist");
    }

    public void updateColumnContext(final BRLConditionColumn brlColumn,
                                    final ConditionCol52 childColumn) {
        subHeaderBuilder.getColumnContext().put(brlColumn,
                                                FromTo.makeFromTo(dtable.getExpandedColumns().indexOf(childColumn),
                                                                  subHeaderBuilder.getTargetColumnIndex()));
    }

    public void updateColumnContext(final BRLActionColumn brlColumn,
                                    final ActionCol52 childColumn) {
        updateColumnContext(brlColumn,
                            dtable.getExpandedColumns().indexOf(childColumn));
    }

    public void updateColumnContext(final BRLActionColumn brlColumn,
                                    final int from) {
        subHeaderBuilder.getColumnContext().put(brlColumn,
                                                FromTo.makeFromTo(from,
                                                                  subHeaderBuilder.getTargetColumnIndex()));
    }
}
