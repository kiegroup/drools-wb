
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.JsArrayLike;


/**
 * JSInterop adapter for <code>ExpressionElementsType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ExpressionElementsType")
public class JSIExpressionElementsType {


    /**
     * Get TYPE_NAME for <code>JSIExpressionElementsType</code>
     * 
     * @return
     *     <b>SCESIM.ExpressionElementsType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.ExpressionElementsType";
    }

    /**
     * Getter for <b>expressionElement</b>
     * 
     * @return
     *      <b>expressionElement</<b>
     */
    @JsProperty(name = "expressionElement")
    public final native JsArrayLike<JSIExpressionElementType> getExpressionElement();

    /**
     * Setter for <b>expressionElement</b>
     * 
     * @param expressionElement
     *      <b>expressionElement</<b> to set.
     */
    @JsProperty(name = "expressionElement")
    public final native void setExpressionElement(JsArrayLike<JSIExpressionElementType> expressionElement);
    



public static native JSIExpressionElementsType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ExpressionElementsType\"}";
        var retrieved = JSON.parse(json)
        console.log("retrieved " + retrieved);
        return retrieved
    }-*/;
}
