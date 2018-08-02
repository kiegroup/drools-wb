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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.uberfire.client.annotations.DefaultPosition;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.workbench.model.CompassPosition;
import org.uberfire.workbench.model.Position;

import static org.drools.workbench.screens.scenariosimulation.client.rightpanel.RightPanelPresenter.IDENTIFIER;

@Dependent
@WorkbenchScreen(identifier = IDENTIFIER)
public class RightPanelPresenter
        implements RightPanelView.Presenter {

    public static final String IDENTIFIER = "org.drools.scenariosimulation.RightPanel";

    private RightPanelView view;

    public RightPanelPresenter() {
        //Zero argument constructor for CDI
    }

    @Inject
    public RightPanelPresenter(RightPanelView view) {
        this.view = view;
    }

    @PostConstruct
    public void setup() {
        view.init(this);
    }

    @DefaultPosition
    public Position getDefaultPosition() {
        return CompassPosition.EAST;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return ScenarioSimulationEditorConstants.INSTANCE.TestTools();
    }

    @WorkbenchPartView
    public Widget asWidget() {
        return view.asWidget();
    }
}
