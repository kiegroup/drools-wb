package org.drools.workbench.screens.scenariosimulation.businesscentral.client.editor;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.businesscentral.client.rightpanel.coverage.CoverageElementView;
import org.drools.workbench.screens.scenariosimulation.businesscentral.client.rightpanel.coverage.CoverageScenarioListView;
import org.jboss.errai.ioc.client.api.ManagedInstance;

/**
 * Class used as Provider for <i>Views</i> that has to be dynamically created
 */
@ApplicationScoped
public class ScenarioSimulationBusinessCentralViewsProvider {

    @Inject
    private ManagedInstance<CoverageElementView> decisionElementViewInstance;

    @Inject
    private ManagedInstance<CoverageScenarioListView> coverageScenarioListView;

    public CoverageElementView getCoverageElementView() {
        return decisionElementViewInstance.get();
    }

    public CoverageScenarioListView getCoverageScenarioListView() {
        return coverageScenarioListView.get();
    }
}
