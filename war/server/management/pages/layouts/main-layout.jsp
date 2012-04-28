<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${not sessionScope.isLogined}">
	<jsp:forward page="/server/management/pages/login/login.jsp"></jsp:forward>
</c:if>
<html>
<head>
<LINK href="/server/management/css/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/server/management/js/validation.js"></script>
<script type="text/javascript" src="/server/management/js/messages.jsp"></script>
<title><bean:message key="special.main.title" /></title>
</head>
<body>
	<div class="page">
		<div class="header">
			<tiles:insert attribute="header" />
		</div>
		<div class="middle">
			<div class="menu">
				<tiles:insert attribute="menu" />
			</div>
			<div class="body">
				<tiles:insert attribute="body" />
			</div>
		</div>
		<div class="footer">
			<tiles:insert attribute="footer" />
		</div>
	</div>
</body>
</html>
