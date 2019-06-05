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

import java.util.Collection;

import org.drools.emf.models.scesim.FactMappingValue;
import org.drools.emf.models.scesim.Scenario;
import org.drools.emf.models.scesim.ScesimPackage;
import org.drools.emf.models.scesim.SimulationDescriptor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scenario</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.impl.ScenarioImpl#getFactMappingValues <em>Fact Mapping Values</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.ScenarioImpl#getSimulationDescriptor <em>Simulation Descriptor</em>}</li>
 * </ul>
 * @generated
 */
public class ScenarioImpl extends MinimalEObjectImpl.Container implements Scenario {

    /**
     * The cached value of the '{@link #getFactMappingValues() <em>Fact Mapping Values</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getFactMappingValues()
     */
    protected EList<FactMappingValue> factMappingValues;

    /**
     * The cached value of the '{@link #getSimulationDescriptor() <em>Simulation Descriptor</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getSimulationDescriptor()
     */
    protected SimulationDescriptor simulationDescriptor;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScenarioImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScesimPackage.Literals.SCENARIO;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FactMappingValue> getFactMappingValues() {
        if (factMappingValues == null) {
            factMappingValues = new EObjectContainmentEList<FactMappingValue>(FactMappingValue.class, this, ScesimPackage.SCENARIO__FACT_MAPPING_VALUES);
        }
        return factMappingValues;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public SimulationDescriptor getSimulationDescriptor() {
        return simulationDescriptor;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSimulationDescriptor(SimulationDescriptor newSimulationDescriptor) {
        if (newSimulationDescriptor != simulationDescriptor) {
            NotificationChain msgs = null;
            if (simulationDescriptor != null) {
                msgs = ((InternalEObject) simulationDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR, null, msgs);
            }
            if (newSimulationDescriptor != null) {
                msgs = ((InternalEObject) newSimulationDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR, null, msgs);
            }
            msgs = basicSetSimulationDescriptor(newSimulationDescriptor, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR, newSimulationDescriptor, newSimulationDescriptor));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSimulationDescriptor(SimulationDescriptor newSimulationDescriptor, NotificationChain msgs) {
        SimulationDescriptor oldSimulationDescriptor = simulationDescriptor;
        simulationDescriptor = newSimulationDescriptor;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR, oldSimulationDescriptor, newSimulationDescriptor);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScesimPackage.SCENARIO__FACT_MAPPING_VALUES:
                return ((InternalEList<?>) getFactMappingValues()).basicRemove(otherEnd, msgs);
            case ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR:
                return basicSetSimulationDescriptor(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ScesimPackage.SCENARIO__FACT_MAPPING_VALUES:
                return getFactMappingValues();
            case ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR:
                return getSimulationDescriptor();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case ScesimPackage.SCENARIO__FACT_MAPPING_VALUES:
                getFactMappingValues().clear();
                getFactMappingValues().addAll((Collection<? extends FactMappingValue>) newValue);
                return;
            case ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR:
                setSimulationDescriptor((SimulationDescriptor) newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case ScesimPackage.SCENARIO__FACT_MAPPING_VALUES:
                getFactMappingValues().clear();
                return;
            case ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR:
                setSimulationDescriptor(null);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case ScesimPackage.SCENARIO__FACT_MAPPING_VALUES:
                return factMappingValues != null && !factMappingValues.isEmpty();
            case ScesimPackage.SCENARIO__SIMULATION_DESCRIPTOR:
                return simulationDescriptor != null;
        }
        return super.eIsSet(featureID);
    }
} //ScenarioImpl
