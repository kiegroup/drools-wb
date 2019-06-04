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

import org.drools.emf.models.scesim.ExpressionElement;
import org.drools.emf.models.scesim.ExpressionIdentifier;
import org.drools.emf.models.scesim.FactIdentifier;
import org.drools.emf.models.scesim.FactMapping;
import org.drools.emf.models.scesim.FactMappingType;
import org.drools.emf.models.scesim.FactMappingValue;
import org.drools.emf.models.scesim.Import;
import org.drools.emf.models.scesim.Imports;
import org.drools.emf.models.scesim.Name;
import org.drools.emf.models.scesim.Scenario;
import org.drools.emf.models.scesim.ScenarioSimulationModel;
import org.drools.emf.models.scesim.Simulation;
import org.drools.emf.models.scesim.SimulationDescriptor;
import org.drools.emf.models.scesim.Type;
import org.drools.emf.models.scesim.scesimFactory;
import org.drools.emf.models.scesim.scesimPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class scesimPackageImpl extends EPackageImpl implements scesimPackage {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionElementEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass expressionIdentifierEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass factIdentifierEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass factMappingEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass factMappingValueEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass importEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass importsEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scenarioEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass scenarioSimulationModelEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass simulationEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass simulationDescriptorEClass = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum factMappingTypeEEnum = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum nameEEnum = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EEnum typeEEnum = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType factMappingTypeObjectEDataType = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType nameObjectEDataType = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType typeObjectEDataType = null;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.drools.emf.models.scesim.scesimPackage#eNS_URI
     * @see #init()
     */
    private scesimPackageImpl() {
        super(eNS_URI, scesimFactory.eINSTANCE);
    }

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>This method is used to initialize {@link scesimPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     */
    public static scesimPackage init() {
        if (isInited) {
            return (scesimPackage) EPackage.Registry.INSTANCE.getEPackage(scesimPackage.eNS_URI);
        }

        // Obtain or create and register package
        Object registeredscesimPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        scesimPackageImpl thescesimPackage = registeredscesimPackage instanceof scesimPackageImpl ? (scesimPackageImpl) registeredscesimPackage : new scesimPackageImpl();

        isInited = true;

        // Initialize simple dependencies
        XMLTypePackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thescesimPackage.createPackageContents();

        // Initialize created meta-data
        thescesimPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thescesimPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(scesimPackage.eNS_URI, thescesimPackage);
        return thescesimPackage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpressionElement() {
        return expressionElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExpressionElement_Step() {
        return (EAttribute) expressionElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getExpressionIdentifier() {
        return expressionIdentifierEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExpressionIdentifier_Name() {
        return (EAttribute) expressionIdentifierEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getExpressionIdentifier_Type() {
        return (EAttribute) expressionIdentifierEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFactIdentifier() {
        return factIdentifierEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFactIdentifier_Name() {
        return (EAttribute) factIdentifierEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFactIdentifier_ClassName() {
        return (EAttribute) factIdentifierEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFactMapping() {
        return factMappingEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFactMapping_ExpressionElements() {
        return (EReference) factMappingEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFactMapping_ExpressionIdentifier() {
        return (EReference) factMappingEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFactMapping_FactIdentifier() {
        return (EReference) factMappingEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFactMapping_ClassName() {
        return (EAttribute) factMappingEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFactMapping_FactAlias() {
        return (EAttribute) factMappingEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFactMapping_ExpressionAlias() {
        return (EAttribute) factMappingEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFactMapping_GenericTypes() {
        return (EAttribute) factMappingEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getFactMappingValue() {
        return factMappingValueEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFactMappingValue_FactIdentifier() {
        return (EReference) factMappingValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getFactMappingValue_ExpressionIdentifier() {
        return (EAttribute) factMappingValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getFactMappingValue_RawValue() {
        return (EReference) factMappingValueEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getImport() {
        return importEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getImport_Type() {
        return (EAttribute) importEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getImports() {
        return importsEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getImports_Imports() {
        return (EReference) importsEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScenario() {
        return scenarioEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScenario_FactMappingValues() {
        return (EReference) scenarioEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScenario_SimulationDescriptor() {
        return (EReference) scenarioEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getScenarioSimulationModel() {
        return scenarioSimulationModelEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getScenarioSimulationModel_Version() {
        return (EAttribute) scenarioSimulationModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScenarioSimulationModel_Simulation() {
        return (EReference) scenarioSimulationModelEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getScenarioSimulationModel_Imports() {
        return (EReference) scenarioSimulationModelEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSimulation() {
        return simulationEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSimulation_SimulationDescriptor() {
        return (EReference) simulationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSimulation_Scenarios() {
        return (EReference) simulationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getSimulationDescriptor() {
        return simulationDescriptorEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getSimulationDescriptor_FactMappings() {
        return (EReference) simulationDescriptorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_DmoSession() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_DmnFilePath() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_Type() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_FileName() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_KieSession() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_KieBase() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_RuleFlowGroup() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_DmnNamespace() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_DmnName() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getSimulationDescriptor_SkipFromBuild() {
        return (EAttribute) simulationDescriptorEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getFactMappingType() {
        return factMappingTypeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getName_() {
        return nameEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EEnum getType() {
        return typeEEnum;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getFactMappingTypeObject() {
        return factMappingTypeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getNameObject() {
        return nameObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getTypeObject() {
        return typeObjectEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public scesimFactory getscesimFactory() {
        return (scesimFactory) getEFactoryInstance();
    }

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        expressionElementEClass = createEClass(EXPRESSION_ELEMENT);
        createEAttribute(expressionElementEClass, EXPRESSION_ELEMENT__STEP);

        expressionIdentifierEClass = createEClass(EXPRESSION_IDENTIFIER);
        createEAttribute(expressionIdentifierEClass, EXPRESSION_IDENTIFIER__NAME);
        createEAttribute(expressionIdentifierEClass, EXPRESSION_IDENTIFIER__TYPE);

        factIdentifierEClass = createEClass(FACT_IDENTIFIER);
        createEAttribute(factIdentifierEClass, FACT_IDENTIFIER__NAME);
        createEAttribute(factIdentifierEClass, FACT_IDENTIFIER__CLASS_NAME);

        factMappingEClass = createEClass(FACT_MAPPING);
        createEReference(factMappingEClass, FACT_MAPPING__EXPRESSION_ELEMENTS);
        createEReference(factMappingEClass, FACT_MAPPING__EXPRESSION_IDENTIFIER);
        createEReference(factMappingEClass, FACT_MAPPING__FACT_IDENTIFIER);
        createEAttribute(factMappingEClass, FACT_MAPPING__CLASS_NAME);
        createEAttribute(factMappingEClass, FACT_MAPPING__FACT_ALIAS);
        createEAttribute(factMappingEClass, FACT_MAPPING__EXPRESSION_ALIAS);
        createEAttribute(factMappingEClass, FACT_MAPPING__GENERIC_TYPES);

        factMappingValueEClass = createEClass(FACT_MAPPING_VALUE);
        createEReference(factMappingValueEClass, FACT_MAPPING_VALUE__FACT_IDENTIFIER);
        createEAttribute(factMappingValueEClass, FACT_MAPPING_VALUE__EXPRESSION_IDENTIFIER);
        createEReference(factMappingValueEClass, FACT_MAPPING_VALUE__RAW_VALUE);

        importEClass = createEClass(IMPORT);
        createEAttribute(importEClass, IMPORT__TYPE);

        importsEClass = createEClass(IMPORTS);
        createEReference(importsEClass, IMPORTS__IMPORTS);

        scenarioEClass = createEClass(SCENARIO);
        createEReference(scenarioEClass, SCENARIO__FACT_MAPPING_VALUES);
        createEReference(scenarioEClass, SCENARIO__SIMULATION_DESCRIPTOR);

        scenarioSimulationModelEClass = createEClass(SCENARIO_SIMULATION_MODEL);
        createEAttribute(scenarioSimulationModelEClass, SCENARIO_SIMULATION_MODEL__VERSION);
        createEReference(scenarioSimulationModelEClass, SCENARIO_SIMULATION_MODEL__SIMULATION);
        createEReference(scenarioSimulationModelEClass, SCENARIO_SIMULATION_MODEL__IMPORTS);

        simulationEClass = createEClass(SIMULATION);
        createEReference(simulationEClass, SIMULATION__SIMULATION_DESCRIPTOR);
        createEReference(simulationEClass, SIMULATION__SCENARIOS);

        simulationDescriptorEClass = createEClass(SIMULATION_DESCRIPTOR);
        createEReference(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__FACT_MAPPINGS);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__DMO_SESSION);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__DMN_FILE_PATH);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__TYPE);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__FILE_NAME);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__KIE_SESSION);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__KIE_BASE);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__RULE_FLOW_GROUP);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__DMN_NAMESPACE);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__DMN_NAME);
        createEAttribute(simulationDescriptorEClass, SIMULATION_DESCRIPTOR__SKIP_FROM_BUILD);

        // Create enums
        factMappingTypeEEnum = createEEnum(FACT_MAPPING_TYPE);
        nameEEnum = createEEnum(NAME);
        typeEEnum = createEEnum(TYPE);

        // Create data types
        factMappingTypeObjectEDataType = createEDataType(FACT_MAPPING_TYPE_OBJECT);
        nameObjectEDataType = createEDataType(NAME_OBJECT);
        typeObjectEDataType = createEDataType(TYPE_OBJECT);
    }

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes

        // Initialize classes, features, and operations; add parameters
        initEClass(expressionElementEClass, ExpressionElement.class, "ExpressionElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExpressionElement_Step(), theXMLTypePackage.getString(), "step", null, 1, 1, ExpressionElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(expressionIdentifierEClass, ExpressionIdentifier.class, "ExpressionIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getExpressionIdentifier_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, ExpressionIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getExpressionIdentifier_Type(), this.getFactMappingType(), "type", null, 1, 1, ExpressionIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(factIdentifierEClass, FactIdentifier.class, "FactIdentifier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFactIdentifier_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, FactIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFactIdentifier_ClassName(), theXMLTypePackage.getString(), "className", null, 1, 1, FactIdentifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(factMappingEClass, FactMapping.class, "FactMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFactMapping_ExpressionElements(), this.getExpressionElement(), null, "expressionElements", null, 0, 1, FactMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFactMapping_ExpressionIdentifier(), this.getExpressionIdentifier(), null, "expressionIdentifier", null, 1, 1, FactMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFactMapping_FactIdentifier(), this.getFactIdentifier(), null, "factIdentifier", null, 1, 1, FactMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFactMapping_ClassName(), theXMLTypePackage.getString(), "className", null, 1, 1, FactMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFactMapping_FactAlias(), theXMLTypePackage.getString(), "factAlias", null, 1, 1, FactMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFactMapping_ExpressionAlias(), theXMLTypePackage.getString(), "expressionAlias", null, 1, 1, FactMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFactMapping_GenericTypes(), theXMLTypePackage.getString(), "genericTypes", null, 0, -1, FactMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(factMappingValueEClass, FactMappingValue.class, "FactMappingValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getFactMappingValue_FactIdentifier(), this.getFactIdentifier(), null, "factIdentifier", null, 1, 1, FactMappingValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getFactMappingValue_ExpressionIdentifier(), theXMLTypePackage.getString(), "expressionIdentifier", null, 1, 1, FactMappingValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFactMappingValue_RawValue(), ecorePackage.getEObject(), null, "rawValue", null, 1, 1, FactMappingValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getImport_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(importsEClass, Imports.class, "Imports", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getImports_Imports(), this.getImport(), null, "imports", null, 0, 1, Imports.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scenarioEClass, Scenario.class, "Scenario", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getScenario_FactMappingValues(), this.getFactMappingValue(), null, "factMappingValues", null, 0, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScenario_SimulationDescriptor(), this.getSimulationDescriptor(), null, "simulationDescriptor", null, 1, 1, Scenario.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(scenarioSimulationModelEClass, ScenarioSimulationModel.class, "ScenarioSimulationModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getScenarioSimulationModel_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, ScenarioSimulationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScenarioSimulationModel_Simulation(), this.getSimulation(), null, "simulation", null, 1, 1, ScenarioSimulationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getScenarioSimulationModel_Imports(), this.getImports(), null, "imports", null, 1, 1, ScenarioSimulationModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(simulationEClass, Simulation.class, "Simulation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSimulation_SimulationDescriptor(), this.getSimulationDescriptor(), null, "simulationDescriptor", null, 1, 1, Simulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSimulation_Scenarios(), this.getScenario(), null, "scenarios", null, 0, 1, Simulation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(simulationDescriptorEClass, SimulationDescriptor.class, "SimulationDescriptor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getSimulationDescriptor_FactMappings(), this.getFactMapping(), null, "factMappings", null, 0, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_DmoSession(), theXMLTypePackage.getString(), "dmoSession", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_DmnFilePath(), theXMLTypePackage.getString(), "dmnFilePath", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_Type(), this.getType(), "type", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_FileName(), theXMLTypePackage.getString(), "fileName", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_KieSession(), theXMLTypePackage.getString(), "kieSession", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_KieBase(), theXMLTypePackage.getString(), "kieBase", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_RuleFlowGroup(), theXMLTypePackage.getString(), "ruleFlowGroup", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_DmnNamespace(), theXMLTypePackage.getString(), "dmnNamespace", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_DmnName(), theXMLTypePackage.getString(), "dmnName", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSimulationDescriptor_SkipFromBuild(), theXMLTypePackage.getBoolean(), "skipFromBuild", null, 1, 1, SimulationDescriptor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(factMappingTypeEEnum, FactMappingType.class, "FactMappingType");
        addEEnumLiteral(factMappingTypeEEnum, FactMappingType.GIVEN);
        addEEnumLiteral(factMappingTypeEEnum, FactMappingType.EXPECT);
        addEEnumLiteral(factMappingTypeEEnum, FactMappingType.OTHER);

        initEEnum(nameEEnum, Name.class, "Name");
        addEEnumLiteral(nameEEnum, Name.DESCRIPTION);
        addEEnumLiteral(nameEEnum, Name.EXPECTED);
        addEEnumLiteral(nameEEnum, Name.GIVEN);
        addEEnumLiteral(nameEEnum, Name.INDEX);
        addEEnumLiteral(nameEEnum, Name.OTHER);

        initEEnum(typeEEnum, Type.class, "Type");
        addEEnumLiteral(typeEEnum, Type.RULE);
        addEEnumLiteral(typeEEnum, Type.DMN);

        // Initialize data types
        initEDataType(factMappingTypeObjectEDataType, FactMappingType.class, "FactMappingTypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(nameObjectEDataType, Name.class, "NameObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);
        initEDataType(typeObjectEDataType, Type.class, "TypeObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);

        // Create annotations
        // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
        createExtendedMetaDataAnnotations();
    }

    /**
     * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected void createExtendedMetaDataAnnotations() {
        String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
        addAnnotation
                (this,
                 source,
                 new String[]{
                         "qualified", "false"
                 });
        addAnnotation
                (expressionElementEClass,
                 source,
                 new String[]{
                         "name", "expressionElement",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getExpressionElement_Step(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "step",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (expressionIdentifierEClass,
                 source,
                 new String[]{
                         "name", "expressionIdentifier",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getExpressionIdentifier_Name(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "name",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getExpressionIdentifier_Type(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "type",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (factIdentifierEClass,
                 source,
                 new String[]{
                         "name", "factIdentifier",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getFactIdentifier_Name(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "name",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactIdentifier_ClassName(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "className",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (factMappingEClass,
                 source,
                 new String[]{
                         "name", "factMapping",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getFactMapping_ExpressionElements(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "expressionElements",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMapping_ExpressionIdentifier(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "expressionIdentifier",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMapping_FactIdentifier(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "factIdentifier",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMapping_ClassName(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "className",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMapping_FactAlias(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "factAlias",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMapping_ExpressionAlias(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "expressionAlias",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMapping_GenericTypes(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "genericTypes",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (factMappingTypeEEnum,
                 source,
                 new String[]{
                         "name", "factMappingType"
                 });
        addAnnotation
                (factMappingTypeObjectEDataType,
                 source,
                 new String[]{
                         "name", "factMappingType:Object",
                         "baseType", "factMappingType"
                 });
        addAnnotation
                (factMappingValueEClass,
                 source,
                 new String[]{
                         "name", "factMappingValue",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getFactMappingValue_FactIdentifier(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "factIdentifier",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMappingValue_ExpressionIdentifier(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "expressionIdentifier",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getFactMappingValue_RawValue(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "rawValue",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (importEClass,
                 source,
                 new String[]{
                         "name", "import",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getImport_Type(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "type",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (importsEClass,
                 source,
                 new String[]{
                         "name", "imports",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getImports_Imports(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "imports",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (nameEEnum,
                 source,
                 new String[]{
                         "name", "name"
                 });
        addAnnotation
                (nameObjectEDataType,
                 source,
                 new String[]{
                         "name", "name:Object",
                         "baseType", "name"
                 });
        addAnnotation
                (scenarioEClass,
                 source,
                 new String[]{
                         "name", "scenario",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getScenario_FactMappingValues(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "factMappingValues",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getScenario_SimulationDescriptor(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "simulationDescriptor",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (scenarioSimulationModelEClass,
                 source,
                 new String[]{
                         "name", "scenarioSimulationModel",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getScenarioSimulationModel_Version(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "version",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getScenarioSimulationModel_Simulation(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "simulation",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getScenarioSimulationModel_Imports(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "imports",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (simulationEClass,
                 source,
                 new String[]{
                         "name", "simulation",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getSimulation_SimulationDescriptor(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "simulationDescriptor",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulation_Scenarios(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "scenarios",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (simulationDescriptorEClass,
                 source,
                 new String[]{
                         "name", "simulationDescriptor",
                         "kind", "elementOnly"
                 });
        addAnnotation
                (getSimulationDescriptor_FactMappings(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "factMappings",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_DmoSession(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "dmoSession",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_DmnFilePath(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "dmnFilePath",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_Type(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "type",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_FileName(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "fileName",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_KieSession(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "kieSession",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_KieBase(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "kieBase",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_RuleFlowGroup(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "ruleFlowGroup",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_DmnNamespace(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "dmnNamespace",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_DmnName(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "dmnName",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (getSimulationDescriptor_SkipFromBuild(),
                 source,
                 new String[]{
                         "kind", "element",
                         "name", "skipFromBuild",
                         "namespace", "##targetNamespace"
                 });
        addAnnotation
                (typeEEnum,
                 source,
                 new String[]{
                         "name", "type"
                 });
        addAnnotation
                (typeObjectEDataType,
                 source,
                 new String[]{
                         "name", "type:Object",
                         "baseType", "type"
                 });
    }
} //scesimPackageImpl
