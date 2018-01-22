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

public class FieldConditionInspectorKey
        extends ConditionInspectorKey {

    private final Pattern52 pattern;
    private final String factField;

    public FieldConditionInspectorKey(final Pattern52 pattern,
                                      final String factField) {
        this.pattern = pattern;
        this.factField = factField;
    }

    public Pattern52 getPattern() {
        return pattern;
    }

    public String getFactField() {
        return factField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FieldConditionInspectorKey)) {
            return false;
        }

        FieldConditionInspectorKey that = (FieldConditionInspectorKey) o;

        if (!pattern.equals(that.pattern)) {
            return false;
        }
        return factField != null ? factField.equals(that.factField) : that.factField == null;
    }

    @Override
    public int hashCode() {
        int result = pattern.hashCode();
        result = ~~result;
        result = 31 * result + (factField != null ? factField.hashCode() : 0);
        result = ~~result;
        return result;
    }
}
