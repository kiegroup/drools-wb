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

package org.drools.workbench.screens.testscenario.client.page.audit;

import java.util.Set;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLElement;
import org.drools.workbench.models.testscenarios.shared.ExecutionTrace;
import org.jboss.errai.common.client.dom.elemental2.Elemental2DomUtil;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
@Dependent
public class AuditPageViewImpl implements AuditPageView {

    @DataField("root")
    private HTMLDivElement root;

    @DataField("audit-log")
    private HTMLDivElement auditLogDiv;

    @DataField("fired-rules")
    private HTMLDivElement firedRulesDiv;

    private Elemental2DomUtil elemental2DomUtil;

    private AuditPage presenter;

    private FiredRulesTable firedRulesTable;

    private AuditLogTable auditLogTable;

    @Inject
    public AuditPageViewImpl(final HTMLDivElement root,
                             final HTMLDivElement auditLogDiv,
                             final HTMLDivElement firedRulesDiv,
                             final Elemental2DomUtil elemental2DomUtil,
                             final FiredRulesTable firedRulesTable,
                             final AuditLogTable auditLogTable) {
        this.root = root;
        this.auditLogDiv = auditLogDiv;
        this.firedRulesDiv = firedRulesDiv;
        this.elemental2DomUtil = elemental2DomUtil;
        this.firedRulesTable = firedRulesTable;
        this.auditLogTable = auditLogTable;

        this.elemental2DomUtil.appendWidgetToElement(firedRulesDiv, firedRulesTable.asWidget());
        this.elemental2DomUtil.appendWidgetToElement(auditLogDiv, auditLogTable.asWidget());
    }

    @Override
    public HTMLElement getElement() {
        return root;
    }

    @Override
    public void init(AuditPage presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showFiredRules(final ExecutionTrace executionTrace) {
        firedRulesTable.redrawFiredRules(executionTrace);
    }

    @Override
    public void showAuditLog(final Set<String> auditLogMessages) {
        auditLogTable.redrawFiredRules(auditLogMessages);
    }
}
