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

import org.drools.emf.models.scesim.FactMapping;
import org.drools.emf.models.scesim.ScesimPackage;
import org.drools.emf.models.scesim.SimulationDescriptor;
import org.drools.emf.models.scesim.Type;
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
 * An implementation of the model object '<em><b>Simulation Descriptor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getFactMappings <em>Fact Mappings</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getDmoSession <em>Dmo Session</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getDmnFilePath <em>Dmn File Path</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getType <em>Type</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getFileName <em>File Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getKieSession <em>Kie Session</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getKieBase <em>Kie Base</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getRuleFlowGroup <em>Rule Flow Group</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getDmnNamespace <em>Dmn Namespace</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#getDmnName <em>Dmn Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl#isSkipFromBuild <em>Skip From Build</em>}</li>
 * </ul>
 * @generated
 */
public class SimulationDescriptorImpl extends MinimalEObjectImpl.Container implements SimulationDescriptor {

    /**
     * The default value of the '{@link #getDmoSession() <em>Dmo Session</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmoSession()
     */
    protected static final String DMO_SESSION_EDEFAULT = null;
    /**
     * The default value of the '{@link #getDmnFilePath() <em>Dmn File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmnFilePath()
     */
    protected static final String DMN_FILE_PATH_EDEFAULT = null;
    /**
     * The default value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getType()
     */
    protected static final Type TYPE_EDEFAULT = Type.RULE;
    /**
     * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getFileName()
     */
    protected static final String FILE_NAME_EDEFAULT = null;
    /**
     * The default value of the '{@link #getKieSession() <em>Kie Session</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getKieSession()
     */
    protected static final String KIE_SESSION_EDEFAULT = null;
    /**
     * The default value of the '{@link #getKieBase() <em>Kie Base</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getKieBase()
     */
    protected static final String KIE_BASE_EDEFAULT = null;
    /**
     * The default value of the '{@link #getRuleFlowGroup() <em>Rule Flow Group</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getRuleFlowGroup()
     */
    protected static final String RULE_FLOW_GROUP_EDEFAULT = null;
    /**
     * The default value of the '{@link #getDmnNamespace() <em>Dmn Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmnNamespace()
     */
    protected static final String DMN_NAMESPACE_EDEFAULT = null;
    /**
     * The default value of the '{@link #getDmnName() <em>Dmn Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmnName()
     */
    protected static final String DMN_NAME_EDEFAULT = null;
    /**
     * The default value of the '{@link #isSkipFromBuild() <em>Skip From Build</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #isSkipFromBuild()
     */
    protected static final boolean SKIP_FROM_BUILD_EDEFAULT = false;
    /**
     * The cached value of the '{@link #getFactMappings() <em>Fact Mappings</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getFactMappings()
     */
    protected EList<FactMapping> factMappings;
    /**
     * The cached value of the '{@link #getDmoSession() <em>Dmo Session</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmoSession()
     */
    protected String dmoSession = DMO_SESSION_EDEFAULT;
    /**
     * The cached value of the '{@link #getDmnFilePath() <em>Dmn File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmnFilePath()
     */
    protected String dmnFilePath = DMN_FILE_PATH_EDEFAULT;
    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getType()
     */
    protected Type type = TYPE_EDEFAULT;
    /**
     * This is true if the Type attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean typeESet;
    /**
     * The cached value of the '{@link #getFileName() <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getFileName()
     */
    protected String fileName = FILE_NAME_EDEFAULT;
    /**
     * The cached value of the '{@link #getKieSession() <em>Kie Session</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getKieSession()
     */
    protected String kieSession = KIE_SESSION_EDEFAULT;
    /**
     * The cached value of the '{@link #getKieBase() <em>Kie Base</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getKieBase()
     */
    protected String kieBase = KIE_BASE_EDEFAULT;
    /**
     * The cached value of the '{@link #getRuleFlowGroup() <em>Rule Flow Group</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getRuleFlowGroup()
     */
    protected String ruleFlowGroup = RULE_FLOW_GROUP_EDEFAULT;
    /**
     * The cached value of the '{@link #getDmnNamespace() <em>Dmn Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmnNamespace()
     */
    protected String dmnNamespace = DMN_NAMESPACE_EDEFAULT;
    /**
     * The cached value of the '{@link #getDmnName() <em>Dmn Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getDmnName()
     */
    protected String dmnName = DMN_NAME_EDEFAULT;
    /**
     * The cached value of the '{@link #isSkipFromBuild() <em>Skip From Build</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #isSkipFromBuild()
     */
    protected boolean skipFromBuild = SKIP_FROM_BUILD_EDEFAULT;

