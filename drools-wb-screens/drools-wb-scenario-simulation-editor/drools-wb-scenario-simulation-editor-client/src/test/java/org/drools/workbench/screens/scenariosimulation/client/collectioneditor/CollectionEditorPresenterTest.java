package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.json.client.JSONValue;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox.ListEditingBoxPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class CollectionEditorPresenterTest extends AbstractCollectionEditorTest {

    @Mock
    private ListEditorElementPresenter listEditorElementPresenterMock;

    @Mock
    private ListEditingBoxPresenter listEditingBoxPresenterMock;

    @Mock
    private CollectionEditorView collectionEditorViewMock;

    @Mock
    private Map<String, CollectionEditorView> collectionEditorViewMapMock = new HashMap<>();

    @Mock
    private Map<String, Map<String, String>> instancePropertiesMapMock = new HashMap<>();

    @Mock
    private Map<String, String> propertyMapMock = new HashMap<>();

    @Mock
    private UListElement elementsContainerMock;

    @Mock
    private JSONValue jsonValueMock;

    @Mock
    private HeadingElement editorTitleMock;

    @Mock
    private SpanElement propertyTitleMock;

    @Mock
    private ObjectSeparator objectSeparatorMock;

    private CollectionEditorPresenter collectionEditorPresenter;

    @Before
    public void setup() {
        when(collectionEditorViewMapMock.get(anyString())).thenReturn(collectionEditorViewMock);
        when(collectionEditorViewMock.getElementsContainer()).thenReturn(elementsContainerMock);
        when(collectionEditorViewMock.getEditorTitle()).thenReturn(editorTitleMock);
        when(collectionEditorViewMock.getPropertyTitle()).thenReturn(propertyTitleMock);
        when(viewsProviderMock.getObjectSeparator()).thenReturn(objectSeparatorMock);
        this.collectionEditorPresenter = spy(new CollectionEditorPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.listEditorElementPresenter = listEditorElementPresenterMock;
                this.listEditingBoxPresenter = listEditingBoxPresenterMock;
                this.collectionEditorViewMap = collectionEditorViewMapMock;
                this.instancePropertiesMap = instancePropertiesMapMock;
            }
        });
        when(collectionEditorPresenter.getJSONValue("TEST-JSON")).thenReturn(jsonValueMock);
    }

    @Test
    public void initStructure() {
        String testClassName = "TEST-CLASSNAME";
        String testPropertyName = "TEST-PROPERTYNAME";
        String testKey = testClassName + "#" + testPropertyName;
        collectionEditorPresenter.initStructure(testClassName, testPropertyName, propertyMapMock, collectionEditorViewMock);
        verify(listEditorElementPresenterMock, times(1)).getProperties(any(), eq("0.0.0"));
        verify(editorTitleMock, times(1)).setInnerText(testKey);
        verify(propertyTitleMock, times(1)).setInnerText(testPropertyName);
        verify(elementsContainerMock, times(1)).appendChild(any());
        verify(viewsProviderMock, times(1)).getObjectSeparator();
        verify(objectSeparatorMock, times(1)).init(collectionEditorPresenter, testKey);
        verify(collectionEditorViewMapMock, times(1)).put(testKey, collectionEditorViewMock);
        verify(instancePropertiesMapMock, times(1)).put(testKey, propertyMapMock);
    }

    @Test
    public void setValueIsWidgetTrue() {
        when(collectionEditorViewMock.isListWidget()).thenReturn(true);
        collectionEditorPresenter.setValue("TEST-JSON", collectionEditorViewMock);
        verify(collectionEditorPresenter, times(1)).populateList(jsonValueMock, collectionEditorViewMock);
    }

    @Test
    public void setValueIsWidgetFalse() {
        when(collectionEditorViewMock.isListWidget()).thenReturn(false);
        collectionEditorPresenter.setValue("TEST-JSON", collectionEditorViewMock);
        verify(collectionEditorPresenter, times(1)).populateMap(jsonValueMock, collectionEditorViewMock);
    }

    @Test
    public void getValueIsWidgetTrue() {
        when(collectionEditorViewMock.isListWidget()).thenReturn(true);
        collectionEditorPresenter.getValue(collectionEditorViewMock);
        verify(collectionEditorPresenter, times(1)).getListValue(collectionEditorViewMock);
    }

    @Test
    public void getValueIsWidgetFalse() {
        when(collectionEditorViewMock.isListWidget()).thenReturn(false);
        collectionEditorPresenter.getValue(collectionEditorViewMock);
        verify(collectionEditorPresenter, times(1)).getMapValue(collectionEditorViewMock);
    }

    @Test
    public void showEditingBox() {
        String key = "TEST-#KEY";
        String propertyName = key.substring(key.lastIndexOf("#") + 1);

        collectionEditorPresenter.showEditingBox(key);
        verify(collectionEditorViewMapMock, times(1)).get(key);
        verify(collectionEditorViewMock, times(1)).getElementsContainer();
        verify(elementsContainerMock, times(1)).appendChild(any());
        verify(listEditingBoxPresenterMock, times(1)).getEditingBox(propertyName, instancePropertiesMapMock.get((key)));
    }

    @Test
    public void onToggleRowExpansionIsShownTrue() {
        collectionEditorPresenter.onToggleRowExpansion(collectionEditorViewMock, true);
        verify(listEditorElementPresenterMock, times(1)).onToggleRowExpansion(true);
    }

    @Test
    public void onToggleRowExpansionIsShownFalse() {
        collectionEditorPresenter.onToggleRowExpansion(collectionEditorViewMock, false);
        verify(listEditorElementPresenterMock, times(1)).onToggleRowExpansion(false);
    }
}
