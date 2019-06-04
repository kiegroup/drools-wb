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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @model kind="package"
 * extendedMetaData="qualified='false'"
 * @generated
 * @see org.drools.emf.models.scesim.scesimFactory
 */
public interface scesimPackage extends EPackage {

    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "scesim";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://www.kie.org/scesim.xsd";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "scesim";
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.ExpressionElementImpl <em>Expression Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.ExpressionElementImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getExpressionElement()
     */
    int EXPRESSION_ELEMENT = 0;
    /**
     * The feature id for the '<em><b>Step</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_ELEMENT__STEP = 0;
    /**
     * The number of structural features of the '<em>Expression Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_ELEMENT_FEATURE_COUNT = 1;
    /**
     * The number of operations of the '<em>Expression Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_ELEMENT_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.ExpressionIdentifierImpl <em>Expression Identifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.ExpressionIdentifierImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getExpressionIdentifier()
     */
    int EXPRESSION_IDENTIFIER = 1;
    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_IDENTIFIER__NAME = 0;
    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_IDENTIFIER__TYPE = 1;
    /**
     * The number of structural features of the '<em>Expression Identifier</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_IDENTIFIER_FEATURE_COUNT = 2;
    /**
     * The number of operations of the '<em>Expression Identifier</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int EXPRESSION_IDENTIFIER_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.FactIdentifierImpl <em>Fact Identifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.FactIdentifierImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactIdentifier()
     */
    int FACT_IDENTIFIER = 2;
    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_IDENTIFIER__NAME = 0;
    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_IDENTIFIER__CLASS_NAME = 1;
    /**
     * The number of structural features of the '<em>Fact Identifier</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_IDENTIFIER_FEATURE_COUNT = 2;
    /**
     * The number of operations of the '<em>Fact Identifier</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_IDENTIFIER_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.FactMappingImpl <em>Fact Mapping</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.FactMappingImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMapping()
     */
    int FACT_MAPPING = 3;
    /**
     * The feature id for the '<em><b>Expression Elements</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING__EXPRESSION_ELEMENTS = 0;
    /**
     * The feature id for the '<em><b>Expression Identifier</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING__EXPRESSION_IDENTIFIER = 1;
    /**
     * The feature id for the '<em><b>Fact Identifier</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING__FACT_IDENTIFIER = 2;
    /**
     * The feature id for the '<em><b>Class Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING__CLASS_NAME = 3;
    /**
     * The feature id for the '<em><b>Fact Alias</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING__FACT_ALIAS = 4;
    /**
     * The feature id for the '<em><b>Expression Alias</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING__EXPRESSION_ALIAS = 5;
    /**
     * The feature id for the '<em><b>Generic Types</b></em>' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING__GENERIC_TYPES = 6;
    /**
     * The number of structural features of the '<em>Fact Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING_FEATURE_COUNT = 7;
    /**
     * The number of operations of the '<em>Fact Mapping</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.FactMappingValueImpl <em>Fact Mapping Value</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.FactMappingValueImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMappingValue()
     */
    int FACT_MAPPING_VALUE = 4;
    /**
     * The feature id for the '<em><b>Fact Identifier</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING_VALUE__FACT_IDENTIFIER = 0;
    /**
     * The feature id for the '<em><b>Expression Identifier</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER = 1;
    /**
     * The feature id for the '<em><b>Raw Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING_VALUE__RAW_VALUE = 2;
    /**
     * The number of structural features of the '<em>Fact Mapping Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING_VALUE_FEATURE_COUNT = 3;
    /**
     * The number of operations of the '<em>Fact Mapping Value</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int FACT_MAPPING_VALUE_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.ImportImpl <em>Import</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.ImportImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getImport()
     */
    int IMPORT = 5;
    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT__TYPE = 0;
    /**
     * The number of structural features of the '<em>Import</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_FEATURE_COUNT = 1;
    /**
     * The number of operations of the '<em>Import</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORT_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.ImportsImpl <em>Imports</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.ImportsImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getImports()
     */
    int IMPORTS = 6;
    /**
     * The feature id for the '<em><b>Imports</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORTS__IMPORTS = 0;
    /**
     * The number of structural features of the '<em>Imports</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORTS_FEATURE_COUNT = 1;
    /**
     * The number of operations of the '<em>Imports</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int IMPORTS_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.ScenarioImpl <em>Scenario</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.ScenarioImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getScenario()
     */
    int SCENARIO = 7;
    /**
     * The feature id for the '<em><b>Fact Mapping Values</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO__FACT_MAPPING_VALUES = 0;
    /**
     * The feature id for the '<em><b>Simulation Descriptor</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO__SIMULATION_DESCRIPTOR = 1;
    /**
     * The number of structural features of the '<em>Scenario</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_FEATURE_COUNT = 2;
    /**
     * The number of operations of the '<em>Scenario</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.ScenarioSimulationModelImpl <em>Scenario Simulation Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.ScenarioSimulationModelImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getScenarioSimulationModel()
     */
    int SCENARIO_SIMULATION_MODEL = 8;
    /**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_SIMULATION_MODEL__VERSION = 0;
    /**
     * The feature id for the '<em><b>Simulation</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_SIMULATION_MODEL__SIMULATION = 1;
    /**
     * The feature id for the '<em><b>Imports</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_SIMULATION_MODEL__IMPORTS = 2;
    /**
     * The number of structural features of the '<em>Scenario Simulation Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_SIMULATION_MODEL_FEATURE_COUNT = 3;
    /**
     * The number of operations of the '<em>Scenario Simulation Model</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SCENARIO_SIMULATION_MODEL_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.SimulationImpl <em>Simulation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.SimulationImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getSimulation()
     */
    int SIMULATION = 9;
    /**
     * The feature id for the '<em><b>Simulation Descriptor</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION__SIMULATION_DESCRIPTOR = 0;
    /**
     * The feature id for the '<em><b>Scenarios</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION__SCENARIOS = 1;
    /**
     * The number of structural features of the '<em>Simulation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_FEATURE_COUNT = 2;
    /**
     * The number of operations of the '<em>Simulation</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl <em>Simulation Descriptor</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.impl.SimulationDescriptorImpl
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getSimulationDescriptor()
     */
    int SIMULATION_DESCRIPTOR = 10;
    /**
     * The feature id for the '<em><b>Fact Mappings</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__FACT_MAPPINGS = 0;
    /**
     * The feature id for the '<em><b>Dmo Session</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__DMO_SESSION = 1;
    /**
     * The feature id for the '<em><b>Dmn File Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__DMN_FILE_PATH = 2;
    /**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__TYPE = 3;
    /**
     * The feature id for the '<em><b>File Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__FILE_NAME = 4;
    /**
     * The feature id for the '<em><b>Kie Session</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__KIE_SESSION = 5;
    /**
     * The feature id for the '<em><b>Kie Base</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__KIE_BASE = 6;
    /**
     * The feature id for the '<em><b>Rule Flow Group</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP = 7;
    /**
     * The feature id for the '<em><b>Dmn Namespace</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__DMN_NAMESPACE = 8;
    /**
     * The feature id for the '<em><b>Dmn Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__DMN_NAME = 9;
    /**
     * The feature id for the '<em><b>Skip From Build</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD = 10;
    /**
     * The number of structural features of the '<em>Simulation Descriptor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR_FEATURE_COUNT = 11;
    /**
     * The number of operations of the '<em>Simulation Descriptor</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SIMULATION_DESCRIPTOR_OPERATION_COUNT = 0;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.FactMappingType <em>Fact Mapping Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingType
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMappingType()
     */
    int FACT_MAPPING_TYPE = 11;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.Name <em>Name</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.Name
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getName_()
     */
    int NAME = 12;
    /**
     * The meta object id for the '{@link org.drools.emf.models.scesim.Type <em>Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.Type
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getType()
     */
    int TYPE = 13;
    /**
     * The meta object id for the '<em>Fact Mapping Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingType
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMappingTypeObject()
     */
    int FACT_MAPPING_TYPE_OBJECT = 14;
    /**
     * The meta object id for the '<em>Name Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.Name
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getNameObject()
     */
    int NAME_OBJECT = 15;
    /**
     * The meta object id for the '<em>Type Object</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.drools.emf.models.scesim.Type
     * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getTypeObject()
     */
    int TYPE_OBJECT = 16;
    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    scesimPackage eINSTANCE = org.drools.emf.models.scesim.impl.scesimPackageImpl.init();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.ExpressionElement <em>Expression Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Expression Element</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ExpressionElement
     */
    EClass getExpressionElement();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.ExpressionElement#getStep <em>Step</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Step</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ExpressionElement#getStep()
     * @see #getExpressionElement()
     */
    EAttribute getExpressionElement_Step();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.ExpressionIdentifier <em>Expression Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Expression Identifier</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ExpressionIdentifier
     */
    EClass getExpressionIdentifier();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.ExpressionIdentifier#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ExpressionIdentifier#getName()
     * @see #getExpressionIdentifier()
     */
    EAttribute getExpressionIdentifier_Name();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.ExpressionIdentifier#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ExpressionIdentifier#getType()
     * @see #getExpressionIdentifier()
     */
    EAttribute getExpressionIdentifier_Type();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.FactIdentifier <em>Fact Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Fact Identifier</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactIdentifier
     */
    EClass getFactIdentifier();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.FactIdentifier#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactIdentifier#getName()
     * @see #getFactIdentifier()
     */
    EAttribute getFactIdentifier_Name();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.FactIdentifier#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactIdentifier#getClassName()
     * @see #getFactIdentifier()
     */
    EAttribute getFactIdentifier_ClassName();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.FactMapping <em>Fact Mapping</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Fact Mapping</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping
     */
    EClass getFactMapping();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.FactMapping#getExpressionElements <em>Expression Elements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression Elements</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping#getExpressionElements()
     * @see #getFactMapping()
     */
    EReference getFactMapping_ExpressionElements();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.FactMapping#getExpressionIdentifier <em>Expression Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression Identifier</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping#getExpressionIdentifier()
     * @see #getFactMapping()
     */
    EReference getFactMapping_ExpressionIdentifier();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.FactMapping#getFactIdentifier <em>Fact Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fact Identifier</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping#getFactIdentifier()
     * @see #getFactMapping()
     */
    EReference getFactMapping_FactIdentifier();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.FactMapping#getClassName <em>Class Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Class Name</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping#getClassName()
     * @see #getFactMapping()
     */
    EAttribute getFactMapping_ClassName();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.FactMapping#getFactAlias <em>Fact Alias</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Fact Alias</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping#getFactAlias()
     * @see #getFactMapping()
     */
    EAttribute getFactMapping_FactAlias();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.FactMapping#getExpressionAlias <em>Expression Alias</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Expression Alias</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping#getExpressionAlias()
     * @see #getFactMapping()
     */
    EAttribute getFactMapping_ExpressionAlias();

