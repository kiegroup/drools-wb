/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtable.backend.server.conversion;

import org.apache.poi.ss.usermodel.Workbook;
import org.drools.workbench.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.drools.workbench.screens.guided.dtable.backend.server.util.TestUtil.loadResource;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FunctionConversionsTest
        extends TestBase {

    @BeforeClass
    public static void setUp() throws Exception {
        final String xml = loadResource(XLSBuilderAttributesNegateTest.class.getResourceAsStream("FunctionConversions.gdst"));

        final GuidedDecisionTable52 dtable = GuidedDTXMLPersistence.getInstance().unmarshal(xml);

        final XLSBuilder.BuildResult buildResult = new XLSBuilder(dtable, makeDMO()).build();
        final Workbook workbook = buildResult.getWorkbook();

        assertEquals(1, workbook.getNumberOfSheets());
        sheet = workbook.iterator().next();
    }

    @Test
    public void headers() {

        assertEquals("RuleSet", cell(1, 1).getStringCellValue());
        assertEquals("mortgages.mortgages", cell(1, 2).getStringCellValue());

        assertEquals("Import", cell(2, 1).getStringCellValue());
        assertEquals("", sheet.getRow(2).getCell(2).getStringCellValue());

        assertEquals("RuleTable FunctionConversions", cell(4, 1).getStringCellValue());
    }

    @Test
    public void columnTypes() {

        assertEquals("CONDITION", cell(5, 1).getStringCellValue());
        assertEquals("ACTION", cell(5, 2).getStringCellValue());
        assertEquals("ACTION", cell(5, 3).getStringCellValue());
        assertEquals("ACTION", cell(5, 4).getStringCellValue());
        assertEquals("ACTION", cell(5, 5).getStringCellValue());
        assertEquals("ACTION", cell(5, 6).getStringCellValue());
        assertNullCell(5, 7);
    }

    @Test
    public void patterns() {

        assertEquals("application : LoanApplication", cell(6, 1).getStringCellValue().trim());
        assertNullCell(6, 2);
        assertNullCell(6, 3);
        assertNullCell(6, 4);
        assertNullCell(6, 5);
        assertNullCell(6, 6);
        assertNullCell(6, 7);
    }

    @Test
    public void constraints() {

        assertEquals("amount <= $param", cell(7, 1).getStringCellValue().trim());
        assertEquals("application.addAmount( $param );", cell(7, 2).getStringCellValue().trim());
        assertEquals("application.addExplanation( $param );", cell(7, 3).getStringCellValue().trim());
        assertEquals("application.deadline( new java.text.SimpleDateFormat( \"d-MMM-yyyy\").parse($param) );", cell(7, 4).getStringCellValue().trim());
        assertEquals("application.negateApproved( $param );", cell(7, 5).getStringCellValue().trim());
        assertEquals("application.numFunction( new java.math.BigDecimal(\"$param\") );", cell(7, 6).getStringCellValue().trim());
        assertNullCell(7, 7);
    }

    @Test
    public void columnTitles() {

        assertEquals("amount max", cell(8, 1).getStringCellValue());
        assertEquals("call functions", cell(8, 2).getStringCellValue());
        assertEquals("call functions", cell(8, 3).getStringCellValue());
        assertEquals("call functions", cell(8, 4).getStringCellValue());
        assertEquals("call functions", cell(8, 5).getStringCellValue());
        assertEquals("call functions", cell(8, 6).getStringCellValue());
        assertNullCell(8, 7);
    }

    @Test
    public void content() {

        assertEquals("200000", cell(9, 1).getStringCellValue());
        assertEquals("1", cell(9, 2).getStringCellValue());
        assertEquals("\"exp\"", cell(9, 3).getStringCellValue());
        assertTrue(cell(9, 4).getStringCellValue().endsWith("-Mar-2021\""));
        assertEquals("true", cell(9, 5).getStringCellValue());
        assertEquals("1", cell(9, 6).getStringCellValue());
        assertNullCell(9, 7);
    }
}