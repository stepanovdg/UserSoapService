<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<div class="menuUsers">
	<bean:message key="menu.title" />
</div>
<ul class="menuLinks">
	<li><html:link href="users.do?method=list">
			<bean:message key="menu.list" />
		</html:link></li>
	<li><html:link href="users.do?method=add">
			<bean:message key="menu.add" />
		</html:link></li>
</ul>

