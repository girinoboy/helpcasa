<%@ taglib prefix="s" uri="/struts-tags" %>   
<h1 align="center">Help Casa</h1>
<h3 align="center">Entrar no Sistema</h3>
<div class="container" style="width: 200px">
	<s:form action="login!checkLogin">
		<div class="body">
			<div id="recuo1Left">
				Nome de Usuário:<s:textfield name="username" label="Nome de Usuário" />
				Senha:<s:password name="password" label="Senha" />
				<div class="footer">
					<s:submit value="Login" cssClass="principal" />
					<s:a href="./cliente!load.action">Cadastro</s:a>
				</div>
			</div>
		</div>
	</s:form>
</div>