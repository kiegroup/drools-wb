/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtree.client.handlers;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.guided.dtree.client.type.GuidedDTreeResourceType;
import org.drools.workbench.screens.guided.dtree.service.GuidedDecisionTreeEditorService;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.security.shared.api.identity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.ext.widgets.common.client.common.BusyIndicatorView;
import org.uberfire.mocks.CallerMock;
import org.uberfire.rpc.SessionInfo;
import org.uberfire.security.authz.AuthorizationManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class NewGuidedDecisionTreeHandlerTest {

    @Mock
    private GuidedDecisionTreeEditorService service;
    private Caller<GuidedDecisionTreeEditorService> serviceCaller;

    @Mock
    private BusyIndicatorView indicatorView;

    @Mock
    private AuthorizationManager authorizationManager;

    @Mock
    private SessionInfo sessionInfo;

    @Mock
    private User user;

    private GuidedDTreeResourceType resourceType = new GuidedDTreeResourceType();

    private NewGuidedDecisionTreeHandler handler;

    @Before
    public void setup() {
        this.serviceCaller = new CallerMock<>(service);
        this.handler = new NewGuidedDecisionTreeHandler(serviceCaller,
                                                        resourceType,
                                                        indicatorView,
                                                        authorizationManager,
                                                        sessionInfo);
        when(sessionInfo.getIdentity()).thenReturn(user);
    }

    @Test
    public void checkCanCreateWhenFeatureDisabled() {
        when(authorizationManager.authorize(eq(NewGuidedDecisionTreeHandler.PERMISSION),
                                            eq(user))).thenReturn(false);
        assertFalse(handler.canCreate());
    }

    @Test
    public void checkCanCreateWhenFeatureEnabled() {
        when(authorizationManager.authorize(eq(NewGuidedDecisionTreeHandler.PERMISSION),
                                            eq(user))).thenReturn(true);
        assertTrue(handler.canCreate());
    }
}
