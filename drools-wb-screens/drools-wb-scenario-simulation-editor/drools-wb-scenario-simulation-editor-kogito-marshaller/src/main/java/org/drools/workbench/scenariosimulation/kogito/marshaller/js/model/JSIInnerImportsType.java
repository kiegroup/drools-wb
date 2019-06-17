
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>InnerImportsType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "InnerImportsType")
public class JSIInnerImportsType {


    /**
     * Getter for <b>_import</b>
     * 
     * @return
     *      <b>_import</<b>
     */
    @JsProperty
    public final native JSIImportType[] getImport();

    /**
     * Setter for <b>_import</b>
     * 
     * @param _import
     *      <b>_import</<b> to set.
     */
    @JsProperty
    public final native void setImport(JSIImportType[] _import);

}
