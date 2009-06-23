<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:form name="form1" method="post" id="form1" action="">
<s:hidden id="cpf" name="historicoDTO.solicitacao.cliente.cpf"/>
	<h1>
		<c:out value="Nome do Serviço" />
	</h1>	
	<table width="100%" cellspacing="1" cellpadding="3" border="1"
		align="center">
		<tr>
			<td></td>			
			<td title="Serviço">
				<strong><c:out value="Serviço" /> </strong>
			</td>
			<td title="Data">
				<strong><c:out value="Data" /> </strong>
			</td>	
			<td title="Periodo">
				<strong><c:out value="Periodo" /> </strong>
			</td>
			<td title="Profissional">
				<strong><c:out value="Profissional" /> </strong>
			</td>
			<td title="Total">
				<strong><c:out value="Total" /> </strong>
			</td>
			<td title="Classificação">
				<strong><c:out value="Classificação" /> </strong>
			</td>			
		</tr>
		<s:if test="${not empty listHistorico}">
			<s:iterator value="listHistorico" status="stat">
				<s:set name="corLinha" />
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
						<s:a href="./casa/historico!consultarHistorico.action?historicoDTO.solicitacao.id=${solicitacao.id}&historicoDTO.solicitacao.cliente.cpf=${historicoDTO.solicitacao.cliente.cpf}"><c:out value="${solicitacao.servico.nome}" /></s:a>
					</td>
					<td>
						<s:date format="dd/MM/yyyy HH:mm:ss"  name="data" />
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
						<c:out value="${solicitacao.funcionario.nome}" />
					</td>
					<td>
						<s:if test="${total gt 0}">
							<c:out value="${total}" />
						</s:if>
						<s:else>
							R$&nbsp;<c:out value="${solicitacao.funcionario.profissao.precoVisita}" />
						</s:else>
					</td>
					<s:if test="${solicitacao.nota.id eq '1'}">
						<td>
						<s:select list="listNota" name="notas"
						id="notas${stat.index+1}" headerKey="0" headerValue="Selecione..."
						listValue="descricao" listKey="${solicitacao.id}"
						cssStyle="vertical-align: top;" onchange="selectAction('aplicaClassificacao',this);"/>
							<!--<s:select name="solicitacao.nota.id"  id="idNota" list="#{'false':'Bom','${nota.id}':'Otimo'}" onchange="selectAction('aplicaClassificacao',this.value);"/>-->
						</td>
					</s:if>
					<s:else>						
						<td>
							<c:out value="${solicitacao.nota.descricao}" />							
						</td>
					</s:else>
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
</s:form>