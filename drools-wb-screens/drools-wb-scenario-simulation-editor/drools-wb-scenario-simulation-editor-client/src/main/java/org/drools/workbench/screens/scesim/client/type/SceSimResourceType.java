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

package org.drools.workbench.screens.scesim.client.type;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import org.drools.workbench.screens.scesim.client.resources.SceSimEditorResources;
import org.drools.workbench.screens.scesim.client.resources.i18n.SceSimEditorConstants;
import org.drools.workbench.screens.scesim.type.SceSimResourceTypeDefinition;
import org.guvnor.common.services.project.categories.Model;
import org.uberfire.client.workbench.type.ClientResourceType;

@ApplicationScoped
public class SceSimResourceType
        extends SceSimResourceTypeDefinition
        implements ClientResourceType {

    //GwtMockito barfs when this is static... so keep it as an instance variable
    private Image IMAGE = new Image(SceSimEditorResources.INSTANCE.images().typeSceSim());

    public SceSimResourceType() {
    }

    @Inject
    public SceSimResourceType(final Model category) {
        super(category);
    }

    @Override
    public IsWidget getIcon() {
        return IMAGE;
    }

    @Override
    public String getDescription() {
        String desc = SceSimEditorConstants.INSTANCE.scesimResourceTypeDescription();
        if (desc == null || desc.isEmpty()) {
            return super.getDescription();
        }
        return desc;
    }
}
