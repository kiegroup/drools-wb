
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>FactMappingValueType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingValueType", isNative = true)
public class JSIFactMappingValueType {

    @JsOverlay
    public final static String TYPE = "SCESIM.FactMappingValueType";

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
        toReturn.setLocalPart("FactMappingValueType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}FactMappingValueType");
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
     * Native getter for <b>factIdentifier</b>
     * 
     * @return
     *     The <b>factIdentifier</b> JSON property
     */
    @JsProperty(name = "factIdentifier")
    public native JSIFactIdentifierType getFactIdentifier();

    /**
     * Setter for <b>factIdentifier</b>
     * 
     * @param factIdentifierParam
     *      <b>factIdentifier</b> to set.
     */
    @JsProperty(name = "factIdentifier")
    public final native void setFactIdentifier(JSIFactIdentifierType factIdentifierParam);

    /**
     * Native getter for <b>expressionIdentifier</b>
     * 
     * @return
     *     The <b>expressionIdentifier</b> JSON property
     */
    @JsProperty(name = "expressionIdentifier")
    public native JSIExpressionIdentifierType getExpressionIdentifier();

    /**
     * Setter for <b>expressionIdentifier</b>
     * 
     * @param expressionIdentifierParam
     *      <b>expressionIdentifier</b> to set.
     */
    @JsProperty(name = "expressionIdentifier")
    public final native void setExpressionIdentifier(JSIExpressionIdentifierType expressionIdentifierParam);

    /**
     * Native getter for <b>rawValue</b>
     * 
     * @return
     *     The <b>rawValue</b> JSON property
     */
    @JsProperty(name = "rawValue")
    public native JSIRawValueType getRawValue();

    /**
     * Setter for <b>rawValue</b>
     * 
     * @param rawValueParam
     *      <b>rawValue</b> to set.
     */
    @JsProperty(name = "rawValue")
    public final native void setRawValue(JSIRawValueType rawValueParam);

}
