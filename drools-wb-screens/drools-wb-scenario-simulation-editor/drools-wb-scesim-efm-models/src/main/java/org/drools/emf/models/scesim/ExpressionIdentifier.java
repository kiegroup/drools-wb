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
 * A representation of the model object '<em><b>Expression Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.ExpressionIdentifier#getName <em>Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.ExpressionIdentifier#getType <em>Type</em>}</li>
 * </ul>
 * @model extendedMetaData="name='expressionIdentifier' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.scesimPackage#getExpressionIdentifier()
 */
public interface ExpressionIdentifier extends EObject {

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
     * @generated
     * @see #setName(String)
     * @see org.drools.emf.models.scesim.scesimPackage#getExpressionIdentifier_Name()
     */
    String getName();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.ExpressionIdentifier#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @generated
     * @see #getName()
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.drools.emf.models.scesim.FactMappingType}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @model unsettable="true" required="true"
     * extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingType
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(FactMappingType)
     * @see org.drools.emf.models.scesim.scesimPackage#getExpressionIdentifier_Type()
     */
    FactMappingType getType();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.ExpressionIdentifier#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingType
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     */
    void setType(FactMappingType value);

    /**
     * Unsets the value of the '{@link org.drools.emf.models.scesim.ExpressionIdentifier#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see #isSetType()
     * @see #getType()
     * @see #setType(FactMappingType)
     */
    void unsetType();

    /**
     * Returns whether the value of the '{@link org.drools.emf.models.scesim.ExpressionIdentifier#getType <em>Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @generated
     * @see #unsetType()
     * @see #getType()
     * @see #setType(FactMappingType)
     */
    boolean isSetType();
} // ExpressionIdentifier
