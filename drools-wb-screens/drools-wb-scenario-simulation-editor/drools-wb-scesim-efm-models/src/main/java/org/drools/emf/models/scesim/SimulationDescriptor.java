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
 * A representation of the model object '<em><b>Simulation Descriptor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getFactMappings <em>Fact Mappings</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmoSession <em>Dmo Session</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnFilePath <em>Dmn File Path</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getType <em>Type</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getFileName <em>File Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getKieSession <em>Kie Session</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getKieBase <em>Kie Base</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getRuleFlowGroup <em>Rule Flow Group</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnNamespace <em>Dmn Namespace</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnName <em>Dmn Name</em>}</li>
 * <li>{@link org.drools.emf.models.scesim.SimulationDescriptor#isSkipFromBuild <em>Skip From Build</em>}</li>
 * </ul>
 * @model extendedMetaData="name='simulationDescriptor' kind='elementOnly'"
 * @generated
 * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor()
 */
public interface SimulationDescriptor extends EObject {

    /**
     * Returns the value of the '<em><b>Fact Mappings</b></em>' containment reference list.
     * The list contents are of type {@link org.drools.emf.models.scesim.FactMapping}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fact Mappings</em>' containment reference list.
     * @model containment="true"
     * extendedMetaData="kind='element' name='factMappings' namespace='##targetNamespace'"
     * @generated
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_FactMappings()
     */
    EList<FactMapping> getFactMappings();

    /**
     * Returns the value of the '<em><b>Dmo Session</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dmo Session</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='dmoSession' namespace='##targetNamespace'"
     * @generated
     * @see #setDmoSession(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_DmoSession()
     */
    String getDmoSession();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmoSession <em>Dmo Session</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dmo Session</em>' attribute.
     * @generated
     * @see #getDmoSession()
     */
    void setDmoSession(String value);

    /**
     * Returns the value of the '<em><b>Dmn File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dmn File Path</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='dmnFilePath' namespace='##targetNamespace'"
     * @generated
     * @see #setDmnFilePath(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_DmnFilePath()
     */
    String getDmnFilePath();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnFilePath <em>Dmn File Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dmn File Path</em>' attribute.
     * @generated
     * @see #getDmnFilePath()
     */
    void setDmnFilePath(String value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.drools.emf.models.scesim.Type}.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @model unsettable="true" required="true"
     * extendedMetaData="kind='element' name='type' namespace='##targetNamespace'"
     * @generated
     * @see org.drools.emf.models.scesim.Type
     * @see #isSetType()
     * @see #unsetType()
     * @see #setType(Type)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_Type()
     */
    Type getType();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @generated
     * @see org.drools.emf.models.scesim.Type
     * @see #isSetType()
     * @see #unsetType()
     * @see #getType()
     */
    void setType(Type value);

    /**
     * Unsets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see #isSetType()
     * @see #getType()
     * @see #setType(Type)
     */
    void unsetType();

    /**
     * Returns whether the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getType <em>Type</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Type</em>' attribute is set.
     * @generated
     * @see #unsetType()
     * @see #getType()
     * @see #setType(Type)
     */
    boolean isSetType();

    /**
     * Returns the value of the '<em><b>File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>File Name</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='fileName' namespace='##targetNamespace'"
     * @generated
     * @see #setFileName(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_FileName()
     */
    String getFileName();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getFileName <em>File Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>File Name</em>' attribute.
     * @generated
     * @see #getFileName()
     */
    void setFileName(String value);

    /**
     * Returns the value of the '<em><b>Kie Session</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kie Session</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='kieSession' namespace='##targetNamespace'"
     * @generated
     * @see #setKieSession(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_KieSession()
     */
    String getKieSession();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getKieSession <em>Kie Session</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kie Session</em>' attribute.
     * @generated
     * @see #getKieSession()
     */
    void setKieSession(String value);

    /**
     * Returns the value of the '<em><b>Kie Base</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Kie Base</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='kieBase' namespace='##targetNamespace'"
     * @generated
     * @see #setKieBase(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_KieBase()
     */
    String getKieBase();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getKieBase <em>Kie Base</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kie Base</em>' attribute.
     * @generated
     * @see #getKieBase()
     */
    void setKieBase(String value);

    /**
     * Returns the value of the '<em><b>Rule Flow Group</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rule Flow Group</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='ruleFlowGroup' namespace='##targetNamespace'"
     * @generated
     * @see #setRuleFlowGroup(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_RuleFlowGroup()
     */
    String getRuleFlowGroup();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getRuleFlowGroup <em>Rule Flow Group</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Rule Flow Group</em>' attribute.
     * @generated
     * @see #getRuleFlowGroup()
     */
    void setRuleFlowGroup(String value);

    /**
     * Returns the value of the '<em><b>Dmn Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dmn Namespace</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='dmnNamespace' namespace='##targetNamespace'"
     * @generated
     * @see #setDmnNamespace(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_DmnNamespace()
     */
    String getDmnNamespace();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnNamespace <em>Dmn Namespace</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dmn Namespace</em>' attribute.
     * @generated
     * @see #getDmnNamespace()
     */
    void setDmnNamespace(String value);

    /**
     * Returns the value of the '<em><b>Dmn Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Dmn Name</em>' attribute.
     * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
     * extendedMetaData="kind='element' name='dmnName' namespace='##targetNamespace'"
     * @generated
     * @see #setDmnName(String)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_DmnName()
     */
    String getDmnName();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnName <em>Dmn Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Dmn Name</em>' attribute.
     * @generated
     * @see #getDmnName()
     */
    void setDmnName(String value);

    /**
     * Returns the value of the '<em><b>Skip From Build</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Skip From Build</em>' attribute.
     * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
     * extendedMetaData="kind='element' name='skipFromBuild' namespace='##targetNamespace'"
     * @generated
     * @see #isSetSkipFromBuild()
     * @see #unsetSkipFromBuild()
     * @see #setSkipFromBuild(boolean)
     * @see org.drools.emf.models.scesim.ScesimPackage#getSimulationDescriptor_SkipFromBuild()
     */
    boolean isSkipFromBuild();

    /**
     * Sets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#isSkipFromBuild <em>Skip From Build</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Skip From Build</em>' attribute.
     * @generated
     * @see #isSetSkipFromBuild()
     * @see #unsetSkipFromBuild()
     * @see #isSkipFromBuild()
     */
    void setSkipFromBuild(boolean value);

    /**
     * Unsets the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#isSkipFromBuild <em>Skip From Build</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see #isSetSkipFromBuild()
     * @see #isSkipFromBuild()
     * @see #setSkipFromBuild(boolean)
     */
    void unsetSkipFromBuild();

    /**
     * Returns whether the value of the '{@link org.drools.emf.models.scesim.SimulationDescriptor#isSkipFromBuild <em>Skip From Build</em>}' attribute is set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return whether the value of the '<em>Skip From Build</em>' attribute is set.
     * @generated
     * @see #unsetSkipFromBuild()
     * @see #isSkipFromBuild()
     * @see #setSkipFromBuild(boolean)
     */
    boolean isSetSkipFromBuild();
} // SimulationDescriptor
