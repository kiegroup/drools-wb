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

public class BaseExpressionEvaluator implements ExpressionEvaluator {

    private final ClassLoader classLoader;

    public BaseExpressionEvaluator(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean evaluate(Object raw, Object resultValue) {
        if (!(raw instanceof String)) {
            return BaseExpressionOperator.EQUALS.eval(raw, resultValue, classLoader);
        }

        String rawValue = (String) raw;
        return BaseExpressionOperator.findOperator(rawValue).eval(rawValue, resultValue, classLoader);
    }

    @Override
    public Object extractSingleValue(Object raw) {
        if (!(raw instanceof String)) {
            return raw;
        }
        String rawValue = (String) raw;
        List<String> values = BaseExpressionOperator.cleanValueFromOperator(rawValue);
        if (values.size() > 1) {
            throw new IllegalArgumentException(new StringBuilder().append("Too many values extracted from ")
                                                       .append(rawValue).toString());
        }
        return values.size() == 0 ? null : values.get(0);
    }
}
