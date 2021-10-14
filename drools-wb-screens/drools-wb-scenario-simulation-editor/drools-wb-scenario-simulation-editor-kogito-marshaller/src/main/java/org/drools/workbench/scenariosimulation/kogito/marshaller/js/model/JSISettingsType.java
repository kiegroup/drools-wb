
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsOverlay;
import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JSIName;
import org.drools.workbench.scenariosimulation.kogito.marshaller.mapper.JsUtils;


/**
 * JSInterop adapter for <code>SettingsType</code>
 * 
 */
@JsType(namespace = JsPackage.GLOBAL, name = "JsInterop__ConstructorAPI__SCESIM__org__drools__workbench__scenariosimulation__kogito__marshaller__js__model__JSISettingsType", isNative = true)
public class JSISettingsType {

    @JsOverlay
    public final static String TYPE = "SCESIM.SettingsType";

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
        toReturn.setLocalPart("settingsType");
        toReturn.setPrefix("");
        toReturn.setKey("{}");
        toReturn.setString("{}settingsType");
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
     * Native getter for <b>dmoSession</b>
     * 
     * @return
     *     The <b>dmoSession</b> JSON property
     */
    @JsProperty(name = "dmoSession")
    public native String getDmoSession();

    /**
     * Setter for <b>dmoSession</b>
     * 
     * @param dmoSessionParam
     *      <b>dmoSession</b> to set.
     */
    @JsProperty(name = "dmoSession")
    public final native void setDmoSession(String dmoSessionParam);

    /**
     * Native getter for <b>dmnFilePath</b>
     * 
     * @return
     *     The <b>dmnFilePath</b> JSON property
     */
    @JsProperty(name = "dmnFilePath")
    public native String getDmnFilePath();

    /**
     * Setter for <b>dmnFilePath</b>
     * 
     * @param dmnFilePathParam
     *      <b>dmnFilePath</b> to set.
     */
    @JsProperty(name = "dmnFilePath")
    public final native void setDmnFilePath(String dmnFilePathParam);

    /**
     * Native getter for <b>type</b>
     * 
     * @return
     *     The <b>type</b> JSON property
     */
    @JsProperty(name = "type")
    public native String getType();

    /**
     * Setter for <b>type</b>
     * 
     * @param typeParam
     *      <b>type</b> to set.
     */
    @JsProperty(name = "type")
    public final native void setType(String typeParam);

    /**
     * Native getter for <b>fileName</b>
     * 
     * @return
     *     The <b>fileName</b> JSON property
     */
    @JsProperty(name = "fileName")
    public native String getFileName();

    /**
     * Setter for <b>fileName</b>
     * 
     * @param fileNameParam
     *      <b>fileName</b> to set.
     */
    @JsProperty(name = "fileName")
    public final native void setFileName(String fileNameParam);

    /**
     * Native getter for <b>kieSession</b>
     * 
     * @return
     *     The <b>kieSession</b> JSON property
     */
    @JsProperty(name = "kieSession")
    public native String getKieSession();

    /**
     * Setter for <b>kieSession</b>
     * 
     * @param kieSessionParam
     *      <b>kieSession</b> to set.
     */
    @JsProperty(name = "kieSession")
    public final native void setKieSession(String kieSessionParam);

    /**
     * Native getter for <b>kieBase</b>
     * 
     * @return
     *     The <b>kieBase</b> JSON property
     */
    @JsProperty(name = "kieBase")
    public native String getKieBase();

    /**
     * Setter for <b>kieBase</b>
     * 
     * @param kieBaseParam
     *      <b>kieBase</b> to set.
     */
    @JsProperty(name = "kieBase")
    public final native void setKieBase(String kieBaseParam);

    /**
     * Native getter for <b>ruleFlowGroup</b>
     * 
     * @return
     *     The <b>ruleFlowGroup</b> JSON property
     */
    @JsProperty(name = "ruleFlowGroup")
    public native String getRuleFlowGroup();

    /**
     * Setter for <b>ruleFlowGroup</b>
     * 
     * @param ruleFlowGroupParam
     *      <b>ruleFlowGroup</b> to set.
     */
    @JsProperty(name = "ruleFlowGroup")
    public final native void setRuleFlowGroup(String ruleFlowGroupParam);

    /**
     * Native getter for <b>dmnNamespace</b>
     * 
     * @return
     *     The <b>dmnNamespace</b> JSON property
     */
    @JsProperty(name = "dmnNamespace")
    public native String getDmnNamespace();

    /**
     * Setter for <b>dmnNamespace</b>
     * 
     * @param dmnNamespaceParam
     *      <b>dmnNamespace</b> to set.
     */
    @JsProperty(name = "dmnNamespace")
    public final native void setDmnNamespace(String dmnNamespaceParam);

    /**
     * Native getter for <b>dmnName</b>
     * 
     * @return
     *     The <b>dmnName</b> JSON property
     */
    @JsProperty(name = "dmnName")
    public native String getDmnName();

    /**
     * Setter for <b>dmnName</b>
     * 
     * @param dmnNameParam
     *      <b>dmnName</b> to set.
     */
    @JsProperty(name = "dmnName")
    public final native void setDmnName(String dmnNameParam);

    /**
     * Native getter for <b>skipFromBuild</b>
     * 
     * @return
     *     The <b>skipFromBuild</b> JSON property
     */
    @JsProperty(name = "skipFromBuild")
    public native boolean getSkipFromBuild();

    /**
     * Setter for <b>skipFromBuild</b>
     * 
     * @param skipFromBuildParam
     *      <b>skipFromBuild</b> to set.
     */
    @JsProperty(name = "skipFromBuild")
    public final native void setSkipFromBuild(boolean skipFromBuildParam);

    /**
     * Native getter for <b>stateless</b>
     * 
     * @return
     *     The <b>stateless</b> JSON property
     */
    @JsProperty(name = "stateless")
    public native boolean getStateless();

    /**
     * Setter for <b>stateless</b>
     * 
     * @param statelessParam
     *      <b>stateless</b> to set.
     */
    @JsProperty(name = "stateless")
    public final native void setStateless(boolean statelessParam);

}
