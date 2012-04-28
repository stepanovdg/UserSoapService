<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="/server/management/css/default.css" rel="stylesheet" type="text/css">
<title><bean:message key="error.404.title" /></title>
</head>
<body>
	<div class="notFound">
		<bean:message key="error.404.body" />
		<p>
			<a href="/index.jsp">Return to main</a>
		</p>
	</div>
</body>
</html>