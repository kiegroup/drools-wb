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

import java.util.Collections;
import java.util.Optional;

import org.drools.scenariosimulation.api.model.Background;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationViolation;
import org.drools.workbench.screens.scenariosimulation.client.enums.GRID_WIDGET;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioCellTextAreaSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationBuilders;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.kie.workbench.common.command.client.CommandResult;
import org.kie.workbench.common.command.client.CommandResultBuilder;
import org.kie.workbench.common.command.client.impl.CommandResultImpl;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getHeaderBuilder;
import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getScenarioGridColumn;

public abstract class AbstractScenarioGridCommand extends AbstractScenarioSimulationCommand {

    protected GRID_WIDGET gridWidget;

    /**
     * The <code>ScenarioSimulationContext.Status</code> to restore when calling <b>undo/redo</b>.
     * Needed only for <b>undoable</b> commands.
     */
    protected ScenarioSimulationContext.Status restorableStatus = null;

    /**
     * Calling this constructor will set the target <code>GRID_WIDGET</code>
     * @param gridWidget
     */
    protected AbstractScenarioGridCommand(final GRID_WIDGET gridWidget) {
        this.gridWidget = gridWidget;
    }

    protected AbstractScenarioGridCommand() {
        // CDI
    }

    @Override
    public CommandResult<ScenarioSimulationViolation> undo(ScenarioSimulationContext context) {
        if (restorableStatus == null) {
            String message = this.getClass().getSimpleName() + "restorableStatus status is null";
            throw new UnsupportedOperationException(message);
        }
        return setCurrentContext(context);
    }

    public CommandResult<ScenarioSimulationViolation> redo(ScenarioSimulationContext context) {
        if (restorableStatus == null) {
            String message = this.getClass().getSimpleName() + "restorableStatus status is null";
            throw new UnsupportedOperationException(message);
        }
        return setCurrentContext(context);
    }

    protected CommandResult<ScenarioSimulationViolation> setCurrentContext(ScenarioSimulationContext context) {
        try {
            final Simulation simulationToRestore = restorableStatus.getSimulation();
            final Background backgroundToRestore = restorableStatus.getBackground();
            if (simulationToRestore != null || backgroundToRestore != null) {
                final ScenarioSimulationContext.Status originalStatus = context.getStatus().cloneStatus();
                context.getSimulationGrid().getModel().clearSelections();
                context.getBackgroundGrid().getModel().clearSelections();
                if (simulationToRestore != null) {
                    context.getSimulationGrid().setContent(simulationToRestore, context.getSettings().getType());
                    context.getScenarioSimulationEditorPresenter().getModel().setSimulation(simulationToRestore);
                }
                if (backgroundToRestore != null) {
                    context.getBackgroundGrid().setContent(backgroundToRestore, context.getSettings().getType());
                    context.getScenarioSimulationEditorPresenter().getModel().setBackground(backgroundToRestore);
                }
                context.getScenarioSimulationEditorPresenter().reloadTestTools(true);
                context.setStatus(restorableStatus);
                restorableStatus = originalStatus;
                return commonExecution(context);
            } else {
                return new CommandResultImpl<>(CommandResult.Type.ERROR, Collections.singletonList(new ScenarioSimulationViolation("Simulation not set inside Model")));
            }
        } catch (Exception e) {
            return new CommandResultImpl<>(CommandResult.Type.ERROR, Collections.singleton(new ScenarioSimulationViolation(e.getMessage())));
        }
    }

    /**
     * Method called soon before actual <b>undo</b> and <b>redo</b> operations to preliminary execute a tab switch <b>without</b>
     * altering the call stack.
     * If the command change the status of a not shown grid, this switches the tab
     * @param context
     * @return <code>Optional&lt;CommandResult&lt;ScenarioSimulationViolation&gt;&gt;</code> of <code>CommandResultBuilder.SUCCESS</code>
     * if a tab switch happened, otherwise <code>Optional.empty()</code>
     */
    public Optional<CommandResult<ScenarioSimulationViolation>> commonUndoRedoPreexecution(final ScenarioSimulationContext context) {
        switch (gridWidget) {
            case SIMULATION:
                context.getScenarioSimulationEditorPresenter().selectSimulationTab();
                break;
            case BACKGROUND:
                context.getScenarioSimulationEditorPresenter().selectBackgroundTab();
                break;
            default:
                throw new IllegalStateException("Illegal GRID_WIDGET " + gridWidget);
        }
        context.getScenarioGridPanelByGridWidget(gridWidget).onResize();
        context.getScenarioGridPanelByGridWidget(gridWidget).select();
        return Optional.of(CommandResultBuilder.SUCCESS);
    }

    /**
     * Returns a <code>ScenarioGridColumn</code> with the following default values:
     * <p>
     * width: 150
     * </p>
     * <p>
     * isMovable: <code>false</code>;
     * </p>
     * <p>
     * isPropertyAssigned: <code>false</code>;
     * </p>
     * <p>
     * columnRenderer: new ScenarioGridColumnRenderer()
     * </p>
     * @param instanceTitle
     * @param propertyTitle
     * @param columnId
     * @param columnGroup
     * @param factMappingType
     * @param factoryHeader
     * @param factoryCell
     * @param placeHolder
     * @return
     */
    protected ScenarioGridColumn getScenarioGridColumnLocal(String instanceTitle,
                                                            String propertyTitle,
                                                            String columnId,
                                                            String columnGroup,
                                                            FactMappingType factMappingType,
                                                            ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader,
                                                            ScenarioCellTextAreaSingletonDOMElementFactory factoryCell,
                                                            String placeHolder) {
        ScenarioSimulationBuilders.HeaderBuilder headerBuilder = getHeaderBuilder(instanceTitle, propertyTitle, columnId, columnGroup, factMappingType, factoryHeader);
        return getScenarioGridColumn(headerBuilder, factoryCell, placeHolder);
    }

    protected Optional<FactIdentifier> getFactIdentifierByColumnTitle(String columnTitle, ScenarioSimulationContext context) {

        return context.getSelectedScenarioGridLayer().getScenarioGrid().getModel().getColumns().stream()
                .filter(column -> columnTitle.equals(((ScenarioGridColumn) column).getInformationHeaderMetaData().getTitle()))
                .findFirst()
                .map(column -> ((ScenarioGridColumn) column).getFactIdentifier());
    }
}
