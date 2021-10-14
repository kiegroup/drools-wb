
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
 * JSInterop adapter for <code>FactMappingValuesType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingValuesType", isNative = true)
public class JSIFactMappingValuesType {

    @JsOverlay
    public final static String TYPE = "SCESIM.FactMappingValuesType";

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
        toReturn.setLocalPart("factMappingValuesType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}factMappingValuesType");
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
     * READ-ONLY getter for <b>factMappingValue</b> as a {@link List}
     * 
     * @return
     *     The <b>factMappingValue</b> mapped as a {@link List}
     */
    @JsOverlay
    public final List<JSIFactMappingValueType> getFactMappingValue() {
        if (getNativeFactMappingValue() == null) {
            setNativeFactMappingValue(JsUtils.getNativeArray());
        }
        return JsUtils.toList(JsUtils.getUnwrappedElementsArray(getNativeFactMappingValue()));
    }

    /**
     * Appends the specified element to the end of <b>factMappingValue</b>
     * 
     * @param element to be appended to <b>factMappingValue</b>
     */
    @JsOverlay
    public final<D extends JSIFactMappingValueType >void addFactMappingValue(final D element) {
        if (getNativeFactMappingValue() == null) {
            setNativeFactMappingValue(JsUtils.getNativeArray());
        }
        JsUtils.add(getNativeFactMappingValue(), element);
    }

    /**
     * Iterates over the specified collection of elements, and adds each element returned by the iterator
     * to the end of <b>factMappingValue</b>
     * 
     * @param elements to be appended to <b>factMappingValue</b>
     */
    @JsOverlay
    public final<D extends JSIFactMappingValueType >void addAllFactMappingValue(D... elements) {
        if (getNativeFactMappingValue() == null) {
            setNativeFactMappingValue(JsUtils.getNativeArray());
        }
        JsUtils.addAll(getNativeFactMappingValue(), elements);
    }

    /**
     * Removes the element at the specified position in the <b>factMappingValue</b>
     * 
     * @param index of the element to be removed
     */
    @JsOverlay
    public final void removeFactMappingValue(final int index) {
        JsUtils.remove(getNativeFactMappingValue(), index);
    }

    /**
     * Native getter for <b>factMappingValue</b>
     * 
     * @return
     *     The <b>factMappingValue</b> JSON property
     */
    @JsProperty(name = "factMappingValue")
    public native JsArrayLike<JSIFactMappingValueType> getNativeFactMappingValue();

    /**
     * Setter for <b>factMappingValue</b> as a {@link List}
     * 
     * @param factMappingValueParam
     *     The <b>factMappingValue</b> mapped as a {@link List}
     */
    @JsOverlay
    public final void setFactMappingValue(List<JSIFactMappingValueType> factMappingValueParam) {
        setNativeFactMappingValue(JsUtils.toJsArrayLike(factMappingValueParam));
    }

    /**
     * Setter for <b>factMappingValue</b>
     * 
     * @param factMappingValueParam
     *      <b>factMappingValue</b> to set.
     */
    @JsProperty(name = "factMappingValue")
    public final native void setNativeFactMappingValue(JsArrayLike<JSIFactMappingValueType> factMappingValueParam);

}
