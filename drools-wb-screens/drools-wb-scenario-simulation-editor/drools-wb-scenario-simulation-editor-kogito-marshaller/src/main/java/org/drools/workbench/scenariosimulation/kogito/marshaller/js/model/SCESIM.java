
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop container for <code>JSIScenarioSimulationModelType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL)
public class SCESIM {


    @JsProperty
    public final native String getName();

    @JsProperty
    public final native JSIScenarioSimulationModelType getValue();

}
