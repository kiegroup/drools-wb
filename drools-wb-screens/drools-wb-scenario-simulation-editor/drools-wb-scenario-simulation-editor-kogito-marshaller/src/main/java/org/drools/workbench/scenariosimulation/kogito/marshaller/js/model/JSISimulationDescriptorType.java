
package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;


/**
 * JSInterop adapter for <code>SimulationDescriptorType</code>
 * 
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name = "SimulationDescriptorType")
public class JSISimulationDescriptorType {


    /**
     * Getter for <b>factMappings</b>
     * 
     * @return
     *      <b>factMappings</<b>
     */
    @JsProperty
    public final native JSIFactMappingsType getFactMappings();

    /**
     * Setter for <b>factMappings</b>
     * 
     * @param factMappings
     *      <b>factMappings</<b> to set.
     */
    @JsProperty
    public final native void setFactMappings(JSIFactMappingsType factMappings);

    /**
     * Getter for <b>dmoSession</b>
     * 
     * @return
     *      <b>dmoSession</<b>
     */
    @JsProperty
    public final native String getDmoSession();

    /**
     * Setter for <b>dmoSession</b>
     * 
     * @param dmoSession
     *      <b>dmoSession</<b> to set.
     */
    @JsProperty
    public final native void setDmoSession(String dmoSession);

    /**
     * Getter for <b>type</b>
     * 
     * @return
     *      <b>type</<b>
     */
    @JsProperty
    public final native String getType();

    /**
     * Setter for <b>type</b>
     * 
     * @param type
     *      <b>type</<b> to set.
     */
    @JsProperty
    public final native void setType(String type);

    /**
     * Getter for <b>fileName</b>
     * 
     * @return
     *      <b>fileName</<b>
     */
    @JsProperty
    public final native String getFileName();

    /**
     * Setter for <b>fileName</b>
     * 
     * @param fileName
     *      <b>fileName</<b> to set.
     */
    @JsProperty
    public final native void setFileName(String fileName);

    /**
     * Getter for <b>kieSession</b>
     * 
     * @return
     *      <b>kieSession</<b>
     */
    @JsProperty
    public final native String getKieSession();

    /**
     * Setter for <b>kieSession</b>
     * 
     * @param kieSession
     *      <b>kieSession</<b> to set.
     */
    @JsProperty
    public final native void setKieSession(String kieSession);

    /**
     * Getter for <b>kieBase</b>
     * 
     * @return
     *      <b>kieBase</<b>
     */
    @JsProperty
    public final native String getKieBase();

    /**
     * Setter for <b>kieBase</b>
     * 
     * @param kieBase
     *      <b>kieBase</<b> to set.
     */
    @JsProperty
    public final native void setKieBase(String kieBase);

    /**
     * Getter for <b>ruleFlowGroup</b>
     * 
     * @return
     *      <b>ruleFlowGroup</<b>
     */
    @JsProperty
    public final native String getRuleFlowGroup();

    /**
     * Setter for <b>ruleFlowGroup</b>
     * 
     * @param ruleFlowGroup
     *      <b>ruleFlowGroup</<b> to set.
     */
    @JsProperty
    public final native void setRuleFlowGroup(String ruleFlowGroup);

}
