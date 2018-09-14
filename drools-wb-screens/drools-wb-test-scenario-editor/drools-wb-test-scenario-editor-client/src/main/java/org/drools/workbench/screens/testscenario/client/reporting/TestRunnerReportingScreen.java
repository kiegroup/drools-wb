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

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.screens.testscenario.client.service.TestRuntimeReportingService;
import org.guvnor.common.services.shared.message.Level;
import org.guvnor.common.services.shared.test.Failure;
import org.guvnor.common.services.shared.test.TestResultMessage;
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
            view.showSuccess();
        } else {
            if (testResultMessage.getFailures() != null) {
                List<SystemMessage> systemMessages = testResultMessage.getFailures().stream().map(this::convert).collect(Collectors.toList());
                view.setSystemMessages(systemMessages);
            }
            view.showFailure();
        }
        view.setRunStatus(getCompletedAt(), getScenariosRun(testResultMessage), getDuration(testResultMessage));
    }

    private SystemMessage convert(Failure failure) {
        SystemMessage systemMessage = new SystemMessage();
        systemMessage.setMessageType("TestResults");
        systemMessage.setLevel(Level.ERROR);
        systemMessage.setText(makeMessage(failure));
        return systemMessage;
    }

    private String getCompletedAt(){
        DateTimeFormat timeFormat = DateTimeFormat.getFormat("HH:mm:ss.SSS");
        return timeFormat.format(new Date());
    }

    private String getScenariosRun(TestResultMessage testResultMessage){
        return String.valueOf(testResultMessage.getRunCount());
    }

    private String getDuration(TestResultMessage testResultMessage){
        Long runTime = testResultMessage.getRunTime();
        Date runtime = new Date(runTime);

        DateTimeFormat secondsFormat = DateTimeFormat.getFormat("s");
        DateTimeFormat minutesFormat = DateTimeFormat.getFormat("m");

        String seconds = secondsFormat.format(runtime) + " seconds";

        if (runTime >= 60000) {
            return minutesFormat.format(runtime) + " minutes and " + seconds;
        } else if (runTime >= 1000) {
            DateTimeFormat miliSecondsFormat = DateTimeFormat.getFormat("SSS");
            return seconds + " and " + miliSecondsFormat.format(runtime)+ " milliseconds";
        } else {
            return runTime + " milliseconds";
        }
    }

    private String makeMessage(Failure failure) {
        final String displayName = failure.getDisplayName();
        final String message = failure.getMessage();
        return displayName + (!(message == null || message.isEmpty()) ? " : " + message : "");
    }
}
