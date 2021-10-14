
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>ScesimModelDescriptorType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIScesimModelDescriptorType", isNative = true)
public class JSIScesimModelDescriptorType {

    @JsOverlay
    public final static String TYPE = "SCESIM.ScesimModelDescriptorType";

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
        toReturn.setLocalPart("scesimModelDescriptorType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}scesimModelDescriptorType");
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
     * Native getter for <b>factMappings</b>
     * 
     * @return
     *     The <b>factMappings</b> JSON property
     */
    @JsProperty(name = "factMappings")
    public native JSIFactMappingsType getFactMappings();

    /**
     * Setter for <b>factMappings</b>
     * 
     * @param factMappingsParam
     *      <b>factMappings</b> to set.
     */
    @JsProperty(name = "factMappings")
    public final native void setFactMappings(JSIFactMappingsType factMappingsParam);

}
