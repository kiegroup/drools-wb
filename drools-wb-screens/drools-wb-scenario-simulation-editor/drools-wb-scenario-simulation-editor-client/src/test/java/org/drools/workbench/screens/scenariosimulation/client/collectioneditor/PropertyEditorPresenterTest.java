package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.Map;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.utils.ConstantHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class PropertyEditorPresenterTest extends AbstractCollectionEditorTest {

    @Mock
    private PropertyEditorView propertyEditorViewMock;

    @Mock
    private PropertyEditorViewImpl propertyEditorViewImplMock;

    @Mock
    private LIElement LIElementMock;

    @Mock
    private SpanElement spanElementMock;

    @Mock
    private SpanElement propertyTextAreaMock;

    @Mock
    private LIElement propertyFieldsMock;

    @Mock
    private Map<String, SpanElement> propertySpanElementMapMock;

    @Mock
    Style styleMock;

    private PropertyEditorPresenter propertyEditorPresenter;

    @Before
    public void setup() {
        when(LIElementMock.getStyle()).thenReturn(styleMock);
        this.propertyEditorPresenter = spy(new PropertyEditorPresenter() {
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
        when(viewsProviderMock.getPropertyEditorView()).thenReturn(propertyEditorViewMock);
        when(propertyEditorViewMock.getPropertyName()).thenReturn(spanElementMock);
        when(propertyEditorViewMock.getPropertyValue()).thenReturn(propertyTextAreaMock);
        when(propertyEditorViewMock.getPropertyFields()).thenReturn(propertyFieldsMock);
        when(propertyEditorViewImplMock.getPropertyFields()).thenReturn(LIElementMock);
        LIElement propertyFields = propertyEditorPresenter.getPropertyFields("TEST-PROPERTYNAME", "TEST-PROPERTYVALUE", "TEST-NODEID", 1);
        assertNotNull(propertyFields);
    }

    @Test
    public void onToggleRowExpansionTrue() {
        propertyEditorPresenter.onToggleRowExpansion(LIElementMock, true);
        verify(LIElementMock, times(1)).addClassName(ConstantHolder.NODE_HIDDEN);
        verify(styleMock, times(1)).setDisplay(Style.Display.NONE);
    }

    @Test
    public void onToggleRowExpansionFalse() {
        propertyEditorPresenter.onToggleRowExpansion(LIElementMock, false);
        verify(LIElementMock, times(1)).removeClassName(ConstantHolder.NODE_HIDDEN);
        verify(styleMock, times(1)).setDisplay(Style.Display.BLOCK);
    }
}
