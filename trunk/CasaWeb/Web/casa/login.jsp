<%@ taglib prefix="s" uri="/struts-tags" %>   
<h2 align="center">Help Casa</h2>
<h1 align="center">Entrar no Sistema</h1>
<div class="container" style="width: 200px">
	<s:form action="login!checkLogin">
		<div class="body">
			<div id="recuo1Left">
				Nome de Usu�rio:<s:textfield name="pessoaDTO.usuario" id="pessoaDTO.usuario" label="Nome de Usu�rio" />
				Senha:<s:password name="pessoaDTO.senha" id="pessoaDTO.senha" label="Senha" />
				<div class="footer">
					<s:submit value="Login" cssClass="principal" />
					<s:a href="./cliente!load.action">Cadastro</s:a>
				</div>
			</div>
		</div>
	</s:form>
</div>