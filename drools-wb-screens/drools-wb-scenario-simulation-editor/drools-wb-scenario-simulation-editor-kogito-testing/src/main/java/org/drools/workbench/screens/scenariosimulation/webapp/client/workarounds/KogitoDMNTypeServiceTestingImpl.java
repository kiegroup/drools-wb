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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import elemental2.promise.IThenable;
import elemental2.promise.Promise;
import org.drools.scenariosimulation.api.model.Settings;
import org.drools.workbench.screens.scenariosimulation.kogito.client.fakes.KogitoDMNTypeService;
import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.kogito.webapp.base.client.workarounds.TestingVFSService;
import org.uberfire.backend.vfs.Path;
import org.uberfire.client.promise.Promises;

@ApplicationScoped
public class KogitoDMNTypeServiceTestingImpl implements KogitoDMNTypeService {

    @Inject
    private TestingVFSService testingVFSService;
    @Inject
    private Promises promises;

    @Override
    public FactModelTuple retrieveFactModelTuple(Path path, String dmnPath) {
        Future<FactModelTuple> future = new Future<FactModelTuple>() {
            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public FactModelTuple get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public FactModelTuple get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        }
        final Promise<FactModelTuple> then = retrieveContent(path).then(new IThenable.ThenOnFulfilledCallbackFn<String, FactModelTuple>() {
            @Override
            public IThenable<FactModelTuple> onInvoke(String content) {
                FactModelTuple toReturn = getFactModelTuple(content);
                return promises.resolve(toReturn);
            }
        });
        promises.promisify()
    }

    @Override
    public void initializeNameAndNamespace(Settings settings, Path path, String dmnPath) {

    }

    protected Promise<String> retrieveContent(final Path path) {
        return promises.create((remoteCallbackFn, errorCallbackFn) -> {
            retrieveContent(path, remoteCallbackFn);
        });
    }

    protected void retrieveContent(final Path path, Promise.PromiseExecutorCallbackFn.ResolveCallbackFn<String> resolveCallbackFn) {
        testingVFSService.loadFile(path, response -> resolveCallbackFn.onInvoke(response), (message, throwable) -> false);
    }

    protected FactModelTuple getFactModelTuple(String dmnContent) {
        // TODO {gcardosi} implement
        return new FactModelTuple();
    }


}
