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

import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.junit.Before;
import org.junit.Test;
import org.kie.pmml.api.enums.DATA_TYPE;
import org.kie.pmml.api.models.MiningField;
import org.kie.pmml.api.models.OutputField;
import org.kie.pmml.api.models.PMMLModel;
import org.uberfire.backend.vfs.Path;

import static org.drools.scenariosimulation.api.utils.ConstantsHolder.VALUE;
import static org.drools.workbench.screens.scenariosimulation.backend.server.PMMLTypeServiceImpl.isTarget;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PMMLTypeServiceImplTest extends AbstractPMMLTest {

    private PMMLTypeServiceImpl pmmlTypeServiceImpl;

    @Before
    public void init() {
        super.init();
        pmmlTypeServiceImpl = new PMMLTypeServiceImpl() {
            @Override
            public PMMLModel getPMMLModel(Path path, String stringPath) {
                return pmmlModelLocal;
            }
        };
    }

    @Test
    public void createTopLevelFactModelTree() {
        // Single property retrieve
        DATA_TYPE simpleString = getSimpleNoCollection();
        FactModelTree retrieved = pmmlTypeServiceImpl.createTopLevelFactModelTree("testPath", simpleString, FactModelTree.Type.INPUT);
        assertNotNull(retrieved);
        assertEquals("testPath", retrieved.getFactName());
        assertEquals(1, retrieved.getSimpleProperties().size());
        assertTrue(retrieved.getSimpleProperties().containsKey(VALUE));
        assertEquals(simpleString.getMappedClass().getCanonicalName(), retrieved.getSimpleProperties().get(VALUE).getTypeName());
        assertFalse(retrieved.getSimpleProperties().get(VALUE).getBaseTypeName().isPresent());
        assertEquals(simpleString.getMappedClass().getCanonicalName(), retrieved.getSimpleProperties().get(VALUE).getPropertyTypeNameToVisualize());
        assertTrue(retrieved.getExpandableProperties().isEmpty());
        assertTrue(retrieved.getGenericTypesMap().isEmpty());
    }

    @Test
    public void retrieveFactModelTuplePmml() {
        FactModelTuple factModelTuple = pmmlTypeServiceImpl.retrieveFactModelTuple(mock(Path.class), null, null);
        // VisibleFacts should match inputs and predictions on given model
        int expectedVisibleFacts = pmmlModelLocal.getMiningFields().size() + pmmlModelLocal.getOutputFields().size();
        assertEquals(expectedVisibleFacts, factModelTuple.getVisibleFacts().size());
        // Verify each miningField has been correctly mapped
        pmmlModelLocal.getMiningFields().forEach(miningField -> verifyFactModelTree(factModelTuple, miningField));
        // Verify each outputField has been correctly mapped
        pmmlModelLocal.getOutputFields().forEach(outputField -> verifyFactModelTree(factModelTuple, outputField));
    }

    /**
     * Verify the <code>FactModelTree</code> generated for a <b>given</b> <code>MiningField</code>
     * @param factModelTuple
     * @param miningField
     */
    private void verifyFactModelTree(FactModelTuple factModelTuple, MiningField miningField ) {
        final String name = miningField.getName();
        // Check the FactModelTree has been mapped between visible facts
        assertTrue(factModelTuple.getVisibleFacts().containsKey(name));
        final FactModelTree mappedFactModelTree = factModelTuple.getVisibleFacts().get(name);
        // Check the FactModelTree is not null
        assertNotNull(mappedFactModelTree);
        DATA_TYPE originalType = miningField.getDataType();
        verifyDATA_TYPE(mappedFactModelTree, originalType);
        FactModelTree.Type expected = isTarget(miningField) ? FactModelTree.Type.PREDICTION : FactModelTree.Type.INPUT;
        assertEquals(expected, mappedFactModelTree.getType());
    }

    /**
     * Verify the <code>FactModelTree</code> generated for a <b>given</b> <code>OutputField</code>
     * @param factModelTuple
     * @param outputField
     */
    private void verifyFactModelTree(FactModelTuple factModelTuple, OutputField outputField) {
        final String name = outputField.getName();
        // Check the FactModelTree has been mapped between visible facts
        assertTrue(factModelTuple.getVisibleFacts().containsKey(name));
        final FactModelTree mappedFactModelTree = factModelTuple.getVisibleFacts().get(name);
        // Check the FactModelTree is not null
        assertNotNull(mappedFactModelTree);
        DATA_TYPE originalType = outputField.getDataType();
        verifyDATA_TYPE(mappedFactModelTree, originalType);
        assertEquals(FactModelTree.Type.PREDICTION, mappedFactModelTree.getType());
    }

    /**
     * Verify the <code>FactModelTree</code> generated for a <code>DATA_TYPE</code>
     * @param mappedFactModelTree
     * @param originalType
     */
    private void verifyDATA_TYPE(FactModelTree mappedFactModelTree, DATA_TYPE originalType) {
        assertTrue(mappedFactModelTree.getSimpleProperties().containsKey(VALUE));
        assertEquals(originalType.getMappedClass().getCanonicalName(), mappedFactModelTree.getSimpleProperties().get(VALUE).getTypeName());
        assertEquals(originalType.getMappedClass().getCanonicalName(), mappedFactModelTree.getSimpleProperties().get(VALUE).getPropertyTypeNameToVisualize());
    }
}