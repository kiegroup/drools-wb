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

public class XLSMethodCallNumbersTest
        extends TestBase {

    @BeforeClass
    public static void setUp() throws Exception {
        final String xml = loadResource(XLSBuilderAttributesNegateTest.class.getResourceAsStream("NumbersInMethods.gdst"));

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

        assertEquals("RuleTable NumbersInMethods", cell(4, 1).getStringCellValue());
    }

    @Test
    public void columnTypes() {

        assertEquals("ACTION", cell(5, 1).getStringCellValue());
        assertEquals("ACTION", cell(5, 2).getStringCellValue());
        assertEquals("ACTION", cell(5, 3).getStringCellValue());
        assertNullCell(5, 4);
    }

    @Test
    public void patterns() {

        assertNullCell(6, 1);
        assertNullCell(6, 2);
        assertNullCell(6, 3);
    }

    @Test
    public void constraints() {

        assertEquals("NumberWrapper a = new NumberWrapper(); insert( a );", cell(7, 1).getStringCellValue().trim());
        assertEquals("a.assignTestId( new java.math.BigDecimal(\"$param\") );", cell(7, 2).getStringCellValue().trim());
        assertEquals("a.assignBigInteger( new java.math.BigInteger(\"$param\") );", cell(7, 3).getStringCellValue().trim());
        assertNullCell(7, 4);
    }

    @Test
    public void columnTitles() {

        assertEquals("", cell(8, 1).getStringCellValue());
        assertEquals("Big Decimal", cell(8, 2).getStringCellValue());
        assertEquals("Big Decimal", cell(8, 3).getStringCellValue());
        assertNullCell(8, 4);
    }

    @Test
    public void content() {

        assertEquals("X", cell(9, 1).getStringCellValue());
        assertEquals("11", cell(9, 2).getStringCellValue());
        assertEquals("22", cell(9, 3).getStringCellValue());
        assertNullCell(9, 4);
    }
}