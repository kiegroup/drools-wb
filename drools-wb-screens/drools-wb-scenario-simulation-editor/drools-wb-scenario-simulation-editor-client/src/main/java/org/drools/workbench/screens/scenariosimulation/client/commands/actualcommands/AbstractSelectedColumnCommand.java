/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.IntStream;

import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.utils.ScenarioSimulationSharedUtils;
import org.uberfire.ext.wires.core.grids.client.model.GridData;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getPropertyMetaDataGroup;
import static org.drools.workbench.screens.scenariosimulation.model.FactMapping.getPropertyPlaceHolder;

/**
 * <b>Abstract</b> <code>Command</code> class which assures that a <code>ScenarioColumn</code> is selected.
 */
public abstract class AbstractSelectedColumnCommand extends AbstractScenarioSimulationCommand {

    public AbstractSelectedColumnCommand() {
        super(true);
    }

    protected abstract void executeIfSelectedColumn(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn);

    @Override
    protected void internalExecute(ScenarioSimulationContext context) {
        getSelectedColumn(context).ifPresent(selectedColumn -> executeIfSelectedColumn(context, selectedColumn));
    }

    /**
     * It inserts a new <code>ScenarioGridColumn</code> in <code>ScenarioGridModel</code>
     * @param context It contains the <b>Context</b> inside which the commands will be executed
     * @param selectedColumn The selected <code>ScenarioGridColumn</code> where the command was launched
     * @param cloneInstance If true, it create a new column inside the same instance of the selected column
     * @param isAsProperty Used to define in which position the new column should be added (in the same instance group or outside)
     * @return The created <code>ScenarioGridColumn</code>
     */
    protected ScenarioGridColumn insertNewColumn(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, boolean cloneInstance, boolean isAsProperty) {
        final ScenarioSimulationContext.Status status = context.getStatus();
        final ScenarioHeaderMetaData selectedInformationHeaderMetaData = selectedColumn.getInformationHeaderMetaData();
        String columnGroup = selectedInformationHeaderMetaData.getColumnGroup();
        String originalInstanceTitle = selectedInformationHeaderMetaData.getTitle();
        final FactMappingType factMappingType = FactMappingType.valueOf(columnGroup.toUpperCase());
        final Map.Entry<String, String> validPlaceholders = context.getModel().getValidPlaceholders();
        String instanceTitle = cloneInstance ? originalInstanceTitle : validPlaceholders.getKey();
        String propertyTitle = validPlaceholders.getValue();
        String placeHolder = ScenarioSimulationEditorConstants.INSTANCE.defineValidType();
        final ScenarioGridColumn scenarioGridColumnLocal = getScenarioGridColumnLocal(instanceTitle,
                                                                                      propertyTitle,
                                                                                      String.valueOf(new Date().getTime()),
                                                                                      columnGroup,
                                                                                      factMappingType,
                                                                                      context.getScenarioHeaderTextBoxSingletonDOMElementFactory(),
                                                                                      context.getScenarioCellTextAreaSingletonDOMElementFactory(),
                                                                                      placeHolder);
        if (cloneInstance) {
            scenarioGridColumnLocal.setFactIdentifier(selectedColumn.getFactIdentifier());
        }
        int columnPosition = -1;
        if (isAsProperty) {
            columnPosition = status.isRight() ? status.getColumnIndex() + 1 : status.getColumnIndex();
        } else {
            GridData.Range instanceRange = context.getModel().getInstanceLimits(status.getColumnIndex());
            columnPosition = status.isRight() ? instanceRange.getMaxRowIndex() + 1 : instanceRange.getMinRowIndex();
        }
        scenarioGridColumnLocal.setInstanceAssigned(cloneInstance);
        scenarioGridColumnLocal.setPropertyAssigned(false);
        context.getModel().insertColumn(columnPosition, scenarioGridColumnLocal);
        return scenarioGridColumnLocal;
    }

