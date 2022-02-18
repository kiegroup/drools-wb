/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.dtablexls.backend.server;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.uberfire.io.IOService;
import org.uberfire.java.nio.file.Paths;
import org.uberfire.java.nio.file.StandardOpenOption;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DecisionTableXLSServiceImplSourceTest {

    @Mock
    private IOService ioService;

    @InjectMocks
    private DecisionTableXLSServiceImpl service;

    private final org.uberfire.java.nio.file.Path xlsPath = Paths.get("file://project/src/main/resources/CanDrinkAndDrive.xls");

    private final org.uberfire.java.nio.file.Path xlsPropertiesPath = Paths.get("file://project/src/main/resources/CanDrinkAndDrive.xls.properties");
    private String xlsPropertiesContent = "";

    @Before
    public void setUp() throws Exception {

        doAnswer(invocationOnMock -> {
            final org.uberfire.java.nio.file.Path argument = invocationOnMock.getArgument(0);

            switch (argument.toFile().getName()) {
                case "CanDrinkAndDrive.xls":
                    return this.getClass().getResourceAsStream("CanDrinkAndDrive.xls");
                case "CanDrinkAndDrive.xls.properties":
                    return new ByteArrayInputStream(xlsPropertiesContent.getBytes());
            }
            return null;
        }).when(ioService).newInputStream(any(),
                                          eq(StandardOpenOption.READ));
    }

    @Test
    public void testNoPropertiesFile() throws IOException {

        doReturn(false).when(ioService).exists(xlsPropertiesPath);

        final String source = service.getSource(org.uberfire.backend.server.util.Paths.convert(xlsPath));

        assertTrue(source.contains("can drink"));
        assertFalse(source.contains("can drive"));
    }

    @Test
    public void testPropertiesFileSheet() throws IOException {

        doReturn(true).when(ioService).exists(xlsPropertiesPath);
        xlsPropertiesContent = "sheets=Sheet2";

        String source = service.getSource(org.uberfire.backend.server.util.Paths.convert(xlsPath));

        assertFalse(source.contains("can drink"));
        assertTrue(source.contains("can drive"));
    }

    @Test
    public void testPropertiesFileBothSheets() throws IOException {

        doReturn(true).when(ioService).exists(xlsPropertiesPath);
        xlsPropertiesContent = "sheets=Sheet1,Sheet2";

        String source = service.getSource(org.uberfire.backend.server.util.Paths.convert(xlsPath));

        assertTrue(source.contains("can drink"));
        assertTrue(source.contains("can drive"));
    }
}
