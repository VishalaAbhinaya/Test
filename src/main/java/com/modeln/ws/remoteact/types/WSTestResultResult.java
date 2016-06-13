
package com.modeln.ws.remoteact.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wSTestResultResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wSTestResultResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="outputLogs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="errorLogs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="testResult" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wSTestResultResult", propOrder = {
    "outputLogs",
    "errorLogs",
    "testResult"
})
public class WSTestResultResult {

    @XmlElement(required = true)
    protected String outputLogs;
    @XmlElement(required = true)
    protected String errorLogs;
    @XmlElement(required = true)
    protected String testResult;

    /**
     * Gets the value of the outputLogs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutputLogs() {
        return outputLogs;
    }

    /**
     * Sets the value of the outputLogs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutputLogs(String value) {
        this.outputLogs = value;
    }

    /**
     * Gets the value of the errorLogs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorLogs() {
        return errorLogs;
    }

    /**
     * Sets the value of the errorLogs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorLogs(String value) {
        this.errorLogs = value;
    }

    /**
     * Gets the value of the testResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTestResult() {
        return testResult;
    }

    /**
     * Sets the value of the testResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTestResult(String value) {
        this.testResult = value;
    }

}
