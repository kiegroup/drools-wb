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
package org.drools.workbench.screens.scenariosimulation.backend.server.expression;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.drools.workbench.screens.scenariosimulation.backend.server.util.ScenarioBeanUtil.convertValue;

public enum BaseExpressionOperator {

    EQUALS("=") {
        @Override
        protected Object getValueForGiven(String className, String value, ClassLoader classLoader) {
            String returnValue = removeOperator(value);

            // empty string is equivalent to null only if there is no operator symbol
            returnValue = "".equals(returnValue) && !match(value).isPresent() ? null : returnValue;

            return convertValue(className, returnValue, classLoader);
        }

        @SuppressWarnings("unchecked")
        @Override
        public boolean eval(Object rawValue, Object resultValue, ClassLoader classLoader) {
            Object parsedResults = rawValue;
            if (parsedResults instanceof String) {
                parsedResults = getValueForGiven(resultValue.getClass().getCanonicalName(), (String) rawValue, classLoader);
            }
            if (parsedResults == null) {
                return resultValue == null;
            }
            if (areComparable(resultValue, parsedResults)) {
                return ((Comparable) resultValue).compareTo(parsedResults) == 0;
            }
            return Objects.equals(resultValue, parsedResults);
        }
    },
    NOT_EQUALS("!", "!=", "<>") {
        @SuppressWarnings("unchecked")
        @Override
        public boolean eval(Object rawValue, Object resultValue, ClassLoader classLoader) {
            Object valueToTest = rawValue;
            BaseExpressionOperator operator = EQUALS;
            // remove symbol to reuse EQUALS.eval
            if (valueToTest instanceof String) {
                String rawStringValue = (String) valueToTest;
                valueToTest = removeOperator(rawStringValue);
                operator = findOperator((String) valueToTest);
            }

            return !operator.eval(valueToTest, resultValue, classLoader);
        }
    },
    RANGE("<", ">", "<=", ">=") {
        @Override
        public boolean eval(Object raw, Object resultValue, ClassLoader classLoader) {
            if (!(raw instanceof String) || !match((String) raw).isPresent()) {
                return false;
            }
            String rawValue = (String) raw;
            return Arrays.stream(rawValue.split(",")).allMatch(elem -> checkStep(elem.trim(), resultValue, classLoader));
        }

        @SuppressWarnings("unchecked")
        boolean checkStep(String stepToCheck, Object resultValue, ClassLoader classLoader) {
            Optional<String> operatorSymbol = match(stepToCheck);
            if (!operatorSymbol.isPresent()) {
                throw new IllegalArgumentException(
                        new StringBuilder().append("Impossible to find the operator in expression ").append(stepToCheck).toString());
            }
            String operator = operatorSymbol.get();
            String cleanValue = removeOperator(stepToCheck);
            Object stepValue = convertValue(resultValue.getClass().getCanonicalName(), cleanValue, classLoader);
            if (!areComparable(stepValue, resultValue)) {
                return false;
            }
            Comparable a = (Comparable) resultValue;
            Comparable b = (Comparable) stepValue;
            switch (operator) {
                case "<":
                    return a.compareTo(b) < 0;
                case ">":
                    return a.compareTo(b) > 0;
                case "<=":
                    return a.compareTo(b) <= 0;
                case ">=":
                    return a.compareTo(b) >= 0;
                default:
                    throw new IllegalStateException(new StringBuilder().append("This should not happen ").append(operator).toString());
            }
        }
    },
    LIST_OF_VALUES("[") {
        @Override
        public boolean eval(Object rawValue, Object resultValue, ClassLoader classLoader) {
            return getValues(rawValue).stream().anyMatch(e -> EQUALS.eval(e, resultValue, classLoader));
        }

        private List<String> getValues(Object raw) {
            if (!(raw instanceof String) || !match((String) raw).isPresent()) {
                return Collections.emptyList();
            }
            String rawValue = ((String) raw).trim();
            if (!rawValue.endsWith("]")) {
                throw new IllegalArgumentException(new StringBuilder().append("Malformed expression: ").append(rawValue).toString());
            }
            return Stream.of(rawValue.substring(1, ((String) raw).length() - 1).split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
    };

    final List<String> symbols;

    BaseExpressionOperator(String... symbols) {
        this.symbols = Arrays.asList(symbols);
        // sort symbols by descending length to match longer symbols first
        this.symbols.sort((a, b) -> Integer.compare(a.length(), b.length()) * -1);
    }

    public static BaseExpressionOperator findOperator(String rawValue) {
        String value = rawValue.trim();
        for (BaseExpressionOperator factMappingValueOperator : BaseExpressionOperator.values()) {
            if (factMappingValueOperator.match(value).isPresent()) {
                return factMappingValueOperator;
            }
        }

        // Equals is the default
        return BaseExpressionOperator.EQUALS;
    }

    protected abstract boolean eval(Object rawValue, Object resultValue, ClassLoader classLoader);

    protected Object getValueForGiven(String className, String value, ClassLoader classLoader) {
        throw new IllegalStateException("This operator cannot be used into a Given clause");
    }

    protected Optional<String> match(String value) {
        value = value.trim();
        return symbols.stream().filter(value::startsWith).findFirst();
    }

    protected String removeOperator(String fullString) {
        Optional<String> operatorSymbol = match(fullString);
        String value = fullString;
        if (operatorSymbol.isPresent()) {
            String symbolToRemove = operatorSymbol.get();
            int index = value.indexOf(symbolToRemove);
            value = value.substring(index + symbolToRemove.length()).trim();
        }
        return value.trim();
    }

    private static boolean areComparable(Object a, Object b) {
        return a instanceof Comparable && b instanceof Comparable;
    }
}