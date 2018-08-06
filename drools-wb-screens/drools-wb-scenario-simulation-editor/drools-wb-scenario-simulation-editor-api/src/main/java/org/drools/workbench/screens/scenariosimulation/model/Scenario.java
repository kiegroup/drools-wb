/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.scenariosimulation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.jboss.errai.common.client.api.annotations.Portable;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Portable
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
        if (getFactMappingValue(factName, expressionIdentifier).isPresent()) {
            throw new IllegalArgumentException(
                    new StringBuilder().append("A fact value for expression '").append(expressionIdentifier.getName())
                            .append("' and fact '").append(factName).append("' already exist").toString());
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