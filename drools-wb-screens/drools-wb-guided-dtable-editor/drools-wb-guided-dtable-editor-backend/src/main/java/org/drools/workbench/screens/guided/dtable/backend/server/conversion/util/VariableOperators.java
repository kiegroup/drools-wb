/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtable.backend.server.conversion.util;

import java.util.HashMap;
import java.util.Map;

public class VariableOperators {

    private final Map<String, String> variableOperatorPairs = new HashMap<>();

    public void append(final String variable,
                       final String operator) {
        variableOperatorPairs.put(variable, operator);
    }

    public String getOperator(final String variable) {
        return variableOperatorPairs.get(variable);
    }
}
