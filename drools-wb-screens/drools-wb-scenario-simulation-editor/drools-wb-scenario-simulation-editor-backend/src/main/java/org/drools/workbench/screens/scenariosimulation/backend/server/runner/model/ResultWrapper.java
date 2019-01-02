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

package org.drools.workbench.screens.scenariosimulation.backend.server.runner.model;

import java.util.function.Supplier;

/**
 * java.util.Optional clone to have the null result
 * @param <T>
 */
public class ResultWrapper<T> {

    private final boolean satisfied;

    private final T result;

    private ResultWrapper(T result, boolean satisfied) {
        this.satisfied = satisfied;
        this.result = result;
    }

    public static <T> ResultWrapper<T> createResult(T result) {
        return new ResultWrapper<>(result, true);
    }

    public static <T> ResultWrapper<T> createErrorResult() {
        return new ResultWrapper<>(null, false);
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public T getResult() {
        return result;
    }

    public T orElse(T defaultValue) {
        return satisfied ? result : defaultValue;
    }

    public T orElseGet(Supplier<T> defaultSupplier) {
        return satisfied ? result : defaultSupplier.get();
    }
}
