
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ExpressionIdentifierReferenceType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ExpressionIdentifierReferenceType")
public class JSIExpressionIdentifierReferenceType {


    /**
     * Get TYPE_NAME for <code>JSIExpressionIdentifierReferenceType</code>
     * 
     * @return
     *     <b>SCESIM.ExpressionIdentifierReferenceType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.ExpressionIdentifierReferenceType";
    }

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
    



public static native JSIExpressionIdentifierReferenceType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ExpressionIdentifierReferenceType\"}";
        var retrieved = JSON.parse(json)
        console.log("retrieved " + retrieved);
        return retrieved
    }-*/;
}
