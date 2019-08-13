/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

import org.kie.dmn.core.impl.BaseDMNTypeImpl;
import org.kie.dmn.feel.lang.Type;
import org.kie.dmn.feel.lang.types.BuiltInType;

public class DMNTypeUtils {

    private DMNTypeUtils() {
    }

    /**
     * This method return first buildInType found or top level Type of the given DMNType
     * @param dmnType
     * @return
     */
    public static Type getRootType(BaseDMNTypeImpl dmnType) {
        if (dmnType.getFeelType() instanceof BuiltInType) {
            return dmnType.getFeelType();
        } else if (dmnType.getBaseType() != null) {
            return getRootType((BaseDMNTypeImpl) dmnType.getBaseType());
        }
        return dmnType.getFeelType();
    }
}
