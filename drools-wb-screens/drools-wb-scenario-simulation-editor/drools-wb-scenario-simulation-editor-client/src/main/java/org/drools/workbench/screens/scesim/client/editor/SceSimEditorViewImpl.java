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

package org.drools.workbench.screens.scesim.client.editor;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import org.drools.workbench.screens.scesim.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scesim.client.renderers.ScenarioGridRenderer;
import org.drools.workbench.screens.scesim.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scesim.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scesim.client.widgets.ScenarioGridPanel;
import org.gwtbootstrap3.client.ui.Button;
import org.kie.workbench.common.widgets.metadata.client.KieEditorViewImpl;

@Dependent
public class SceSimEditorViewImpl
        extends KieEditorViewImpl
        implements SceSimEditorView {

    private final org.gwtbootstrap3.client.ui.Button addButton;

    @Inject
    public SceSimEditorViewImpl(final Button addButton) {
        this.addButton = addButton;
    }

    @PostConstruct
    public void init() {
        final ScenarioGridLayer scenarioGridLayer = new ScenarioGridLayer();
        final ScenarioGrid scenarioGrid = new ScenarioGrid(new ScenarioGridModel(), scenarioGridLayer, new ScenarioGridRenderer(false));

        scenarioGridLayer.add(scenarioGrid);

        ScenarioGridPanel scenarioGridPanel = new ScenarioGridPanel(1000, 1000);

        scenarioGridPanel.add(scenarioGridLayer);

        initWidget(scenarioGridPanel);
    }

    @Override
    public SceSimEditorViewImpl setWidget(final IsWidget widget) {
        GWT.log("setWidget " + widget);
        //clear();
        //this.add(widget);
        //  this.scenarioGridPanel.setWidget(widget);
        return this;
    }

    @Override
    public SceSimEditorViewImpl clear() {
        GWT.log("clear");
        //scenarioGridPanel.clear();
        return this;
    }

    @Override
    public void setContent(List<SceSimRow> content) {
        GWT.log("setContent " + content);
    }

    @Override
    public ScenarioGridPanel getContent() {
        GWT.log("getContent");
        //return scenarioGridPanel;
        return null;
    }
}