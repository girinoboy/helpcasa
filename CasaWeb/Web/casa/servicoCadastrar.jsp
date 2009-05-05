<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width: 710px;" class="container">
	<s:form name="form1" method="post" id="form1" action="">
		<h1>
			Cadastro de Serviços
		</h1>

		<table cellpadding="1" cellspacing="0" width="100%">
			<tr>

				<td class="label_entrada_dados">
					Serviço:
				</td>
				<td>
					<s:textfield id="servicoDTO.nome" name="servicoDTO.nome" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Profissão:
				</td>
				<td>
					<s:select name="servicoDTO.profissaoDTO.id" id="idprofissao" label="profissao" list="profissoes" headerKey="-1" />					
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Descricao:
				</td>
				<td>
					<s:textfield id="servicoDTO.descricao"
						name="servicoDTO.descricao" />
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