    /**
     * This is true if the Skip From Build attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean skipFromBuildESet;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimulationDescriptorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScesimPackage.Literals.SIMULATION_DESCRIPTOR;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<FactMapping> getFactMappings() {
        if (factMappings == null) {
            factMappings = new EObjectContainmentEList<FactMapping>(FactMapping.class, this, ScesimPackage.SIMULATION_DESCRIPTOR__FACT_MAPPINGS);
        }
        return factMappings;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDmoSession() {
        return dmoSession;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDmoSession(String newDmoSession) {
        String oldDmoSession = dmoSession;
        dmoSession = newDmoSession;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__DMO_SESSION, oldDmoSession, dmoSession));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDmnFilePath() {
        return dmnFilePath;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDmnFilePath(String newDmnFilePath) {
        String oldDmnFilePath = dmnFilePath;
        dmnFilePath = newDmnFilePath;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__DMN_FILE_PATH, oldDmnFilePath, dmnFilePath));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Type getType() {
        return type;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setType(Type newType) {
        Type oldType = type;
        type = newType == null ? TYPE_EDEFAULT : newType;
        boolean oldTypeESet = typeESet;
        typeESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__TYPE, oldType, type, !oldTypeESet));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetType() {
        Type oldType = type;
        boolean oldTypeESet = typeESet;
        type = TYPE_EDEFAULT;
        typeESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScesimPackage.SIMULATION_DESCRIPTOR__TYPE, oldType, TYPE_EDEFAULT, oldTypeESet));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetType() {
        return typeESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFileName(String newFileName) {
        String oldFileName = fileName;
        fileName = newFileName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__FILE_NAME, oldFileName, fileName));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getKieSession() {
        return kieSession;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setKieSession(String newKieSession) {
        String oldKieSession = kieSession;
        kieSession = newKieSession;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__KIE_SESSION, oldKieSession, kieSession));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getKieBase() {
        return kieBase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setKieBase(String newKieBase) {
        String oldKieBase = kieBase;
        kieBase = newKieBase;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__KIE_BASE, oldKieBase, kieBase));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getRuleFlowGroup() {
        return ruleFlowGroup;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setRuleFlowGroup(String newRuleFlowGroup) {
        String oldRuleFlowGroup = ruleFlowGroup;
        ruleFlowGroup = newRuleFlowGroup;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP, oldRuleFlowGroup, ruleFlowGroup));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDmnNamespace() {
        return dmnNamespace;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDmnNamespace(String newDmnNamespace) {
        String oldDmnNamespace = dmnNamespace;
        dmnNamespace = newDmnNamespace;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAMESPACE, oldDmnNamespace, dmnNamespace));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getDmnName() {
        return dmnName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setDmnName(String newDmnName) {
        String oldDmnName = dmnName;
        dmnName = newDmnName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAME, oldDmnName, dmnName));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSkipFromBuild() {
        return skipFromBuild;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setSkipFromBuild(boolean newSkipFromBuild) {
        boolean oldSkipFromBuild = skipFromBuild;
        skipFromBuild = newSkipFromBuild;
        boolean oldSkipFromBuildESet = skipFromBuildESet;
        skipFromBuildESet = true;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD, oldSkipFromBuild, skipFromBuild, !oldSkipFromBuildESet));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void unsetSkipFromBuild() {
        boolean oldSkipFromBuild = skipFromBuild;
        boolean oldSkipFromBuildESet = skipFromBuildESet;
        skipFromBuild = SKIP_FROM_BUILD_EDEFAULT;
        skipFromBuildESet = false;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.UNSET, ScesimPackage.SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD, oldSkipFromBuild, SKIP_FROM_BUILD_EDEFAULT, oldSkipFromBuildESet));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isSetSkipFromBuild() {
        return skipFromBuildESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScesimPackage.SIMULATION_DESCRIPTOR__FACT_MAPPINGS:
                return ((InternalEList<?>) getFactMappings()).basicRemove(otherEnd, msgs);
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
            case ScesimPackage.SIMULATION_DESCRIPTOR__FACT_MAPPINGS:
                return getFactMappings();
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMO_SESSION:
                return getDmoSession();
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_FILE_PATH:
                return getDmnFilePath();
            case ScesimPackage.SIMULATION_DESCRIPTOR__TYPE:
                return getType();
            case ScesimPackage.SIMULATION_DESCRIPTOR__FILE_NAME:
                return getFileName();
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_SESSION:
                return getKieSession();
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_BASE:
                return getKieBase();
            case ScesimPackage.SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP:
                return getRuleFlowGroup();
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAMESPACE:
                return getDmnNamespace();
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAME:
                return getDmnName();
            case ScesimPackage.SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD:
                return isSkipFromBuild();
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
            case ScesimPackage.SIMULATION_DESCRIPTOR__FACT_MAPPINGS:
                getFactMappings().clear();
                getFactMappings().addAll((Collection<? extends FactMapping>) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMO_SESSION:
                setDmoSession((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_FILE_PATH:
                setDmnFilePath((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__TYPE:
                setType((Type) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__FILE_NAME:
                setFileName((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_SESSION:
                setKieSession((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_BASE:
                setKieBase((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP:
                setRuleFlowGroup((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAMESPACE:
                setDmnNamespace((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAME:
                setDmnName((String) newValue);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD:
                setSkipFromBuild((Boolean) newValue);
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
            case ScesimPackage.SIMULATION_DESCRIPTOR__FACT_MAPPINGS:
                getFactMappings().clear();
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMO_SESSION:
                setDmoSession(DMO_SESSION_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_FILE_PATH:
                setDmnFilePath(DMN_FILE_PATH_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__TYPE:
                unsetType();
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__FILE_NAME:
                setFileName(FILE_NAME_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_SESSION:
                setKieSession(KIE_SESSION_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_BASE:
                setKieBase(KIE_BASE_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP:
                setRuleFlowGroup(RULE_FLOW_GROUP_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAMESPACE:
                setDmnNamespace(DMN_NAMESPACE_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAME:
                setDmnName(DMN_NAME_EDEFAULT);
                return;
            case ScesimPackage.SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD:
                unsetSkipFromBuild();
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
            case ScesimPackage.SIMULATION_DESCRIPTOR__FACT_MAPPINGS:
                return factMappings != null && !factMappings.isEmpty();
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMO_SESSION:
                return DMO_SESSION_EDEFAULT == null ? dmoSession != null : !DMO_SESSION_EDEFAULT.equals(dmoSession);
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_FILE_PATH:
                return DMN_FILE_PATH_EDEFAULT == null ? dmnFilePath != null : !DMN_FILE_PATH_EDEFAULT.equals(dmnFilePath);
            case ScesimPackage.SIMULATION_DESCRIPTOR__TYPE:
                return isSetType();
            case ScesimPackage.SIMULATION_DESCRIPTOR__FILE_NAME:
                return FILE_NAME_EDEFAULT == null ? fileName != null : !FILE_NAME_EDEFAULT.equals(fileName);
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_SESSION:
                return KIE_SESSION_EDEFAULT == null ? kieSession != null : !KIE_SESSION_EDEFAULT.equals(kieSession);
            case ScesimPackage.SIMULATION_DESCRIPTOR__KIE_BASE:
                return KIE_BASE_EDEFAULT == null ? kieBase != null : !KIE_BASE_EDEFAULT.equals(kieBase);
            case ScesimPackage.SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP:
                return RULE_FLOW_GROUP_EDEFAULT == null ? ruleFlowGroup != null : !RULE_FLOW_GROUP_EDEFAULT.equals(ruleFlowGroup);
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAMESPACE:
                return DMN_NAMESPACE_EDEFAULT == null ? dmnNamespace != null : !DMN_NAMESPACE_EDEFAULT.equals(dmnNamespace);
            case ScesimPackage.SIMULATION_DESCRIPTOR__DMN_NAME:
                return DMN_NAME_EDEFAULT == null ? dmnName != null : !DMN_NAME_EDEFAULT.equals(dmnName);
            case ScesimPackage.SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD:
                return isSetSkipFromBuild();
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
        result.append(" (dmoSession: ");
        result.append(dmoSession);
        result.append(", dmnFilePath: ");
        result.append(dmnFilePath);
        result.append(", type: ");
        if (typeESet) {
            result.append(type);
        } else {
            result.append("<unset>");
        }
        result.append(", fileName: ");
        result.append(fileName);
        result.append(", kieSession: ");
        result.append(kieSession);
        result.append(", kieBase: ");
        result.append(kieBase);
        result.append(", ruleFlowGroup: ");
        result.append(ruleFlowGroup);
        result.append(", dmnNamespace: ");
        result.append(dmnNamespace);
        result.append(", dmnName: ");
        result.append(dmnName);
        result.append(", skipFromBuild: ");
        if (skipFromBuildESet) {
            result.append(skipFromBuild);
        } else {
            result.append("<unset>");
        }
        result.append(')');
        return result.toString();
    }
} //SimulationDescriptorImpl
