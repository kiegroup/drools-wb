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
package org.drools.workbench.screens.scenariosimulation.client.dropdown;

import java.util.List;
import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;
import elemental2.dom.HTMLElement;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.uberfire.client.mvp.UberElemental;
import org.uberfire.mvp.Command;

@Dependent
public class ScenarioSimulationDropdown {

    private final ScenarioSimulationDropdown.View view;

    private final ScenarioSimulationAssetsDropdownProvider dataProvider;

//    private final List<String> kieAssets = new ArrayList<>();

    protected Command onValueChangeHandler = () -> {/* Nothing. */};

    @Inject
    public ScenarioSimulationDropdown(final ScenarioSimulationDropdown.View view,
                                      final ScenarioSimulationAssetsDropdownProvider dataProvider) {
        this.view = view;
        this.dataProvider = dataProvider;
    }

    @PostConstruct
    public void init() {
        view.init(this);
    }

    public void registerOnChangeHandler(final Command onChangeHandler) {
        this.onValueChangeHandler = onChangeHandler;
    }

    public void loadAssets() {
        clear();
        initializeDropdown();
    }

    public void initialize() {
        view.refreshSelectPicker();
    }

    public void clear() {
        view.clear();
    }

    public Widget asWidget() {
        return ElementWrapperWidget.getWidget(getElement());
    }

    public HTMLElement getElement() {
        return view.getElement();
    }

    public String getValue() {
        return view.getValue();
    }

    protected Consumer<List<String>> getAssetListConsumer() {
        return this::assetListConsumerMethod;
    }

    protected void assetListConsumerMethod(List<String> assetList) {
        assetList.forEach(this::addValue);
        view.refreshSelectPicker();
        view.initialize();
    }

    protected void onValueChanged() {
        onValueChangeHandler.execute();
    }

    protected void initializeDropdown() {
        view.enableDropdownMode();
        dataProvider.getItems(getAssetListConsumer());
    }

    protected void addValue(final String kieAsset) {
        view.addValue(kieAsset);
    }

    public interface View extends UberElemental<ScenarioSimulationDropdown>,
                                  IsElement {

        void clear();

        void addValue(final String entry);

        void refreshSelectPicker();

        void initialize();

        String getValue();

        void enableDropdownMode();
    }
}
