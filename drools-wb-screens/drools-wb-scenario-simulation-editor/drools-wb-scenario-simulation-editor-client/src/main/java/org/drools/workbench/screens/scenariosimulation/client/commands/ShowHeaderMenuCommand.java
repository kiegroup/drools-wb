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
package org.drools.workbench.screens.scenariosimulation.client.commands;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.uberfire.mvp.Command;

public class ShowHeaderMenuCommand implements Command {

    private final ScenarioGrid scenarioGrid;
    private final VerticalPanel menuPanel;
    private final int left;
    private final int top;


    public ShowHeaderMenuCommand(ScenarioGrid scenarioGrid, int left, int top) {
        this.scenarioGrid = scenarioGrid;
        menuPanel = new VerticalPanel();
        menuPanel.add(new Label("One"));
        menuPanel.add(new Label("Two"));
        menuPanel.add(new Label("Three"));
        menuPanel.addHandler(event -> hide(), ClickEvent.getType());
        menuPanel.addStyleName("dropdown-menu");
        this.left = left;
        this.top = top;
    }

    @Override
    public void execute() {
        GWT.log("ShowHeaderMenuCommand execute " + scenarioGrid);
        hide();
        RootPanel.get().add( menuPanel, left, top );
    }

    private void hide() {
        if ( menuPanel.isAttached() ) {
            RootPanel.get().remove(menuPanel );
        }
    }


}
