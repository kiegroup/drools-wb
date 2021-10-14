
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>ImportsType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIImportsType", isNative = true)
public class JSIImportsType {

    @JsOverlay
    public final static String TYPE = "SCESIM.ImportsType";

    @JsOverlay
    public static boolean instanceOf(final Object instance) {
        return TYPE.equals(JsUtils.getTypeName(instance));
    }

    /**
     * Getter for specific <code>JSIName</code>
     * 
     * @return
     *     Getter for specific <code>JSIName</code>
     */
    @JsOverlay
    public static JSIName getJSIName() {
        JSIName toReturn = new JSIName();
        toReturn.setNamespaceURI("");
        toReturn.setLocalPart("importsType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}importsType");
        return toReturn;
    }

    /**
     * Native getter for <b>TYPE_NAME</b>
     * 
     * @return
     *     The <b>TYPE_NAME</b> JSON property
     */
    @JsProperty(name = "TYPE_NAME")
    public native String getTYPE_NAME();

    /**
     * Native getter for <b>imports</b>
     * 
     * @return
     *     The <b>imports</b> JSON property
     */
    @JsProperty(name = "imports")
    public native JSIWrappedImportsType getImports();

    /**
     * Setter for <b>imports</b>
     * 
     * @param importsParam
     *      <b>imports</b> to set.
     */
    @JsProperty(name = "imports")
    public final native void setImports(JSIWrappedImportsType importsParam);

}
