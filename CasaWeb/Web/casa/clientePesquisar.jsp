<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form name="form1" id="form1">
	<div class="container" style="width: 650px">
		<h1>
			Pesquisar Cliente
		</h1>
		<h2>
			Digite o CPF do cliente que deseje pesquisar e clique no botão
			pesquisar
		</h2>
		CPF:<s:textfield name="cliente.cpf" id="cpf"  />
		<s:submit value="Pesquisar" onclick="selectAction('pesquisar');" cssClass="principal"/>
	</div>
</s:form>