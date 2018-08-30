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
 * The contextual menu of a specific <i>EXPECTED</i> column
 * It differ from <code>HeaderExpectedContextMenu</code> because it manage column (insert/remove) in different way
 */
@Dependent
public class ExpectedContextMenu extends AbstractHeaderMenuPresenter {

    private final String EXPECTEDCONTEXTMENU_EXPECTED = "expectedcontextmenu-expected";
    private final String EXPECTEDCONTEXTMENU_SCENARIO = "expectedcontextmenu-scenario";
    private final String EXPECTEDCONTEXTMENU_INSERT_COLUMN_LEFT = "expectedcontextmenu-insert-column-left";
    private final String EXPECTEDCONTEXTMENU_INSERT_COLUMN_RIGHT = "expectedcontextmenu-insert-column-right";
    private final String EXPECTEDCONTEXTMENU_DELETE_COLUMN = "expectedcontextmenu-delete-column";
    private final String EXPECTEDCONTEXTMENU_INSERT_ROW_BELOW = "expectedcontextmenu-insert-row-below";

    @PostConstruct
    @Override
    public void initMenu() {
//        addMenuItem(EXPECTEDCONTEXTMENU_EXPECTED, constants.expected().toUpperCase(), "expected");
//        addExecutableMenuItem(EXPECTEDCONTEXTMENU_INSERT_COLUMN_LEFT, constants.insertColumnLeft(), "insertColumnLeft", () -> GWT.log(EXPECTEDCONTEXTMENU_INSERT_COLUMN_LEFT));
//        addExecutableMenuItem(EXPECTEDCONTEXTMENU_INSERT_COLUMN_RIGHT, constants.insertColumnRight(), "insertColumnRight", () -> GWT.log(EXPECTEDCONTEXTMENU_INSERT_COLUMN_RIGHT));
//        addExecutableMenuItem(EXPECTEDCONTEXTMENU_DELETE_COLUMN, constants.deleteColumn(), "deleteColumn", () -> GWT.log(EXPECTEDCONTEXTMENU_DELETE_COLUMN));
        // SCENARIO
        addMenuItem(EXPECTEDCONTEXTMENU_SCENARIO, constants.scenario(), "scenario");
        addExecutableMenuItem(EXPECTEDCONTEXTMENU_INSERT_ROW_BELOW, constants.insertRowBelow(), "insertRowBelow", appendRowEvent);
    }
}
