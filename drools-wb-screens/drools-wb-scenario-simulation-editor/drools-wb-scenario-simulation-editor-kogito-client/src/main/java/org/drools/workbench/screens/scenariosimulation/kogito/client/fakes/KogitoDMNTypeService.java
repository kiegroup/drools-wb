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
import org.drools.workbench.screens.scenariosimulation.service.DMNTypeService;
import org.jboss.errai.bus.server.annotations.Remote;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.uberfire.backend.vfs.Path;

/**
 * Interface required because <b>runtime</b> and <b>testing</b> environments would
 * need/provide different implementations
 */
@Remote
public interface KogitoDMNTypeService extends DMNTypeService {


    /**
     * Retrieves a <code>FactModelTuple</code> representing the given <b>dmn</b> file
     * @param path
     * @param dmnPath
     * @return
     * @throws Exception
     */
    void retrieveFactModelTuple(Path path, String dmnPath, RemoteCallback<FactModelTuple> callback);

}
