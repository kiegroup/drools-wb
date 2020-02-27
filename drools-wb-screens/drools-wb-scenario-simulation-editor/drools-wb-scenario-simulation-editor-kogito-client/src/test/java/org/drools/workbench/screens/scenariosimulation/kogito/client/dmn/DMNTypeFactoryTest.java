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
package org.drools.workbench.screens.scenariosimulation.kogito.client.dmn;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITItemDefinition;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class DMNTypeFactoryTest {

    public static final String NAMESPACE = "namespace";
    public static final String NAME = "name";
    public static final String ID = "id";

    @Mock
    private JSITItemDefinition jsitItemDefinitionMock;
    @Mock
    private JSITItemDefinition jsitItemDefinitionNestedMock;

    @Before
    public void setup() {
        when(jsitItemDefinitionMock.getName()).thenReturn(NAME);
        when(jsitItemDefinitionMock.getId()).thenReturn(ID);
        when(jsitItemDefinitionMock.getIsCollection()).thenReturn(false);
    }

    @Test
    public void getDMNTypeNullItems() {
        /*ClientDMNType clientDmnType = DMNTypeFactory.getDMNType(jsitItemDefinitionMock, NAMESPACE, new HashMap<>());
        assertEquals(NAMESPACE, clientDmnType.getNamespace());
        assertEquals(NAME, clientDmnType.getName());
        assertFalse(clientDmnType.isCollection());
        assertNull(clientDmnType.getFeelType());
    }

    @Test
    public void getDMNTypeEmptyItems() {
        when(jsitItemDefinitionMock.getItemComponent()).thenReturn(new ArrayList<>());
        ClientDMNType clientDmnType = DMNTypeFactory.getDMNType(jsitItemDefinitionMock, NAMESPACE, new HashMap<>());
        assertEquals(NAMESPACE, clientDmnType.getNamespace());
        assertEquals(NAME, clientDmnType.getName());
        assertFalse(clientDmnType.isCollection());
        assertFalse(clientDmnType.isComposite());
        assertNull(clientDmnType.getFields());
        assertNull(clientDmnType.getFeelType());
    }

    @Test
    public void getDMNTypeItems() {
        when(jsitItemDefinitionMock.getItemComponent()).thenReturn(Arrays.asList(jsitItemDefinitionNestedMock));
        ClientDMNType clientDmnType = DMNTypeFactory.getDMNType(jsitItemDefinitionMock, NAMESPACE, new HashMap<>());
        assertEquals(NAMESPACE, clientDmnType.getNamespace());
        assertEquals(NAME, clientDmnType.getName());
        assertFalse(clientDmnType.isCollection());
        assertTrue(clientDmnType.isComposite());
        assertNotNull(clientDmnType.getFields());
        assertTrue(clientDmnType.getFields().size() == 1);
        assertNull(clientDmnType.getFeelType());*/
    }
}
