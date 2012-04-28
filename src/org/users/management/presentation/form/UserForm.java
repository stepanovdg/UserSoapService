package org.users.management.presentation.form;

import java.util.LinkedList;
import java.util.Locale;

import org.apache.struts.action.ActionForm;

import org.users.management.constants.ForwardNames;
import org.users.management.model.User;

/**
 * Management Action Form Class
 * 
 * @author Mikalai_Kulikou
 * 
 */
public class UserForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private User userMessage;
	private LinkedList<User> usersList;
	private Long deleteIds[];
	private String simpleDate;
	private String currentPage = ForwardNames.LIST_USERS;
	private String previousPage = ForwardNames.LIST_USERS;
	private Locale currentLocale = new Locale(Locale.ENGLISH.toString());

	public User getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(User userMessage) {
		this.userMessage = userMessage;
	}

	public LinkedList<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(LinkedList<User> usersList) {
		this.usersList = usersList;
	}

	public Long[] getDeleteIds() {
		return deleteIds;
	}

	public void setDeleteIds(Long[] deleteIds) {
		this.deleteIds = deleteIds;
	}

	public String getSimpleDate() {
		return simpleDate;
	}

	public void setSimpleDate(String simpleDate) {
		this.simpleDate = simpleDate;
	}

	public Locale getCurrentLocale() {
		return currentLocale;
	}

	public void setCurrentLocale(Locale currentLocale) {
		this.currentLocale = currentLocale;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(String previousPage) {
		this.previousPage = previousPage;
	}

}
