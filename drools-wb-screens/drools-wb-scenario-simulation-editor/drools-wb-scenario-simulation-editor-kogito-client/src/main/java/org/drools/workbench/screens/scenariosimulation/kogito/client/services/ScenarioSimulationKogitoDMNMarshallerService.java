/*
 * Copyright 2020 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.scenariosimulation.kogito.client.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

import jsinterop.base.Js;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;
import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.MainJs;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.callbacks.DMN12UnmarshallCallback;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITDefinitions;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITItemDefinition;
import org.kie.workbench.common.dmn.webapp.kogito.marshaller.js.model.dmn12.JSITNamedElement;
import org.uberfire.backend.vfs.Path;
import org.uberfire.backend.vfs.PathFactory;
import org.uberfire.client.callbacks.Callback;
import org.uberfire.ext.widgets.common.client.common.popups.errors.ErrorPopup;

public class ScenarioSimulationKogitoDMNMarshallerService {

    @Inject
    private ScenarioSimulationKogitoResourceContentService resourceContentService;

    public void retrieveDMNData(final Path dmnFilePath,
                                final Callback<JSITDefinitions> callback) {
        resourceContentService.getFileContent(dmnFilePath,
                                              dmnContent -> {
                                                  DMN12UnmarshallCallback dmn12UnmarshallCallback =
                                                          getDMN12UnmarshallCallback(callback,dmnFilePath);
                                                  MainJs.unmarshall(dmnContent, "", dmn12UnmarshallCallback);
                                              },
                                              getErrorCallback());
    }

    private DMN12UnmarshallCallback getDMN12UnmarshallCallback(final Callback<JSITDefinitions> callback,
                                                               final Path dmnFilePath) {
        return dmn12 -> {
            final JSITDefinitions jsitDefinitions = Js.uncheckedCast(JsUtils.getUnwrappedElement(dmn12));
            if (jsitDefinitions.getImport() != null && !jsitDefinitions.getImport().isEmpty()) {
                final Map<String, Path> includedDMNImportsPaths = jsitDefinitions.getImport().stream()
                        .filter(jsitImport -> jsitImport.getLocationURI().endsWith(".dmn"))
                        .collect(Collectors.toMap(JSITNamedElement::getName,
                                                  jsitImport -> PathFactory.newPath(jsitImport.getLocationURI(),
                                                                                    dmnFilePath.toURI().replace(dmnFilePath.getFileName(),
                                                                                                                jsitImport.getLocationURI()))));

                for (Map.Entry<String, Path> importPath : includedDMNImportsPaths.entrySet()) {
                    final Map<String, JSITDefinitions> importedItemDefinitions = new HashMap<>();
                    resourceContentService.getFileContent(importPath.getValue(),
                                                          getDMNImportContentRemoteCallback(callback,
                                                                                            importPath.getKey(),
                                                                                            jsitDefinitions,
                                                                                            importedItemDefinitions,
                                                                                            includedDMNImportsPaths.size()),
                                                          getErrorCallback());
                }
            }
        };
    }

    private RemoteCallback<String> getDMNImportContentRemoteCallback(final Callback<JSITDefinitions> callback,
                                                                              final String importName,
                                                                              final JSITDefinitions definitions,
                                                                              final Map<String, JSITDefinitions> importedDefinitions,
                                                                              final int importsNumber) {
        return dmnContent -> {
            DMN12UnmarshallCallback dmn12UnmarshallCallback = getDMN12ImportsUnmarshallCallback(callback,
                                                                                                importName,
                                                                                                definitions,
                                                                                                importedDefinitions,
                                                                                                importsNumber);
            MainJs.unmarshall(dmnContent, "", dmn12UnmarshallCallback);
        };
    }

    private DMN12UnmarshallCallback getDMN12ImportsUnmarshallCallback(final Callback<JSITDefinitions> callback,
                                                                        final String importName,
                                                                        final JSITDefinitions definitions,
                                                                        final Map<String, JSITDefinitions> importedDefinitions,
                                                                        final int importsNumber) {
        return dmn12 -> {
            final JSITDefinitions jsitDefinitions = Js.uncheckedCast(JsUtils.getUnwrappedElement(dmn12));
            importedDefinitions.put(importName, jsitDefinitions);

            if (importsNumber == importedDefinitions.size()) {
                List<JSITItemDefinition> itemDefinitions = new ArrayList<>();

                importedDefinitions.entrySet().stream().forEach(entry -> {
                    final JSITDefinitions def = Js.uncheckedCast(entry.getValue());
                    List<JSITItemDefinition> itemDefinitionsRaw = def.getItemDefinition();
                    String prefix = entry.getKey();

                    for (int i = 0; i < itemDefinitionsRaw.size(); i++) {
                        JSITItemDefinition value = Js.uncheckedCast(itemDefinitionsRaw.get(i));
                        value.setName(prefix + "." + value.getName());
                        itemDefinitions.add(value);
                    }
                });

                definitions.addAllItemDefinition(itemDefinitions.toArray(new JSITItemDefinition[itemDefinitions.size()]));
                callback.callback(definitions);
            }
        };
    }

    private ErrorCallback<String> getErrorCallback() {
        return (error, exception) -> {
            ErrorPopup.showMessage(exception.getMessage());
            return false;
        };
    }

}
