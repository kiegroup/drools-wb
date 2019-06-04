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
 * A representation of the model object '<em><b>Fact Identifier</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.FactIdentifier#getName <em>Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.FactIdentifier#getClassName <em>Class Name</em>}</li>
 * </ul>
 * @model extendedMetaData="name='factIdentifier' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.scesimPackage#getFactIdentifier()
 */
public interface FactIdentifier extends EObject {

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
     * @generated
     * @see #setName(String)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactIdentifier_Name()
     */
    String getName();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactIdentifier#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @generated
     * @see #getName()
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Class Name</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='className' namespace='##targetNamespace'"
     * @generated
     * @see #setClassName(String)
     * @see org.drools.emf.models.scesim.scesimPackage#getFactIdentifier_ClassName()
     */
    String getClassName();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.FactIdentifier#getClassName <em>Class Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Class Name</em>' attribute.
     * @generated
     * @see #getClassName()
     */
    void setClassName(String value);
} // FactIdentifier
