
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.callbacks;

import jsinterop.annotations.JsFunction;


/**
 * Marshaller callback for <code>SCESIM</code>
 * 
 */
@JsFunction
public interface SCESIMMarshallCallback {


    void callEvent(String xmlString);

}
