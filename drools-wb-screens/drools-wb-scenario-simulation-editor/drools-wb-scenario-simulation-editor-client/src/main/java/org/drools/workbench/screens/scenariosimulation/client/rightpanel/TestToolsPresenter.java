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

package org.drools.workbench.screens.scenariosimulation.client.rightpanel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.shared.EventBus;
import org.drools.scenariosimulation.api.model.FactMappingValueType;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.events.SetPropertyHeaderEvent;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.uberfire.client.annotations.WorkbenchScreen;

import static org.drools.workbench.screens.scenariosimulation.client.rightpanel.TestToolsPresenter.DEFAULT_PREFERRED_WIDHT;
import static org.drools.workbench.screens.scenariosimulation.client.rightpanel.TestToolsPresenter.IDENTIFIER;

@ApplicationScoped
@WorkbenchScreen(identifier = IDENTIFIER, preferredWidth = DEFAULT_PREFERRED_WIDHT)
public class TestToolsPresenter extends AbstractSubDockPresenter<TestToolsView> implements TestToolsView.Presenter {

    public static final String IDENTIFIER = "org.drools.scenariosimulation.TestTools";
    protected Map<String, FactModelTree> dataObjectFieldsMap = new TreeMap<>();
    protected Map<String, FactModelTree> simpleJavaTypeFieldsMap = new TreeMap<>();
    protected Map<String, FactModelTree> instanceFieldsMap = new TreeMap<>();
    protected Map<String, FactModelTree> simpleJavaInstanceFieldsMap = new TreeMap<>();
    protected Map<String, FactModelTree> hiddenFieldsMap = new TreeMap<>();
    protected EventBus eventBus;
    protected GridWidget gridWidget;
    protected boolean editingColumnEnabled = false;
    protected ListGroupItemView selectedListGroupItemView;
    protected FieldItemView selectedFieldItemView;
    private ListGroupItemPresenter listGroupItemPresenter;

    public TestToolsPresenter() {
        //Zero argument constructor for CDI
        title = ScenarioSimulationEditorConstants.INSTANCE.testTools();
    }

    @Inject
    public TestToolsPresenter(TestToolsView view, ListGroupItemPresenter listGroupItemPresenter) {
        super(view);
        this.listGroupItemPresenter = listGroupItemPresenter;
        this.listGroupItemPresenter.init(this);
        title = ScenarioSimulationEditorConstants.INSTANCE.testTools();
    }

    @Override
    public void onClearSearch() {
        view.clearInputSearch();
        view.hideClearButton();
        onSearchedEvent("");
    }

    @Override
    public void onUndoSearch() {
        view.clearInputSearch();
        view.hideClearButton();
        onPerfectMatchSearchedEvent(listGroupItemPresenter.getFilterTerm(), true);
    }

    @Override
    public void onClearNameField() {
        view.clearNameField();
    }

    @Override
    public void onClearStatus() {
        onClearSearch();
        onClearNameField();
    }

    @Override
    public void clearDataObjectList() {
        view.getDataObjectListContainer().removeAllChildren();
    }

    @Override
    public void clearSimpleJavaTypeList() {
        view.getSimpleJavaTypeListContainer().removeAllChildren();
    }

    @Override
    public void clearInstanceList() {
        view.getInstanceListContainer().removeAllChildren();
    }

    @Override
    public void clearSimpleJavaInstanceFieldList() {
        view.getSimpleJavaInstanceListContainer().removeAllChildren();
    }

    @Override
    public void updateDataObjectListSeparator() {
        if (view.getDataObjectListContainer().getChildCount() == 0) {
            view.getDataObjectListContainerSeparator().getStyle().setDisplay(Style.Display.NONE);
        } else {
            view.getDataObjectListContainerSeparator().getStyle().setDisplay(Style.Display.BLOCK);
        }
    }

    @Override
    public void updateSimpleJavaTypeListSeparator() {
        if (view.getSimpleJavaTypeListContainer().getChildCount() == 0) {
            view.getSimpleJavaTypeListContainerSeparator().getStyle().setDisplay(Style.Display.NONE);
        } else {
            view.getSimpleJavaTypeListContainerSeparator().getStyle().setDisplay(Style.Display.BLOCK);
        }
    }

