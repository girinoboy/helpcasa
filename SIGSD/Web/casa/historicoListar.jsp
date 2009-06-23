<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:form name="form1" method="post" id="form1" action="">
<s:hidden id="cpf" name="historicoDTO.solicitacao.cliente.cpf"/>
	<h1>
		<c:out value="Consulta do Historico de Serviço" />
	</h1>	
	<table width="100%" cellspacing="1" cellpadding="3" border="1"
		align="center">
		<tr>
			<td></td>			
			
			<td title="Data">
				<strong><c:out value="Data" /> </strong>
			</td>	
			<td title="Periodo">
				<strong><c:out value="Periodo" /> </strong>
			</td>
			<td title="Status">
				<strong><c:out value="Status" /> </strong>
			</td>
			<td title="Perfil">
				<strong><c:out value="Perfil" /> </strong>
			</td>
			<td title="Nome">
				<strong><c:out value="Nome" /> </strong>
			</td>			
		</tr>
		<s:if test="${not empty listaHistoricoDetalhada}">
			<s:iterator value="listaHistoricoDetalhada" status="stat">
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
						<s:date name="data" format="dd/MM/yyyy"/>
					</td>
					<td>
						<s:if
							test="${solicitacao.periodo eq 1} ">
							<c:out value="Manha" />
						</s:if>
						<s:elseif
							test="${solicitacao.periodo eq 2}">
							<c:out value="Tarde" />
						</s:elseif>
						<s:else>
							<c:out value="Integral" />
						</s:else>
					</td>	
					<td>
						<s:if test="${status eq 1} ">
							<c:out value="Solicitado" />
						</s:if>
						<s:elseif test="${status eq 2}">
							<c:out value="Cancelado" />
						</s:elseif>
						<s:elseif test="${status eq 3}">
							<c:out value="Finalizado" />
						</s:elseif>
						<s:else>
							<c:out value="Pago" />
						</s:else>
					</td>
					<td>
						<c:out value="${solicitacao.funcionario.nome}" />
					</td>
					<td>
						<s:if test="${total gt 0}">
							<c:out value="${total}" />
						</s:if>
						<s:else>
							R$&nbsp;<c:out value="${solicitacao.funcionario.profissao.precoVisita}" />
						</s:else>
					
						
						<td>
							<c:out value="${solicitacao.nota.descricao}" />							
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
			<input type="button" value="Voltar"
				onClick="selectAction('voltar');" class="voltar" />
		</div>
</s:form>