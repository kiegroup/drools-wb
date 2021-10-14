
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.callbacks;

import jsinterop.annotations.JsFunction;
import org.drools.workbench.scenariosimulation.kogito.marshaller.js.model.SCESIM;


/**
 * Unmarshaller callback for <code>SCESIM</code>
 * 
 */
@JsFunction
public interface SCESIMUnmarshallCallback {


    void callEvent(SCESIM sCESIM);

}
