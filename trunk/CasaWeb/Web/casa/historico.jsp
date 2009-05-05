<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<s:form name="form1" method="post" id="form1" action="">
	<h1>
		<c:out value="Consulta do Historico de Serviço" />
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
				<s:if test="${statusFormatado == 'Inválido'}">
					<s:set name="corLinha" value="'#FF6633'" />
				</s:if>
				<tr bgcolor="${corLinha}" onmouseover="this.bgColor='#C9D7DE'"
					onmouseout="this.bgColor='${corLinha}'">
					<td>
						<c:out value="${stat.index+1}" />
					</td>					
					<td>
						<c:out value="${historico.pi.registro}" />
					</td>
					<td>
						<c:out value="${historico.usuario.nome}" />
					</td>	
					<td>
						<s:date format="dd/MM/yyyy HH:mm:ss"  name="data" />
					</td>
					<td>
						<c:out value="${historico.desistencias.pedidoInspecaoPericial.finalidade}" />
					</td>
					<s:if test="${data eq 'S'}">
						<td>
							<c:out value="${nota.descricao}" />
						</td>
					</s:if>
					<s:else>						
						<td>
							<s:select name="nota.id"  id="d" list="#{'false':'Bom','${nota.id}':'Otimo'}" onchange="selectAction('concedeDesistencia',this.value);"/>							
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