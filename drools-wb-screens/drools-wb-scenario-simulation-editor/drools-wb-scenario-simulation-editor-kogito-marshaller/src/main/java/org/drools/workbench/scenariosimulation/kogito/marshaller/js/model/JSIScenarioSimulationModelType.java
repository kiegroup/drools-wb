
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>ScenarioSimulationModelType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSIScenarioSimulationModelType", isNative = true)
public class JSIScenarioSimulationModelType {

    @JsOverlay
    public final static String TYPE = "SCESIM.ScenarioSimulationModelType";

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
        toReturn.setLocalPart("ScenarioSimulationModelType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}ScenarioSimulationModelType");
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
     * Native getter for <b>simulation</b>
     * 
     * @return
     *     The <b>simulation</b> JSON property
     */
    @JsProperty(name = "simulation")
    public native JSISimulationType getSimulation();

    /**
     * Setter for <b>simulation</b>
     * 
     * @param simulationParam
     *      <b>simulation</b> to set.
     */
    @JsProperty(name = "simulation")
    public final native void setSimulation(JSISimulationType simulationParam);

    /**
     * Native getter for <b>background</b>
     * 
     * @return
     *     The <b>background</b> JSON property
     */
    @JsProperty(name = "background")
    public native JSIBackgroundType getBackground();

    /**
     * Setter for <b>background</b>
     * 
     * @param backgroundParam
     *      <b>background</b> to set.
     */
    @JsProperty(name = "background")
    public final native void setBackground(JSIBackgroundType backgroundParam);

    /**
     * Native getter for <b>settings</b>
     * 
     * @return
     *     The <b>settings</b> JSON property
     */
    @JsProperty(name = "settings")
    public native JSISettingsType getSettings();

    /**
     * Setter for <b>settings</b>
     * 
     * @param settingsParam
     *      <b>settings</b> to set.
     */
    @JsProperty(name = "settings")
    public final native void setSettings(JSISettingsType settingsParam);

    /**
     * Native getter for <b>imports</b>
     * 
     * @return
     *     The <b>imports</b> JSON property
     */
    @JsProperty(name = "imports")
    public native JSIImportsType getImports();

    /**
     * Setter for <b>imports</b>
     * 
     * @param importsParam
     *      <b>imports</b> to set.
     */
    @JsProperty(name = "imports")
    public final native void setImports(JSIImportsType importsParam);

    /**
     * Native getter for <b>version</b>
     * 
     * @return
     *     The <b>version</b> JSON property
     */
    @JsProperty(name = "version")
    public native String getVersion();

    /**
     * Setter for <b>version</b>
     * 
     * @param versionParam
     *      <b>version</b> to set.
     */
    @JsProperty(name = "version")
    public final native void setVersion(String versionParam);

}
