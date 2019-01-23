package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.json.client.JSONValue;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox.ItemEditingBoxPresenter;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox.KeyValueEditingBoxPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class CollectionPresenterTest extends AbstractCollectionEditorTest {

    private static final String TEST_JSON = "TEST-JSON";
    private final static String TEST_CLASSNAME = "TEST-CLASSNAME";
    private final static String TEST_PROPERTYNAME = "TEST-PROPERTYNAME";
    private final static String TEST_KEY = TEST_CLASSNAME + "#" + TEST_PROPERTYNAME;
    private final static int CHILD_COUNT = 3;
    private final static String ITEM_ID = String.valueOf(CHILD_COUNT - 1);

    @Mock
    private ItemElementPresenter listElementPresenterMock;

    @Mock
    private KeyValueElementPresenter mapElementPresenterMock;

    @Mock
    private ItemEditingBoxPresenter listEditingBoxPresenterMock;

    @Mock
    private KeyValueEditingBoxPresenter mapEditingBoxPresenterMock;

    @Mock
    private CollectionView collectionViewMock;

    private Map<String, Map<String, String>> instancePropertiesMapLocal = new HashMap<>();

    private Map<String, String> keyPropertyMapLocal = new HashMap<>();
    private Map<String, String> propertyMapLocal = new HashMap<>();

    @Mock
    private UListElement elementsContainerMock;

    @Mock
    private LIElement listEditingBoxMock;

    @Mock
    private LIElement mapEditingBoxMock;

    @Mock
    private LIElement itemElementMock;

    @Mock
    private JSONValue jsonValueMock;

    @Mock
    private LIElement objectSeparatorLIMock;

    @Mock
    private HeadingElement editorTitleMock;

    @Mock
    private SpanElement propertyTitleMock;

    private CollectionPresenter collectionEditorPresenter;

    @Before
    public void setup() {
        when(elementsContainerMock.getChildCount()).thenReturn(CHILD_COUNT);
        when(editorTitleMock.getInnerText()).thenReturn(TEST_KEY);
        when(collectionViewMock.getElementsContainer()).thenReturn(elementsContainerMock);
        when(collectionViewMock.getEditorTitle()).thenReturn(editorTitleMock);
        when(collectionViewMock.getPropertyTitle()).thenReturn(propertyTitleMock);
        this.collectionEditorPresenter = spy(new CollectionPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.listElementPresenter = listElementPresenterMock;
                this.mapElementPresenter = mapElementPresenterMock;
                this.listEditingBoxPresenter = listEditingBoxPresenterMock;
                this.mapEditingBoxPresenter = mapEditingBoxPresenterMock;
                this.instancePropertiesMap = instancePropertiesMapLocal;
                this.collectionView = collectionViewMock;
                this.objectSeparatorLI = objectSeparatorLIMock;
            }
        });
        when(collectionEditorPresenter.getJSONValue(anyString())).thenReturn(jsonValueMock);
        instancePropertiesMapLocal.clear();
        when(listElementPresenterMock.getItemContainer(anyString(), anyMap())).thenReturn(itemElementMock);
        when(mapElementPresenterMock.getKeyValueContainer(anyString(), anyMap(), anyMap())).thenReturn(itemElementMock);
        when(listEditingBoxPresenterMock.getEditingBox(anyString(), anyMap())).thenReturn(listEditingBoxMock);
        when(mapEditingBoxPresenterMock.getEditingBox(anyString(), anyMap(), anyMap())).thenReturn(mapEditingBoxMock);
    }

    @Test
    public void initListStructure() {
        collectionEditorPresenter.initListStructure(TEST_KEY, propertyMapLocal, collectionViewMock);
        verify(collectionEditorPresenter, times(1)).commonInit(eq(TEST_KEY), eq(collectionViewMock));
        assertTrue(instancePropertiesMapLocal.containsKey(TEST_KEY));
        assertEquals(instancePropertiesMapLocal.get(TEST_KEY), propertyMapLocal);
        verify(listEditingBoxPresenterMock, times(1)).setCollectionEditorPresenter(eq(collectionEditorPresenter));
        verify(listElementPresenterMock, times(1)).setCollectionEditorPresenter(eq(collectionEditorPresenter));
    }

    @Test
    public void initMapStructure() {
        collectionEditorPresenter.initMapStructure(TEST_KEY, keyPropertyMapLocal, propertyMapLocal, collectionViewMock);
        verify(collectionEditorPresenter, times(1)).commonInit(eq(TEST_KEY), eq(collectionViewMock));
        assertTrue(instancePropertiesMapLocal.containsKey(TEST_KEY + "#key"));
        assertEquals(instancePropertiesMapLocal.get(TEST_KEY + "#key"), keyPropertyMapLocal);
        assertTrue(instancePropertiesMapLocal.containsKey(TEST_KEY + "#value"));
        assertEquals(instancePropertiesMapLocal.get(TEST_KEY + "#value"), propertyMapLocal);
        verify(mapEditingBoxPresenterMock, times(1)).setCollectionEditorPresenter(eq(collectionEditorPresenter));
        verify(mapElementPresenterMock, times(1)).setCollectionEditorPresenter(eq(collectionEditorPresenter));
    }

    @Test
    public void setValueIsListWidgetTrue() {
        commonSetValue(true);
    }

    @Test
    public void setValueIsListWidgetFalse() {
        commonSetValue(false);
    }

    @Test
    public void showEditingBoxIsListWidgetTrue() {
        when(collectionViewMock.isListWidget()).thenReturn(true);
        collectionEditorPresenter.showEditingBox();
        verify(collectionViewMock, times(1)).getElementsContainer();
        verify(listEditingBoxPresenterMock, times(1)).getEditingBox(eq(TEST_KEY), anyMap());
        verify(elementsContainerMock, times(1)).appendChild(eq(listEditingBoxMock));
    }

    @Test
    public void showEditingBoxIsListWidgetFalse() {
        when(collectionViewMock.isListWidget()).thenReturn(false);
        collectionEditorPresenter.showEditingBox();
        verify(collectionViewMock, times(1)).getElementsContainer();
        verify(mapEditingBoxPresenterMock, times(1)).getEditingBox(eq(TEST_KEY), anyMap(), anyMap());
        verify(elementsContainerMock, times(1)).appendChild(eq(mapEditingBoxMock));
    }

    @Test
    public void onToggleRowExpansionIsShownTrue() {
        commonOnToggleRowExpansionIsShown(true, true);
        commonOnToggleRowExpansionIsShown(true, false);
    }

    @Test
    public void onToggleRowExpansionIsShownFalse() {
        commonOnToggleRowExpansionIsShown(false, true);
        commonOnToggleRowExpansionIsShown(false, false);
    }

    @Test
    public void addListItem() {
        collectionEditorPresenter.addListItem(propertyMapLocal);
        verify(collectionViewMock, times(1)).getElementsContainer();
        verify(elementsContainerMock, times(1)).getChildCount();
        verify(listElementPresenterMock, times(1)).getItemContainer(eq(ITEM_ID), eq(propertyMapLocal));
        verify(elementsContainerMock, times(1)).insertBefore(eq(itemElementMock), eq(objectSeparatorLIMock));
    }

    @Test
    public void addMapItem() {
        collectionEditorPresenter.addMapItem(keyPropertyMapLocal, propertyMapLocal);
        verify(collectionViewMock, times(1)).getElementsContainer();
        verify(elementsContainerMock, times(1)).getChildCount();
        verify(mapElementPresenterMock, times(1)).getKeyValueContainer(eq(ITEM_ID), eq(keyPropertyMapLocal), eq(propertyMapLocal));
        verify(elementsContainerMock, times(1)).insertBefore(eq(itemElementMock), eq(objectSeparatorLIMock));
    }

    private void commonSetValue(boolean isListWidget) {
        collectionEditorPresenter.setValue(null);
        verify(collectionEditorPresenter, never()).getJSONValue(anyString());
        reset(collectionEditorPresenter);
        collectionEditorPresenter.setValue("");
        verify(collectionEditorPresenter, never()).getJSONValue(anyString());
        reset(collectionEditorPresenter);
        when(collectionViewMock.isListWidget()).thenReturn(isListWidget);
        collectionEditorPresenter.setValue(TEST_JSON);
        if (isListWidget) {
            verify(collectionEditorPresenter, times(1)).populateList(isA(JSONValue.class));
            verify(collectionEditorPresenter, never()).populateMap(isA(JSONValue.class));
        } else {
            verify(collectionEditorPresenter, times(1)).populateMap(isA(JSONValue.class));
            verify(collectionEditorPresenter, never()).populateList(isA(JSONValue.class));
        }
    }

    private void commonOnToggleRowExpansionIsShown(boolean isShown, boolean isListWidget) {
        when(collectionViewMock.isListWidget()).thenReturn(isListWidget);
        collectionEditorPresenter.onToggleRowExpansion(isShown);
        verify(collectionViewMock, times(1)).toggleRowExpansion();
        if (isListWidget) {
            verify(listElementPresenterMock, times(1)).onToggleRowExpansion(eq(isShown));
        } else {
            verify(mapElementPresenterMock, times(1)).onToggleRowExpansion(eq(isShown));
        }
        reset(collectionViewMock);
        reset(listElementPresenterMock);
        reset(mapElementPresenterMock);
    }
}
