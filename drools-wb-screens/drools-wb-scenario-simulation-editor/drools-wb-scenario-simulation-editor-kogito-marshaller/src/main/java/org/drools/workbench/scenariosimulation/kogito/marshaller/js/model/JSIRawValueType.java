
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>RawValueType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "RawValueType")
public class JSIRawValueType {


    /**
     * Getter for <b>value</b>
     * 
     * @return
     *      <b>value</<b>
     */
    @JsProperty(name = "value")
    public final native String getValue();

    /**
     * Setter for <b>value</b>
     * 
     * @param value
     *      <b>value</<b> to set.
     */
    @JsProperty(name = "value")
    public final native void setValue(String value);

    /**
     * Getter for <b>clazz</b>
     * 
     * @return
     *      <b>clazz</<b>
     */
    @JsProperty(name = "clazz")
    public final native String getClazz();

    /**
     * Setter for <b>clazz</b>
     * 
     * @param clazz
     *      <b>clazz</<b> to set.
     */
    @JsProperty(name = "clazz")
    public final native void setClazz(String clazz);
    



public static native JSIRawValueType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.RawValueType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
