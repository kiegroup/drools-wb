
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ScenarioType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ScenarioType")
public class JSIScenarioType {


    /**
     * Get TYPE_NAME for <code>JSIScenarioType</code>
     * 
     * @return
     *     <b>SCESIM.ScenarioType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.ScenarioType";
    }

    /**
     * Getter for <b>factMappingValues</b>
     * 
     * @return
     *      <b>factMappingValues</<b>
     */
    @JsProperty(name = "factMappingValues")
    public final native JSIFactMappingValuesType getFactMappingValues();

    /**
     * Setter for <b>factMappingValues</b>
     * 
     * @param factMappingValues
     *      <b>factMappingValues</<b> to set.
     */
    @JsProperty(name = "factMappingValues")
    public final native void setFactMappingValues(JSIFactMappingValuesType factMappingValues);

    /**
     * Getter for <b>simulationDescriptor</b>
     * 
     * @return
     *      <b>simulationDescriptor</<b>
     */
    @JsProperty(name = "simulationDescriptor")
    public final native JSISimulationDescriptorType getSimulationDescriptor();

    /**
     * Setter for <b>simulationDescriptor</b>
     * 
     * @param simulationDescriptor
     *      <b>simulationDescriptor</<b> to set.
     */
    @JsProperty(name = "simulationDescriptor")
    public final native void setSimulationDescriptor(JSISimulationDescriptorType simulationDescriptor);
    



public static native JSIScenarioType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ScenarioType\"}";
        var retrieved = JSON.parse(json)
        console.log("retrieved " + retrieved);
        return retrieved
    }-*/;
}
