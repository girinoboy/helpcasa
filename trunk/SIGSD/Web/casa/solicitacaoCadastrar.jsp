<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <s:hidden id="distancia" name="solicitacao.funcionario.distancia"/>
<div style="width: 710px;" class="container">
	<s:form name="form1" id="form1" onsubmit="return false">
	<s:hidden id="idCliente" name="solicitacaoDTO.cliente.id" value="${solicitacaoDTO.cliente.id}"/>
	<s:hidden id="cpf" name="solicitacaoDTO.cliente.cpf" value="${pessoaSessao.cpf}"/>
		<table cellpadding="1" cellspacing="0" width="100%">
			<tr>
				<td class="label_entrada_dados">
					Data:*
				</td>
				<td>
					<s:date name="solicitacaoDTO.data" format="dd/MM/yyyy" id="formatada"/>
					<s:textfield id="data" name="data" value="%{formatada}"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Servico:*
				</td>
				<td>					
					<s:select name="solicitacaoDTO.servico.id" id="id" list="servicos" headerKey="-1" />
				</td>
				
			</tr>
			<tr><td></td><td><div id="distance" style="display: none;">distancia:</div> </td></tr> 
			<tr>
				<td class="label_entrada_dados">
					Período:*
				</td>
				<td>
				<s:set name="periodos" value="3" scope="session"/>
				<s:if test="${not empty listHorariosDisponiveis}">
					<s:iterator value="listHorariosDisponiveis" status="stat"><!--
						
						Periodo:<c:out value="${periodo}" default="null"></c:out><br>
						idFuncionario:<c:out value="${funcionario.id}" default="null"></c:out><br>
						CEP:<c:out value="${funcionario.cep}" default="null"></c:out><br>
						--><s:hidden name="distancia" id="distancia${stat.index+1}" value="3" onmouseup="javascript:setDirections('${pessoaSessao.cep}','${funcionario.cep}','pt_BR').substr(0,4)"/> 	
						<s:hidden name="idFuncionario" id="idFuncionario${stat.index+1}" value="${funcionario.id}" />
						<s:hidden name="cep" id="cep${stat.index+1}" value="${funcionario.cep}" />
						<br>
						<script>
							document.getElementById('distancia${stat.index+1}').value = setDirections('${pessoaSessao.cep}','${funcionario.cep}','pt_BR').substr(0,4);
						</script>
						<s:set name="max" value="${stat.index+1}" scope="session"/>
						<s:if test="${periodo eq 1}">
							<s:set name="periodos" value="1" scope="session"/>
						</s:if>
						<s:elseif test="${periodo eq 2}">
							<s:set name="periodos" value="2" scope="session"/>
						</s:elseif>
						<s:elseif test="${periodo eq 0}">
							<s:set name="periodos" value="0" scope="session"/>
						</s:elseif>
						<s:else>
							<s:set name="periodos" value="3" scope="session"/>
						</s:else>
					</s:iterator>									
				</s:if>
				
					<s:hidden name="max" id="max" value="${sessionScope.max}" />					
					<br>
					<!-- CEP - cliente:<c:out value="${pessoaSessao.cep}" default="null"></c:out><br> -->
					<s:if test="${periodos eq 0}">
						<s:radio list="#{'1':'manha','2':'tarde','3':'integral'}" name="solicitacaoDTO.periodo" id="periodo" />
					</s:if>
					<s:elseif test="${periodos eq 1}">
						<s:radio list="#{'1':'manha'}" name="solicitacaoDTO.periodo" id="periodo" disabled="true"/>
						<s:radio list="#{'2':'tarde'}" name="solicitacaoDTO.periodo" id="periodo" />
						<s:radio list="#{'3':'integral'}" name="solicitacaoDTO.periodo" id="periodo" disabled="true"/>
					</s:elseif>
					<s:elseif test="${periodos eq 2}">
						<s:radio list="#{'1':'manha'}" name="solicitacaoDTO.periodo" id="periodo" />
						<s:radio list="#{'2':'tarde','3':'integral'}" name="solicitacaoDTO.periodo" id="periodo" disabled="true"/>
					</s:elseif>
					<s:else>
						<s:radio list="#{'1':'manha','2':'tarde','3':'integral'}" name="solicitacaoDTO.periodo" id="periodo" disabled="true"/>
					</s:else>
					
				</td>
				
			</tr>

		</table>

		<div class="footer">
			<input type="button" value="Solicitar"
				onClick="selectAction('incluir',true);" class="principal" />
			<input type="button" value="Verificar Horários Disponiveis" 
				onclick="selectAction('disponiveis');" class="secundario"/>
			<!--<input type="button" value="distancia" 
				onclick="caculaDistanciaFixa(); "/>-->
			<input type="button" value="Cancelar Solicitações"
				onClick="selectAction('listar');" class="geral" />	
			<s:if test="${pessoaSessao.perfil.descricao eq 'Administrador' or pessoaSessao.perfil.descricao eq 'Atendente'}">
				<input type="button" value="Cancelar"
					onClick="selectAction('voltar');" class="voltar" />
			</s:if>
		</div>
	</s:form>
</div>

<div id="directions" style="width: 275px; display: none;" display="block"></div>
<div id="map_canvas" style="width: 310px; height: 400px; display: none; " display="none"></div>