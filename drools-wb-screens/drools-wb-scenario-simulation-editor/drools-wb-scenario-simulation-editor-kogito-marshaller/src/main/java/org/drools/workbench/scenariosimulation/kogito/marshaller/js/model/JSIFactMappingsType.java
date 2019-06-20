
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.JsArrayLike;


/**
 * JSInterop adapter for <code>FactMappingsType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "FactMappingsType")
public class JSIFactMappingsType {


    /**
     * Get TYPE_NAME for <code>JSIFactMappingsType</code>
     * 
     * @return
     *     <b>SCESIM.FactMappingsType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.FactMappingsType";
    }

    /**
     * Getter for <b>factMapping</b>
     * 
     * @return
     *      <b>factMapping</<b>
     */
    @JsProperty(name = "factMapping")
    public final native JsArrayLike<JSIFactMappingType> getFactMapping();

    /**
     * Setter for <b>factMapping</b>
     * 
     * @param factMapping
     *      <b>factMapping</<b> to set.
     */
    @JsProperty(name = "factMapping")
    public final native void setFactMapping(JsArrayLike<JSIFactMappingType> factMapping);
    



public static native JSIFactMappingsType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.FactMappingsType\"}";
        var retrieved = JSON.parse(json)
        console.log("retrieved " + retrieved);
        return retrieved
    }-*/;
}
