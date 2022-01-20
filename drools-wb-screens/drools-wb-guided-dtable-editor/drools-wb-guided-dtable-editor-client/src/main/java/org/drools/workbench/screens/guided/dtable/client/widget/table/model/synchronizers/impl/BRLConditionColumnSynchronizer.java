/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.guided.dtable.client.widget.table.model.synchronizers.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.enterprise.context.Dependent;

import org.drools.workbench.models.datamodel.rule.CompositeFactPattern;
import org.drools.workbench.models.datamodel.rule.CompositeFieldConstraint;
import org.drools.workbench.models.datamodel.rule.FactPattern;
import org.drools.workbench.models.datamodel.rule.FieldConstraint;
import org.drools.workbench.models.datamodel.rule.IFactPattern;
import org.drools.workbench.models.datamodel.rule.IPattern;
import org.drools.workbench.models.datamodel.rule.RuleModel;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraint;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionVariableColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumnFieldDiff;
import org.drools.workbench.models.guided.dtable.shared.model.DTCellValue52;
import org.drools.workbench.screens.guided.dtable.client.widget.table.events.gwt.BoundFactsChangedEvent;
import org.drools.workbench.screens.guided.dtable.client.widget.table.model.synchronizers.ModelSynchronizer;
import org.drools.workbench.screens.guided.dtable.client.widget.table.model.synchronizers.ModelSynchronizer.VetoDeletePatternInUseException;
import org.drools.workbench.screens.guided.dtable.client.widget.table.model.synchronizers.ModelSynchronizer.VetoException;
import org.drools.workbench.screens.guided.dtable.client.widget.table.model.synchronizers.ModelSynchronizer.VetoUpdatePatternInUseException;

@Dependent
public class BRLConditionColumnSynchronizer extends BaseColumnSynchronizer<BaseColumnSynchronizer.ColumnMetaData, BaseColumnSynchronizer.ColumnMetaData, BaseColumnSynchronizer.ColumnMetaData> {

    @Override
    public int priority() {
        return 3;
    }

    @Override
    public boolean handlesAppend(final MetaData metaData) throws VetoException {
        return handlesUpdate(metaData);
    }

    @Override
    public void append(final ColumnMetaData metaData) throws VetoException {
        //Check operation is supported
        if (!handlesAppend(metaData)) {
            return;
        }

        final BRLConditionColumn column = (BRLConditionColumn) metaData.getColumn();
        model.getConditions().add(column);
        for (BRLConditionVariableColumn childModelColumn : column.getChildColumns()) {
            synchroniseAppendColumn(childModelColumn);
        }
    }

    @Override
    public boolean handlesUpdate(final MetaData metaData) throws VetoException {
        if (!(metaData instanceof ColumnMetaData)) {
            return false;
        }
        return ((ColumnMetaData) metaData).getColumn() instanceof BRLConditionColumn;
    }

    @Override
    public List<BaseColumnFieldDiff> update(final ColumnMetaData originalMetaData,
                                            final ColumnMetaData editedMetaData) throws VetoException {
        //Check operation is supported
        if (!(handlesUpdate(originalMetaData) && handlesUpdate(editedMetaData))) {
            return Collections.emptyList();
        }

        //Check whether any discarded bindings were used. If so veto the update.
        final BRLConditionColumn originalColumn = (BRLConditionColumn) originalMetaData.getColumn();
        final BRLConditionColumn editedColumn = (BRLConditionColumn) editedMetaData.getColumn();

        final Set<String> originalBindings = getPatternBindings(originalColumn);
        final Set<String> editedBindings = getPatternBindings(editedColumn);
        originalBindings.removeAll(editedBindings);
        for (String binding : originalBindings) {
            if (rm.isBoundFactUsed(binding)) {
                throw new VetoUpdatePatternInUseException();
            }
        }

        final List<BaseColumnFieldDiff> diffs = originalColumn.diff(editedColumn);

        //Copy existing data for re-use if applicable
        final Map<String, List<DTCellValue52>> originalColumnsData = new HashMap<>();
        for (BRLConditionVariableColumn variable : originalColumn.getChildColumns()) {
            int iColumnIndex = model.getExpandedColumns().indexOf(variable);
            final List<DTCellValue52> originalColumnData = new ArrayList<>();
            final String key = makeUpdateBRLConditionColumnKey(variable);
            for (List<DTCellValue52> row : model.getData()) {
                originalColumnData.add(row.get(iColumnIndex));
            }
            originalColumnsData.put(key,
                                    originalColumnData);
        }

        //Insert new columns setting data from that above, if applicable. Column visibility is handled here too.
        model.getConditions().add(model.getConditions().indexOf(originalColumn),
                                  editedColumn);
        for (BRLConditionVariableColumn childModelColumn : editedColumn.getChildColumns()) {
            final String key = makeUpdateBRLConditionColumnKey(childModelColumn);
            if (originalColumnsData.containsKey(key)) {
                final List<DTCellValue52> originalColumnData = originalColumnsData.get(key);
                synchroniseAppendColumn(childModelColumn,
                                        originalColumnData);
            } else {
                synchroniseAppendColumn(childModelColumn);
            }
        }

        //Delete columns for the original definition
        doDelete(originalColumn);

        //Signal patterns changed event to Decision Table Widget
        final BoundFactsChangedEvent pce = new BoundFactsChangedEvent(rm.getLHSBoundFacts());
        eventBus.fireEvent(pce);

        return diffs;
    }

