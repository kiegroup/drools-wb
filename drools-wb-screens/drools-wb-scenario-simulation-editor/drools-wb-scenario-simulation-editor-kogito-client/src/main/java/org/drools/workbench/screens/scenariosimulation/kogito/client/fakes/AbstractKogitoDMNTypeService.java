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
package org.drools.workbench.screens.scenariosimulation.kogito.client.fakes;

import org.drools.workbench.screens.scenariosimulation.model.typedescriptor.FactModelTuple;

/**
 * <b>Abstract</b> class to provide method shared by actual implementations
 */
public abstract class AbstractKogitoDMNTypeService implements KogitoDMNTypeService {

    /**
     * Returns a <code>FactModelTuple</code> from the given <b>dmnContent</b>
     * @param dmnContent
     * @return
     */
    protected FactModelTuple getFactModelTuple(String dmnContent) {
//        // TODO {gcardosi} implement
//        AtomicReference<DMN12> dmn12AtomicReference = new AtomicReference<>();
//        unmarshall(dmnContent, dmn12AtomicReference);
//        Future<DMN12> dmn12Future = new Future<DMN12>() {
//            @Override
//            public boolean cancel(boolean mayInterruptIfRunning) {
//                return false;
//            }
//
//            @Override
//            public boolean isCancelled() {
//                return false;
//            }
//
//            @Override
//            public boolean isDone() {
//                return false;
//            }
//
//            @Override
//            public DMN12 get() throws InterruptedException, ExecutionException {
//                return null;
//            }
//
//            @Override
//            public DMN12 get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
//                return null;
//            }
//        }

        return new FactModelTuple();
    }

//    protected void unmarshall(String dmnContent, AtomicReference<DMN12> dmn12AtomicReference) {
//        MainJs.unmarshall(dmnContent, "", new DMN12UnmarshallCallback() {
//            @Override
//            public void callEvent(DMN12 dmn12) {
//                dmn12AtomicReference.set(dmn12);
//            }
//        });
//    }

//    protected Supplier<DMN12> getFactModelTupleSupplier() {
//        return new Supplier<DMN12>() {
//            @Override
//            public DMN12 get() {
//                return null;
//            }
//        };
//    }

}
