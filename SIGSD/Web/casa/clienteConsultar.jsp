<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="width: 710px;" class="container">
	<s:form name="form1" id="form1">
		<s:hidden name="naoPesquisar" id="naoPesquisar" value="${requestScope.naoPesquisar}"/>
		<s:hidden name="clienteDTO.id" id="id"/>
		
		<table cellpadding="1" cellspacing="0" width="100%">
			<tr>
				<td class="label_entrada_dados">
					Nome:*
				</td>
				<td>
					<s:textfield name="clienteDTO.nome" label="NOME" size="60"
						id="nome" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					CPF:*
				</td>
				<td>
					<s:textfield name="clienteDTO.cpf" label="CPF" size="14" id="cpf" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					RG:*
				</td>
				<td>
					<s:textfield name="clienteDTO.rg" label="RG" size="10" id="rg" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Endereço:*
				</td>
				<td>
					<s:textfield name="clienteDTO.endereco" label="ENDEREÇO" size="89"
						id="endereco" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Cidade:*
				</td>
				<td>
					<s:textfield name="clienteDTO.cidade" label="CIDADE" size="30" id="cidade" readonly="true"/>
					<label class="table">
						UF:*
					</label>
					<s:select name="clienteDTO.uf.id" id="uf" label="UF" list="ufs" headerKey="-1" disabled="true"/>
					<!--<s:property value="clienteDTO.uf.descricao"/>					
				--></td>
				
			</tr>
			<tr>
				<td class="label_entrada_dados">
					CEP:*
				</td>
				<td>
					<s:textfield name="clienteDTO.cep" label="CEP" size="10" id="cep" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Telefone:*
				</td>
				<td>
					<s:textfield name="clienteDTO.telefone" label="TELEFONE" size="15"
						id="telefone" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Celular:*
				</td>
				<td>
					<s:textfield name="clienteDTO.celular" label="CELULAR" size="15" id="celular" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Data de Nascimento:*
				</td>
				<td>
				<s:textfield name="clienteDTO.nasc" id="nasc" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					E-mail:*
				</td>
				<td>
					<s:textfield name="clienteDTO.email" label="EMAIL" size="89" id="email" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Nome de usuario:*
				</td>
				<td>
					<s:textfield name="clienteDTO.usuario" label="NOME DE USUARIO"
						size="60" id="usuario" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Senha:*
				</td>
				<td>
					<s:password name="clienteDTO.senha" label="SENHA" size="60"
						id="senha" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Repita sua senha:*
				</td>
				<td>
					<s:password name="clienteDTO.senhaRepita" label="REPITA SUA SENHA"
						size="60" id="senhaRepita" />
				</td>
			</tr>
		</table>		
		
		<div class="footer">
		<s:hidden id="alterado" name="alterado"/>
		<s:hidden id="telaConsulta" name="telaConsulta"/>
		 <s:if test="${not alterado}">
		 	<script>disableAll();</script>
			<input type="button" value="Alterar" onClick="selectAction('alterar');"
				class="principal" />
			<input type="button" value="Excluir"
				onClick="selectAction('exclui');" class="secundario" />
			<input type="button" value="Cancelar"
				onClick="selectAction('voltar');" class="voltar" />
		</s:if>
		<s:else>
			<input type="button" value="Salvar" onClick="selectAction('altera');"
				class="principal" />
			<s:if test="${pessoaSessao.perfil.descricao eq 'Cliente'}">
			<input type="button" value="Cancelar"
				onClick="selectAction('voltarCliente');" class="voltar" />
			</s:if>
			<s:else>
				<input type="button" value="Cancelar"
				onClick="selectAction('voltar');" class="voltar" />			
			</s:else>
		</s:else>
		</div>
	</s:form>
</div>
