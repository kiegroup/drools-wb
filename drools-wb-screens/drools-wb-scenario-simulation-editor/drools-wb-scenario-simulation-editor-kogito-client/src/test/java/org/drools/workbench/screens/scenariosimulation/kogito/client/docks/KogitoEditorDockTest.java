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

package org.drools.workbench.screens.scenariosimulation.kogito.client.docks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.widgets.client.docks.AbstractWorkbenchDocksHandler;
import org.kie.workbench.common.widgets.client.docks.WorkbenchDocksHandler;
import org.mockito.Mock;
import org.uberfire.client.workbench.docks.UberfireDock;
import org.uberfire.client.workbench.docks.UberfireDockPosition;
import org.uberfire.client.workbench.docks.UberfireDocks;
import org.uberfire.mvp.PlaceRequest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class KogitoEditorDockTest {

    public static final String AUTHORING_PERSPECTIVE = "authoring";

    @Mock
    private UberfireDocks uberfireDocks;
    @Mock
    private PlaceRequest placeRequest;
    @Mock
    private ManagedInstance<WorkbenchDocksHandler> handlers;

    private KogitoEditorDock kogitoEditorDockSpy;

    private TestWorkbenchDocksHandler handler;

    @Before
    public void setup() {
        handler = createNewWorkbenchDocksHandler();

        List<WorkbenchDocksHandler> list = new ArrayList<>();
        list.add(handler);
        when(handlers.iterator()).thenReturn(list.iterator());

        kogitoEditorDockSpy = spy(new KogitoEditorDock(uberfireDocks, handlers));

        kogitoEditorDockSpy.initialize();

        //verify(handlers).iterator();

        assertFalse(kogitoEditorDockSpy.isSetup());

        kogitoEditorDockSpy.setup(AUTHORING_PERSPECTIVE, placeRequest);

        assertTrue(kogitoEditorDockSpy.isSetup());
    }

    @Test
    public void expandAuthoringDock() {
        final UberfireDock dockToOpen = mock(UberfireDock.class);
        kogitoEditorDockSpy.expandAuthoringDock(dockToOpen);

        verify(uberfireDocks).show(UberfireDockPosition.EAST, AUTHORING_PERSPECTIVE);
        verify(uberfireDocks).open(dockToOpen);
    }


    private TestWorkbenchDocksHandler createNewWorkbenchDocksHandler() {
        List<UberfireDock> docks = new ArrayList<>();

        docks.add(new UberfireDock(UberfireDockPosition.EAST,
                                   "RANDOM",
                                   placeRequest,
                                   AUTHORING_PERSPECTIVE));
        docks.add(new UberfireDock(UberfireDockPosition.EAST,
                                   "RANDOM",
                                   placeRequest,
                                   AUTHORING_PERSPECTIVE));

        return new TestWorkbenchDocksHandler(docks);
    }

    public class TestWorkbenchDocksHandler extends AbstractWorkbenchDocksHandler {

        private List<UberfireDock> docks;

        public TestWorkbenchDocksHandler(List<UberfireDock> docks) {
            this.docks = docks;
        }

        @Override
        public Collection<UberfireDock> provideDocks(String perspectiveIdentifier) {
            return docks;
        }

        public void refresh(boolean shouldRefresh,
                            boolean shouldDisable) {
            refreshDocks(shouldRefresh,
                         shouldDisable);
        }
    }

}
