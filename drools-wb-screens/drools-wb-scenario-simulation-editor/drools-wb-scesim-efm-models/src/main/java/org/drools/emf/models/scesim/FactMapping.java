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
 * A representation of the model object '<em><b>Fact Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.FactMapping#getExpressionElements <em>Expression Elements</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMapping#getExpressionIdentifier <em>Expression Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMapping#getFactIdentifier <em>Fact Identifier</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMapping#getClassName <em>Class Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMapping#getFactAlias <em>Fact Alias</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMapping#getExpressionAlias <em>Expression Alias</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactMapping#getGenericTypes <em>Generic Types</em>}</li>
 * </ul>
 * @model extendedMetaData="name='factMapping' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping()
 */
public interface FactMapping extends EObject {

    /**
     * Returns the value of the '<em><b>Expression Elements</b></em>' containment reference list.
     * The list contents are of type {@link org.drools.emf.models.scesim.ExpressionElement}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Elements</em>' containment reference list.
     * @model containment="true"
     * extendedMetaData="kind='element' name='expressionElements' namespace='##targetNamespace'"
     * @generated
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping_ExpressionElements()
     */
    EList<ExpressionElement> getExpressionElements();

    /**
     * Returns the value of the '<em><b>Expression Identifier</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Identifier</em>' containment reference.
     * @model containment="true" required="true"
     * extendedMetaData="kind='element' name='expressionIdentifier' namespace='##targetNamespace'"
     * @generated
     * @see #setExpressionIdentifier(ExpressionIdentifier)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping_ExpressionIdentifier()
     */
    ExpressionIdentifier getExpressionIdentifier();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMapping#getExpressionIdentifier <em>Expression Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Identifier</em>' containment reference.
     * @generated
     * @see #getExpressionIdentifier()
     */
    void setExpressionIdentifier(ExpressionIdentifier value);

    /**
     * Returns the value of the '<em><b>Fact Identifier</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fact Identifier</em>' containment reference.
     * @model containment="true" required="true"
     * extendedMetaData="kind='element' name='factIdentifier' namespace='##targetNamespace'"
     * @generated
     * @see #setFactIdentifier(FactIdentifier)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping_FactIdentifier()
     */
    FactIdentifier getFactIdentifier();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMapping#getFactIdentifier <em>Fact Identifier</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fact Identifier</em>' containment reference.
     * @generated
     * @see #getFactIdentifier()
     */
    void setFactIdentifier(FactIdentifier value);

    /**
     * Returns the value of the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Class Name</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='className' namespace='##targetNamespace'"
     * @generated
     * @see #setClassName(String)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping_ClassName()
     */
    String getClassName();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMapping#getClassName <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Name</em>' attribute.
     * @generated
     * @see #getClassName()
     */
    void setClassName(String value);

    /**
     * Returns the value of the '<em><b>Fact Alias</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fact Alias</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='factAlias' namespace='##targetNamespace'"
     * @generated
     * @see #setFactAlias(String)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping_FactAlias()
     */
    String getFactAlias();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMapping#getFactAlias <em>Fact Alias</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fact Alias</em>' attribute.
     * @generated
     * @see #getFactAlias()
     */
    void setFactAlias(String value);

    /**
     * Returns the value of the '<em><b>Expression Alias</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Alias</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='expressionAlias' namespace='##targetNamespace'"
     * @generated
     * @see #setExpressionAlias(String)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping_ExpressionAlias()
     */
    String getExpressionAlias();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactMapping#getExpressionAlias <em>Expression Alias</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Alias</em>' attribute.
     * @generated
     * @see #getExpressionAlias()
     */
    void setExpressionAlias(String value);

    /**
     * Returns the value of the '<em><b>Generic Types</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Generic Types</em>' attribute list.
     * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
     * extendedMetaData="kind='element' name='genericTypes' namespace='##targetNamespace'"
     * @generated
     * @see org.drools.emf.models.scesim.scesimPackage#getFactMapping_GenericTypes()
     */
    EList<String> getGenericTypes();
} // FactMapping
