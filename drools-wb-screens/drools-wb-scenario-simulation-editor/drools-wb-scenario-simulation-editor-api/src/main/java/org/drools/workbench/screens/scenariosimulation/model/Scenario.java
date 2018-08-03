package org.drools.workbench.screens.scenariosimulation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Scenario {

    private final String description;
    private final List<FactMappingValue> factMappingValues = new ArrayList<>();

    public Scenario(String description) {
        this.description = description;
    }

    public List<FactMappingValue> getFactMappingValues() {
        return Collections.unmodifiableList(factMappingValues);
    }

    public FactMappingValue getFactMappingValueByIndex(int index) {
        return factMappingValues.get(index);
    }

    public FactMappingValue addMappingValue(String factName, ExpressionIdentifier expressionIdentifier, String value) {
        return addMappingValue(factMappingValues.size(), factName, expressionIdentifier, value);
    }

    public FactMappingValue addMappingValue(int index, String factName, ExpressionIdentifier expressionIdentifier, String value) {
        if(getFactMappingValue(factName, expressionIdentifier).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("A fact value for expression '%s' and fact '%s' already exist", expressionIdentifier.getName(), factName));
        }
        FactMappingValue factMappingValue = new FactMappingValue(factName, expressionIdentifier, value);
        factMappingValues.add(index, factMappingValue);
        return factMappingValue;
    }

    public Optional<FactMappingValue> getFactMappingValue(String factName, ExpressionIdentifier expressionIdentifier) {
        return factMappingValues.stream().filter(e -> e.getFactName().equalsIgnoreCase(factName) &&
                e.getExpressionIdentifier().equals(expressionIdentifier)).findFirst();
    }

    public List<FactMappingValue> getFactMappingValuesByFactName(String factName) {
        return factMappingValues.stream().filter(e -> e.getFactName().equalsIgnoreCase(factName)).collect(toList());
    }

    public String getDescription() {
        return description;
    }

    public Collection<String> getFactNames() {
        return factMappingValues.stream().map(FactMappingValue::getFactName).collect(toSet());
    }

}