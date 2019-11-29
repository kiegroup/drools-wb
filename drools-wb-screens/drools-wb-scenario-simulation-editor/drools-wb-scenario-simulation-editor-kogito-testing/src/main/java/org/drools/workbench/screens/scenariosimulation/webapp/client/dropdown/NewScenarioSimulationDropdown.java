package org.drools.workbench.screens.scenariosimulation.webapp.client.dropdown;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

import org.drools.workbench.screens.scenariosimulation.client.dropdown.ScenarioSimulationDropdownView;
import org.kie.workbench.common.widgets.client.assets.dropdown.AbstractKieAssetsDropdown;

@Dependent
public class NewScenarioSimulationDropdown extends AbstractKieAssetsDropdown {

    @Inject
    public NewScenarioSimulationDropdown(@Named(ScenarioSimulationDropdownView.BEAN_NAME) ScenarioSimulationDropdownView view, DmnAssetsDropdownProviderImpl dataProvider) {
        super(view, dataProvider);
    }
}