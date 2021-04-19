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
package org.drools.workbench.screens.guided.dtable.backend.server.conversion.util;

import java.util.Objects;

/**
 * From origin column index to target column index.
 * If the column for example does not have values or is limited we only use a tick mark in the cell.
 */
public class FromTo {

    private final int fromColumnIndex;
    private final int toColumnIndex;
    private final boolean useATickAsAValue;

    public static FromTo makePlaceHolder(final int from,
                                         final int to) {
        return new FromTo(from,
                          to,
                          true);
    }

    public static FromTo makeFromTo(final int from,
                                    final int to) {
        return new FromTo(from,
                          to,
                          false);
    }

    private FromTo(final int fromColumnIndex,
                   final int toColumnIndex,
                   final boolean useATickAsAValue) {
        this.fromColumnIndex = fromColumnIndex;
        this.toColumnIndex = toColumnIndex;
        this.useATickAsAValue = useATickAsAValue;
    }

    public int getFromColumnIndex() {
        return fromColumnIndex;
    }

    public int getToColumnIndex() {
        return toColumnIndex;
    }

    public boolean shouldUseATickAsValue() {
        return useATickAsAValue;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FromTo fromTo = (FromTo) o;
        return fromColumnIndex == fromTo.fromColumnIndex && toColumnIndex == fromTo.toColumnIndex && useATickAsAValue == fromTo.useATickAsAValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromColumnIndex, toColumnIndex, useATickAsAValue);
    }
}
