package org.drools.workbench.screens.guided.rule.client.editor.factPattern;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.google.gwt.event.shared.EventBus;
import com.google.gwtmockito.GwtMockitoTestRunner;
import com.google.gwtmockito.WithClassesToStub;
import org.appformer.project.datamodel.oracle.DataType;
import org.appformer.project.datamodel.oracle.FieldAccessorsAndMutators;
import org.appformer.project.datamodel.oracle.ModelField;
import org.appformer.project.datamodel.oracle.OperatorsOracle;
import org.drools.workbench.models.datamodel.rule.ConnectiveConstraint;
import org.drools.workbench.models.datamodel.rule.FactPattern;
import org.drools.workbench.models.datamodel.rule.SingleFieldConstraint;
import org.drools.workbench.screens.guided.rule.client.editor.CEPOperatorsDropdown;
import org.drools.workbench.screens.guided.rule.client.editor.RuleModeller;
import org.drools.workbench.screens.guided.rule.client.resources.images.GuidedRuleEditorImages508;
import org.jboss.errai.common.client.api.Caller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.workbench.common.services.datamodel.service.IncrementalDataModelService;
import org.kie.workbench.common.services.shared.preferences.ApplicationPreferences;
import org.kie.workbench.common.widgets.client.datamodel.AsyncPackageDataModelOracleImpl;
import org.mockito.Mock;
import org.uberfire.mocks.CallerMock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@WithClassesToStub(GuidedRuleEditorImages508.class)
@RunWith(GwtMockitoTestRunner.class)
public class ConnectivesTest {

    @Mock
    private RuleModeller modeller;

    @Mock
    private EventBus eventBus;

    @Mock
    private FactPattern pattern;

    @Mock
    private SingleFieldConstraint singleFieldConstraint;

    @Mock
    private ConnectiveConstraint connectiveConstraint;

    @Mock
    private IncrementalDataModelService service;

    @Mock
    CEPOperatorsDropdown dropdown;

    private Caller<IncrementalDataModelService> serviceCaller;

    @Mock
    private AsyncPackageDataModelOracleImpl oracle;

    private Connectives connectives;

    @Before
    public void setUp() throws Exception {
        ApplicationPreferences.setUp(new HashMap() {{
            put(ApplicationPreferences.DATE_FORMAT,
                "dd/MM/YYYY");
        }});

        serviceCaller = new CallerMock<>(service);
        oracle = new AsyncPackageDataModelOracleImpl(serviceCaller,
                                                     null);

        final ModelField[] modelFields = new ModelField[]{
                new ModelField("street",
                               String.class.getName(),
                               ModelField.FIELD_CLASS_TYPE.REGULAR_CLASS,
                               ModelField.FIELD_ORIGIN.DECLARED,
                               FieldAccessorsAndMutators.BOTH,
                               DataType.TYPE_STRING),
                new ModelField("number",
                               Integer.class.getName(),
                               ModelField.FIELD_CLASS_TYPE.REGULAR_CLASS,
                               ModelField.FIELD_ORIGIN.DECLARED,
                               FieldAccessorsAndMutators.BOTH,
                               DataType.TYPE_NUMERIC_INTEGER)};

        Map<String, ModelField[]> fields = new HashMap<>();
        fields.put("org.Address",
                   modelFields);

        oracle.addModelFields(fields);

        connectives = spy(new Connectives(modeller,
                                          eventBus,
                                          pattern,
                                          false));

        doReturn(oracle).when(connectives).getDataModelOracle();
        doReturn(dropdown).when(connectives).getDropdown(any(),
                                                         eq(connectiveConstraint));

        doReturn(oracle).when(modeller).getDataModelOracle();

        doReturn(Stream.of(connectiveConstraint).toArray(ConnectiveConstraint[]::new))
                .when(singleFieldConstraint).getConnectives();
    }

    @Test
    public void testConnectiveOperatorsString() throws Exception {
        doReturn("org.Address").when(connectiveConstraint).getFactType();
        doReturn("street").when(connectiveConstraint).getFieldName();

        connectives.connectives(singleFieldConstraint);
        verify(connectives).getDropdown(OperatorsOracle.STRING_CONNECTIVES,
                                        connectiveConstraint);
    }

    @Test
    public void testConnectiveOperatorsInteger() throws Exception {
        doReturn("org.Address").when(connectiveConstraint).getFactType();
        doReturn("number").when(connectiveConstraint).getFieldName();

        connectives.connectives(singleFieldConstraint);
        verify(connectives).getDropdown(OperatorsOracle.COMPARABLE_CONNECTIVES,
                                        connectiveConstraint);
    }
}
