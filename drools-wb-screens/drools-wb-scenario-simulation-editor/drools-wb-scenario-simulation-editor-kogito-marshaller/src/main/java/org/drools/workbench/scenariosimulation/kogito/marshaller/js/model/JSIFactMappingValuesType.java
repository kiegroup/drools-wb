
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.JsArrayLike;


/**
 * JSInterop adapter for <code>FactMappingValuesType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "FactMappingValuesType")
public class JSIFactMappingValuesType {


    /**
     * Get TYPE_NAME for <code>JSIFactMappingValuesType</code>
     * 
     * @return
     *     <b>SCESIM.FactMappingValuesType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.FactMappingValuesType";
    }

    /**
     * Getter for <b>factMappingValue</b>
     * 
     * @return
     *      <b>factMappingValue</<b>
     */
    @JsProperty(name = "factMappingValue")
    public final native JsArrayLike<JSIFactMappingValueType> getFactMappingValue();

    /**
     * Setter for <b>factMappingValue</b>
     * 
     * @param factMappingValue
     *      <b>factMappingValue</<b> to set.
     */
    @JsProperty(name = "factMappingValue")
    public final native void setFactMappingValue(JsArrayLike<JSIFactMappingValueType> factMappingValue);
    



public static native JSIFactMappingValuesType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.FactMappingValuesType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
