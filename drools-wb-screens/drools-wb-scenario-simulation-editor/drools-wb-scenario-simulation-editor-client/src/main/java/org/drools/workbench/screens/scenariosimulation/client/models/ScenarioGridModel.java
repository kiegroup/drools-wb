/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Random;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridReloadEvent;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.values.ScenarioGridCellValue;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridCell;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.Scenario;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.SimulationDescriptor;
import org.uberfire.ext.wires.core.grids.client.model.GridCell;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.model.GridRow;
import org.uberfire.ext.wires.core.grids.client.model.impl.BaseGridData;

public class ScenarioGridModel extends BaseGridData {

    private Simulation simulation;

    private EventBus eventBus;

    public ScenarioGridModel() {
    }

    public ScenarioGridModel(boolean isMerged) {
        super(isMerged);
    }

    /**
     * Method to bind the data serialized inside backend <code>ScenarioSimulationModel</code>
     * @param simulation
     */
    public void bindContent(Simulation simulation) {
        this.simulation = simulation;
        checkSimulation();
    }

    public void setEventBus(EventBus eventBus) {
        GWT.log("ScenarioGridModel " + this.toString() + " setEventBus " + eventBus.toString());
        this.eventBus = eventBus;
    }

    @Override
    public void appendColumn(final GridColumn<?> column) {
        commonAddColumn(-1, column);
    }

    /**
     * This method <i>append</i> a new row to the grid <b>and</b> to the underlying model
     * @param row
     */
    public void appendEditingRow(GridRow row) {
        super.appendRow(row);
        int rowIndex = getRowCount() - 1;
        commonAddRow(rowIndex);
    }

    /**
     * This method <i>insert</i> a new row to the grid <b>and</b> to the underlying model
     * @param row
     */
    public void insertEditingRow(int rowIndex, GridRow row) {
        super.insertRow(rowIndex, row);
        commonAddRow(rowIndex);
    }

    /**
     * This method <i>insert</i> a new column to the grid <b>and</b> to the underlying model
     * @param index
     * @param column
     */
    @Override
    public void insertColumn(final int index, final GridColumn<?> column) {
        commonAddColumn(index, column);
    }

    /**
     * This method <i>insert</i> a new column to the grid <b>without</b> modify underlying model
     * @param index
     * @param column
     */
    public void populateColumn(final int index, final GridColumn<?> column) {
        checkSimulation();
        super.insertColumn(index, column);
    }

    /**
     * This method <i>set</i> a cell value to the grid <b>and</b> to the underlying model
     * @param rowIndex
     * @param columnIndex
     * @param cellSupplier
     */
    @Override
    public Range setCell(int rowIndex, int columnIndex, Supplier<GridCell<?>> cellSupplier) {
        checkSimulation();
        Range toReturn = super.setCell(rowIndex, columnIndex, cellSupplier);
        try {
            Optional<?> optionalValue = getCellValue(getCell(rowIndex, columnIndex));
            if (!optionalValue.isPresent()) {
                return toReturn;
            }
            Object rawValue = optionalValue.get();
            if (rawValue instanceof String) { // Just to avoid unchecked cast - BaseGridData/GridRow should be generified
                final String cellValue = (String) rawValue;
                Scenario scenarioByIndex = simulation.getScenarioByIndex(rowIndex);
                FactMapping factMappingByIndex = simulation.getSimulationDescriptor().getFactMappingByIndex(columnIndex);
                FactIdentifier factIdentifier = factMappingByIndex.getFactIdentifier();
                ExpressionIdentifier expressionIdentifier = factMappingByIndex.getExpressionIdentifier();
                scenarioByIndex.addOrUpdateMappingValue(factIdentifier, expressionIdentifier, cellValue);
            } else {
                throw new IllegalArgumentException("Type not supported " + rawValue.getClass().getCanonicalName());
            }
        } catch (Throwable t) {
            eventBus.fireEvent(new ScenarioGridReloadEvent());
            return super.deleteCell(rowIndex, columnIndex);
        }
        return toReturn;
    }

    /**
     * This method <i>set</i> a cell value to the grid <b>without</b> modify underlying model
     * @param rowIndex
     * @param columnIndex
     * @param cellSupplier
     */
    public Range populateCell(int rowIndex, int columnIndex, Supplier<GridCell<?>> cellSupplier) {
        checkSimulation();
        return super.setCell(rowIndex, columnIndex, cellSupplier);
    }

    /**
     * Return the first index to the left of the given group, i.e. <b>excluded</b> the left-most index of <b>that</b> group
     * @param groupName
     * @return
     */
    public int getFirstIndexLeftOfGroup(String groupName) {
        // HORRIBLE TRICK BECAUSE gridColumn.getIndex() DOES NOT REFLECT ACTUAL POSITION, BUT ONLY ORDER OF INSERTION
        final Optional<Integer> first = this.getColumns()    // Retrieving the column list
                .stream()  // streaming
                .filter(gridColumn -> gridColumn.getHeaderMetaData().get(1).getColumnGroup().equals(groupName))  // filtering by group name
                .findFirst()
                .map(gridColumn -> {
                    int indexOfColumn = this.getColumns().indexOf(gridColumn);
                    return indexOfColumn > -1 ? indexOfColumn: 0;   // mapping the retrieved column to its index inside the list, or 0
                });
        return first.orElseGet(() -> 0); // returning the retrieved value or, if null, 0
    }

