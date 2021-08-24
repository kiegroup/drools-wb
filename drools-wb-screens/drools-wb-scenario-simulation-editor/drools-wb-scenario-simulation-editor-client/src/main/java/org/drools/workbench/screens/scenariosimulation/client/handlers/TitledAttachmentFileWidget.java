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
package org.drools.workbench.screens.scenariosimulation.client.handlers;

import java.util.Optional;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.drools.workbench.screens.scenariosimulation.client.dropdown.ScenarioSimulationDropdown;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationService;
import org.gwtbootstrap3.client.ui.FormLabel;
import org.gwtbootstrap3.client.ui.constants.ElementTags;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.constants.Styles;
import org.gwtbootstrap3.client.ui.html.Div;
import org.gwtbootstrap3.client.ui.html.Span;
import org.jboss.errai.common.client.api.Caller;
import org.kie.workbench.common.widgets.client.assets.dropdown.KieAssetsDropdownItem;

public class TitledAttachmentFileWidget extends Composite {

    protected FlowPanel fields = GWT.create(FlowPanel.class);
    protected VerticalPanel pmmlFields = GWT.create(VerticalPanel.class);
    protected Div divElement = GWT.create(Div.class);
    protected FormLabel titleLabel = GWT.create(FormLabel.class);
    protected Span errorLabel = GWT.create(Span.class);
    protected Span pmmlModelLabel = GWT.create(Span.class);
    protected TextArea pmmlModelText = GWT.create(TextArea.class);
    protected ScenarioSimulationDropdown scenarioSimulationDropdown;
    protected Caller<ScenarioSimulationService> scenarioSimulationService;
    protected String selectedPath;
    protected String selectedType = "dmn";

    public TitledAttachmentFileWidget(String title,
                                      Caller<ScenarioSimulationService> scenarioSimulationService, ScenarioSimulationDropdown scenarioSimulationDropdown) {
        this.scenarioSimulationService = scenarioSimulationService;
        this.scenarioSimulationDropdown = scenarioSimulationDropdown;
        titleLabel.setStyleName("control-label");
        titleLabel.setText(title);
        divElement.add(titleLabel);
        divElement.getElement().appendChild(createIconElement());
        errorLabel.setStyleName("help-block");
        errorLabel.setColor("#c00");
        errorLabel.setText(ScenarioSimulationEditorConstants.INSTANCE.chooseValidDMNAsset());
        fields.add(divElement);
        fields.add(this.scenarioSimulationDropdown.asWidget());
        fields.add(this.errorLabel);
        pmmlModelLabel.setText(ScenarioSimulationEditorConstants.INSTANCE.insertPMMLModelName());
        pmmlFields.add(this.pmmlModelLabel);
        pmmlFields.add(this.pmmlModelText);
        fields.add(pmmlFields);
        pmmlFields.setVisible(false);
        initWidget(fields);
        scenarioSimulationDropdown.init();
        scenarioSimulationDropdown.registerOnChangeHandler(() -> {
            final Optional<KieAssetsDropdownItem> value = scenarioSimulationDropdown.getValue();
            selectedPath = value.map(KieAssetsDropdownItem::getValue).orElse(null);
            validate(selectedType);
        });
    }

    public void setVisible(String type, boolean visible) {
        selectedType = type;
        setVisible(visible);
        if (visible) {
            switch (selectedType) {
                case "dmn":
                    titleLabel.setText(ScenarioSimulationEditorConstants.INSTANCE.chooseDMN());
                    errorLabel.setText(ScenarioSimulationEditorConstants.INSTANCE.chooseValidDMNAsset());
                    break;
                case "pmml":
                    titleLabel.setText(ScenarioSimulationEditorConstants.INSTANCE.choosePMML());
                    errorLabel.setText(ScenarioSimulationEditorConstants.INSTANCE.chooseValidPMMLAsset());
                    break;
                default:
                    errorLabel.setText("");
            }
            pmmlFields.setVisible(selectedType.equals("pmml"));
        }
        setVisible(this.getElement(), visible);
    }

    public void clearStatus() {
        updateAssetList("*");
        errorLabel.setText(null);
        selectedPath = null;
        pmmlModelText.setText(null);
    }

    public void updateAssetList(String type) {
        scenarioSimulationDropdown.loadAssets(type);
    }

    public String getSelectedPath() {
        return selectedPath;
    }

    public String getModelName() {
        return pmmlModelText.getText();
    }

    public boolean validate(String type) {
        boolean isPathSelected = selectedPath != null && !selectedPath.isEmpty();
        String toSet = "";
        if (!isPathSelected) {
            switch (type) {
                case "dmn":
                    toSet = ScenarioSimulationEditorConstants.INSTANCE.chooseValidDMNAsset();
                    break;
                case "pmml":
                    toSet = ScenarioSimulationEditorConstants.INSTANCE.chooseValidPMMLAsset();
                    break;
                default:
                    toSet = "";
            }
        }
        boolean isModelNameSet = !type.equals("pmml") || !pmmlModelText.getText().isEmpty();
        if (!isModelNameSet) {
            toSet += " " + ScenarioSimulationEditorConstants.INSTANCE.insertPMMLModelName();
        }
        errorLabel.setText(toSet);
        return isPathSelected && isModelNameSet;
    }

    /**
     * @return a new icon element.
     */
    protected Element createIconElement() {
        Element e = Document.get().createElement(ElementTags.I);
        e.addClassName(Styles.FONT_AWESOME_BASE);
        e.addClassName(IconType.STAR.getCssName());
        Style s = e.getStyle();
        s.setFontSize(6, Style.Unit.PX);
        s.setPaddingLeft(2, Style.Unit.PX);
        s.setPaddingRight(5, Style.Unit.PX);
        s.setColor("#b94a48");
        Element sup = Document.get().createElement("sup");
        sup.appendChild(e);
        return sup;
    }
}