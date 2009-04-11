<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width: 710px;" class="container">
	<s:form name="form1" id="form1">
		<h1>
			Aviso Alguns dados não pode ser alterados! Seus respectivos capos
			encontran-se desabilitados
		</h1>

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
					<s:textfield name="cpf" label="CPF" size="14" id="cpf" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					RG:
				</td>
				<td>
					<s:textfield name="rg" label="RG" size="10" id="rg" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Endereço:
				</td>
				<td>
					<s:textfield name="endereco" label="ENDEREÇO" size="100"
						id="endereco" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Cidade
				</td>
				<td>
					<s:textfield name="cidade" label="CIDADE" size="30" id="cidade" />
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
					<s:textfield name="cep" label="CEP" size="10" id="cep" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Telefone:*
				</td>
				<td>
					<s:textfield name="telefone" label="TELEFONE" size="10"
						id="telefone" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Celular:
				</td>
				<td>
					<s:textfield name="celular" label="CELULAR" size="10" id="telefone" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Data de Nascimento:
				</td>
				<td>
					<s:datetimepicker name="user.birthday" label="Data de Nascimento"
						displayFormat="dd/MM/yyyy" toggleType="explode"
						toggleDuration="200" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					E-mail:
				</td>
				<td>
					<s:textfield name="email" label="EMAIL" size="100" id="email" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Nome de usuario:
				</td>
				<td>
					<s:textfield name="clienteDTO.nome" label="NOME DE USUARIO"
						size="60" id="user" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Senha:
				</td>
				<td>
					<s:password name="clienteDTO.nome" label="SENHA" size="60"
						id="senha" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Repita sua senha:
				</td>
				<td>
					<s:password name="clienteDTO.nome" label="REPITA SUA SENHA"
						size="60" id="rp" />
				</td>
			</tr>
		</table>

		<div class="footer">
			<input type="button" value="Cadastrar"
				onClick="selectAction('incluir');" class="principal" />
			<input type="button" value="Cancelar"
				onClick="selectAction('direcionaLogin');" class="voltar" />
		</div>
	</s:form>
</div>