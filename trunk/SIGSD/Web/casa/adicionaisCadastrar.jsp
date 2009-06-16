<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:form name="form1" id="form1" action="/casa/adicionais!inclui.action" onsubmit="return validarForm();">


	<s:hidden id="idSolicitacao" name="adicionaisDTO.solicitacao.id" value="${adicionaisDTO.solicitacao.id}"/>

	<div class="container" style="width: 720px">
		<h1>
			Adicional
		</h1>
		<div class="body">
			
			<label class="label_entrada_dados" style="width:220px;">
				Descrição:*
			</label>
			<label class="c_e_dados_float">
				<s:textfield name="adicionaisDTO.descricao" id="descricao" maxlength="255" size="60"/>
			</label>
			<br class="clr" />
			<label class="label_entrada_dados" style="width:220px;">
				Valor:*
			</label>
			<label class="c_e_dados_float">
				<s:textfield name="adicionaisDTO.valor" id="valor" maxlength="255" size="20"/>
			</label>
			<br class="clr" />
			<label class="label_entrada_dados" style="width:220px;">
				Data:*
			</label>
			<label class="c_e_dados_float">
			<s:textfield name="adicionaisDTO.data" id="data" />
			
			</label>
			<br class="clr" />
			<label class="label_entrada_dados" style="width:220px;">
				Observa&ccedil;&otilde;es gerais:
			</label>
			<label class="c_e_dados_float">
			
			<s:textarea name="adicionaisDTO.observacao" id="observacoes" />
			
			</label>
			<br class="clr" />
		</div>
		<!--container body-->

		<div class="footer">
			<input type="button" value="Salvar" class="principal" onclick="selectAction('incluir');"/>
			<input type="button" value="Cancelar" class="voltar" onclick="selectAction('voltar');"/>

		</div>
		<!--container footer-->
	</div>
	<!--container-->
</s:form>