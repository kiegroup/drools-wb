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

package org.drools.workbench.screens.scenariosimulation.service;

import java.util.List;

import org.jboss.errai.bus.server.annotations.Remote;
import org.uberfire.java.nio.file.FileSystem;

/**
 * Assets specific service
 */
@Remote
public interface AssetsService {

    /**
     * This method <b>tries</b> to avoid importing specific <b>backend</b> implementations where they should not be needed nor available
     * (e.g. avoid using uberfire vfs stuff inside submarine environment)
     * @param fileSystem
     * @param containerDirectoryFullPath
     * @param assetType
     * @param packageName
     * @return
     * @throws Exception
     */
    List<String> getAssets(final FileSystem fileSystem,
                           final String containerDirectoryFullPath,
                           String assetType, String packageName) throws Exception;
}
