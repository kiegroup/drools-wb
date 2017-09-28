package org.drools.workbench.screens.testscenario.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.Column;
import org.drools.workbench.models.testscenarios.shared.FactData;
import org.drools.workbench.models.testscenarios.shared.Field;
import org.drools.workbench.models.testscenarios.shared.FieldData;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

public class FieldDataValues extends CellTable<FieldData> {

    private FactData factData;

    public FieldDataValues(final FactData factData,
                           final String factType) {

        this.factData = factData;

        setStriped(true);
        setCondensed(true);
        setBordered(true);
        setWidth("100%");

        final TextCell nameCell = new TextCell();
        Column<FieldData, String> nameColumn = new Column<FieldData, String>(nameCell) {
            @Override
            public String getValue(FieldData model) {
                return factType + " [" + model.getName() + "]";
            }
        };

        addColumn(nameColumn,
                  "The field");

        final EditTextCell valueCell = new EditTextCell();
        Column<FieldData, String> valueColumn = new Column<FieldData, String>(valueCell) {
            @Override
            public String getValue(FieldData model) {
                return model.getValue();
            }
        };

        valueColumn.setFieldUpdater(new FieldUpdater<FieldData, String>() {
            @Override
            public void update(int i,
                               FieldData fieldData,
                               String s) {
                fieldData.setValue(s);
            }
        });

        addColumn(valueColumn,
                  "Value");

        render();
    }

    public void render() {
        List<FieldData> fields = new ArrayList<>();
        for (Field field : factData.getFieldData()) {
            if (field instanceof FieldData) {
                fields.add((FieldData) field);
            }
        }
        setRowData(fields);
    }
}