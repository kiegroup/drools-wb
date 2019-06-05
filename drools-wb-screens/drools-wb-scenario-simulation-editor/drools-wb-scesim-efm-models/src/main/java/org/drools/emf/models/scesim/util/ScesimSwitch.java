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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @generated
 * @see org.drools.emf.models.scesim.ScesimPackage
 */
public class ScesimSwitch<T> extends Switch<T> {

    /**
     * The cached model package
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected static ScesimPackage modelPackage;

    /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ScesimSwitch() {
        if (modelPackage == null) {
            modelPackage = ScesimPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case ScesimPackage.EXPRESSION_ELEMENT: {
                ExpressionElement expressionElement = (ExpressionElement) theEObject;
                T result = caseExpressionElement(expressionElement);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.EXPRESSION_IDENTIFIER: {
                ExpressionIdentifier expressionIdentifier = (ExpressionIdentifier) theEObject;
                T result = caseExpressionIdentifier(expressionIdentifier);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.FACT_IDENTIFIER: {
                FactIdentifier factIdentifier = (FactIdentifier) theEObject;
                T result = caseFactIdentifier(factIdentifier);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.FACT_MAPPING: {
                FactMapping factMapping = (FactMapping) theEObject;
                T result = caseFactMapping(factMapping);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.FACT_MAPPING_VALUE: {
                FactMappingValue factMappingValue = (FactMappingValue) theEObject;
                T result = caseFactMappingValue(factMappingValue);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.IMPORT: {
                Import import_ = (Import) theEObject;
                T result = caseImport(import_);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.IMPORTS: {
                Imports imports = (Imports) theEObject;
                T result = caseImports(imports);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.SCENARIO: {
                Scenario scenario = (Scenario) theEObject;
                T result = caseScenario(scenario);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.SCENARIO_SIMULATION_MODEL: {
                ScenarioSimulationModel scenarioSimulationModel = (ScenarioSimulationModel) theEObject;
                T result = caseScenarioSimulationModel(scenarioSimulationModel);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.SIMULATION: {
                Simulation simulation = (Simulation) theEObject;
                T result = caseSimulation(simulation);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            case ScesimPackage.SIMULATION_DESCRIPTOR: {
                SimulationDescriptor simulationDescriptor = (SimulationDescriptor) theEObject;
                T result = caseSimulationDescriptor(simulationDescriptor);
                if (result == null) {
                    result = defaultCase(theEObject);
                }
                return result;
            }
            default:
                return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Expression Element</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression Element</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseExpressionElement(ExpressionElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Expression Identifier</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Expression Identifier</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseExpressionIdentifier(ExpressionIdentifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Fact Identifier</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Fact Identifier</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseFactIdentifier(FactIdentifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Fact Mapping</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Fact Mapping</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseFactMapping(FactMapping object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Fact Mapping Value</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Fact Mapping Value</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseFactMappingValue(FactMappingValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Import</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseImport(Import object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Imports</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Imports</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseImports(Imports object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Scenario</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Scenario</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseScenario(Scenario object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Scenario Simulation Model</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Scenario Simulation Model</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseScenarioSimulationModel(ScenarioSimulationModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Simulation</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Simulation</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseSimulation(Simulation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Simulation Descriptor</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Simulation Descriptor</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     */
    public T caseSimulationDescriptor(SimulationDescriptor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @generated
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     */
    @Override
    public T defaultCase(EObject object) {
        return null;
    }
} //scesimSwitch
