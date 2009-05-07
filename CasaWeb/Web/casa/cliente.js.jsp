<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/login!incluiCliente.action?"/>';
		submeter = true;
	}else if(action == 'direcionaLogin'){
		url = '<c:url value="/casa/login!load.action?"/>';
		submeter = true;
	}else if(action == 'pesquisar'){
		var cpf = document.getElementById('cpf');
		var funcao = document.getElementById('funcao');
		if(funcao.value == 'cliente'){
			url = '<c:url value="/casa/cliente!pesquisar.action?"/>';	
			params='clienteDTO.cpf='+cpf.value;
		}
		else{
			url = '<c:url value="/casa/solicitacao!load.action?"/>';	
			params='solicitacaoDTO.cliente.cpf='+cpf.value;	
		}
		submeter = true;
	}else if(action == 'voltar'){
		
		url = '<c:url value="/casa/cliente!pesquisar.action?"/>';
		params='funcao=cliente';
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
	var cep = document.getElementById('cep');
	var endereco = document.getElementById('endereco');
	var telefone = document.getElementById('telefone');
	var rg = document.getElementById('rg');
	
	var cidade = document.getElementById('cidade');
	var uf = document.getElementById('id');
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
	if(rg.value == ''){
		alert('O RG � obrigat�rio.');
		rg.focus();
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
	//document.getElementById('cpf').value = '';
	jQuery('#cep').mask('99999-999');
	jQuery('#telefone').mask('(99) 9999-9999');
	jQuery('#celular').mask('(99) 9999-9999');
}