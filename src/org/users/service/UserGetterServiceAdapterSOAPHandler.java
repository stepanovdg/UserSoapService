package org.users.service;

import java.util.Iterator;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;
import org.users.service.jaxws.GetUserName;

/**
 * Handles Soap message
 * 
 * @author Mikalai_Kulikou
 * 
 */
public class UserGetterServiceAdapterSOAPHandler {
	private static final String UNRECOGNIZED_SOAP_REQUEST = "Unrecognized SOAP request.";
	private static final String GET_USER_NAME = "getUserName";
	private static final String NAMESPACE_URI = "http://service.users.org/";
	private static final QName GET_USER_QNAME = new QName(NAMESPACE_URI,
			GET_USER_NAME);

	private MessageFactory messageFactory;
	private UserGetterServiceAdapter userGetterServiceAdapter;

	public UserGetterServiceAdapterSOAPHandler() throws SOAPException {
		messageFactory = MessageFactory.newInstance();
		userGetterServiceAdapter = new UserGetterServiceAdapter();
	}

	public SOAPMessage handleSOAPRequest(SOAPMessage request)
			throws SOAPException {
		SOAPBody soapBody = request.getSOAPBody();
		@SuppressWarnings("rawtypes")
		Iterator iterator = soapBody.getChildElements();
		Object responsePojo = null;
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof SOAPElement) {
				SOAPElement soapElement = (SOAPElement) next;
				QName qname = soapElement.getElementQName();
				if (GET_USER_QNAME.equals(qname)) {
					responsePojo = handleGetUserNameRequest(soapElement);
					break;
				}
			}
		}
		SOAPMessage soapResponse = messageFactory.createMessage();
		soapBody = soapResponse.getSOAPBody();
		if (responsePojo != null) {
			JAXB.marshal(responsePojo, new SAAJResult(soapBody));
		} else {
			SOAPFault fault = soapBody.addFault();
			fault.setFaultString(UNRECOGNIZED_SOAP_REQUEST);
		}
		return soapResponse;
	}

	private Object handleGetUserNameRequest(SOAPElement soapElement) {
		GetUserName getUserNameRequest = JAXB.unmarshal(new DOMSource(
				soapElement), GetUserName.class);
		return userGetterServiceAdapter.getUserName(getUserNameRequest);

	}

}
