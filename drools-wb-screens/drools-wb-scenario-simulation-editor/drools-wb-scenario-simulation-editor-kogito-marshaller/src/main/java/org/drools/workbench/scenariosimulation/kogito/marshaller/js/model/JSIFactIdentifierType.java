
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>FactIdentifierType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactIdentifierType", isNative = true)
public class JSIFactIdentifierType {

    @JsOverlay
    public final static String TYPE = "SCESIM.FactIdentifierType";

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
        toReturn.setLocalPart("factIdentifierType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}factIdentifierType");
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
     * Native getter for <b>name</b>
     * 
     * @return
     *     The <b>name</b> JSON property
     */
    @JsProperty(name = "name")
    public native String getName();

    /**
     * Setter for <b>name</b>
     * 
     * @param nameParam
     *      <b>name</b> to set.
     */
    @JsProperty(name = "name")
    public final native void setName(String nameParam);

    /**
     * Native getter for <b>className</b>
     * 
     * @return
     *     The <b>className</b> JSON property
     */
    @JsProperty(name = "className")
    public native String getClassName();

    /**
     * Setter for <b>className</b>
     * 
     * @param classNameParam
     *      <b>className</b> to set.
     */
    @JsProperty(name = "className")
    public final native void setClassName(String classNameParam);

    /**
     * Native getter for <b>importPrefix</b>
     * 
     * @return
     *     The <b>importPrefix</b> JSON property
     */
    @JsProperty(name = "importPrefix")
    public native String getImportPrefix();

    /**
     * Setter for <b>importPrefix</b>
     * 
     * @param importPrefixParam
     *      <b>importPrefix</b> to set.
     */
    @JsProperty(name = "importPrefix")
    public final native void setImportPrefix(String importPrefixParam);

}
