
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ScenarioType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "ScenarioType")
public class JSIScenarioType {


    /**
     * Getter for <b>factMappingValues</b>
     * 
     * @return
     *      <b>factMappingValues</<b>
     */
    @JsProperty
    public final native JSIFactMappingValuesType getFactMappingValues();

    /**
     * Setter for <b>factMappingValues</b>
     * 
     * @param factMappingValues
     *      <b>factMappingValues</<b> to set.
     */
    @JsProperty
    public final native void setFactMappingValues(JSIFactMappingValuesType factMappingValues);

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

}
