<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form name="form1" id="form1">
	<div class="container" style="width: 650px">
		<h2>
			Agenda Virtual
		</h2>
		</hr>
		<h1>
			Agenda Virtual
		</h1><br><br><br>
		<s:datetimepicker name="profissional.dia"
			label="Dia que deseja visualizar" theme="ajax"
			displayFormat="dd/MM/yyyy" onblur="selectAction('listar')" />
		<input type="button" value="ok" onclick="selectAction('listar')">
		<br><br><br><br>
		<s:if test="${profissionalDTO.listar}">
			<h1>Lista de Serviços</h1>
			<table width="200" height="2" border="1">
<tbody><tr>
<td><label>Data e Hora</label></td>
<td><a href="">Serviço</a></td>
<td>Profissional/Cliente;</td>
<td>Valor</td>
<td>Classificação</td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td></tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td><html:select value="0" property="combo1" name="combo1"><html:option value="0" key="Selecione"></html:option><html:option value="5" key="Péssimo"></html:option></html:select></td></tr>
</tbody></table>
		</s:if>
	</div>
</s:form>