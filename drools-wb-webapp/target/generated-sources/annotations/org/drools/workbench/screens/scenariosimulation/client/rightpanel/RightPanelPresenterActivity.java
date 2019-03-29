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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import javax.annotation.Generated;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import javax.inject.Named;
import org.uberfire.client.mvp.AbstractWorkbenchScreenActivity;
import org.uberfire.client.mvp.PlaceManager;

import org.uberfire.workbench.model.Position;

import com.google.gwt.user.client.ui.IsWidget;

@Dependent
@Generated("org.uberfire.annotations.processors.WorkbenchScreenProcessor")
@Named("org.drools.scenariosimulation.RightPanel")
/*
 * WARNING! This class is generated. Do not modify.
 */
public class RightPanelPresenterActivity extends AbstractWorkbenchScreenActivity {

    @Inject
    private TestToolsPresenter realPresenter;

    @Inject
    //Constructor injection for testing
    public RightPanelPresenterActivity(final PlaceManager placeManager) {
        super( placeManager );
    }

    @Override
    public int preferredWidth() {
       return 300;
    }

    @Override
    public String getTitle() {
        return realPresenter.getTitle();
    }

    @Override
    public IsWidget getWidget() {
        return realPresenter.asWidget();
    }


    @Override
    public Position getDefaultPosition() {
        return realPresenter.getDefaultPosition();
    }
    @Override
    public String getIdentifier() {
        return "org.drools.scenariosimulation.RightPanel";
    }
}
