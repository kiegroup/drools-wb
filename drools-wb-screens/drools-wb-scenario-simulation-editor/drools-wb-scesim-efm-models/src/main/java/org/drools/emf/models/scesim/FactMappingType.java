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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Fact Mapping Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @model extendedMetaData="name='factMappingType'"
 * @generated
 * @see org.drools.emf.models.scesim.scesimPackage#getFactMappingType()
 */
public enum FactMappingType implements Enumerator {
    /**
     * The '<em><b>GIVEN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #GIVEN_VALUE
     */
    GIVEN(0, "GIVEN", "GIVEN"),

    /**
     * The '<em><b>EXPECT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #EXPECT_VALUE
     */
    EXPECT(1, "EXPECT", "EXPECT"),

    /**
     * The '<em><b>OTHER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     * @see #OTHER_VALUE
     */
    OTHER(2, "OTHER", "OTHER");

    /**
     * The '<em><b>GIVEN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     * @ordered
     * @see #GIVEN
     */
    public static final int GIVEN_VALUE = 0;

    /**
     * The '<em><b>EXPECT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     * @ordered
     * @see #EXPECT
     */
    public static final int EXPECT_VALUE = 1;

    /**
     * The '<em><b>OTHER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @model
     * @generated
     * @ordered
     * @see #OTHER
     */
    public static final int OTHER_VALUE = 2;

    /**
     * An array of all the '<em><b>Fact Mapping Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final FactMappingType[] VALUES_ARRAY =
            new FactMappingType[]{
                    GIVEN,
                    EXPECT,
                    OTHER,
            };

    /**
     * A public read-only list of all the '<em><b>Fact Mapping Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List<FactMappingType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final int value;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String name;
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    FactMappingType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * Returns the '<em><b>Fact Mapping Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FactMappingType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FactMappingType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Fact Mapping Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FactMappingType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            FactMappingType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Fact Mapping Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FactMappingType get(int value) {
        switch (value) {
            case GIVEN_VALUE:
                return GIVEN;
            case EXPECT_VALUE:
                return EXPECT;
            case OTHER_VALUE:
                return OTHER;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }
} //FactMappingType
