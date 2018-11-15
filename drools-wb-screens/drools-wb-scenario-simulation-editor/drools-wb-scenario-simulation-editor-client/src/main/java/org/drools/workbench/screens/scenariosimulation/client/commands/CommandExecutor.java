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
package org.drools.workbench.screens.scenariosimulation.client.commands;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.event.Event;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DeleteColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DeleteRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DisableRightPanelEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DuplicateRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.EnableRightPanelEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ReloadRightPanelEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioGridReloadEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.ScenarioNotificationEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.SetInstanceHeaderEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.SetPropertyHeaderEvent;
import org.drools.workbench.screens.scenariosimulation.client.handlers.AppendColumnEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.AppendRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.DeleteColumnEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.DeleteRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.DisableRightPanelEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.DuplicateRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.EnableRightPanelEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.InsertColumnEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.InsertRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.PrependColumnEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.PrependRowEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ReloadRightPanelEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioGridReloadEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioNotificationEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.SetInstanceHeaderEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.handlers.SetPropertyHeaderEventHandler;
import org.drools.workbench.screens.scenariosimulation.client.popup.DeletePopupPresenter;
import org.drools.workbench.screens.scenariosimulation.client.popup.PreserveDeletePopupPresenter;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.uberfire.workbench.events.NotificationEvent;

/**
 * This class is meant to be a centralized listener for events fired up by UI, responding to them with specific <code>Command</code>s.
 * <p>
 * It follows the GWT standard Event/Handler mechanism
 */
