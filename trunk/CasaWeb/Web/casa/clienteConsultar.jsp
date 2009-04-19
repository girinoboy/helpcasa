<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="width: 710px;" class="container">
	<s:form name="form1" id="form1">
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
					<s:textfield name="clienteDTO.cpf" label="CPF" size="14" id="cpf" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					RG:
				</td>
				<td>
					<s:textfield name="clienteDTO.rg" label="RG" size="10" id="rg" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Endere�o:
				</td>
				<td>
					<s:textfield name="clienteDTO.endereco" label="ENDERE�O" size="100"
						id="endereco" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Cidade
				</td>
				<td>
					<s:textfield name="clienteDTO.cidade" label="CIDADE" size="30" id="cidade" />
					<select id="uf" name="uf">
						<option value="df" selected="selected">
							DF
						</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					CEP
				</td>
				<td>
					<s:textfield name="clienteDTO.cep" label="CEP" size="10" id="cep" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Telefone:*
				</td>
				<td>
					<s:textfield name="clienteDTO.telefone" label="TELEFONE" size="10"
						id="telefone" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Celular:
				</td>
				<td>
					<s:textfield name="clienteDTO.celular" label="CELULAR" size="10" id="celular" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Data de Nascimento:
				</td>
				<td>
					<s:textfield name="clienteDTO.nasc" id="nasc"/>
					<s:date name="clienteDTO.nasc" format="dd/MM/yyyy"
						id="nascimento" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					E-mail:
				</td>
				<td>
					<s:textfield name="clienteDTO.email" label="EMAIL" size="100" id="email" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Nome de usuario:
				</td>
				<td>
					<s:textfield name="clienteDTO.usuario" label="NOME DE USUARIO"
						size="60" id="user" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Senha:
				</td>
				<td>
					<s:password name="clienteDTO.senha" label="SENHA" size="60"
						id="senha" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Repita sua senha:
				</td>
				<td>
					<s:password name="clienteDTO.senha" label="REPITA SUA SENHA"
						size="60" id="senha" />
				</td>
			</tr>
		</table>
		<div class="footer">
			<input type="button" value="Editar" onClick="selectAction('editar');"
				class="principal" />
			<input type="button" value="Cancelar"
				onClick="selectAction('voltar');" class="voltar" />
		</div>
	</s:form>
</div>
