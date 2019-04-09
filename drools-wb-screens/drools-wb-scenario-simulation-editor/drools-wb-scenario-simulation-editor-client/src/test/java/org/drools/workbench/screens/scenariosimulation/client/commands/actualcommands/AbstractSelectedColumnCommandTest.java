package org.drools.workbench.screens.scenariosimulation.client.commands.actualcommands;

import java.util.ArrayList;
import java.util.List;

import org.drools.workbench.screens.scenariosimulation.client.metadata.ScenarioHeaderMetaData;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridColumn;
import org.drools.workbench.screens.scenariosimulation.model.ExpressionElement;
import org.drools.workbench.screens.scenariosimulation.model.FactIdentifier;
import org.drools.workbench.screens.scenariosimulation.model.FactMapping;
import org.drools.workbench.screens.scenariosimulation.model.FactMappingValue;
import org.junit.Before;
import org.mockito.Mock;
import org.uberfire.ext.wires.core.grids.client.model.GridColumn;
import org.uberfire.ext.wires.core.grids.client.model.GridData;

import static org.mockito.Mockito.when;

public abstract class AbstractSelectedColumnCommandTest extends AbstractScenarioSimulationCommandTest {

    protected String GRID_COLUMN_ID_1 = GRID_COLUMN_ID + "_1";
    protected String GRID_PROPERTY_TITLE_1 = GRID_PROPERTY_TITLE + "_1";
    protected String FACT_ALIAS_1 = FACT_ALIAS + "_1";
    protected String FULL_CLASS_NAME_1 = FULL_CLASS_NAME + "_1";
    protected String FACT_IDENTIFIER_NAME_1 = FACT_IDENTIFIER_NAME + "_1";
    protected String VALUE_1 = VALUE + "_1";

    @Mock
    protected ScenarioGridColumn scenarioGridColumnMock1;
    @Mock
    protected FactMapping factMappingMock1;
    @Mock
    protected FactMappingValue factMappingValueMock1;
    @Mock
    protected FactIdentifier factIdentifierMock1;
    @Mock
    protected List<GridColumn.HeaderMetaData> headerMetaDatasMock1;
    @Mock
    protected ScenarioHeaderMetaData informationHeaderMetaDataMock1;
    @Mock
    protected ScenarioHeaderMetaData propertyHeaderMetaDataMock1;

    @Before
    public void setup() {
        super.setup();

        /* Column 1 represents a single fact with a single property, columns 2 & 3 represents a single fact with
           two properties */
        when(scenarioGridColumnMock1.getHeaderMetaData()).thenReturn(headerMetaDatasMock1);
        when(scenarioGridColumnMock1.getInformationHeaderMetaData()).thenReturn(informationHeaderMetaDataMock1);
        when(scenarioGridColumnMock1.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetaDataMock1);
        when(scenarioGridColumnMock1.getFactIdentifier()).thenReturn(factIdentifierMock1);
        when(scenarioGridColumnMock1.isInstanceAssigned()).thenReturn(Boolean.TRUE);
        when(scenarioGridColumnMock1.isPropertyAssigned()).thenReturn(Boolean.TRUE);

        GridData.Range range = new GridData.Range(COLUMN_NUMBER, COLUMN_NUMBER);
        when(scenarioGridModelMock.getInstanceLimits(COLUMN_NUMBER)).thenReturn(range);
        //when(scenarioGridModelMock.getSelectedColumn()).thenReturn((GridColumn) scenarioGridColumnMock1);

        when(headerMetaDatasMock1.get(COLUMN_NUMBER + 1)).thenReturn(informationHeaderMetaDataMock1);

        when(informationHeaderMetaDataMock1.getTitle()).thenReturn(VALUE_1);
        when(informationHeaderMetaDataMock1.getColumnGroup()).thenReturn(COLUMN_GROUP);

        when(propertyHeaderMetaDataMock1.getMetadataType()).thenReturn(ScenarioHeaderMetaData.MetadataType.PROPERTY);
        when(propertyHeaderMetaDataMock1.getTitle()).thenReturn(GRID_PROPERTY_TITLE_1);
        when(propertyHeaderMetaDataMock1.getColumnGroup()).thenReturn(COLUMN_GROUP);
        when(propertyHeaderMetaDataMock1.getColumnId()).thenReturn(GRID_COLUMN_ID_1);

        when(factMappingMock1.getFactIdentifier()).thenReturn(factIdentifierMock1);
        when(factMappingMock1.getFactAlias()).thenReturn(FACT_ALIAS_1);
        when(factMappingMock1.getClassName()).thenReturn(VALUE_CLASS_NAME);

        List<ExpressionElement> expressionElements = new ArrayList<>();
        expressionElements.add(new ExpressionElement(FACT_ALIAS_1));
        expressionElements.add(new ExpressionElement("test"));
        when(factMappingMock1.getExpressionElements()).thenReturn(expressionElements);

        when(factIdentifierMock1.getClassName()).thenReturn(FULL_CLASS_NAME_1);
        when(factIdentifierMock1.getName()).thenReturn(FACT_IDENTIFIER_NAME_1);

        gridColumns.add(scenarioGridColumnMock1);
        factMappingValuesLocal.add(factMappingValueMock1);
        factIdentifierSet.add(factIdentifierMock1);
        factMappingLocal.add(factMappingMock1);
        when(simulationDescriptorMock.getFactMappingByIndex(COLUMN_NUMBER)).thenReturn(factMappingMock1);

        //TODO doReturn(factMappingMock).when(simulationDescriptorMock).addFactMapping(anyInt(), anyString(), anyObject(), anyObject());


        /*

        /*
        when(scenarioGridColumnMock1.getHeaderMetaData()).thenReturn(headerMetaDatasMock2);
        when(scenarioGridColumnMock1.getInformationHeaderMetaData()).thenReturn(informationHeaderMetaDataMock2);
        when(scenarioGridColumnMock1.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetaDataMock2);
        when(scenarioGridColumnMock1.getFactIdentifier()).thenReturn(factIdentifierMock2);
        /*
        when(scenarioGridColumnMock1.getHeaderMetaData()).thenReturn(headerMetaDatasMock2);
        when(scenarioGridColumnMock1.getInformationHeaderMetaData()).thenReturn(informationHeaderMetaDataMock2);
        when(scenarioGridColumnMock1.getPropertyHeaderMetaData()).thenReturn(propertyHeaderMetaDataMock);
        when(scenarioGridColumnMock1.getFactIdentifier()).thenReturn(factIdentifierMock2); */
    }

}
