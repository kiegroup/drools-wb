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

import org.drools.emf.models.scesim.FactIdentifier;
import org.drools.emf.models.scesim.FactMappingValue;
import org.drools.emf.models.scesim.scesimPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fact Mapping Value</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingValueImpl#getFactIdentifier <em>Fact Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingValueImpl#getExpressionIdentifier <em>Expression Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingValueImpl#getRawValue <em>Raw Value</em>}</li>
 * </ul>
 * @generated
 */
public class FactMappingValueImpl extends MinimalEObjectImpl.Container implements FactMappingValue {

    /**
     * The default value of the '{@link #getExpressionIdentifier() <em>Expression Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getExpressionIdentifier()
     */
    protected static final String EXPRESSION_IDENTIFIER_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getFactIdentifier() <em>Fact Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getFactIdentifier()
     */
    protected FactIdentifier factIdentifier;
    /**
     * The cached value of the '{@link #getExpressionIdentifier() <em>Expression Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getExpressionIdentifier()
     */
    protected String expressionIdentifier = EXPRESSION_IDENTIFIER_EDEFAULT;

    /**
     * The cached value of the '{@link #getRawValue() <em>Raw Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getRawValue()
     */
    protected EObject rawValue;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FactMappingValueImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return scesimPackage.Literals.FACT_MAPPING_VALUE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FactIdentifier getFactIdentifier() {
        return factIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFactIdentifier(FactIdentifier newFactIdentifier) {
        if (newFactIdentifier != factIdentifier) {
            NotificationChain msgs = null;
            if (factIdentifier != null) {
                msgs = ((InternalEObject) factIdentifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER, null, msgs);
            }
            if (newFactIdentifier != null) {
                msgs = ((InternalEObject) newFactIdentifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER, null, msgs);
            }
            msgs = basicSetFactIdentifier(newFactIdentifier, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER, newFactIdentifier, newFactIdentifier));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFactIdentifier(FactIdentifier newFactIdentifier, NotificationChain msgs) {
        FactIdentifier oldFactIdentifier = factIdentifier;
        factIdentifier = newFactIdentifier;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER, oldFactIdentifier, newFactIdentifier);
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
    public String getExpressionIdentifier() {
        return expressionIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setExpressionIdentifier(String newExpressionIdentifier) {
        String oldExpressionIdentifier = expressionIdentifier;
        expressionIdentifier = newExpressionIdentifier;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, scesimPackage.FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER, oldExpressionIdentifier, expressionIdentifier));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EObject getRawValue() {
        return rawValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRawValue(EObject newRawValue) {
        if (newRawValue != rawValue) {
            NotificationChain msgs = null;
            if (rawValue != null) {
                msgs = ((InternalEObject) rawValue).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE, null, msgs);
            }
            if (newRawValue != null) {
                msgs = ((InternalEObject) newRawValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE, null, msgs);
            }
            msgs = basicSetRawValue(newRawValue, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE, newRawValue, newRawValue));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetRawValue(EObject newRawValue, NotificationChain msgs) {
        EObject oldRawValue = rawValue;
        rawValue = newRawValue;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE, oldRawValue, newRawValue);
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
            case scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER:
                return basicSetFactIdentifier(null, msgs);
            case scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE:
                return basicSetRawValue(null, msgs);
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
            case scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER:
                return getFactIdentifier();
            case scesimPackage.FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER:
                return getExpressionIdentifier();
            case scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE:
                return getRawValue();
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
            case scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER:
                setFactIdentifier((FactIdentifier) newValue);
                return;
            case scesimPackage.FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER:
                setExpressionIdentifier((String) newValue);
                return;
            case scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE:
                setRawValue((EObject) newValue);
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
            case scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER:
                setFactIdentifier(null);
                return;
            case scesimPackage.FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER:
                setExpressionIdentifier(EXPRESSION_IDENTIFIER_EDEFAULT);
                return;
            case scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE:
                setRawValue(null);
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
            case scesimPackage.FACT_MAPPING_VALUE__FACT_IDENTIFIER:
                return factIdentifier != null;
            case scesimPackage.FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER:
                return EXPRESSION_IDENTIFIER_EDEFAULT == null ? expressionIdentifier != null : !EXPRESSION_IDENTIFIER_EDEFAULT.equals(expressionIdentifier);
            case scesimPackage.FACT_MAPPING_VALUE__RAW_VALUE:
                return rawValue != null;
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
        result.append(" (expressionIdentifier: ");
        result.append(expressionIdentifier);
        result.append(')');
        return result.toString();
    }
} //FactMappingValueImpl
