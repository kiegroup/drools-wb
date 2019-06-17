
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
    @JsProperty
    public final native JSISimulationType getSimulation();

    /**
     * Setter for <b>simulation</b>
     * 
     * @param simulation
     *      <b>simulation</<b> to set.
     */
    @JsProperty
    public final native void setSimulation(JSISimulationType simulation);

    /**
     * Getter for <b>imports</b>
     * 
     * @return
     *      <b>imports</<b>
     */
    @JsProperty
    public final native JSIImportsType getImports();

    /**
     * Setter for <b>imports</b>
     * 
     * @param imports
     *      <b>imports</<b> to set.
     */
    @JsProperty
    public final native void setImports(JSIImportsType imports);

    /**
     * Getter for <b>version</b>
     * 
     * @return
     *      <b>version</<b>
     */
    @JsProperty
    public final native Float getVersion();

    /**
     * Setter for <b>version</b>
     * 
     * @param version
     *      <b>version</<b> to set.
     */
    @JsProperty
    public final native void setVersion(Float version);

}
