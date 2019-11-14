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

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;

import com.google.gwt.dom.client.LIElement;
import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.events.DeleteRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.DuplicateRowEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertRowEvent;

/**
 * The contextual menu of a a <i>ROW</i> cell whose <b>GROUP</b> does <b>allow</b> column modification (insert/delete). It has the same items has {@link AbstractColumnMenuPresenter} and specific ones (?)
 */
@Dependent
public class BackgroundGridContextMenu extends AbstractHeaderMenuPresenter {

    // This strings are used to give unique id in the final dom
    protected static final String B_GRIDCONTEXTMENU_GRID_TITLE = "b-gridcontextmenu-grid-title";
    protected static final String B_GRIDCONTEXTMENU_INSERT_ROW_ABOVE = "b-gridcontextmenu-insert-row-above";
    protected static final String B_GRIDCONTEXTMENU_INSERT_ROW_BELOW = "b-gridcontextmenu-insert-row-below";
    protected static final String B_GRIDCONTEXTMENU_DELETE_ROW = "b-gridcontextmenu-delete-row";
    protected static final String B_GRIDCONTEXTMENU_DUPLICATE_ROW = "b-gridcontextmenu-duplicate-row";

    protected LIElement insertRowAboveLIElement;
    protected LIElement insertRowBelowLIElement;
    protected LIElement duplicateRowLIElement;
    protected LIElement deleteRowLIElement;

    @PostConstruct
    @Override
    public void initMenu() {
        HEADERCONTEXTMENU_PREPEND_ROW = B_GRIDCONTEXTMENU_INSERT_ROW_BELOW;
        headerContextMenuGridTitleId = B_GRIDCONTEXTMENU_GRID_TITLE;
        headerContextMenuGridTitleLabel = constants.background();
        headerContextMenuGridTitleI18n = "background";
        super.initMenu();
        insertRowAboveLIElement = addExecutableMenuItem(B_GRIDCONTEXTMENU_INSERT_ROW_ABOVE, constants.insertRowAbove(), "insertRowAbove");
        duplicateRowLIElement = addExecutableMenuItem(B_GRIDCONTEXTMENU_DUPLICATE_ROW, constants.duplicateRow(), "duplicateRow");
        deleteRowLIElement = addExecutableMenuItem(B_GRIDCONTEXTMENU_DELETE_ROW, constants.deleteRow(), "deleteRow");
    }

    public void show(final GridWidget gridWidget, final int mx, final int my, int rowIndex) {
        show(gridWidget, mx, my);
        mapEvent(insertRowAboveLIElement, new InsertRowEvent(gridWidget, rowIndex));
        mapEvent(insertRowBelowLIElement, new InsertRowEvent(gridWidget, rowIndex + 1));
        mapEvent(duplicateRowLIElement, new DuplicateRowEvent(gridWidget, rowIndex));
        mapEvent(deleteRowLIElement, new DeleteRowEvent(gridWidget, rowIndex));
    }
}
