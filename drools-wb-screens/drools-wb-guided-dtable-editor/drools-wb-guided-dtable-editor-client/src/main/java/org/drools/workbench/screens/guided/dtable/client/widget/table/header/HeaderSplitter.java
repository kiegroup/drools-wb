/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.drools.workbench.screens.guided.dtable.client.widget.table.header;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.screens.guided.dtable.client.widget.table.VerticalDecisionTableHeaderWidget;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.ResourcesProvider;

/**
 * A Widget to split Conditions section
 */
public class HeaderSplitter extends FocusPanel {

    /**
     * Animation to change the height of a row
     */
    private class HeaderRowAnimation extends Animation {

        private TableRowElement tre;
        private int startHeight;
        private int endHeight;

        private HeaderRowAnimation( TableRowElement tre,
                                    int startHeight,
                                    int endHeight ) {
            this.tre = tre;
            this.startHeight = startHeight;
            this.endHeight = endHeight;
        }

        // Set row height by setting height of children
        private void setHeight( int height ) {
            for ( int i = 0; i < tre.getChildCount(); i++ ) {
                tre.getChild( i ).getFirstChild().<DivElement>cast().getStyle().setHeight( height,
                        Style.Unit.PX );
            }
            parent.fireResizeEvent();
        }

        @Override
        protected void onComplete() {
            super.onComplete();
            setHeight( endHeight );
        }

        @Override
        protected void onUpdate( double progress ) {
            int height = (int) ( startHeight + ( progress * ( endHeight - startHeight ) ) );
            setHeight( height );
        }

    }

    private TableRowElement[] rowHeaders;
    private final HorizontalPanel hp = new HorizontalPanel();
    private final Image icon = new Image();
    private boolean isCollapsed = true;

    private ResourcesProvider<BaseColumn> resources;
    private VerticalDecisionTableHeaderWidget parent;

    public HeaderSplitter(ResourcesProvider<BaseColumn> resources, VerticalDecisionTableHeaderWidget parent) {
        this.resources = resources;
        this.parent = parent;
        hp.setHorizontalAlignment( HorizontalPanel.ALIGN_CENTER );
        hp.setVerticalAlignment( VerticalPanel.ALIGN_MIDDLE );
        hp.getElement().getStyle().setHeight( resources.rowHeaderSplitterHeight(),
                Style.Unit.PX );
        hp.setWidth( "100%" );
        setIconImage();
        hp.add( icon );
        add( hp );

        // Handle action
        addClickHandler( new ClickHandler() {

            public void onClick( ClickEvent event ) {
                if ( isCollapsed ) {
                    showRow( 2 );
                    showRow( 3 );
                } else {
                    hideRow( 2 );
                    hideRow( 3 );
                }
                isCollapsed = !isCollapsed;
                setIconImage();
            }

        } );
    }

    // Hide a row using our animation
    private void hideRow( int iRow ) {
        if ( rowHeaders == null
                || ( rowHeaders.length - 1 ) < iRow ) {
            return;
        }
        TableRowElement tre = rowHeaders[ iRow ];
        HeaderRowAnimation anim = new HeaderRowAnimation( tre,
                resources.rowHeaderHeight(),
                0 );
        anim.run( 250 );
    }

    // Set icon's resource accordingly
    private void setIconImage() {
        if ( isCollapsed ) {
            icon.setResource( resources.smallDownArrowIcon() );
        } else {
            icon.setResource( resources.smallUpArrowIcon() );
        }
    }

    // Set rows to animate
    public void setRowHeaders( TableRowElement[] rowHeaders ) {
        this.rowHeaders = rowHeaders;
    }

    // Show a row using our animation
    private void showRow( int iRow ) {
        if ( rowHeaders == null || ( rowHeaders.length - 1 ) < iRow ) {
            return;
        }
        TableRowElement tre = rowHeaders[ iRow ];
        HeaderRowAnimation anim = new HeaderRowAnimation( tre,
                0,
                resources.rowHeaderHeight() );
        anim.run( 250 );
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }
}