<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>


function selectAction(action,valor){
	
	var url = '';
	var params = '';
	var submeter;

	if(action =='aplicaClassificacao'){
		url = '<c:url value="/casa/historico!aplicaClassificacao.action?"/>';
		params = 'idNota=' + valor;
		submeter = true;
	}else{
		alert('Solicita��o n�o poder� ser encaminhada, pois a a��o n�o foi encontrada.');
		submeter = false;
	}
	
	//Caso necessite de valida��o dos campos
	if(submeter == true){
		document.getElementById('form1').action = url + params;
		document.getElementById('form1').submit();
	}
}

