package org.users.service;

import org.users.service.jaxws.GetUserName;
import org.users.service.jaxws.GetUserNameResponse;

/**
 * Service method adapter
 * 
 * @author Mikalai_Kulikou
 * 
 */
public class UserGetterServiceAdapter {
	private UserGetterService getterService = new UserGetterService();

	public GetUserNameResponse getUserName(GetUserName request) {
		long id = request.getId();
		String responseUser = getterService.getUserName(id);
		GetUserNameResponse response = new GetUserNameResponse();
		response.setReturn(responseUser);
		return response;
	}
}
