<%@ taglib prefix="s" uri="/struts-tags" %>   

<s:form action="login!checkLogin">
	<s:textfield name="username" label="Nome de Usu�rio"></s:textfield>
	<s:password name="password" label="Senha"></s:password>
	<s:submit value="Efetuar Login"></s:submit>
</s:form>
