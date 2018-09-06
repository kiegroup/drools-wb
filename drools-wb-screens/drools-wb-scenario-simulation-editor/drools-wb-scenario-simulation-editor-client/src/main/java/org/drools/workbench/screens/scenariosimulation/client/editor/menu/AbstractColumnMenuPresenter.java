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

import org.drools.workbench.screens.scenariosimulation.client.commands.InsertColumnLeftCommand;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertColumnLeftEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.InsertColumnRightEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependColumnEvent;

/**
 * This class is meant to provide common methods to <b>column-specific</b> menus {@link ExpectedContextMenu} and {@link GivenContextMenu}
 * It is provided to avoid code duplication in concrete implementations
 */
public abstract class AbstractColumnMenuPresenter extends AbstractHeaderMenuPresenter {

    String COLUMNCONTEXTMENU_COLUMN;
    String COLUMNCONTEXTMENU_INSERT_COLUMN_LEFT;
    String COLUMNCONTEXTMENU_INSERT_COLUMN_RIGHT;
    String COLUMNCONTEXTMENU_DELETE_COLUMN;
    String COLUMNCONTEXTMENU_LABEL;
    String COLUMNCONTEXTMENU_I18N;

    private final InsertColumnLeftEvent insertColumnLeftEvent = new InsertColumnLeftEvent();
    private final InsertColumnRightEvent insertColumnRightEvent = new InsertColumnRightEvent();

    /**
     * This method set <b>column-specific</b> menu items and common <b>SCENARIO</b> menu items
     */
    public void initMenu() {
        addMenuItem(COLUMNCONTEXTMENU_COLUMN, COLUMNCONTEXTMENU_LABEL, COLUMNCONTEXTMENU_I18N);
        addExecutableMenuItem(COLUMNCONTEXTMENU_INSERT_COLUMN_LEFT, constants.insertColumnLeft(), "insertColumnLeft", insertColumnLeftEvent);
        addExecutableMenuItem(COLUMNCONTEXTMENU_INSERT_COLUMN_RIGHT, constants.insertColumnRight(), "insertColumnRight", insertColumnRightEvent);
        //        addExecutableMenuItem(COLUMNCONTEXTMENU_DELETE_COLUMN, constants.deleteColumn(), "deleteColumn", () -> GWT.log(COLUMNCONTEXTMENU_DELETE_COLUMN));
        super.initMenu();
    }
}
