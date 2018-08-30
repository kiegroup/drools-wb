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

package org.drools.workbench.screens.scenariosimulation.backend.server.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public class ScenarioBeanUtil {

    private ScenarioBeanUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T fillBean(String className, Map<List<String>, Object> params) throws ReflectiveOperationException {
        Class<T> clazz = (Class<T>) Class.forName(className);

        final T beanToFill = clazz.newInstance();

        for (Map.Entry<List<String>, Object> param : params.entrySet()) {
            fillProperty(beanToFill, param.getKey(), param.getValue());
        }

        return beanToFill;
    }

    private static <T> void fillProperty(T beanToFill, List<String> steps, Object propertyValue) throws ReflectiveOperationException {
        List<String> pathToProperty = steps.subList(0, steps.size() - 1);
        String lastStep = steps.get(steps.size() - 1);

        Object currentObject = navigateToObject(beanToFill, pathToProperty);

        Field last = currentObject.getClass().getDeclaredField(lastStep);
        last.setAccessible(true);
        last.set(currentObject, propertyValue);
    }

    private static Object getOrCreate(Field declaredField, Object currentObject) throws ReflectiveOperationException {
        try {
            return declaredField.get(currentObject);
        } catch (NullPointerException e) {
            Object initValue = declaredField.getDeclaringClass().newInstance();
            declaredField.set(currentObject, initValue);
            return initValue;
        }
    }

    public static Object navigateToObject(Object rootObject, List<String> steps) throws ReflectiveOperationException {
        if (steps.size() < 1) {
            throw new IllegalArgumentException(new StringBuilder().append("Invalid path to a property: path='")
                                                       .append(String.join(".", steps)).append("'").toString());
        }

        Class<?> currentClass = rootObject.getClass();
        Object currentObject = rootObject;

        for (String step : steps) {
            Field declaredField = currentClass.getDeclaredField(step);
            declaredField.setAccessible(true);
            currentClass = declaredField.getDeclaringClass();
            currentObject = getOrCreate(declaredField, currentObject);
        }

        return currentObject;
    }
}
