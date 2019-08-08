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
package org.drools.workbench.screens.scenariosimulation.backend.server.downloadreport;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.drools.scenariosimulation.api.model.ScenarioWithIndex;

public class ScenarioCsvDownloadReport {

    /**
     * @param auditMessagesMap Map of the messages to print in the CSV report: inside the <b>value</b> Map, <b>key</b> is the message, <b>value</b> is the severity level
     * @return
     * @throws IOException
     */
    public String getReport(Map<ScenarioWithIndex, Map<String, String>> auditMessagesMap) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        CSVPrinter printer = new CSVPrinter(stringBuilder, CSVFormat.DEFAULT);
        generateHeader(printer);
        for (Map.Entry<ScenarioWithIndex, Map<String, String>> auditEntry : auditMessagesMap.entrySet()) {
            String scenario = auditEntry.getKey().getScenario().getDescription();
            List<Object> values = auditEntry.getValue().entrySet().stream().map(entry -> Arrays.asList(scenario, entry.getKey(), entry.getValue())).collect(Collectors.toList());
            printer.printRecord(values.toArray());
        }
        printer.close();
        return stringBuilder.toString();
    }

    protected void generateHeader(CSVPrinter printer) throws IOException {
        printer.printRecord(Arrays.asList("Scenario", "Result", "Level"));
    }
}
