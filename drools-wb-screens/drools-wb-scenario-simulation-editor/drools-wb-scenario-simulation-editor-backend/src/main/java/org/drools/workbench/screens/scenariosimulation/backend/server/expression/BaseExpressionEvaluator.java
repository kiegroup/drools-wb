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

import java.util.List;
import java.util.Objects;

public class BaseExpressionEvaluator implements ExpressionEvaluator {

    private final ClassLoader classLoader;

    public BaseExpressionEvaluator(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean evaluate(Object raw, Object resultValue) {
        if(!(raw instanceof String)) {
            if (areComparable(resultValue, raw)) {
                return ((Comparable) resultValue).compareTo(raw) == 0;
            }
            return Objects.equals(resultValue, raw);
        }


        // FIXME
        return true;



        /*
        String rawExpression = (String) raw;
        BaseExpressionOperator operator = BaseExpressionOperator.findOperator(rawExpression);
        List<String> expectedValueList = BaseExpressionOperator.cleanValueFromOperator(rawExpression);

        switch (operator) {
            case EQUALS:
                if (areComparable(resultValue, raw)) {
                    return ((Comparable) resultValue).compareTo(expectedValueList.get(0)) == 0;
                }
                return Objects.equals(resultValue, expectedValueList.get(0));
            case NOT_EQUALS:
                if (areComparable(resultValue, expectedValueList.get(0))) {
                    return ((Comparable) resultValue).compareTo(expectedValueList.get(0)) != 0;
                }
                return !Objects.equals(resultValue, expectedValueList.get(0));
            default:
                throw new UnsupportedOperationException(new StringBuilder().append("Operator ").append(operator.name())
                                                                .append(" is not supported").toString());
        }
        */
    }

    // FIXME to test
    @Override
    public Object extractSingleValue(Object raw) {
        if(!(raw instanceof String)) {
            return raw;
        }
        String rawValue = (String) raw;
        List<String> values = BaseExpressionOperator.cleanValueFromOperator(rawValue);
        if(values.size() != 1) {
            throw new IllegalArgumentException(new StringBuilder().append("Impossible to extract a single value from ")
                                                       .append(rawValue).toString());
        }
        return values.get(0);
    }

    private boolean areComparable(Object a, Object b) {
        return a instanceof Comparable && b instanceof Comparable;
    }
}
