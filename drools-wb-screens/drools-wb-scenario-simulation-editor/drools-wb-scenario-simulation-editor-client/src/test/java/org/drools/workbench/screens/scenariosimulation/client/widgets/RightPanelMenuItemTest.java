package org.drools.workbench.screens.scenariosimulation.client.widgets;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.commands.RightPanelMenuCommand;
import org.drools.workbench.screens.scenariosimulation.client.resources.i18n.ScenarioSimulationEditorConstants;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.RightPanelPresenter;
import org.gwtbootstrap3.client.ui.Button;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.PlaceStatus;
import org.uberfire.mvp.impl.DefaultPlaceRequest;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class RightPanelMenuItemTest {

    private RightPanelMenuItem rightPanelMenuItem;

    @Mock
    private RightPanelMenuCommand rightPanelMenuCommand;

    @Mock
    private PlaceManager placeManager;

    @Before
    public void setup() {
        this.rightPanelMenuItem = new RightPanelMenuItem(placeManager, rightPanelMenuCommand);
        when(placeManager.getStatus(RightPanelPresenter.IDENTIFIER)).thenReturn(PlaceStatus.OPEN);
    }

    @Test
    public void init() {
        rightPanelMenuItem.init();
        verify(rightPanelMenuItem.getButton(), times(1)).setText(ScenarioSimulationEditorConstants.INSTANCE.HideRightPanel());
        verify(placeManager, times(1)).registerOnOpenCallback(any(DefaultPlaceRequest.class), any());
        verify(placeManager, times(1)).registerOnCloseCallback(any(DefaultPlaceRequest.class), any());
    }

    @Test
    public void build() {
        Widget retrieved = rightPanelMenuItem.build();
        assertNotNull(retrieved);
        assertTrue(retrieved instanceof Button);
    }
}