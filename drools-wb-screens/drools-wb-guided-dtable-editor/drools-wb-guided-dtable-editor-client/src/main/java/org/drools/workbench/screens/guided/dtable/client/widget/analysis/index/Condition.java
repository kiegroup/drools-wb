/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.guided.dtable.client.widget.analysis.index;


import org.drools.workbench.screens.guided.dtable.client.widget.analysis.cache.HasKeys;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.Key;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.UUIDKey;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.UpdatableKey;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.matchers.ComparableMatchers;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.matchers.UUIDMatchers;
import org.uberfire.commons.validation.PortablePreconditions;

public abstract class Condition<T extends Comparable>
        implements HasKeys {

    private final static String SUPER_TYPE  = "superType";
    private final static String COLUMN_UUID = "columnUUID";
    private final static String VALUE       = "value";

    protected final UUIDKey uuidKey = new UUIDKey( this );
    protected final Column                  column;
    private         UpdatableKey<Condition> valueKey;
    private         ConditionSuperType      superType;

    public Condition( final Column column,
                      final ConditionSuperType superType,
                      final T value ) {
        this.column = PortablePreconditions.checkNotNull( "column", column );
        this.superType = PortablePreconditions.checkNotNull( "superType", superType );
        this.valueKey = new UpdatableKey<>( "value",
                                            value );
    }

    public UUIDKey getUuidKey() {
        return uuidKey;
    }

    public static ComparableMatchers value() {
        return new ComparableMatchers( VALUE );
    }

    public static Matchers columnUUID() {
        return new Matchers( COLUMN_UUID );
    }

    public static Matchers superType() {
        return new Matchers( SUPER_TYPE );
    }

    public static Matchers uuid() {
        return new UUIDMatchers();
    }

    public T getValue() {
        return ( T ) valueKey.getValue().getComparable();
    }

    public void setValue( final T value ) {
        if ( valueKey.getValue().equals( value ) ) {
            return;
        } else {
            final UpdatableKey<Condition> oldKey = valueKey;

            final UpdatableKey<Condition> newKey = new UpdatableKey<>( VALUE,
                                                                       value );

            valueKey = newKey;

            oldKey.update( newKey,
                           this );

        }

    }

    @Override
    public Key[] keys() {
        return new Key[]{
                uuidKey,
                valueKey,
                new Key( SUPER_TYPE,
                         superType ),
                new Key( COLUMN_UUID,
                         column.getUuidKey() ),
        };
    }

    public static String[] keyIds() {
        return new String[]{
                UUIDKey.UNIQUE_UUID,
                VALUE,
                SUPER_TYPE,
                COLUMN_UUID
        };
    }
}
