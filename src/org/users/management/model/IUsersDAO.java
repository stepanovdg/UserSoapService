package org.users.management.model;

import java.util.LinkedList;

import org.users.management.model.User;

import com.google.appengine.api.datastore.Key;

/**
 * Interface for Spring initialization
 * 
 * @author Mikalai_Kulikou
 * 
 */
public interface IUsersDAO {

	public LinkedList<User> getList();

	public Key save(User userMessage);

	public void remove(Long[] usersIdArray);

}
