<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<fmt:setLocale value="${userForm.currentLocale}" />
<div class="userLinkTop">
	<html:link href="users.do?method=list">
		<bean:message key="view.link.list" />
	</html:link>
	<bean:message key="special.symbol.next" />
	<bean:message key="view.link.current" />
</div>
<bean:define id="userId" name="userForm" property="userMessage.uid"></bean:define>

<div class="addName">
	<bean:message key="view.title" />
</div>
<div class="nameView">
	<bean:write name="userForm" property="userMessage.name"></bean:write>
</div>


<div class="addDate">
	<bean:message key="view.date" />
</div>
<div class="dateView">
	<fmt:formatDate value="${userForm.userMessage.date}" dateStyle="short" />
</div>

<div class="addUid">
	<bean:message key="view.brief" />
</div>
<div class="uidView">
	<bean:write name="userForm" property="userMessage.uid"></bean:write>
</div>

<div class="addJSON">
	<bean:message key="view.content" />
</div>
<div class="jsonView">
	<bean:write name="userForm" property="userMessage.jsonData"></bean:write>
</div>

<html:form onsubmit="return confirmDelete()" styleClass="deleteView"
	action="users.do?method=delete">
	<html:hidden property="userMessage.uid" value="${userId}" />
	<html:submit>
		<bean:message key="view.button.delete" />
	</html:submit>
</html:form>

<html:form styleClass="editView" action="users.do?method=edit">
	<html:hidden property="userMessage.uid" value="${userId}" />
	<html:submit>
		<bean:message key="view.button.edit" />
	</html:submit>
</html:form>


