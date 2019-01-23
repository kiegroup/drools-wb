package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.Collections;
import java.util.Map;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ListEditorElementPresenterTest extends AbstractCollectionEditorTest {

    @Mock
    private PropertyPresenter propertyPresenterMock;

    @Mock
    private ItemElementView listEditorElementViewMock;

    @Mock
    private LIElement itemSeparatorMock;

    // FIXME - no need to mock map/list
    @Mock
    private Map<ItemElementView, String> listEditorElementViewMapMock;

    @Mock
    private LIElement propertyFieldsMock;

    @Mock
    private SpanElement faAngleRightMock;

    private ItemElementPresenter itemElementPresenter;

    @Before
    public void setup() {
        when(viewsProviderMock.getListEditorElementView()).thenReturn(listEditorElementViewMock);
        when(listEditorElementViewMock.getItemSeparator()).thenReturn(itemSeparatorMock);

        this.itemElementPresenter = spy(new ItemElementPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.propertyPresenter = propertyPresenterMock;
//                this.itemElementViewList = listEditorElementViewMapMock;
            }
        });
    }

    @Test
    public void getProperties() {
        String testPropertyName = "TEST-PROPERTYNAME";
        String testPropertyValue = "TEST-PROPERTYVALUE";
        Map<String, String> testPropertiesMap = Collections.singletonMap(testPropertyName, testPropertyValue);
        String testNodeId = "TEST-NODEID";

        when(propertyPresenterMock.getPropertyFields(testPropertyName, testPropertyValue, testNodeId)).thenReturn(propertyFieldsMock);

//        List<LIElement> properties = itemElementPresenter.getProperties(testPropertiesMap, testNodeId);

//        verify(listEditorElementViewMock, times(1)).init(itemElementPresenter);
//        verify(itemSeparatorMock, times(1)).setAttribute("data-nodeid", testNodeId);
//        verify(listEditorElementViewMapMock, times(1)).put(listEditorElementViewMock, testNodeId);
//        assertNotNull(properties);
    }

    @Test
    public void onToggleRowExpansion() {
        when(listEditorElementViewMock.getFaAngleRight()).thenReturn(faAngleRightMock);
        itemElementPresenter.onToggleRowExpansion(listEditorElementViewMock, true);
        verify(propertyPresenterMock, times(1)).onToggleRowExpansion(anyString(), eq(true));
    }
}
