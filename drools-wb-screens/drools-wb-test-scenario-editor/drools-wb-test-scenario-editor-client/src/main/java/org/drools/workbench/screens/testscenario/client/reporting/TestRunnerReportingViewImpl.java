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
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Widget;
import elemental2.dom.HTMLAnchorElement;
import elemental2.dom.HTMLDivElement;
import org.drools.workbench.screens.testscenario.client.service.TestRuntimeReportingService;
import org.guvnor.common.services.shared.message.Level;
import org.guvnor.common.services.shared.test.Failure;
import org.guvnor.messageconsole.client.console.MessageConsoleService;
import org.guvnor.messageconsole.client.console.widget.MessageTableWidget;
import org.guvnor.messageconsole.client.console.widget.button.ViewHideAlertsButtonPresenter;
import org.gwtbootstrap3.client.ui.constants.ColumnSize;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
public class TestRunnerReportingViewImpl
        implements TestRunnerReportingView {

    private Presenter presenter;

    @Inject
    private ViewHideAlertsButtonPresenter alertsPresenter;

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

    @Inject
    private MessageConsoleService consoleService;

    protected final MessageTableWidget<Failure> dataGrid = new MessageTableWidget<Failure>() {{
        setToolBarVisible(false);
    }};

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

        addSuccessColumn();
        addTextColumn();

        dataGrid.addStyleName(ColumnSize.MD_12.getCssName());
    }

    @EventHandler("viewAlerts")
    public void onClickEvent(ClickEvent event) {
        alertsPresenter.viewAlerts();
    }

    private void addSuccessColumn() {
        dataGrid.addLevelColumn(10,
                                new MessageTableWidget.ColumnExtractor<Level>() {
                                    @Override
                                    public Level getValue(final Object row) {
                                        presenter.onAddingFailure((Failure) row);
                                        return Level.ERROR;
                                    }
                                });
    }

    private void addTextColumn() {
        dataGrid.addTextColumn(90,
                               new MessageTableWidget.ColumnExtractor<String>() {
                                   @Override
                                   public String getValue(final Object row) {

                                       return makeMessage((Failure) row);
                                   }
                               });
    }

    private String makeMessage(Failure failure) {
        final String displayName = failure.getDisplayName();
        final String message = failure.getMessage();
        return displayName + (!(message == null || message.isEmpty()) ? " : " + message : "");
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void bindDataGridToService(TestRuntimeReportingService testRuntimeReportingService) {
        testRuntimeReportingService.addDataDisplay(dataGrid);
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
    public void setExplanation(String explanation) {
    }

    @Override
    public void setRunStatus(int runCount,
                             long runTime) {
        setCompletionTime();
        setRunCount(runCount);
        setDuration(runTime);
    }

    private void setRunCount(int runCount) {
        scenariosRun.textContent = String.valueOf(runCount);
    }

    private void setCompletionTime() {
        DateTimeFormat timeFormat = DateTimeFormat.getFormat("HH:mm:ss.SSS");
        completedAt.textContent = timeFormat.format(new Date());
    }

    private void setDuration(long runTime) {
        Date runtime = new Date(runTime);

        DateTimeFormat secondsFormat = DateTimeFormat.getFormat("s");
        DateTimeFormat minutesFormat = DateTimeFormat.getFormat("m");

        String seconds = secondsFormat.format(runtime) + " seconds";
        String milliSeconds = runTime + " milliseconds";

        if (runTime >= 60000) {
            duration.textContent = minutesFormat.format(runtime) + " minutes and " + seconds;
        } else if (runTime >= 1000) {
            duration.textContent = seconds + " and " + milliSeconds;
        } else {
            duration.textContent = milliSeconds;
        }
    }

    @Override
    public Widget asWidget() {
        return ElementWrapperWidget.getWidget(resultPanel);
    }
}
