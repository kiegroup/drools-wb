package org.drools.workbench.screens.scenariosimulation.model;

import java.util.Objects;

public class ExpressionIdentifier {

    private final String name;
    private final FactMappingType type;

    public ExpressionIdentifier(String name, FactMappingType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public FactMappingType getType() {
        return type;
    }

    public static ExpressionIdentifier identifier(String name, FactMappingType type) {
        return new ExpressionIdentifier(name, type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExpressionIdentifier that = (ExpressionIdentifier) o;
        return Objects.equals(name, that.name) &&
                type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
