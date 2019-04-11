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

package org.drools.workbench.screens.scenariosimulation.client.factories;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.google.gwt.dom.client.DivElement;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.CollectionPresenter;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.CollectionViewImpl;
import org.drools.workbench.screens.scenariosimulation.utils.ScenarioSimulationSharedUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ScenarioSimulationUtils.isSimpleJavaType;
import static org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel.Type.DMN;
import static org.drools.workbench.screens.scenariosimulation.model.ScenarioSimulationModel.Type.RULE;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class CollectionEditorSingletonDOMElementFactoryTest extends AbstractFactoriesTest {

    protected final String STRING_CLASS_NAME = String.class.getCanonicalName();
    protected final String NUMBER_CLASS_NAME = Number.class.getCanonicalName();

    protected CollectionEditorSingletonDOMElementFactory collectionEditorSingletonDOMElementFactoryMock;
    protected CollectionViewImpl collectionEditorViewImpl;

    @Mock
    protected CollectionPresenter collectionPresenterMock;

    @Mock
    protected DivElement collectionEditorModalBodyMock;

    @Before
    public void setup() {
        super.setup();
        this.collectionEditorViewImpl = spy(new CollectionViewImpl() {
            {
                this.presenter = collectionPresenterMock;
                this.collectionEditorModalBody = collectionEditorModalBodyMock;
            }
        });

        this.collectionEditorSingletonDOMElementFactoryMock = spy(new CollectionEditorSingletonDOMElementFactory(scenarioGridPanelMock,
                                                                                                                 scenarioGridLayerMock,
                                                                                                                 scenarioGridMock,
                                                                                                                 scenarioSimulationContextLocal,
                                                                                                                 viewsProviderMock) {
            @Override
            protected void manageList(CollectionViewImpl collectionEditorView, String key, String genericTypeName0) {
                // DO nothing
            }

            @Override
            protected void manageMap(CollectionViewImpl collectionEditorView, String key, String genericTypeName0, String genericTypeName1, boolean isRule) {
                //Do nothing
            }

        });

        factMappingMock.getGenericTypes().add(STRING_CLASS_NAME);
        factMappingMock.getGenericTypes().add(NUMBER_CLASS_NAME);
        when(factMappingMock.getFactAlias()).thenReturn(FULL_CLASS_NAME);
    }

    @Test(expected = IllegalStateException.class)
    public void setCollectionEditorStructureData_EmptyPropertyClass() {
        when(factMappingMock.getExpressionAlias()).thenReturn(MAP_CLASS_NAME);

        setCollectionEditorStructureData();
    }

    @Test
    public void setCollectionEditorStructureData_ManageMapRuleSimpleType() {
        when(factMappingMock.getExpressionAlias()).thenReturn(MAP_CLASS_NAME);
        when(factMappingMock.getClassName()).thenReturn(MAP_CLASS_NAME);
        when(simulationDescriptorMock.getType()).thenReturn(RULE);

        setCollectionEditorStructureData();
    }

    @Test
    public void setCollectionEditorStructureData_ManageMapRule_NotSimpleType() {
        when(factMappingMock.getExpressionAlias()).thenReturn(MAP_CLASS_NAME);
        when(factMappingMock.getClassName()).thenReturn(MAP_CLASS_NAME);
        when(simulationDescriptorMock.getType()).thenReturn(RULE);
        factMappingMock.getGenericTypes().clear();
        factMappingMock.getGenericTypes().add(FULL_CLASS_NAME);
        factMappingMock.getGenericTypes().add(FULL_CLASS_NAME + "1");

        setCollectionEditorStructureData();
    }

    @Test
    public void setCollectionEditorStructureData_ManageMapRule() {
        when(factMappingMock.getExpressionAlias()).thenReturn(MAP_CLASS_NAME);
        when(factMappingMock.getClassName()).thenReturn(MAP_CLASS_NAME);
        when(simulationDescriptorMock.getType()).thenReturn(RULE);

        setCollectionEditorStructureData();
    }

    @Test
    public void setCollectionEditorStructureData_ManageListRule() {
        when(factMappingMock.getExpressionAlias()).thenReturn(LIST_CLASS_NAME);
        when(factMappingMock.getClassName()).thenReturn(LIST_CLASS_NAME);
        when(simulationDescriptorMock.getType()).thenReturn(RULE);

        setCollectionEditorStructureData();
    }

    @Test
    public void setCollectionEditorStructureData_ManageListDMN() {
        when(factMappingMock.getExpressionAlias()).thenReturn(LIST_CLASS_NAME);
        when(factMappingMock.getClassName()).thenReturn(LIST_CLASS_NAME);
        when(simulationDescriptorMock.getType()).thenReturn(DMN);

        setCollectionEditorStructureData();
    }

    private void setCollectionEditorStructureData() {
        collectionEditorSingletonDOMElementFactoryMock.setCollectionEditorStructureData(collectionEditorViewImpl, factMappingMock);
        boolean isRule = RULE.equals(simulationDescriptorMock.getType());
        String genericTypeName0 = factMappingMock.getGenericTypes().get(0);
        String genericTypeName1 = factMappingMock.getGenericTypes().get(1);
        if (isRule && !isSimpleJavaType(genericTypeName0)) {
            verify(collectionEditorSingletonDOMElementFactoryMock, times(1)).getRuleComplexType(eq(genericTypeName0));
            genericTypeName0 = genericTypeName0.substring(genericTypeName0.lastIndexOf(".") + 1);
        }
        String key = factMappingMock.getFactAlias() + "#" + factMappingMock.getExpressionAlias();
        if (ScenarioSimulationSharedUtils.isList(factMappingMock.getExpressionAlias())) {
            verify(collectionEditorSingletonDOMElementFactoryMock, times(1)).manageList(
                    eq(collectionEditorViewImpl), eq(key), eq(genericTypeName0));
        } else {
            verify(collectionEditorSingletonDOMElementFactoryMock, times(1)).manageMap(
                    eq(collectionEditorViewImpl), eq(key), eq(genericTypeName0), eq(genericTypeName1), eq(isRule));
        }
    }
}