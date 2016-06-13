package com.modeln.ws.remoteact.handler;

import java.util.Set;
import java.io.IOException;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Created by IntelliJ IDEA.
 * User: jbpringuey
 * Date: Nov 25, 2012
 * Time: 4:54:48 PM
 */
public class CredentialInjectHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        System.out.println("\nClient : handleMessage()......\n");
        Boolean isRequest = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

        //if this is a request, true for outbound messages, false for inbound
        if (isRequest) {

            try {
                SOAPMessage soapMsg = context.getMessage();
                SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
                SOAPHeader soapHeader = soapEnv.getHeader();

                //if no header, add one
                if (soapHeader == null) {
                    soapHeader = soapEnv.addHeader();
                }
                String namespace = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";
                String prefix ="wsse";
                QName security = new QName(namespace, "Security",prefix);
                SOAPElement securityElement = soapHeader.addChildElement(security);

                QName userNameToken = new QName(namespace, "UsernameToken");
                SOAPElement userNameTokenElement = securityElement.addChildElement(userNameToken);

                QName username = new QName(namespace, "Username");
                SOAPElement userNameElement = userNameTokenElement.addChildElement(username);
                userNameElement.addTextNode(System.getProperty("Username"));

                QName password = new QName(namespace, "Password");
                SOAPElement passwordElement = userNameTokenElement.addChildElement(password);
                passwordElement.addTextNode(System.getProperty("Password"));

                soapMsg.saveChanges();

                //tracking
                soapMsg.writeTo(System.out);


            } catch (SOAPException e) {
                System.err.println(e);
            } catch (IOException e) {
                System.err.println(e);
            }

        }

        //continue other handler chain
        return true;
    }

    public boolean handleFault(SOAPMessageContext context) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void close(MessageContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Set<QName> getHeaders() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
