package org.drools.workbench.screens.scenariosimulation.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class FactMapping {

    private final List<ExpressionElement> expressionElements = new LinkedList<>();

    private final ExpressionIdentifier expressionIdentifier;

    private final FactIdentifier factIdentifier;

    private Class<?> clazz;

    public FactMapping(ExpressionIdentifier expressionIdentifier, FactIdentifier factIdentifier) {
        this.expressionIdentifier = expressionIdentifier;
        this.clazz = factIdentifier.getClazz();
        this.factIdentifier = factIdentifier;
    }

    public String getFullExpression() {
        return expressionElements.stream().map(ExpressionElement::getStep).collect(Collectors.joining("."));
    }

    public List<ExpressionElement> getExpressionElements() {
        return expressionElements;
    }

    public void addExpressionElement(String stepName, Class<?> clazz) {
        this.clazz = clazz;
        expressionElements.add(new ExpressionElement(stepName));
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public ExpressionIdentifier getExpressionIdentifier() {
        return expressionIdentifier;
    }

    public FactIdentifier getFactIdentifier() {
        return factIdentifier;
    }
}