@Dependent
public class CommandExecutor implements AppendColumnEventHandler,
                                        AppendRowEventHandler,
                                        DeleteColumnEventHandler,
                                        DeleteRowEventHandler,
                                        DisableRightPanelEventHandler,
                                        DuplicateRowEventHandler,
                                        EnableRightPanelEventHandler,
                                        InsertColumnEventHandler,
                                        InsertRowEventHandler,
                                        PrependColumnEventHandler,
                                        PrependRowEventHandler,
                                        ReloadRightPanelEventHandler,
                                        ScenarioGridReloadEventHandler,
                                        ScenarioNotificationEventHandler,
                                        SetInstanceHeaderEventHandler,
                                        SetPropertyHeaderEventHandler {

    protected DeletePopupPresenter deletePopupPresenter;
    protected PreserveDeletePopupPresenter preserveDeletePopupPresenter;

    protected EventBus eventBus;

    protected List<HandlerRegistration> handlerRegistrationList = new ArrayList<>();

    protected Event<NotificationEvent> notificationEvent;

    protected ScenarioSimulationContext context;

    public CommandExecutor() {
        // CDI
    }

    public void setEventBus(EventBus eventBus) {
        this.eventBus = eventBus;
        registerHandlers();
    }

    public void setDeletePopupPresenter(DeletePopupPresenter deletePopupPresenter) {
        this.deletePopupPresenter = deletePopupPresenter;
    }

    public void setPreserveDeletePopupPresenter(PreserveDeletePopupPresenter preserveDeletePopupPresenter) {
        this.preserveDeletePopupPresenter = preserveDeletePopupPresenter;
    }

    public void setNotificationEvent(Event<NotificationEvent> notificationEvent) {
        this.notificationEvent = notificationEvent;
    }

    public void setContext(ScenarioSimulationContext context) {
        this.context = context;
    }

    @PreDestroy
    public void unregisterHandlers() {
        handlerRegistrationList.forEach(HandlerRegistration::removeHandler);
    }

    @Override
    public void onEvent(AppendColumnEvent event) {
        context.setColumnId(String.valueOf(new Date().getTime()));
        context.setColumnGroup(event.getColumnGroup());
        new AppendColumnCommand().execute(context);

//        commonExecute(new AppendColumnCommand(model, String.valueOf(new Date().getTime()), event.getColumnGroup(), context.getScenarioGridPanel(), context.getScenarioGridLayer()));
    }

    @Override
    public void onEvent(AppendRowEvent event) {
        new AppendRowCommand().execute(context);
//        commonExecute(new AppendRowCommand(model));
    }

    @Override
    public void onEvent(DeleteColumnEvent event) {
//        int deletedColumnIndex = event.getColumnIndex();
//        GridColumn<?> selectedColumn = context.getModel().getSelectedColumn();
        context.setColumnIndex(event.getColumnIndex());
        context.setColumnGroup(event.getColumnGroup());
        new DeleteColumnCommand().execute(context);
//
//
//        boolean toDisable = selectedColumn == null || context.getModel().getColumns().indexOf(selectedColumn) == deletedColumnIndex;
//
//
//
////        commonExecute(new DeleteColumnCommand(model, deletedColumnIndex, event.getColumnGroup(), context.getScenarioGridPanel(), context.getScenarioGridLayer()));
//        if (rightPanelPresenter != null && toDisable) {
//            commonExecute(new DisableRightPanelCommand(rightPanelPresenter));
//        }
    }

    @Override
    public void onEvent(DeleteRowEvent event) {
        context.setRowIndex(event.getRowIndex());
        new DeleteRowCommand().execute(context);
//        commonExecute(new DeleteRowCommand(model, event.getRowIndex()));
    }

    @Override
    public void onEvent(DisableRightPanelEvent event) {
        new DisableRightPanelCommand().execute(context);
//        if (rightPanelPresenter != null) {
//            commonExecute(new DisableRightPanelCommand(rightPanelPresenter));
//        }
    }

    @Override
    public void onEvent(DuplicateRowEvent event) {
        context.setRowIndex(event.getRowIndex());
        new DuplicateRowCommand().execute(context);
//        commonExecute(new DuplicateRowCommand(model, event.getRowIndex()));
    }

    @Override
    public void onEvent(EnableRightPanelEvent event) {
        context.setFilterTerm(event.getFilterTerm());
        context.setPropertyName(event.getPropertyName());
        context.setNotEqualsSearch(event.isNotEqualsSearch());
        new EnableRightPanelCommand().execute(context);

//        if (scenarioSimulationEditorPresenter != null) {
//            scenarioSimulationEditorPresenter.expandToolsDock();
//        }
//        commonExecute(new EnableRightPanelCommand(rightPanelPresenter, event.getFilterTerm(), event.getPropertyName(), event.isNotEqualsSearch()));
    }

    @Override
    public void onEvent(InsertColumnEvent event) {
        context.setColumnId(String.valueOf(new Date().getTime()));
        context.setColumnIndex(event.getColumnIndex());
        context.setRight(event.isRight());
        context.setAsProperty(event.isAsProperty());
        new InsertColumnCommand().execute(context);
//        commonExecute(new InsertColumnCommand(model, String.valueOf(new Date().getTime()), event.getColumnIndex(), event.isRight(), event.isAsProperty(), context.getScenarioGridPanel(), context.getScenarioGridLayer()));
    }

    @Override
    public void onEvent(InsertRowEvent event) {
        context.setRowIndex(event.getRowIndex());
        new InsertRowCommand().execute(context);
//        commonExecute(new InsertRowCommand(model, event.getRowIndex()));
    }

    @Override
    public void onEvent(PrependColumnEvent event) {
        context.setColumnId(String.valueOf(new Date().getTime()));
        context.setColumnGroup(event.getColumnGroup());
        new PrependColumnCommand().execute(context);
//        commonExecute(new PrependColumnCommand(model, String.valueOf(new Date().getTime()), event.getColumnGroup(), context.getScenarioGridPanel(), context.getScenarioGridLayer()));
    }

    @Override
    public void onEvent(PrependRowEvent event) {
        new PrependRowCommand().execute(context);
//        commonExecute(new PrependRowCommand(model));
    }

    @Override
    public void onEvent(ReloadRightPanelEvent event) {
        context.setDisable(event.isDisable());
        context.setOpenDock(event.isOpenDock());
        new ReloadRightPanelCommand().execute(context);
//        commonExecute(new ReloadRightPanelCommand(scenarioSimulationEditorPresenter, event.isDisable(), event.isOpenDock()));
    }

    @Override
    public void handle(ScenarioGridReloadEvent event) {
        // TODO CHECK FOR POSITION
        context.getScenarioGridPanel().onResize();
    }

    @Override
    public void onEvent(ScenarioNotificationEvent event) {
        // TODO CHECK FOR POSITION
        notificationEvent.fire(new NotificationEvent(event.getMessage(), event.getType()));
    }

    @Override
    public void onEvent(SetInstanceHeaderEvent event) {
        // TODO CHECK FOR REFACTORING
        if (context.getModel().getSelectedColumn() == null) {
            return;
        }
        // TODO CHECK FOR REFACTORING
        if (context.getModel().isSameSelectedColumnType(event.getClassName())) {
            return;
        }
        context.setFullPackage(event.getFullPackage());
        context.setClassName(event.getClassName());
        if (((ScenarioGridColumn) context.getModel().getSelectedColumn()).isInstanceAssigned()) {
            org.uberfire.mvp.Command okPreserveCommand = () -> new SetInstanceHeaderCommand().execute(context);
            deletePopupPresenter.show(ScenarioSimulationEditorConstants.INSTANCE.changeTypeMainTitle(),
                                      ScenarioSimulationEditorConstants.INSTANCE.changeTypeMainQuestion(),
                                      ScenarioSimulationEditorConstants.INSTANCE.changeTypeText1(),
                                      ScenarioSimulationEditorConstants.INSTANCE.changeTypeTextQuestion(),
                                      ScenarioSimulationEditorConstants.INSTANCE.changeTypeTextDanger(),
                                      ScenarioSimulationEditorConstants.INSTANCE.changeType(),
                                      okPreserveCommand);
        } else {
            new SetInstanceHeaderCommand().execute(context);
//            commonExecute(new SetInstanceHeaderCommand(model, event.getFullPackage(), event.getClassName(), context.getScenarioGridPanel(), context.getScenarioGridLayer()));
        }
    }

    @Override
    public void onEvent(SetPropertyHeaderEvent event) {
        // TODO CHECK FOR REFACTORING
        if (context.getModel().getSelectedColumn() == null) {
            return;
        }
        context.setFullPackage(event.getFullPackage());
        context.setValue(event.getValue());
        context.setValueClassName(event.getValueClassName());
        if (context.getModel().isSelectedColumnEmpty()) {
            new SetPropertyHeaderCommand().execute(context);

            //commonExecute(new SetPropertyHeaderCommand(model, event.getFullPackage(), event.getValue(), event.getValueClassName(), context.getScenarioGridPanel(), context.getScenarioGridLayer(), false));
        } else if (context.getModel().isSameSelectedColumnProperty(event.getValue())) {
            return;
        } else if (context.getModel().isSameSelectedColumnType(event.getValueClassName())) {
            org.uberfire.mvp.Command okDeleteCommand = () -> {
                context.setKeepData(false);
                new SetPropertyHeaderCommand().execute(context);
            };
            org.uberfire.mvp.Command okPreserveCommand = () -> {
                context.setKeepData(true);
                new SetPropertyHeaderCommand().execute(context);
            };
//            org.uberfire.mvp.Command okDeleteCommand = () -> commonExecute(new SetPropertyHeaderCommand(model, event.getFullPackage(), event.getValue(), event.getValueClassName(), context.getScenarioGridPanel(), context.getScenarioGridLayer(), false));
//            org.uberfire.mvp.Command okPreserveCommand = () -> commonExecute(new SetPropertyHeaderCommand(model, event.getFullPackage(), event.getValue(), event.getValueClassName(), context.getScenarioGridPanel(), context.getScenarioGridLayer(), true));
            preserveDeletePopupPresenter.show(ScenarioSimulationEditorConstants.INSTANCE.preserveDeleteScenarioMainTitle(),
                                              ScenarioSimulationEditorConstants.INSTANCE.preserveDeleteScenarioMainQuestion(),
                                              ScenarioSimulationEditorConstants.INSTANCE.preserveDeleteScenarioText1(),
                                              ScenarioSimulationEditorConstants.INSTANCE.preserveDeleteScenarioTextQuestion(),
                                              ScenarioSimulationEditorConstants.INSTANCE.preserveDeleteScenarioTextOption1(),
                                              ScenarioSimulationEditorConstants.INSTANCE.preserveDeleteScenarioTextOption2(),
                                              ScenarioSimulationEditorConstants.INSTANCE.preserveValues(),
                                              ScenarioSimulationEditorConstants.INSTANCE.deleteValues(),
                                              okPreserveCommand,
                                              okDeleteCommand);
        } else if (!context.getModel().isSameSelectedColumnType(event.getValueClassName())) {
            org.uberfire.mvp.Command okPreserveCommand = () -> {
                context.setKeepData(false);
                new SetPropertyHeaderCommand().execute(context);
            };
            //            org.uberfire.mvp.Command okPreserveCommand = () -> commonExecute(new SetPropertyHeaderCommand(model, event.getFullPackage(), event.getValue(), event.getValueClassName(), context.getScenarioGridPanel(), context.getScenarioGridLayer(), false));
            deletePopupPresenter.show(ScenarioSimulationEditorConstants.INSTANCE.deleteScenarioMainTitle(),
                                      ScenarioSimulationEditorConstants.INSTANCE.deleteScenarioMainQuestion(),
                                      ScenarioSimulationEditorConstants.INSTANCE.deleteScenarioText1(),
                                      ScenarioSimulationEditorConstants.INSTANCE.deleteScenarioTextQuestion(),
                                      ScenarioSimulationEditorConstants.INSTANCE.deleteScenarioTextDanger(),
                                      ScenarioSimulationEditorConstants.INSTANCE.deleteValues(),
                                      okPreserveCommand);
        }
//        if (scenarioSimulationEditorPresenter != null) {
//            scenarioSimulationEditorPresenter.reloadRightPanel(false);
//        }
    }

    void registerHandlers() {
        handlerRegistrationList.add(eventBus.addHandler(AppendColumnEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(AppendRowEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(DeleteColumnEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(DeleteRowEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(DisableRightPanelEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(DuplicateRowEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(EnableRightPanelEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(InsertColumnEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(InsertRowEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(PrependColumnEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(PrependRowEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(ReloadRightPanelEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(ScenarioGridReloadEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(ScenarioNotificationEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(SetInstanceHeaderEvent.TYPE, this));
        handlerRegistrationList.add(eventBus.addHandler(SetPropertyHeaderEvent.TYPE, this));
    }
}
