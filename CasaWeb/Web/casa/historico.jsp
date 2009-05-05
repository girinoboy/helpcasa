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
			<td title="Matricula">
				<strong><c:out value="Matricula" /> </strong>
			</td>
			<td title="Nome">
				<strong><c:out value="Nome" /> </strong>
			</td>	
			<td title="Data desistencia">
				<strong><c:out value="Data desistencia" /> </strong>
			</td>
			<td title="Finalidade">
				<strong><c:out value="Finalidade" /> </strong>
			</td>
			<td title="Desistencia Concedida">
				<strong><c:out value="Desistencia Concedida" /> </strong>
			</td>			
		</tr>
		<s:if test="${not empty listaDesistencias}">
			<s:iterator value="listaDesistencias" status="stat">
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
						<c:out value="${gerenciamentoDesistencias.pi.registro}" />
					</td>
					<td>
						<c:out value="${gerenciamentoDesistencias.usuario.nome}" />
					</td>	
					<td>
						<s:date format="dd/MM/yyyy HH:mm:ss"  name="gerenciamentoDesistencias.desistencias.dataDesistencia" />
					</td>
					<td>
						<c:out value="${gerenciamentoDesistencias.desistencias.pedidoInspecaoPericial.finalidade}" />
					</td>
					<s:if test="${gerenciamentoDesistencias.desistencias.desistenciaConcedida eq 'S'}">
						<td>
							<c:out value="${gerenciamentoDesistencias.desistencias.desistenciaConcedida}" />
						</td>
					</s:if>
					<s:else>						
						<td>
							<s:select name="gerenciamentoDTO.gerenciamentoDesistencias.desistencias.pedidoInspecaoPericial.id"  id="gerenciamentoDTO.gerenciamentoDesistencias.desistencias.pedidoInspecaoPericial.id" list="#{'false':'Não','${gerenciamentoDesistencias.desistencias.pedidoInspecaoPericial.id}':'Sim'}" onchange="selectAction('concedeDesistencia',this.value);"/>							
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