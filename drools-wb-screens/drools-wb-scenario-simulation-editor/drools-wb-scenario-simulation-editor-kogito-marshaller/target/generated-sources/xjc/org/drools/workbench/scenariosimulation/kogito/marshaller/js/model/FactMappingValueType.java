//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.07 at 03:06:01 PM EDT 
//


package org.drools.workbench.scenariosimulation.kogito.marshaller.js.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FactMappingValueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FactMappingValueType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="factIdentifier" type="{}factIdentifierType"/&gt;
 *         &lt;element name="expressionIdentifier" type="{}expressionIdentifierType"/&gt;
 *         &lt;element name="rawValue" type="{}rawValueType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FactMappingValueType", propOrder = {
    "factIdentifier",
    "expressionIdentifier",
    "rawValue"
})
public class FactMappingValueType {

    @XmlElement(required = true)
    protected FactIdentifierType factIdentifier;
    @XmlElement(required = true)
    protected ExpressionIdentifierType expressionIdentifier;
    protected RawValueType rawValue;

    /**
     * Gets the value of the factIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link FactIdentifierType }
     *     
     */
    public FactIdentifierType getFactIdentifier() {
        return factIdentifier;
    }

    /**
     * Sets the value of the factIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link FactIdentifierType }
     *     
     */
    public void setFactIdentifier(FactIdentifierType value) {
        this.factIdentifier = value;
    }

    /**
     * Gets the value of the expressionIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ExpressionIdentifierType }
     *     
     */
    public ExpressionIdentifierType getExpressionIdentifier() {
        return expressionIdentifier;
    }

    /**
     * Sets the value of the expressionIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpressionIdentifierType }
     *     
     */
    public void setExpressionIdentifier(ExpressionIdentifierType value) {
        this.expressionIdentifier = value;
    }

    /**
     * Gets the value of the rawValue property.
     * 
     * @return
     *     possible object is
     *     {@link RawValueType }
     *     
     */
    public RawValueType getRawValue() {
        return rawValue;
    }

    /**
     * Sets the value of the rawValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link RawValueType }
     *     
     */
    public void setRawValue(RawValueType value) {
        this.rawValue = value;
    }

}
