<%@ taglib uri="http://struts.apache.org/tags-html-el" prefix="html-el"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<div class="logo">
	<bean:message key="header.title" />
</div>
<div class="l18nLinks">
	<html-el:link href="users.do?method=changeLanguage&lang=en">
		<bean:message key="header.language.en" />
	</html-el:link>
	<html-el:link href="users.do?method=changeLanguage&lang=ru">
		<bean:message key="header.language.ru" />
	</html-el:link>
</div>