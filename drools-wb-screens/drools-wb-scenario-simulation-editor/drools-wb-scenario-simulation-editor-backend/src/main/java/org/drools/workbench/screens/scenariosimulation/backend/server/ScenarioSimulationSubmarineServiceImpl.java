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
package org.drools.workbench.screens.scenariosimulation.backend.server;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.drools.scenariosimulation.api.model.ScenarioSimulationModel;
import org.drools.scenariosimulation.backend.util.ScenarioSimulationXMLPersistence;
import org.drools.workbench.screens.scenariosimulation.service.ScenarioSimulationSubmarineService;
import org.jboss.errai.bus.server.annotations.Service;

/**
 * Submarine specific service
 */
@Service
@ApplicationScoped
public class ScenarioSimulationSubmarineServiceImpl implements ScenarioSimulationSubmarineService {


    @Override
    public String marshal(ScenarioSimulationModel sc) {
        return ScenarioSimulationXMLPersistence.getInstance().marshal(sc);
    }

    @Override
    public ScenarioSimulationModel unmarshal(String rawXml) {
        return ScenarioSimulationXMLPersistence.getInstance().unmarshal(rawXml);
    }

    @Override
    public List<String> getAssets(final String containerDirectoryFullPath, String assetType, String packageName) throws Exception {
        // For the moment being the package name is ignored in this submarine version
        File assetsDirectory = getAssetsDirectory(containerDirectoryFullPath);
        final String[] filteredFiles = assetsDirectory.list((dir, name) -> name.endsWith("." + assetType));
        return filteredFiles != null ? Arrays.asList(filteredFiles) : Collections.emptyList();
    }

    protected File getAssetsDirectory(String containerDirectoryFullPath) throws Exception {
        final URL assets = getClass().getClassLoader().getResource(containerDirectoryFullPath);
        if (assets == null) {
            throw new Exception(containerDirectoryFullPath + " directory not readable");
        }
        File toReturn = new File(assets.getFile());
        if (!toReturn.exists() || !toReturn.canRead() || !toReturn.isDirectory()) {
            throw new Exception(toReturn.getAbsolutePath() + " is not a readable directory");
        }
        return toReturn;
    }
}
