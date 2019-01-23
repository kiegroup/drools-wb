package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class ListEditorElementViewImplTest {

    @Mock
    private ListEditorElementView.Presenter listEditorElementViewPresenterMock;

    private ListEditorElementViewImpl listEditorElementViewImpl;

    @Before
    public void setup() {
        this.listEditorElementViewImpl = spy(new ListEditorElementViewImpl() {
            {
                this.presenter = listEditorElementViewPresenterMock;
            }
        });
    }

    @Test
    public void onFaAngleRightClick() {
        ClickEvent clickEventMock = mock(ClickEvent.class);
        listEditorElementViewImpl.onFaAngleRightClick(clickEventMock);
        verify(listEditorElementViewPresenterMock, times(1)).onToggleRowExpansion(eq(listEditorElementViewImpl), anyBoolean());
    }
}
