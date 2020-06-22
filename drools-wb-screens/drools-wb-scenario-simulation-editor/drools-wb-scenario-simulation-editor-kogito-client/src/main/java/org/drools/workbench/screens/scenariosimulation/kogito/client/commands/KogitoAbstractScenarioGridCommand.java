package org.drools.workbench.screens.scenariosimulation.kogito.client.commands;

import org.appformer.kogito.bridge.client.interop.WindowRef;
import org.appformer.kogito.bridge.client.stateControl.interop.StateControl;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationViolation;
import org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands.AbstractScenarioGridCommand;
import org.kie.workbench.common.command.client.CommandResult;

import javax.annotation.PostConstruct;
import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.function.Supplier;

@Decorator
public abstract class KogitoAbstractScenarioGridCommand extends AbstractScenarioGridCommand {

    @Delegate
    @Any
    @Inject
    AbstractScenarioGridCommand command;

    private Supplier<Boolean> envelopeAvailableSupplier;
    private Supplier<StateControl> stateControlSupplier;

    @PostConstruct
    public void setup() {
         envelopeAvailableSupplier = WindowRef::isEnvelopeAvailable;
         stateControlSupplier = StateControl::get;
    }

    @Override
    public CommandResult<ScenarioSimulationViolation> undo(ScenarioSimulationContext context) {
        //If running in Kogito we should initialize the Kogito StateControl undo/redo commands. Otherwise we should keep the key binding.
        if (isEnvelopeAvailable()) {
            stateControlSupplier.get().setRedoCommand(() -> command.undo(context));
        }
        return super.undo(context);
    }

    private boolean isEnvelopeAvailable() {
        return envelopeAvailableSupplier.get();
    }
}
