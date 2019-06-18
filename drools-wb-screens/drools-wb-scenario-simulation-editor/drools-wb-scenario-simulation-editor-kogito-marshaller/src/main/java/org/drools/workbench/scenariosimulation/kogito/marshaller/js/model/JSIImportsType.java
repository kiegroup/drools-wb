
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ImportsType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "ImportsType")
public class JSIImportsType {


    /**
     * Getter for <b>imports</b>
     * 
     * @return
     *      <b>imports</<b>
     */
    @JsProperty(name = "imports")
    public final native JSIWrappedImportsType getImports();

    /**
     * Setter for <b>imports</b>
     * 
     * @param imports
     *      <b>imports</<b> to set.
     */
    @JsProperty(name = "imports")
    public final native void setImports(JSIWrappedImportsType imports);

}
