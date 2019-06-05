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

package org.drools.emf.models.scesim.impl;

import org.drools.emf.models.scesim.ExpressionElement;
import org.drools.emf.models.scesim.ExpressionIdentifier;
import org.drools.emf.models.scesim.FactIdentifier;
import org.drools.emf.models.scesim.FactMapping;
import org.drools.emf.models.scesim.FactMappingType;
import org.drools.emf.models.scesim.FactMappingValue;
import org.drools.emf.models.scesim.Import;
import org.drools.emf.models.scesim.Imports;
import org.drools.emf.models.scesim.Name;
import org.drools.emf.models.scesim.Scenario;
import org.drools.emf.models.scesim.ScenarioSimulationModel;
import org.drools.emf.models.scesim.ScesimFactory;
import org.drools.emf.models.scesim.ScesimPackage;
import org.drools.emf.models.scesim.Simulation;
import org.drools.emf.models.scesim.SimulationDescriptor;
import org.drools.emf.models.scesim.Type;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScesimFactoryImpl extends EFactoryImpl implements ScesimFactory {

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScesimFactoryImpl() {
        super();
    }

    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ScesimFactory init() {
        try {
            ScesimFactory thescesimFactory = (ScesimFactory) EPackage.Registry.INSTANCE.getEFactory(ScesimPackage.eNS_URI);
            if (thescesimFactory != null) {
                return thescesimFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ScesimFactoryImpl();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @deprecated
     */
    @Deprecated
    public static ScesimPackage getPackage() {
        return ScesimPackage.eINSTANCE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ScesimPackage.EXPRESSION_ELEMENT:
                return createExpressionElement();
            case ScesimPackage.EXPRESSION_IDENTIFIER:
                return createExpressionIdentifier();
            case ScesimPackage.FACT_IDENTIFIER:
                return createFactIdentifier();
            case ScesimPackage.FACT_MAPPING:
                return createFactMapping();
            case ScesimPackage.FACT_MAPPING_VALUE:
                return createFactMappingValue();
            case ScesimPackage.IMPORT:
                return createImport();
            case ScesimPackage.IMPORTS:
                return createImports();
            case ScesimPackage.SCENARIO:
                return createScenario();
            case ScesimPackage.SCENARIO_SIMULATION_MODEL:
                return createScenarioSimulationModel();
            case ScesimPackage.SIMULATION:
                return createSimulation();
            case ScesimPackage.SIMULATION_DESCRIPTOR:
                return createSimulationDescriptor();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
            case ScesimPackage.FACT_MAPPING_TYPE:
                return createFactMappingTypeFromString(eDataType, initialValue);
            case ScesimPackage.NAME:
                return createNameFromString(eDataType, initialValue);
            case ScesimPackage.TYPE:
                return createTypeFromString(eDataType, initialValue);
            case ScesimPackage.FACT_MAPPING_TYPE_OBJECT:
                return createFactMappingTypeObjectFromString(eDataType, initialValue);
            case ScesimPackage.NAME_OBJECT:
                return createNameObjectFromString(eDataType, initialValue);
            case ScesimPackage.TYPE_OBJECT:
                return createTypeObjectFromString(eDataType, initialValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
            case ScesimPackage.FACT_MAPPING_TYPE:
                return convertFactMappingTypeToString(eDataType, instanceValue);
            case ScesimPackage.NAME:
                return convertNameToString(eDataType, instanceValue);
            case ScesimPackage.TYPE:
                return convertTypeToString(eDataType, instanceValue);
            case ScesimPackage.FACT_MAPPING_TYPE_OBJECT:
                return convertFactMappingTypeObjectToString(eDataType, instanceValue);
            case ScesimPackage.NAME_OBJECT:
                return convertNameObjectToString(eDataType, instanceValue);
            case ScesimPackage.TYPE_OBJECT:
                return convertTypeObjectToString(eDataType, instanceValue);
            default:
                throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ExpressionElement createExpressionElement() {
        ExpressionElementImpl expressionElement = new ExpressionElementImpl();
        return expressionElement;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ExpressionIdentifier createExpressionIdentifier() {
        ExpressionIdentifierImpl expressionIdentifier = new ExpressionIdentifierImpl();
        return expressionIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FactIdentifier createFactIdentifier() {
        FactIdentifierImpl factIdentifier = new FactIdentifierImpl();
        return factIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FactMapping createFactMapping() {
        FactMappingImpl factMapping = new FactMappingImpl();
        return factMapping;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public FactMappingValue createFactMappingValue() {
        FactMappingValueImpl factMappingValue = new FactMappingValueImpl();
        return factMappingValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Import createImport() {
        ImportImpl import_ = new ImportImpl();
        return import_;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Imports createImports() {
        ImportsImpl imports = new ImportsImpl();
        return imports;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Scenario createScenario() {
        ScenarioImpl scenario = new ScenarioImpl();
        return scenario;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ScenarioSimulationModel createScenarioSimulationModel() {
        ScenarioSimulationModelImpl scenarioSimulationModel = new ScenarioSimulationModelImpl();
        return scenarioSimulationModel;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Simulation createSimulation() {
        SimulationImpl simulation = new SimulationImpl();
        return simulation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SimulationDescriptor createSimulationDescriptor() {
        SimulationDescriptorImpl simulationDescriptor = new SimulationDescriptorImpl();
        return simulationDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FactMappingType createFactMappingTypeFromString(EDataType eDataType, String initialValue) {
        FactMappingType result = FactMappingType.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        }
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFactMappingTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Name createNameFromString(EDataType eDataType, String initialValue) {
        Name result = Name.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        }
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNameToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type createTypeFromString(EDataType eDataType, String initialValue) {
        Type result = Type.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        }
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTypeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FactMappingType createFactMappingTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createFactMappingTypeFromString(ScesimPackage.Literals.FACT_MAPPING_TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertFactMappingTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertFactMappingTypeToString(ScesimPackage.Literals.FACT_MAPPING_TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Name createNameObjectFromString(EDataType eDataType, String initialValue) {
        return createNameFromString(ScesimPackage.Literals.NAME, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertNameObjectToString(EDataType eDataType, Object instanceValue) {
        return convertNameToString(ScesimPackage.Literals.NAME, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Type createTypeObjectFromString(EDataType eDataType, String initialValue) {
        return createTypeFromString(ScesimPackage.Literals.TYPE, initialValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String convertTypeObjectToString(EDataType eDataType, Object instanceValue) {
        return convertTypeToString(ScesimPackage.Literals.TYPE, instanceValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ScesimPackage getscesimPackage() {
        return (ScesimPackage) getEPackage();
    }
} //scesimFactoryImpl
