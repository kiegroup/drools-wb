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
 * A representation of the model object '<em><b>Imports</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.Imports#getImports <em>Imports</em>}</li>
 * </ul>
 * @model extendedMetaData="name='imports' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.ScesimPackage#getImports()
 */
public interface Imports extends EObject {

    /**
     * Returns the value of the '<em><b>Imports</b></em>' containment reference list.
     * The list contents are of type {@link org.drools.emf.models.scesim.Import}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Imports</em>' containment reference list.
     * @model containment="true"
     * extendedMetaData="kind='element' name='imports' namespace='##targetNamespace'"
     * @generated
     * @see org.drools.emf.models.scesim.ScesimPackage#getImports_Imports()
     */
    EList<Import> getImports();
} // Imports
