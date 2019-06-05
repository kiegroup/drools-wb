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

import org.drools.emf.models.scesim.ExpressionElement;
import org.drools.emf.models.scesim.ExpressionIdentifier;
import org.drools.emf.models.scesim.FactIdentifier;
import org.drools.emf.models.scesim.FactMapping;
import org.drools.emf.models.scesim.ScesimPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fact Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingImpl#getExpressionElements <em>Expression Elements</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingImpl#getExpressionIdentifier <em>Expression Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingImpl#getFactIdentifier <em>Fact Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingImpl#getClassName <em>Class Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingImpl#getFactAlias <em>Fact Alias</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingImpl#getExpressionAlias <em>Expression Alias</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.impl.FactMappingImpl#getGenericTypes <em>Generic Types</em>}</li>
 * </ul>
 * @generated
 */
public class FactMappingImpl extends MinimalEObjectImpl.Container implements FactMapping {

    /**
     * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getClassName()
     */
    protected static final String CLASS_NAME_EDEFAULT = null;
    /**
     * The default value of the '{@link #getFactAlias() <em>Fact Alias</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getFactAlias()
     */
    protected static final String FACT_ALIAS_EDEFAULT = null;
    /**
     * The default value of the '{@link #getExpressionAlias() <em>Expression Alias</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getExpressionAlias()
     */
    protected static final String EXPRESSION_ALIAS_EDEFAULT = null;
    /**
     * The cached value of the '{@link #getExpressionElements() <em>Expression Elements</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getExpressionElements()
     */
    protected EList<ExpressionElement> expressionElements;
    /**
     * The cached value of the '{@link #getExpressionIdentifier() <em>Expression Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getExpressionIdentifier()
     */
    protected ExpressionIdentifier expressionIdentifier;
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
     * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getClassName()
     */
    protected String className = CLASS_NAME_EDEFAULT;
    /**
     * The cached value of the '{@link #getFactAlias() <em>Fact Alias</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getFactAlias()
     */
    protected String factAlias = FACT_ALIAS_EDEFAULT;
    /**
     * The cached value of the '{@link #getExpressionAlias() <em>Expression Alias</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getExpressionAlias()
     */
    protected String expressionAlias = EXPRESSION_ALIAS_EDEFAULT;

