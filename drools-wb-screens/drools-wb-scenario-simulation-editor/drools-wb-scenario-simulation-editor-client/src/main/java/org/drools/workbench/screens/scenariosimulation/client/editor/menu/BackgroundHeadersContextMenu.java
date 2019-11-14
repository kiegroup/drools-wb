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
package org.drools.workbench.screens.scenariosimulation.client.editor.menu;

import com.google.gwt.dom.client.LIElement;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.events.DeleteColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertColumnEvent;

/**
 * This class is meant to provide common methods to <b>column-specific</b> menus {@link ExpectedContextMenu} and {@link GivenContextMenu},
 * both <b>instance</b> and <b>property</b> header.
 * It is provided to avoid code duplication in concrete implementations
 */
public class BackgroundHeadersContextMenu extends AbstractHeaderMenuPresenter {

    // This strings are used to give unique id in the final dom
    protected static final String B_HEADERSCONTEXTMENU_GRID_TITLE = "b-headerscontextmenu-grid-title";
    protected static final String B_HEADERSCONTEXTMENU_GIVEN = "b-headerscontextmenu-given";
    protected static final String B_HEADERSCONTEXTMENU_INSERT_COLUMN_LEFT = "b-headerscontextmenu-insert-column-left";
    protected static final String B_HEADERSCONTEXTMENU_INSERT_COLUMN_RIGHT = "b-headerscontextmenu-insert-column-right";
    protected static final String B_HEADERSCONTEXTMENU_DELETE_COLUMN = "b-headerscontextmenu-delete-column";
    protected static final String B_HEADERSCONTEXTMENU_DELETE_INSTANCE = "b-headerscontextmenu-delete-instance";

    protected LIElement insertColumnLeftLIElement;
    protected LIElement insertColumnRightLIElement;
    protected LIElement deleteColumnInstanceLIElement;
    protected LIElement columnContextLIElement;

    /**
     * This method set <b>column-specific</b> menu items and common <b>SCENARIO</b> menu items
     */
    @Override
    public void initMenu() {
        columnContextLIElement = addMenuItem(B_HEADERSCONTEXTMENU_GIVEN, constants.given(), "given");
        insertColumnLeftLIElement = addExecutableMenuItem(B_HEADERSCONTEXTMENU_INSERT_COLUMN_LEFT, constants.insertColumnLeft(), "insertColumnLeft");
        insertColumnRightLIElement = addExecutableMenuItem(B_HEADERSCONTEXTMENU_INSERT_COLUMN_RIGHT, constants.insertColumnRight(), "insertColumnRight");
        deleteColumnInstanceLIElement = addExecutableMenuItem(B_HEADERSCONTEXTMENU_DELETE_COLUMN, constants.deleteColumn(), "deleteColumn");
        headerContextMenuGridTitleId = B_HEADERSCONTEXTMENU_GRID_TITLE;
        headerContextMenuGridTitleLabel = constants.background();
        headerContextMenuGridTitleI18n = "background";
        super.initMenu();
    }

    public void show(final GridWidget gridWidget, final int mx, final int my, int columnIndex, String group, boolean asProperty) {
        mapEvent(insertColumnLeftLIElement, new InsertColumnEvent(gridWidget, columnIndex, false, asProperty));
        mapEvent(insertColumnRightLIElement, new InsertColumnEvent(gridWidget, columnIndex, true, asProperty));
        if (asProperty) {
            updateExecutableMenuItemAttributes(deleteColumnInstanceLIElement, B_HEADERSCONTEXTMENU_DELETE_COLUMN, constants.deleteColumn(), "deleteColumn");
            mapEvent(deleteColumnInstanceLIElement, new DeleteColumnEvent(gridWidget, columnIndex, group, true));
        } else {
            updateExecutableMenuItemAttributes(deleteColumnInstanceLIElement, B_HEADERSCONTEXTMENU_DELETE_INSTANCE, constants.deleteInstance(), "deleteInstance");
            mapEvent(deleteColumnInstanceLIElement, new DeleteColumnEvent(gridWidget, columnIndex, group, false));
        }
        show(gridWidget, mx, my);
    }
}
