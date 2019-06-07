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

import com.google.gwt.core.client.GWT;
import org.drools.scenariosimulation.api.model.ExpressionIdentifier;
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.scenariosimulation.api.model.FactMappingValue;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.FactMappingValueAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ExpressionIdentifierKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.FactIdentifierKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.FactMappingValueKM;

public class FactMappingValueConverter extends AbstractAutoBeanConverter<FactMappingValueKM, FactMappingValueAutobeanFactory, FactMappingValue> {

    protected FactIdentifierConverter factIdentifierConverter = new FactIdentifierConverter();
    protected ExpressionIdentifierConverter expressionIdentifierConverter = new ExpressionIdentifierConverter();

    public FactMappingValueConverter() {
        factory = GWT.create(FactMappingValueAutobeanFactory.class);
        clazz = FactMappingValueKM.class;
    }

    @Override
    public FactMappingValue toApiModel(FactMappingValueKM kogitoModel) {
        FactIdentifier factIdentifier = factIdentifierConverter.toApiModel(kogitoModel.getFactIdentifier());
        ExpressionIdentifier expressionIdentifier = expressionIdentifierConverter.toApiModel(kogitoModel.getExpressionIdentifier());
        return new FactMappingValue(factIdentifier, expressionIdentifier, kogitoModel.getRawValue());
    }

    @Override
    public FactMappingValueKM toKogitoModel(FactMappingValue apiModel) {
        FactIdentifierKM factIdentifierKM = factIdentifierConverter.toKogitoModel(apiModel.getFactIdentifier());
        ExpressionIdentifierKM expressionIdentifierKM = expressionIdentifierConverter.toKogitoModel(apiModel.getExpressionIdentifier());
        FactMappingValueKM toReturn = makeKogitoModel();
        toReturn.setExpressionIdentifier(expressionIdentifierKM);
        toReturn.setFactIdentifier(factIdentifierKM);
        toReturn.setRawValue(apiModel.getRawValue());
        return toReturn;
    }
}
