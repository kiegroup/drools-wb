
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop container for <code>org.drools.workbench.scenariosimulation.kogito.marshaller.js.model</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class SCESIM {


    /**
     * Native getter for <b>name</b>
     * 
     * @return
     *     The <b>name</b> JSON property
     */
    @JsProperty(name = "name")
    public native String getName();

    /**
     * Native getter for <b>ScenarioSimulationModel</b>
     * 
     * @return
     *     The <b>ScenarioSimulationModel</b> JSON property
     */
    @JsProperty(name = "ScenarioSimulationModel")
    public native JSIScenarioSimulationModelType getScenarioSimulationModel();

    /**
     * Setter for <b>ScenarioSimulationModel</b>
     * 
     * @param ScenarioSimulationModelParam
     *      <b>ScenarioSimulationModel</b> to set.
     */
    @JsProperty(name = "ScenarioSimulationModel")
    public final native void setScenarioSimulationModel(JSIScenarioSimulationModelType ScenarioSimulationModelParam);

}
