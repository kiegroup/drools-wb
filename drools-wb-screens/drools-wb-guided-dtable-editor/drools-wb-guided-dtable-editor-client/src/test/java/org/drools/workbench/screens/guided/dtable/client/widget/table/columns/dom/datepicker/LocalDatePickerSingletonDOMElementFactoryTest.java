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

package org.drools.workbench.screens.guided.dtable.client.widget.table.columns.dom.datepicker;

import java.util.Date;
import java.util.HashMap;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.drools.workbench.screens.guided.dtable.client.widget.table.GuidedDecisionTableView;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeDateEvent;
import org.gwtbootstrap3.extras.datepicker.client.ui.base.events.ChangeDateHandler;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.uberfire.ext.widgets.common.client.common.DatePicker;
import org.uberfire.ext.wires.core.grids.client.widget.layer.GridLayer;
import org.uberfire.ext.wires.core.grids.client.widget.layer.impl.GridLienzoPanel;

import static org.junit.Assert.assertEquals;
import static org.kie.workbench.common.services.shared.preferences.ApplicationPreferences.DATE_FORMAT;
import static org.kie.workbench.common.services.shared.preferences.ApplicationPreferences.KIE_TIMEZONE_OFFSET;
import static org.kie.workbench.common.widgets.client.util.TimeZoneUtils.FORMATTER;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class LocalDatePickerSingletonDOMElementFactoryTest {

    private static final String TEST_DATE_FORMAT = "MM-dd-yyyy HH:mm:ss";

    @Mock
    private GridLienzoPanel gridPanel;

    @Mock
    private GridLayer gridLayer;

    @Mock
    private GuidedDecisionTableView gridWidget;

    @Mock
    private DatePicker datePicker;

    @Mock
    private LocalDatePickerDOMElement datePickerDOMElement;

    @Captor
    private ArgumentCaptor<ChangeDateHandler> dateChangeHandlerCaptor;

    @BeforeClass
    public static void setupStatic() {
        setStandardTimeZone();
        initializeApplicationPreferences();
    }

    private static void initializeApplicationPreferences() {
        ApplicationPreferences.setUp(new HashMap<String, String>() {{
            put(KIE_TIMEZONE_OFFSET, "10800000");
            put(DATE_FORMAT, TEST_DATE_FORMAT);
        }});
    }

    private static void setStandardTimeZone() {
        System.setProperty("user.timezone", "Europe/Vilnius");
    }

    @Test
    public void testGetValue() {

        final LocalDatePickerSingletonDOMElementFactory factory = spy(makeFactory());
        final String dateStringValue = "05-01-2018 00:00:00";
        final Date date = FORMATTER.parse(dateStringValue);

        doReturn(datePicker).when(factory).getWidget();
        when(datePicker.getValue()).thenReturn(date);

        final String actualDate = factory.getValue();

        assertEquals(dateStringValue, actualDate);
    }

    @Test
    public void testConvert() {
        assertEquals("05-01-2020", makeFactory().convert("05-01-2020"));
    }

    @Test
    public void testRegisterHandlers() {
        final LocalDatePickerSingletonDOMElementFactory factory = spy(makeFactory());
        factory.registerHandlers(datePicker, datePickerDOMElement);

        verify(datePicker).addChangeDateHandler(dateChangeHandlerCaptor.capture());
        dateChangeHandlerCaptor.getValue().onChangeDate(mock(ChangeDateEvent.class));

        verify(factory).flush();
        verify(factory).destroyResources();
        verify(gridLayer).batch();
        verify(gridPanel).setFocus(true);
    }

    private LocalDatePickerSingletonDOMElementFactory makeFactory() {
        return new LocalDatePickerSingletonDOMElementFactory(gridPanel, gridLayer, gridWidget);
    }
}
