package org.drools.workbench.screens.scenariosimulation.model;

import org.jboss.errai.common.client.api.annotations.Portable;

@Portable
public class ExpressionElement {

    private final String step;

    public ExpressionElement(String step) {
        this.step = step;
    }

    public String getStep() {
        return step;
    }
}
