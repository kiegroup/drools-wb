
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>RawValueType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIRawValueType", isNative = true)
public class JSIRawValueType {

    @JsOverlay
    public final static String TYPE = "SCESIM.RawValueType";

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
        toReturn.setLocalPart("rawValueType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}rawValueType");
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
     * Native getter for <b>value</b>
     * 
     * @return
     *     The <b>value</b> JSON property
     */
    @JsProperty(name = "value")
    public native String getValue();

    /**
     * Setter for <b>value</b>
     * 
     * @param valueParam
     *      <b>value</b> to set.
     */
    @JsProperty(name = "value")
    public final native void setValue(String valueParam);

    /**
     * Native getter for <b>clazz</b>
     * 
     * @return
     *     The <b>clazz</b> JSON property
     */
    @JsProperty(name = "clazz")
    public native String getClazz();

    /**
     * Setter for <b>clazz</b>
     * 
     * @param clazzParam
     *      <b>clazz</b> to set.
     */
    @JsProperty(name = "clazz")
    public final native void setClazz(String clazzParam);

}
