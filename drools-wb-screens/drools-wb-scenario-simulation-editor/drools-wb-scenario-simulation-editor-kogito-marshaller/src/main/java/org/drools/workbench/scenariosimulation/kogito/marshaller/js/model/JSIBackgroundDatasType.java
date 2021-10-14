
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
 * JSInterop adapter for <code>BackgroundDatasType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIBackgroundDatasType", isNative = true)
public class JSIBackgroundDatasType {

    @JsOverlay
    public final static String TYPE = "SCESIM.BackgroundDatasType";

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
        toReturn.setLocalPart("backgroundDatasType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}backgroundDatasType");
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
     * READ-ONLY getter for <b>backgroundData</b> as a {@link List}
     * 
     * @return
     *     The <b>backgroundData</b> mapped as a {@link List}
     */
    @JsOverlay
    public final List<JSIBackgroundDataType> getBackgroundData() {
        if (getNativeBackgroundData() == null) {
            setNativeBackgroundData(JsUtils.getNativeArray());
        }
        return JsUtils.toList(JsUtils.getUnwrappedElementsArray(getNativeBackgroundData()));
    }

    /**
     * Appends the specified element to the end of <b>backgroundData</b>
     * 
     * @param element to be appended to <b>backgroundData</b>
     */
    @JsOverlay
    public final<D extends JSIBackgroundDataType >void addBackgroundData(final D element) {
        if (getNativeBackgroundData() == null) {
            setNativeBackgroundData(JsUtils.getNativeArray());
        }
        JsUtils.add(getNativeBackgroundData(), element);
    }

    /**
     * Iterates over the specified collection of elements, and adds each element returned by the iterator
     * to the end of <b>backgroundData</b>
     * 
     * @param elements to be appended to <b>backgroundData</b>
     */
    @JsOverlay
    public final<D extends JSIBackgroundDataType >void addAllBackgroundData(D... elements) {
        if (getNativeBackgroundData() == null) {
            setNativeBackgroundData(JsUtils.getNativeArray());
        }
        JsUtils.addAll(getNativeBackgroundData(), elements);
    }

    /**
     * Removes the element at the specified position in the <b>backgroundData</b>
     * 
     * @param index of the element to be removed
     */
    @JsOverlay
    public final void removeBackgroundData(final int index) {
        JsUtils.remove(getNativeBackgroundData(), index);
    }

    /**
     * Native getter for <b>backgroundData</b>
     * 
     * @return
     *     The <b>backgroundData</b> JSON property
     */
    @JsProperty(name = "backgroundData")
    public native JsArrayLike<JSIBackgroundDataType> getNativeBackgroundData();

    /**
     * Setter for <b>backgroundData</b> as a {@link List}
     * 
     * @param backgroundDataParam
     *     The <b>backgroundData</b> mapped as a {@link List}
     */
    @JsOverlay
    public final void setBackgroundData(List<JSIBackgroundDataType> backgroundDataParam) {
        setNativeBackgroundData(JsUtils.toJsArrayLike(backgroundDataParam));
    }

    /**
     * Setter for <b>backgroundData</b>
     * 
     * @param backgroundDataParam
     *      <b>backgroundData</b> to set.
     */
    @JsProperty(name = "backgroundData")
    public final native void setNativeBackgroundData(JsArrayLike<JSIBackgroundDataType> backgroundDataParam);

}
