
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>SimulationDescriptorReferenceType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "SimulationDescriptorReferenceType")
public class JSISimulationDescriptorReferenceType {


    /**
     * Getter for <b>reference</b>
     * 
     * @return
     *      <b>reference</<b>
     */
    @JsProperty(name = "reference")
    public final native String getReference();

    /**
     * Setter for <b>reference</b>
     * 
     * @param reference
     *      <b>reference</<b> to set.
     */
    @JsProperty(name = "reference")
    public final native void setReference(String reference);
    



public static native JSISimulationDescriptorReferenceType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.SimulationDescriptorReferenceType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