    /**
     * Returns the meta object for the attribute list '{@link org.drools.emf.models.scesim.FactMapping#getGenericTypes <em>Generic Types</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute list '<em>Generic Types</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMapping#getGenericTypes()
     * @see #getFactMapping()
     */
    EAttribute getFactMapping_GenericTypes();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.FactMappingValue <em>Fact Mapping Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Fact Mapping Value</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingValue
     */
    EClass getFactMappingValue();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.FactMappingValue#getFactIdentifier <em>Fact Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fact Identifier</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingValue#getFactIdentifier()
     * @see #getFactMappingValue()
     */
    EReference getFactMappingValue_FactIdentifier();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.FactMappingValue#getExpressionIdentifier <em>Expression Identifier</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Expression Identifier</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingValue#getExpressionIdentifier()
     * @see #getFactMappingValue()
     */
    EAttribute getFactMappingValue_ExpressionIdentifier();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.FactMappingValue#getRawValue <em>Raw Value</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Raw Value</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingValue#getRawValue()
     * @see #getFactMappingValue()
     */
    EReference getFactMappingValue_RawValue();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.Import <em>Import</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Import</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Import
     */
    EClass getImport();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.Import#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Import#getType()
     * @see #getImport()
     */
    EAttribute getImport_Type();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.Imports <em>Imports</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Imports</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Imports
     */
    EClass getImports();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.Imports#getImports <em>Imports</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Imports</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Imports#getImports()
     * @see #getImports()
     */
    EReference getImports_Imports();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.Scenario <em>Scenario</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Scenario</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Scenario
     */
    EClass getScenario();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.Scenario#getFactMappingValues <em>Fact Mapping Values</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fact Mapping Values</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Scenario#getFactMappingValues()
     * @see #getScenario()
     */
    EReference getScenario_FactMappingValues();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.Scenario#getSimulationDescriptor <em>Simulation Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Simulation Descriptor</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Scenario#getSimulationDescriptor()
     * @see #getScenario()
     */
    EReference getScenario_SimulationDescriptor();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.ScenarioSimulationModel <em>Scenario Simulation Model</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Scenario Simulation Model</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ScenarioSimulationModel
     */
    EClass getScenarioSimulationModel();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ScenarioSimulationModel#getVersion()
     * @see #getScenarioSimulationModel()
     */
    EAttribute getScenarioSimulationModel_Version();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getSimulation <em>Simulation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Simulation</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ScenarioSimulationModel#getSimulation()
     * @see #getScenarioSimulationModel()
     */
    EReference getScenarioSimulationModel_Simulation();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.ScenarioSimulationModel#getImports <em>Imports</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Imports</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.ScenarioSimulationModel#getImports()
     * @see #getScenarioSimulationModel()
     */
    EReference getScenarioSimulationModel_Imports();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.Simulation <em>Simulation</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simulation</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Simulation
     */
    EClass getSimulation();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.Simulation#getSimulationDescriptor <em>Simulation Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Simulation Descriptor</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Simulation#getSimulationDescriptor()
     * @see #getSimulation()
     */
    EReference getSimulation_SimulationDescriptor();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.Simulation#getScenarios <em>Scenarios</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Scenarios</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Simulation#getScenarios()
     * @see #getSimulation()
     */
    EReference getSimulation_Scenarios();

