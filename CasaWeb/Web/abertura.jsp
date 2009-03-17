<%@ taglib prefix="s" uri="/struts-tags" %>   

<s:form action="login!checkLogin">
	<s:textfield name="username" label="User"></s:textfield>
	<s:password name="password" label="Password"></s:password>
	<s:submit value="Sign In"></s:submit>
</s:form>
