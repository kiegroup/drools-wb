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

import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Composite;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@ApplicationScoped
@Templated(stylesheet = "/org/drools/workbench/screens/scenariosimulation/client/resources/css/ScenarioSimulationEditorStyles.css")
public class SettingsViewImpl
        extends Composite
        implements SettingsView {

    protected Presenter presenter;

    @DataField("nameLabel")
    protected LabelElement nameLabel = Document.get().createLabelElement();

    @DataField("fileName")
    protected InputElement fileName = Document.get().createTextInputElement();

    @DataField("typeLabel")
    protected LabelElement typeLabel = Document.get().createLabelElement();

    @DataField("scenarioType")
    protected SpanElement scenarioType = Document.get().createSpanElement();

    @DataField("ruleSettings")
    protected DivElement ruleSettings = Document.get().createDivElement();

    @DataField("dmoSession")
    protected InputElement dmoSession = Document.get().createTextInputElement();

    @DataField("ruleFlowGroup")
    protected InputElement ruleFlowGroup = Document.get().createTextInputElement();

    @DataField("dmnSettings")
    protected DivElement dmnSettings = Document.get().createDivElement();

    @DataField("dmnFileLabel")
    protected LabelElement dmnFileLabel = Document.get().createLabelElement();

    @DataField("dmnFilePath")
    protected InputElement dmnFilePath = Document.get().createTextInputElement();

    @DataField("dmnNamespaceLabel")
    protected LabelElement dmnNamespaceLabel = Document.get().createLabelElement();

    @DataField("dmnNamespace")
    protected InputElement dmnNamespace = Document.get().createTextInputElement();

    @DataField("dmnNameLabel")
    protected LabelElement dmnNameLabel = Document.get().createLabelElement();

    @DataField("dmnName")
    protected InputElement dmnName = Document.get().createTextInputElement();

    @DataField("skipFromBuild")
    protected InputElement skipFromBuild = Document.get().createCheckInputElement();

    @DataField("skipFromBuildLabel")
    protected SpanElement skipFromBuildLabel = Document.get().createSpanElement();

    @DataField("saveButton")
    protected ButtonElement saveButton = Document.get().createButtonElement();

    @DataField("stateless")
    protected InputElement stateless = Document.get().createCheckInputElement();

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
    public void reset() {
        scenarioType.setInnerText("");
        fileName.setValue("");
        dmnName.setValue("");
        dmnNamespace.setValue("");
        dmnFilePath.setValue("");
        skipFromBuild.setChecked(false);
        stateless.setChecked(false);
        dmnSettings.getStyle().setDisplay(Style.Display.NONE);
        ruleSettings.getStyle().setDisplay(Style.Display.NONE);
    }

    @Override
    public LabelElement getNameLabel() {
        return nameLabel;
    }

    @Override
    public InputElement getFileName() {
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
    public InputElement getDmoSession() {
        return dmoSession;
    }

    @Override
    public InputElement getRuleFlowGroup() {
        return ruleFlowGroup;
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
    public InputElement getDmnFilePath() {
        return dmnFilePath;
    }

    @Override
    public LabelElement getDmnNamespaceLabel() {
        return dmnNamespaceLabel;
    }

    @Override
    public InputElement getDmnNamespace() {
        return dmnNamespace;
    }

    @Override
    public LabelElement getDmnNameLabel() {
        return dmnNameLabel;
    }

    @Override
    public InputElement getDmnName() {
        return dmnName;
    }

    @Override
    public InputElement getSkipFromBuild() {
        return skipFromBuild;
    }

    @Override
    public SpanElement getSkipFromBuildLabel() {
        return skipFromBuildLabel;
    }

    @Override
    public InputElement getStateless() {
        return stateless;
    }

    @Override
    public ButtonElement getSaveButton() {
        return saveButton;
    }

    @EventHandler("saveButton")
    public void onSaveButtonClickEvent(ClickEvent event) {
        presenter.onSaveButton(scenarioType.getInnerText());
    }
}