    /**
     * Returns the meta object for class '{@link org.drools.emf.models.scesim.SimulationDescriptor <em>Simulation Descriptor</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Simulation Descriptor</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor
     */
    EClass getSimulationDescriptor();

    /**
     * Returns the meta object for the containment reference '{@link org.drools.emf.models.scesim.SimulationDescriptor#getFactMappings <em>Fact Mappings</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Fact Mappings</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getFactMappings()
     * @see #getSimulationDescriptor()
     */
    EReference getSimulationDescriptor_FactMappings();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmoSession <em>Dmo Session</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dmo Session</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getDmoSession()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_DmoSession();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnFilePath <em>Dmn File Path</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dmn File Path</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getDmnFilePath()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_DmnFilePath();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getType()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_Type();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getFileName <em>File Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>File Name</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getFileName()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_FileName();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getKieSession <em>Kie Session</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kie Session</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getKieSession()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_KieSession();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getKieBase <em>Kie Base</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kie Base</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getKieBase()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_KieBase();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getRuleFlowGroup <em>Rule Flow Group</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Rule Flow Group</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getRuleFlowGroup()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_RuleFlowGroup();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnNamespace <em>Dmn Namespace</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dmn Namespace</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getDmnNamespace()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_DmnNamespace();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#getDmnName <em>Dmn Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Dmn Name</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#getDmnName()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_DmnName();

