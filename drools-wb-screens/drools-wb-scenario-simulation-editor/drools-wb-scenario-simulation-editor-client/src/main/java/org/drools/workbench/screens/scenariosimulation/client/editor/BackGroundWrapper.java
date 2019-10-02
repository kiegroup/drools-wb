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

package org.drools.workbench.screens.scenariosimulation.client.editor;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RequiresResize;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;

public class BackGroundWrapper extends Composite implements RequiresResize {

    private ScenarioGridPanel background;

    public void init(ScenarioGridPanel panel) {
        this.background = panel;
        panel.getScenarioGridLayer().enterPinnedMode(
                panel.getScenarioGridLayer().getScenarioGrid(), () -> {
                });  // Hack to overcome default implementation
        initWidget(panel);
    }
    @Override
    public void onResize() {
        final Widget parent = getParent();
        if (parent != null) {
            final double w = parent.getOffsetWidth();
            final double h = parent.getOffsetHeight();
            setPixelSize((int) w, (int) h);
        }
        background.onResize();
    }
}
