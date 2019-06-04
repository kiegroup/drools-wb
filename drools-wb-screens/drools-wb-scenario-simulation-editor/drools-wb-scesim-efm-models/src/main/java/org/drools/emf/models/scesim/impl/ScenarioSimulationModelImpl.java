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

import org.drools.emf.models.scesim.Imports;
import org.drools.emf.models.scesim.ScenarioSimulationModel;
import org.drools.emf.models.scesim.Simulation;
import org.drools.emf.models.scesim.scesimPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scenario Simulation Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.impl.ScenarioSimulationModelImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.ScenarioSimulationModelImpl#getSimulation <em>Simulation</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.ScenarioSimulationModelImpl#getImports <em>Imports</em>}</li>
 * </ul>
 * @generated
 */
public class ScenarioSimulationModelImpl extends MinimalEObjectImpl.Container implements ScenarioSimulationModel {

    /**
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getVersion()
     */
    protected static final String VERSION_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getVersion()
     */
    protected String version = VERSION_EDEFAULT;

    /**
     * The cached value of the '{@link #getSimulation() <em>Simulation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getSimulation()
     */
    protected Simulation simulation;

    /**
     * The cached value of the '{@link #getImports() <em>Imports</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getImports()
     */
    protected Imports imports;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ScenarioSimulationModelImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return scesimPackage.Literals.SCENARIO_SIMULATION_MODEL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getVersion() {
        return version;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, scesimPackage.SCENARIO_SIMULATION_MODEL__VERSION, oldVersion, version));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Simulation getSimulation() {
        return simulation;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSimulation(Simulation newSimulation) {
        if (newSimulation != simulation) {
            NotificationChain msgs = null;
            if (simulation != null) {
                msgs = ((InternalEObject) simulation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION, null, msgs);
            }
            if (newSimulation != null) {
                msgs = ((InternalEObject) newSimulation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION, null, msgs);
            }
            msgs = basicSetSimulation(newSimulation, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION, newSimulation, newSimulation));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetSimulation(Simulation newSimulation, NotificationChain msgs) {
        Simulation oldSimulation = simulation;
        simulation = newSimulation;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION, oldSimulation, newSimulation);
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
    public Imports getImports() {
        return imports;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setImports(Imports newImports) {
        if (newImports != imports) {
            NotificationChain msgs = null;
            if (imports != null) {
                msgs = ((InternalEObject) imports).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS, null, msgs);
            }
            if (newImports != null) {
                msgs = ((InternalEObject) newImports).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS, null, msgs);
            }
            msgs = basicSetImports(newImports, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS, newImports, newImports));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetImports(Imports newImports, NotificationChain msgs) {
        Imports oldImports = imports;
        imports = newImports;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS, oldImports, newImports);
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
            case scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION:
                return basicSetSimulation(null, msgs);
            case scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS:
                return basicSetImports(null, msgs);
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
            case scesimPackage.SCENARIO_SIMULATION_MODEL__VERSION:
                return getVersion();
            case scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION:
                return getSimulation();
            case scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS:
                return getImports();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case scesimPackage.SCENARIO_SIMULATION_MODEL__VERSION:
                setVersion((String) newValue);
                return;
            case scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION:
                setSimulation((Simulation) newValue);
                return;
            case scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS:
                setImports((Imports) newValue);
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
            case scesimPackage.SCENARIO_SIMULATION_MODEL__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION:
                setSimulation(null);
                return;
            case scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS:
                setImports(null);
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
            case scesimPackage.SCENARIO_SIMULATION_MODEL__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case scesimPackage.SCENARIO_SIMULATION_MODEL__SIMULATION:
                return simulation != null;
            case scesimPackage.SCENARIO_SIMULATION_MODEL__IMPORTS:
                return imports != null;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) {
            return super.toString();
        }

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (version: ");
        result.append(version);
        result.append(')');
        return result.toString();
    }
} //ScenarioSimulationModelImpl
