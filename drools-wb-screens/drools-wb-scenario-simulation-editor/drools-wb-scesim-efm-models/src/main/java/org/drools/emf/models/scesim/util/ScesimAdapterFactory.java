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

package org.drools.emf.models.scesim.util;

import org.drools.emf.models.scesim.ExpressionElement;
import org.drools.emf.models.scesim.ExpressionIdentifier;
import org.drools.emf.models.scesim.FactIdentifier;
import org.drools.emf.models.scesim.FactMapping;
import org.drools.emf.models.scesim.FactMappingValue;
import org.drools.emf.models.scesim.Import;
import org.drools.emf.models.scesim.Imports;
import org.drools.emf.models.scesim.Scenario;
import org.drools.emf.models.scesim.ScenarioSimulationModel;
import org.drools.emf.models.scesim.ScesimPackage;
import org.drools.emf.models.scesim.Simulation;
import org.drools.emf.models.scesim.SimulationDescriptor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @generated
 * @see org.drools.emf.models.scesim.ScesimPackage
 */
public class ScesimAdapterFactory extends AdapterFactoryImpl {

    /**
     * The cached model package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ScesimPackage modelPackage;
    /**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScesimSwitch<Adapter> modelSwitch =
            new ScesimSwitch<Adapter>() {

                @Override
                public Adapter caseExpressionElement(ExpressionElement object) {
                    return createExpressionElementAdapter();
                }

                @Override
                public Adapter caseExpressionIdentifier(ExpressionIdentifier object) {
                    return createExpressionIdentifierAdapter();
                }

                @Override
                public Adapter caseFactIdentifier(FactIdentifier object) {
                    return createFactIdentifierAdapter();
                }

                @Override
                public Adapter caseFactMapping(FactMapping object) {
                    return createFactMappingAdapter();
                }

                @Override
                public Adapter caseFactMappingValue(FactMappingValue object) {
                    return createFactMappingValueAdapter();
                }

                @Override
                public Adapter caseImport(Import object) {
                    return createImportAdapter();
                }

                @Override
                public Adapter caseImports(Imports object) {
                    return createImportsAdapter();
                }

                @Override
                public Adapter caseScenario(Scenario object) {
                    return createScenarioAdapter();
                }

                @Override
                public Adapter caseScenarioSimulationModel(ScenarioSimulationModel object) {
                    return createScenarioSimulationModelAdapter();
                }

                @Override
                public Adapter caseSimulation(Simulation object) {
                    return createSimulationAdapter();
                }

                @Override
                public Adapter caseSimulationDescriptor(SimulationDescriptor object) {
                    return createSimulationDescriptorAdapter();
                }

                @Override
                public Adapter defaultCase(EObject object) {
                    return createEObjectAdapter();
                }
            };

    /**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScesimAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ScesimPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.ExpressionElement <em>Expression Element</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.ExpressionElement
     */
    public Adapter createExpressionElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.ExpressionIdentifier <em>Expression Identifier</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.ExpressionIdentifier
     */
    public Adapter createExpressionIdentifierAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.FactIdentifier <em>Fact Identifier</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.FactIdentifier
     */
    public Adapter createFactIdentifierAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.FactMapping <em>Fact Mapping</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping
     */
    public Adapter createFactMappingAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.FactMappingValue <em>Fact Mapping Value</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingValue
     */
    public Adapter createFactMappingValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.Import <em>Import</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.Import
     */
    public Adapter createImportAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.Imports <em>Imports</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.Imports
     */
    public Adapter createImportsAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.Scenario <em>Scenario</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.Scenario
     */
    public Adapter createScenarioAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.ScenarioSimulationModel <em>Scenario Simulation Model</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.ScenarioSimulationModel
     */
    public Adapter createScenarioSimulationModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.Simulation <em>Simulation</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.Simulation
     */
    public Adapter createSimulationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.drools.emf.models.scesim.SimulationDescriptor <em>Simulation Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor
     */
    public Adapter createSimulationDescriptorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }
} //scesimAdapterFactory
