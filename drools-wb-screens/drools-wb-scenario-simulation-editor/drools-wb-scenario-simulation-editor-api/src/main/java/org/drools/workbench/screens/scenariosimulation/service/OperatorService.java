package org.drools.workbench.screens.scenariosimulation.service;

import org.drools.workbench.screens.scenariosimulation.model.FactMappingValueOperator;
import org.jboss.errai.bus.server.annotations.Remote;

@Remote
public interface OperatorService {

    Boolean evaluate(FactMappingValueOperator operator, Object resultValue, Object expectedValue);
}
