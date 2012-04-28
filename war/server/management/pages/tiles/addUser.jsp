<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="userLinkTop">
	<html:link href="users.do?method=list">
		<bean:message key="add.link.list" />
	</html:link>
	<bean:message key="special.symbol.next" />
	<bean:message key="add.link.current" />
</div>
<html:form onsubmit="return validateAddForm(this)"
	action="users.do?method=save">
	<div class="addName">
		<bean:message key="add.title" />
	</div>
	<html:text styleClass="addUserName" property="userMessage.name"
		maxlength="100"></html:text>

	<div class="addDate">
		<bean:message key="add.date" />
	</div>

	<html:text styleClass="addUserDate" property="simpleDate"
		maxlength="10"></html:text>

	<div class="addUid">
		<bean:message key="add.brief" />
	</div>
	<html:textarea styleClass="addUserUid" property="userMessage.uid"></html:textarea>

	<div class="addJSON">
		<bean:message key="add.content" />
	</div>
	<html:textarea styleClass="addUserJSON" property="userMessage.jsonData"></html:textarea>



	<div class="save">
		<html:submit>
			<bean:message key="add.button.save" />
		</html:submit>
	</div>
</html:form>
<div class="cancel">
	<html:form action="users.do?method=cancel">
		<html:submit>
			<bean:message key="add.button.cancel" />
		</html:submit>
	</html:form>
</div>


