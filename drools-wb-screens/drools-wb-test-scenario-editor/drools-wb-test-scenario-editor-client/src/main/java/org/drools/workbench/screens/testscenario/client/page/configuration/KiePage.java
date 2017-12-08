/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.testscenario.client.page.configuration;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.drools.workbench.models.testscenarios.shared.Scenario;
import org.drools.workbench.screens.testscenario.client.ScenarioParentWidget;
import org.drools.workbench.screens.testscenario.client.resources.i18n.TestScenarioConstants;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.views.pfly.multipage.PageImpl;

@Dependent
public class KiePage extends PageImpl implements KiePageView.Presenter {

    private KiePageView kiePageView;

    @Inject
    public KiePage(final KiePageView kiePageView) {
        super(ElementWrapperWidget.getWidget(kiePageView.getElement()), TestScenarioConstants.INSTANCE.Configuration());
        this.kiePageView = kiePageView;
    }

    public void refresh(final ScenarioParentWidget scenarioParentWidget, final Path path, final Scenario scenario) {
        kiePageView.refresh(scenarioParentWidget, path, scenario);
    }
}
