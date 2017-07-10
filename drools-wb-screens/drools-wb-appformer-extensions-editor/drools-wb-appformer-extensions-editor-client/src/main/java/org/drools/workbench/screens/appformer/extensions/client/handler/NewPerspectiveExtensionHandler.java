/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.drools.workbench.screens.appformer.extensions.client.handler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import org.drools.workbench.screens.appformer.extensions.client.resources.AppformerExtensionsEditorResources;
import org.drools.workbench.screens.appformer.extensions.client.resources.i18n.AppformerExtensionsEditorConstants;
import org.drools.workbench.screens.appformer.extensions.client.type.PerspectiveExtensionResourceType;
import org.drools.workbench.screens.appformer.extensions.service.PerspectiveExtensionService;
import org.guvnor.common.services.project.model.Package;
import org.jboss.errai.common.client.api.Caller;
import org.kie.workbench.common.widgets.client.handlers.DefaultNewResourceHandler;
import org.kie.workbench.common.widgets.client.handlers.NewResourcePresenter;
import org.kie.workbench.common.widgets.client.resources.i18n.CommonConstants;
import org.uberfire.ext.widgets.common.client.callbacks.HasBusyIndicatorDefaultErrorCallback;
import org.uberfire.ext.widgets.common.client.common.BusyIndicatorView;
import org.uberfire.workbench.type.ResourceTypeDefinition;

/**
 * Handler for the creation of new Perspective Extensions
 */
@ApplicationScoped
public class NewPerspectiveExtensionHandler extends DefaultNewResourceHandler {

    private Caller<PerspectiveExtensionService> perspectiveExtensionService;

    private PerspectiveExtensionResourceType perspectiveExtensionResourceTypeDefinition;

    private BusyIndicatorView busyIndicatorView;

    public NewPerspectiveExtensionHandler() {

    }

    @Inject
    public NewPerspectiveExtensionHandler(Caller<PerspectiveExtensionService> perspectiveExtensionService,
                                          PerspectiveExtensionResourceType perspectiveExtensionResourceTypeDefinition,
                                          BusyIndicatorView busyIndicatorView) {

        this.perspectiveExtensionService = perspectiveExtensionService;
        this.perspectiveExtensionResourceTypeDefinition = perspectiveExtensionResourceTypeDefinition;
        this.busyIndicatorView = busyIndicatorView;
    }

    @Override
    public String getDescription() {

        return AppformerExtensionsEditorConstants.INSTANCE.newPerspectiveExtension();
    }

    @Override
    public IsWidget getIcon() {
        return new Image(AppformerExtensionsEditorResources.INSTANCE.images().extensions());
    }

    @Override
    public ResourceTypeDefinition getResourceType() {
        return perspectiveExtensionResourceTypeDefinition;
    }

    @Override
    public void create(final Package pkg,
                       final String baseFileName,
                       final NewResourcePresenter presenter) {
        busyIndicatorView.showBusyIndicator(CommonConstants.INSTANCE.Saving());
        perspectiveExtensionService.call(getSuccessCallback(presenter),
                                         new HasBusyIndicatorDefaultErrorCallback(busyIndicatorView)).create(pkg.getPackageMainResourcesPath(),
                                                                                                             buildFileName(baseFileName,
                                                                                                                           perspectiveExtensionResourceTypeDefinition),
                                                                                                             "",
                                                                                                             "");
    }
}
