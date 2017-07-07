/*
 * Copyright 2012 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.appformer.extensions.client.editor;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.Scheduler;
import org.drools.workbench.screens.appformer.extensions.client.resources.i18n.AppformerExtensionsEditorConstants;
import org.jboss.errai.common.client.dom.DOMUtil;
import org.jboss.errai.common.client.dom.Div;
import org.jboss.errai.common.client.dom.HTMLElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.kie.workbench.common.widgets.metadata.client.KieEditorViewImpl;

@Dependent
@Templated
public class PerspectiveExtensionEditorViewImpl
        extends KieEditorViewImpl
        implements PerspectiveExtensionEditorView {

    @Inject
    @DataField("pe-layout")
    Div layoutContainer;
    private PerspectiveExtensionEditorPresenter presenter;

    @Override
    public void onResize() {

    }

    @Override
    public void setContent(HTMLElement layoutEditorPlugin) {
        DOMUtil.removeAllChildren(layoutContainer);
        layoutContainer.appendChild(layoutEditorPlugin);
    }

    @Override
    public String emptyTitleText() {
        return AppformerExtensionsEditorConstants.INSTANCE.emptyTitleText();
    }

    @Override
    public String emptySubTitleText() {
        return AppformerExtensionsEditorConstants.INSTANCE.emptySubTitleText();
    }

    @Override
    public void init(final PerspectiveExtensionEditorPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        Scheduler.get().scheduleDeferred(() -> onResize());
    }
}
