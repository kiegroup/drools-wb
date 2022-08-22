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

package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Settings;
import org.kie.pmml.api.enums.DATA_TYPE;
import org.kie.pmml.api.enums.FIELD_USAGE_TYPE;
import org.kie.pmml.api.models.MiningField;
import org.kie.pmml.api.models.OutputField;
import org.kie.pmml.api.models.PMMLModel;
import org.kie.pmml.evaluator.assembler.factories.PMMLRuntimeFactoryImpl;
import org.kie.pmml.evaluator.core.service.PMMLRuntimeInternalImpl;

import static org.junit.Assert.assertNotNull;

public abstract class AbstractPMMLTest {

    protected static final String STRING_ACTIVE_MINING_FIELD = "STRING_ACTIVE_MINING_FIELD";
    protected static final String DOUBLE_PREDICTED_MINING_FIELD = "DOUBLE_PREDICTED_MINING_FIELD";
    protected static final String STRING_OUTPUT_FIELD = "STRING_OUTPUT_FIELD";
    protected static final String STRING_OUTPUT_FIELD_TARGET = "STRING_OUTPUT_FIELD_TARGET";
    protected static final String DOUBLE_OUTPUT_FIELD = "DOUBLE_OUTPUT_FIELD";
    protected static final String DOUBLE_OUTPUT_FIELD_TARGET = "DOUBLE_OUTPUT_FIELD_TARGET";
    protected static final String PMML_FILE = "CategoricalVariablesRegression.pmml";
    protected static final String MODEL_NAME = "CategoricalVariablesRegression";
    protected PMMLModel pmmlModelLocal;
    protected Set<MiningField> miningFields;
    protected Set<OutputField> outputFields;
    protected Settings settingsLocal;

    protected void init() {
        settingsLocal = new Settings();
        settingsLocal.setType(ScenarioSimulationModel.Type.PMML);
        miningFields = new HashSet<>();
        MiningField stringActiveMiningField = getMiningField(DATA_TYPE.STRING, FIELD_USAGE_TYPE.ACTIVE,
                                                             STRING_ACTIVE_MINING_FIELD);
        miningFields.add(stringActiveMiningField);
        MiningField doublePredictedMiningField = getMiningField(DATA_TYPE.DOUBLE, FIELD_USAGE_TYPE.PREDICTED,
                                                                DOUBLE_PREDICTED_MINING_FIELD);
        miningFields.add(doublePredictedMiningField);

        outputFields = new HashSet<>();
        OutputField stringOutputField = getOutputField(DATA_TYPE.STRING, STRING_OUTPUT_FIELD, STRING_OUTPUT_FIELD_TARGET);
        outputFields.add(stringOutputField);
        OutputField doubleOutputField = getOutputField(DATA_TYPE.DOUBLE, DOUBLE_OUTPUT_FIELD, DOUBLE_OUTPUT_FIELD_TARGET);
        outputFields.add(doubleOutputField);
        final URL resource = AbstractPMMLTest.class.getResource(PMML_FILE);
        assertNotNull(resource);
        PMMLRuntimeInternalImpl pmmlRuntime =
                (PMMLRuntimeInternalImpl) new PMMLRuntimeFactoryImpl().getPMMLRuntimeFromFile(new File(resource.getFile()));
        pmmlModelLocal = pmmlRuntime.getPMMLModel(MODEL_NAME).orElseThrow(() -> new RuntimeException("Unable to find " +
                                                                                                            "model " + MODEL_NAME));
    }

    /**
     * Returns a <b>single</b> <code>SimpleTypeImpl</code>
     * @return
     */
    protected DATA_TYPE getSimpleNoCollection() {
        return DATA_TYPE.STRING;
    }

    protected MiningField getMiningField(DATA_TYPE dataType, FIELD_USAGE_TYPE usageType, String name) {
        return new MiningField(name,
                               usageType,
                               null,
                               dataType,
                               null,
                               null,
                               null,
                               null,
                               null,
                               null);
    }

    protected OutputField getOutputField(DATA_TYPE dataType, String name, String targetField) {
        return new OutputField(name,
                               null,
                               dataType,
                               targetField,
                               null,
                               null);
    }
}