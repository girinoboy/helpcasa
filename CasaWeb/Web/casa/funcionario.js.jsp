<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/funcionario!inclui.action?"/>';
		submeter = validaCamposAoIncluir();
	}else if(action == 'direcionaLogin'){
		url = '<c:url value="/casa/login!load.action?"/>';
		submeter = true;
	}else if(action == 'voltar'){		
		url = '<c:url value="/casa/funcionario!load.action?"/>';		
		submeter = true;
	}else if(action == 'cadastrar'){
		url = '<c:url value="/casa/funcionario!cadastrar.action?"/>';
		submeter = true;
	}else{
		alert('A��o n�o encontrada.');
		submeter = false;
	}
	
	//Caso necessite de valida��o dos campos
	if(submeter == true){
		document.getElementById('form1').action = url + params;
		document.getElementById('form1').submit(); 
	}
	
}

function validaCamposAoIncluir(){
	var nome = document.getElementById('nome');
	var cpf = document.getElementById('cpf');
	var cpf = document.getElementById('cpf');
	var telefone = document.getElementById('telefone');
	var matricula = document.getElementById('matricula');
	
	
	var celular = document.getElementById('celular');
	var nasc = document.getElementById('nasc');
	var email = document.getElementById('email');
	var usuario = document.getElementById('usuario');
	var senha = document.getElementById('senha');
	var senhaRepita = document.getElementById('senhaRepita');
	
	if(nome.value == ''){
		alert('O Nome � obrigat�rio.');
		nome.focus();
		return false;
	}
	if(cpf.value == ''){
		alert('O CPF � obrigat�rio.');
		cpf.focus();
		return false;
	}
	if(cep.value == ''){
		alert('O CEP � obrigat�rio.');
		cep.focus();
		return false;
	}
	if(endereco.value == ''){
		alert('O Endere�o � obrigat�rio.');
		endereco.focus();
		return false;
	}
	if(telefone.value == ''){
		alert('O Telefone � obrigat�rio.');
		telefone.focus();
		return false;
	}
	if(matricula.value == ''){
		alert('A Matricula � obrigat�ria.');
		matricula.focus();
		return false;
	}
	if(cidade.value == ''){
		alert('A Cidade � obrigat�ria.');
		cidade.focus();
		return false;
	}
	if(uf.value == ''){
		alert('A UF � obrigat�ria.');
		uf.focus();
		return false;
	}
	if(celular.value == ''){
		alert('O Celular � obrigat�rio.');
		celular.focus();
		return false;
	}
	if(nasc.value == ''){
		alert('O Nascimento � obrigat�rio.');
		nasc.focus();
		return false;
	}/*
	if(email.value == ''){
		alert('O Email � obrigat�rio.');
		email.focus();
		return false;
	}*/
	if(usuario.value == ''){
		alert('O Usuario � obrigat�rio.');
		usuario.focus();
		return false;
	}
	if(senha.value == ''){
		alert('A Senha � obrigat�rio.');
		senha.focus();
		return false;
	}
	if(senhaRepita.value != senha.value){
		alert('Senha n�o confere.');
		senhaRepita.focus();
		return false;
	}
	return true;
}

function checkUnCheckAll(check, nameCheckBox){

	var checkBoxs = document.getElementsByName(nameCheckBox);
	if(checkBoxs != null && checkBoxs.length > 0){
		if(check.checked){
			for(i=0; i < checkBoxs.length; i++){
				checkBoxs[i].checked = true;
			}
		}else{
			for(i=0; i < checkBoxs.length; i++){
				checkBoxs[i].checked = false;
			}
		}
	}
}

function disableAll(){
	var arrayInput = document.getElementsByTagName('input');
	var arrayTextarea = document.getElementsByTagName('textarea');	
	for (i = 0; i < arrayInput.length; i++) {
		if(arrayInput[i].type=='radio' || arrayInput[i].type=='text' || arrayInput[i].type=='password')
    		arrayInput[i].disabled = true;    	    	
  	}
  	for(i = 0; i < arrayTextarea.length; i++){
  		arrayTextarea[i].disabled=true;
  	}
  	
}

function loadMascara(){
  	//jQuery('#cpf').numeric();
	jQuery('#cpf').mask('999.999.999-99');
//	document.getElementById('cpf').value = '';
	jQuery('#cep').mask('99999-999');
	jQuery('#matricula').mask('999999-9');
	jQuery('#telefone').mask('(99) 9999-9999');
	jQuery('#celular').mask('(99) 9999-9999');
}