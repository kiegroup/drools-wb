
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ScenarioSimulationModelType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "ScenarioSimulationModelType")
public class JSIScenarioSimulationModelType {


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
    public final native Double getVersion();

    /**
     * Setter for <b>version</b>
     * 
     * @param version
     *      <b>version</<b> to set.
     */
    @JsProperty(name = "version")
    public final native void setVersion(Double version);

}
