<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width: 710px;" class="container">
	<s:form name="form1" id="form1">
	<s:hidden id="idCliente" name="solicitacaoDTO.cliente.id" value="${solicitacaoDTO.cliente.id}"/>
		<table cellpadding="1" cellspacing="0" width="100%">
			<tr>
				<td class="label_entrada_dados">
					Data:
				</td>
				<td>
					<s:datetimepicker name="solicitacaoDTO.data" label="Data"
						displayFormat="dd/MM/yyyy" toggleType="explode"
						toggleDuration="200" id="data" required="true"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Servico:
				</td>
				<td>					
					<s:select name="solicitacaoDTO.servico.id" id="id" list="servicos" headerKey="-1" />
				</td>
				
			</tr>
			<tr>
				<td class="label_entrada_dados">
					periodo
				</td>
				<td>
					<s:radio list="#{'1':'manha','2':'tarde','3':'integral'}" name="solicitacaoDTO.periodo" id="periodo"/>
				</td>
			</tr>
			
		</table>

		<div class="footer">
			<input type="button" value="Solicitar"
				onClick="selectAction('incluir');" class="principal" />
			<input type="button" value="Cancelar"
				onClick="selectAction('direcionaLogin');" class="voltar" />
		</div>
	</s:form>
</div>