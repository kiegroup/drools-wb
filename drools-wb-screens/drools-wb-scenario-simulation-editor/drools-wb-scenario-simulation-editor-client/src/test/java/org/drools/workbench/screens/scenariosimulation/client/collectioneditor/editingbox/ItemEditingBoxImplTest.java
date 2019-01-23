package org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.collectioneditor.CollectionViewImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class ItemEditingBoxImplTest {

    @Mock
    private ItemEditingBox.Presenter presenterMock;

    private ItemEditingBoxImpl itemEditingBoxImpl;

    @Before
    public void setup() {
        this.itemEditingBoxImpl = spy(new ItemEditingBoxImpl() {
            {
                this.presenter = presenterMock;
            }
        });
    }

    @Test
    public void onSaveItemClickEvent() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        itemEditingBoxImpl.onSaveItemClickEvent(clickEventMock);
        verify(presenterMock, times(1)).save(itemEditingBoxImpl);
        verify(itemEditingBoxImpl, times(1)).close(clickEventMock);
    }

    @Test
    public void close() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        itemEditingBoxImpl.close(clickEventMock);
        verify(presenterMock, times(1)).close(itemEditingBoxImpl);
        verify(clickEventMock, times(1)).stopPropagation();
    }
}
