<%@ taglib prefix="s" uri="/struts-tags" %>   

<s:form action="login!checkLogin">
<h2 align="center">Help Casa</h2>
<h5 align="center">Entrar no Sistema</h5>
	<s:textfield name="username" label="Nome de Usuário"></s:textfield>
	<s:password name="password" label="Senha"></s:password>
	<s:a href="./cliente!load.action">Cadastro</s:a>
	<s:submit value="Login"></s:submit>
</s:form>
