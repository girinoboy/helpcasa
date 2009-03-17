<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/cliente!inclui.action?"/>';
		submeter = validaCamposAoIncluir();
	}
	
	//Caso necessite de validação dos campos
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
		alert('O Nome é obrigatório.');
		nome.focus();
		return false;
	}
	if(cpf.value == ''){
		alert('O CPF é obrigatório.');
		cpf.focus();
		return false;
	}
	if(cep.value == ''){
		alert('O CEP é obrigatório.');
		cep.focus();
		return false;
	}
	if(endereco.value == ''){
		alert('O Endereço é obrigatório.');
		endereco.focus();
		return false;
	}
	if(telefone.value == ''){
		alert('O Telefone é obrigatório.');
		telefone.focus();
		return false;
	}
	if(rg.value == ''){
		alert('O RG é obrigatório.');
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