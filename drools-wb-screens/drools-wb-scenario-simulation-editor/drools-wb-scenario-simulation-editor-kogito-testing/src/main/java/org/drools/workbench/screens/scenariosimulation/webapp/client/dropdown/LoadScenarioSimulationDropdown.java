package org.drools.workbench.screens.scenariosimulation.webapp.client.dropdown;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.drools.workbench.screens.scenariosimulation.client.dropdown.ScenarioSimulationDropdownView;
import org.kie.workbench.common.widgets.client.assets.dropdown.AbstractKieAssetsDropdown;

@Dependent
public class LoadScenarioSimulationDropdown extends AbstractKieAssetsDropdown {

    @Inject
    public LoadScenarioSimulationDropdown(ScenarioSimulationDropdownView view, AllScenarioSimulationAssetsDropdownProviderImpl dataProvider) {
        super(view, dataProvider);
    }
}