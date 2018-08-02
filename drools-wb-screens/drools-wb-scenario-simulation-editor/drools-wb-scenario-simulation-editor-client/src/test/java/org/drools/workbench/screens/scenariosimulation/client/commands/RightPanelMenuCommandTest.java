package org.drools.workbench.screens.scenariosimulation.client.commands;

import com.google.gwtmockito.GwtMockitoTestRunner;
import org.drools.workbench.screens.scenariosimulation.client.rightpanel.RightPanelPresenter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.mvp.PlaceStatus;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(GwtMockitoTestRunner.class)
public class RightPanelMenuCommandTest {

    private RightPanelMenuCommand rightPanelMenuCommand;

    @Mock
    private PlaceManager placeManager;

    @Before
    public void setup() {
        this.rightPanelMenuCommand = new RightPanelMenuCommand(placeManager);
    }

    @Test
    public void execute() {
        when(placeManager.getStatus(RightPanelPresenter.IDENTIFIER)).thenReturn(PlaceStatus.OPEN);
        rightPanelMenuCommand.execute();
        verify(placeManager, times(1)).closePlace(RightPanelPresenter.IDENTIFIER);
        when(placeManager.getStatus(RightPanelPresenter.IDENTIFIER)).thenReturn(PlaceStatus.CLOSE);
        rightPanelMenuCommand.execute();
        verify(placeManager, times(1)).goTo(RightPanelPresenter.IDENTIFIER);
    }
}