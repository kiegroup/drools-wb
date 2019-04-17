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

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Composite;
import elemental2.dom.DomGlobal;
import elemental2.dom.HTMLDivElement;
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
    protected Element reportAvailable = Document.get().createElement("dd");

    @DataField
    protected Element reportExecuted = Document.get().createElement("dd");

    @DataField
    protected Element reportCoverage = Document.get().createElement("dd");

    @DataField
    protected Element decisionList = Document.get().createElement("dl");

    @DataField
    protected HTMLDivElement donutChart = (HTMLDivElement) DomGlobal.document.createElement("div");

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Element getReportAvailable() {
        return reportAvailable;
    }

    @Override
    public Element getReportExecuted() {
        return reportExecuted;
    }

    @Override
    public Element getReportCoverage() {
        return reportCoverage;
    }

    @Override
    public Element getDecisionList() {
        return decisionList;
    }

    @Override
    public HTMLDivElement getDonutChart() {
        return donutChart;
    }
}
