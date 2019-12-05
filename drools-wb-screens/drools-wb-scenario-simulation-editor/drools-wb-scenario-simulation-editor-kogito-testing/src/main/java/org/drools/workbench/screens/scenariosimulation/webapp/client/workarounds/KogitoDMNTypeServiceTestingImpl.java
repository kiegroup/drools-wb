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
package org.drools.workbench.screens.scenariosimulation.webapp.client.workarounds;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import elemental2.promise.Promise;
import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.MainJs;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.JSIScenarioSimulationModelType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;
import org.drools.workbench.screens.scenariosimulation.kogito.client.fakes.KogitoDMNTypeService;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.kogito.webapp.base.client.workarounds.TestingVFSService;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.promise.Promises;

import static org.drools.workbench.screens.scenariosimulation.kogito.client.converters.scesim.ApiJSInteropConverter.getJSIScenarioSimulationModelType;

@ApplicationScoped
public class KogitoDMNTypeServiceTestingImpl implements KogitoDMNTypeService {

    @Inject
    private TestingVFSService testingVFSService;
    @Inject
    private Promises promises;

    @Override
    public FactModelTuple retrieveFactModelTuple(Path path, String dmnPath) {
        testingVFSService.loadFile(path, new RemoteCallback<String>() {
            @Override
            public void callback(String response) {
                // FactModelTuple toReturn = getFactModelTuple(response);
            }
        }, new ErrorCallback<Object>() {
            @Override
            public boolean error(Object message, Throwable throwable) {
                return false;
            }
        });
        return null;
    }

    @Override
    public void initializeNameAndNamespace(Settings settings, Path path, String dmnPath) {

    }

    protected Promise<FactModelTuple> transform(final Path path) {
        return promises.create((remoteCallbackFn, errorCallbackFn) -> {
            marshallContent(resource, remoteCallbackFn);
        });
    }

    protected void marshallContent(final Path path, Promise.PromiseExecutorCallbackFn.ResolveCallbackFn<FactModelTuple> resolveCallbackFn) {
        final JSIScenarioSimulationModelType jsiScenarioSimulationModelType = getJSIScenarioSimulationModelType(scenarioSimulationModel);
        JsUtils.setValueOnWrapped(scesimContainer, jsiScenarioSimulationModelType);
        MainJs.marshall(scesimContainer, "scesim", getJSInteropMarshallCallback(resolveCallbackFn));
    }


}
