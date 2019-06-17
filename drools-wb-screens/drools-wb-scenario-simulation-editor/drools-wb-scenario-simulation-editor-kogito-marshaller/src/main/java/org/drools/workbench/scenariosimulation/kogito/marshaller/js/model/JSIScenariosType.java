
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>ScenariosType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "ScenariosType")
public class JSIScenariosType {


    /**
     * Getter for <b>scenario</b>
     * 
     * @return
     *      <b>scenario</<b>
     */
    @JsProperty
    public final native JSIScenarioType getScenario();

    /**
     * Setter for <b>scenario</b>
     * 
     * @param scenario
     *      <b>scenario</<b> to set.
     */
    @JsProperty
    public final native void setScenario(JSIScenarioType scenario);

}
