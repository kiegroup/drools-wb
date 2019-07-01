
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>FactMappingValueType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "FactMappingValueType")
public class JSIFactMappingValueType {


    /**
     * Getter for <b>factIdentifier</b>
     * 
     * @return
     *      <b>factIdentifier</<b>
     */
    @JsProperty(name = "factIdentifier")
    public final native JSIFactIdentifierType getFactIdentifier();

    /**
     * Setter for <b>factIdentifier</b>
     * 
     * @param factIdentifier
     *      <b>factIdentifier</<b> to set.
     */
    @JsProperty(name = "factIdentifier")
    public final native void setFactIdentifier(JSIFactIdentifierType factIdentifier);

    /**
     * Getter for <b>expressionIdentifier</b>
     * 
     * @return
     *      <b>expressionIdentifier</<b>
     */
    @JsProperty(name = "expressionIdentifier")
    public final native JSIExpressionIdentifierType getExpressionIdentifier();

    /**
     * Setter for <b>expressionIdentifier</b>
     * 
     * @param expressionIdentifier
     *      <b>expressionIdentifier</<b> to set.
     */
    @JsProperty(name = "expressionIdentifier")
    public final native void setExpressionIdentifier(JSIExpressionIdentifierType expressionIdentifier);

    /**
     * Getter for <b>rawValue</b>
     * 
     * @return
     *      <b>rawValue</<b>
     */
    @JsProperty(name = "rawValue")
    public final native JSIRawValueType getRawValue();

    /**
     * Setter for <b>rawValue</b>
     * 
     * @param rawValue
     *      <b>rawValue</<b> to set.
     */
    @JsProperty(name = "rawValue")
    public final native void setRawValue(JSIRawValueType rawValue);
    



public static native JSIFactMappingValueType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.FactMappingValueType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
