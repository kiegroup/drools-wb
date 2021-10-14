
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>FactMappingType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingType", isNative = true)
public class JSIFactMappingType {

    @JsOverlay
    public final static String TYPE = "SCESIM.FactMappingType";

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
        toReturn.setLocalPart("FactMappingType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}FactMappingType");
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
     * Native getter for <b>expressionElements</b>
     * 
     * @return
     *     The <b>expressionElements</b> JSON property
     */
    @JsProperty(name = "expressionElements")
    public native JSIExpressionElementsType getExpressionElements();

    /**
     * Setter for <b>expressionElements</b>
     * 
     * @param expressionElementsParam
     *      <b>expressionElements</b> to set.
     */
    @JsProperty(name = "expressionElements")
    public final native void setExpressionElements(JSIExpressionElementsType expressionElementsParam);

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
     * Native getter for <b>factAlias</b>
     * 
     * @return
     *     The <b>factAlias</b> JSON property
     */
    @JsProperty(name = "factAlias")
    public native String getFactAlias();

    /**
     * Setter for <b>factAlias</b>
     * 
     * @param factAliasParam
     *      <b>factAlias</b> to set.
     */
    @JsProperty(name = "factAlias")
    public final native void setFactAlias(String factAliasParam);

    /**
     * Native getter for <b>expressionAlias</b>
     * 
     * @return
     *     The <b>expressionAlias</b> JSON property
     */
    @JsProperty(name = "expressionAlias")
    public native String getExpressionAlias();

    /**
     * Setter for <b>expressionAlias</b>
     * 
     * @param expressionAliasParam
     *      <b>expressionAlias</b> to set.
     */
    @JsProperty(name = "expressionAlias")
    public final native void setExpressionAlias(String expressionAliasParam);

    /**
     * Native getter for <b>genericTypes</b>
     * 
     * @return
     *     The <b>genericTypes</b> JSON property
     */
    @JsProperty(name = "genericTypes")
    public native JSIGenericTypes getGenericTypes();

    /**
     * Setter for <b>genericTypes</b>
     * 
     * @param genericTypesParam
     *      <b>genericTypes</b> to set.
     */
    @JsProperty(name = "genericTypes")
    public final native void setGenericTypes(JSIGenericTypes genericTypesParam);

    /**
     * Native getter for <b>columnWidth</b>
     * 
     * @return
     *     The <b>columnWidth</b> JSON property
     */
    @JsProperty(name = "columnWidth")
    public native double getColumnWidth();

    /**
     * Setter for <b>columnWidth</b>
     * 
     * @param columnWidthParam
     *      <b>columnWidth</b> to set.
     */
    @JsProperty(name = "columnWidth")
    public final native void setColumnWidth(double columnWidthParam);

    /**
     * Native getter for <b>factMappingValueType</b>
     * 
     * @return
     *     The <b>factMappingValueType</b> JSON property
     */
    @JsProperty(name = "factMappingValueType")
    public native String getFactMappingValueType();

    /**
     * Setter for <b>factMappingValueType</b>
     * 
     * @param factMappingValueTypeParam
     *      <b>factMappingValueType</b> to set.
     */
    @JsProperty(name = "factMappingValueType")
    public final native void setFactMappingValueType(String factMappingValueTypeParam);

}
