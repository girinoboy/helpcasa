<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<s:form name="form1" id="form1">
<s:hidden id="idSolicitacao" value="${profissionalDTO.solicitacao.id}" name="profissionalDTO.solicitacao.id"/>
	<div class="container" style="width: 650px">
		<h2>
			Detalhamento do Servi�o
		</h2>
		</hr>
		<h1>
			Detalhamento do Servi�o
		</h1><br><br><br>
		Servi�o: <s:property value="profissionalDTO.solicitacao.servico.nome"/> <br> 
		data:  <s:property value="data"/> <br>   periodo: <s:property value="profissionalDTO.solicitacao.periodo"/> <br>
		cliente: <s:property value="profissionalDTO.solicitacao.cliente.nome"/> <br>
		valor: <s:property value="profissionalDTO.solicitacao.funcionario.profissao.precoVisita"/> <br>
		situa��o: <s:property value="historicoDTO.status"/> <br>
		<br><br><br><br>
	
			<h1>
				Adicionais
			</h1>
			<table width="100%" cellspacing="1" cellpadding="3" border="1"
				align="center">
				<tr>
					<td></td>
					<td title="id">
						<input type="checkbox" name="checkAll" id="checkAll"
					onclick="checkUnCheckAll(this, 'idsAdicional');" />
					</td>
					<td title="Descricao">
						<strong><c:out value="Descricao" /> </strong>
					</td>
					<td title="Valor">
						<strong><c:out value="Valor" /> </strong>
					</td>
					<td title="Data">
						<strong><c:out value="Data" /> </strong>
					</td>
					<td title="Observa��o">
						<strong><c:out value="Observa��o" /> </strong>
					</td>
					
				</tr>
				<s:if test="${not empty listAdicionais}">
					<s:iterator value="listAdicionais" status="stat">
						<s:set name="corLinha" value="" />
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
								<input type="checkbox" name="idsAdicional" id="idsAdicional"
									value="<s:property value='id'/>" />
							</td>
							<td>
								<c:out value="${descricao}" />
							</td>
							<td>
								R$&nbsp;<c:out value="${valor}" />
							</td>
							<td>
								<c:out value="${data}" />
							</td>
							<td>
								<c:out value="${observacao}" />
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
			<input type="button" value="Finalizar Solicita��o" onClick="selectAction('finalizar');"
				class="principal" />
			<input type="button" value="Adicionar Adicionais"
				onClick="selectAction('adicionar');" class="adicionar" />
			<input type="button" value="Excluir Adicionais"
				onClick="selectAction('excluir');" class="secundario" />
			<input type="button" value="Voltar"
				onClick="selectAction('voltar');" class="voltar" />
		</div>
	</div>
</s:form>