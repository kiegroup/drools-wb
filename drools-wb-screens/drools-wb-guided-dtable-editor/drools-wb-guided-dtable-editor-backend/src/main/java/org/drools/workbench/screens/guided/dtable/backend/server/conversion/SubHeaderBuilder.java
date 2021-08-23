/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
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
package org.drools.workbench.screens.guided.dtable.backend.server.conversion;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.drools.core.util.DateUtils;
import org.drools.workbench.models.datamodel.rule.ActionCallMethod;
import org.drools.workbench.models.datamodel.rule.Attribute;
import org.drools.workbench.models.datamodel.workitems.PortableParameterDefinition;
import org.drools.workbench.models.datamodel.workitems.PortableWorkDefinition;
import org.drools.workbench.models.guided.dtable.shared.model.ActionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.ActionInsertFactCol52;
import org.drools.workbench.models.guided.dtable.shared.model.ActionRetractFactCol52;
import org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52;
import org.drools.workbench.models.guided.dtable.shared.model.ActionWorkItemCol52;
import org.drools.workbench.models.guided.dtable.shared.model.ActionWorkItemSetFieldCol52;
import org.drools.workbench.models.guided.dtable.shared.model.AttributeCol52;
import org.drools.workbench.models.guided.dtable.shared.model.BRLActionColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLActionVariableColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BRLConditionVariableColumn;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.DescriptionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.models.guided.dtable.shared.model.MetadataCol52;
import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;
import org.drools.workbench.screens.guided.dtable.backend.server.conversion.util.ColumnContext;
import org.drools.workbench.screens.guided.dtable.backend.server.conversion.util.NotificationReporter;
import org.drools.workbench.screens.guided.dtable.backend.server.conversion.util.Skipper;
import org.kie.soup.commons.validation.PortablePreconditions;
import org.kie.soup.project.datamodel.imports.Import;
import org.kie.soup.project.datamodel.oracle.DataType;
import org.kie.soup.project.datamodel.oracle.MethodInfo;
import org.kie.soup.project.datamodel.oracle.PackageDataModelOracle;

import static org.drools.workbench.models.datamodel.rule.BaseSingleFieldConstraint.TYPE_PREDICATE;
import static org.drools.workbench.models.datamodel.rule.BaseSingleFieldConstraint.TYPE_RET_VALUE;

public class SubHeaderBuilder {

    public static final String ACTION = "ACTION";
    public static final String METADATA = "METADATA";
    public static final String CONDITION = "CONDITION";

    private static final int COLUMN_TYPE_ROW = 5;
    private static final int FIELD_ROW = 7;
    private static final int HEADER_TITLE_ROW = 8;

    private final GuidedDecisionTable52 dtable;
    private final PackageDataModelOracle dmo;
    private final XLSColumnUtilities columnUtilities;
    private final Row headerRow;
    private final Row fieldRow;
    private final Row headerTitleRow;

    private int targetColumnIndex = 0;
    private final BRLColumnSubHeaderProvider brlColumnSubHeaderProvider;
    private final ColumnContext columnContext;

    private int sourceColumnIndex = 0;

    public ColumnContext getColumnContext() {
        return columnContext;
    }

    public Row getFieldRow() {
        return fieldRow;
    }

    public int getTargetColumnIndex() {
        return targetColumnIndex;
    }

    public SubHeaderBuilder(final Sheet sheet,
                            final GuidedDecisionTable52 dtable,
                            final PackageDataModelOracle dmo,
                            final ColumnContext columnContext) {
        PortablePreconditions.checkNotNull("sheet", sheet);
        this.dmo = PortablePreconditions.checkNotNull("dmo", dmo);
        this.dtable = PortablePreconditions.checkNotNull("dtable", dtable);
        this.columnContext = PortablePreconditions.checkNotNull("brlColumnIndex", columnContext);
        brlColumnSubHeaderProvider = new BRLColumnSubHeaderProvider(this,
                                                                    columnContext);
        this.columnUtilities = new XLSColumnUtilities(dtable,
                                                      PortablePreconditions.checkNotNull("dmo", dmo),
                                                      false);

        this.headerRow = sheet.createRow(COLUMN_TYPE_ROW);
        this.fieldRow = sheet.createRow(FIELD_ROW);
        this.headerTitleRow = sheet.createRow(HEADER_TITLE_ROW);
    }

