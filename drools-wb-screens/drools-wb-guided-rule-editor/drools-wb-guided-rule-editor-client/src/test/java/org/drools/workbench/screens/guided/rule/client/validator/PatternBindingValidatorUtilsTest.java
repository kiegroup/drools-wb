package org.drools.workbench.screens.guided.rule.client.validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.drools.workbench.screens.guided.rule.client.editor.validator.PatternBindingValidator;
import org.drools.workbench.screens.guided.rule.client.editor.validator.PatternBindingValidatorUtils;
import org.guvnor.common.services.shared.message.Level;
import org.guvnor.common.services.shared.validation.model.ValidationMessage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PatternBindingValidatorUtilsTest {

    @Test
    public void getValidationTextToDisplayMatchingVariable() {
        String validationTextToDisplay = PatternBindingValidatorUtils.getValidationTextToDisplay(getPatternBindingValidators(),
                                                                                                 "variable");
        assertEquals("validator\nvalidator", validationTextToDisplay);
    }

    @Test
    public void getValidationTextToDisplayNonMatchingVariable() {
        String validationTextToDisplay = PatternBindingValidatorUtils.getValidationTextToDisplay(getPatternBindingValidators(),
                                                                                                 "nonMatchingVariable");
        assertEquals("", validationTextToDisplay);
    }

    private Collection<PatternBindingValidator> getPatternBindingValidators() {
        PatternBindingValidator validator = variableName -> {
            if ("variable".equals(variableName)) {
                return Arrays.asList(new ValidationMessage(Level.WARNING, "validator"));
            }
            return Collections.emptyList();
        };
        return Arrays.asList(validator, validator);
    }
}
