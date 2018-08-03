package org.drools.workbench.screens.scenariosimulation.model;

import java.util.Objects;

public class FactIdentifier {

    private final String name;
    private final Class<?> clazz;

    public FactIdentifier(String name, Class<?> clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FactIdentifier that = (FactIdentifier) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, clazz);
    }
}
