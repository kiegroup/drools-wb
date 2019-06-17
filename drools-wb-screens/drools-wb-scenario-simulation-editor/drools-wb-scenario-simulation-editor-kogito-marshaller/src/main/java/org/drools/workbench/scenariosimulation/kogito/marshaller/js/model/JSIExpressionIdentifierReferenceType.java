
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ExpressionIdentifierReferenceType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "ExpressionIdentifierReferenceType")
public class JSIExpressionIdentifierReferenceType {


    /**
     * Getter for <b>reference</b>
     * 
     * @return
     *      <b>reference</<b>
     */
    @JsProperty
    public final native String getReference();

    /**
     * Setter for <b>reference</b>
     * 
     * @param reference
     *      <b>reference</<b> to set.
     */
    @JsProperty
    public final native void setReference(String reference);

}
