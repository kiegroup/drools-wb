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
import java.util.List;
import java.util.Set;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class AuditLogTableTest {

    private AuditLogTable table;

    @Captor
    private ArgumentCaptor<List<String>> stringsCaptor;

    @Before
    public void setUp() throws Exception {
        table = spy(new AuditLogTable());
    }

    @Test
    public void testRedrawFiredRules() throws Exception {
        final Set<String> log = new HashSet<String>() {{
            add("Rule 1 fired");
            add("Rule 2 fired");
        }};

        table.redrawFiredRules(log);
        verify(table).setRowData(stringsCaptor.capture());

        Assertions.assertThat(stringsCaptor.getValue().size()).isEqualTo(log.size());
        Assertions.assertThat(stringsCaptor.getValue()).containsAll(log);
    }
}
