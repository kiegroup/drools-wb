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
package org.drools.workbench.scenario.simulation.showcase.client.screens.editor;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.logging.client.LogConfiguration;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.inject.Inject;
import org.drools.workbench.scenario.simulation.showcase.client.fakeinterfaces.ScreenPanelView;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.widgets.common.ErrorPopupPresenter;
import org.uberfire.client.workbench.widgets.listbar.ResizeFlowPanel;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.Command;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.Menus;

@Dependent
@WorkbenchScreen(identifier = SessionDiagramEditorScreen.SCREEN_ID)
public class SessionDiagramEditorScreen {

    private static Logger LOGGER = Logger.getLogger(SessionDiagramEditorScreen.class.getName());

    public static final String SCREEN_ID = "SessionDiagramEditorScreen";

    private PlaceRequest placeRequest;
    public static final String TITLE = "Authoring Screen";
    private Menus menu = null;

    private ScreenPanelView screenPanelView;
    private ErrorPopupPresenter errorPopupPresenter;
    private PlaceManager placeManager;

    public SessionDiagramEditorScreen() {
        //CDI proxy
    }

    @Inject
    public SessionDiagramEditorScreen(final ScreenPanelView screenPanelView,
                                      final ErrorPopupPresenter errorPopupPresenter,
                                      final PlaceManager placeManager) {
        this.screenPanelView = screenPanelView;
        this.errorPopupPresenter = errorPopupPresenter;
        this.placeManager = placeManager;
        GWT.log("screenPanelView " + screenPanelView);
    }

    @PostConstruct
    public void init() {
        /*  decisionNavigatorDock.init(AuthoringPerspective.PERSPECTIVE_ID);*/
    }

    @OnStartup
    public void onStartup(final PlaceRequest placeRequest) {

        this.placeRequest = placeRequest;

        final ScenarioGrid.ScenarioGridLayer scenarioGridLayer = new ScenarioGrid.ScenarioGridLayer();
        final ScenarioGrid scenarioGrid = new ScenarioGrid(new ScenarioGrid.ScenarioGridModel(), scenarioGridLayer, new ScenarioGrid.ScenarioGridRender(false));

        scenarioGridLayer.add(scenarioGrid);

        ScenarioGrid.ScenarioGridPanel scenarioGridPanel = new ScenarioGrid.ScenarioGridPanel(1000, 1000);

        scenarioGridPanel.add(scenarioGridLayer);
        GWT.log("scenarioGridPanel " + scenarioGridPanel);
        screenPanelView.setWidget(scenarioGridPanel);
    }

   /*     scenarioGridPanel.setWidget(new Label("INNER PUPPA WAS HERE!!!!"));*/

    @WorkbenchPartTitle
    public String getTitle() {
        return TITLE;
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        return screenPanelView;
    }

    private Menus makeMenuBar() {
        final MenuFactory.TopLevelMenusBuilder<MenuFactory.MenuBuilder> m =
                MenuFactory
                        .newTopLevelMenu("Save")
                        .respondsWith(getSaveCommand())
                        .endMenu();
        /*   m.newTopLevelMenu(menuDevCommandsBuilder.build()).endMenu();*/
        return m.build();
    }

    private Command getSaveCommand() {
        return this::validateAndSave;
    }

    private void validateAndSave() {
        GWT.log("validateAndSave");
        final Command save = this::save;
    }

