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
package org.drools.workbench.screens.guided.dtable.backend.server.conversion.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.drools.workbench.models.datamodel.rule.ActionFieldList;
import org.drools.workbench.models.datamodel.rule.ActionFieldValue;
import org.drools.workbench.models.datamodel.rule.FactPattern;
import org.drools.workbench.models.datamodel.rule.FieldNatureType;
import org.drools.workbench.models.datamodel.rule.IAction;
import org.drools.workbench.models.datamodel.rule.IPattern;
import org.drools.workbench.models.guided.dtable.shared.model.BRLColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionColumn;

public class ColumnContext {

    private int brlActionColumnCount = 0;

    private final List<String> addedInserts = new ArrayList<>();

    private final Map<BRLColumn, List<FromTo>> map = new HashMap<>();
    private final Map<IPattern, List<String>> conditionVariablesByDefinition = new HashMap<>();
    private final VariableOperators variableOperators = new VariableOperators();

    public void put(final BRLColumn brlColumn,
                    final FromTo childColumn) {
        if (!map.containsKey(brlColumn)) {
            map.put(brlColumn, new ArrayList<>());
        }
        if (!map.get(brlColumn).contains(childColumn)) {
            map.get(brlColumn).add(childColumn);
        }
    }

    public List<FromTo> getCols(final BRLColumn baseColumn) {
        return map.get(baseColumn);
    }

    public List<ValuePlaceHolder> getVariablesInOrderOfUse(final IAction iAction) {
        final List<ValuePlaceHolder> result = new ArrayList<>();

        if (iAction instanceof ActionFieldList) {

            for (final ActionFieldValue fieldValue : ((ActionFieldList) iAction).getFieldValues()) {
                if (fieldValue.getNature() == FieldNatureType.TYPE_LITERAL) {
                    result.add(new ValuePlaceHolder(ValuePlaceHolder.Type.VALUE,
                                                    fieldValue.getValue()));
                } else if (fieldValue.getNature() == FieldNatureType.TYPE_TEMPLATE) {
                    result.add(new ValuePlaceHolder(ValuePlaceHolder.Type.VARIABLE,
                                                    fieldValue.getValue()));
                }
            }
        }
        return result;
    }

    public List<String> getVariablesInOrderOfUse(final BRLConditionColumn brlColumn) {

        final List<String> result = new ArrayList<>();
        for (final IPattern iPattern : brlColumn.getDefinition()) {
            result.addAll(getVariablesInOrderOfUse(iPattern));
        }
        return result;
    }

    public List<String> getVariablesInOrderOfUse(final IPattern iPattern) {
        final List<String> result = new ArrayList<>();
        if (!conditionVariablesByDefinition.containsKey(iPattern)) {
            conditionVariablesByDefinition.put(iPattern, new ArrayList<>());
        }

        if (iPattern instanceof FactPattern) {
            addBoundName(((FactPattern) iPattern).getBoundName());
            result.addAll(new ConstraintVisitor(iPattern,
                                                conditionVariablesByDefinition,
                                                variableOperators).visit());
        }
        return result;
    }

    private List<String> getVariables(final IPattern iPattern) {
        return conditionVariablesByDefinition.get(iPattern);
    }

    public int getAmountOfUniqueVariables(final IPattern iPattern) {
        return new HashSet<>(getVariables(iPattern)).size();
    }

    public void addBoundName(final String boundName) {
        if (StringUtils.isNotEmpty(boundName)) {
            addedInserts.add(boundName);
        }
    }

    public boolean isBoundNameFree(final String boundName) {
        return !addedInserts.contains(boundName);
    }

    public String getNextFreeColumnFactName() {
        return "brlColumnFact" + brlActionColumnCount++;
    }

    public VariableOperators getVariableOperators() {
        return variableOperators;
    }
}
