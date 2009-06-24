<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width: 650px;" class="container">
	<s:form>
		<div class="body">
			<h1>
			Gerar Boleto Fatura - Simples
			</h1>
			<h2>Clique no serviço para visualizar a fatura detalhada e em seguida gerar o boleto respectivo</h2>
			
			
	<h2 align="center">
		<c:out value="Listagem de Serviços Prestados" />
	</h2>
	<br />
			<div class="td_rotulo_linha_inf">Cliente: ${solicitacaoDTO.cliente.nome}</div>
	<table width="100%" cellspacing="1" cellpadding="3" border="1"
		align="center">
		<tr>
			<td></td>
			
			<td title="Serviço">
				<strong><c:out value="Serviço" /> </strong>
			</td>
			<td title="Profissional">
				<strong><c:out value="Profissional" /> </strong>
			</td>
			<td title="Valor">
				<strong><c:out value="Valor" /> </strong>
			</td>


		</tr>
		<s:if test="${not empty listFaturaBasica}">
			<s:iterator value="listFaturaBasica" status="stat">
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
						<s:a
							href="./casa/solicitacao!consultarFaturaDetalhada.action?solicitacaoDTO.id=${id}&solicitacaoDTO.cliente.cpf=${solicitacaoDTO.cliente.cpf}&solicitacaoDTO.cliente.nome=${solicitacaoDTO.cliente.nome}">
							<c:out value="${servico.nome}" />
						</s:a>
					</td>
					<td>
						<c:out value="${funcionario.nome}" />
					</td>
					<td>
						<s:if test="${total gt 0}">
							R$&nbsp;<c:out value="${total}" />
						</s:if>
						<s:else>
							R$&nbsp;<c:out value="${funcionario.profissao.precoVisita}" />
						</s:else>						
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
		</div>
	</s:form>
</div>