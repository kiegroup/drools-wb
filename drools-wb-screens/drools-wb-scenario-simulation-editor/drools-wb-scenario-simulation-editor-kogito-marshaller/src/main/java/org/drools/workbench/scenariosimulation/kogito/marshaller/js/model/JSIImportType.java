
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ImportType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ImportType")
public class JSIImportType {


    /**
     * Get TYPE_NAME for <code>JSIImportType</code>
     * 
     * @return
     *     <b>SCESIM.ImportType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.ImportType";
    }

    /**
     * Getter for <b>type</b>
     * 
     * @return
     *      <b>type</<b>
     */
    @JsProperty(name = "type")
    public final native String getType();

    /**
     * Setter for <b>type</b>
     * 
     * @param type
     *      <b>type</<b> to set.
     */
    @JsProperty(name = "type")
    public final native void setType(String type);
    



public static native JSIImportType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ImportType\"}";
        var retrieved = JSON.parse(json)
        console.log("retrieved " + retrieved);
        return retrieved
    }-*/;
}
