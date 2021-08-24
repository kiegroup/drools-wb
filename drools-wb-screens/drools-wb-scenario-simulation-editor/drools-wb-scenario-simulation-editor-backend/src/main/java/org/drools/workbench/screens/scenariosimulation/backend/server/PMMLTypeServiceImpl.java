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

package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.io.File;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.enterprise.context.ApplicationScoped;

import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.drools.workbench.screens.scenariosimulation.service.PMMLTypeService;
import org.guvnor.common.services.backend.exceptions.ExceptionUtilities;
import org.jboss.errai.bus.server.annotations.Service;
import org.kie.pmml.api.enums.DATA_TYPE;
import org.kie.pmml.api.enums.FIELD_USAGE_TYPE;
import org.kie.pmml.api.models.MiningField;
import org.kie.pmml.api.models.OutputField;
import org.kie.pmml.api.models.PMMLModel;
import org.kie.pmml.evaluator.assembler.factories.PMMLRuntimeFactoryImpl;
import org.kie.pmml.evaluator.core.service.PMMLRuntimeInternalImpl;
import org.uberfire.backend.vfs.Path;

@Service
@ApplicationScoped
public class PMMLTypeServiceImpl
        extends AbstractKieContainerService
        implements PMMLTypeService {

    @Override
    public FactModelTuple retrieveFactModelTuple(Path path, String pmmlPath) {
        PMMLModel pmmlModel = getPMMLModel(path, pmmlPath);
        SortedMap<String, FactModelTree> visibleFacts = new TreeMap<>();
        SortedMap<String, FactModelTree> hiddenFacts = new TreeMap<>();
        ErrorHolder errorHolder = new ErrorHolder();

        for (MiningField miningField : pmmlModel.getMiningFields()) {
            final DATA_TYPE type = miningField.getDataType();
            final String name = miningField.getName();
            FactModelTree.Type modelTreeType = isTarget(miningField) ? FactModelTree.Type.PREDICTION : FactModelTree.Type.INPUT;
            try {
                visibleFacts.put(name, createTopLevelFactModelTree(name, type, modelTreeType));
            } catch (Exception e) {
                throw ExceptionUtilities.handleException(e);
            }
        }

        for (OutputField outputField : pmmlModel.getOutputFields()) {
            DATA_TYPE type = outputField.getDataType();
            final String name = outputField.getName();
            try {
                visibleFacts.put(name, createTopLevelFactModelTree(name, type, FactModelTree.Type.PREDICTION));
            } catch (Exception e) {
                throw ExceptionUtilities.handleException(e);
            }
        }
        FactModelTuple factModelTuple = new FactModelTuple(visibleFacts, hiddenFacts);
        errorHolder.getMultipleNestedCollection().forEach(factModelTuple::addMultipleNestedCollectionError);
        errorHolder.getMultipleNestedObject().forEach(factModelTuple::addMultipleNestedObjectError);
        return factModelTuple;
    }

    public PMMLModel getPMMLModel(Path path, String modelName) {
        File pmmlFile = new File(path.toURI());
        PMMLRuntimeInternalImpl pmmlRuntime =
                (PMMLRuntimeInternalImpl) new PMMLRuntimeFactoryImpl().getPMMLRuntimeFromFile(pmmlFile);
        return pmmlRuntime.getPMMLModel(modelName).orElseThrow(() -> new RuntimeException("Unable to find model " + modelName));
    }

    /**
     * This method is the <b>entry point</b> for <code>FactModelTree</code>. It is the one to be called from the very
     * top level <code>DATA_TYPE</code>
     * @param factName
     * @param type
     * @param fmType
     * @return
     */
    protected FactModelTree createTopLevelFactModelTree(String factName, DATA_TYPE type, FactModelTree.Type fmType) {
        return FactModelTree.ofSimplePMML(factName, type.getName(), type.getName(), fmType);
    }

    public static boolean isTarget(final MiningField miningField) {
        return (miningField.getUsageType().equals(FIELD_USAGE_TYPE.TARGET) || miningField.getUsageType().equals(FIELD_USAGE_TYPE.PREDICTED));
    }

    static class ErrorHolder {

        Set<String> multipleNestedObject = new TreeSet<>();
        Set<String> multipleNestedCollection = new TreeSet<>();

        public Set<String> getMultipleNestedObject() {
            return multipleNestedObject;
        }

        public Set<String> getMultipleNestedCollection() {
            return multipleNestedCollection;
        }
    }
}