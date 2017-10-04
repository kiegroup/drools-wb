package org.drools.workbench.screens.guided.rule.client.editor.validator;

import java.util.Collection;
import java.util.stream.Collectors;

import org.guvnor.common.services.shared.validation.model.ValidationMessage;

public final class PatternBindingValidatorUtils {

    private PatternBindingValidatorUtils() {
    }

    public static String getValidationTextToDisplay(final Collection<PatternBindingValidator> validators,
                                                    final String variableName) {
        return validators
                .stream()
                .flatMap(v -> v.validate(variableName).stream())
                .map(ValidationMessage::getText)
                .collect(Collectors.joining("\n"));
    }

}
