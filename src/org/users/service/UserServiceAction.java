package org.users.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.StringTokenizer;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.users.management.constants.ForwardNames;

/**
 * Struts Action class for service
 * 
 * @author Mikalai_Kulikou
 * 
 */
public class UserServiceAction extends Action {

	private static final String TEXT_XML_CHARSET_UTF_8 = "text/xml;charset=\"utf-8\"";
	private static final String EXCEPTION_WHILE_CREATING_SOAP_MESSAGE = "Exception while creating SOAP message.";
	static final private MessageFactory messageFactory;
	static final private UserGetterServiceAdapterSOAPHandler soapHandler;

	static {
		try {
			messageFactory = MessageFactory.newInstance();
			soapHandler = new UserGetterServiceAdapterSOAPHandler();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	static private MimeHeaders getHeaders(HttpServletRequest req) {

		Enumeration headerNames = req.getHeaderNames();
		MimeHeaders headers = new MimeHeaders();
		while (headerNames.hasMoreElements()) {
			String headerName = (String) headerNames.nextElement();
			String headerValue = req.getHeader(headerName);
			StringTokenizer values = new StringTokenizer(headerValue, ",");
			while (values.hasMoreTokens()) {
				headers.addHeader(headerName, values.nextToken().trim());
			}
		}
		return headers;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		OutputStream os = null;
		try {
			// Get all the headers from the HTTP request
			MimeHeaders headers = getHeaders(request);

			// Construct a SOAPMessage from the XML in the request body
			InputStream is = request.getInputStream();
			SOAPMessage soapRequest = messageFactory.createMessage(headers, is);

			// Handle soapReqest
			SOAPMessage soapResponse = soapHandler
					.handleSOAPRequest(soapRequest);
			// Write to HttpServeltResponse
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(TEXT_XML_CHARSET_UTF_8);
			os = response.getOutputStream();
			soapResponse.writeTo(os);

		} catch (IllegalStateException e) {
		} catch (SOAPException e) {
			throw new IOException(EXCEPTION_WHILE_CREATING_SOAP_MESSAGE, e);
		} finally {
			if (os != null) {
				os.close();
			}
		}
		return mapping.findForward(ForwardNames.SERVICE);

	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward(ForwardNames.HOME);
	}
}
