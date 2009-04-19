<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/login!incluiCliente.action?"/>';
		submeter = validaCamposAoIncluir();
	}else if(action == 'direcionaLogin'){
		url = '<c:url value="/casa/login!load.action?"/>';
		submeter = true;
	}else if(action == 'pesquisar'){
		var cpf = document.getElementById('cpf');
		url = '<c:url value="/casa/cliente!pesquisar.action?"/>';
		params='clienteDTO.cpf='+cpf.value;
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
	return true;
}


function disableAll(){
	var arrayInput = document.getElementsByTagName('input');
	var arrayTextarea = document.getElementsByTagName('textarea');	
	for (i = 0; i < arrayInput.length; i++) {
		if(arrayInput[i].type=='radio' || arrayInput[i].type=='text')
    		arrayInput[i].disabled = true;    	    	
  	}
  	for(i = 0; i < arrayTextarea.length; i++){
  		arrayTextarea[i].disabled=true;
  	}
  	
}

function loadMascara(){
  	//jQuery('#cpf').numeric();
	jQuery('#cpf').mask('999.999.999-99');
	document.getElementById('cpf').value = '';
}