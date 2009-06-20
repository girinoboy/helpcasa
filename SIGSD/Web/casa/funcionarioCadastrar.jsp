<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="width: 710px;" class="container">
	<s:form name="form1" id="form1">
		<h1>
			Cadastrar Funcionario
		</h1>

		<table cellpadding="1" cellspacing="0" width="100%">
			<tr>
				<td class="label_entrada_dados">
					Nome:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.nome" label="NOME" size="60"
						id="nome" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Matricula:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.matricula" label="NOME" size="60"
						id="matricula" readonly="true"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Profissão:	
				</td>
				<td><s:select name="funcionarioDTO.profissao.id" id="idprofissao" label="profissao" list="profissoes" headerKey="0" headerValue="Selecione" /></td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					CPF:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.cpf" label="CPF" size="14" id="cpf" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					RG:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.rg" label="RG" size="10" id="rg" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Telefone:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.telefone" label="TELEFONE" size="10"
						id="telefone" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Celular:
				</td>
				<td>
					<s:textfield name="funcionarioDTO.celular" label="CELULAR" size="10" id="celular" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					E-mail:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.email" label="EMAIL" size="89" id="email" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Data de Nascimento:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.nasc" id="nasc"/>
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Nome de usuario:*
				</td>
				<td>
					<s:textfield name="funcionarioDTO.usuario" label="NOME DE USUARIO"
						size="60" id="usuario" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Senha:*
				</td>
				<td>
					<s:password name="funcionarioDTO.senha" label="SENHA" size="60"
						id="senha" />
				</td>
			</tr>
			<tr>
				<td class="label_entrada_dados">
					Repita sua senha:*
				</td>
				<td>
					<s:password name="funcionarioDTO.senhaRepita" label="REPITA SUA SENHA"
						size="60" id="senhaRepita" />
				</td>
			</tr>
		</table>

		<div class="footer">
			<input type="button" value="Cadastrar"
				onClick="selectAction('incluir');" class="principal" />
			<input type="button" value="Cancelar"
				onClick="selectAction('voltar');" class="voltar" />
		</div>
	</s:form>
</div>