
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
 * JSInterop adapter for <code>GenericTypes</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIGenericTypes", isNative = true)
public class JSIGenericTypes {

    @JsOverlay
    public final static String TYPE = "SCESIM.GenericTypes";

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
        toReturn.setLocalPart("genericTypes");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}genericTypes");
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
     * READ-ONLY getter for <b>string</b> as a {@link List}
     * 
     * @return
     *     The <b>string</b> mapped as a {@link List}
     */
    @JsOverlay
    public final List<String> getString() {
        if (getNativeString() == null) {
            setNativeString(JsUtils.getNativeArray());
        }
        return JsUtils.toList(JsUtils.getUnwrappedElementsArray(getNativeString()));
    }

    /**
     * Appends the specified element to the end of <b>string</b>
     * 
     * @param element to be appended to <b>string</b>
     */
    @JsOverlay
    public final<D extends String >void addString(final D element) {
        if (getNativeString() == null) {
            setNativeString(JsUtils.getNativeArray());
        }
        JsUtils.add(getNativeString(), element);
    }

    /**
     * Iterates over the specified collection of elements, and adds each element returned by the iterator
     * to the end of <b>string</b>
     * 
     * @param elements to be appended to <b>string</b>
     */
    @JsOverlay
    public final<D extends String >void addAllString(D... elements) {
        if (getNativeString() == null) {
            setNativeString(JsUtils.getNativeArray());
        }
        JsUtils.addAll(getNativeString(), elements);
    }

    /**
     * Removes the element at the specified position in the <b>string</b>
     * 
     * @param index of the element to be removed
     */
    @JsOverlay
    public final void removeString(final int index) {
        JsUtils.remove(getNativeString(), index);
    }

    /**
     * Native getter for <b>string</b>
     * 
     * @return
     *     The <b>string</b> JSON property
     */
    @JsProperty(name = "string")
    public native JsArrayLike<String> getNativeString();

    /**
     * Setter for <b>string</b> as a {@link List}
     * 
     * @param stringParam
     *     The <b>string</b> mapped as a {@link List}
     */
    @JsOverlay
    public final void setString(List<String> stringParam) {
        setNativeString(JsUtils.toJsArrayLike(stringParam));
    }

    /**
     * Setter for <b>string</b>
     * 
     * @param stringParam
     *      <b>string</b> to set.
     */
    @JsProperty(name = "string")
    public final native void setNativeString(JsArrayLike<String> stringParam);

}
