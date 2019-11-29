package org.drools.workbench.screens.scenariosimulation.webapp.client.dropdown;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.drools.workbench.screens.scenariosimulation.client.dropdown.ScenarioSimulationDropdownView;
import org.kie.workbench.common.widgets.client.assets.dropdown.AbstractKieAssetsDropdown;

@Dependent
public class LoadScenarioSimulationDropdown extends AbstractKieAssetsDropdown {

    @Inject
    public LoadScenarioSimulationDropdown(@Named(ScenarioSimulationDropdownView.BEAN_NAME) ScenarioSimulationDropdownView view, ScenarioSimulationAssetsDropdownProviderImpl dataProvider) {
        super(view, dataProvider);
    }
}