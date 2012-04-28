<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${userForm.currentLocale}" />

<logic:present name="userForm" property="usersList">
	<html:form action="users.do?method=delete"
		onsubmit="return validateDeleteForm(this)">
		<div class="userLinkTop">
			<html:link href="users.do?method=list">
				<bean:message key="list.link.list" />
			</html:link>
			<bean:message key="special.symbol.next" />
			<bean:message key="list.link.current" />
		</div>

		<logic:iterate id="User" name="userForm" property="usersList"
			indexId="index">

			<div class="listName">${User.name}</div>
			<div class="listDate">
				<fmt:formatDate value="${User.date}" dateStyle="short" />
			</div>
			<div class="listUid">${User.uid}</div>
			<div class="listLinks">
				<html:link paramId="userMessage.uid" paramName="User"
					paramProperty="uid" href="users.do?method=view">
					<bean:message key="list.link.view" />
				</html:link>
				<html:link paramId="userMessage.uid" paramName="User"
					paramProperty="uid" href="users.do?method=edit">
					<bean:message key="list.link.edit" />
				</html:link>
				<html:multibox property="deleteIds">
					<bean:write name="User" property="uid" />
				</html:multibox>
			</div>



		</logic:iterate>
		<html-el:submit styleClass="listDelete">
			<bean:message key="list.button.delete" />
		</html-el:submit>
	</html:form>
</logic:present>
<logic:notPresent name="userForm" property="usersList">
	<p>
		<bean:message key="special.message.noanyusers" />
	</p>
</logic:notPresent>

