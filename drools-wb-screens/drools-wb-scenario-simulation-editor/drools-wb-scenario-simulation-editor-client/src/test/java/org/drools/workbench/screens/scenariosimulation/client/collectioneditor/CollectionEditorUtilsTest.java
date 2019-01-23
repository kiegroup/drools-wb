package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.dom.client.SpanElement;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.utils.ConstantHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(GwtMockitoTestRunner.class)
public class CollectionEditorUtilsTest {

    @Mock
    private SpanElement faAngleRightMock;

    private CollectionEditorUtils collectionEditorUtils;

    @Before
    public void setup() {
        this.collectionEditorUtils = spy(new CollectionEditorUtils() {
            {
            }
        });
    }

    @Test
    public void toggleRowExpansionToExpandTrue() {
        collectionEditorUtils.toggleRowExpansion(faAngleRightMock, true);
        verify(faAngleRightMock, times(1)).addClassName(ConstantHolder.FA_ANGLE_DOWN);
        verify(faAngleRightMock, times(1)).removeClassName(ConstantHolder.FA_ANGLE_RIGHT);
    }

    @Test
    public void toggleRowExpansionToExpandFalse() {
        collectionEditorUtils.toggleRowExpansion(faAngleRightMock, false);
        verify(faAngleRightMock, times(1)).addClassName(ConstantHolder.FA_ANGLE_RIGHT);
        verify(faAngleRightMock, times(1)).removeClassName(ConstantHolder.FA_ANGLE_DOWN);
    }

}
