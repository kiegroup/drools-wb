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
import org.drools.scenariosimulation.api.model.FactMappingType;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.ExpressionIdentifierAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ExpressionIdentifierKM;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.FactMappingTypeKM;

public class ExpressionIdentifierConverter extends AbstractAutoBeanConverter<ExpressionIdentifierKM, ExpressionIdentifierAutobeanFactory, ExpressionIdentifier> {

    public ExpressionIdentifierConverter() {
        factory = GWT.create(ExpressionIdentifierAutobeanFactory.class);
        clazz = ExpressionIdentifierKM.class;
    }

    @Override
    public ExpressionIdentifier toApiModel(ExpressionIdentifierKM kogitoModel) {
        return new ExpressionIdentifier(kogitoModel.getName(), FactMappingType.valueOf(kogitoModel.getType().name()));
    }

    @Override
    public ExpressionIdentifierKM toKogitoModel(ExpressionIdentifier apiModel) {
        ExpressionIdentifierKM toReturn = makeKogitoModel();
        toReturn.setName(apiModel.getName());
        toReturn.setType(FactMappingTypeKM.valueOf(apiModel.getType().name()));
        return toReturn;
    }
}
