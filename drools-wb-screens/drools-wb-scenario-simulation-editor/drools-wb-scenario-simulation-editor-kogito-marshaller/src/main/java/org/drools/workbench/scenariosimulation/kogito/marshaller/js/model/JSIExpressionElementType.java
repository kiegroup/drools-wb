
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>ExpressionElementType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIExpressionElementType", isNative = true)
public class JSIExpressionElementType {

    @JsOverlay
    public final static String TYPE = "SCESIM.ExpressionElementType";

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
        toReturn.setLocalPart("ExpressionElementType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}ExpressionElementType");
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
     * Native getter for <b>step</b>
     * 
     * @return
     *     The <b>step</b> JSON property
     */
    @JsProperty(name = "step")
    public native String getStep();

    /**
     * Setter for <b>step</b>
     * 
     * @param stepParam
     *      <b>step</b> to set.
     */
    @JsProperty(name = "step")
    public final native void setStep(String stepParam);

}
