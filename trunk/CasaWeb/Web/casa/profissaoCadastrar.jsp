<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width: 710px;" class="container">
	<s:form name="form1" method="post" id="form1" action="">
		<h1>
			Cadastro de Profiss�o
		</h1>

		<table cellpadding="1" cellspacing="0" width="100%">
			<tr>

				<td class="label_entrada_dados">
					Profiss�o:
				</td>
				<td>
					<s:textfield id="profissaoDTO.nome" name="profissaoDTO.nome" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					PrecoVisita:
				</td>
				<td>
					<s:textfield id="profissaoDTO.precoVisita"
						name="profissaoDTO.precoVisita" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Descricao:
				</td>
				<td>
					<s:textfield id="profissaoDTO.descricao" name="profissaoDTO.descricao" />
				</td>
			</tr>
		</table>

		<div class="footer">
			<input type="button" value="Salvar"
				onClick="selectAction('incluir');" class="adicionar" />
			<input type="button" value="Cancelar"
				onClick="selectAction('voltar');" class="voltar" />
		</div>
	</s:form>
</div>