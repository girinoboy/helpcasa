<%@ taglib prefix="s" uri="/struts-tags" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:form name="form1" id="form1">
		<s:textfield name="clienteDTO.nome" label="NOME" size="60" />
		<c:out value="${clienteDTO.nome}" />
		<s:textfield name="cpf" label="CPF" size="14" />
		<s:textfield name="cep" label="CEP" size="10" />
		<s:textfield name="endereco" label="ENDEREÇO" size="100" />
		<s:textfield name="telefone" label="TELEFONE" size="10" />
		<s:textfield name="rg" label="RG" size="10" />
		<s:textfield name="email" label="EMAIL" size="100" />
</s:form>

