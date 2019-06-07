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

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.factories.KogitoAutobeanFactory;
import org.drools.workbench.screens.scenariosimulation.kogito.client.autobean.model.KogitoModel;

public abstract class AbstractAutoBeanConverter<T extends KogitoModel, S extends KogitoAutobeanFactory<T>, V> {

    protected S factory;
    protected Class<T> clazz;

    public abstract V toApiModel(T kogitoModel);
    public abstract T toKogitoModel(V apiModel);

    public T makeKogitoModel() {
        // Construct the AutoBean
        AutoBean<T> toReturn = factory.getKogitoModel();
        // Return the T interface shim
        return toReturn.as();
    }

    public String serializeToJson(T kogitoModel) {
        // Retrieve the AutoBean controller
        AutoBean<T> bean = AutoBeanUtils.getAutoBean(kogitoModel);

        return AutoBeanCodex.encode(bean).getPayload();
    }

    public T deserializeFromJson(String json) {
        AutoBean<T> bean = AutoBeanCodex.decode(factory, clazz, json);
        return bean.as();
    }

}
