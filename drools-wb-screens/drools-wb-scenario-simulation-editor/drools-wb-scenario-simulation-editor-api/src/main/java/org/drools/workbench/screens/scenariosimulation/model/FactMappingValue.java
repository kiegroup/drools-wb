package org.drools.workbench.screens.scenariosimulation.model;

import java.util.Objects;
import java.util.function.BiFunction;

public class FactMappingValue {

    private final String factName;
    private final ExpressionIdentifier expressionIdentifier;
    private final Object rawValue;
    private Operator operator = Operator.equals;

    public FactMappingValue(String factName, ExpressionIdentifier expressionIdentifier, Object rawValue) {
        this.factName = factName;
        this.expressionIdentifier = expressionIdentifier;
        this.rawValue = rawValue;
    }

    public FactMappingValue(String factName, ExpressionIdentifier expressionIdentifier, Object rawValue, Operator operator) {
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

    public Operator getOperator() {
        return operator;
    }

    public enum Operator {

        equals(Objects::equals),
        less_them((a, b) -> defaultComparator.apply(a, b) < 0),
        great_then((a, b) -> defaultComparator.apply(a, b) > 0);

        final BiFunction<Object, Object, Boolean> compare;

        Operator(BiFunction<Object, Object, Boolean> compare) {
            this.compare = compare;
        }

        public Boolean evaluate(Object resultValue, Object expectedValue) {
            return compare.apply(resultValue, expectedValue);
        }

    }

    private static BiFunction<Object, Object, Integer> defaultComparator = (a, b) -> {
        if (Comparable.class.isAssignableFrom(a.getClass()) && Comparable.class.isAssignableFrom(b.getClass())
                && a.getClass().equals(b.getClass())) {
            Comparable comparableA = (Comparable) a;
            return comparableA.compareTo(b);
        }
        throw new IllegalArgumentException("Object cannot be compared '" + a.getClass().getCanonicalName() + "'");
    };
}