    /**
     * Sets the instance header for a <code>ScenarioSimulationContext</code>.
     * @param context It contains the <b>Context</b> inside which the commands will be executed
     * @param selectedColumn The selected <code>ScenarioGridColumn</code> where the command was launched
     */
    protected void setInstanceHeader(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String alias, String fullClassName) {
        int columnIndex = context.getModel().getColumns().indexOf(selectedColumn);
        final FactIdentifier factIdentifier = setEditableHeadersAndGetFactIdentifier(context, selectedColumn, alias, fullClassName);
        setInstanceHeaderMetaData(selectedColumn, alias, factIdentifier);
        final ScenarioHeaderMetaData propertyHeaderMetaData = selectedColumn.getPropertyHeaderMetaData();
        setPropertyMetaData(propertyHeaderMetaData, getPropertyPlaceHolder(columnIndex), false, selectedColumn, ScenarioSimulationEditorConstants.INSTANCE.defineValidType());
        context.getModel().updateColumnInstance(columnIndex, selectedColumn);
    }

    /**
     * Returns an <code>Optional<ScenarioGridColumn></code> for a <code>ScenarioSimulationContext</code>.
     * @param context It contains the <b>Context</b> inside which the commands will be executed
     * @return
     */
    protected Optional<ScenarioGridColumn> getSelectedColumn(ScenarioSimulationContext context) {
        return Optional.ofNullable((ScenarioGridColumn) context.getModel().getSelectedColumn());
    }

    /**
     * Returns the full package <code>String</code> of a <code>ScenarioSimulationContext</code>.
     * @param context
     * @return
     */
    protected String getFullPackage(ScenarioSimulationContext context) {
        String fullPackage = context.getStatus().getFullPackage();
        if (!fullPackage.endsWith(".")) {
            fullPackage += ".";
        }
        return fullPackage;
    }

    /**
     * Sets the editable headers on a given <code>ScenarioGridColumn</code> and returns a <code>FactIdentifier</code>.
     * @param context
     * @param selectedColumn
     * @param aliasName
     * @param canonicalClassName
     * @return
     */
    protected FactIdentifier setEditableHeadersAndGetFactIdentifier(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String aliasName, String canonicalClassName) {
        final ScenarioSimulationModel.Type simulationModelType = context.getModel().getSimulation().get().getSimulationDescriptor().getType();
        selectedColumn.setEditableHeaders(!simulationModelType.equals(ScenarioSimulationModel.Type.DMN));
        String nameToUseForCreation = simulationModelType.equals(ScenarioSimulationModel.Type.DMN) ? aliasName : selectedColumn.getInformationHeaderMetaData().getColumnId();
        return getFactIdentifierByColumnTitle(aliasName, context).orElse(FactIdentifier.create(nameToUseForCreation, canonicalClassName));
    }

    /**
     * Sets the metadata for an instance header on a given <code>ScenarioGridColumn</code>.
     * @param scenarioGridColumn
     * @param aliasName
     * @param factIdentifier
     */
    protected void setInstanceHeaderMetaData(ScenarioGridColumn scenarioGridColumn, String aliasName, FactIdentifier factIdentifier) {
        scenarioGridColumn.getInformationHeaderMetaData().setTitle(aliasName);
        scenarioGridColumn.setInstanceAssigned(true);
        scenarioGridColumn.setFactIdentifier(factIdentifier);
    }

    protected void setPropertyHeader(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String value, String propertyClass) {
        this.setPropertyHeader(context, selectedColumn, value, propertyClass, null);
    }

