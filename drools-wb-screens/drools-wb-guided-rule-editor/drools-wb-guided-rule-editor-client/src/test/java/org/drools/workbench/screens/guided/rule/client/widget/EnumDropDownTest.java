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

package org.drools.workbench.screens.guided.rule.client.widget;

import java.util.stream.IntStream;

import com.google.gwtmockito.GwtMockito;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.gwtbootstrap3.client.ui.ListBox;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.soup.project.datamodel.oracle.DropDownData;
import org.mockito.Mock;
import org.uberfire.backend.vfs.Path;
import org.uberfire.ext.widgets.common.client.common.DropDownValueChanged;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(GwtMockitoTestRunner.class)
public class EnumDropDownTest {

    // not used in our tests
    private String currentValue = "anything";

    @Mock
    private DropDownValueChanged valueChanged;

    @Mock
    private DropDownData dropData;

    @Mock
    private Path resource;

    @Mock
    private ListBox listBox;

    private EnumDropDown testedWidget;

    @Before
    public void setUp() throws Exception {
        GwtMockito.useProviderForType(ListBox.class, (aClass -> listBox));
    }

    private void mockValues(final String... values) {
        doReturn(values.length).when(listBox).getItemCount();
        IntStream.range(0, values.length).forEach(i -> doReturn(values[i]).when(listBox).getValue(i));

        doReturn(values).when(dropData).getFixedList();
    }

    private void mockSelectedIndexes(final int... selectedIndexes) {
        doReturn(selectedIndexes.length > 1).when(listBox).isMultipleSelect();
        if (selectedIndexes.length == 1) {
            doReturn(selectedIndexes[0]).when(listBox).getSelectedIndex();
        } else {
            IntStream.range(0, selectedIndexes.length)
                    .forEach(i -> doReturn(true).when(listBox).isItemSelected(i));
        }

        testedWidget = new EnumDropDown(currentValue,
                                        valueChanged,
                                        dropData,
                                        selectedIndexes.length == 1, // multi select
                                        resource);
    }

    @Test
    public void testEncodeSingleValue() throws Exception {
        mockValues("a", "b");
        mockSelectedIndexes(0);
        assertThat(testedWidget.encodeSelectedItems()).isEqualTo("a");
    }

    @Test
    public void testEncodeMultipleValues() throws Exception {
        mockValues("a", "b");
        mockSelectedIndexes(0, 1);
        assertThat(testedWidget.encodeSelectedItems()).isEqualTo("( \"a\",\"b\" )");
    }

    @Test
    public void testEncodeSingleQuotedValue() throws Exception {
        mockValues("a", "\"b, c\"");
        mockSelectedIndexes(1);
        assertThat(testedWidget.encodeSelectedItems()).isEqualTo("\"b, c\"");
    }

    @Test
    public void testEncodeMultipleValuesIncludeComplex() throws Exception {
        mockValues("a", "\"b, c\"");
        mockSelectedIndexes(0, 1);
        assertThat(testedWidget.encodeSelectedItems()).isEqualTo("( \"a\",\"\"b, c\"\" )");
    }
}
