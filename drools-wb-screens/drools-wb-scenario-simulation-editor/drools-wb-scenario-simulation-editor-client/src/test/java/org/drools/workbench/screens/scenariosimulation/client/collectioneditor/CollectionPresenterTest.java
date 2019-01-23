package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.json.client.JSONValue;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox.ItemEditingBoxPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class CollectionPresenterTest extends AbstractCollectionEditorTest {

    @Mock
    private ItemElementPresenter listElementPresenterMock;

    @Mock
    private ItemEditingBoxPresenter listEditingBoxPresenterMock;

    @Mock
    private CollectionView collectionEditorViewMock;

    // FIXME no need to mock map/list
    @Mock
    private Map<String, Map<String, String>> instancePropertiesMapMock = new HashMap<>();

    // FIXME no need to mock map/list
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

    private CollectionPresenter collectionEditorPresenter;

    @Before
    public void setup() {
        when(collectionEditorViewMock.getElementsContainer()).thenReturn(elementsContainerMock);
        when(collectionEditorViewMock.getEditorTitle()).thenReturn(editorTitleMock);
        when(collectionEditorViewMock.getPropertyTitle()).thenReturn(propertyTitleMock);
        this.collectionEditorPresenter = spy(new CollectionPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.listElementPresenter = listElementPresenterMock;
                this.listEditingBoxPresenter = listEditingBoxPresenterMock;
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
        collectionEditorPresenter.initListStructure(testClassName, propertyMapMock, collectionEditorViewMock);
//        verify(listElementPresenterMock, times(1)).getProperties(any(), eq("0.0.0"));
//        verify(editorTitleMock, times(1)).setInnerText(testKey);
//        verify(propertyTitleMock, times(1)).setInnerText(testPropertyName);
//        verify(elementsContainerMock, times(1)).appendChild(any());
//        verify(viewsProviderMock, times(1)).getObjectSeparator();
//        verify(objectSeparatorMock, times(1)).init(collectionEditorPresenter, testKey);
//        verify(collectionEditorViewMapMock, times(1)).put(testKey, collectionEditorViewMock);
//        verify(instancePropertiesMapMock, times(1)).put(testKey, propertyMapMock);
    }

    @Test
    public void setValueIsWidgetTrue() {
        when(collectionEditorViewMock.isListWidget()).thenReturn(true);
        collectionEditorPresenter.setValue("TEST-JSON");
        verify(collectionEditorPresenter, times(1)).populateList(jsonValueMock);
    }

    @Test
    public void setValueIsWidgetFalse() {
        when(collectionEditorViewMock.isListWidget()).thenReturn(false);
        collectionEditorPresenter.setValue("TEST-JSON");
        verify(collectionEditorPresenter, times(1)).populateMap(jsonValueMock);
    }

    @Test
    public void showEditingBox() {
        String key = "TEST-#KEY";
        String propertyName = key.substring(key.lastIndexOf("#") + 1);
        collectionEditorPresenter.showEditingBox();
        verify(collectionEditorViewMock, times(1)).getElementsContainer();
        verify(elementsContainerMock, times(1)).appendChild(any());
        verify(listEditingBoxPresenterMock, times(1)).getEditingBox(propertyName, instancePropertiesMapMock.get((key)));
    }

    @Test
    public void onToggleRowExpansionIsShownTrue() {
        collectionEditorPresenter.onToggleRowExpansion(true);
        verify(listElementPresenterMock, times(1)).onToggleRowExpansion(true);
    }

    @Test
    public void onToggleRowExpansionIsShownFalse() {
        collectionEditorPresenter.onToggleRowExpansion(false);
        verify(listElementPresenterMock, times(1)).onToggleRowExpansion(false);
    }
}
