package org.drools.workbench.screens.scenariosimulation.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SimulationDescriptor {

    private final List<FactMapping> factMappings = new ArrayList<>();

    public List<FactMapping> getFactMappings() {
        return Collections.unmodifiableList(factMappings);
    }

    public Set<FactIdentifier> getFactIdentifiers() {
        return factMappings.stream().map(FactMapping::getFactIdentifier).collect(Collectors.toSet());
    }

    public FactMapping getFactMappingByIndex(int index) {
        return factMappings.get(index);
    }
    
    public List<FactMapping> getFactMappingsByFactName(String factName) {
        return internalFilter(e -> e.getFactIdentifier().getName().equalsIgnoreCase(factName));
    }

    public Optional<FactMapping> getFactMapping(ExpressionIdentifier ei, FactIdentifier factIdentifier) {
        List<FactMapping> factMappings = internalFilter(e -> e.getExpressionIdentifier().equals(ei) &&
                e.getFactIdentifier().equals(factIdentifier));
        return factMappings.stream().findFirst();
    }

    public FactIdentifier newFactIdentifier(String factName, Class<?> clazz) {
        return new FactIdentifier(factName, clazz);
    }

    private List<FactMapping> internalFilter(Predicate<FactMapping> predicate) {
        return factMappings.stream().filter(predicate).collect(Collectors.toList());
    }

    public FactMapping addFactMapping(ExpressionIdentifier expressionIdentifier, FactIdentifier factIdentifier) {
        return addFactMapping(factMappings.size(), expressionIdentifier, factIdentifier);
    }

    public FactMapping addFactMapping(int index, ExpressionIdentifier expressionIdentifier, FactIdentifier factIdentifier) {
        if(getFactMapping(expressionIdentifier, factIdentifier).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("An expression with name '%s' already exists for the fact '%s'", expressionIdentifier.getName(), factIdentifier.getName()));
        }
        FactMapping factMapping = new FactMapping(expressionIdentifier, factIdentifier);
        factMappings.add(index, factMapping);
        return factMapping;
    }

}
