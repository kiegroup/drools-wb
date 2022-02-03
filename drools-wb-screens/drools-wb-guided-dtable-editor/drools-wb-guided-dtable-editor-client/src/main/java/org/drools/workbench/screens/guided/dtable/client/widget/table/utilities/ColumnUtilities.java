/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
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

package org.drools.workbench.screens.guided.dtable.client.widget.table.utilities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import com.google.gwt.i18n.shared.DateTimeFormat;
import org.drools.workbench.models.datamodel.rule.BaseSingleFieldConstraint;
import org.drools.workbench.models.guided.dtable.shared.model.ActionInsertFactCol52;
import org.drools.workbench.models.guided.dtable.shared.model.ActionSetFieldCol52;
import org.drools.workbench.models.guided.dtable.shared.model.AttributeCol52;
import org.drools.workbench.models.guided.dtable.shared.model.BaseColumn;
import org.drools.workbench.models.guided.dtable.shared.model.ConditionCol52;
import org.drools.workbench.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.drools.workbench.models.guided.dtable.shared.model.Pattern52;
import org.drools.workbench.models.guided.dtable.shared.util.ColumnUtilitiesBase;
import org.drools.workbench.screens.guided.dtable.client.resources.GuidedDecisionTableResources;
import org.drools.workbench.screens.guided.dtable.client.widget.table.columns.control.ColumnLabelWidget;
import org.kie.soup.commons.util.ListSplitter;
import org.kie.soup.commons.validation.PortablePreconditions;
import org.kie.soup.project.datamodel.oracle.DataType;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracle;

import static org.drools.workbench.screens.guided.dtable.client.widget.table.utilities.EnumLoaderUtilities.convertDropDownData;

/**
 * Utilities for Columns
 */
