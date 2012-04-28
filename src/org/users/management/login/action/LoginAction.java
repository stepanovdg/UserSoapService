package org.users.management.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.actions.DispatchAction;

/**
 * Process login actions
 * 
 * @author Dzmitry_Stsiapanau
 * 
 */
public class LoginAction extends DispatchAction {
	private static final String TRY_LOGIN = "tryLogin";
	private static final String LIST_USERS = "listUsers";
	private static final String IS_LOGINED = "isLogined";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";
	private static final String ADMIN = "admin";
	private static final String HOME = "home";

	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession currentSession = request.getSession(true);
		DynaActionForm loginForm = (DynaActionForm) form;
		if (ADMIN.equals(loginForm.get(LOGIN))
				&& ADMIN.equals(loginForm.get(PASSWORD))) {
			currentSession.setAttribute(IS_LOGINED, true);
			return mapping.findForward(LIST_USERS);
		} else {
			currentSession.setAttribute(IS_LOGINED, false);
			return mapping.findForward(TRY_LOGIN);
		}

	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward(HOME);
	}
}
