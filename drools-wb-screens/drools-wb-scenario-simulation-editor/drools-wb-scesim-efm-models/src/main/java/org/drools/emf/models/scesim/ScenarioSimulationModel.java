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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scenario Simulation Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getVersion <em>Version</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getSimulation <em>Simulation</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getImports <em>Imports</em>}</li>
 * </ul>
 * @model extendedMetaData="name='scenarioSimulationModel' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.ScesimPackage#getScenarioSimulationModel()
 */
public interface ScenarioSimulationModel extends EObject {

    /**
     * Returns the value of the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Version</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='version' namespace='##targetNamespace'"
     * @generated
     * @see #setVersion(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getScenarioSimulationModel_Version()
     */
    String getVersion();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getVersion <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Version</em>' attribute.
     * @generated
     * @see #getVersion()
     */
    void setVersion(String value);

    /**
     * Returns the value of the '<em><b>Simulation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Simulation</em>' containment reference.
     * @model containment="true" required="true"
     * extendedMetaData="kind='element' name='simulation' namespace='##targetNamespace'"
     * @generated
     * @see #setSimulation(Simulation)
     * @see org.drools.emf.models.scesim.ScesimPackage#getScenarioSimulationModel_Simulation()
     */
    Simulation getSimulation();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getSimulation <em>Simulation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Simulation</em>' containment reference.
     * @generated
     * @see #getSimulation()
     */
    void setSimulation(Simulation value);

    /**
     * Returns the value of the '<em><b>Imports</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Imports</em>' containment reference.
     * @model containment="true" required="true"
     * extendedMetaData="kind='element' name='imports' namespace='##targetNamespace'"
     * @generated
     * @see #setImports(Imports)
     * @see org.drools.emf.models.scesim.ScesimPackage#getScenarioSimulationModel_Imports()
     */
    Imports getImports();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getImports <em>Imports</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Imports</em>' containment reference.
     * @generated
     * @see #getImports()
     */
    void setImports(Imports value);
} // ScenarioSimulationModel
