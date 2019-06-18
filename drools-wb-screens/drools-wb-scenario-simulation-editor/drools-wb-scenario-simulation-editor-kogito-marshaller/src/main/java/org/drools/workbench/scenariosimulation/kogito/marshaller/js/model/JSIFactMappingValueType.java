
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>FactMappingValueType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "FactMappingValueType")
public class JSIFactMappingValueType {


    /**
     * Getter for <b>factIdentifier</b>
     * 
     * @return
     *      <b>factIdentifier</<b>
     */
    @JsProperty(name = "factIdentifier")
    public final native JSIFactIdentifierReferenceType getFactIdentifier();

    /**
     * Setter for <b>factIdentifier</b>
     * 
     * @param factIdentifier
     *      <b>factIdentifier</<b> to set.
     */
    @JsProperty(name = "factIdentifier")
    public final native void setFactIdentifier(JSIFactIdentifierReferenceType factIdentifier);

    /**
     * Getter for <b>expressionIdentifier</b>
     * 
     * @return
     *      <b>expressionIdentifier</<b>
     */
    @JsProperty(name = "expressionIdentifier")
    public final native JSIExpressionIdentifierReferenceType getExpressionIdentifier();

    /**
     * Setter for <b>expressionIdentifier</b>
     * 
     * @param expressionIdentifier
     *      <b>expressionIdentifier</<b> to set.
     */
    @JsProperty(name = "expressionIdentifier")
    public final native void setExpressionIdentifier(JSIExpressionIdentifierReferenceType expressionIdentifier);

    /**
     * Getter for <b>rawValue</b>
     * 
     * @return
     *      <b>rawValue</<b>
     */
    @JsProperty(name = "rawValue")
    public final native String getRawValue();

    /**
     * Setter for <b>rawValue</b>
     * 
     * @param rawValue
     *      <b>rawValue</<b> to set.
     */
    @JsProperty(name = "rawValue")
    public final native void setRawValue(String rawValue);

}
