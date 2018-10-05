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

import static org.drools.workbench.screens.scenariosimulation.backend.server.util.ScenarioBeanUtil.convertValue;

public enum BaseExpressionOperator {

    EQUALS("=") {
        @SuppressWarnings("unchecked")
        @Override
        public boolean eval(Object rawValue, Object resultValue, ClassLoader classLoader) {
            Object parsedResults = rawValue;
            if (parsedResults instanceof String) {
                Optional<Object> firstValue = cleanValue((String) rawValue).stream()
                        .map(elem -> convertValue(resultValue.getClass().getCanonicalName(), elem, classLoader))
                        .findFirst();
                parsedResults = convertValue(resultValue.getClass().getCanonicalName(), firstValue.orElse(null), classLoader);
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
            Object parsedResults = rawValue;
            if (parsedResults instanceof String) {
                Optional<Object> firstValue = cleanValue((String) rawValue).stream()
                        .map(elem -> convertValue(resultValue.getClass().getCanonicalName(), elem, classLoader))
                        .findFirst();
                parsedResults = convertValue(resultValue.getClass().getCanonicalName(), firstValue.orElse(null), classLoader);
            }
            if (parsedResults == null) {
                return resultValue == null;
            }
            if (areComparable(resultValue, parsedResults)) {
                return ((Comparable) resultValue).compareTo(parsedResults) != 0;
            }
            return !Objects.equals(resultValue, parsedResults);
        }
    };
//    ,
//    // FIXME
//    RANGE {
//        @Override
//        protected Optional<String> match(String value) {
//            return Optional.empty();
//        }
//    },
//    LIST_OF_VALUES;

    final List<String> symbols;

    BaseExpressionOperator(String... symbols) {
        this.symbols = Arrays.asList(symbols);
        // sort symbols by descending length to match longer symbols first
        this.symbols.sort((a, b) -> Integer.compare(a.length(), b.length()) * -1);
    }

    public abstract boolean eval(Object rawValue, Object resultValue, ClassLoader classLoader);

    protected Optional<String> match(String value) {
        return getSymbols().stream().filter(value::startsWith).findFirst();
    }

    protected List<String> cleanValue(String value) {
        Optional<String> operatorSymbol = match(value);
        if (operatorSymbol.isPresent()) {
            String symbolToRemove = operatorSymbol.get();
            int index = value.indexOf(symbolToRemove);
            value = value.substring(index + symbolToRemove.length()).trim();
        }

        String returnValue = value.trim();
        // empty string is equivalent to null only if there is no operator symbol
        return "".equals(returnValue) && !operatorSymbol.isPresent() ? Collections.emptyList() : Collections.singletonList(returnValue);
    }

    public static List<String> cleanValueFromOperator(String rawValue) {
        if (rawValue == null) {
            return Collections.emptyList();
        }
        String value = rawValue.trim();
        BaseExpressionOperator operator = findOperator(value);
        return operator.cleanValue(value);
    }

    private List<String> getSymbols() {
        return symbols;
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

    private static boolean areComparable(Object a, Object b) {
        return a instanceof Comparable && b instanceof Comparable;
    }
}