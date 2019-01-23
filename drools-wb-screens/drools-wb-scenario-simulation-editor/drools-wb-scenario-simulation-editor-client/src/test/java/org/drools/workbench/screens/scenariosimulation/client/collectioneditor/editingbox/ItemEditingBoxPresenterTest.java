package org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.CollectionView;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.PropertyPresenter;
import org.drools.workbench.screens.scenariosimulation.client.utils.ViewsProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class ItemEditingBoxPresenterTest {

    private static final String TEST_KEY = "TEST-CLASSNAME#TEST-PROPERTYNAME";
    private static final String TEST_PROPERTYNAME = "TEST-PROPERTYNAME";

    private Map<String, String> testInstancePropertyMap = Collections.singletonMap("TEST-KEY", "TEST-VALUE");
    private Map<String, String> testPropertiesValues = new HashMap<>();

    @Mock
    private PropertyPresenter propertyPresenterMock;

    @Mock
    private CollectionView.Presenter collectionViewPresenterMock;

    @Mock
    private ViewsProvider viewsProviderMock;

    @Mock
    private LIElement editingBoxMock;

    @Mock LIElement propertyFieldsMock;

    @Mock
    private ItemEditingBox itemEditingBoxMock;

    @Mock
    private HeadingElement editingBoxTitleMock;

    @Mock
    private UListElement propertiesContainerMock;

    private ItemEditingBoxPresenter itemEditingBoxPresenter;

    @Before
    public void setup() {
        when(viewsProviderMock.getItemEditingBox()).thenReturn(itemEditingBoxMock);
        when(itemEditingBoxMock.getEditingBoxTitle()).thenReturn(editingBoxTitleMock);
        when(itemEditingBoxMock.getPropertiesContainer()).thenReturn(propertiesContainerMock);
        when(propertyPresenterMock.getEditingPropertyFields(anyString(), anyString(), anyString())).thenReturn(propertyFieldsMock);
        when(itemEditingBoxMock.getEditingBox()).thenReturn(editingBoxMock);
        this.itemEditingBoxPresenter = spy(new ItemEditingBoxPresenter() {
            {
                this.viewsProvider = viewsProviderMock;
                this.propertyPresenter = propertyPresenterMock;
                this.collectionEditorPresenter = collectionViewPresenterMock;
            }
        });
    }

    @Test
    public void getEditingBox() {
        editingBoxMock = itemEditingBoxPresenter.getEditingBox(TEST_KEY, testInstancePropertyMap);
        verify(viewsProviderMock, times(1)).getItemEditingBox();
        verify(itemEditingBoxMock, times(1)).init(itemEditingBoxPresenter);
        verify(itemEditingBoxMock, times(1)).setKey(TEST_KEY);
        verify(editingBoxTitleMock, times(1)).setInnerText("Edit " + TEST_PROPERTYNAME);
        verify(propertiesContainerMock, times(1)).appendChild(propertyFieldsMock);
        verify(itemEditingBoxMock, times(1)).getEditingBox();
        assertNotNull(editingBoxMock);
    }

    @Test
    public void save() {
        itemEditingBoxPresenter.save(itemEditingBoxMock);
        verify(propertyPresenterMock, times(1)).updateProperties("value");
        verify(collectionViewPresenterMock, times(1)).addListItem(anyMap());
    }

    @Test
    public void close() {
        itemEditingBoxPresenter.close(itemEditingBoxMock);
        verify(editingBoxMock, times(1)).removeFromParent();
    }


}
