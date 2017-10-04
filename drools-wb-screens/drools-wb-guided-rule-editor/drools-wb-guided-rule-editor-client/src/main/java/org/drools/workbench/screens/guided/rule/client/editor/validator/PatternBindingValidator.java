package org.drools.workbench.screens.guided.rule.client.editor.validator;

import java.util.Collection;

import org.guvnor.common.services.shared.validation.model.ValidationMessage;

public interface PatternBindingValidator {

    Collection<ValidationMessage> validate(final String variableName);
}
