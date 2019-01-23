package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.Map;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class PropertyPresenterTest extends AbstractCollectionEditorTest {

    @Mock
    private PropertyView propertyViewMock;

    @Mock
    private PropertyViewImpl propertyViewImplMock;

    @Mock
    private LIElement liElementMock;

    @Mock
    private SpanElement spanElementMock;

    @Mock
    private SpanElement propertyTextAreaMock;

    @Mock
    private LIElement propertyFieldsMock;

    // FIXME - no need to mock map/list
    @Mock
    private Map<String, SpanElement> propertySpanElementMapMock;

    @Mock
    private Style styleMock;

    private PropertyPresenter propertyEditorPresenter;

    @Before
    public void setup() {
        when(liElementMock.getStyle()).thenReturn(styleMock);
        this.propertyEditorPresenter = spy(new PropertyPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.propertySpanElementMap = propertySpanElementMapMock;
            }
        });
    }

    @Test
    public void getPropertyValueContainsKey() {
        String testPropertyName = "TEST-PROPERTYNAME";
        propertySpanElementMapMock.put(testPropertyName, spanElementMock);
        //when(propertySpanElementMapMock.get(anyString())).thenReturn(mockPropertyEditorView);

    }

    @Test
    public void getPropertyFields() {
        when(viewsProviderMock.getPropertyEditorView()).thenReturn(propertyViewMock);
        when(propertyViewMock.getPropertyName()).thenReturn(spanElementMock);
//        when(propertyViewMock.getPropertyValue()).thenReturn(propertyTextAreaMock);
        when(propertyViewMock.getPropertyFields()).thenReturn(propertyFieldsMock);
        when(propertyViewImplMock.getPropertyFields()).thenReturn(liElementMock);
        LIElement propertyFields = propertyEditorPresenter.getPropertyFields("TEST-PROPERTYNAME", "TEST-PROPERTYVALUE", "TEST-NODEID");
        assertNotNull(propertyFields);
    }

    @Test
    public void onToggleRowExpansionTrue() {
//        propertyEditorPresenter.toggleRowExpansion(liElementMock, true);
//        verify(liElementMock, times(1)).addClassName(ConstantHolder.NODE_HIDDEN);
//        verify(styleMock, times(1)).setDisplay(Style.Display.NONE);
    }

    @Test
    public void onToggleRowExpansionFalse() {
//        propertyEditorPresenter.toggleRowExpansion(liElementMock, false);
//        verify(liElementMock, times(1)).removeClassName(ConstantHolder.NODE_HIDDEN);
//        verify(styleMock, times(1)).setDisplay(Style.Display.BLOCK);
    }
}
