/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.client.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import com.google.gwt.core.client.GWT;
import org.uberfire.ext.wires.core.grids.client.model.GridCell;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseGridData;

public class ScenarioGridModel extends BaseGridData {

    private Optional<Map<Integer, String>> optionalHeadersMap = Optional.empty();
    private Optional<Map<Integer, Map<Integer, String>>> optionalRowsMap = Optional.empty();

    public ScenarioGridModel() {
        super();
    }

    public ScenarioGridModel(boolean isMerged) {
        super(isMerged);
    }

    /**
     * Method to bind the data serialized inside backend <code>ScenarioSimulationModel</code>
     * @param headersMap
     * @param rowsMap
     */
    public void bindContent(Map<Integer, String> headersMap, Map<Integer, Map<Integer, String>> rowsMap) {
        this.optionalHeadersMap = Optional.of(headersMap);
        this.optionalRowsMap = Optional.of(rowsMap);
    }

    @Override
    public void appendColumn(GridColumn<?> column) {
        super.appendColumn(column);
        optionalHeadersMap.ifPresent(headersMap -> {
            int columnIndex = getColumnCount() - 1;
            headersMap.put(columnIndex, column.getHeaderMetaData().get(0).getTitle());
        });
    }

    @Override
    public Range setCell(int rowIndex, int columnIndex, Supplier<GridCell<?>> cellSupplier) {
        Range toReturn =  super.setCell(rowIndex, columnIndex, cellSupplier);
        optionalRowsMap.ifPresent(rowsMap -> {
            final GridCell<String> addedCell = (GridCell<String>) getCell(rowIndex, columnIndex);
            final String cellValue = addedCell.getValue().getValue();
            rowsMap.computeIfPresent(rowIndex, (integer, integerStringMap) -> {
                integerStringMap.put(columnIndex, cellValue);
                return integerStringMap;
            });
            rowsMap.computeIfAbsent(rowIndex, integer -> {
                Map<Integer, String> toReturn1 = new HashMap<>(1);
                toReturn1.put(columnIndex, cellValue);
                return toReturn1;
            });
        });
        return toReturn;
    }

    public void clear() {
        GWT.log("ScenarioGridModel " +
                        "clear  " + System.identityHashCode(this));
        GWT.log("Before Deleting rows getRowCount() " + getRowCount());
        // Deleting rows
        IntStream.range(0, getRowCount()).forEach(this::deleteRow);
        GWT.log("After Deleting rows getRowCount() " + getRowCount());
        GWT.log("Before Deleting columns getColumns() " + getColumns());
        // Deleting columns
        getColumns().forEach(this::deleteColumn);
        GWT.log("After Deleting columns getColumns() " + getColumns());
    }
}