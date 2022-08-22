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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.scenariosimulation.api.model.Simulation;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntimeFactory;
import org.kie.pmml.api.enums.DATA_TYPE;
import org.kie.pmml.api.exceptions.KiePMMLInternalException;
import org.kie.pmml.api.models.MiningField;
import org.kie.pmml.api.models.OutputField;
import org.kie.pmml.api.models.PMMLModel;
import org.kie.pmml.api.runtime.PMMLRuntime;

import static org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError.createFieldChangedError;
import static org.drools.workbench.screens.scenariosimulation.model.FactMappingValidationError.createNodeChangedError;

public class PMMLScenarioValidation extends AbstractScenarioValidation {

    public static final PMMLScenarioValidation INSTANCE = new PMMLScenarioValidation();

    /**
     * Validate structure of a PMML test scenario.
     * Supported checks for each column:
     * - empty column skip
     * - PMML node removed
     * - simple type becomes complex type
     * - navigation of data type still valid
     * - field type changed
     * @param simulation
     * @param settings
     * @param kieContainer
     * @return
     */
    @Override
    public List<FactMappingValidationError> validate(Simulation simulation, Settings settings,
                                                     KieContainer kieContainer) {
        List<FactMappingValidationError> errors = new ArrayList<>();
        String pmmlModelName = settings.getPmmlModelName();
        PMMLModel pmmlModel = getPMMLModel(kieContainer, pmmlModelName);

        for (FactMapping factMapping : simulation.getScesimModelDescriptor().getFactMappings()) {
            if (isToSkip(factMapping)) {
                continue;
            }

            String nodeName = factMapping.getFactIdentifier().getName();

            DATA_TYPE fieldType;
            try {
                fieldType = Stream.of(getMiningFieldDataTypeByName(pmmlModel.getMiningFields(), nodeName),
                                          getOutputFieldDataTypeByName(pmmlModel.getOutputFields(), nodeName))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .findFirst()
                        .orElseThrow(() -> new KiePMMLInternalException(String.format("Failed to find DataType for " +
                                                                                              "field %s",
                                                                                      nodeName)));
            } catch (NullPointerException e) {
                errors.add(createNodeChangedError(factMapping, "node not found"));
                continue;
            }
            if (!isPMMLFactMappingValid(factMapping, fieldType)) {
                errors.add(defineFieldChangedError(factMapping, fieldType, fieldType));
            }
        }
        return errors;
    }

    private static Optional<DATA_TYPE> getMiningFieldDataTypeByName(List<MiningField> miningFields, String fieldName) {
        return miningFields.stream().filter(miningField -> fieldName.equals(miningField.getName()))
                .findFirst()
                .map(MiningField::getDataType);
    }

    private static Optional<DATA_TYPE> getOutputFieldDataTypeByName(List<OutputField> outputFields, String fieldName) {
        return outputFields.stream().filter(outputField -> fieldName.equals(outputField.getName()))
                .findFirst()
                .map(OutputField::getDataType);
    }

    private FactMappingValidationError defineFieldChangedError(FactMapping factMapping, DATA_TYPE factType,
                                                               DATA_TYPE fieldType) {
//        String typeName = factMapping.getClassName();
//        if (isConstraintAdded(typeName, fieldType)) {
//            return FactMappingValidationError.createFieldAddedConstraintError(factMapping);
//        }
//        if (isConstraintRemoved(typeName, factType, fieldType)) {
//            return FactMappingValidationError.createFieldRemovedConstraintError(factMapping);
//        }
        return createFieldChangedError(factMapping, fieldType.getName());
    }

    private boolean isPMMLFactMappingValid(FactMapping factMapping, DATA_TYPE pmmlType) {
        String factMappingType = factMapping.getClassName();
        return Objects.equals(factMappingType, pmmlType.getName());
    }
//
//    /**
//     * To define if a constraint (allowed values) were added in a DATA_TYPE fieldType given a typeName, the following
//     * conditions
//     * are requires:
//     * - typeName MUST BE a BuiltInType (eg. STRING, NUMERIC ..)
//     * - DATA_TYPE fieldType MUST have at least one defined Allowed Values
//     * - DATA_TYPE fieldType MUST have a Base Type. It's name MUST be equals to given typeName.
//     * @param typeName TypeName present in the scesim file
//     * @param fieldType DATA_TYPE of field under analysis
//     * @return
//     */
//    private boolean isConstraintAdded(String typeName, DATA_TYPE fieldType) {
//        boolean isTypeNameBuiltInType = !Objects.equals(UNKNOWN, BuiltInType.determineTypeFromName(typeName));
//        boolean hasFieldTypeAllowedValues =
//                fieldType.getAllowedValues() != null && !fieldType.getAllowedValues().isEmpty();
//        boolean hasFieldTypeBaseType = Objects.nonNull(fieldType.getBaseType());
//        if (isTypeNameBuiltInType && hasFieldTypeBaseType && hasFieldTypeAllowedValues) {
//            Type baseType = getRootType((BaseDATA_TYPEImpl) fieldType.getBaseType());
//            return Objects.equals(typeName, baseType.getName());
//        }
//        return false;
//    }
//
//    /**
//     * To define if a constraint (allowed values) were removed in a DATA_TYPE fieldType given a typeName, the
//     * following conditions
//     * are requires:
//     * - DATA_TYPE fieldType MUST DON'T have Allowed Values defined
//     * - DATA_TYPE fieldType MUST DON'T have a Base Type.
//     * - typeName MUST BE a DATA_TYPE factType's field. The field's DATA_TYPE MUST BE the same of given fieldType
//     * DATA_TYPE
//     * @param typeName
//     * @param factType Fact DATA_TYPE
//     * @param fieldType Field DATA_TYPE
//     * @return
//     */
//    private boolean isConstraintRemoved(String typeName, DATA_TYPE factType, DATA_TYPE fieldType) {
//        boolean hasFieldTypeAllowedValues =
//                fieldType.getAllowedValues() != null && !fieldType.getAllowedValues().isEmpty();
//        boolean hasFieldTypeBaseType = Objects.nonNull(fieldType.getBaseType());
//        boolean isTypeNameFactTypeField = factType.getFields().containsKey(typeName);
//        if (!hasFieldTypeBaseType && !hasFieldTypeAllowedValues && isTypeNameFactTypeField) {
//            DATA_TYPE typeNameDATA_TYPE = factType.getFields().get(typeName);
//            return Objects.equals(fieldType.getNamespace(), typeNameDATA_TYPE.getNamespace());
//        }
//        return false;
//    }

    protected PMMLModel getPMMLModel(KieContainer kieContainer, String pmmlModelName) {
        PMMLRuntime pmmlRuntime = KieRuntimeFactory.of(kieContainer.getKieBase()).get(PMMLRuntime.class);
        return pmmlRuntime.getPMMLModel(pmmlModelName).orElseThrow(() -> new RuntimeException("Unable to find model " + pmmlModelName));
    }
}