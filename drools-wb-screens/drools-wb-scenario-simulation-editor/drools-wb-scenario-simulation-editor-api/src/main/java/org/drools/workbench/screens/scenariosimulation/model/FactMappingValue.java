package org.drools.workbench.screens.scenariosimulation.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class FactMappingValue {

    private final String factName;
    private final ExpressionIdentifier expressionIdentifier;
    private final Object rawValue;
    private FactMappingValueOperator operator = FactMappingValueOperator.EQUALS;

    public FactMappingValue(String factName, ExpressionIdentifier expressionIdentifier, Object rawValue) {
        this.factName = factName;
        this.expressionIdentifier = expressionIdentifier;
        this.rawValue = rawValue;
    }

    public FactMappingValue(String factName, ExpressionIdentifier expressionIdentifier, Object rawValue, FactMappingValueOperator operator) {
        this(factName, expressionIdentifier, rawValue);
        this.operator = operator;
    }

    public String getFactName() {
        return factName;
    }

    public ExpressionIdentifier getExpressionIdentifier() {
        return expressionIdentifier;
    }

    public Object getRawValue() {
        return rawValue;
    }

    public FactMappingValueOperator getOperator() {
        return operator;
    }

}
