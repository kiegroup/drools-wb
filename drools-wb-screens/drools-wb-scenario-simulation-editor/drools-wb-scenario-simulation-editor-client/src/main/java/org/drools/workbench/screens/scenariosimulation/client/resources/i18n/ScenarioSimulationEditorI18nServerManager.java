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

package org.drools.workbench.screens.scenariosimulation.client.resources.i18n;

import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;

public class ScenarioSimulationEditorI18nServerManager {

    public static String retrieveMessage(FactMappingValidationError error) {
        switch (error.getServerMessage()) {
            case DMN_SCENARIO_VALIDATION_NODE_CHANGED_ERROR:
                return ScenarioSimulationEditorConstants.INSTANCE.dmnScenarioValidationNodeChangedError(error.getParameters()[0],
                                                                                                        error.getParameters()[1]);
            case DMN_SCENARIO_VALIDATION_FIELD_CHANGED_ERROR:
                return ScenarioSimulationEditorConstants.INSTANCE.dmnScenarioValidationFieldChangedError(error.getParameters()[0],
                                                                                                         error.getParameters()[1]);
            case DMN_SCENARIO_VALIDATION_FIELD_ADDED_CONSTRAINT_ERROR:
                return ScenarioSimulationEditorConstants.INSTANCE.dmnScenarioValidationFieldAddedConstraintError();
            case DMN_SCENARIO_VALIDATION_FIELD_REMOVED_CONSTRAINT_ERROR:
                return ScenarioSimulationEditorConstants.INSTANCE.dmnScenarioValidationFieldRemoveConstraintError();
            default:
                throw new IllegalStateException();
        }
    }
}