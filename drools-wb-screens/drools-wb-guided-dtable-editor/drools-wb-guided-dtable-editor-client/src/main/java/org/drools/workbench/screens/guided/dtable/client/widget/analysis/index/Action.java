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
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.matchers.UUIDMatchers;
import org.uberfire.commons.validation.PortablePreconditions;

public abstract class Action
        implements HasKeys {

    protected static final String VALUE       = "value";
    protected static final String SUPER_TYPE  = "superType";
    protected static final String COLUMN_UUID = "columnUUID";

    protected final UUIDKey uuidKey = new UUIDKey( this );
    protected final Column               column;
    private final   ActionSuperType      superType;
    protected       UpdatableKey<Action> valueKey;

    public Action( final Column column,
                   final ActionSuperType superType,
                   final Comparable value ) {
        this.column = PortablePreconditions.checkNotNull( "column", column );
        this.superType = PortablePreconditions.checkNotNull( "superType", superType );
        this.valueKey = new UpdatableKey<>( Action.VALUE,
                                            value );
    }

    public UUIDKey getUuidKey() {
        return uuidKey;
    }

    public static Matchers value() {
        return new Matchers( VALUE );
    }

    public static Matchers superType() {
        return new Matchers( SUPER_TYPE );
    }

    public static Matchers columnUUID() {
        return new Matchers( COLUMN_UUID );
    }

    public Comparable getValue() {
        return valueKey.getValue().getComparable();
    }

    public static Matchers uuid() {
        return new UUIDMatchers();
    }

    public static String[] keyIDs() {
        return new String[]{
                UUIDKey.UNIQUE_UUID,
                COLUMN_UUID,
                SUPER_TYPE,
                VALUE
        };
    }

    @Override
    public Key[] keys() {
        return new Key[]{
                uuidKey,
                new Key( SUPER_TYPE,
                         superType ),
                new Key( COLUMN_UUID,
                         column.getUuidKey() ),
                valueKey
        };
    }

    public void setValue( final Comparable value ) {
        if ( valueKey.getValue().equals( value ) ) {
            return;
        } else {
            final UpdatableKey<Action> oldKey = valueKey;

            final UpdatableKey<Action> newKey = new UpdatableKey<>( Action.VALUE,
                                                                    value );
            valueKey = newKey;

            oldKey.update( newKey,
                            this );
        }
    }
}
