<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/server/management/css/login.css" rel="stylesheet"
	type="text/css">
<title><bean:message key="login.title" /></title>
</head>
<body>
	<center>
		<html:form action="login.do?method=login" styleClass="loginForm">
			<p>
				<bean:message key="login.login" />
				<html:text property="login" styleClass="login" />
			</p>
			<p>
				<bean:message key="login.password" />
				<html:password property="password" styleClass="password" />
			</p>
			<p>
				<html:submit></html:submit>
			</p>
		</html:form>

		<bean:message key="login.description" />
	</center>
</body>
</html>