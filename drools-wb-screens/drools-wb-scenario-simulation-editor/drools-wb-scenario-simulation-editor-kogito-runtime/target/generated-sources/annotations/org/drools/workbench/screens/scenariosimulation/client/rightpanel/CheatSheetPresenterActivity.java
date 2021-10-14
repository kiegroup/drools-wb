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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import javax.annotation.Generated;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import com.google.gwt.user.client.ui.InlineLabel;

import javax.inject.Named;
import org.uberfire.client.mvp.AbstractWorkbenchScreenActivity;
import org.uberfire.client.mvp.PlaceManager;

import org.uberfire.mvp.PlaceRequest;

import com.google.gwt.user.client.ui.IsWidget;

@Dependent
@Generated("org.uberfire.annotations.processors.WorkbenchScreenProcessor")
@Named("org.drools.scenariosimulation.CheatSheet")
/*
 * WARNING! This class is generated. Do not modify.
 */
public class CheatSheetPresenterActivity extends AbstractWorkbenchScreenActivity {

    @Inject
    private CheatSheetPresenter realPresenter;

    @Inject
    //Constructor injection for testing
    public CheatSheetPresenterActivity(final PlaceManager placeManager) {
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
    public String getIdentifier() {
        return "org.drools.scenariosimulation.CheatSheet";
    }
}
