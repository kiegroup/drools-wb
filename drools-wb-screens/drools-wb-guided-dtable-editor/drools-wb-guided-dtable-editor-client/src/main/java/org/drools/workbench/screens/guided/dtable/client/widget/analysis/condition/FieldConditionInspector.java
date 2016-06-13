/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.analysis.condition;

import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;

public abstract class FieldConditionInspector
        extends ConditionInspector {

    protected FieldConditionInspector( final Pattern52 pattern,
                                       final String factField ) {
        super( new FieldConditionInspectorKey( pattern,
                                          factField ) );
    }

    public FieldConditionInspectorKey getKey() {
        return ( FieldConditionInspectorKey ) key;
    }


    public Pattern52 getPattern() {
        return getKey().getPattern();
    }

    public String getFactField() {
        return getKey().getFactField();
    }

    protected boolean nullSafeEquals( Object a,
                                      Object b ) {
        if ( a == null ) {
            return b == null;
        } else {
            return a.equals( b );
        }
    }

    public abstract String toHumanReadableString();

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( this == obj ) {
            return true;
        }
        if ( !obj.getClass().equals( this.getClass() ) ) {
            return false;
        }
        if ( this.toHumanReadableString().equals( (( FieldConditionInspector ) obj).toHumanReadableString() ) ) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return ~~toHumanReadableString().hashCode();
    }
}
