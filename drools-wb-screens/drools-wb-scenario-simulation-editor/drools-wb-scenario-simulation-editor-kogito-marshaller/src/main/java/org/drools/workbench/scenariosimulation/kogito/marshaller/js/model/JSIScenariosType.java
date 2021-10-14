
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
 * JSInterop adapter for <code>ScenariosType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIScenariosType", isNative = true)
public class JSIScenariosType {

    @JsOverlay
    public final static String TYPE = "SCESIM.ScenariosType";

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
        toReturn.setLocalPart("scenariosType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}scenariosType");
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
     * READ-ONLY getter for <b>scenario</b> as a {@link List}
     * 
     * @return
     *     The <b>scenario</b> mapped as a {@link List}
     */
    @JsOverlay
    public final List<JSIScenarioType> getScenario() {
        if (getNativeScenario() == null) {
            setNativeScenario(JsUtils.getNativeArray());
        }
        return JsUtils.toList(JsUtils.getUnwrappedElementsArray(getNativeScenario()));
    }

    /**
     * Appends the specified element to the end of <b>scenario</b>
     * 
     * @param element to be appended to <b>scenario</b>
     */
    @JsOverlay
    public final<D extends JSIScenarioType >void addScenario(final D element) {
        if (getNativeScenario() == null) {
            setNativeScenario(JsUtils.getNativeArray());
        }
        JsUtils.add(getNativeScenario(), element);
    }

    /**
     * Iterates over the specified collection of elements, and adds each element returned by the iterator
     * to the end of <b>scenario</b>
     * 
     * @param elements to be appended to <b>scenario</b>
     */
    @JsOverlay
    public final<D extends JSIScenarioType >void addAllScenario(D... elements) {
        if (getNativeScenario() == null) {
            setNativeScenario(JsUtils.getNativeArray());
        }
        JsUtils.addAll(getNativeScenario(), elements);
    }

    /**
     * Removes the element at the specified position in the <b>scenario</b>
     * 
     * @param index of the element to be removed
     */
    @JsOverlay
    public final void removeScenario(final int index) {
        JsUtils.remove(getNativeScenario(), index);
    }

    /**
     * Native getter for <b>scenario</b>
     * 
     * @return
     *     The <b>scenario</b> JSON property
     */
    @JsProperty(name = "scenario")
    public native JsArrayLike<JSIScenarioType> getNativeScenario();

    /**
     * Setter for <b>scenario</b> as a {@link List}
     * 
     * @param scenarioParam
     *     The <b>scenario</b> mapped as a {@link List}
     */
    @JsOverlay
    public final void setScenario(List<JSIScenarioType> scenarioParam) {
        setNativeScenario(JsUtils.toJsArrayLike(scenarioParam));
    }

    /**
     * Setter for <b>scenario</b>
     * 
     * @param scenarioParam
     *      <b>scenario</b> to set.
     */
    @JsProperty(name = "scenario")
    public final native void setNativeScenario(JsArrayLike<JSIScenarioType> scenarioParam);

}
