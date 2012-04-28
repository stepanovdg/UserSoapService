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
@XmlRootElement(name = "getUserName", namespace = "http://service.users.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUserName", namespace = "http://service.users.org/")
public class GetUserName {

	@XmlElement(name = "id", namespace = "")
	private long id;

	/**
	 * 
	 * @return returns long
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * 
	 * @param id
	 *            the value for the id property
	 */
	public void setId(long id) {
		this.id = id;
	}

}
