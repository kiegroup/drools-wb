package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class ObjectSeparatorTest {

    @Mock
    private CollectionEditorPresenter collectionEditorPresenterMock;

    private ObjectSeparator objectSeparator;

    @Before
    public void setup() {
        this.objectSeparator = spy(new ObjectSeparator() {
            {
                this.collectionEditorPresenter = collectionEditorPresenterMock;
            }
        });
    }

    @Test
    public void onAddItemButtonClick() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        objectSeparator.onAddItemButtonClick(clickEventMock);
        verify(collectionEditorPresenterMock, times(1)).showEditingBox(anyString());
    }
}