    @Override
    public void updateInstanceListSeparator() {
        if (view.getInstanceListContainer().getChildCount() < 1 || GridWidget.BACKGROUND.equals(gridWidget)) {
            view.getInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.NONE);
        } else {
            view.getInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.BLOCK);
        }
    }

    @Override
    public void updateSimpleJavaInstanceFieldListSeparator() {
        if (view.getSimpleJavaInstanceListContainer().getChildCount() < 1 || GridWidget.BACKGROUND.equals(gridWidget)) {
            view.getSimpleJavaInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.NONE);
        } else {
            view.getSimpleJavaInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.BLOCK);
        }
    }

    @Override
    public Optional<FactModelTree> getFactModelTreeFromFactTypeMap(String factName) {
        return Optional.ofNullable(dataObjectFieldsMap.get(factName));
    }

    @Override
    public Optional<FactModelTree> getFactModelTreeFromSimpleJavaTypeMap(String factName) {
        return Optional.ofNullable(simpleJavaTypeFieldsMap.get(factName));
    }

    @Override
    public Optional<FactModelTree> getFactModelTreeFromInstanceMap(String factName) {
        return Optional.ofNullable(instanceFieldsMap.get(factName));
    }

    @Override
    public Optional<FactModelTree> getFactModelTreeFromSimpleJavaInstanceMap(String factName) {
        return Optional.ofNullable(simpleJavaInstanceFieldsMap.get(factName));
    }

    @Override
    public FactModelTree getFactModelTreeFromHiddenMap(String factName) {
        return hiddenFieldsMap.get(factName);
    }

    @Override
    public void setDataObjectFieldsMap(SortedMap<String, FactModelTree> dataObjectFieldsMap) {
        clearDataObjectList();
        this.dataObjectFieldsMap = dataObjectFieldsMap;
        this.dataObjectFieldsMap.forEach(this::addDataObjectListGroupItemView);
        updateDataObjectListSeparator();
    }

    @Override
    public void setSimpleJavaTypeFieldsMap(SortedMap<String, FactModelTree> simpleJavaTypeFieldsMap) {
        clearSimpleJavaTypeList();
        this.simpleJavaTypeFieldsMap = simpleJavaTypeFieldsMap;
        this.simpleJavaTypeFieldsMap.forEach(this::addSimpleJavaTypeListGroupItemView);
        updateSimpleJavaTypeListSeparator();
    }

    @Override
    public void setInstanceFieldsMap(SortedMap<String, FactModelTree> instanceFieldsMap) {
        clearInstanceList();
        this.instanceFieldsMap = instanceFieldsMap;
        this.instanceFieldsMap.forEach(this::addInstanceListGroupItemView);
        updateInstanceListSeparator();
    }

    @Override
    public void setSimpleJavaInstanceFieldsMap(SortedMap<String, FactModelTree> simpleJavaInstanceFieldsMap) {
        clearSimpleJavaInstanceFieldList();
        this.simpleJavaInstanceFieldsMap = simpleJavaInstanceFieldsMap;
        this.simpleJavaInstanceFieldsMap.forEach(this::addSimpleJavaInstanceListGroupItemView);
        updateSimpleJavaInstanceFieldListSeparator();
    }

    @Override
    public void setHiddenFieldsMap(SortedMap<String, FactModelTree> hiddenFieldsMap) {
        this.hiddenFieldsMap = hiddenFieldsMap;
    }

    @Override
    public void hideProperties(Map<String, List<List<String>>> propertiesToHide) {
        listGroupItemPresenter.showAll();
        propertiesToHide.entrySet().stream().forEach(
                stringListEntry -> stringListEntry.getValue()
                        .forEach(propertyParts ->
                                         listGroupItemPresenter.hideProperty(stringListEntry.getKey(), propertyParts))
        );
    }

    @Override
    public void onShowClearButton() {
        view.showClearButton();
    }

    @Override
    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void setGridWidget(GridWidget gridWidget) {
        this.gridWidget = gridWidget;
        switch (gridWidget) {
            case BACKGROUND:
                hideInstances();
                break;
            case SIMULATION:
                showInstances();
                break;
            default:
                throw new IllegalArgumentException("Illegal GridWidget " + gridWidget);
        }
    }

    @Override
    public void onSearchedEvent(String search) {
        clearLists();
        dataObjectFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().toLowerCase().contains(search.toLowerCase()))
                .forEach(filteredEntry -> addDataObjectListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        simpleJavaTypeFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().toLowerCase().contains(search.toLowerCase()))
                .forEach(filteredEntry -> addSimpleJavaTypeListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        instanceFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().toLowerCase().contains(search.toLowerCase()))
                .forEach(filteredEntry -> addInstanceListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        simpleJavaInstanceFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().toLowerCase().contains(search.toLowerCase()))
                .forEach(filteredEntry -> addSimpleJavaInstanceListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        updateSeparators();
    }

    @Override
    public void onPerfectMatchSearchedEvent(String search, boolean notEqualsSearch) {
        clearLists();
        dataObjectFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> filterTerm(entry.getKey(), search, notEqualsSearch))
                .forEach(filteredEntry -> addDataObjectListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        simpleJavaTypeFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> filterTerm(entry.getKey(), search, notEqualsSearch))
                .forEach(filteredEntry -> addSimpleJavaTypeListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        instanceFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> filterTerm(entry.getKey(), search, notEqualsSearch))
                .forEach(filteredEntry -> addInstanceListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        simpleJavaInstanceFieldsMap
                .entrySet()
                .stream()
                .filter(entry -> filterTerm(entry.getKey(), search, notEqualsSearch))
                .forEach(filteredEntry -> addSimpleJavaInstanceListGroupItemView(filteredEntry.getKey(), filteredEntry.getValue()));
        updateSeparators();
    }

    @Override
    public void addDataObjectListGroupItemView(String factName, FactModelTree factModelTree) {
        DivElement toAdd = listGroupItemPresenter.getDivElement(factName, factModelTree);
        view.getDataObjectListContainer().appendChild(toAdd);
    }

    @Override
    public void addSimpleJavaTypeListGroupItemView(String factName, FactModelTree factModelTree) {
        DivElement toAdd = listGroupItemPresenter.getDivElement(factName, factModelTree);
        view.getSimpleJavaTypeListContainer().appendChild(toAdd);
    }

    @Override
    public void addInstanceListGroupItemView(String instanceName, FactModelTree factModelTree) {
        DivElement toAdd = listGroupItemPresenter.getDivElement(instanceName, factModelTree);
        view.getInstanceListContainer().appendChild(toAdd);
    }

    @Override
    public void addSimpleJavaInstanceListGroupItemView(String instanceName, FactModelTree factModelTree) {
        DivElement toAdd = listGroupItemPresenter.getDivElement(instanceName, factModelTree);
        view.getSimpleJavaInstanceListContainer().appendChild(toAdd);
    }

    @Override
    public void onEnableEditorTab() {
        onDisableEditorTab();
        listGroupItemPresenter.enable();
        editingColumnEnabled = true;
        view.enableEditorTab();
        view.enableSearch();
    }

    @Override
    public void onEnableEditorTab(String filterTerm, List<String> propertyNameElements, boolean notEqualsSearch) {
        onDisableEditorTab();
        onPerfectMatchSearchedEvent(filterTerm, notEqualsSearch);
        listGroupItemPresenter.enable(filterTerm);
        editingColumnEnabled = true;
        view.enableEditorTab();
        /* If notEqualsSearch is TRUE, then the instance is not assigned for the selected column.
         * Therefore, it isn't necessary to search through the maps to check it. In that case, the search is activated.
         */
        if (!notEqualsSearch) {
            updateInstanceIsAssignedStatus(filterTerm);
        } else {
            view.enableSearch();
        }
        if (propertyNameElements != null && !notEqualsSearch) {
            listGroupItemPresenter.selectProperty(filterTerm, propertyNameElements);
        }
    }

    @Override
    public void onDisableEditorTab() {
        onSearchedEvent("");
        listGroupItemPresenter.disable();
        editingColumnEnabled = false;
        view.disableEditorTab();
        selectedFieldItemView = null;
        selectedListGroupItemView = null;
    }

    @Override
    public void setSelectedElement(ListGroupItemView selected) {
        selectedListGroupItemView = selected;
        selectedFieldItemView = null;
        if (filterTerm(selected.getFactName(), listGroupItemPresenter.getFilterTerm(), selected.isInstanceAssigned())) {
            view.disableAddButton();
        } else {
            view.enableAddButton();
        }
    }

    @Override
    public void setSelectedElement(FieldItemView selected) {
        selectedFieldItemView = selected;
        selectedListGroupItemView = null;
        String factName = selectedFieldItemView.getFullPath().split("\\.")[0];
        boolean isFactNameAssigned = listGroupItemPresenter.isInstanceAssigned(factName);
        if (filterTerm(factName, listGroupItemPresenter.getFilterTerm(), isFactNameAssigned)) {
            view.disableAddButton();
        } else {
            view.enableAddButton();
        }
    }

    @Override
    public void onModifyColumn() {
        if (editingColumnEnabled) {
            if (selectedListGroupItemView != null) {
                String className = selectedListGroupItemView.getActualClassName();
                getFullPackage(className).ifPresent(fullPackage -> eventBus.fireEvent(
                            new SetPropertyHeaderEvent(gridWidget,
                                                       fullPackage,
                                                       Arrays.asList(className),
                                                       fullPackage + "." + className,
                                                       FactMappingValueType.EXPRESSION)));
            } else if (selectedFieldItemView != null) {
                String baseClass = selectedFieldItemView.getFullPath().split("\\.")[0];
                String value = isSimple(baseClass) ?
                        selectedFieldItemView.getFullPath() :
                        selectedFieldItemView.getFullPath() + "." + selectedFieldItemView.getFieldName();
                List<String> propertyNameElements = Collections.unmodifiableList(Arrays.asList(value.split("\\.")));
                getFullPackage(baseClass).ifPresent(fullPackage -> eventBus.fireEvent(new SetPropertyHeaderEvent(gridWidget, fullPackage,
                                                                                                                 propertyNameElements,
                                                                                                                 selectedFieldItemView.getClassName(),
                                                                                                                 FactMappingValueType.NOT_EXPRESSION)));
            }
        }
    }

    @Override
    public void reset() {
        listGroupItemPresenter.reset();
        view.reset();
    }

    /**
     * Method to hide all the <b>instance-related</b> html
     */
    protected void hideInstances() {
        view.getInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.NONE);
        view.getSimpleJavaInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.NONE);
        view.getInstanceListContainer().getStyle().setDisplay(Style.Display.NONE);
        view.getSimpleJavaInstanceListContainer().getStyle().setDisplay(Style.Display.NONE);
    }

    /**
     * Method to show all the <b>instance-related</b> html
     */
    protected void showInstances() {
        view.getInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.BLOCK);
        view.getSimpleJavaInstanceListContainerSeparator().getStyle().setDisplay(Style.Display.BLOCK);
        view.getInstanceListContainer().getStyle().setDisplay(Style.Display.BLOCK);
        view.getSimpleJavaInstanceListContainer().getStyle().setDisplay(Style.Display.BLOCK);
    }

    /**
     * It navigates through the maps, to check if the given key is present or not in the keySet of these maps.
     * If present, then a INSTANCE is already assigned to the selected column. Then, it assigns the search result to
     * its related view.
     * @param key
     */
    protected void updateInstanceIsAssignedStatus(String key) {
        if (key != null && !key.isEmpty()) {
            boolean assigned = dataObjectFieldsMap.keySet().contains(key) ||
                    simpleJavaTypeFieldsMap.keySet().contains(key) ||
                    instanceFieldsMap.keySet().contains(key) ||
                    simpleJavaInstanceFieldsMap.keySet().contains(key);
            listGroupItemPresenter.setInstanceAssigned(key, assigned);
        }
    }

    protected Optional<String> getFullPackage(String className) {
        return getFactModelTreeFromMaps(className).map(FactModelTree::getFullPackage);
    }

    protected Optional<FactModelTree> getFactModelTreeFromMaps(String key) {
        return Optional.ofNullable(getFactModelTreeFromFactTypeMap(key)
                                           .orElseGet(() -> getFactModelTreeFromSimpleJavaTypeMap(key)
                                                   .orElseGet(() -> getFactModelTreeFromInstanceMap(key)
                                                           .orElseGet(() -> getFactModelTreeFromSimpleJavaInstanceMap(key).orElse(null)))));
    }

    protected boolean isSimple(String key) {
        return Optional.ofNullable(getFactModelTreeFromSimpleJavaTypeMap(key))
                .orElseGet(() -> getFactModelTreeFromSimpleJavaInstanceMap(key))
                .isPresent();
    }

    protected void clearLists() {
        clearDataObjectList();
        clearSimpleJavaTypeList();
        clearInstanceList();
        clearSimpleJavaInstanceFieldList();
    }

    protected void updateSeparators() {
        updateDataObjectListSeparator();
        updateSimpleJavaTypeListSeparator();
        updateInstanceListSeparator();
        updateSimpleJavaInstanceFieldListSeparator();
    }

    /**
     * It determines if a key (factTitle) is present or not in the search variable.
     * @param key It's the title of the fact to search
     * @param search It represents a concatenation of titles, with ";" as separator
     * @param notEqualsSearch It establishes the method logic: to check if a key is present or not in search string
     * @return
     */
    protected boolean filterTerm(String key, String search, boolean notEqualsSearch) {
        List<String> terms = Arrays.asList(search.split(";"));
        if (notEqualsSearch) {
            return !terms.contains(key);
        } else {
            return terms.contains(key);
        }
    }
}