    @Override
    public boolean handlesDelete(final MetaData metaData) throws VetoException {
        if (!(metaData instanceof ColumnMetaData)) {
            return false;
        }
        final boolean isBRLConditionColumn = ((ColumnMetaData) metaData).getColumn() instanceof BRLConditionColumn;
        final boolean isBRLConditionVariableColumn = ((ColumnMetaData) metaData).getColumn() instanceof BRLConditionVariableColumn;

        return isBRLConditionColumn || isBRLConditionVariableColumn;
    }

    @Override
    public void delete(final ColumnMetaData metaData) throws VetoException {
        //Check operation is supported
        if (!handlesDelete(metaData)) {
            return;
        }

        if (metaData.getColumn() instanceof BRLConditionColumn) {
            final BRLConditionColumn column = (BRLConditionColumn) metaData.getColumn();

            //If Pattern has been updated and there was only one child column then original Pattern will be deleted
            final Set<String> bindings = getPatternBindings(column);
            for (String binding : bindings) {
                if (rm.isBoundFactUsed(binding)) {
                    throw new VetoDeletePatternInUseException();
                }
            }

            doDelete(column);
        } else if (metaData.getColumn() instanceof BRLConditionVariableColumn) {
            doDelete((BRLConditionVariableColumn) metaData.getColumn());
        }
    }

    private void doDelete(final BRLConditionVariableColumn column) {
        final int iFirstColumnIndex = model.getExpandedColumns().indexOf(column);
        synchroniseDeleteColumn(iFirstColumnIndex);
        final BRLConditionColumn brlColumn = model.getBRLColumn(column);
        brlColumn.getChildColumns().remove(column);

        for (IPattern iPattern : brlColumn.getDefinition()) {
            if (iPattern instanceof FactPattern) {
                removePattern(column, (FactPattern) iPattern);
            } else if (iPattern instanceof CompositeFactPattern) {
                for (IFactPattern childPattern : ((CompositeFactPattern) iPattern).getPatterns()) {
                    if (childPattern instanceof FactPattern) {
                        removePattern(column,
                                      (FactPattern) childPattern);
                    }
                }
            }
        }
    }

    private void removePattern(final BRLConditionVariableColumn column,
                               final FactPattern factPattern) {
        final FieldConstraint[] constraints = factPattern.getConstraintList().getConstraints();
        for (int i = 0; i < constraints.length; i++) {
            FieldConstraint constraint = constraints[i];
            if (constraint instanceof CompositeFieldConstraint) {
                final CompositeFieldConstraint compositeFieldConstraint = (CompositeFieldConstraint) constraint;
                final int compositeFieldConstraintIndexByVar = getCompositeFieldConstraintIndexByVar(compositeFieldConstraint,
                                                                                                     column.getVarName());

                if (compositeFieldConstraintIndexByVar >= 0) {
                    compositeFieldConstraint.removeConstraint(compositeFieldConstraintIndexByVar);
                    return;
                }
            } else if (constraint instanceof SingleFieldConstraint) {
                if (((SingleFieldConstraint) constraint).getValue().equals(column.getVarName())) {
                    factPattern.getConstraintList().removeConstraint(i);
                    return;
                }
            }
        }
    }

