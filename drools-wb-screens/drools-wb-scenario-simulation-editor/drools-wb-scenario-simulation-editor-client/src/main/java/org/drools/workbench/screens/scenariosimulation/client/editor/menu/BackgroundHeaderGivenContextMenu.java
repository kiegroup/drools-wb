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

import org.drools.workbench.screens.scenariosimulation.client.enums.GridWidget;
import org.drools.workbench.screens.scenariosimulation.client.events.AppendColumnEvent;
import org.drools.workbench.screens.scenariosimulation.client.events.PrependColumnEvent;

/**
 * The contextual menu of the top level <i>GIVEN</i> group for a Background Grid
 * It differ from <code>GivenContextMenu</code> because it manage column (insert/remove) in different way
 */
@Dependent
public class BackgroundHeaderGivenContextMenu extends AbstractHeaderGroupMenuPresenter {

    protected static final String B_HEADERGIVENCONTEXTMENU_GIVEN = "b-headergivencontextmenu-given";
    protected static final String B_HEADERGIVENCONTEXTMENU_GRID_TITLE = "b-headergivencontextmenu-grid-title";
    protected static final String B_HEADERGIVENCONTEXTMENU_INSERT_COLUMN_LEFT = "b-headergivencontextmenu-insert-column-left";
    protected static final String B_HEADERGIVENCONTEXTMENU_INSERT_COLUMN_RIGHT = "b-headergivencontextmenu-insert-column-right";
    protected static final String B_HEADERGIVENCONTEXTMENU_DELETE_COLUMN = "b-headergivencontextmenu-delete-column";
    protected static final String B_HEADERGIVENCONTEXTMENU_INSERT_ROW_ABOVE = "b-headergivencontextmenu-insert-row-above";

    @PostConstruct
    @Override
    public void initMenu() {
        HEADERCONTEXTMENU_GROUP = B_HEADERGIVENCONTEXTMENU_GIVEN;
        HEADERCONTEXTMENU_INSERT_COLUMN_LEFT = B_HEADERGIVENCONTEXTMENU_INSERT_COLUMN_LEFT;
        HEADERCONTEXTMENU_INSERT_COLUMN_RIGHT = B_HEADERGIVENCONTEXTMENU_INSERT_COLUMN_RIGHT;
        HEADERCONTEXTMENU_DELETE_COLUMN = B_HEADERGIVENCONTEXTMENU_DELETE_COLUMN;
        HEADERCONTEXTMENU_PREPEND_ROW = B_HEADERGIVENCONTEXTMENU_INSERT_ROW_ABOVE;
        HEADERCONTEXTMENU_LABEL = constants.given().toUpperCase();
        HEADERCONTEXTMENU_I18N = "given";
        headerContextMenuGridTitleId = B_HEADERGIVENCONTEXTMENU_GRID_TITLE;
        headerContextMenuGridTitleLabel = constants.background();
        headerContextMenuGridTitleI18n = "background";
        super.initMenu();
    }

    @Override
    public void show(final GridWidget gridWidget, int mx, int my) {
        super.show(gridWidget, mx, my);
        mapEvent(appendColumnElement, new AppendColumnEvent(gridWidget, "GIVEN"));
        mapEvent(prependColumnElement, new PrependColumnEvent(gridWidget, "GIVEN"));
    }
}
