
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>FactMappingType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "FactMappingType")
public class JSIFactMappingType {


    /**
     * Getter for <b>expressionElements</b>
     * 
     * @return
     *      <b>expressionElements</<b>
     */
    @JsProperty(name = "expressionElements")
    public final native JSIExpressionElementsType getExpressionElements();

    /**
     * Setter for <b>expressionElements</b>
     * 
     * @param expressionElements
     *      <b>expressionElements</<b> to set.
     */
    @JsProperty(name = "expressionElements")
    public final native void setExpressionElements(JSIExpressionElementsType expressionElements);

    /**
     * Getter for <b>expressionIdentifier</b>
     * 
     * @return
     *      <b>expressionIdentifier</<b>
     */
    @JsProperty(name = "expressionIdentifier")
    public final native JSIExpressionIdentifierType getExpressionIdentifier();

    /**
     * Setter for <b>expressionIdentifier</b>
     * 
     * @param expressionIdentifier
     *      <b>expressionIdentifier</<b> to set.
     */
    @JsProperty(name = "expressionIdentifier")
    public final native void setExpressionIdentifier(JSIExpressionIdentifierType expressionIdentifier);

    /**
     * Getter for <b>factIdentifier</b>
     * 
     * @return
     *      <b>factIdentifier</<b>
     */
    @JsProperty(name = "factIdentifier")
    public final native JSIFactIdentifierType getFactIdentifier();

    /**
     * Setter for <b>factIdentifier</b>
     * 
     * @param factIdentifier
     *      <b>factIdentifier</<b> to set.
     */
    @JsProperty(name = "factIdentifier")
    public final native void setFactIdentifier(JSIFactIdentifierType factIdentifier);

    /**
     * Getter for <b>className</b>
     * 
     * @return
     *      <b>className</<b>
     */
    @JsProperty(name = "className")
    public final native String getClassName();

    /**
     * Setter for <b>className</b>
     * 
     * @param className
     *      <b>className</<b> to set.
     */
    @JsProperty(name = "className")
    public final native void setClassName(String className);

    /**
     * Getter for <b>factAlias</b>
     * 
     * @return
     *      <b>factAlias</<b>
     */
    @JsProperty(name = "factAlias")
    public final native String getFactAlias();

    /**
     * Setter for <b>factAlias</b>
     * 
     * @param factAlias
     *      <b>factAlias</<b> to set.
     */
    @JsProperty(name = "factAlias")
    public final native void setFactAlias(String factAlias);

    /**
     * Getter for <b>expressionAlias</b>
     * 
     * @return
     *      <b>expressionAlias</<b>
     */
    @JsProperty(name = "expressionAlias")
    public final native String getExpressionAlias();

    /**
     * Setter for <b>expressionAlias</b>
     * 
     * @param expressionAlias
     *      <b>expressionAlias</<b> to set.
     */
    @JsProperty(name = "expressionAlias")
    public final native void setExpressionAlias(String expressionAlias);

    /**
     * Getter for <b>genericTypes</b>
     * 
     * @return
     *      <b>genericTypes</<b>
     */
    @JsProperty(name = "genericTypes")
    public final native String[] getGenericTypes();

    /**
     * Setter for <b>genericTypes</b>
     * 
     * @param genericTypes
     *      <b>genericTypes</<b> to set.
     */
    @JsProperty(name = "genericTypes")
    public final native void setGenericTypes(String[] genericTypes);

}
