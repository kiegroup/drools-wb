
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import java.util.List;
import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.JsArrayLike;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>ExpressionElementsType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIExpressionElementsType", isNative = true)
public class JSIExpressionElementsType {

    @JsOverlay
    public final static String TYPE = "SCESIM.ExpressionElementsType";

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
        toReturn.setLocalPart("expressionElementsType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}expressionElementsType");
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
     * READ-ONLY getter for <b>expressionElement</b> as a {@link List}
     * 
     * @return
     *     The <b>expressionElement</b> mapped as a {@link List}
     */
    @JsOverlay
    public final List<JSIExpressionElementType> getExpressionElement() {
        if (getNativeExpressionElement() == null) {
            setNativeExpressionElement(JsUtils.getNativeArray());
        }
        return JsUtils.toList(JsUtils.getUnwrappedElementsArray(getNativeExpressionElement()));
    }

    /**
     * Appends the specified element to the end of <b>expressionElement</b>
     * 
     * @param element to be appended to <b>expressionElement</b>
     */
    @JsOverlay
    public final<D extends JSIExpressionElementType >void addExpressionElement(final D element) {
        if (getNativeExpressionElement() == null) {
            setNativeExpressionElement(JsUtils.getNativeArray());
        }
        JsUtils.add(getNativeExpressionElement(), element);
    }

    /**
     * Iterates over the specified collection of elements, and adds each element returned by the iterator
     * to the end of <b>expressionElement</b>
     * 
     * @param elements to be appended to <b>expressionElement</b>
     */
    @JsOverlay
    public final<D extends JSIExpressionElementType >void addAllExpressionElement(D... elements) {
        if (getNativeExpressionElement() == null) {
            setNativeExpressionElement(JsUtils.getNativeArray());
        }
        JsUtils.addAll(getNativeExpressionElement(), elements);
    }

    /**
     * Removes the element at the specified position in the <b>expressionElement</b>
     * 
     * @param index of the element to be removed
     */
    @JsOverlay
    public final void removeExpressionElement(final int index) {
        JsUtils.remove(getNativeExpressionElement(), index);
    }

    /**
     * Native getter for <b>expressionElement</b>
     * 
     * @return
     *     The <b>expressionElement</b> JSON property
     */
    @JsProperty(name = "expressionElement")
    public native JsArrayLike<JSIExpressionElementType> getNativeExpressionElement();

    /**
     * Setter for <b>expressionElement</b> as a {@link List}
     * 
     * @param expressionElementParam
     *     The <b>expressionElement</b> mapped as a {@link List}
     */
    @JsOverlay
    public final void setExpressionElement(List<JSIExpressionElementType> expressionElementParam) {
        setNativeExpressionElement(JsUtils.toJsArrayLike(expressionElementParam));
    }

    /**
     * Setter for <b>expressionElement</b>
     * 
     * @param expressionElementParam
     *      <b>expressionElement</b> to set.
     */
    @JsProperty(name = "expressionElement")
    public final native void setNativeExpressionElement(JsArrayLike<JSIExpressionElementType> expressionElementParam);

}
