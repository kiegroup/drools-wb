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
package org.drools.workbench.screens.guided.dtable.client.widget.analysis.condition;

import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.CompositeColumn;

public class BRLConditionInspector
        extends ConditionInspector {

    public BRLConditionInspector( final CompositeColumn<? extends BaseColumn> column ) {
        super( new BRLConditionInspectorKey( column ) );
    }

    @Override
    public boolean conflicts( final Object other ) {
        return false;
    }

    @Override
    public boolean overlaps( final Object other ) {
        if ( other instanceof BRLConditionInspector ) {
            return key.equals( (( BRLConditionInspector ) other).key );
        } else {
            return false;
        }
    }

    @Override
    public boolean isRedundant( final Object other ) {
        if ( other instanceof BRLConditionInspector ) {
            return key.equals( (( BRLConditionInspector ) other).key );
        } else {
            return false;
        }
    }

    @Override
    public boolean subsumes( final Object other ) {
        if ( other instanceof BRLConditionInspector ) {
            return key.equals( (( BRLConditionInspector ) other).key );
        } else {
            return false;
        }
    }

    @Override
    public boolean hasValue() {
        return true;
    }
}
