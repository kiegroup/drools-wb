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

import java.util.ArrayList;

import org.drools.workbench.models.datamodel.oracle.DataType;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.keys.Key;
import org.drools.workbench.screens.guided.dtable.client.widget.analysis.index.matchers.FieldMatchers;
import org.uberfire.commons.validation.PortablePreconditions;

public class FieldAction
        extends Action {

    private static final String FIELD                 = "field";
    private static final String FACT_TYPE__FIELD_NAME = "factType.fieldName";

    private final Field              field;
    private final DataType.DataTypes dataType;

    public FieldAction( final Field field,
                        final Column column,
                        final DataType.DataTypes dataType,
                        final Comparable value ) {
        super( column,
               ActionSuperType.FIELD_ACTION,
               value );

        this.field = PortablePreconditions.checkNotNull( "field", field );
        this.dataType = PortablePreconditions.checkNotNull( "dataType", dataType );
    }

    public static FieldMatchers field() {
        return new FieldMatchers( FIELD );
    }

    public Field getField() {
        return field;
    }

    public DataType.DataTypes getDataType() {
        return dataType;
    }

    public static String[] keyIDs() {
        final ArrayList<String> keys = new ArrayList<>();
        for ( final String key : Action.keyIDs() ) {
            keys.add( key );
        }

        keys.add( FIELD );
        keys.add( FACT_TYPE__FIELD_NAME );

        return keys.toArray( new String[keys.size()] );
    }

    @Override
    public Key[] keys() {
        final ArrayList<Key> keys = new ArrayList<>();

        for ( final Key key : super.keys() ) {
            keys.add( key );
        }

        keys.add( new Key( FIELD,
                           field ) );
        keys.add( new Key( FACT_TYPE__FIELD_NAME,
                           field.getFactType() + "." + field.getName() ) );

        return keys.toArray( new Key[keys.size()] );
    }
}