    /**
     * The cached value of the '{@link #getGenericTypes() <em>Generic Types</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #getGenericTypes()
     */
    protected EList<String> genericTypes;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected FactMappingImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ScesimPackage.Literals.FACT_MAPPING;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<ExpressionElement> getExpressionElements() {
        if (expressionElements == null) {
            expressionElements = new EObjectContainmentEList<ExpressionElement>(ExpressionElement.class, this, ScesimPackage.FACT_MAPPING__EXPRESSION_ELEMENTS);
        }
        return expressionElements;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public ExpressionIdentifier getExpressionIdentifier() {
        return expressionIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setExpressionIdentifier(ExpressionIdentifier newExpressionIdentifier) {
        if (newExpressionIdentifier != expressionIdentifier) {
            NotificationChain msgs = null;
            if (expressionIdentifier != null) {
                msgs = ((InternalEObject) expressionIdentifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER, null, msgs);
            }
            if (newExpressionIdentifier != null) {
                msgs = ((InternalEObject) newExpressionIdentifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER, null, msgs);
            }
            msgs = basicSetExpressionIdentifier(newExpressionIdentifier, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER, newExpressionIdentifier, newExpressionIdentifier));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetExpressionIdentifier(ExpressionIdentifier newExpressionIdentifier, NotificationChain msgs) {
        ExpressionIdentifier oldExpressionIdentifier = expressionIdentifier;
        expressionIdentifier = newExpressionIdentifier;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER, oldExpressionIdentifier, newExpressionIdentifier);
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
    public FactIdentifier getFactIdentifier() {
        return factIdentifier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFactIdentifier(FactIdentifier newFactIdentifier) {
        if (newFactIdentifier != factIdentifier) {
            NotificationChain msgs = null;
            if (factIdentifier != null) {
                msgs = ((InternalEObject) factIdentifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER, null, msgs);
            }
            if (newFactIdentifier != null) {
                msgs = ((InternalEObject) newFactIdentifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER, null, msgs);
            }
            msgs = basicSetFactIdentifier(newFactIdentifier, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER, newFactIdentifier, newFactIdentifier));
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER, oldFactIdentifier, newFactIdentifier);
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
    public String getClassName() {
        return className;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setClassName(String newClassName) {
        String oldClassName = className;
        className = newClassName;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.FACT_MAPPING__CLASS_NAME, oldClassName, className));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getFactAlias() {
        return factAlias;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setFactAlias(String newFactAlias) {
        String oldFactAlias = factAlias;
        factAlias = newFactAlias;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.FACT_MAPPING__FACT_ALIAS, oldFactAlias, factAlias));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String getExpressionAlias() {
        return expressionAlias;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void setExpressionAlias(String newExpressionAlias) {
        String oldExpressionAlias = expressionAlias;
        expressionAlias = newExpressionAlias;
        if (eNotificationRequired()) {
            eNotify(new ENotificationImpl(this, Notification.SET, ScesimPackage.FACT_MAPPING__EXPRESSION_ALIAS, oldExpressionAlias, expressionAlias));
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EList<String> getGenericTypes() {
        if (genericTypes == null) {
            genericTypes = new EDataTypeEList<String>(String.class, this, ScesimPackage.FACT_MAPPING__GENERIC_TYPES);
        }
        return genericTypes;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ELEMENTS:
                return ((InternalEList<?>) getExpressionElements()).basicRemove(otherEnd, msgs);
            case ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER:
                return basicSetExpressionIdentifier(null, msgs);
            case ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER:
                return basicSetFactIdentifier(null, msgs);
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
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ELEMENTS:
                return getExpressionElements();
            case ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER:
                return getExpressionIdentifier();
            case ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER:
                return getFactIdentifier();
            case ScesimPackage.FACT_MAPPING__CLASS_NAME:
                return getClassName();
            case ScesimPackage.FACT_MAPPING__FACT_ALIAS:
                return getFactAlias();
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ALIAS:
                return getExpressionAlias();
            case ScesimPackage.FACT_MAPPING__GENERIC_TYPES:
                return getGenericTypes();
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
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ELEMENTS:
                getExpressionElements().clear();
                getExpressionElements().addAll((Collection<? extends ExpressionElement>) newValue);
                return;
            case ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER:
                setExpressionIdentifier((ExpressionIdentifier) newValue);
                return;
            case ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER:
                setFactIdentifier((FactIdentifier) newValue);
                return;
            case ScesimPackage.FACT_MAPPING__CLASS_NAME:
                setClassName((String) newValue);
                return;
            case ScesimPackage.FACT_MAPPING__FACT_ALIAS:
                setFactAlias((String) newValue);
                return;
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ALIAS:
                setExpressionAlias((String) newValue);
                return;
            case ScesimPackage.FACT_MAPPING__GENERIC_TYPES:
                getGenericTypes().clear();
                getGenericTypes().addAll((Collection<? extends String>) newValue);
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
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ELEMENTS:
                getExpressionElements().clear();
                return;
            case ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER:
                setExpressionIdentifier(null);
                return;
            case ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER:
                setFactIdentifier(null);
                return;
            case ScesimPackage.FACT_MAPPING__CLASS_NAME:
                setClassName(CLASS_NAME_EDEFAULT);
                return;
            case ScesimPackage.FACT_MAPPING__FACT_ALIAS:
                setFactAlias(FACT_ALIAS_EDEFAULT);
                return;
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ALIAS:
                setExpressionAlias(EXPRESSION_ALIAS_EDEFAULT);
                return;
            case ScesimPackage.FACT_MAPPING__GENERIC_TYPES:
                getGenericTypes().clear();
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
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ELEMENTS:
                return expressionElements != null && !expressionElements.isEmpty();
            case ScesimPackage.FACT_MAPPING__EXPRESSION_IDENTIFIER:
                return expressionIdentifier != null;
            case ScesimPackage.FACT_MAPPING__FACT_IDENTIFIER:
                return factIdentifier != null;
            case ScesimPackage.FACT_MAPPING__CLASS_NAME:
                return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
            case ScesimPackage.FACT_MAPPING__FACT_ALIAS:
                return FACT_ALIAS_EDEFAULT == null ? factAlias != null : !FACT_ALIAS_EDEFAULT.equals(factAlias);
            case ScesimPackage.FACT_MAPPING__EXPRESSION_ALIAS:
                return EXPRESSION_ALIAS_EDEFAULT == null ? expressionAlias != null : !EXPRESSION_ALIAS_EDEFAULT.equals(expressionAlias);
            case ScesimPackage.FACT_MAPPING__GENERIC_TYPES:
                return genericTypes != null && !genericTypes.isEmpty();
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
        result.append(" (className: ");
        result.append(className);
        result.append(", factAlias: ");
        result.append(factAlias);
        result.append(", expressionAlias: ");
        result.append(expressionAlias);
        result.append(", genericTypes: ");
        result.append(genericTypes);
        result.append(')');
        return result.toString();
    }
} //FactMappingImpl
