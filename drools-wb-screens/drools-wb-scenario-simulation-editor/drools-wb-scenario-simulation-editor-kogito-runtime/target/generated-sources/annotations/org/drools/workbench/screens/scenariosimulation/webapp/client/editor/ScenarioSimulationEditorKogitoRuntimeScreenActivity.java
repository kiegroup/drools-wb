/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.scenariosimulation.webapp.client.editor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.Generated;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import javax.inject.Named;
import org.uberfire.client.mvp.AbstractWorkbenchClientEditorActivity;
import org.uberfire.client.mvp.PlaceManager;
import elemental2.promise.Promise;
import org.uberfire.client.mvp.IsClientEditor;

import org.uberfire.mvp.PlaceRequest;

import com.google.gwt.user.client.ui.IsWidget;


@Dependent
@Generated("org.uberfire.annotations.processors.WorkbenchClientEditorProcessor")
@Named("ScenarioSimulationEditor")
@IsClientEditor
/*
 * WARNING! This class is generated. Do not modify.
 */
public class ScenarioSimulationEditorKogitoRuntimeScreenActivity extends AbstractWorkbenchClientEditorActivity {

    @Inject
    private ScenarioSimulationEditorKogitoRuntimeScreen realPresenter;

    @Inject
    //Constructor injection for testing
    public ScenarioSimulationEditorKogitoRuntimeScreenActivity(final PlaceManager placeManager) {
        super( placeManager );
    }


    @Override
    public void onStartup(final PlaceRequest place) {
        super.onStartup( place );
        realPresenter.onStartup( place );
    }
    @Override
    public boolean onMayClose() {
        return realPresenter.mayClose();
    }

    @Override
    public IsWidget getTitleDecoration() {
        return realPresenter.getTitle();
    }

    @Override
    public String getTitle() {
        return realPresenter.getTitleText();
    }

    @Override
    public IsWidget getWidget() {
        return realPresenter.getWidget();
    }

    @Override
    public boolean isDirty() {
        return realPresenter.isDirty();
    }
    @Override
    public Promise<Void> setContent(String path, String value) {
        return realPresenter.setContent(path, value);
    }
    @Override
    public Promise<String> getContent() {
        return realPresenter.getContent();
    }
    @Override
    public Promise<String> getPreview() {
        return Promise.resolve("");
    }

    @Override
    public Promise validate() {
        return Promise.resolve(Collections.emptyList());
    }

    @Override
    public String getIdentifier() {
        return "ScenarioSimulationEditor";
    }
}
