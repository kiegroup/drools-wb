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
package org.drools.emf.models.scesim;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 * @see org.drools.emf.models.scesim.scesimPackage
 */
public interface scesimFactory extends EFactory {

    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    scesimFactory eINSTANCE = org.drools.emf.models.scesim.impl.scesimFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Expression Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression Element</em>'.
     * @generated
     */
    ExpressionElement createExpressionElement();

    /**
     * Returns a new object of class '<em>Expression Identifier</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Expression Identifier</em>'.
     * @generated
     */
    ExpressionIdentifier createExpressionIdentifier();

    /**
     * Returns a new object of class '<em>Fact Identifier</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Fact Identifier</em>'.
     * @generated
     */
    FactIdentifier createFactIdentifier();

    /**
     * Returns a new object of class '<em>Fact Mapping</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Fact Mapping</em>'.
     * @generated
     */
    FactMapping createFactMapping();

    /**
     * Returns a new object of class '<em>Fact Mapping Value</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Fact Mapping Value</em>'.
     * @generated
     */
    FactMappingValue createFactMappingValue();

    /**
     * Returns a new object of class '<em>Import</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Import</em>'.
     * @generated
     */
    Import createImport();

    /**
     * Returns a new object of class '<em>Imports</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Imports</em>'.
     * @generated
     */
    Imports createImports();

    /**
     * Returns a new object of class '<em>Scenario</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Scenario</em>'.
     * @generated
     */
    Scenario createScenario();

    /**
     * Returns a new object of class '<em>Scenario Simulation Model</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Scenario Simulation Model</em>'.
     * @generated
     */
    ScenarioSimulationModel createScenarioSimulationModel();

    /**
     * Returns a new object of class '<em>Simulation</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Simulation</em>'.
     * @generated
     */
    Simulation createSimulation();

    /**
     * Returns a new object of class '<em>Simulation Descriptor</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Simulation Descriptor</em>'.
     * @generated
     */
    SimulationDescriptor createSimulationDescriptor();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    scesimPackage getscesimPackage();
} //scesimFactory
