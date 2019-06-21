
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ScenarioSimulationModelType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ScenarioSimulationModelType")
public class JSIScenarioSimulationModelType {


    /**
     * Get TYPE_NAME for <code>JSIScenarioSimulationModelType</code>
     * 
     * @return
     *     <b>SCESIM.ScenarioSimulationModelType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.ScenarioSimulationModelType";
    }

    /**
     * Getter for <b>simulation</b>
     * 
     * @return
     *      <b>simulation</<b>
     */
    @JsProperty(name = "simulation")
    public final native JSISimulationType getSimulation();

    /**
     * Setter for <b>simulation</b>
     * 
     * @param simulation
     *      <b>simulation</<b> to set.
     */
    @JsProperty(name = "simulation")
    public final native void setSimulation(JSISimulationType simulation);

    /**
     * Getter for <b>imports</b>
     * 
     * @return
     *      <b>imports</<b>
     */
    @JsProperty(name = "imports")
    public final native JSIImportsType getImports();

    /**
     * Setter for <b>imports</b>
     * 
     * @param imports
     *      <b>imports</<b> to set.
     */
    @JsProperty(name = "imports")
    public final native void setImports(JSIImportsType imports);

    /**
     * Getter for <b>version</b>
     * 
     * @return
     *      <b>version</<b>
     */
    @JsProperty(name = "version")
    public final native String getVersion();

    /**
     * Setter for <b>version</b>
     * 
     * @param version
     *      <b>version</<b> to set.
     */
    @JsProperty(name = "version")
    public final native void setVersion(String version);
    



public static native JSIScenarioSimulationModelType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ScenarioSimulationModelType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
