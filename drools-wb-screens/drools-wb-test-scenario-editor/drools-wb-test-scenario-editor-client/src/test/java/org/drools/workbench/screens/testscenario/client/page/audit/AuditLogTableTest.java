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

package org.drools.workbench.screens.testscenario.client.page.audit;

import java.util.HashSet;
import java.util.Set;

import com.google.gwtmockito.GwtMockitoTestRunner;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLTableElement;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class AuditLogTableTest {

    @Mock
    private HTMLElement itemElement;

    @Mock
    private AuditLogTableItem item;

    @Mock
    private ManagedInstance<AuditLogTableItem> items;

    @Mock
    private HTMLTableElement itemsContainer;

    private AuditLogTable table;

    @Before
    public void setUp() throws Exception {
        table = spy(new AuditLogTable(itemsContainer, items));

        when(items.get()).thenReturn(item);
        when(item.getElement()).thenReturn(itemElement);
    }

    @Test
    public void testRedrawFiredRules() throws Exception {
        final Set<String> log = new HashSet<String>() {{
            add("Rule 1 fired");
            add("Rule 2 fired");
        }};

        table.redrawFiredRules(log);
        verify(itemsContainer, times(2)).appendChild(itemElement);
    }
}
