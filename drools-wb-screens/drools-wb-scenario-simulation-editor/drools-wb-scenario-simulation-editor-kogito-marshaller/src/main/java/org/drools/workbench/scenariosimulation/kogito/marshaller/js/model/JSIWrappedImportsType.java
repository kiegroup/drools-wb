
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
 * JSInterop adapter for <code>WrappedImportsType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIWrappedImportsType", isNative = true)
public class JSIWrappedImportsType {

    @JsOverlay
    public final static String TYPE = "SCESIM.WrappedImportsType";

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
        toReturn.setLocalPart("wrappedImportsType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}wrappedImportsType");
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
     * READ-ONLY getter for <b>_import</b> as a {@link List}
     * 
     * @return
     *     The <b>_import</b> mapped as a {@link List}
     */
    @JsOverlay
    public final List<JSIImportType> getImport() {
        if (getNativeImport() == null) {
            setNativeImport(JsUtils.getNativeArray());
        }
        return JsUtils.toList(JsUtils.getUnwrappedElementsArray(getNativeImport()));
    }

    /**
     * Appends the specified element to the end of <b>_import</b>
     * 
     * @param element to be appended to <b>_import</b>
     */
    @JsOverlay
    public final<D extends JSIImportType >void addImport(final D element) {
        if (getNativeImport() == null) {
            setNativeImport(JsUtils.getNativeArray());
        }
        JsUtils.add(getNativeImport(), element);
    }

    /**
     * Iterates over the specified collection of elements, and adds each element returned by the iterator
     * to the end of <b>_import</b>
     * 
     * @param elements to be appended to <b>_import</b>
     */
    @JsOverlay
    public final<D extends JSIImportType >void addAllImport(D... elements) {
        if (getNativeImport() == null) {
            setNativeImport(JsUtils.getNativeArray());
        }
        JsUtils.addAll(getNativeImport(), elements);
    }

    /**
     * Removes the element at the specified position in the <b>_import</b>
     * 
     * @param index of the element to be removed
     */
    @JsOverlay
    public final void removeImport(final int index) {
        JsUtils.remove(getNativeImport(), index);
    }

    /**
     * Native getter for <b>_import</b>
     * 
     * @return
     *     The <b>_import</b> JSON property
     */
    @JsProperty(name = "_import")
    public native JsArrayLike<JSIImportType> getNativeImport();

    /**
     * Setter for <b>_import</b> as a {@link List}
     * 
     * @param _importParam
     *     The <b>_import</b> mapped as a {@link List}
     */
    @JsOverlay
    public final void setImport(List<JSIImportType> _importParam) {
        setNativeImport(JsUtils.toJsArrayLike(_importParam));
    }

    /**
     * Setter for <b>_import</b>
     * 
     * @param _importParam
     *      <b>_import</b> to set.
     */
    @JsProperty(name = "_import")
    public final native void setNativeImport(JsArrayLike<JSIImportType> _importParam);

}
