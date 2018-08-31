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
 * The contextual menu of the top level <i>EXPECTED</i> group.
 * It differ from <code>ExpectedContextMenu</code> because it manage column (insert/remove) in different way
 */
@Dependent
public class HeaderExpectedContextMenu extends AbstractHeaderMenuPresenter {

    private final String HEADEREXPECTEDCONTEXTMENU_EXPECTED = "headerexpectedcontextmenu-expected";
    private final String HEADEREXPECTEDCONTEXTMENU_SCENARIO = "headerexpectedcontextmenu-scenario";
    private final String HEADEREXPECTEDCONTEXTMENU_INSERT_COLUMN_LEFT = "headerexpectedcontextmenu-insert-column-left";
    private final String HEADEREXPECTEDCONTEXTMENU_INSERT_COLUMN_RIGHT = "headerexpectedcontextmenu-insert-column-right";
    private final String HEADEREXPECTEDCONTEXTMENU_DELETE_COLUMN = "headerexpectedcontextmenu-delete-column";
    private final String HEADEREXPECTEDCONTEXTMENU_INSERT_ROW_BELOW = "headerexpectedcontextmenu-insert-row-below";

    @PostConstruct
    @Override
    public void initMenu() {
        addMenuItem(HEADEREXPECTEDCONTEXTMENU_EXPECTED, constants.expected().toUpperCase(), "expected");
       // addExecutableMenuItem(HEADEREXPECTEDCONTEXTMENU_INSERT_COLUMN_LEFT, constants.insertColumnLeft(), "insertColumnLeft", () -> GWT.log(HEADEREXPECTEDCONTEXTMENU_INSERT_COLUMN_LEFT));
        addExecutableMenuItem(HEADEREXPECTEDCONTEXTMENU_INSERT_COLUMN_RIGHT, constants.insertColumnRight(), "insertColumnRight", appendColumnEvent);
//        addExecutableMenuItem(HEADEREXPECTEDCONTEXTMENU_DELETE_COLUMN, constants.deleteColumn(), "deleteColumn", () -> GWT.log(HEADEREXPECTEDCONTEXTMENU_DELETE_COLUMN));
        // SCENARIO
        addMenuItem(HEADEREXPECTEDCONTEXTMENU_SCENARIO, constants.scenario(), "scenario");
        addExecutableMenuItem(HEADEREXPECTEDCONTEXTMENU_INSERT_ROW_BELOW, constants.insertRowBelow(), "insertRowBelow", appendRowEvent);
    }
}
