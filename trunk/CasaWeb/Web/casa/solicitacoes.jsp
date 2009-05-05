<%@ taglib prefix="s" uri="/struts-tags" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:form name="form1" method="post" id="form1" action="">
	<h1>
		<c:out value="Listagem de Servi�os" />
	</h1>
	
	<s:hidden id="funcao" name="funcao" value="${funcao}" />

<c:out value="${funcao}"/>
<s:datetimepicker name="clienteDTO.nasc" label="Data de Nascimento"
						displayFormat="dd/MM/yyyy" toggleType="explode"
						toggleDuration="200" />
						<input type="button" value="ok" onclick="selectAction('pesquisar')">
		
	<table width="100%" cellspacing="1" cellpadding="3" border="1"
		align="center">
		<tr>
			<td></td>			
			<td title="id">
			<input type="checkbox" name="checkAll" id="checkAll"
						onclick="checkUnCheckAll(this, 'idsServico');" />
			</td>
			<td title="Data">
				<strong><c:out value="Data" /> </strong>
			</td>
			<td title="Horario">
				<strong><c:out value="Horario" /> </strong>
			</td>		
		</tr>
		<s:if test="${not empty listServicos}">
			<s:iterator value="listServicos" status="stat">
				<s:set name="corLinha" />
				<s:if test="${stat.index%2 eq 0}">
					<s:set name="corLinha" value="'#e6f7ff'" />
				</s:if>
				<s:else>
					<s:set name="corLinha" value="'#FFFFFF'" />
				</s:else>
				<s:if test="${statusFormatado == 'Inv�lido'}">
					<s:set name="corLinha" value="'#FF6633'" />
				</s:if>
				<tr bgcolor="${corLinha}" onmouseover="this.bgColor='#C9D7DE'"
					onmouseout="this.bgColor='${corLinha}'">
					<td>
						<c:out value="${stat.index+1}" />
					</td>					
					<td>
						<input type="checkbox" name="idsServico"
							id="idsServico" value="<s:property value='id'/>" />
					</td>
					<td>
						<c:out value="${nome}" />
					</td>
					<td>
						<c:out value="${profissao.nome}" />
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