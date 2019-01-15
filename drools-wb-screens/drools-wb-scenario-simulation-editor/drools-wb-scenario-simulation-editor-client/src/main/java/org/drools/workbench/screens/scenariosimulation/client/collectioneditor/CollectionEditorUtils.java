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
package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.dom.client.SpanElement;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ConstantHolder.FA_ANGLE_DOWN;
import static org.drools.workbench.screens.scenariosimulation.client.utils.ConstantHolder.FA_ANGLE_RIGHT;

public class CollectionEditorUtils {


    public static boolean isShown(SpanElement faAngleRight) {
        return faAngleRight.getClassName().contains(FA_ANGLE_DOWN);
    }

    public static  void toggleRowExpansion(SpanElement faAngleRight, boolean toExpand) {
        if (toExpand) {
            faAngleRight.addClassName(FA_ANGLE_DOWN);
            faAngleRight.removeClassName(FA_ANGLE_RIGHT);
        } else {
            faAngleRight.addClassName(FA_ANGLE_RIGHT);
            faAngleRight.removeClassName(FA_ANGLE_DOWN);
        }
    }
}
