/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.workbench.client.navbar;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Panel;
import org.kie.workbench.common.widgets.client.search.ClearSearchEvent;
import org.kie.workbench.common.widgets.client.search.ContextualSearch;
import org.kie.workbench.common.widgets.client.search.SearchBehavior;
import org.kie.workbench.common.widgets.client.search.SetSearchTextEvent;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.widgets.menu.PerspectiveContextMenusPresenter;
import org.uberfire.mvp.impl.DefaultPlaceRequest;

/**
 * A stand-alone (i.e. devoid of Workbench dependencies) View
 */
public class ComplementNavAreaView
        extends Composite
        implements ComplementNavAreaPresenter.View {

    interface ViewBinder
            extends
            UiBinder<Panel, ComplementNavAreaView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField
    public Button searchButton;

    @UiField
    public TextBox searchTextBox;

    @Inject
    private ContextualSearch contextualSearch;

    @Inject
    private PlaceManager placeManager;

    @UiField
    public FlowPanel contextMenuArea;

    @UiField
    public FlowPanel searchPanel;

    @Inject
    private PerspectiveContextMenusPresenter contextMenu;

    @PostConstruct
    public void init() {
        initWidget( uiBinder.createAndBindUi( this ) );
        if ( Window.Location.getParameterMap().containsKey( "no_search" ) ) {
            searchPanel.setVisible( false );
        }
        contextMenuArea.add( contextMenu.getView() );
        contextualSearch.setDefaultSearchBehavior( new SearchBehavior() {
            @Override
            public void execute( final String term ) {
                placeManager.goTo( new DefaultPlaceRequest( "FullTextSearchForm" ).addParameter( "term", term ) );
            }
        } );
    }

    @UiHandler("searchButton")
    public void search( ClickEvent e ) {
        contextualSearch.getSearchBehavior().execute( searchTextBox.getText() );
    }

    public void onClearSearchBox( @Observes ClearSearchEvent clearSearch ) {
        searchTextBox.setText( "" );
    }

    public void onSetSearchText( @Observes SetSearchTextEvent setSearchText ) {
        searchTextBox.setText( setSearchText.getSearchText() );
    }

}