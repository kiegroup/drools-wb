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
package org.drools.workbench.screens.scenariosimulation.client.commands;

import javax.enterprise.context.Dependent;

import com.google.gwt.event.shared.EventBus;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.drools.workbench.screens.scenariosimulation.client.models.ScenarioGridModel;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.RightPanelView;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;

/**
 * This class represent the <b>Context</b> inside which the commands will be executed
 */
@Dependent
public class ScenarioSimulationContext {

    protected ScenarioGridModel model;
    protected ScenarioGridPanel scenarioGridPanel;
    protected ScenarioGridLayer scenarioGridLayer;
    protected ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter;
    protected RightPanelView.Presenter rightPanelPresenter;

    protected String columnId;
    protected int columnIndex;
    protected boolean isRight;
    protected boolean asProperty;

    protected String columnGroup;

    protected String fullPackage;
    protected String className;

    protected String value;
    protected String valueClassName;
    protected boolean keepData;


    public void setModel(ScenarioGridModel model) {
        this.model = model;
    }


    public void setScenarioSimulationEditorPresenter(ScenarioSimulationEditorPresenter scenarioSimulationEditorPresenter) {
        this.scenarioSimulationEditorPresenter = scenarioSimulationEditorPresenter;
    }

    public void setRightPanelPresenter(RightPanelView.Presenter rightPanelPresenter) {
        this.rightPanelPresenter = rightPanelPresenter;
    }

    public ScenarioGridPanel getScenarioGridPanel() {
        return scenarioGridPanel;
    }

    /**
     * This method set <code>ScenarioGridPanel</code>, <code>ScenarioGridLayer</code> and <code>ScenarioGridModel</code>
     * from the give <code>ScenarioGridPanel</code>
     * @param scenarioGridPanel
     */
    public void setScenarioGridPanel(ScenarioGridPanel scenarioGridPanel) {
        this.scenarioGridPanel = scenarioGridPanel;
        this.scenarioGridLayer = scenarioGridPanel.getScenarioGridLayer();
        this.model = scenarioGridLayer.getScenarioGrid().getModel();
    }

    public ScenarioGridModel getModel() {
        return model;
    }

    public ScenarioGridLayer getScenarioGridLayer() {
        return scenarioGridLayer;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public boolean isAsProperty() {
        return asProperty;
    }

    public void setAsProperty(boolean asProperty) {
        this.asProperty = asProperty;
    }

    public String getColumnGroup() {
        return columnGroup;
    }

    public void setColumnGroup(String columnGroup) {
        this.columnGroup = columnGroup;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public String getFullPackage() {
        return fullPackage;
    }

    public void setFullPackage(String fullPackage) {
        this.fullPackage = fullPackage;
    }

    public boolean isKeepData() {
        return keepData;
    }

    public void setKeepData(boolean keepData) {
        this.keepData = keepData;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueClassName() {
        return valueClassName;
    }

    public void setValueClassName(String valueClassName) {
        this.valueClassName = valueClassName;
    }
}
