/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.appformer.extensions.model;

import org.guvnor.common.services.shared.metadata.model.Overview;
import org.jboss.errai.common.client.api.annotations.Portable;
import org.uberfire.commons.validation.PortablePreconditions;
import org.uberfire.ext.layout.editor.api.editor.LayoutTemplate;

@Portable
public class PerspectiveExtensionContent {

    private LayoutTemplate content;
    private Overview overview;

    public PerspectiveExtensionContent() {
    }

    public PerspectiveExtensionContent(final LayoutTemplate content,
                                       final Overview overview
    ) {
        this.overview = PortablePreconditions.checkNotNull("overview",
                                                           overview);
        this.content = PortablePreconditions.checkNotNull("content",
                                                          content);
    }

    public LayoutTemplate getContent() {
        return content;
    }

    public Overview getOverview() {
        return overview;
    }
}