    /**
     * Returns the meta object for the attribute '{@link org.drools.emf.models.scesim.SimulationDescriptor#isSkipFromBuild <em>Skip From Build</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Skip From Build</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.SimulationDescriptor#isSkipFromBuild()
     * @see #getSimulationDescriptor()
     */
    EAttribute getSimulationDescriptor_SkipFromBuild();

    /**
     * Returns the meta object for enum '{@link org.drools.emf.models.scesim.FactMappingType <em>Fact Mapping Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Fact Mapping Type</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingType
     */
    EEnum getFactMappingType();

    /**
     * Returns the meta object for enum '{@link org.drools.emf.models.scesim.Name <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Name</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Name
     */
    EEnum getName_();

    /**
     * Returns the meta object for enum '{@link org.drools.emf.models.scesim.Type <em>Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>Type</em>'.
     * @generated
     * @see org.drools.emf.models.scesim.Type
     */
    EEnum getType();

    /**
     * Returns the meta object for data type '{@link org.drools.emf.models.scesim.FactMappingType <em>Fact Mapping Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Fact Mapping Type Object</em>'.
     * @model instanceClass="org.drools.emf.models.scesim.FactMappingType"
     * extendedMetaData="name='factMappingType:Object' baseType='factMappingType'"
     * @generated
     * @see org.drools.emf.models.scesim.FactMappingType
     */
    EDataType getFactMappingTypeObject();

    /**
     * Returns the meta object for data type '{@link org.drools.emf.models.scesim.Name <em>Name Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Name Object</em>'.
     * @model instanceClass="org.drools.emf.models.scesim.Name"
     * extendedMetaData="name='name:Object' baseType='name'"
     * @generated
     * @see org.drools.emf.models.scesim.Name
     */
    EDataType getNameObject();

    /**
     * Returns the meta object for data type '{@link org.drools.emf.models.scesim.Type <em>Type Object</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>Type Object</em>'.
     * @model instanceClass="org.drools.emf.models.scesim.Type"
     * extendedMetaData="name='type:Object' baseType='type'"
     * @generated
     * @see org.drools.emf.models.scesim.Type
     */
    EDataType getTypeObject();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    scesimFactory getscesimFactory();

