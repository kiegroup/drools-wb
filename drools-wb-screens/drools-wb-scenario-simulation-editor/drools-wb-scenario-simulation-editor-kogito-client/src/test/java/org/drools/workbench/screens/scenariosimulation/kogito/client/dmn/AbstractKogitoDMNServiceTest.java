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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDRGElement;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDefinitions;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITItemDefinition;
import org.mockito.Mock;
import org.uberfire.backend.vfs.Path;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class AbstractKogitoDMNServiceTest {

    public static final String NAMESPACE = "namespace";

    @Mock
    private JSITDefinitions jsiITDefinitionsMock;

    private AbstractKogitoDMNService abstractKogitoDMNServiceSpy;
    private List<JSITItemDefinition> jstiItemDefinitions;
    private List<JSITDRGElement> jsitdrgElements;

    @Before
    public void setup() {
        abstractKogitoDMNServiceSpy = spy(new AbstractKogitoDMNService() {
            @Override
            public void getDMNContent(Path path, RemoteCallback<String> remoteCallback, ErrorCallback<Object> errorCallback) {
                //Do nothing
            }
        });

        jstiItemDefinitions = new ArrayList<>();
        jsitdrgElements = new ArrayList<>();
        when(jsiITDefinitionsMock.getNamespace()).thenReturn(NAMESPACE);
        when(jsiITDefinitionsMock.getItemDefinition()).thenReturn(jstiItemDefinitions);
        when(jsiITDefinitionsMock.getDrgElement()).thenReturn(jsitdrgElements);
    }

    @Test
    /* Not Possibile to test other getFactModelTuple() cases, because of static JSInterops method which can't be mocked */
    /* Eg. instanceOf() method inside JSITDRGElement class and subclasses */
    public void retrieveFactModelTupleDmnListEmptyElements() {
        FactModelTuple factModelTuple = abstractKogitoDMNServiceSpy.getFactModelTuple(jsiITDefinitionsMock);
        assertTrue(factModelTuple.getVisibleFacts().isEmpty());
        assertTrue(factModelTuple.getHiddenFacts().isEmpty());
    }

    @Test
    public void getDMNTypesMapEmptyItemDefinitions() {
        Map<String, DMNTypeFactory.DMNType> dmnTypesMap = abstractKogitoDMNServiceSpy.getDMNTypesMap(jstiItemDefinitions, NAMESPACE);
        // It must contains all elements defined in BuiltInType ENUM without ANY
        assertTrue(dmnTypesMap.size() == 14);
    }

    @Test
    public void getDMNTypesMapWithItemDefinitions() {
        JSITItemDefinition jstiItemDefinitionMock = mock(JSITItemDefinition.class);
        when(jstiItemDefinitionMock.getName()).thenReturn("name");
        jstiItemDefinitions.add(jstiItemDefinitionMock);
        Map<String, DMNTypeFactory.DMNType> dmnTypesMap = abstractKogitoDMNServiceSpy.getDMNTypesMap(jstiItemDefinitions, NAMESPACE);
        // It must contains all elements defined in BuiltInType ENUM + one defined jstiItemDefinitionMock item
        assertTrue(dmnTypesMap.size() == 15);
    }
}


