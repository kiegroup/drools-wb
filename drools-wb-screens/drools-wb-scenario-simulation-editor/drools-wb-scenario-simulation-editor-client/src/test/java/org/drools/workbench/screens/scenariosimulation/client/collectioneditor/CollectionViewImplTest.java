package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.events.CloseCompositeEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class CollectionViewImplTest {

    private CollectionViewImpl collectionEditorViewImpl;

    @Mock
    private CollectionPresenter collectionEditorPresenterMock;

    @Before
    public void setup() {
        this.collectionEditorViewImpl = spy(new CollectionViewImpl() {
            {
                this.presenter = collectionEditorPresenterMock;
            }
        });
    }

    @Test
    public void setValue() {
        String testValue = "TEST-JSON";
        collectionEditorViewImpl.setValue(testValue);
        verify(collectionEditorPresenterMock, times(1)).setValue(eq(testValue));
    }

    @Test
    public void onCloseCollectionEditorButtonClick() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        collectionEditorViewImpl.onCloseCollectionEditorButtonClick(clickEventMock);
        verify(collectionEditorViewImpl, times(1)).fireEvent(isA(CloseCompositeEvent.class));
    }
}
