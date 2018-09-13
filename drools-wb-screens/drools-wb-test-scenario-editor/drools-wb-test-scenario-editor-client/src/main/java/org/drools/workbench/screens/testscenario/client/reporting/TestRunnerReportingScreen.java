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
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.screens.testscenario.client.service.TestRuntimeReportingService;
import org.guvnor.common.services.shared.message.Level;
import org.guvnor.common.services.shared.test.Failure;
import org.guvnor.common.services.shared.test.TestResultMessage;
import org.guvnor.messageconsole.events.PublishBatchMessagesEvent;
import org.guvnor.messageconsole.events.SystemMessage;
import org.uberfire.client.annotations.DefaultPosition;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.workbench.model.CompassPosition;
import org.uberfire.workbench.model.Position;

@ApplicationScoped
@WorkbenchScreen(identifier = "org.kie.guvnor.TestResults", preferredWidth = 437)
public class TestRunnerReportingScreen
        implements TestRunnerReportingView.Presenter {

    private TestRunnerReportingView view;

    @Inject
    private Event<PublishBatchMessagesEvent> publishBatchMessagesEvent;

    public TestRunnerReportingScreen() {
        //Zero argument constructor for CDI
    }

    @Inject
    public TestRunnerReportingScreen(TestRunnerReportingView view,
                                     TestRuntimeReportingService testRuntimeReportingService) {
        this.view = view;
        view.setPresenter(this);
    }

    @DefaultPosition
    public Position getDefaultPosition() {
        return CompassPosition.EAST;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "";
    }

    @WorkbenchPartView
    public Widget asWidget() {
        return view.asWidget();
    }

    public void onTestRun(@Observes TestResultMessage testResultMessage) {
        if (testResultMessage.wasSuccessful()) {
            publishMessages(new ArrayList<>());
            view.showSuccess();
        } else {
            if (testResultMessage.getFailures() != null) {
                publishMessages(testResultMessage.getFailures().stream().map(this::convert).collect(Collectors.toList()));
            }
            view.showFailure();
        }

        view.setRunStatus(testResultMessage.getRunCount(),
                          testResultMessage.getRunTime());
    }

    private void publishMessages(List<SystemMessage> systemMessages) {
        PublishBatchMessagesEvent batchMessages = new PublishBatchMessagesEvent();
        batchMessages.setCleanExisting(true);
        batchMessages.setMessagesToPublish(systemMessages);
        publishBatchMessagesEvent.fire(batchMessages);
    }

    private SystemMessage convert(Failure failure) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setMessageType("TestResults");
        systemMessage.setLevel(Level.ERROR);
        systemMessage.setText(makeMessage(failure));
        return systemMessage;
    }

    private String makeMessage(Failure failure) {
        final String displayName = failure.getDisplayName();
        final String message = failure.getMessage();
        return displayName + (!(message == null || message.isEmpty()) ? " : " + message : "");
    }
}
