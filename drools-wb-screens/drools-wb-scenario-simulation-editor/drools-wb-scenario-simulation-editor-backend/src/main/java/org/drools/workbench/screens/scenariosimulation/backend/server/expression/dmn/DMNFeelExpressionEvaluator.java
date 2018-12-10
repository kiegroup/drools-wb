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

package org.drools.workbench.screens.scenariosimulation.backend.server.expression.dmn;

import java.util.List;

import org.drools.workbench.screens.scenariosimulation.backend.server.expression.ExpressionEvaluator;
import org.kie.dmn.feel.FEEL;
import org.kie.dmn.feel.lang.EvaluationContext;
import org.kie.dmn.feel.lang.impl.EvaluationContextImpl;
import org.kie.dmn.feel.lang.impl.FEELEventListenersManager;
import org.kie.dmn.feel.runtime.UnaryTest;
import org.kie.internal.utils.ClassLoaderUtil;

// FIXME to test
public class DMNFeelExpressionEvaluator implements ExpressionEvaluator {

    private final FEEL feel = FEEL.newInstance();

    @Override
    public boolean evaluate(Object rawExpression, Object resultValue, Class<?> resultClass) {
        if(!(rawExpression instanceof String)) {
            throw new IllegalArgumentException("Raw expression should be a string");
        }
        // FIXME refactor to replace with Map with context
        EvaluationContext evaluationContext = new EvaluationContextImpl(ClassLoaderUtil.getClassLoader(null, null, true),
                                                                        new FEELEventListenersManager());
        List<UnaryTest> unaryTests = feel.evaluateUnaryTests((String) rawExpression);
        return unaryTests.stream().allMatch(unaryTest -> unaryTest.apply(evaluationContext, resultValue));
    }

    @Override
    public Object getValueForGiven(String className, Object raw, ClassLoader classLoader) {
        if(!(raw instanceof String)) {
            throw new IllegalArgumentException("Raw expression should be a string");
        }
        // FIXME refactor to replace with Map with context
        EvaluationContext evaluationContext = new EvaluationContextImpl(ClassLoaderUtil.getClassLoader(null, null, true),
                                                                        new FEELEventListenersManager());
        return feel.evaluate((String) raw, evaluationContext);
    }
}
