
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<s:form name="form1" id="form1">
		<s:hidden id="cpf" name="solicitacaoDTO.cliente.cpf"
			value="${solicitacaoDTO.cliente.cpf}" />
		<h1>Gerar Boleto - Fatura Detalhada	</h1>
		<div class="body">
			<br />
			<div class="td_rotulo_linha_inf">Cliente: ${solicitacaoDTO.cliente.nome}</div>
			<s:set name="total" value="0"/>
			<br />
			<s:if test="${not empty listFaturaDetalhada}">
				<s:iterator value="listFaturaDetalhada" status="stat">					
					<div class="td_rotulo_linha_inf">Valor do Serviço: ${funcionario.profissao.precoVisita}</div>
					<s:set name="total" value="${funcionario.profissao.precoVisita}" />
				</s:iterator>
			</s:if>
			
			<br />
			<table width="100%" cellspacing="1" cellpadding="3" border="1"
				align="center">
				<tr>
					<td></td>
					<td title="Descrição">
						<strong><c:out value="Descrição" /> </strong>
					</td>
					<td title="Data">
						<strong><c:out value="Data" /> </strong>
					</td>
					<td title="Valor">
						<strong><c:out value="Valor" /> </strong>
					</td>
					<td title="Observações">
						<strong><c:out value="Observações" /> </strong>
					</td>

				</tr>
				
				<s:if test="${not empty listAdicional}">
					<s:iterator value="listAdicional" status="stat">
						<s:set name="corLinha" value="" />
						<s:if test="${stat.index%2 eq 0}">
							<s:set name="corLinha" value="'#e6f7ff'" />
						</s:if>
						<s:else>
							<s:set name="corLinha" value="'#FFFFFF'" />
						</s:else>
						<tr bgcolor="${corLinha}" onmouseover="this.bgColor='#C9D7DE'"
							onmouseout="this.bgColor='${corLinha}'">
							<td>
								<c:out value="${stat.index+1}" />
							</td>
							<td>
								<c:out value="${descricao}" />
							</td>
							<td>
								<s:date format="dd/MM/yyyy HH:mm:ss" name="data" />
							</td>
							<td>
								<c:out value="${valor}" />
							</td>
							<td>
								<c:out value="${observacao}" />
							</td>
						</tr>
						<s:set name="total" value="${total+valor}" />
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
			<br />
			<br />
			<div class="td_rotulo_linha_inf" align="right">
				Total:
				<label style="color: red;">
					R$ ${total}
				</label>
				<s:hidden name="valor" value="${total}"/>
			</div>
			<div class="footer">				
			
				<input type="button" value="Gerar Boleto" 
					onClick="selectAction('boleto');" class="principal" />
				<input type="button" value="Voltar"
					onClick="selectAction('voltar');" class="voltar" />
			</div>
		</div>
	</s:form>
</div>