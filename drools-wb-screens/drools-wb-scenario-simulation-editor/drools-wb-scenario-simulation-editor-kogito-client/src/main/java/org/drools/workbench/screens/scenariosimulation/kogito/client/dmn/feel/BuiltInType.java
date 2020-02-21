/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.kogito.client.dmn.feel;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum BuiltInType implements SimpleType {

    UNKNOWN(SimpleType.ANY, "unknown", "any"), // updated FEEL lattice of types from DMN v1.2
    NUMBER(SimpleType.NUMBER),
    STRING(SimpleType.STRING),
    DATE(SimpleType.DATE),
    TIME(SimpleType.TIME),
    DATE_TIME(SimpleType.DATE_AND_TIME, "dateTime"),
    DURATION("duration", SimpleType.DAYS_AND_TIME_DURATION, SimpleType.YEARS_AND_MONTHS_DURATION, "dayTimeDuration", "yearMonthDuration"),
    BOOLEAN(SimpleType.BOOLEAN),
    RANGE("range"),
    FUNCTION(SimpleType.FUNCTION), // TODO be parametrized as FUNCTION<type>
    LIST(SimpleType.LIST),
    CONTEXT(SimpleType.CONTEXT),
    UNARY_TEST("unary test");

    private final String[] names;
    private final Collection<BuiltInTypeSymbol> symbols;

    BuiltInType(String... names) {
        this.names = names;
        this.symbols = Arrays.asList(names).stream().map(n -> new BuiltInTypeSymbol(n, this)).collect(Collectors.toList());
    }

    public String getName() {
        return names[0];
    }

    public String[] getNames() {
        return names;
    }

    public Object fromString(String value) {
        // TODO {gcardosi} correct implementation
        return value;
    }

    public String toString(Object value) {
        // TODO {gcardosi} correct implementation
        return value.toString();
    }

    public Collection<BuiltInTypeSymbol> getSymbols() {
        return symbols;
    }

    @Override
    public String toString() {
        return "Type{ " +
               names[0] +
               " }";
    }

    public static Type determineTypeFromName( String name ) {
        if( name == null ) {
            return UNKNOWN;
        }
        for( BuiltInType t : BuiltInType.values() ) {
            for( String n : t.getNames() ) {
                if( n.equals( name ) ) {
                    return t;
                }
            }
        }
        return UNKNOWN;
    }

    public static Type determineTypeFromInstance( Object o ) {
        if( o == null ) {
            return UNKNOWN;
        } else if( o instanceof Number ) {
            return NUMBER;
        } else if( o instanceof String ) {
            return STRING;
        } else if( o instanceof Date ) {
            return DATE;
        } else if( o instanceof Boolean ) {
            return BOOLEAN;
        } else if( o instanceof List ) {
            return LIST;
        } else if( o instanceof Map ) {
            return CONTEXT;
        }
        return UNKNOWN;
    }

    public static boolean isInstanceOf( Object o, Type t ) {
        if ( o == null ) {
            return false; // See FEEL specifications Table 49.
        }
        if ( t == UNKNOWN ) {
            return true;
        }
        return determineTypeFromInstance( o ) == t;
    }

    public static boolean isInstanceOf( Object o, String name ) {
        return determineTypeFromInstance( o ) == determineTypeFromName( name );
    }

    @Override
    public boolean isInstanceOf(Object o) {
        return isInstanceOf(o, this);
    }

    @Override
    public boolean isAssignableValue(Object value) {
        if ( value == null ) {
            return true; // a null-value can be assigned to any type.
        }
        return isInstanceOf(value, this);
    }

    @Override
    public boolean conformsTo(Type t) {
        return t == UNKNOWN || this == t;
    }

}
