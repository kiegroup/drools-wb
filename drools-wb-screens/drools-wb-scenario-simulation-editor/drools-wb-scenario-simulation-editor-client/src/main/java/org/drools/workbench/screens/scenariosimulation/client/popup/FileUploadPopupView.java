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
package org.drools.workbench.screens.scenariosimulation.client.popup;

import javax.enterprise.context.ApplicationScoped;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;
import org.uberfire.mvp.Command;

@ApplicationScoped
@Templated
public class FileUploadPopupView extends AbstractScenarioPopupView implements FileUploadPopup {

    @DataField("file")
    protected InputElement file = Document.get().createHiddenInputElement();

    @DataField("fileText")
    protected InputElement fileText = Document.get().createTextInputElement();

    @DataField("chooseButton")
    protected SpanElement chooseButton = Document.get().createSpanElement();

    @Override
    public void show(Command importCommand) {
        super.show(ScenarioSimulationEditorConstants.INSTANCE.selectImportFile(),
                   ScenarioSimulationEditorConstants.INSTANCE.importLabel(), importCommand);
    }

    @Override
    public String getFileName() {
        return fileText.getValue();
    }

    @EventHandler("chooseButton")
    public void onChooseButtonClickEvent(ClickEvent clickEvent) {
        ((InputElement) file.cast()).click();
    }

    @EventHandler("file")
    public void onFileChangeEvent(ChangeEvent event) {
        fileText.setValue(file.getValue());
    }
}