    private int getCompositeFieldConstraintIndexByVar(final CompositeFieldConstraint compositeFieldConstraint,
                                                      final String varName) {
        int index = -1;
        for (FieldConstraint fieldConstraint : compositeFieldConstraint.getConstraints()) {
            if (fieldConstraint instanceof SingleFieldConstraint) {
                if (Objects.equals(((SingleFieldConstraint) fieldConstraint).getValue(), varName)) {
                    return index + 1;
                }
            }
            index++;
        }
        return index;
    }

    private void doDelete(final BRLConditionColumn column) {
        if (column.getChildColumns().size() > 0) {
            final int iFirstColumnIndex = model.getExpandedColumns().indexOf(column.getChildColumns().get(0));
            for (int iColumnIndex = 0; iColumnIndex < column.getChildColumns().size(); iColumnIndex++) {
                synchroniseDeleteColumn(iFirstColumnIndex);
            }
        }
        model.getConditions().remove(column);
    }

    @Override
    public boolean handlesMoveColumnsTo(final List<? extends MetaData> metaData) throws VetoException {
        return isBRLFragment(metaData);
    }

    private boolean isBRLFragment(final List<? extends MetaData> metaData) {
        if (!metaData.stream().allMatch((c) -> (c instanceof MoveColumnToMetaData))) {
            return false;
        }
        if (!metaData.stream().allMatch((c) -> ((MoveColumnToMetaData) c).getColumn() instanceof BRLConditionVariableColumn)) {
            return false;
        }
        final MoveColumnToMetaData md = (MoveColumnToMetaData) metaData.get(0);
        final BRLConditionVariableColumn srcModelColumn = (BRLConditionVariableColumn) md.getColumn();
        final BRLConditionColumn srcModelPattern = model.getBRLColumn(srcModelColumn);
        return srcModelPattern.getChildColumns().size() == metaData.size();
    }

    @Override
    public void moveColumnsTo(final List<MoveColumnToMetaData> metaData) throws VetoException {
        //Check operation is supported
        if (!handlesMoveColumnsTo(metaData)) {
            return;
        }
        if (isBRLFragment(metaData)) {
            doMoveBRLFragment(metaData);
        } else {
            throw new ModelSynchronizer.MoveVetoException();
        }
    }

    private void doMoveBRLFragment(final List<MoveColumnToMetaData> metaData) throws VetoException {
        final MoveColumnToMetaData md = metaData.get(0);
        final BRLConditionVariableColumn srcModelColumn = (BRLConditionVariableColumn) md.getColumn();
        final BRLConditionColumn srcModelBRLFragment = model.getBRLColumn(srcModelColumn);
        if (srcModelBRLFragment == null) {
            throw new ModelSynchronizer.MoveVetoException();
        }
        final List<BRLConditionVariableColumn> srcModelBRLFragmentColumns = srcModelBRLFragment.getChildColumns();
        final int srcModelPatternConditionColumnCount = srcModelBRLFragmentColumns.size();
        if (srcModelPatternConditionColumnCount == 0) {
            throw new ModelSynchronizer.MoveVetoException();
        }
        if (srcModelPatternConditionColumnCount != metaData.size()) {
            throw new ModelSynchronizer.MoveVetoException();
        }

        final int tgtColumnIndex = md.getTargetColumnIndex();
        final int tgtPatternIndex = findTargetPatternIndex(md);
        final List<BaseColumn> allModelColumns = model.getExpandedColumns();

        moveModelData(tgtColumnIndex,
                      allModelColumns.indexOf(srcModelBRLFragmentColumns.get(0)),
                      allModelColumns.indexOf(srcModelBRLFragmentColumns.get(0)) + srcModelPatternConditionColumnCount - 1);

        model.getConditions().remove(srcModelBRLFragment);
        model.getConditions().add(tgtPatternIndex,
                                  srcModelBRLFragment);
    }

    private String makeUpdateBRLConditionColumnKey(final BRLConditionVariableColumn variable) {
        StringBuilder key = new StringBuilder(variable.getVarName()).append(":").append(variable.getFieldType()).append(":").append(variable.getFactField()).append(":").append(variable.getFactType());
        return key.toString();
    }

    private Set<String> getPatternBindings(final BRLConditionColumn column) {
        final Set<String> bindings = new HashSet<>();
        final List<IPattern> definition = column.getDefinition();
        final RuleModel rm = new RuleModel();
        rm.lhs = definition.toArray(new IPattern[definition.size()]);
        bindings.addAll(rm.getLHSVariables(true, true));
        return bindings;
    }
}
