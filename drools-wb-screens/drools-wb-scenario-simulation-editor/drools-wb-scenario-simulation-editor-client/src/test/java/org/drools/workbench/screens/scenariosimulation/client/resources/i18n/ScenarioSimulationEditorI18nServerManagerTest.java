package org.drools.workbench.screens.scenariosimulation.client.resources.i18n;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.drools.scenariosimulation.api.utils.ConstantsHolder.VALUE;
import static org.junit.Assert.assertEquals;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioSimulationEditorI18nServerManagerTest {

    private FactMapping factMapping;

    @Before
    public void setup() {
        factMapping = new FactMapping(FactIdentifier.create("myType", "tMYTYPE"),
                                      ExpressionIdentifier.create(VALUE, FactMappingType.GIVEN));
        factMapping.setFactAlias("FactAlias");
        factMapping.setExpressionAlias("ExpressionAlias");
    }

    @Test
    public void retrieveMessageFieldAddedConstraint() {
        FactMappingValidationError error = FactMappingValidationError.createFieldAddedConstraintError(factMapping);
        String message = ScenarioSimulationEditorI18nServerManager.retrieveMessage(error);
        String expected = ScenarioSimulationEditorConstants.INSTANCE.scenarioValidationFieldAddedConstraintError();
        assertEquals(expected, message);
    }

    @Test
    public void retrieveMessageFieldRemovedConstraint() {
        FactMappingValidationError error = FactMappingValidationError.createFieldRemovedConstraintError(factMapping);
        String message = ScenarioSimulationEditorI18nServerManager.retrieveMessage(error);
        String expected = ScenarioSimulationEditorConstants.INSTANCE.scenarioValidationFieldRemovedConstraintError();
        assertEquals(expected, message);
    }

    @Test
    public void retrieveMessageFieldChangedError() {
        String newType = "newType";
        String type = "type";
        factMapping.addExpressionElement("step", type);
        FactMappingValidationError error = FactMappingValidationError.createFieldChangedError(factMapping, newType);
        String message = ScenarioSimulationEditorI18nServerManager.retrieveMessage(error);
        String expected = ScenarioSimulationEditorConstants.INSTANCE.scenarioValidationFieldChangedError(type, newType);
        assertEquals(expected, message);
    }

    @Test
    public void retrieveMessageNodeChangedError() {
        String newType = "newType";
        FactMappingValidationError error = FactMappingValidationError.createNodeChangedError(factMapping, newType);
        String message = ScenarioSimulationEditorI18nServerManager.retrieveMessage(error);
        String expected = ScenarioSimulationEditorConstants.INSTANCE.scenarioValidationNodeChangedError("tMYTYPE", newType);
        assertEquals(expected, message);
    }
}
