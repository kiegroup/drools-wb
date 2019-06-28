
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ExpressionElementType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ExpressionElementType")
public class JSIExpressionElementType {


    /**
     * Getter for <b>step</b>
     * 
     * @return
     *      <b>step</<b>
     */
    @JsProperty(name = "step")
    public final native String getStep();

    /**
     * Setter for <b>step</b>
     * 
     * @param step
     *      <b>step</<b> to set.
     */
    @JsProperty(name = "step")
    public final native void setStep(String step);
    



public static native JSIExpressionElementType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ExpressionElementType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
