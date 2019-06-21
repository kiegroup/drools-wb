
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ExpressionIdentifierType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ExpressionIdentifierType")
public class JSIExpressionIdentifierType {


    /**
     * Get TYPE_NAME for <code>JSIExpressionIdentifierType</code>
     * 
     * @return
     *     <b>SCESIM.ExpressionIdentifierType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.ExpressionIdentifierType";
    }

    /**
     * Getter for <b>name</b>
     * 
     * @return
     *      <b>name</<b>
     */
    @JsProperty(name = "name")
    public final native String getName();

    /**
     * Setter for <b>name</b>
     * 
     * @param name
     *      <b>name</<b> to set.
     */
    @JsProperty(name = "name")
    public final native void setName(String name);

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
    



public static native JSIExpressionIdentifierType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ExpressionIdentifierType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
