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
package org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.converters;

import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.core.client.GWT;
import org.drools.scenariosimulation.api.model.ExpressionElement;
import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.FactMappingAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ExpressionElementKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ExpressionIdentifierKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.FactIdentifierKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.FactMappingKM;

public class FactMappingConverter extends AbstractAutoBeanConverter<FactMappingKM, FactMappingAutobeanFactory, FactMapping> {

    protected FactIdentifierConverter factIdentifierConverter = new FactIdentifierConverter();
    protected ExpressionIdentifierConverter expressionIdentifierConverter = new ExpressionIdentifierConverter();
    protected ExpressionElementConverter expressionElementConverter = new ExpressionElementConverter();

    public FactMappingConverter() {
        factory = GWT.create(FactMappingAutobeanFactory.class);
        clazz = FactMappingKM.class;
    }

    @Override
    public FactMapping toApiModel(FactMappingKM kogitoModel) {
        FactIdentifier factIdentifier = factIdentifierConverter.toApiModel(kogitoModel.getFactIdentifier());
        ExpressionIdentifier expressionIdentifier = expressionIdentifierConverter.toApiModel(kogitoModel.getExpressionIdentifier());
        List<ExpressionElement> expressionElements = kogitoModel.getExpressionElements().stream()
                .map(km -> expressionElementConverter.toApiModel(km))
                .collect(Collectors.toList());
        FactMapping toReturn = new FactMapping(kogitoModel.getFactAlias(), factIdentifier, expressionIdentifier);
        toReturn.getExpressionElements().addAll(expressionElements);
        toReturn.setGenericTypes(kogitoModel.getGenericTypes());
        toReturn.setExpressionAlias(kogitoModel.getExpressionAlias());
        return toReturn;
    }

    @Override
    public FactMappingKM toKogitoModel(FactMapping apiModel) {
        FactIdentifierKM factIdentifierKM = factIdentifierConverter.toKogitoModel(apiModel.getFactIdentifier());
        ExpressionIdentifierKM expressionIdentifierKM = expressionIdentifierConverter.toKogitoModel(apiModel.getExpressionIdentifier());
        List<ExpressionElementKM> expressionElementKMs = apiModel.getExpressionElements().stream()
                .map(bm -> expressionElementConverter.toKogitoModel(bm))
                .collect(Collectors.toList());
        FactMappingKM toReturn = makeKogitoModel();
        toReturn.setClassName(apiModel.getClassName());
        toReturn.setFactAlias(apiModel.getFactAlias());
        toReturn.setExpressionAlias(apiModel.getExpressionAlias());
        toReturn.setExpressionElements(expressionElementKMs);
        toReturn.setExpressionIdentifier(expressionIdentifierKM);
        toReturn.setFactIdentifier(factIdentifierKM);
        toReturn.setGenericTypes(apiModel.getGenericTypes());
        return toReturn;
    }
}
