package org.drools.workbench.screens.scenariosimulation.client.collectioneditor;

import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.dom.client.Style;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.utils.ConstantHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.drools.workbench.screens.scenariosimulation.client.utils.ConstantHolder.NODE_HIDDEN;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class CollectionEditorUtilsTest {

    @Mock
    private SpanElement faAngleRightMock;

    @Mock
    private LIElement liElementMock;

    @Mock
    private Style styleMock;

    @Before
    public void setup() {
        when(liElementMock.getStyle()).thenReturn(styleMock);
    }

    @Test
    public void toggleRowExpansionToExpandTrueSpanElement() {
        commonToggleRowExpansionSpanElement(true);
    }

    @Test
    public void toggleRowExpansionToExpandFalseSpanElement() {
        commonToggleRowExpansionSpanElement(false);
    }

    @Test
    public void toggleRowExpansionToExpandTrueLIElement() {
        commonToggleRowExpansionLIElement(true);
    }

    @Test
    public void toggleRowExpansionToExpandFalseLIElement() {
        commonToggleRowExpansionLIElement(false);
    }

    @Test
    public void setSpanAttributeAttributes() {
        String dataI18nKey = "DATA_I18N_KEY";
        String innerText = "INNER_TEXT";
        String dataField = "DATA_FIELD";
        CollectionEditorUtils.setSpanAttributeAttributes(dataI18nKey, innerText, dataField, faAngleRightMock);
        verify(faAngleRightMock, times(1)).setInnerText(eq(innerText));
        verify(faAngleRightMock, times(1)).setAttribute(eq("data-i18n-key"), eq(dataI18nKey));
        verify(faAngleRightMock, times(1)).setAttribute(eq("data-field"), eq(dataField));
    }

    private void commonToggleRowExpansionSpanElement(boolean toExpand) {
        CollectionEditorUtils.toggleRowExpansion(faAngleRightMock, toExpand);
        String classToadd = toExpand ? ConstantHolder.FA_ANGLE_DOWN : ConstantHolder.FA_ANGLE_RIGHT;
        String classToRemove = toExpand ? ConstantHolder.FA_ANGLE_RIGHT : ConstantHolder.FA_ANGLE_DOWN;
        verify(faAngleRightMock, times(1)).addClassName(classToadd);
        verify(faAngleRightMock, times(1)).removeClassName(classToRemove);
    }

    private void commonToggleRowExpansionLIElement(boolean isShown) {
        CollectionEditorUtils.toggleRowExpansion(liElementMock, isShown);
        Style.Display displayToSet = isShown ? Style.Display.NONE : Style.Display.BLOCK;
        verify(styleMock, times(1)).setDisplay(displayToSet);
        if (isShown) {
            verify(liElementMock, times(1)).addClassName(NODE_HIDDEN);
        } else {
            verify(liElementMock, times(1)).removeClassName(NODE_HIDDEN);
        }
    }

}