    /**
     * Return the first index to the right of the given group, i.e. <b>excluded</b> the right-most index of <b>that</b> group
     * @param groupName
     * @return
     */
    public int getFirstIndexRightOfGroup(String groupName) {
        // HORRIBLE TRICK BECAUSE gridColumn.getIndex() DOES NOT REFLECT ACTUAL POSITION, BUT ONLY ORDER OF INSERTION
        final Optional<Integer> last = this.getColumns()    // Retrieving the column list
                .stream()  // streaming
                .filter(gridColumn -> gridColumn.getHeaderMetaData().get(1).getColumnGroup().equals(groupName))  // filtering by group name
                .reduce((first, second) -> second)  // reducing to have only the last element
                .map(gridColumn -> {
                    int indexOfColumn = this.getColumns().indexOf(gridColumn);
                    return indexOfColumn > -1 ? indexOfColumn + 1 : getColumnCount();   // mapping the retrieved column to its index inside the list +1, or to the total number of columns
                });
        return last.orElseGet(this::getColumnCount); // returning the retrieved value or, if null, the total number of columns
    }

    /**
     * Returns how many columns are already in place for the given group
     * @param groupName
     * @return
     */
    public long getGroupSize(String groupName) {
        return this.getColumns()
                .stream()
                .filter(gridColumn -> gridColumn.getHeaderMetaData().get(1).getColumnGroup().equals(groupName))
                .count();
    }

    public void clear() {
        // Deleting rows
        int to = getRowCount();
        IntStream.range(0, to)
                .map(i -> to - i - 1)
                .forEach(this::deleteRow);
        List<GridColumn<?>> copyList = new ArrayList<>(getColumns());
        copyList.forEach(this::deleteColumn);
        // clear can be called before bind
        if (simulation != null) {
            simulation.clear();
        }
    }

    public Optional<Simulation> getSimulation() {
        return Optional.ofNullable(simulation);
    }

    /**
     * This method <i>add</i> or <i>insert</i> a new column to the grid <b>and</b> to the underlying model, depending on the index value.
     * If index == -1 -> add, otherwise insert
     * @param index
     * @param column
     */
    protected void commonAddColumn(final int index, final GridColumn<?> column) {
        checkSimulation();
        SimulationDescriptor simulationDescriptor = simulation.getSimulationDescriptor();
        ScenarioHeaderMetaData scenarioHeaderMetaData = (ScenarioHeaderMetaData) column.getHeaderMetaData().get(1);
        String title = scenarioHeaderMetaData.getTitle();
        String group = scenarioHeaderMetaData.getColumnGroup();
        String columnId = scenarioHeaderMetaData.getColumnId();
        FactIdentifier factIdentifier = FactIdentifier.create(columnId, String.class.getCanonicalName());
        ExpressionIdentifier ei = ExpressionIdentifier.create(columnId, FactMappingType.valueOf(group));
        if (index == -1) {  // This is actually an append
            super.appendColumn(column);
        } else {
            super.insertColumn(index, column);
        }
        final int columnIndex = index == -1 ? getColumnCount() : index;
        try {
            simulationDescriptor.addFactMapping(columnIndex, title, factIdentifier, ei);
        } catch (Throwable t) {
            GWT.log("DELETING COLUMN " + column);
            super.deleteColumn(column);
            eventBus.fireEvent(new ScenarioGridReloadEvent());
            return;
        }
        final List<Scenario> scenarios = simulation.getScenarios();
        IntStream.range(0, scenarios.size())
                .forEach(rowIndex -> {
                    String value = title + "-" + Random.nextInt();
                    setCell(rowIndex, columnIndex, () -> new ScenarioGridCell(new ScenarioGridCellValue(value)));
                });
    }

    protected void commonAddRow(int rowIndex) {
        Scenario scenario = simulation.addScenario();
        final SimulationDescriptor simulationDescriptor = simulation.getSimulationDescriptor();
        IntStream.range(0, getColumnCount()).forEach(columnIndex -> {
            // TODO CHECK IF STILL WORKING AFTER DZONCA MODIFICATION  2955
            final FactMapping factMappingByIndex = simulationDescriptor.getFactMappingByIndex(columnIndex);
            String value = factMappingByIndex.getExpressionAlias() + "-" + Random.nextInt();
            scenario.addMappingValue(factMappingByIndex.getFactIdentifier(), factMappingByIndex.getExpressionIdentifier(), value);
            setCell(rowIndex, columnIndex, () -> new ScenarioGridCell(new ScenarioGridCellValue(value)));
        });
    }

    private void checkSimulation() {
        Objects.requireNonNull(simulation, "Bind a simulation to the ScenarioGridModel to use it");
    }

    // Helper method to avoid potential NPE
    private Optional<?> getCellValue(GridCell<?> gridCell) {
        if (gridCell == null || gridCell.getValue() == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(gridCell.getValue().getValue());
    }
}