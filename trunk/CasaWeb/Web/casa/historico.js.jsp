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
		alert('Solicitação não poderá ser encaminhada, pois a ação não foi encontrada.');
		submeter = false;
	}
	
	//Caso necessite de validação dos campos
	if(submeter == true){
		document.getElementById('form1').action = url + params;
		document.getElementById('form1').submit();
	}
}

