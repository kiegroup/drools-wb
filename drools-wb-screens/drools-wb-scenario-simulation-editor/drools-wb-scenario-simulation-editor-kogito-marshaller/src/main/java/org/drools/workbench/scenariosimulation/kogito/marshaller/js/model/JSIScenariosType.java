
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import jsinterop.base.JsArrayLike;


/**
 * JSInterop adapter for <code>ScenariosType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "ScenariosType")
public class JSIScenariosType {


    /**
     * Get TYPE_NAME for <code>JSIScenariosType</code>
     * 
     * @return
     *     <b>SCESIM.ScenariosType</b>
     */
    public final static String getTypeName() {
        return "SCESIM.ScenariosType";
    }

    /**
     * Getter for <b>scenario</b>
     * 
     * @return
     *      <b>scenario</<b>
     */
    @JsProperty(name = "scenario")
    public final native JsArrayLike<JSIScenarioType> getScenario();

    /**
     * Setter for <b>scenario</b>
     * 
     * @param scenario
     *      <b>scenario</<b> to set.
     */
    @JsProperty(name = "scenario")
    public final native void setScenario(JsArrayLike<JSIScenarioType> scenario);
    



public static native JSIScenariosType newInstance() /*-{
        var json = "{\"TYPE_NAME\": \"SCESIM.ScenariosType\"}";
        var retrieved = JSON.parse(json)
        console.log("retrieved " + retrieved);
        return retrieved
    }-*/;
}
