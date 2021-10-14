
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
 * JSInterop adapter for <code>FactMappingsType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIFactMappingsType", isNative = true)
public class JSIFactMappingsType {

    @JsOverlay
    public final static String TYPE = "SCESIM.FactMappingsType";

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
        toReturn.setLocalPart("factMappingsType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}factMappingsType");
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
     * READ-ONLY getter for <b>factMapping</b> as a {@link List}
     * 
     * @return
     *     The <b>factMapping</b> mapped as a {@link List}
     */
    @JsOverlay
    public final List<JSIFactMappingType> getFactMapping() {
        if (getNativeFactMapping() == null) {
            setNativeFactMapping(JsUtils.getNativeArray());
        }
        return JsUtils.toList(JsUtils.getUnwrappedElementsArray(getNativeFactMapping()));
    }

    /**
     * Appends the specified element to the end of <b>factMapping</b>
     * 
     * @param element to be appended to <b>factMapping</b>
     */
    @JsOverlay
    public final<D extends JSIFactMappingType >void addFactMapping(final D element) {
        if (getNativeFactMapping() == null) {
            setNativeFactMapping(JsUtils.getNativeArray());
        }
        JsUtils.add(getNativeFactMapping(), element);
    }

    /**
     * Iterates over the specified collection of elements, and adds each element returned by the iterator
     * to the end of <b>factMapping</b>
     * 
     * @param elements to be appended to <b>factMapping</b>
     */
    @JsOverlay
    public final<D extends JSIFactMappingType >void addAllFactMapping(D... elements) {
        if (getNativeFactMapping() == null) {
            setNativeFactMapping(JsUtils.getNativeArray());
        }
        JsUtils.addAll(getNativeFactMapping(), elements);
    }

    /**
     * Removes the element at the specified position in the <b>factMapping</b>
     * 
     * @param index of the element to be removed
     */
    @JsOverlay
    public final void removeFactMapping(final int index) {
        JsUtils.remove(getNativeFactMapping(), index);
    }

    /**
     * Native getter for <b>factMapping</b>
     * 
     * @return
     *     The <b>factMapping</b> JSON property
     */
    @JsProperty(name = "factMapping")
    public native JsArrayLike<JSIFactMappingType> getNativeFactMapping();

    /**
     * Setter for <b>factMapping</b> as a {@link List}
     * 
     * @param factMappingParam
     *     The <b>factMapping</b> mapped as a {@link List}
     */
    @JsOverlay
    public final void setFactMapping(List<JSIFactMappingType> factMappingParam) {
        setNativeFactMapping(JsUtils.toJsArrayLike(factMappingParam));
    }

    /**
     * Setter for <b>factMapping</b>
     * 
     * @param factMappingParam
     *      <b>factMapping</b> to set.
     */
    @JsProperty(name = "factMapping")
    public final native void setNativeFactMapping(JsArrayLike<JSIFactMappingType> factMappingParam);

}
