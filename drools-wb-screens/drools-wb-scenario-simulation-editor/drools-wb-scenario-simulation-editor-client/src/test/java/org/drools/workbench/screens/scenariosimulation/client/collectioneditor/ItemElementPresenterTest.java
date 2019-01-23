package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ItemElementPresenterTest extends AbstractCollectionEditorTest {

    private static final String TEST_ITEM_ID = "TEST-ITEM-ID";

    private Map<String, String> testPropertiesMap = Collections.singletonMap("TEST-KEY", "TEST-VALUE");

    @Mock
    private PropertyPresenter propertyPresenterMock;

    @Mock
    private ItemElementView itemElementViewMock;

    @Mock
    private LIElement itemSeparatorMock;

    // FIXME - no need to mock map/list
    @Mock
    private Map<ItemElementView, String> listEditorElementViewMapMock;

    @Mock
    private LIElement propertyFieldsMock;

    @Mock
    private SpanElement faAngleRightMock;

    @Mock
    private LIElement itemContainerMock;

    @Mock
    private LIElement saveChangeMock;

    @Mock
    private Style styleMock;

    private ItemElementPresenter itemElementPresenter;

    @Before
    public void setup() {
        when(viewsProviderMock.getListEditorElementView()).thenReturn(itemElementViewMock);
        when(itemElementViewMock.getItemSeparator()).thenReturn(itemSeparatorMock);
        when(itemElementViewMock.getItemContainer()).thenReturn(itemContainerMock);
        when(itemElementViewMock.getSaveChange()).thenReturn(saveChangeMock);
        when(saveChangeMock.getStyle()).thenReturn(styleMock);
        when(propertyPresenterMock.getPropertyFields(anyString(), anyString(), anyString())).thenReturn(propertyFieldsMock);
        when(itemElementViewMock.getFaAngleRight()).thenReturn(faAngleRightMock);

        this.itemElementPresenter = spy(new ItemElementPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.propertyPresenter = propertyPresenterMock;
//                this.itemElementViewList = listEditorElementViewMapMock;
            }
        });
    }

    @Test
    public void getItemContainer() {
        itemContainerMock = itemElementPresenter.getItemContainer(TEST_ITEM_ID, testPropertiesMap);
        verify(itemElementViewMock, times(1)).init(itemElementPresenter);
        verify(itemElementViewMock, times(1)).setItemId(TEST_ITEM_ID);
        verify(itemElementViewMock, times(1)).getItemContainer();
        verify(itemElementViewMock, times(1)).getSaveChange();
        verify(itemContainerMock, times(1)).insertBefore(propertyFieldsMock, saveChangeMock);
        // how to verify if itemElementView has been added to itemElementViewList if it doesn't need mocked?
        assertNotNull(itemContainerMock);
    }

    @Test
    public void onToggleRowExpansionIsShownTrue() {
        itemElementPresenter.onToggleRowExpansion(itemElementViewMock, true);
        verify(propertyPresenterMock, times(1)).onToggleRowExpansion(anyString(), eq(true));
    }

    @Test
    public void onToggleRowExpansionIsShownFalse() {
        itemElementPresenter.onToggleRowExpansion(itemElementViewMock, false);
        verify(propertyPresenterMock, times(1)).onToggleRowExpansion(anyString(), eq(false));
    }

    @Test
    public void onEditItem() {
        itemElementPresenter.onEditItem(itemElementViewMock);
        verify(propertyPresenterMock, times(1)).editProperties(anyString());
        verify(styleMock, times(1)).setVisibility(Style.Visibility.VISIBLE);
    }

    @Test
    public void updateItem() {
        itemElementPresenter.updateItem(itemElementViewMock);
        verify(propertyPresenterMock, times(1)).updateProperties(anyString());
        verify(styleMock, times(1)).setVisibility(Style.Visibility.HIDDEN);
    }

    @Test
    public void onStopEditingItem() {
        itemElementPresenter.onStopEditingItem(itemElementViewMock);
        verify(propertyPresenterMock, times(1)).stopEditProperties(anyString());
        verify(styleMock, times(1)).setVisibility(Style.Visibility.HIDDEN);
    }

    @Test
    public void onDeleteItem() {
        itemElementPresenter.onDeleteItem(itemElementViewMock);
        verify(propertyPresenterMock, times(1)).deleteProperties(anyString());
        verify(itemContainerMock, times(1)).removeFromParent();
        // need to verify itemElementView was removed from itemElementViewList
    }

    @Test
    public void getItemsProperties() {
        List<Map<String, String>> itemsProperties = itemElementPresenter.getItemsProperties();
        assertNotNull(itemsProperties);
    }
}
