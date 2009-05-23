<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<s:form name="form1" id="form1">
<s:hidden id="idSolicitacao" value="${profissionalDTO.historico.solicitacao.id}" name="profissionalDTO.historico.solicitacao.id"/>
	<div class="container" style="width: 650px">
		<h2>
			Detalhamento do Serviço
		</h2>
		</hr>
		<h1>
			Detalhamento do Serviço
		</h1><br><br><br>
		<label class="label_entrada_dados" style="width: 220px;">
			Serviço:
		</label>
		<label class="c_e_dados_float">
			<s:property
				value="profissionalDTO.historico.solicitacao.servico.nome" />
		</label>
		<br class="clr" />
		<label class="label_entrada_dados" style="width: 220px;">
			data:
		</label>
		<label class="c_e_dados_float">
			<s:property value="data" />
		</label>
		<br class="clr" />
		<label class="label_entrada_dados" style="width: 220px;">
			periodo:
		</label>
		<label class="c_e_dados_float">
			<s:property value="profissionalDTO.historico.solicitacao.periodo" />
		</label>
		<br class="clr" />
		<label class="label_entrada_dados" style="width: 220px;">
			cliente:
		</label>
		<label class="c_e_dados_float">
			<s:property
				value="profissionalDTO.historico.solicitacao.cliente.nome" />
		</label>
		<br class="clr" />
		<label class="label_entrada_dados" style="width: 220px;">
			valor:
		</label>
		<label class="c_e_dados_float">
			<s:property
				value="profissionalDTO.historico.solicitacao.funcionario.profissao.precoVisita" />
		</label>
		<br class="clr" />
		<label class="label_entrada_dados" style="width: 220px;">
			situação:
		</label>
		<label class="c_e_dados_float">
			<s:property value="profissionalDTO.historico.status" />
		</label>
		<br class="clr" />


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
					<td title="Observação">
						<strong><c:out value="Observação" /> </strong>
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
						<s:if test="${statusFormatado == 'Inválido'}">
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
			<input type="button" value="Finalizar Solicitação" onClick="selectAction('finalizar');"
				class="principal" />
			<input type="button" value="Adicionar Extras"
				onClick="selectAction('adicionar');" class="adicionar" />
			<input type="button" value="Excluir Adicionais"
				onClick="selectAction('excluir');" class="secundario" />
			<input type="button" value="Voltar"
				onClick="selectAction('voltar');" class="voltar" />
		</div>
	</div>
</s:form>