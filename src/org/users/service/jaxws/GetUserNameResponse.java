package org.users.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Auto generated JAX-WS class
 * 
 * @author Mikalai_Kulikou
 * 
 */
@XmlRootElement(name = "getUserNameResponse", namespace = "http://service.users.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserNameResponse", namespace = "http://service.users.org/")
public class GetUserNameResponse {

	@XmlElement(name = "return", namespace = "")
	private String _return;

	/**
	 * 
	 * @return returns String
	 */
	public String getReturn() {
		return this._return;
	}

	/**
	 * 
	 * @param _return
	 *            the value for the _return property
	 */
	public void setReturn(String _return) {
		this._return = _return;
	}

}
