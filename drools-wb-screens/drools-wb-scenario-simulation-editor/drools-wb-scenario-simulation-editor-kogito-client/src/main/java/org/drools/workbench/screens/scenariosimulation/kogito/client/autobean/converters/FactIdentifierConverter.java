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
import org.drools.scenariosimulation.api.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.FactIdentifierAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.FactIdentifierKM;

public class FactIdentifierConverter extends AbstractAutoBeanConverter<FactIdentifierKM, FactIdentifierAutobeanFactory, FactIdentifier> {

    public FactIdentifierConverter() {
        factory = GWT.create(FactIdentifierAutobeanFactory.class);
        clazz = FactIdentifierKM.class;
    }

    @Override
    public FactIdentifier toApiModel(FactIdentifierKM kogitoModel) {
        return new FactIdentifier(kogitoModel.getName(), kogitoModel.getClassName());
    }

    @Override
    public FactIdentifierKM toKogitoModel(FactIdentifier apiModel) {
        FactIdentifierKM toReturn = makeKogitoModel();
        toReturn.setClassName(apiModel.getClassName());
        toReturn.setName(apiModel.getName());
        return toReturn;
    }
}
