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

import javax.enterprise.context.Dependent;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Dependent
@Templated(stylesheet = "/org/drools/workbench/screens/scenariosimulation/client/resources/css/ScenarioSimulationEditorStyles.css")
public class SettingsViewImpl
        extends Composite
        implements SettingsView {

    private Presenter presenter;

    @DataField("nameLabel")
    protected LabelElement nameLabel = Document.get().createLabelElement();

    @DataField("fileName")
    protected SpanElement fileName = Document.get().createSpanElement();

    @DataField("typeLabel")
    protected LabelElement typeLabel = Document.get().createLabelElement();

    @DataField("scenarioType")
    protected SpanElement scenarioType = Document.get().createSpanElement();

    @DataField("ruleSettings")
    protected DivElement ruleSettings = Document.get().createDivElement();

    @DataField("kieSession")
    protected InputElement kieSession = Document.get().createTextInputElement();

    @DataField("kieBase")
    protected InputElement kieBase = Document.get().createTextInputElement();

    @DataField("ruleFlowGroup")
    protected InputElement ruleFlowGroup = Document.get().createTextInputElement();

    @DataField("dmoSession")
    protected InputElement dmoSession = Document.get().createTextInputElement();

    @DataField("dmnSettings")
    protected DivElement dmnSettings = Document.get().createDivElement();

    @DataField("dmnFileLabel")
    protected LabelElement dmnFileLabel = Document.get().createLabelElement();

    @DataField("dmnFilePath")
    protected SpanElement dmnFilePath = Document.get().createSpanElement();

    @DataField("dmnNamespaceLabel")
    protected LabelElement dmnNamespaceLabel = Document.get().createLabelElement();

    @DataField("dmnNamespace")
    protected SpanElement dmnNamespace = Document.get().createSpanElement();

    @DataField("dmnNameLabel")
    protected LabelElement dmnNameLabel = Document.get().createLabelElement();

    @DataField("dmnName")
    protected SpanElement dmnName = Document.get().createSpanElement();

    public SettingsViewImpl() {
    }

    @Override
    public void init(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public Presenter getPresenter() {
        return presenter;
    }

    @Override
    public LabelElement getNameLabel() {
        return nameLabel;
    }

    @Override
    public SpanElement getFileName() {
        return fileName;
    }

    @Override
    public LabelElement getTypeLabel() {
        return typeLabel;
    }

    @Override
    public SpanElement getScenarioType() {
        return scenarioType;
    }

    @Override
    public DivElement getRuleSettings() {
        return ruleSettings;
    }

    @Override
    public InputElement getKieSession() {
        return kieSession;
    }

    @Override
    public InputElement getKieBase() {
        return kieBase;
    }

    @Override
    public InputElement getRuleFlowGroup() {
        return ruleFlowGroup;
    }

    @Override
    public InputElement getDmoSession() {
        return dmoSession;
    }

    @Override
    public DivElement getDmnSettings() {
        return dmnSettings;
    }

    @Override
    public LabelElement getDmnFileLabel() {
        return dmnFileLabel;
    }

    @Override
    public SpanElement getDmnFilePath() {
        return dmnFilePath;
    }

    @Override
    public LabelElement getDmnNamespaceLabel() {
        return dmnNamespaceLabel;
    }

    @Override
    public SpanElement getDmnNamespace() {
        return dmnNamespace;
    }

    @Override
    public LabelElement getDmnNameLabel() {
        return dmnNameLabel;
    }

    @Override
    public SpanElement getDmnName() {
        return dmnName;
    }

    @EventHandler("kieSession")
    public void onKieSessionKeyDownEvent(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            presenter.onKieSessionKeyDownEvent(kieSession.getValue());
        }
    }

    @EventHandler("kieBase")
    public void onKieBaseKeyDownEvent(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            presenter.onKieBaseKeyDownEvent(kieBase.getValue());
        }
    }

    @EventHandler("ruleFlowGroup")
    public void onRuleFlowGroupKeyDownEvent(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            presenter.onRuleFlowGroupKeyDownEvent(ruleFlowGroup.getValue());
        }
    }

    @EventHandler("dmoSession")
    public void onDmoSessionKeyDownEvent(KeyDownEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
            presenter.onDmoSessionKeyDownEvent(dmoSession.getValue());
        }
    }

}
