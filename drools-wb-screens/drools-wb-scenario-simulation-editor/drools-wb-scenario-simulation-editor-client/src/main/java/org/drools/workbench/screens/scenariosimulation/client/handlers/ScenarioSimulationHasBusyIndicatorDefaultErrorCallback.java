/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.client.handlers;

import com.google.gwt.user.client.ui.PopupPanel;
import org.jboss.errai.bus.client.api.messaging.Message;
import org.uberfire.ext.widgets.common.client.callbacks.HasBusyIndicatorDefaultErrorCallback;
import org.uberfire.ext.widgets.common.client.common.HasBusyIndicator;

public class ScenarioSimulationHasBusyIndicatorDefaultErrorCallback extends HasBusyIndicatorDefaultErrorCallback {

    protected PopupPanel loadingPopup;

    public ScenarioSimulationHasBusyIndicatorDefaultErrorCallback(HasBusyIndicator view, PopupPanel loadingPopup) {
        super(view);
        this.loadingPopup = loadingPopup;
    }


    @Override
    public boolean error(final Message message,
                         final Throwable throwable) {
        loadingPopup.hide(true);
        return errorLocal(message,
                           throwable);
    }

    public void hideBusyIndicator() {
        loadingPopup.hide(true);
        super.hideBusyIndicator();
    }

    // Indirection add for testing purpose
    protected boolean errorLocal(final Message message,
                                 final Throwable throwable) {
        return super.error(message,
                           throwable);
    }
}