public class ColumnUtilities
        extends ColumnUtilitiesBase {

    private Map<String, Map<String, String>> enumLookUpsCache = new HashMap<>();
    private final AsyncPackageDataModelOracle oracle;

    public ColumnUtilities(final GuidedDecisionTable52 model,
                           final AsyncPackageDataModelOracle oracle) {
        super(model);
        this.oracle = PortablePreconditions.checkNotNull("oracle",
                                                         oracle);
    }

    protected String getTypeFromDataOracle(final String factType,
                                           final String fieldName) {
        final String type = oracle.getFieldType(factType,
                                                fieldName);
        return type;
    }

    public static void setColumnLabelStyleWhenHidden(final ColumnLabelWidget label,
                                                     final boolean isHidden) {
        if (isHidden) {
            label.addStyleName(GuidedDecisionTableResources.INSTANCE.css().columnLabelHidden());
        } else {
            label.removeStyleName(GuidedDecisionTableResources.INSTANCE.css().columnLabelHidden());
        }
    }

    // Check whether the given column can accept "otherwise" values
    public static boolean canAcceptOtherwiseValues(final BaseColumn column) {
        if (!(column instanceof ConditionCol52)) {
            return false;
        }
        final ConditionCol52 cc = (ConditionCol52) column;

        //Check column contains literal values and uses the equals operator
        if (cc.getConstraintValueType() != BaseSingleFieldConstraint.TYPE_LITERAL) {
            return false;
        }

        //Check operator is supported
        if (cc.getOperator() == null) {
            return false;
        }
        if (cc.getOperator().equals("==")) {
            return true;
        }
        if (cc.getOperator().equals("!=")) {
            return true;
        }
        return false;
    }

    public String[] getValueList(final BaseColumn col) {
        if (col instanceof AttributeCol52) {
            return getValueList((AttributeCol52) col);
        } else if (col instanceof ConditionCol52) {
            return getValueList((ConditionCol52) col);
        } else if (col instanceof ActionSetFieldCol52) {
            return getValueList((ActionSetFieldCol52) col);
        } else if (col instanceof ActionInsertFactCol52) {
            return getValueList((ActionInsertFactCol52) col);
        }
        return new String[0];
    }

    public Map<String, String> getValueListLookups(final BaseColumn column) {
        String[] valueList = getValueList(column);
        if (valueList != null && valueList.length > 0) {
            return convertDropDownData(valueList);
        } else {

            if (column instanceof ConditionCol52) {
                return getEnums((ConditionCol52) column);
            } else if (column instanceof ActionSetFieldCol52) {
                return getEnums((ActionSetFieldCol52) column);
            } else if (column instanceof ActionInsertFactCol52) {
                return getEnums((ActionInsertFactCol52) column);
            } else {
                return Collections.emptyMap();
            }
        }
    }

    private Map<String, String> getEnums(final ActionInsertFactCol52 column) {
        final String key = getBoundFactType(column.getBoundName()) + "." + column.getFactField();
        if (enumLookUpsCache.containsKey(key)) {
            return enumLookUpsCache.get(key);
        }
        return Collections.emptyMap();
    }

    private Map<String, String> getEnums(final ActionSetFieldCol52 column) {
        final String key = getBoundFactType(column.getBoundName()) + "." + column.getFactField();
        if (enumLookUpsCache.containsKey(key)) {
            return enumLookUpsCache.get(key);
        }
        return Collections.emptyMap();
    }

    private Map<String, String> getEnums(final ConditionCol52 column) {
        final Pattern52 pattern = model.getPattern(column);
        final String key = pattern.getFactType() + "." + column.getFactField();
        if (enumLookUpsCache.containsKey(key)) {
            return enumLookUpsCache.get(key);
        }
        return Collections.emptyMap();
    }

    private String[] getValueList(final AttributeCol52 col) {
        if ("no-loop".equals(col.getAttribute()) || "enabled".equals(col.getAttribute())) {
            return new String[]{"true", "false"};
        }
        return new String[0];
    }

    private String[] getValueList(final ConditionCol52 col) {
        if (col.getValueList() != null && !"".equals(col.getValueList())) {
            return parseValueList(col.getFieldType(),
                                  col.getValueList());
        }

        return new String[0];
    }

    private String[] getValueList(final ActionSetFieldCol52 col) {
        if (col.getValueList() != null && !"".equals(col.getValueList())) {
            return parseValueList(getTypeFromDataOracle(getBoundFactType(col.getBoundName()),
                                                        col.getFactField()),
                                  col.getValueList());
        }
        return new String[0];
    }

    private String[] getValueList(final ActionInsertFactCol52 col) {
        if (col.getValueList() != null && !"".equals(col.getValueList())) {
            return parseValueList(getTypeFromDataOracle(col.getFactType(),
                                                        col.getFactField()),
                                  col.getValueList());
        }
        return new String[0];
    }

    private String[] parseValueList(String fieldType,
                                    String valueList) {

        final String[] values = ListSplitter.split("'", true, valueList);

        return Stream.of(values).filter(value -> isValueValidForType(parseActualValue(value),
                                                                     convertToTypeSafeType(fieldType)))
                .toArray(String[]::new);
    }

    private String parseActualValue(final String value) {
        if (value != null && value.indexOf("=") > 0) {
            return value.substring(0, value.indexOf("="));
        } else {
            return value;
        }
    }

    private boolean isValueValidForType(String value,
                                        DataType.DataTypes type) {

        switch (type) {
            case STRING:
                return true;
            case NUMERIC:
                return canBeConstructed((input -> new BigDecimal(input)),
                                        value);
            case NUMERIC_BIGDECIMAL:
                return canBeConstructed((input -> new BigDecimal(input)),
                                        value);
            case NUMERIC_BIGINTEGER:
                return canBeConstructed((input -> new BigInteger(input)),
                                        value);
            case NUMERIC_BYTE:
                return canBeConstructed((input -> Byte.valueOf(input)),
                                        value);
            case NUMERIC_DOUBLE:
                return canBeConstructed((input -> Double.valueOf(input)),
                                        value);
            case NUMERIC_FLOAT:
                return canBeConstructed((input -> Float.valueOf(input)),
                                        value);
            case NUMERIC_INTEGER:
                return canBeConstructed((input -> Integer.valueOf(input)),
                                        value);
            case NUMERIC_LONG:
                return canBeConstructed((input -> Long.valueOf(input)),
                                        value);
            case NUMERIC_SHORT:
                return canBeConstructed((input -> Short.valueOf(input)),
                                        value);
            case BOOLEAN:
                return value.trim().equalsIgnoreCase("true") || value.trim().equalsIgnoreCase("false");
            case DATE: {
                final String pattern = ApplicationPreferences.getDroolsDateFormat();
                if (pattern != null) {
                    return canBeConstructed((input -> DateTimeFormat.getFormat(pattern).parse(input)),
                                            value);
                }
            }
            default:
                return true;
        }
    }

    private boolean canBeConstructed(Function<String, Object> constructFunction,
                                     String value) {
        try {
            constructFunction.apply(value);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean hasValueList(final AttributeCol52 col) {
        if ("no-loop".equals(col.getAttribute()) || "enabled".equals(col.getAttribute())) {
            return true;
        }
        return false;
    }

    public void addEnumLookUp(final String factName,
                              final String factField,
                              final Map<String, String> result) {
        enumLookUpsCache.put(factName + "." + factField, result);
    }
}