    private void save() {
        GWT.log("save");
        /*diagramService.save(getSession(),
        new ServiceCallback<Diagram<Graph, Metadata>>() {
            @Override
            public void onSuccess(Diagram<Graph, Metadata> item) {
                log(Level.INFO,
                    "Save operation finished for diagram [" + item.getName() + "].");
            }

            @Override
            public void onError(ClientRuntimeError error) {
                showError(error);
            }
        });*//*
    }
 /*
    private void newDiagram(final String uuid,
                            final String title,
                            final String definitionSetId,
                            final String shapeSetId,
                            final Command callback) {
        GWT.log("newDiagram");
        BusyPopup.showMessage("Loading");
        final Metadata metadata = buildMetadata(definitionSetId,
                                                shapeSetId,
                                                title);
        BusyPopup.close();
        open(metadata, callback, uuid);


        *//*clientFactoryServices.newDiagram(uuid,
                                         definitionSetId,
                                         metadata,
                                         new ServiceCallback<Diagram>() {
                                             @Override
                                             public void onSuccess(final Diagram diagram) {
                                                 final Metadata metadata = diagram.getMetadata();
                                                 metadata.setShapeSetId(shapeSetId);
                                                 metadata.setTitle(title);
                                                 open(diagram,
                                                      callback);
                                             }

                                             @Override
                                             public void onError(final ClientRuntimeError error) {
                                                 showError(error);
                                                 callback.execute();
                                             }
                                         });*//*
    }

    private Metadata buildMetadata(final String defSetId,
                                   final String shapeSetId,
                                   final String title) {
        return new MetadataImpl.MetadataImplBuilder(defSetId,
                                                    definitionManager)
                .setTitle(title)
                .setShapeSetId(shapeSetId)
                .build();
    }

    private void load(final String name,
                      final Command callback) {
        GWT.log("load");
        BusyPopup.showMessage("Loading");
        IsWidget toShow = new Label("PUPPA HAS BEEN HERE with name " + name + " !!!");
        GWT.log("toShow " + toShow);
        BusyPopup.close();

        //open(toShow, callback, name);
       *//* diagramService.loadByName(name,
                                  new ServiceCallback<Diagram>() {
                                      @Override
                                      public void onSuccess(final Diagram diagram) {
                                          open(diagram,
                                               callback);
                                      }

                                      @Override
                                      public void onError(final ClientRuntimeError error) {
                                          showError(error);
                                          callback.execute();
                                      }
                                  });*//*
    }

    void open(Metadata metadata *//*final Diagram diagram*//*,
              final Command callback, String uuid) {
        GWT.log("open");
//        screenPanelView.setWidget(widget);
       *//* SessionPresenter.View view = presenter.getView();
        view.setCanvasWidget(widget);
*//*
        GWT.log("sessionManager " + sessionManager);
        *//*ClientSession currentSession = sessionManager.getCurrentSession();
        if (currentSession == null) {
            createNewSession(metadata, callback, uuid);
            GWT.log("WE MUST CREATE A SESSION");
        } else {
            GWT.log("currentSession " + currentSession);
        }*//*
        expressionEditor.setExpression(uuid, new Decision(), Optional.empty());
        addExpressionEditorToCanvasWidget();

        *//*         *//*
        screenPanelView.setWidget(presenter.getView());
         presenter
                .withToolbar(true)
                .withPalette(true)
                .displayNotifications(type -> true)
                .open(getSession(),
                      new ScreenPresenterCallback(() -> {
                          expressionEditor.init(presenter);
                          openDock(presenter.getInstance());
                          callback.execute();
                      }));
    }

    @OnFocus
    public void onFocus() {
        final EditorSession session = presenter.getInstance();
        GWT.log("FOCUS [" + session + "]");
        if (null != session) {
            sessionFocusedEvent.fire(new SessionFocusedEvent(session));
        }
    }

    private boolean isSameSession(final ClientSession other) {
        return null != other && null != getSession() && other.equals(getSession());
    }

    @OnClose
    public void onClose() {
        destroyDock();
        destroySession();
    }

    void openDock(final EditorSession session) {
        decisionNavigatorDock.open();
        decisionNavigatorDock.setupContent(session.getCanvasHandler());
    }

    void destroyDock() {
        decisionNavigatorDock.close();
        decisionNavigatorDock.resetContent();
    }

    void destroySession() {
        presenter.destroy();
    }

    @WorkbenchMenu
    public Menus getMenu() {
        return menu;
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return title;
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        return screenPanelView.asWidget();
    }

    @WorkbenchContextId
    public String getMyContextRef() {
        return "sessionDiagramEditorScreenContext";
    }

  *//*  private final class ScreenPresenterCallback implements SessionPresenter.SessionPresenterCallback<Diagram> {

        private final Command callback;

        private ScreenPresenterCallback(final Command callback) {
            this.callback = callback;
        }

        @Override
        public void afterSessionOpened() {

        }

        @Override
        public void afterCanvasInitialized() {

        }

        @Override
        public void onSuccess() {
            BusyPopup.close();
            callback.execute();
        }

        @Override
        public void onError(final ClientRuntimeError error) {
            showError(error);
            callback.execute();
        }
    }*//*

    private void updateTitle(final String title) {
        // Change screen title.
        SessionDiagramEditorScreen.this.title = title;
        changeTitleNotificationEvent.fire(new ChangeTitleWidgetEvent(placeRequest,
                                                                     this.title));
    }

    private EditorSession getSession() {
        return null != presenter ? presenter.getInstance() : null;
    }

   *//* private CanvasHandler getCanvasHandler() {
        return null != getSession() ? getSession().getCanvasHandler() : null;
    }*//*

         *//* private Diagram getDiagram() {
        return null != getCanvasHandler() ? getCanvasHandler().getDiagram() : null;
    }*//*

         *//*private void createNewSession(Metadata metadata, Command callback, String uuid) {
        GWT.log("createNewSession " + metadata);
        sessionManager.newSession(metadata,
                                  EditorSession.class,
                                  session -> open(metadata,
                                                  callback, uuid));
        ClientSession currentSession = sessionManager.getCurrentSession();
        GWT.log("currentSession " + currentSession);

    }*//*

    private void showError(final ClientRuntimeError error) {
        screenErrorView.showError(error);
        screenPanelView.setWidget(screenErrorView.asWidget());
        log(Level.SEVERE,
            error.toString());
        BusyPopup.close();
    }

    private void onSessionErrorEvent(@Observes OnSessionErrorEvent errorEvent) {
        if (isSameSession(errorEvent.getSession())) {
            showError(errorEvent.getError());
        }
    }

    private void onEditExpressionEvent(final @Observes EditExpressionEvent event) {
        if (isSameSession(event.getSession())) {
            sessionCommandManager.execute((AbstractCanvasHandler) sessionManager.getCurrentSession().getCanvasHandler(),
                                          new NavigateToExpressionEditorCommand(expressionEditor,
                                                                                presenter,
                                                                                sessionManager,
                                                                                sessionCommandManager,
                                                                                event.getNodeUUID(),
                                                                                event.getHasExpression(),
                                                                                event.getHasName()));
        }
    }*/

    private void log(final Level level,
                     final String message) {
        if (LogConfiguration.loggingIsEnabled()) {
            LOGGER.log(level,
                       message);
        }
    }

    // See https://issues.jboss.org/browse/ERRAI-1090
    // The Widget returned from ElementWrapperWidget does not implement interfaces
    // defined on the editor.getElement() and hence RequiresResize is lost.
    // Wrap the editor in a ResizeFlowPanel to support RequiresResize.
    private ResizeFlowPanel wrapElementForErrai1090() {
        GWT.log("wrapElementForErrai1090");
        /* final Widget w = ElementWrapperWidget.getWidget(expressionEditor.getElement());*/
        final ResizeFlowPanel container = new ResizeFlowPanel() {

            @Override
            public void onResize() {
                super.onResize();
                /*expressionEditor.getView().onResize();*/
            }
        };
        container.getElement().setId("dmn-expression-editor-container");
        container.getElement().getStyle().setDisplay(Style.Display.FLEX);
        container.getElement().getStyle().setWidth(100.0, Style.Unit.PCT);
        container.getElement().getStyle().setHeight(100.0, Style.Unit.PCT);
        /* container.add(w);*/

        return container;
    }
}
