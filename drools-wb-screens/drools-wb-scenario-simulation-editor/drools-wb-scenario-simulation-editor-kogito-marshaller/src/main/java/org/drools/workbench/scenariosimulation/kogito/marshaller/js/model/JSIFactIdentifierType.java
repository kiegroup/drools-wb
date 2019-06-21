
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>FactIdentifierType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "FactIdentifierType")
public class JSIFactIdentifierType {


    /**
     * Get TYPE_NAME for <code>JSIFactIdentifierType</code>
     * 
     * @return
     *     <b>SCESIM.FactIdentifierType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.FactIdentifierType";
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
     * Getter for <b>className</b>
     * 
     * @return
     *      <b>className</<b>
     */
    @JsProperty(name = "className")
    public final native String getClassName();

    /**
     * Setter for <b>className</b>
     * 
     * @param className
     *      <b>className</<b> to set.
     */
    @JsProperty(name = "className")
    public final native void setClassName(String className);

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
    



public static native JSIFactIdentifierType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.FactIdentifierType\"}";
        var retrieved = JSON.parse(json)
        return retrieved
    }-*/;
}
