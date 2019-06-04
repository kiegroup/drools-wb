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
 * A representation of the model object '<em><b>Fact Mapping Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.FactMappingValue#getFactIdentifier <em>Fact Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMappingValue#getExpressionIdentifier <em>Expression Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMappingValue#getRawValue <em>Raw Value</em>}</li>
 * </ul>
 * @model extendedMetaData="name='factMappingValue' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.scesimPackage#getFactMappingValue()
 */
public interface FactMappingValue extends EObject {

    /**
     * Returns the value of the '<em><b>Fact Identifier</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fact Identifier</em>' containment reference.
     * @model containment="true" required="true"
     * extendedMetaData="kind='element' name='factIdentifier' namespace='##targetNamespace'"
     * @generated
     * @see #setFactIdentifier(FactIdentifier)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMappingValue_FactIdentifier()
     */
    FactIdentifier getFactIdentifier();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMappingValue#getFactIdentifier <em>Fact Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fact Identifier</em>' containment reference.
     * @generated
     * @see #getFactIdentifier()
     */
    void setFactIdentifier(FactIdentifier value);

    /**
     * Returns the value of the '<em><b>Expression Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Identifier</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='expressionIdentifier' namespace='##targetNamespace'"
     * @generated
     * @see #setExpressionIdentifier(String)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMappingValue_ExpressionIdentifier()
     */
    String getExpressionIdentifier();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMappingValue#getExpressionIdentifier <em>Expression Identifier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Identifier</em>' attribute.
     * @generated
     * @see #getExpressionIdentifier()
     */
    void setExpressionIdentifier(String value);

    /**
     * Returns the value of the '<em><b>Raw Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Raw Value</em>' containment reference.
     * @model containment="true" required="true"
     * extendedMetaData="kind='element' name='rawValue' namespace='##targetNamespace'"
     * @generated
     * @see #setRawValue(EObject)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMappingValue_RawValue()
     */
    EObject getRawValue();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMappingValue#getRawValue <em>Raw Value</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Raw Value</em>' containment reference.
     * @generated
     * @see #getRawValue()
     */
    void setRawValue(EObject value);
} // FactMappingValue
