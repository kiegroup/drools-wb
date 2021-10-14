
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>ImportType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIImportType", isNative = true)
public class JSIImportType {

    @JsOverlay
    public final static String TYPE = "SCESIM.ImportType";

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
        toReturn.setLocalPart("ImportType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}ImportType");
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
     * Native getter for <b>type</b>
     * 
     * @return
     *     The <b>type</b> JSON property
     */
    @JsProperty(name = "type")
    public native String getType();

    /**
     * Setter for <b>type</b>
     * 
     * @param typeParam
     *      <b>type</b> to set.
     */
    @JsProperty(name = "type")
    public final native void setType(String typeParam);

}