    public void build(final NotificationReporter notificationReporter) {

        final List<BaseColumn> expandedColumns = dtable.getExpandedColumns();

        for (; sourceColumnIndex < expandedColumns.size(); sourceColumnIndex++) {

            final BaseColumn baseColumn = expandedColumns.get(sourceColumnIndex);

            if (Skipper.shouldSkip(notificationReporter, baseColumn)) {
                // Ignore and do not add to count
                continue;
            } else if (baseColumn instanceof AttributeCol52) {
                addAttribute((AttributeCol52) baseColumn);
            } else if (baseColumn instanceof MetadataCol52) {
                addMetadata((MetadataCol52) baseColumn);
            } else if (baseColumn instanceof BRLConditionVariableColumn) {
                final BRLConditionColumn brlColumn = dtable.getBRLColumn((BRLConditionVariableColumn) baseColumn);
                addBRLConditionColumn(brlColumn);
                sourceColumnIndex = sourceColumnIndex + brlColumn.getChildColumns().size() - 1;
            } else if (baseColumn instanceof BRLActionVariableColumn) {
                final BRLActionColumn brlColumn = dtable.getBRLColumn((BRLActionVariableColumn) baseColumn);
                addBRLActionColumn(brlColumn);
                sourceColumnIndex = sourceColumnIndex + brlColumn.getChildColumns().size() - 1;
            } else if (baseColumn instanceof ConditionCol52) {
                addCondition((ConditionCol52) baseColumn);
            } else if (baseColumn instanceof ActionCol52) {
                addAction((ActionCol52) baseColumn);
            } else if (baseColumn instanceof DescriptionCol52) {
                // This is actually a column, but header is not written down in XLS
            } else {
                throw new IllegalArgumentException("TODO REMOTE THIS");
            }
            incrementTargetIndex();
        }
    }

    public void incrementTargetIndex() {
        targetColumnIndex++;
    }

    private void addBRLConditionColumn(final BRLConditionColumn brlColumn) {
        brlColumnSubHeaderProvider.getBRLColumnSubHeaderBuilder(dtable,
                                                                brlColumn);
    }

    private void addBRLActionColumn(final BRLActionColumn brlColumn) {
        brlColumnSubHeaderProvider.getBRLColumnSubHeaderBuilder(dtable,
                                                                brlColumn);
    }

    public void addAction(final ActionCol52 baseColumn) {

        if (baseColumn instanceof ActionWorkItemSetFieldCol52) {
            addWorkItemSetField((ActionWorkItemSetFieldCol52) baseColumn);
        } else if (baseColumn instanceof ActionSetFieldCol52) {
            addSetField((ActionSetFieldCol52) baseColumn);
        } else if (baseColumn instanceof ActionInsertFactCol52) {
            addBRLActionVariableColumn((ActionInsertFactCol52) baseColumn);
        } else if (baseColumn instanceof ActionWorkItemCol52) {
            addWorkItem((ActionWorkItemCol52) baseColumn);
        } else if (baseColumn instanceof ActionRetractFactCol52) {
            addRetract((ActionRetractFactCol52) baseColumn);
        }
    }

    public void addHeaderAndTitle(final String title,
                                  final String header) {
        headerRow.createCell(targetColumnIndex)
                .setCellValue(title);

        headerTitleRow.createCell(targetColumnIndex)
                .setCellValue(header);
    }

    private void addWorkItem(final ActionWorkItemCol52 column) {

        final PortableWorkDefinition workItemDefinition = column.getWorkItemDefinition();

        final String wimManagerName = getWorkItemManagerVariableName(workItemDefinition.getName());
        final String wiParamName = getWorkItemParameterVariableName(workItemDefinition.getName());
        final StringBuilder builder = new StringBuilder();
        for (final PortableParameterDefinition parameterDefinition : workItemDefinition.getParameters()) {

            builder.append(wiParamName);
            builder.append(".getParameters().put(");
            builder.append("\"");
            builder.append(parameterDefinition.getName());
            builder.append("\", ");
            builder.append(parameterDefinition.asString());
            builder.append(");\n");
        }
        final String format = MessageFormat.format("org.drools.core.process.instance.WorkItemManager {0} = (org.drools.core.process.instance.WorkItemManager) drools.getWorkingMemory().getWorkItemManager();\n" +
                                                           "org.drools.core.process.instance.impl.WorkItemImpl {1} = new org.drools.core.process.instance.impl.WorkItemImpl();\n" +
                                                           "{1}.setName( \"{2}\" );\n" +
                                                           "{3}" +
                                                           "{0}.internalExecuteWorkItem( {1} );",
                                                   wimManagerName,
                                                   wiParamName,
                                                   workItemDefinition.getName(),
                                                   builder.toString());

        addHeaderAndTitle(ACTION,
                          column.getHeader());
        fieldRow.createCell(targetColumnIndex).setCellValue(format);
    }

    private String getWorkItemParameterVariableName(final String name) {
        return String.format("wi%sParameter", name);
    }

    private String getWorkItemManagerVariableName(final String name) {
        return String.format("wi%sManager", name);
    }

    private void addRetract(final ActionRetractFactCol52 column) {

        addHeaderAndTitle(ACTION,
                          column.getHeader());
        fieldRow.createCell(targetColumnIndex).setCellValue("retract( $param );");
    }

