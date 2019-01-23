package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ListEditorElementPresenterTest extends AbstractCollectionEditorTest {

    @Mock
    private PropertyEditorPresenter propertyEditorPresenterMock;

    @Mock
    private ListEditorElementView listEditorElementViewMock;

    @Mock
    private LIElement itemSeparatorMock;

    @Mock
    private Map<ListEditorElementView, String> listEditorElementViewMapMock;

    @Mock
    private LIElement propertyFieldsMock;

    @Mock
    private SpanElement faAngleRightMock;

    private ListEditorElementPresenter listEditorElementPresenter;

    @Before
    public void setup() {
        when(viewsProviderMock.getListEditorElementView()).thenReturn(listEditorElementViewMock);
        when(listEditorElementViewMock.getItemSeparator()).thenReturn(itemSeparatorMock);

        this.listEditorElementPresenter = spy(new ListEditorElementPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.propertyEditorPresenter = propertyEditorPresenterMock;
                this.listEditorElementViewMap = listEditorElementViewMapMock;
            }
        });
    }

    @Test
    public void getProperties() {
        String testPropertyName = "TEST-PROPERTYNAME";
        String testPropertyValue = "TEST-PROPERTYVALUE";
        Map<String, String> testPropertiesMap = Collections.singletonMap(testPropertyName, testPropertyValue);
        String testNodeId = "TEST-NODEID";

        when(propertyEditorPresenterMock.getPropertyFields(testPropertyName, testPropertyValue, testNodeId, new AtomicInteger(0).getAndIncrement())).thenReturn(propertyFieldsMock);

        List<LIElement> properties = listEditorElementPresenter.getProperties(testPropertiesMap, testNodeId);

        verify(listEditorElementViewMock, times(1)).init(listEditorElementPresenter);
        verify(itemSeparatorMock, times(1)).setAttribute("data-nodeid", testNodeId);
        verify(listEditorElementViewMapMock, times(1)).put(listEditorElementViewMock, testNodeId);
        assertNotNull(properties);
    }

    @Test
    public void onToggleRowExpansion() {
        when(listEditorElementViewMock.getFaAngleRight()).thenReturn(faAngleRightMock);
        listEditorElementPresenter.onToggleRowExpansion(listEditorElementViewMock, true);
        verify(propertyEditorPresenterMock, times(1)).onToggleRowExpansion(anyString(), eq(true));
    }
}