    /**
     * <!-- begin-user-doc -->
     * Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     * @generated
     */
    interface Literals {

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.ExpressionElementImpl <em>Expression Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.ExpressionElementImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getExpressionElement()
         */
        EClass EXPRESSION_ELEMENT = eINSTANCE.getExpressionElement();

        /**
         * The meta object literal for the '<em><b>Step</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXPRESSION_ELEMENT__STEP = eINSTANCE.getExpressionElement_Step();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.ExpressionIdentifierImpl <em>Expression Identifier</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.ExpressionIdentifierImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getExpressionIdentifier()
         */
        EClass EXPRESSION_IDENTIFIER = eINSTANCE.getExpressionIdentifier();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXPRESSION_IDENTIFIER__NAME = eINSTANCE.getExpressionIdentifier_Name();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute EXPRESSION_IDENTIFIER__TYPE = eINSTANCE.getExpressionIdentifier_Type();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.FactIdentifierImpl <em>Fact Identifier</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.FactIdentifierImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactIdentifier()
         */
        EClass FACT_IDENTIFIER = eINSTANCE.getFactIdentifier();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FACT_IDENTIFIER__NAME = eINSTANCE.getFactIdentifier_Name();

        /**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FACT_IDENTIFIER__CLASS_NAME = eINSTANCE.getFactIdentifier_ClassName();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.FactMappingImpl <em>Fact Mapping</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.FactMappingImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMapping()
         */
        EClass FACT_MAPPING = eINSTANCE.getFactMapping();

        /**
         * The meta object literal for the '<em><b>Expression Elements</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FACT_MAPPING__EXPRESSION_ELEMENTS = eINSTANCE.getFactMapping_ExpressionElements();

        /**
         * The meta object literal for the '<em><b>Expression Identifier</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FACT_MAPPING__EXPRESSION_IDENTIFIER = eINSTANCE.getFactMapping_ExpressionIdentifier();

        /**
         * The meta object literal for the '<em><b>Fact Identifier</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FACT_MAPPING__FACT_IDENTIFIER = eINSTANCE.getFactMapping_FactIdentifier();

        /**
         * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FACT_MAPPING__CLASS_NAME = eINSTANCE.getFactMapping_ClassName();

        /**
         * The meta object literal for the '<em><b>Fact Alias</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FACT_MAPPING__FACT_ALIAS = eINSTANCE.getFactMapping_FactAlias();

        /**
         * The meta object literal for the '<em><b>Expression Alias</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FACT_MAPPING__EXPRESSION_ALIAS = eINSTANCE.getFactMapping_ExpressionAlias();

        /**
         * The meta object literal for the '<em><b>Generic Types</b></em>' attribute list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FACT_MAPPING__GENERIC_TYPES = eINSTANCE.getFactMapping_GenericTypes();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.FactMappingValueImpl <em>Fact Mapping Value</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.FactMappingValueImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMappingValue()
         */
        EClass FACT_MAPPING_VALUE = eINSTANCE.getFactMappingValue();

        /**
         * The meta object literal for the '<em><b>Fact Identifier</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FACT_MAPPING_VALUE__FACT_IDENTIFIER = eINSTANCE.getFactMappingValue_FactIdentifier();

        /**
         * The meta object literal for the '<em><b>Expression Identifier</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER = eINSTANCE.getFactMappingValue_ExpressionIdentifier();

        /**
         * The meta object literal for the '<em><b>Raw Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference FACT_MAPPING_VALUE__RAW_VALUE = eINSTANCE.getFactMappingValue_RawValue();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.ImportImpl <em>Import</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.ImportImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getImport()
         */
        EClass IMPORT = eINSTANCE.getImport();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute IMPORT__TYPE = eINSTANCE.getImport_Type();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.ImportsImpl <em>Imports</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.ImportsImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getImports()
         */
        EClass IMPORTS = eINSTANCE.getImports();

        /**
         * The meta object literal for the '<em><b>Imports</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference IMPORTS__IMPORTS = eINSTANCE.getImports_Imports();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.ScenarioImpl <em>Scenario</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.ScenarioImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getScenario()
         */
        EClass SCENARIO = eINSTANCE.getScenario();

        /**
         * The meta object literal for the '<em><b>Fact Mapping Values</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SCENARIO__FACT_MAPPING_VALUES = eINSTANCE.getScenario_FactMappingValues();

        /**
         * The meta object literal for the '<em><b>Simulation Descriptor</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SCENARIO__SIMULATION_DESCRIPTOR = eINSTANCE.getScenario_SimulationDescriptor();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.ScenarioSimulationModelImpl <em>Scenario Simulation Model</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.ScenarioSimulationModelImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getScenarioSimulationModel()
         */
        EClass SCENARIO_SIMULATION_MODEL = eINSTANCE.getScenarioSimulationModel();

