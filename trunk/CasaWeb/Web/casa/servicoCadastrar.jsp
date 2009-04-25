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
					<s:textfield id="servico.nome" name="servico.nome" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Profissão:
				</td>
				<td>
					<s:textfield id="profissao.idprofissao"
						name="profissao.idprofissao" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Descricao:
				</td>
				<td>
					<s:textfield id="servico.descricao"
						name="servico.descricao" />
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