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
package org.drools.workbench.screens.scenariosimulation.client.factories;

import java.util.function.Supplier;

import javax.enterprise.context.Dependent;

import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioMenuItem;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationEditorPresenter;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.uberfire.backend.vfs.Path;
import org.uberfire.workbench.model.menu.MenuItem;

@Dependent
public class ScenarioMenuItemFactory {

    private ScenarioMenuItem runMenuItem;
    private ScenarioMenuItem undoMenuItem;
    private ScenarioMenuItem redoMenuItem;
    private ScenarioMenuItem downloadMenuItem;
    private ScenarioMenuItem importMenuItem;
    private ScenarioMenuItem exportToCsvMenuItem;
    private ScenarioSimulationEditorPresenter presenter;

    void init(final ScenarioSimulationEditorPresenter presenter) {
        this.presenter = presenter;
    }

    public MenuItem getRunScenarioMenuItem() {
        if (runMenuItem == null) {
            runMenuItem = new ScenarioMenuItem(IconType.PLAY, () -> presenter.onRunScenario());
        }
        return runMenuItem;
    }

    public MenuItem getUndoMenuItem() {
        if (undoMenuItem == null) {
            undoMenuItem = new ScenarioMenuItem(IconType.UNDO,  () -> presenter.onUndo());
        }
        return undoMenuItem;
    }

    public MenuItem getRedoMenuItem() {
        if (redoMenuItem == null) {
            redoMenuItem = new ScenarioMenuItem(IconType.REPEAT, () -> presenter.onRedo());
        }
        return redoMenuItem;
    }

    public MenuItem getDownloadMenuItem(final Supplier<Path> pathSupplier) {
        if (downloadMenuItem == null) {
            downloadMenuItem = new ScenarioMenuItem(IconType.DOWNLOAD, () -> presenter.onDownload(pathSupplier));
        }
        return downloadMenuItem;
    }

    public MenuItem getExportToCsvMenuItem() {
        if (exportToCsvMenuItem == null) {
            exportToCsvMenuItem = new ScenarioMenuItem("Export", () -> presenter.onExportToCsv());
        }
        return exportToCsvMenuItem;
    }

    public MenuItem getImportMenuItem() {
        if (importMenuItem == null) {
            importMenuItem = new ScenarioMenuItem(ScenarioSimulationEditorConstants.INSTANCE.importLabel(),
                                                  () -> presenter.showImportDialog());
        }
        return importMenuItem;
    }
}
