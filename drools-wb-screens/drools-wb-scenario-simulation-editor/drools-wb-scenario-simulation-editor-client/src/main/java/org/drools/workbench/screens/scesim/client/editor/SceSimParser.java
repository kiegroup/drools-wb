/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.scesim.client.editor;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods to parse SceSims.
 */
public class SceSimParser {

    private SceSimParser() {
    }

    /**
     * Parse scesim definitions from a String
     * @param content
     * @return A List of Enum definitions
     */
    public static List<SceSimRow> fromString(final String content) {
        final List<SceSimRow> enums = new ArrayList<SceSimRow>();

        if (content == null || content.isEmpty()) {
            return enums;
        } else {
            final String[] lines = content.split("\\n");
            for (int i = 0; i < lines.length; i++) {
                final String line = lines[i].trim();
                final SceSimRow er = parseEnum(line);
                if (er != null) {
                    enums.add(er);
                }
            }
        }
        return enums;
    }

    private static SceSimRow parseEnum(final String line) {
        if (line.equals("") || line.startsWith("#") || line.startsWith("//")) {
            return null;
        }

        final int colonIndex = line.indexOf(":");
        if (colonIndex < 0) {
            return new SceSimRow(line);
        }
        String factField = line.substring(0,
                                          colonIndex);
        factField = factField.trim();
        final int dotIndex = factField.indexOf(".");
        if (dotIndex < 0) {
            return new SceSimRow(line);
        }

        String factName = factField.substring(0,
                                              dotIndex);
        factName = factName.trim();
        String fieldName = factField.substring(dotIndex + 1,
                                               factField.length());
        fieldName = fieldName.trim();

        if (!factName.startsWith("'")) {
            return new SceSimRow(line);
        }
        if (!fieldName.endsWith("'")) {
            return new SceSimRow(line);
        }
        factName = factName.substring(1).trim();
        fieldName = fieldName.substring(0, fieldName.length() - 1).trim();

        final String context = line.substring(colonIndex + 1).trim();

        final SceSimRow er = new SceSimRow(factName,
                                           fieldName,
                                           context);
        if (!er.isValid()) {
            return new SceSimRow(line);
        }
        return er;
    }

    /**
     * Parse enum definitions to String
     * @param content
     * @return A String representing the Enum definitions
     */
    public static String toString(final List<SceSimRow> content) {
        if (content == null || content.isEmpty()) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        for (final SceSimRow sceSimRow : content) {
            sb.append(sceSimRow.toString()).append("\n");
        }
        return sb.toString();
    }
}