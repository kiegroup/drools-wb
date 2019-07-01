
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
    



public static native JSIScenarioType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ScenarioType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
