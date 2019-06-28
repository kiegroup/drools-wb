
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>GenericTypes</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "GenericTypes")
public class JSIGenericTypes {


    /**
     * Getter for <b>string</b>
     * 
     * @return
     *      <b>string</<b>
     */
    @JsProperty(name = "string")
    public final native String[] getString();

    /**
     * Setter for <b>string</b>
     * 
     * @param string
     *      <b>string</<b> to set.
     */
    @JsProperty(name = "string")
    public final native void setString(String[] string);
    



public static native JSIGenericTypes newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.GenericTypes\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
