<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'incluir'){
		url = '<c:url value="/casa/solicitacao!solicitacaoInclui.action?"/>';
		submeter = validaCamposAoIncluir();
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
	var data = document.getElementById('data');
	var id = document.getElementById('id');
	var periodo1 = document.getElementById('periodo1');
	var periodo2 = document.getElementById('periodo2');
	var periodo3 = document.getElementById('periodo3');
	
	if(data.value == ''){
		alert('A Data � obrigat�ria.');
		data.focus();
		return false;
	}
	if(id.value == 0){
		alert('O Servi�o � obrigat�rio.');
		id.focus();
		return false;
	}
	if(periodo1.check == false && periodo2.check == false && periodo3.check == false){
		alert('O Periodo � obrigat�rio.');
		id.focus();
		return false;
	}
	
	return true;
}