/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.kogito.client.editor.strategies;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import org.drools.workbench.screens.scenariosimulation.client.commands.ScenarioSimulationContext;
import org.drools.workbench.screens.scenariosimulation.client.editor.strategies.AbstractDMODataManagementStrategy;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.TestToolsView;
import org.drools.workbench.screens.scenariosimulation.kogito.client.dmo.KogitoAsyncPackageDataModelOracle;
import org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModelContent;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTree;
import org.kie.soup.project.datamodel.oracle.ModelField;
import org.uberfire.backend.vfs.ObservablePath;
import org.uberfire.client.callbacks.Callback;

public class KogitoDMODataManagementStrategy extends AbstractDMODataManagementStrategy {

    protected KogitoAsyncPackageDataModelOracle kogitoOracle;

    public KogitoDMODataManagementStrategy(KogitoAsyncPackageDataModelOracle kogitoOracle) {
        this.kogitoOracle = kogitoOracle;
    }

    @Override
    public void manageScenarioSimulationModelContent(ObservablePath currentPath, ScenarioSimulationModelContent toManage) {
        model = toManage.getModel();
        kogitoOracle.init(currentPath);
    }

    @Override
    public boolean isADataType(String value) {
        return kogitoOracle != null && Arrays.asList(kogitoOracle.getFactTypes()).contains(value);
    }

    @Override
    protected List<String> getFactTypes() {
        return Arrays.asList(kogitoOracle.getFactTypes());
    }

    @Override
    protected boolean hasEnumValues(String factType, String factField) {
        return false;
    }

    @Override
    protected String[] getEnumValues(String factType, String factField) {
        return new String[0];
    }

    @Override
    protected void getSuperType(String factType, Callback<String> callback) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean skipPopulateTestTools() {
        return kogitoOracle == null || kogitoOracle.getFactTypes().length == 0;
    }

    @Override
    protected void manageDataObjects(List<String> dataObjectsTypes, Map<String, String> superTypeMap, TestToolsView.Presenter testToolsPresenter, int expectedElements, SortedMap<String, FactModelTree> dataObjectsFieldsMap, ScenarioSimulationContext context, List<String> simpleJavaTypes, GridWidget gridWidget) {
        // Iterate over all dataObjects to retrieve their modelfields
        dataObjectsTypes.forEach(factType -> {
            ModelField[] retrieved = kogitoOracle.getFieldCompletions(factType);
            FactModelTree toSend = getFactModelTree(factType, superTypeMap, retrieved);
            aggregatorCallbackMethod(testToolsPresenter, expectedElements, dataObjectsFieldsMap, context, toSend, simpleJavaTypes, gridWidget);
        });
    }

    @Override
    protected String getFQCNByFactName(String factName) {
        return kogitoOracle.getFQCNByFactName(factName);
    }

    @Override
    protected String getParametricFieldType(String factName, String propertyName) {
        return kogitoOracle.getParametricFieldType(factName, propertyName);
    }
}
