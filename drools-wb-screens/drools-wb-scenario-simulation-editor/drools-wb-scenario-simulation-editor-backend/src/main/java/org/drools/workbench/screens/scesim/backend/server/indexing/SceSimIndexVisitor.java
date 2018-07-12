/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scesim.backend.server.indexing;

import java.util.Map;

import org.kie.soup.commons.validation.PortablePreconditions;
import org.kie.soup.project.datamodel.oracle.ModelField;
import org.kie.soup.project.datamodel.oracle.ModuleDataModelOracle;
import org.kie.workbench.common.services.datamodel.backend.server.builder.util.DataEnumLoader;
import org.kie.workbench.common.services.refactoring.ResourceReference;
import org.kie.workbench.common.services.refactoring.backend.server.impact.ResourceReferenceCollector;
import org.kie.workbench.common.services.refactoring.service.PartType;
import org.kie.workbench.common.services.refactoring.service.ResourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uberfire.java.nio.file.Path;

/**
 * Visitor to extract index information from a DataEnumLoader
 */
public class SceSimIndexVisitor extends ResourceReferenceCollector {

    private static final Logger logger = LoggerFactory.getLogger(SceSimIndexVisitor.class);

    private final ModuleDataModelOracle dmo;
    private final Path resourcePath;
    private final DataEnumLoader enumLoader;

    public SceSimIndexVisitor(final ModuleDataModelOracle dmo,
                              final Path resourcePath,
                              final DataEnumLoader enumLoader) {
        this.dmo = PortablePreconditions.checkNotNull("dmo",
                                                      dmo);
        this.resourcePath = PortablePreconditions.checkNotNull("resourcePath",
                                                               resourcePath);
        this.enumLoader = PortablePreconditions.checkNotNull("enumLoader",
                                                             enumLoader);
    }

    public void visit() {

    }

}
