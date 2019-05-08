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

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.KeyUpEvent;
import elemental2.dom.Element;
import elemental2.dom.HTMLInputElement;
import elemental2.dom.HTMLOptionElement;
import elemental2.dom.HTMLSelectElement;
import org.jboss.errai.ui.client.local.spi.TranslationService;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.uberfire.client.views.pfly.selectpicker.JQuerySelectPicker;
import org.uberfire.client.views.pfly.selectpicker.JQuerySelectPickerEvent;

import static org.kie.workbench.common.widgets.client.resources.i18n.KieWorkbenchWidgetsConstants.KieAssetsDropdownView_Select;

public class ScenarioSimulationDropdownView implements ScenarioSimulationDropdown.View {

    public static final String HIDDEN_CSS_CLASS = "hidden";

    @DataField("native-select")
    private final HTMLSelectElement nativeSelect;

    @DataField("fallback-input")
    private final HTMLInputElement fallbackInput;

    private final HTMLOptionElement htmlOptionElement;

    private final TranslationService translationService;

    protected ScenarioSimulationDropdown presenter;

    @Inject
    public ScenarioSimulationDropdownView(final HTMLSelectElement nativeSelect,
                                 final HTMLInputElement fallbackInput,
                                 final HTMLOptionElement htmlOptionElement,
                                 final TranslationService translationService) {
        this.nativeSelect = nativeSelect;
        this.fallbackInput = fallbackInput;
        this.htmlOptionElement = htmlOptionElement;
        this.translationService = translationService;
    }

    @Override
    public void init(final ScenarioSimulationDropdown presenter) {
        this.presenter = presenter;
    }

    @PostConstruct
    public void init() {
        nativeSelect.hidden = false;
        fallbackInput.hidden = true;
        dropdown().on("hidden.bs.select", getOnDropdownChangeHandler());
    }

    @Override
    public void addValue(final String entry) {
        nativeSelect.appendChild(entryOption(entry));
    }

    @Override
    public void clear() {
        removeChildren(nativeSelect);
        nativeSelect.appendChild(selectOption());
        refreshSelectPicker();
    }

    @Override
    public void initialize() {
        fallbackInput.value = "";
        dropdown().selectpicker("val", "");
    }

    @Override
    public void refreshSelectPicker() {
        dropdown().selectpicker("refresh");
    }

    @Override
    public String getValue() {
        return fallbackInput.value;
    }

    @Override
    public void enableDropdownMode() {
        fallbackInput.classList.add(HIDDEN_CSS_CLASS);
        nativeSelect.classList.remove(HIDDEN_CSS_CLASS);
        dropdown().selectpicker("show");
    }

    @EventHandler("fallback-input")
    public void onFallbackInputChange(final KeyUpEvent e) {
        presenter.onValueChanged();
    }

    protected HTMLOptionElement selectOption() {
        final HTMLOptionElement toReturn = makeHTMLOptionElement();
        toReturn.text = translationService.format(KieAssetsDropdownView_Select);
        toReturn.value = "";
        return toReturn;
    }

    protected HTMLOptionElement makeHTMLOptionElement() {
        // This is a workaround for an issue on Errai (ERRAI-1114) related to 'ManagedInstance' + 'HTMLOptionElement'.
        return (HTMLOptionElement) htmlOptionElement.cloneNode(false);
    }

    protected JQuerySelectPicker dropdown() {
        return JQuerySelectPicker.$(nativeSelect);
    }

    protected JQuerySelectPicker.CallbackFunction getOnDropdownChangeHandler() {
        return this::onDropdownChangeHandlerMethod;
    }

    protected void onDropdownChangeHandlerMethod(JQuerySelectPickerEvent event) {
        fallbackInput.value = event.target.value;
        presenter.onValueChanged();
    }

    protected HTMLOptionElement entryOption(final String entry) {

        final HTMLOptionElement option = makeHTMLOptionElement();
        option.text = entry;
        option.value = entry;
        return option;
    }

    protected void removeChildren(final Element element) {
        while (element.firstChild != null) {
            element.removeChild(element.firstChild);
        }
    }
}
