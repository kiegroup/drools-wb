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

package org.drools.workbench.screens.testscenario.client.reporting;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Widget;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import org.guvnor.messageconsole.events.PublishBatchMessagesEvent;
import org.guvnor.messageconsole.events.SystemMessage;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class TestRunnerReportingViewImpl
        implements TestRunnerReportingView {

    private Presenter presenter;

    @Inject
    private Event<PublishBatchMessagesEvent> publishBatchMessagesEvent;

    @DataField
    private HTMLDivElement resultPanel;

    @DataField
    private HTMLDivElement testResultIcon;

    @DataField
    private HTMLDivElement testResultText;

    @DataField
    private HTMLDivElement scenariosRun;

    @DataField
    private HTMLDivElement completedAt;

    @DataField
    private HTMLDivElement duration;

    @DataField
    private HTMLAnchorElement viewAlerts;

    List<SystemMessage> systemMessages = new ArrayList<>();

    @Inject
    public TestRunnerReportingViewImpl(HTMLDivElement resultPanel,
                                       HTMLDivElement testResultIcon,
                                       HTMLDivElement testResultText,
                                       HTMLDivElement scenariosRun,
                                       HTMLDivElement completedAt,
                                       HTMLDivElement duration,
                                       HTMLAnchorElement viewAlerts) {
        this.resultPanel = resultPanel;
        this.testResultIcon = testResultIcon;
        this.testResultText = testResultText;
        this.scenariosRun = scenariosRun;
        this.completedAt = completedAt;
        this.duration = duration;
        this.viewAlerts = viewAlerts;
    }

    @EventHandler("viewAlerts")
    public void onClickEvent(ClickEvent event) {
        PublishBatchMessagesEvent messagesEvent = new PublishBatchMessagesEvent();
        messagesEvent.setCleanExisting(true);
        messagesEvent.setMessagesToPublish(systemMessages);
        publishBatchMessagesEvent.fire(messagesEvent);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setSystemMessages(List<SystemMessage> systemMessages){
        this.systemMessages = systemMessages;
    }

    @Override
    public void showSuccess() {
        testResultIcon.className = "pficon pficon-ok";
        testResultText.textContent = "PASSED";
    }

    @Override
    public void showFailure() {
        testResultIcon.className = "pficon pficon-error-circle-o";
        testResultText.textContent = "FAILED";
    }

    @Override
    public void setRunStatus(String completedAt,
                             String scenariosRun,
                             String duration) {
        this.completedAt.textContent = completedAt;
        this.scenariosRun.textContent = scenariosRun;
        this.duration.textContent = duration;
    }

    @Override
    public Widget asWidget() {
        return ElementWrapperWidget.getWidget(resultPanel);
    }
}
