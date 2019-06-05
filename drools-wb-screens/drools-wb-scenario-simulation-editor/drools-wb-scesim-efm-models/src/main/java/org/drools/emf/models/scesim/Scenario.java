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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.Scenario#getFactMappingValues <em>Fact Mapping Values</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.Scenario#getSimulationDescriptor <em>Simulation Descriptor</em>}</li>
 * </ul>
 * @model extendedMetaData="name='scenario' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.ScesimPackage#getScenario()
 */
public interface Scenario extends EObject {

    /**
     * Returns the value of the '<em><b>Fact Mapping Values</b></em>' containment reference list.
     * The list contents are of type {@link org.drools.emf.models.scesim.FactMappingValue}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fact Mapping Values</em>' containment reference list.
     * @model containment="true"
     * extendedMetaData="kind='element' name='factMappingValues' namespace='##targetNamespace'"
     * @generated
     * @see org.drools.emf.models.scesim.ScesimPackage#getScenario_FactMappingValues()
     */
    EList<FactMappingValue> getFactMappingValues();

    /**
     * Returns the value of the '<em><b>Simulation Descriptor</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Simulation Descriptor</em>' containment reference.
     * @model containment="true" required="true"
     * extendedMetaData="kind='element' name='simulationDescriptor' namespace='##targetNamespace'"
     * @generated
     * @see #setSimulationDescriptor(SimulationDescriptor)
     * @see org.drools.emf.models.scesim.ScesimPackage#getScenario_SimulationDescriptor()
     */
    SimulationDescriptor getSimulationDescriptor();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.Scenario#getSimulationDescriptor <em>Simulation Descriptor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Simulation Descriptor</em>' containment reference.
     * @generated
     * @see #getSimulationDescriptor()
     */
    void setSimulationDescriptor(SimulationDescriptor value);
} // Scenario
