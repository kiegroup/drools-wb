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

public class XLSBuilderInOperatorTest
        extends TestBase {

    @BeforeClass
    public static void setUp() throws Exception {
        final String xml = loadResource(XLSBuilderAttributesNegateTest.class.getResourceAsStream("ColumnWithIn.gdst"));

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

        assertEquals("RuleTable ColumnWithIn", cell(4, 1).getStringCellValue());
    }

    @Test
    public void columnTypes() {

        assertEquals("CONDITION", cell(5, 1).getStringCellValue());
        assertEquals("CONDITION", cell(5, 2).getStringCellValue());
        assertNullCell(5, 3);
    }

    @Test
    public void patterns() {

        assertEquals("a : Applicant", cell(6, 1).getStringCellValue().trim());
        assertEquals("a : Applicant", cell(6, 2).getStringCellValue().trim());
        assertNullCell(6, 3);
    }

    @Test
    public void constraints() {

        assertEquals("name in $param", cell(7, 1).getStringCellValue().trim());
        assertEquals("name not in $param", cell(7, 2).getStringCellValue().trim());
        assertNullCell(7, 3);
    }

    @Test
    public void columnTitles() {

        assertEquals("In", cell(8, 1).getStringCellValue());
        assertEquals("Not In", cell(8, 2).getStringCellValue());
        assertNullCell(8, 3);
    }

    @Test
    public void content() {

        assertNullCell(9, 1);
        assertNullCell(9, 2);
        assertNullCell(9, 3);
        assertEquals("('Toni', 'Toni\\'s wife', 'Peter')", cell(10, 1).getStringCellValue());
        assertEquals("('Peter\\'s friend, or dog', 'Cat')", cell(10, 2).getStringCellValue()); // TODO ( and ) ?
        assertNullCell(10, 3);
    }
}