
package com.modeln.ws.remoteact.types;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.modeln.ws.remoteact.types package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ExecuteActResponse_QNAME = new QName("http://remoteact.ws.modeln.com/types", "executeActResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.modeln.ws.remoteact.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExecuteAct }
     * 
     */
    public ExecuteAct createExecuteAct() {
        return new ExecuteAct();
    }

    /**
     * Create an instance of {@link WSTestResultResult }
     * 
     */
    public WSTestResultResult createWSTestResultResult() {
        return new WSTestResultResult();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSTestResultResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://remoteact.ws.modeln.com/types", name = "executeActResponse")
    public JAXBElement<WSTestResultResult> createExecuteActResponse(WSTestResultResult value) {
        return new JAXBElement<WSTestResultResult>(_ExecuteActResponse_QNAME, WSTestResultResult.class, null, value);
    }

}
