package org.users.service;

import java.util.LinkedList;

import javax.jws.*;

import org.users.management.model.JPAUsersDAO;
import org.users.management.model.User;

/**
 * Servise method class
 * 
 * @author Mikalai_Kulikou
 * 
 */
@WebService
public class UserGetterService {

	private static final String USER_WITH_ID = "User with id ";
	private static final String WAS_NOT_FOUND = " was not found";
	private static final String ID3 = " id ";
	private static final String HAS = " has ";
	private static final String ID2 = "id";
	private static final String GET_USER_NAME = "getUserName";

	private static final JPAUsersDAO usersDAO = new JPAUsersDAO();

	@WebMethod(operationName = GET_USER_NAME)
	public String getUserName(@WebParam(name = ID2) long id) {
		Long userId = new Long(id);
		LinkedList<User> usersList = usersDAO.getList();
		for (User user : usersList) {
			if (user.getUid().equals(userId)) {
				return (user.getName() + HAS + userId + ID3);
			}
		}
		return USER_WITH_ID + userId + WAS_NOT_FOUND;
	}
}
