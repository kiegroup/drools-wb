
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>FactMappingsType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "FactMappingsType")
public class JSIFactMappingsType {


    /**
     * Getter for <b>factMapping</b>
     * 
     * @return
     *      <b>factMapping</<b>
     */
    @JsProperty(name = "factMapping")
    public final native JSIFactMappingType[] getFactMapping();

    /**
     * Setter for <b>factMapping</b>
     * 
     * @param factMapping
     *      <b>factMapping</<b> to set.
     */
    @JsProperty(name = "factMapping")
    public final native void setFactMapping(JSIFactMappingType[] factMapping);

}
