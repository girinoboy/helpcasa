<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

function selectAction(action){
	
	var url = '';
	var params = '';
	var submeter;
	
	if(action == 'voltar'){
			var cpf = document.getElementById('cpf');
			url = '<c:url value="/casa/solicitacao!consultarFaturaBasica.action?"/>';	
			//params='solicitacaoDTO.cliente.cpf='+cpf.value;	
			submeter = true;
	}else if(action == 'boleto'){
			var cpf = document.getElementById('cpf');
			url = '<c:url value="/casa/boleto!gerarBoleto.action?"/>';	
			params='clienteDTO.cpf='+cpf.value;	
			submeter = true;
	}else{
		alert('Ação não encontrada.');
		submeter = false;
	}
	
	//Caso necessite de validação dos campos
	if(submeter == true){
		document.getElementById('form1').action = url + params;
		document.getElementById('form1').submit(); 
	}
	
}