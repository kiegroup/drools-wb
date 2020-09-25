package org.drools.workbench.screens.scenariosimulation.kogito.client.services;

import org.jboss.errai.common.client.api.ErrorCallback;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.uberfire.backend.vfs.Path;

public interface ScenarioSimulationKogitoResourceContentService {

    void getFileContent(final Path path,
                        final RemoteCallback<String> remoteCallback,
                        final ErrorCallback<String> errorCallback);
}
