<%@ taglib prefix="s" uri="/struts-tags" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<br><br><br>
<h2 align="center">SIGSD - Sistema de Informação Gerencial de Serviços Domesticos</h2><br/>
<h3 align="center">Entrar no Sistema</h3>
<div class="container" style="width: 200px">
	<s:form action="login!checkLogin">	
		<div class="body">
			<div id="recuo1Left">				
				Nome de Usuário:<s:textfield name="pessoaDTO.usuario" id="pessoaDTO.usuario" label="Nome de Usuário" />
				Senha:<s:password name="pessoaDTO.senha" id="pessoaDTO.senha" label="Senha" />
				<div class="footer">
					<s:submit value="Login" cssClass="principal" />
					<s:a href="./cliente!load.action">Cadastro</s:a>
				</div>
			</div>
		</div>
	</s:form>
</div>