    /**
     * It assigns a property to the selected <code>ScenarioGridColumn</code>
     * @param context It contains the <b>Context</b> inside which the commands will be executed
     * @param selectedColumn The selected <code>ScenarioGridColumn</code> where the command was launched
     * @param value It contains the path instance_name.property.name (eg. Author.isAlive)
     * @param propertyClass it contains the full classname of the instance (eg. com.Author)
     */
    protected void setPropertyHeader(ScenarioSimulationContext context, ScenarioGridColumn selectedColumn, String value, String propertyClass, String propertyHeaderTitle) {
        int columnIndex = context.getModel().getColumns().indexOf(selectedColumn);
        final List<String> valuesElements = Arrays.asList(value.split("\\."));
        String aliasName = valuesElements.get(0);
        String canonicalClassName = getFullPackage(context) + aliasName;
        final FactIdentifier factIdentifier = setEditableHeadersAndGetFactIdentifier(context, selectedColumn, aliasName, canonicalClassName);
        String className = factIdentifier.getClassName();
        propertyHeaderTitle = propertyHeaderTitle != null ? propertyHeaderTitle : getPropertyHeaderTitle(context, value, factIdentifier);
        final GridData.Range instanceLimits = context.getModel().getInstanceLimits(columnIndex);
        IntStream.range(instanceLimits.getMinRowIndex(), instanceLimits.getMaxRowIndex() + 1)
                .forEach(index -> {
                    final ScenarioGridColumn scenarioGridColumn = (ScenarioGridColumn) context.getModel().getColumns().get(index);
                    if (!scenarioGridColumn.isInstanceAssigned()) { // We have not defined the instance, yet
                        setInstanceHeaderMetaData(scenarioGridColumn, aliasName, factIdentifier);
                    }
                });
        selectedColumn.getPropertyHeaderMetaData().setColumnGroup(getPropertyMetaDataGroup(selectedColumn.getInformationHeaderMetaData().getColumnGroup()));
        setPropertyMetaData(selectedColumn.getPropertyHeaderMetaData(), propertyHeaderTitle, false, selectedColumn, ScenarioSimulationEditorConstants.INSTANCE.insertValue());
        selectedColumn.setPropertyAssigned(true);
        context.getModel().updateColumnProperty(columnIndex,
                                                selectedColumn,
                                                value,
                                                propertyClass, context.getStatus().isKeepData());
        if (ScenarioSimulationSharedUtils.isCollection(propertyClass)) {
            final SortedMap<String, FactModelTree> dataObjectFieldsMap = context.getDataObjectFieldsMap();
            final List<String> classNameElements = Arrays.asList(className.split("\\."));
            final FactModelTree nestedFactModelTree = navigateComplexObject(dataObjectFieldsMap.get(classNameElements.get(classNameElements.size() - 1)),
                                                                            classNameElements,
                                                                            dataObjectFieldsMap);

            selectedColumn.setFactory(context.getCollectionEditorSingletonDOMElementFactory());
            final FactMapping factMappingByIndex = context.getModel().getSimulation().get().getSimulationDescriptor().getFactMappingByIndex(columnIndex);
            factMappingByIndex.setGenericTypes(nestedFactModelTree.getGenericTypeInfo(valuesElements.get(valuesElements.size() - 1)));
        } else {
            selectedColumn.setFactory(context.getScenarioCellTextAreaSingletonDOMElementFactory());
        }
        if (context.getScenarioSimulationEditorPresenter() != null) {
            context.getScenarioSimulationEditorPresenter().reloadRightPanel(false);
        }
    }

    /**
     * Sets the title and readOnly setting of a property header and sets the place holder on a given <code>ScenarioGridColumn</code>.
     * @param propertyHeaderMetaData
     * @param title
     * @param readOnly
     * @param selectedColumn
     * @param placeHolder
     */
    protected void setPropertyMetaData(ScenarioHeaderMetaData propertyHeaderMetaData, String title, boolean readOnly, ScenarioGridColumn selectedColumn, String placeHolder) {
        propertyHeaderMetaData.setTitle(title);
        propertyHeaderMetaData.setReadOnly(readOnly);
        selectedColumn.setPlaceHolder(placeHolder);
    }

    protected FactModelTree navigateComplexObject(FactModelTree factModelTree, List<String> elements, SortedMap<String, FactModelTree> dataObjectFieldsMap) {
        FactModelTree nestedFactModelTree = factModelTree;
        if (elements.size() > 2) {
            for (String step : elements.subList(1, elements.size() - 1)) {
                if (nestedFactModelTree.getExpandableProperties().containsKey(step)) {
                    nestedFactModelTree = dataObjectFieldsMap.get(factModelTree.getExpandableProperties().get(step));
                }
            }
        }
        return nestedFactModelTree;
    }

    protected String getPropertyHeaderTitle(ScenarioSimulationContext context, String value, FactIdentifier factIdentifier) {
        String toReturn = value.contains(".") ? value.substring(value.indexOf(".") + 1) : "value";
        final List<FactMapping> factMappingsByFactName = context.getStatus().getSimulation().getSimulationDescriptor().getFactMappingsByFactName(factIdentifier.getName());
        final Optional<FactMapping> matchingFactMapping = factMappingsByFactName.stream()
                .filter(factMapping -> Objects.equals(factMapping.getFullExpression(), value))
                .findFirst();
        if (matchingFactMapping.isPresent()) {
            toReturn = matchingFactMapping.get().getExpressionAlias();
        }
        return toReturn;
    }
}