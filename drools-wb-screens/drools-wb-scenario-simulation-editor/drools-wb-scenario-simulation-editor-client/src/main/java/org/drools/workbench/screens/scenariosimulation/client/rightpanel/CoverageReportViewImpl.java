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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import javax.enterprise.context.ApplicationScoped;

import com.google.gwt.user.client.ui.Composite;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import elemental2.dom.HTMLUListElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

// FIXME to test
@ApplicationScoped
@Templated(stylesheet = "/org/drools/workbench/screens/scenariosimulation/client/resources/css/ScenarioSimulationEditorStyles.css")
public class CoverageReportViewImpl
        extends Composite
        implements CoverageReportView {

    protected Presenter presenter;

    @DataField
    protected HTMLElement reportAvailable = (HTMLElement) DomGlobal.document.createElement("dd");

    @DataField
    protected HTMLElement reportExecuted = (HTMLElement) DomGlobal.document.createElement("dd");

    @DataField
    protected HTMLElement reportCoverage = (HTMLElement) DomGlobal.document.createElement("dd");

    @DataField
    protected HTMLElement decisionList = (HTMLElement) DomGlobal.document.createElement("dl");

    @DataField
    protected HTMLDivElement donutChart = (HTMLDivElement) DomGlobal.document.createElement("div");

    @DataField
    protected HTMLUListElement scenarioList = (HTMLUListElement) DomGlobal.document.createElement("ul");

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public HTMLElement getReportAvailable() {
        return reportAvailable;
    }

    @Override
    public HTMLElement getReportExecuted() {
        return reportExecuted;
    }

    @Override
    public HTMLElement getReportCoverage() {
        return reportCoverage;
    }

    @Override
    public HTMLElement getDecisionList() {
        return decisionList;
    }

    @Override
    public HTMLDivElement getDonutChart() {
        return donutChart;
    }

    @Override
    public HTMLUListElement getScenarioList() {
        return scenarioList;
    }
}
