package org.drools.workbench.screens.scenariosimulation.client.factories;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.google.gwt.user.client.ui.Widget;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationView;
import org.drools.workbench.screens.scenariosimulation.client.editor.ScenarioSimulationViewImpl;
import org.drools.workbench.screens.scenariosimulation.client.handlers.ScenarioSimulationGridPanelContextMenuHandler;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGrid;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridLayer;
import org.drools.workbench.screens.scenariosimulation.client.widgets.ScenarioGridPanel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.uberfire.ext.wires.core.grids.client.widget.layer.impl.DefaultGridLayer;

import static org.drools.workbench.screens.scenariosimulation.client.factories.ScenarioSimulationViewProvider.newScenarioSimulationView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(LienzoMockitoTestRunner.class)
public class ScenarioSimulationViewProviderTest {


    @Test
    public void newScenarioSimulationViewTest() {
        ScenarioSimulationView retrieved = newScenarioSimulationView();
        assertNotNull(retrieved);
        ScenarioGridPanel scenarioGridPanel= retrieved.getScenarioGridPanel();
        assertNotNull(scenarioGridPanel);
        DefaultGridLayer defaultGridLayer = scenarioGridPanel.getDefaultGridLayer();
        assertNotNull(defaultGridLayer);
        assertTrue(defaultGridLayer instanceof ScenarioGridLayer);
        ScenarioGrid scenarioGrid = scenarioGridPanel.getScenarioGrid();
        assertNotNull(scenarioGrid);
        assertEquals(defaultGridLayer, scenarioGrid.getScenarioGridLayer());
        ScenarioSimulationGridPanelContextMenuHandler contextMenuHandler = scenarioGridPanel.getContextMenuHandler();
        assertNotNull(contextMenuHandler);
        assertEquals(defaultGridLayer, contextMenuHandler.getScenarioGridLayer());
    }
}