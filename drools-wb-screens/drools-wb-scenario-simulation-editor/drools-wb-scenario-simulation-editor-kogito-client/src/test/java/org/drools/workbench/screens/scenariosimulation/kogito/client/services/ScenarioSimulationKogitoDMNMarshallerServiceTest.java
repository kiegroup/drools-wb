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
package org.drools.workbench.screens.scenariosimulation.kogito.client.services;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.callbacks.DMN12UnmarshallCallback;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.DMN12;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDefinitions;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.client.callbacks.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(GwtMockitoTestRunner.class)
public class ScenarioSimulationKogitoDMNMarshallerServiceTest {

    @InjectMocks @Spy
    private ScenarioSimulationKogitoDMNMarshallerService dmnMarshallerServiceSpy;
    @Mock
    private Callback<JSITDefinitions> jsitDefinitionsCallbackMock;
    @Mock
    private DMN12 dmn12Mock;
    @Mock
    private ErrorCallback<String> errorCallbackMock;
    @Mock
    private JSITDefinitions jsitDefinitionsMock;
    @Mock
    private ScenarioSimulationKogitoResourceContentService resourceContentServiceMock;
    @Captor
    private ArgumentCaptor<RemoteCallback<String>> remoteCallbackArgumentCaptor;
    @Captor
    private ArgumentCaptor<DMN12UnmarshallCallback> dmn12UnmarshallCallbackArgumentCaptor;

    @Before
    public void setup() {
        doReturn(jsitDefinitionsMock).when(dmnMarshallerServiceSpy).uncheckedCast(dmn12Mock);
    }

    @Test
    public void getDMNContentNoImports() {
        Path path = PathFactory.newPath("file.dmn", "src/file.dmn");
        dmnMarshallerServiceSpy.getDMNContent(path, jsitDefinitionsCallbackMock, errorCallbackMock);
        verify(resourceContentServiceMock, times(1)).getFileContent(eq(path), remoteCallbackArgumentCaptor.capture(), eq(errorCallbackMock));
        remoteCallbackArgumentCaptor.getValue().callback("<xml>content<xml>");
        verify(dmnMarshallerServiceSpy, times(1)).getDMN12UnmarshallCallback(eq(path), eq(jsitDefinitionsCallbackMock), eq(errorCallbackMock));
        verify(dmnMarshallerServiceSpy, times(1)).unmarshallDMN(eq("<xml>content<xml>"), dmn12UnmarshallCallbackArgumentCaptor.capture());
        dmn12UnmarshallCallbackArgumentCaptor.getValue().callEvent(dmn12Mock);
        verify(jsitDefinitionsCallbackMock, times(1)).callback(jsitDefinitionsMock);
        verify(dmnMarshallerServiceSpy, never()).getDMNImportContentRemoteCallback(any(), any(), any(), anyInt());
    }

}