        /**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SCENARIO_SIMULATION_MODEL__VERSION = eINSTANCE.getScenarioSimulationModel_Version();

        /**
         * The meta object literal for the '<em><b>Simulation</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SCENARIO_SIMULATION_MODEL__SIMULATION = eINSTANCE.getScenarioSimulationModel_Simulation();

        /**
         * The meta object literal for the '<em><b>Imports</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SCENARIO_SIMULATION_MODEL__IMPORTS = eINSTANCE.getScenarioSimulationModel_Imports();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.SimulationImpl <em>Simulation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.SimulationImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getSimulation()
         */
        EClass SIMULATION = eINSTANCE.getSimulation();

        /**
         * The meta object literal for the '<em><b>Simulation Descriptor</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SIMULATION__SIMULATION_DESCRIPTOR = eINSTANCE.getSimulation_SimulationDescriptor();

        /**
         * The meta object literal for the '<em><b>Scenarios</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SIMULATION__SCENARIOS = eINSTANCE.getSimulation_Scenarios();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.impl.SimulationDescriptorImpl <em>Simulation Descriptor</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.impl.SimulationDescriptorImpl
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getSimulationDescriptor()
         */
        EClass SIMULATION_DESCRIPTOR = eINSTANCE.getSimulationDescriptor();

        /**
         * The meta object literal for the '<em><b>Fact Mappings</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SIMULATION_DESCRIPTOR__FACT_MAPPINGS = eINSTANCE.getSimulationDescriptor_FactMappings();

        /**
         * The meta object literal for the '<em><b>Dmo Session</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__DMO_SESSION = eINSTANCE.getSimulationDescriptor_DmoSession();

        /**
         * The meta object literal for the '<em><b>Dmn File Path</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__DMN_FILE_PATH = eINSTANCE.getSimulationDescriptor_DmnFilePath();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__TYPE = eINSTANCE.getSimulationDescriptor_Type();

        /**
         * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__FILE_NAME = eINSTANCE.getSimulationDescriptor_FileName();

        /**
         * The meta object literal for the '<em><b>Kie Session</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__KIE_SESSION = eINSTANCE.getSimulationDescriptor_KieSession();

        /**
         * The meta object literal for the '<em><b>Kie Base</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__KIE_BASE = eINSTANCE.getSimulationDescriptor_KieBase();

        /**
         * The meta object literal for the '<em><b>Rule Flow Group</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP = eINSTANCE.getSimulationDescriptor_RuleFlowGroup();

        /**
         * The meta object literal for the '<em><b>Dmn Namespace</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__DMN_NAMESPACE = eINSTANCE.getSimulationDescriptor_DmnNamespace();

        /**
         * The meta object literal for the '<em><b>Dmn Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__DMN_NAME = eINSTANCE.getSimulationDescriptor_DmnName();

        /**
         * The meta object literal for the '<em><b>Skip From Build</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD = eINSTANCE.getSimulationDescriptor_SkipFromBuild();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.FactMappingType <em>Fact Mapping Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.FactMappingType
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMappingType()
         */
        EEnum FACT_MAPPING_TYPE = eINSTANCE.getFactMappingType();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.Name <em>Name</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.Name
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getName_()
         */
        EEnum NAME = eINSTANCE.getName_();

        /**
         * The meta object literal for the '{@link org.drools.emf.models.scesim.Type <em>Type</em>}' enum.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.Type
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getType()
         */
        EEnum TYPE = eINSTANCE.getType();

        /**
         * The meta object literal for the '<em>Fact Mapping Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.FactMappingType
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getFactMappingTypeObject()
         */
        EDataType FACT_MAPPING_TYPE_OBJECT = eINSTANCE.getFactMappingTypeObject();

        /**
         * The meta object literal for the '<em>Name Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.Name
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getNameObject()
         */
        EDataType NAME_OBJECT = eINSTANCE.getNameObject();

        /**
         * The meta object literal for the '<em>Type Object</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         * @see org.drools.emf.models.scesim.Type
         * @see org.drools.emf.models.scesim.impl.scesimPackageImpl#getTypeObject()
         */
        EDataType TYPE_OBJECT = eINSTANCE.getTypeObject();
    }
} //scesimPackage
