<%@ taglib prefix="s" uri="/struts-tags" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:form name="form1" method="post" id="form1" action="">
	<h1>
		<c:out value="Listagem de Funcionários" />
	</h1>	
	<table width="100%" cellspacing="1" cellpadding="3" border="1"
		align="center">
		<tr>
			<td></td>			
			<td title="id">
			<input type="checkbox" name="checkAll" id="checkAll"
						onclick="checkUnCheckAll(this, 'idsFuncionario');" />
			</td>
			<td title="Nome">
				<strong><c:out value="Nome" /> </strong>
			</td>
			<td title="CPF">
				<strong><c:out value="CPF" /> </strong>
			</td>	
			<td title="Perfil">
				<strong><c:out value="Perfil" /> </strong>
			</td>
			
						
		</tr>
		<s:if test="${not empty listFuncionarios}">
			<s:iterator value="listFuncionarios" status="stat">
				<s:set name="corLinha" />
				<s:if test="${stat.index%2 eq 0}">
					<s:set name="corLinha" value="'#e6f7ff'" />
				</s:if>
				<s:else>
					<s:set name="corLinha" value="'#FFFFFF'" />
				</s:else>
				<s:if test="${statusFormatado == 'Inválido'}">
					<s:set name="corLinha" value="'#FF6633'" />
				</s:if>
				<tr bgcolor="${corLinha}" onmouseover="this.bgColor='#C9D7DE'"
					onmouseout="this.bgColor='${corLinha}'">
					<td>
						<c:out value="${stat.index+1}" />
					</td>					
					<td>
						<input type="checkbox" name="idsFuncionario"
							id="idsFuncionario" value="<s:property value='id'/>" />
					</td>
					<td>
						<s:a href="funcionario!alterar.action?funcionarioDTO.id=${id}" value="${nome}"><c:out value="${nome}" /></s:a> 
					</td>
					<td>
						<c:out value="${cpf}" />
					</td>
					<td>
						<c:out value="${perfil.descricao}" />
					</td>						
				</tr>
			</s:iterator>
		</s:if>
		<s:else>
			<tr>
				<td colspan="15" align="center">
					<font color="red">Nenhum registro econtrado </font>
				</td>
			</tr>
		</s:else>
	</table>
		<div class="footer">
			<input type="button" value="Cadastrar"
				onClick="selectAction('cadastrar');" class="adicionar" />
			<input type="button" value="Excluir"
				onClick="selectAction('excluir');" class="geral" />
		</div>
</s:form>