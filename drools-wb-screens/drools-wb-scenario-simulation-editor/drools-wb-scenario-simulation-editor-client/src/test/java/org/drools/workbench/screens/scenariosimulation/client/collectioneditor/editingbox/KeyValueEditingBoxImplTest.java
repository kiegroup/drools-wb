package org.drools.workbench.screens.scenariosimulation.client.collectioneditor.editingbox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class KeyValueEditingBoxImplTest {

    @Mock
    private KeyValueEditingBox.Presenter presenterMock;

    private KeyValueEditingBoxImpl keyValueEditingBoxImpl;

    @Before
    public void setup() {
        this.keyValueEditingBoxImpl = spy(new KeyValueEditingBoxImpl() {
            {
                this.presenter = presenterMock;
            }
        });
    }

    @Test
    public void onSaveItemClickEvent() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        keyValueEditingBoxImpl.onSaveItemClickEvent(clickEventMock);
        verify(presenterMock, times(1)).save();
        verify(keyValueEditingBoxImpl, times(1)).close(clickEventMock);
    }

    @Test
    public void close() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        keyValueEditingBoxImpl.close(clickEventMock);
        verify(presenterMock, times(1)).close(keyValueEditingBoxImpl);
        verify(clickEventMock, times(1)).stopPropagation();
    }
}
