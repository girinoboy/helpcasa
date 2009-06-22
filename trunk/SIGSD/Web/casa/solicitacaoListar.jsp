<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:form name="form1" method="post" id="form1" action="">
	<s:hidden name="solicitacaoDTO.cliente.id"/>
	<h1>
		<c:out value="Listagem de Solicitações" />
	</h1>
	<table width="100%" cellspacing="1" cellpadding="3" border="1"
		align="center">
		<tr>
			<td></td>
			<td title="id">
				<input type="checkbox" name="checkAll" id="checkAll"
					onclick="checkUnCheckAll(this, 'idsSolicitacao');" />
			</td>
			<td title="Serviço Solicitado">
				<strong><c:out value="Serviço Solicitado" /> </strong>
			</td>
			<td title="Data">
				<strong><c:out value="Data" /> </strong>
			</td>
			<td title="Período">
				<strong><c:out value="Período" /> </strong>
			</td>
			<td title="Profissional">
				<strong><c:out value="Profissional" /> </strong>
			</td>


		</tr>
		<s:if test="${not empty listSolicitacoes}">
			<s:iterator value="listSolicitacoes" status="stat">
				<s:set name="corLinha" value=""/>
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
						<input type="checkbox" name="idsSolicitacao" id="idsSolicitacao"
							value="<s:property value='id'/>" />
					</td>
					<td>											
						<c:out value="${servico.nome}" />						
					</td>
					<td>
						<c:out value="${data}" />
					</td>
					<td>
						<s:if test="${periodo eq 1} ">
							<c:out value="Manha" />
						</s:if>
						<s:elseif test="${periodo eq 2}">
							<c:out value="Tarde" />
						</s:elseif>
						<s:else>
							<c:out value="Integral" />
						</s:else>
					</td>
					<td>
						<c:out value="${funcionario.nome}" />
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
		<input type="button" value="Cancelar Solicitação"
			onClick="selectAction('cancela');" class="secundario" />
		<s:if test="${pessoaSessao.perfil.descricao eq 'Cliente'}">
			<input type="button" value="Voltar"
				onClick="selectAction('voltarSolicitacaoCliente');" class="voltar" />
		</s:if>
		<s:else>
			<input type="button" value="Voltar"
				onClick="selectAction('voltarSolicitacao');" class="voltar" />
		</s:else>
	</div>
</s:form>