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
import org.drools.scenariosimulation.api.model.ExpressionElement;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.ExpressionElementAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.ExpressionElementKM;

public class ExpressionElementConverter extends AbstractAutoBeanConverter<ExpressionElementKM, ExpressionElementAutobeanFactory, ExpressionElement> {

    public ExpressionElementConverter() {
        factory = GWT.create(ExpressionElementAutobeanFactory.class);
        clazz = ExpressionElementKM.class;
    }

    @Override
    public ExpressionElement toApiModel(ExpressionElementKM kogitoModel) {
        return new ExpressionElement(kogitoModel.getStep());
    }

    @Override
    public ExpressionElementKM toKogitoModel(ExpressionElement apiModel) {
        ExpressionElementKM toReturn = makeKogitoModel();
        toReturn.setStep(apiModel.getStep());
        return toReturn;
    }
}
