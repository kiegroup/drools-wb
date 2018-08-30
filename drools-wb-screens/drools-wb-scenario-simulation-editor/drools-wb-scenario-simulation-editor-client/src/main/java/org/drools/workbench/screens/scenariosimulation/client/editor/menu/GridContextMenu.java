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
 * The contextual menu of a any <i>ROW</i> cell
 */
@Dependent
public class GridContextMenu extends AbstractHeaderMenuPresenter {

    private final String GRIDCONTEXTMENU_SCENARIO =  "gridcontextmenu-scenario";
    private final String GRIDCONTEXTMENU_INSERT_ROW_ABOVE =  "gridcontextmenu-insert-row-above";
    private final String GRIDCONTEXTMENU_INSERT_ROW_BELOW =  "gridcontextmenu-insert-row-below";
    private final String GRIDCONTEXTMENU_DELETE_ROW =  "gridcontextmenu-delete-row";
    private final String GRIDCONTEXTMENU_DUPLICATE_ROW =  "gridcontextmenu-duplicate-row";

    @PostConstruct
    @Override
    public void initMenu() {
        addMenuItem(GRIDCONTEXTMENU_SCENARIO, constants.scenario(), "scenario");
       // addExecutableMenuItem(GRIDCONTEXTMENU_INSERT_ROW_ABOVE, constants.insertRowAbove(), "insertRowAbove", () -> GWT.log(GRIDCONTEXTMENU_INSERT_ROW_ABOVE));
        addExecutableMenuItem(GRIDCONTEXTMENU_INSERT_ROW_BELOW, constants.insertRowBelow(), "insertRowBelow", appendRowEvent);
//        addExecutableMenuItem(GRIDCONTEXTMENU_DELETE_ROW, constants.deleteRow(), "deleteRow", () -> GWT.log(GRIDCONTEXTMENU_DELETE_ROW));
//        addExecutableMenuItem(GRIDCONTEXTMENU_DUPLICATE_ROW, constants.duplicateRow(), "duplicateRow", () -> GWT.log(GRIDCONTEXTMENU_DUPLICATE_ROW));
    }
}
