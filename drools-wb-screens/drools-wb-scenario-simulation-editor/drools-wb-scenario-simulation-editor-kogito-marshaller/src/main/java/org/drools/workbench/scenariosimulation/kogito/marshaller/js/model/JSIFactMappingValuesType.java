
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>FactMappingValuesType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "FactMappingValuesType")
public class JSIFactMappingValuesType {


    /**
     * Getter for <b>factMappingValue</b>
     * 
     * @return
     *      <b>factMappingValue</<b>
     */
    @JsProperty(name = "factMappingValue")
    public final native JSIFactMappingValueType[] getFactMappingValue();

    /**
     * Setter for <b>factMappingValue</b>
     * 
     * @param factMappingValue
     *      <b>factMappingValue</<b> to set.
     */
    @JsProperty(name = "factMappingValue")
    public final native void setFactMappingValue(JSIFactMappingValueType[] factMappingValue);

}
