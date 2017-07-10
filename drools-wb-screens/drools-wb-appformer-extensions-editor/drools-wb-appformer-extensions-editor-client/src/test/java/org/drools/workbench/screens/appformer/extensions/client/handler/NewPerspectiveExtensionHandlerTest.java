/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.drools.workbench.screens.appformer.extensions.client.handler;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.appformer.extensions.client.type.PerspectiveExtensionResourceType;
import org.drools.workbench.screens.appformer.extensions.service.PerspectiveExtensionService;
import org.guvnor.common.services.project.model.Package;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.handlers.NewResourcePresenter;
import org.mockito.Mock;
import org.uberfire.backend.vfs.Path;
import org.uberfire.ext.widgets.common.client.common.BusyIndicatorView;
import org.uberfire.mocks.CallerMock;
import org.uberfire.workbench.type.ResourceTypeDefinition;

import static org.mockito.Mockito.*;

@RunWith(GwtMockitoTestRunner.class)
public class NewPerspectiveExtensionHandlerTest {

    public static final String FILE_NAME = "fileName";
    @Mock
    PerspectiveExtensionService perspectiveExtensionService;
    CallerMock<PerspectiveExtensionService> perspectiveExtensionServiceCaller;

    @Mock
    PerspectiveExtensionResourceType perspectiveExtensionResourceTypeDefinition;

    @Mock
    private BusyIndicatorView busyIndicatorView;

    @Mock
    Package pck;

    @Mock
    Path pckPath;

    @Mock
    NewResourcePresenter newResourcePresenter;

    NewPerspectiveExtensionHandler newPerspectiveExtensionHandler;

    @Before
    public void setup() {
        perspectiveExtensionServiceCaller = new CallerMock<>(perspectiveExtensionService);
        newPerspectiveExtensionHandler = new NewPerspectiveExtensionHandler(perspectiveExtensionServiceCaller,
                                                                            perspectiveExtensionResourceTypeDefinition,
                                                                            busyIndicatorView) {
            @Override
            protected RemoteCallback<Path> getSuccessCallback(NewResourcePresenter presenter) {
                return p -> {
                };
            }

            @Override
            protected String buildFileName(String baseFileName,
                                           ResourceTypeDefinition resourceType) {
                return FILE_NAME;
            }
        };
        when(pck.getPackageMainResourcesPath()).thenReturn(pckPath);
    }

    @Test
    public void createTest() {
        newPerspectiveExtensionHandler.create(pck,
                                              "baseFile",
                                              newResourcePresenter);
        verify(perspectiveExtensionService).create(eq(pckPath),
                                                   eq(FILE_NAME),
                                                   eq(""),
                                                   eq(""));
    }
}