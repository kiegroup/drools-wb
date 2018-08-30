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

/**
 * The contextual menu of a specific <i>GIVEN</i> column
 * It differ from <code>HeaderGivenContextMenu</code> because it manage column (insert/remove) in different way
 */
@Dependent
public class GivenContextMenu extends AbstractHeaderMenuPresenter {

    private final String GIVENCONTEXTMENU_GIVEN = "givencontextmenu-given";
    private final String GIVENCONTEXTMENU_SCENARIO = "givencontextmenu-scenario";
    private final String GIVENCONTEXTMENU_INSERT_COLUMN_LEFT = "givencontextmenu-insert-column-left";
    private final String GIVENCONTEXTMENU_INSERT_COLUMN_RIGHT = "givencontextmenu-insert-column-right";
    private final String GIVENCONTEXTMENU_DELETE_COLUMN = "givencontextmenu-delete-column";
    private final String GIVENCONTEXTMENU_INSERT_ROW_BELOW = "givencontextmenu-insert-row-below";

    @PostConstruct
    @Override
    public void initMenu() {
//        addMenuItem(GIVENCONTEXTMENU_GIVEN, constants.given(), "given");
//        addExecutableMenuItem(GIVENCONTEXTMENU_INSERT_COLUMN_LEFT, constants.insertColumnLeft(), "insertColumnLeft", () -> GWT.log(GIVENCONTEXTMENU_INSERT_COLUMN_LEFT));
//        addExecutableMenuItem(GIVENCONTEXTMENU_INSERT_COLUMN_RIGHT, constants.insertColumnRight(), "insertColumnRight", () -> GWT.log(GIVENCONTEXTMENU_INSERT_COLUMN_RIGHT));
//        addExecutableMenuItem(GIVENCONTEXTMENU_DELETE_COLUMN, constants.deleteColumn(), "deleteColumn", () -> GWT.log(GIVENCONTEXTMENU_DELETE_COLUMN));
        // SCENARIO
        addMenuItem(GIVENCONTEXTMENU_SCENARIO, constants.scenario(), "scenario");
        addExecutableMenuItem(GIVENCONTEXTMENU_INSERT_ROW_BELOW, constants.insertRowBelow(), "insertRowBelow", appendRowEvent);
    }
}
