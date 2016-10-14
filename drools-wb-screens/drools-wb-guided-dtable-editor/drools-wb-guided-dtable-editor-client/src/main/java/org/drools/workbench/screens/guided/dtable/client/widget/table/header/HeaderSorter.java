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

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.DynamicColumn;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.ResourcesProvider;
import org.kie.workbench.common.widgets.decoratedgrid.client.widget.SortConfiguration;

/**
 * A Widget to display sort order
 */
public class HeaderSorter extends FocusPanel {

    private final HorizontalPanel hp = new HorizontalPanel();
    private final DynamicColumn<BaseColumn> col;

    private ResourcesProvider<BaseColumn> resources;

    public HeaderSorter( final DynamicColumn<BaseColumn> col, ResourcesProvider<BaseColumn> resources ) {
        this.resources = resources;
        this.col = col;
        hp.setHorizontalAlignment( HorizontalPanel.ALIGN_CENTER );
        hp.setVerticalAlignment( VerticalPanel.ALIGN_MIDDLE );
        hp.setHeight( resources.rowHeaderSorterHeight() + "px" );
        hp.setWidth( "100%" );
        setIconImage();
        add( hp );

        // Ensure our icon is updated when the SortDirection changes
        col.addValueChangeHandler( new ValueChangeHandler<SortConfiguration>() {

            public void onValueChange( ValueChangeEvent<SortConfiguration> event ) {
                setIconImage();
            }

        } );
    }

    // Set icon's resource accordingly
    private void setIconImage() {
        hp.clear();
        switch ( col.getSortDirection() ) {
            case ASCENDING:
                switch ( col.getSortIndex() ) {
                    case 0:
                        hp.add( new Image( resources.upArrowIcon() ) );
                        break;
                    default:
                        hp.add( new Image( resources.smallUpArrowIcon() ) );
                }
                break;
            case DESCENDING:
                switch ( col.getSortIndex() ) {
                    case 0:
                        hp.add( new Image( resources.downArrowIcon() ) );
                        break;
                    default:
                        hp.add( new Image( resources.smallDownArrowIcon() ) );
                }
                break;
            default:
                hp.add( new Image( resources.arrowSpacerIcon() ) );
        }
    }

}