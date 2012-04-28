package org.users.management.presentation.action;

import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import org.users.management.constants.ForwardNames;
import org.users.management.constants.LocaleArgs;
import org.users.management.model.IUsersDAO;
import org.users.management.model.User;
import org.users.management.presentation.form.UserForm;
import org.users.management.util.DateConverter;

import com.google.appengine.api.datastore.Key;

/**
 * Management Action Class
 * 
 * @author Mikalai_Kulikou
 * 
 */
public final class UserAction extends DispatchAction {

	private IUsersDAO usersDAO;

	public void setUsersDAO(IUsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		LinkedList<User> usersList = usersDAO.getList();
		UserForm userForm = (UserForm) form;
		userForm.setUsersList(usersList);
		userForm.setUserMessage(new User());
		userForm.setPreviousPage(userForm.getCurrentPage());
		userForm.setCurrentPage(ForwardNames.LIST_USERS);

		return mapping.findForward(ForwardNames.LIST_USERS);
	}

	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		User userMessage = userForm.getUserMessage();
		userForm.setPreviousPage(userForm.getCurrentPage());
		userForm.setCurrentPage(ForwardNames.VIEW_USER);

		if (userMessage.getUid() == 0) {
			return mapping.findForward(ForwardNames.LIST_USERS);
		} else if (userMessage.getName() == null) {
			setUsersMessageById(userForm, userMessage.getUid());

		}

		return mapping.findForward(ForwardNames.VIEW_USER);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		User userMessage = userForm.getUserMessage();

		if (userMessage.getName() == null) {
			setUsersMessageById(userForm, userMessage.getUid());
		}
		userForm.setPreviousPage(userForm.getCurrentPage());
		userForm.setCurrentPage(ForwardNames.EDIT_USER);
		userForm.setSimpleDate(DateConverter.simpleDate(userForm
				.getUserMessage().getDate(), userForm.getCurrentLocale()));
		return mapping.findForward(ForwardNames.EDIT_USER);
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserForm userForm = (UserForm) form;
		User userMessage = userForm.getUserMessage();
		Long[] selectedUsers = userForm.getDeleteIds();

		if (userMessage.getKey() != null) {
			Long[] currentUsers = new Long[1];
			currentUsers[0] = userMessage.getKey().getId();

			usersDAO.remove(currentUsers);
			userForm.setUserMessage(null);
		} else {
			usersDAO.remove(selectedUsers);
		}

		return list(mapping, form, request, response);
	}

	public ActionForward changeLanguage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		User user = new User();
		user.setDate(new Date());
		UserForm userForm = (UserForm) form;
		String lang = request.getParameter(LocaleArgs.LOCALE_PARAMETER);
		user = DateConverter.convertDate(user, userForm.getSimpleDate(),
				userForm.getCurrentLocale());
		userForm.setCurrentLocale(new Locale(lang));
		setLocale(request, new Locale(lang));
		userForm.setSimpleDate(DateConverter.simpleDate(user.getDate(),
				userForm.getCurrentLocale()));

		return mapping.findForward(userForm.getCurrentPage());
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		if (userForm.getCurrentPage() == ForwardNames.EDIT_USER
				&& userForm.getPreviousPage() == ForwardNames.VIEW_USER) {
			return view(mapping, form, request, response);
		} else {
			return list(mapping, form, request, response);
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;
		User userMessage = DateConverter.convertDate(userForm.getUserMessage(),
				userForm.getSimpleDate(), userForm.getCurrentLocale());

		if (userMessage.getKey() == null) {
			Key userMessageKey = usersDAO.save(userMessage);
			userMessage.setKey(userMessageKey);
		} else {
			usersDAO.save(userMessage);
		}

		return view(mapping, form, request, response);
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserForm userForm = (UserForm) form;

		userForm.setPreviousPage(userForm.getCurrentPage());
		userForm.setCurrentPage(ForwardNames.ADD_USER);

		User user = new User();
		user.setDate(new Date());
		userForm.setSimpleDate(DateConverter.simpleDate(user.getDate(),
				userForm.getCurrentLocale()));
		userForm.setUserMessage(user);
		return mapping.findForward(ForwardNames.ADD_USER);

	}

	private void setUsersMessageById(UserForm userForm, Long userId) {
		LinkedList<User> usersList = userForm.getUsersList();
		for (User user : usersList) {
			if (user.getUid().equals(userId)) {
				userForm.setUserMessage(user);
			}
		}
	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward(ForwardNames.HOME);
	}
}
