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
package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationViolation;
import org.drools.workbench.screens.scenariosimulation.client.factories.FactoryProvider;
import org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioHeaderTextBoxSingletonDOMElementFactory;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationBuilders;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.Simulation;
import org.kie.workbench.common.command.client.AbstractCommand;
import org.kie.workbench.common.command.client.CommandResult;
import org.kie.workbench.common.command.client.CommandResultBuilder;
import org.kie.workbench.common.command.client.impl.CommandResultImpl;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getHeaderBuilder;
import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.getScenarioGridColumn;

/**
 * <b>Abstract</b> <code>Command</code> class to provide common methods used by actual implementations
 */
public abstract class AbstractScenarioSimulationCommand extends AbstractCommand<ScenarioSimulationContext, ScenarioSimulationViolation> {

    private static final AtomicLong COUNTER_ID = new AtomicLong();

    /**
     * Auto-generated incremental identifier used  to uniquely identify each command
     */
    private final long id;

    /**
     * Flag that indicates if the command is <b>undoable</b>. Default is <code>false</code>
     */
    private final boolean undoable;

    /**
     * The <code>ScenarioSimulationContext.Status</code> to restore when calling <b>undo/redo</b>.
     * Needed only for <b>undoable</b> commands.
     */
    protected ScenarioSimulationContext.Status restorableStatus = null;

    /**
     * Calling this constructor will set the command as <b>not undoable</b>
     */
    public AbstractScenarioSimulationCommand() {
        this.id = COUNTER_ID.getAndIncrement();
        this.undoable = false;
    }

    /**
     * Calling this constructor will set the command as <b>undoable</b>
     * @param restorableStatus
     */
    public AbstractScenarioSimulationCommand(ScenarioSimulationContext.Status restorableStatus) {
        this.id = COUNTER_ID.getAndIncrement();
        this.undoable = true;
        this.restorableStatus = restorableStatus;
    }

    public long getId() {
        return id;
    }

    public boolean isUndoable() {
        return undoable;
    }

    @Override
    public CommandResult<ScenarioSimulationViolation> undo(ScenarioSimulationContext context) throws UnsupportedOperationException {
        if (!undoable || restorableStatus == null) {
            String message = !undoable ? this.getClass().getSimpleName() + " is not undoable" : "restorableStatus status is null";
            throw new UnsupportedOperationException(message);
        }
        return setCurrentContext(context);
    }

    public CommandResult<ScenarioSimulationViolation> redo(ScenarioSimulationContext context) throws UnsupportedOperationException {
        if (!undoable || restorableStatus == null) {
            String message = !undoable ? this.getClass().getSimpleName() + " is not redoable" : "restorableStatus status is null";
            throw new UnsupportedOperationException(message);
        }
        return setCurrentContext(context);
    }

    @Override
    public CommandResult<ScenarioSimulationViolation> execute(ScenarioSimulationContext context) {
        try {
            internalExecute(context);
            return commonExecution(context);
        } catch (Exception e) {
            return new CommandResultImpl<>(CommandResult.Type.ERROR, Collections.singleton(new ScenarioSimulationViolation(e.getMessage())));
        }
    }

    public ScenarioSimulationContext.Status getRestorableStatus() {
        return restorableStatus;
    }

    public void setRestorableStatus(ScenarioSimulationContext.Status restorableStatus) {
        this.restorableStatus = restorableStatus;
    }

    protected CommandResult<ScenarioSimulationViolation> setCurrentContext(ScenarioSimulationContext context) {
        try {
            final Simulation toRestore = restorableStatus.getSimulation();
            if (toRestore != null) {
                context.getScenarioSimulationEditorPresenter().getView().setContent(toRestore);
                context.getScenarioSimulationEditorPresenter().getModel().setSimulation(toRestore);
                context.setStatus(restorableStatus);
                return commonExecution(context);
            } else {
                return new CommandResultImpl<>(CommandResult.Type.ERROR, Collections.singletonList(new ScenarioSimulationViolation("Simulation not set inside Model")));
            }
        } catch (Exception e) {
            return new CommandResultImpl<>(CommandResult.Type.ERROR, Collections.singleton(new ScenarioSimulationViolation(e.getMessage())));
        }
    }

    protected abstract void internalExecute(ScenarioSimulationContext context);

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
     * @param scenarioGridPanel
     * @param gridLayer
     * @return
     */
    protected ScenarioGridColumn getScenarioGridColumnLocal(String instanceTitle,
                                                            String propertyTitle,
                                                            String columnId,
                                                            String columnGroup,
                                                            FactMappingType factMappingType,
                                                            ScenarioGridPanel scenarioGridPanel,
                                                            ScenarioGridLayer gridLayer,
                                                            String placeHolder) {
        ScenarioHeaderTextBoxSingletonDOMElementFactory factoryHeader = FactoryProvider.getHeaderTextBoxFactory(scenarioGridPanel, gridLayer);
        ScenarioSimulationBuilders.HeaderBuilder headerBuilder = getHeaderBuilder(instanceTitle, propertyTitle, columnId, columnGroup, factMappingType, factoryHeader);
        return getScenarioGridColumn(headerBuilder, scenarioGridPanel, gridLayer, placeHolder);
    }

    protected ScenarioGridColumn getScenarioGridColumnLocal(ScenarioSimulationBuilders.HeaderBuilder headerBuilder, ScenarioSimulationContext context) {
        // indirection add for test
        return getScenarioGridColumn(headerBuilder, context.getScenarioGridPanel(), context.getScenarioGridLayer(), ScenarioSimulationEditorConstants.INSTANCE.insertValue());
    }

    protected Optional<FactIdentifier> getFactIdentifierByColumnTitle(String columnTitle, ScenarioSimulationContext context) {

        return context.getScenarioGridLayer().getScenarioGrid().getModel().getColumns().stream()
                .filter(column -> columnTitle.equals(((ScenarioGridColumn) column).getInformationHeaderMetaData().getTitle()))
                .findFirst()
                .map(column -> ((ScenarioGridColumn) column).getFactIdentifier());
    }

    protected CommandResult<ScenarioSimulationViolation> commonExecution(ScenarioSimulationContext context) {
        context.getScenarioGridPanel().onResize();
        context.getScenarioGridPanel().select();
        return CommandResultBuilder.SUCCESS;
    }
}
