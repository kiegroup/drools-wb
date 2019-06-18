
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>WrappedImportsType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "WrappedImportsType")
public class JSIWrappedImportsType {


    /**
     * Getter for <b>_import</b>
     * 
     * @return
     *      <b>_import</<b>
     */
    @JsProperty(name = "_import")
    public final native JSIImportType[] getImport();

    /**
     * Setter for <b>_import</b>
     * 
     * @param _import
     *      <b>_import</<b> to set.
     */
    @JsProperty(name = "_import")
    public final native void setImport(JSIImportType[] _import);

}