    private void addBRLActionVariableColumn(final ActionInsertFactCol52 baseColumn) {
        boolean makeInsert = makeInsert(baseColumn.getBoundName(),
                                        baseColumn.getFactType());
        if (makeInsert) {
            incrementTargetIndex();
        }
        addBRLActionVariableColumn(baseColumn.getHeader(),
                                   baseColumn.getBoundName(),
                                   baseColumn.getFactType(),
                                   baseColumn.getFactField(),
                                   baseColumn.getType());
    }

    public void addBRLActionVariableColumn(final String header,
                                           final String boundName,
                                           final String factType,
                                           final String factField,
                                           final String valueType) {

        addHeaderAndTitle(ACTION,
                          header);

        if (isMethod(factType,
                     factField)) {
            fieldRow.createCell(targetColumnIndex).setCellValue(addMethod(boundName,
                                                                          factField,
                                                                          getRHSParamWithWrapper(valueType)));
        } else {
            fieldRow.createCell(targetColumnIndex).setCellValue(addSetMethod(boundName,
                                                                             factField,
                                                                             getRHSParamWithWrapper(valueType)));
        }
    }

    public boolean makeInsert(final String boundName,
                              final String factType) {

        if (columnContext.isBoundNameFree(boundName)) {

            addHeaderAndTitle(ACTION,
                              "");

            fieldRow.createCell(targetColumnIndex).setCellValue(MessageFormat.format("{0} {1} = new {2}(); insert( {1} );",
                                                                                     factType,
                                                                                     boundName,
                                                                                     factType));

            columnContext.addBoundName(boundName);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Check if this is a method or a mutator (get/set)
     * However this ignores if the parameter count matches.
     *
     * @return
     */
    private boolean isMethod(final String factType,
                             final String factField) {
        final Optional<String> fqcn = getFQCN(factType);
        if (fqcn.isPresent()) {
            for (final Map.Entry<String, List<MethodInfo>> entry : dmo.getModuleMethodInformation().entrySet()) {
                if (Objects.equals(entry.getKey(), fqcn.get())) {
                    for (final MethodInfo methodInfo : entry.getValue()) {
                        if (Objects.equals(methodInfo.getName(), factField)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private Optional<String> getFQCN(final String factType) {
        final String ending = "." + factType;
        for (final Import anImport : dtable.getImports().getImports()) {
            if (anImport.getType().endsWith(ending)) {
                return Optional.of(anImport.getType());
            }
        }
        return Optional.of(dtable.getPackageName() + ending);
    }

    public String getRHSParamWithWrapper(final String valueType) {
        return getRHSParamWithWrapper(valueType,
                                      "$param");
    }

    public String getRHSParamWithWrapper(final String valueType,
                                         final String var) {

        switch (valueType) {

            case DataType.TYPE_DATE:
                return String.format("new java.text.SimpleDateFormat( \"%s\").parse(%s)", DateUtils.getDateFormatMask(), var);
            case DataType.TYPE_NUMERIC_BIGDECIMAL:
                return String.format("new java.math.BigDecimal(\"%s\")", var);
            case DataType.TYPE_NUMERIC_BIGINTEGER:
                return String.format("new java.math.BigInteger(\"%s\")", var);
            case DataType.TYPE_NUMERIC_DOUBLE:
                return var + "d";
            case DataType.TYPE_NUMERIC_FLOAT:
                return var + "f";
            case DataType.TYPE_NUMERIC_LONG:
                return var + "L";
            default:
                return var;
        }
    }

    private String getLHSParamWithWrapper(final String type) {

        switch (type) {

            case DataType.TYPE_NUMERIC_BIGDECIMAL:
                return "$paramB";
            case DataType.TYPE_NUMERIC_BIGINTEGER:
                return "$paramI";
            default:
                return "$param";
        }
    }

    private void addSetField(final ActionSetFieldCol52 column) {

        addSetField(column,
                    getRHSParamWithWrapper(column.getType()));
    }

    private void addWorkItemSetField(final ActionWorkItemSetFieldCol52 column) {

        addSetField(column,
                    String.format("(%s) %s.getResult( \"Result\" )",
                                  getColumnDataType(sourceColumnIndex),
                                  getWorkItemParameterVariableName(column.getWorkItemName())));
    }

    private void addSetField(final ActionSetFieldCol52 column,
                             final String value) {

        addHeaderAndTitle(ACTION,
                          column.getHeader());
        fieldRow.createCell(targetColumnIndex).setCellValue(addSetMethod(column.getBoundName(),
                                                                         column.getFactField(),
                                                                         value));
    }

    public void addMethodCallSetField(final ActionSetFieldCol52 column,
                                      final String value) {

        addHeaderAndTitle(ACTION,
                          column.getHeader());
        fieldRow.createCell(targetColumnIndex).setCellValue(addSetMethod(column.getBoundName(),
                                                                         column.getFactField(),
                                                                         value));
    }

    public void addMethodCallWithParameters(final String header,
                                            final ActionCallMethod iAction,
                                            final String params) {

        addHeaderAndTitle(ACTION,
                          header);
        fieldRow.createCell(targetColumnIndex).setCellValue(String.format("%s.%s( %s );",
                                                                          iAction.getVariable(),
                                                                          iAction.getMethodName(),
                                                                          params));
    }

    public void addMethodCallWithoutParameters(final String header,
                                               final ActionCallMethod iAction) {

        addHeaderAndTitle(ACTION,
                          header);
        fieldRow.createCell(targetColumnIndex).setCellValue(String.format("%s.%s( );",
                                                                          iAction.getVariable(),
                                                                          iAction.getMethodName()));
    }

    public void addCondition(final ConditionCol52 column) {

        addHeaderAndTitle(CONDITION,
                          column.getHeader());

        checkIfNewPattern(column);

        if (column.getConstraintValueType() == TYPE_PREDICATE) {

            if (column.getFactField().contains("$param")) {

                fieldRow.createCell(targetColumnIndex).setCellValue(String.format("eval( %s )",
                                                                                  column.getFactField()));
            } else {

                fieldRow.createCell(targetColumnIndex).setCellValue(String.format("eval( %s )",
                                                                                  "$param"));
            }
        } else if (column.getBinding() == null || column.getBinding().trim().isEmpty()) {
            fieldRow.createCell(targetColumnIndex).setCellValue(String.format(getTemplate(column.getConstraintValueType(),
                                                                                          column.getFieldType()),
                                                                              column.getFactField(),
                                                                              getOperator(column)));
        } else {
            fieldRow.createCell(targetColumnIndex).setCellValue(String.format(getTemplateWithBinds(column.getConstraintValueType()),
                                                                              column.getBinding(),
                                                                              column.getFactField(),
                                                                              getOperator(column)));
        }
    }

    private void checkIfNewPattern(final ConditionCol52 column) {
        final Pattern52 pattern = dtable.getPattern(column);
        if (pattern != null) {
            columnContext.addBoundName(pattern.getBoundName());
        }
    }

    private String getTemplate(final int constraintValueType,
                               final String fieldType) {
        if (constraintValueType == TYPE_RET_VALUE) {
            return "%s %s ( " + getLHSParamWithWrapper(fieldType) + " )";
        } else {
            return "%s %s " + getLHSParamWithWrapper(fieldType);
        }
    }

    private String getTemplateWithBinds(final int constraintValueType) {
        if (constraintValueType == TYPE_RET_VALUE) {
            return "%s : %s %s ( $param )";
        } else {
            return "%s : %s %s $param";
        }
    }

    private String getOperator(final ConditionCol52 column) {
        if (Objects.equals("== null", column.getOperator())) {
            return "==";
        } else if (Objects.equals("!= null", column.getOperator())) {
            return "!=";
        } else if (column instanceof BRLConditionVariableColumn) {
            return columnContext.getVariableOperators().getOperator(((BRLConditionVariableColumn) column).getVarName());
        } else {
            return column.getOperator();
        }
    }

    private void addAttribute(final AttributeCol52 column) {
        if (Objects.equals(Attribute.NEGATE_RULE.getAttributeName(), column.getAttribute())) {
            throw new UnsupportedOperationException("Conversion of the negate attribute is not supported.");
        } else {
            headerRow.createCell(targetColumnIndex)
                    .setCellValue(getAttribute(column));
        }
    }

    private String getAttribute(final AttributeCol52 column) {

        if (Objects.equals(Attribute.SALIENCE.getAttributeName(), column.getAttribute())) {
            return "PRIORITY";
        } else {
            return column.getAttribute().toUpperCase();
        }
    }

    private void addMetadata(final MetadataCol52 column) {

        addHeaderAndTitle(METADATA,
                          column.getHeader());

        fieldRow.createCell(targetColumnIndex).setCellValue(String.format("%s( $param )",
                                                                          column.getMetadata()));
    }

    private String addMethod(final String boundName,
                             final String factField,
                             final String value) {
        return String.format("%s.%s( %s );",
                             boundName,
                             factField,
                             value);
    }

    private String addSetMethod(final String boundName,
                                final String factField,
                                final String value) {
        return String.format("%s.set%s%s( %s );",
                             boundName,
                             factField.substring(0, 1).toUpperCase(),
                             factField.substring(1),
                             value);
    }

    private String getColumnDataType(final int columnIndex) {
        return columnUtilities.getType(dtable.getExpandedColumns().get(columnIndex));
    }
}
