
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>SimulationType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "SimulationType")
public class JSISimulationType {


    /**
     * Getter for <b>simulationDescriptor</b>
     * 
     * @return
     *      <b>simulationDescriptor</<b>
     */
    @JsProperty
    public final native JSISimulationDescriptorType getSimulationDescriptor();

    /**
     * Setter for <b>simulationDescriptor</b>
     * 
     * @param simulationDescriptor
     *      <b>simulationDescriptor</<b> to set.
     */
    @JsProperty
    public final native void setSimulationDescriptor(JSISimulationDescriptorType simulationDescriptor);

    /**
     * Getter for <b>scenarios</b>
     * 
     * @return
     *      <b>scenarios</<b>
     */
    @JsProperty
    public final native JSIScenariosType getScenarios();

    /**
     * Setter for <b>scenarios</b>
     * 
     * @param scenarios
     *      <b>scenarios</<b> to set.
     */
    @JsProperty
    public final native void setScenarios(JSIScenariosType scenarios);

}
